package plantmegapack.gui;

import java.io.IOException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiAddPlant
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private PMPBiomeType biome;
	private PMPHabitat habitat;
	private PMPDataSetPlants dataPlants;
	private PMPGuiListbox plantList;
	private PMPPlantCategory categoryFilter;
	
	public PMPGuiAddPlant(GuiScreen parent, PMPBiomeType biome, PMPHabitat habitat, PMPDataSetPlants dataPlants, PMPPlantCategory categoryFilter) {
		super(parent, "", 2);
		this.biome = biome;
		this.habitat = habitat;
		this.dataPlants = dataPlants;
		this.categoryFilter = categoryFilter;
		this.name = String.format("%s - %s %s", new Object[] { I18n.translateToLocal("gui.addPlant"), this.biome.getLocalizedName(), this.habitat.getLocalizedName() });
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();
		int xPos = this.width / 2;// - ('`' + 2);
		int yPos = 36;
		getClass();
		getClass();
		this.plantList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		populatePlantList();
		this.plantList.selectListItem(0);
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.plantList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				this.dataPlants.addPlant(this.plantList.getSelectedItemKey());
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
		this.plantList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		int xPos = this.width / 2 + 2;
		int yPos = 40;
		if ((this.plantList.getSelectedItemKey() != null) && (!this.plantList.getSelectedItemKey().isEmpty())) {
			PMPGuiCore.drawPlantTexture(this.plantList.getSelectedItemKey(), xPos, yPos, 32);
			drawPlantInfo();
		}
	}
	
	private void drawPlantInfo() {
		int xPos = this.width / 2 + 2 + 40;
		int yPos = 40;
		PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(this.plantList.getSelectedItemKey())).getPlantData();
		if (plantData == null) {
			return;
		}
		String commonName = plantData.attributes.getLocalizedName();
		String latinName = plantData.attributes.getLatinName();
		drawString(getFontRenderer(), commonName, xPos, yPos, 5296160);
		if (plantData.attributes.category != PMPPlantCategory.grou) {
			commonName = "�o" + latinName + "�r";
			yPos += getFontRenderer().FONT_HEIGHT + 2;
			drawString(getFontRenderer(), commonName, xPos, yPos, 8437888);
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populatePlantList() {
		for (PMPPlant plant : PMPPlant.values()) {
			boolean addPlant = false;
			if ((this.dataPlants.getPlantSpawnData(plant.name()) == null) && (plant.category != PMPPlantCategory.crop)) {
				switch (this.habitat) {
				case frdp: 
					addPlant = plant.category == PMPPlantCategory.fres;
					break;
				case frfl: 
					addPlant = plant.category == PMPPlantCategory.floa;
					break;
				case frmr: 
					addPlant = (plant.category == PMPPlantCategory.crpa) || (plant.category == PMPPlantCategory.imme);
					break;
				case bedg: 
				case open: 
				case shad: 
				case wedg: 
					addPlant = (!plant.isAquaticPlant()) && (plant.category != PMPPlantCategory.clim) && (plant.category != PMPPlantCategory.epip) && (plant.category != PMPPlantCategory.vine);
					break;
				case slop: 
					addPlant = (!plant.isAquaticPlant()) && (plant.category != PMPPlantCategory.epip) && (plant.category != PMPPlantCategory.vine);
					break;
				case madp: 
					addPlant = (plant.category == PMPPlantCategory.cora) || (plant.category == PMPPlantCategory.salt);
					break;
				case mafl: 
					break;
				case mamr: 
					addPlant = (plant.category == PMPPlantCategory.cora) || (plant.category == PMPPlantCategory.salt);
					break;
				case epip: 
					addPlant = plant.category == PMPPlantCategory.epip;
					break;
				case vine: 
					addPlant = plant.category == PMPPlantCategory.vine;
					break;
				}
				if (this.categoryFilter != null) {
					addPlant = this.categoryFilter == plant.category;
				}
				if (addPlant) {
					String label = String.format("%s: %s", new Object[] { plant.category.getLocalizedNameFormatted(), plant.getLocalizedName() });
					this.plantList.addListboxEntry(plant.name(), label);
				}
			}
		}
	}
}
