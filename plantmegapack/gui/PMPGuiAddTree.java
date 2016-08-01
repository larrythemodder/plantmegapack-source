package plantmegapack.gui;

import java.io.IOException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSapling;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataSetTrees;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.tree.PMPTree;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiAddTree
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private PMPBiomeType biome;
	private PMPHabitat habitat;
	private PMPDataSetTrees dataTrees;
	private PMPGuiListbox treeList;
	
	public PMPGuiAddTree(GuiScreen parent, PMPBiomeType biome, PMPHabitat habitat, PMPDataSetTrees dataTrees) {
		super(parent, "", 2);
		this.biome = biome;
		this.habitat = habitat;
		this.dataTrees = dataTrees;
		this.name = String.format("%s - %s %s", new Object[] { I18n.translateToLocal("gui.addTree"), this.biome.getLocalizedName(), this.habitat.getLocalizedName() });
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();
		int xPos = this.width / 2;// - ('�' + 2);
		int yPos = 36;
		getClass();
		getClass();
		this.treeList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		populateTreeList();
		this.treeList.selectListItem(0);
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.treeList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				this.dataTrees.addTree(this.treeList.getSelectedItemKey());
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				break;
			}
		}
		super.actionPerformed(button);
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.treeList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		int xPos = this.width / 2 + 2;
		int yPos = 40;
		if ((this.treeList.getSelectedItemKey() != null) && (!this.treeList.getSelectedItemKey().isEmpty())) {
			PMPGuiCore.drawTreeTexture(this.treeList.getSelectedItemKey(), xPos, yPos, 32);
			drawTreeInfo();
		}
	}
	
	private void drawTreeInfo() {
		int xPos = this.width / 2 + 2 + 40;
		int yPos = 40;
		PMPTree tree = PlantMegaPack.blocks.getSapling(this.treeList.getSelectedItemKey()).getTree();
		drawString(getFontRenderer(), tree.getLocalizedName(), xPos, yPos, 5296160);
		String text = "�o" + tree.getLatinName() + "�r";
		yPos += getFontRenderer().FONT_HEIGHT + 2;
		drawString(getFontRenderer(), text, xPos, yPos, 8437888);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populateTreeList() {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (this.dataTrees.getTreeSpawnData(sapling.name()) == null) {
				char colorCode;
				String treeTypeKey;
				if (sapling.isFruitTree()) {
					treeTypeKey = "gui.fruitTree";
					colorCode = '6';
				} else {
					treeTypeKey = "gui.tree";
					colorCode = '2';
				}
				this.treeList.addListboxEntry(sapling.name(), "�" + colorCode + I18n.translateToLocal(treeTypeKey) + "�7:�r " + sapling.getLocalizedName());
			}
		}
	}
}
