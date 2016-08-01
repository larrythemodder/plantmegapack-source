package plantmegapack.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSapling;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataTree;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiListboxState;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.object.PMPSapling;
import plantmegapack.tree.PMPTree;

@SideOnly(Side.CLIENT)
public class PMPGuiTrees
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private ResourceLocation textureGears;
	private ResourceLocation textureReset;
	private PMPGuiListbox treeList;
	private static PMPGuiListboxState treeListState = new PMPGuiListboxState();
	private PMPGuiButton treeSettings;
	private PMPGuiButton buttonResetSelectedTree;
	private PMPGuiButton buttonResetAllTrees;
	
	public PMPGuiTrees(GuiScreen parent) {
		super(parent, "gui.trees", 0);
		this.textureGears = new ResourceLocation("plantmegapack:textures/gui/iconGears.png");
		this.textureReset = new ResourceLocation("plantmegapack:textures/gui/iconReset.png");
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		int yPos = 36;
		getClass();getClass();this.treeList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		if (this.treeList != null) {
			loadTreeNames();
		}
		xPos += 24;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetAllTrees = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetAllTrees") + "...");
		this.buttonList.add(this.buttonResetAllTrees);
		
		xPos = this.width / 2 + 12 + 20;
		yPos = 84;
		this.treeSettings = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.settings"));
		this.buttonList.add(this.treeSettings);
		
		yPos = 180;
		this.buttonResetSelectedTree = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.resetSelectedTree") + "...");
		this.buttonList.add(this.buttonResetSelectedTree);
		
		this.treeList.setListboxState(treeListState);
		listItemSelected(null, treeListState.selectedElement);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			PMPDataTree treeData = PlantMegaPack.blocks.getSapling(this.treeList.getSelectedItemKey()).getTreeData();
			if (treeData == null) {
				return;
			}
			switch (button.id) {
			case 2: 
				treeListState.resetState();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.resetAllTrees", "iconReset"));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiTreeSettings(this, treeData));
				return;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 101, "gui.resetSelectedTree", "iconReset"));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if ((buttonID == 100) && (state)) {
			PlantMegaPack.blocks.resetAllTrees();
		}
		if ((buttonID == 101) && (state)) {
			PMPDataTree treeData = PlantMegaPack.blocks.getSapling(this.treeList.getSelectedItemKey()).getTreeData();
			treeData.resetAllDefaults();
			treeData.saveSettings();
		}
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.treeList.handleMouseInput();
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.treeList.drawScreen(par1, par2, par3);
		drawTextures();
		super.drawScreen(par1, par2, par3);
		int xPos = this.width / 2 + 2;
		int yPos = 40;
		PMPGuiCore.drawTreeTexture(this.treeList.getSelectedItemKey(), xPos, yPos, 32);
		drawTreeInfo();
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		getClass();int yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
		
		xPos = this.width / 2 + 8;
		yPos = 84;
		PMPGuiCore.drawTexture(this.textureGears, xPos, yPos, 20);
		
		yPos = 180;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		this.treeList.saveListboxState(treeListState);
	}
	
	private void loadTreeNames() {
		for (PMPSapling sapling : PMPSapling.values()) {
			String treeTypeKey = "gui.tree";
			char colorCode = '2';
			if (sapling.isFruitTree()) {
				treeTypeKey = "gui.fruitTree";
				colorCode = '6';
			}
			this.treeList.addListboxEntry(sapling.name(), "�" + colorCode + I18n.translateToLocal(treeTypeKey) + "�7:�r " + sapling.getLocalizedName());
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
}
