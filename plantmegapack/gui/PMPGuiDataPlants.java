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
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.worldgen.PMPWorldgenFeature;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiDataPlants
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private final int LIST_HEIGHT = 116;
	private final int LIST_BUTTON_WIDTH;
	private ResourceLocation textureAdd;
	private ResourceLocation textureGears;
	private ResourceLocation textureRemove;
	private ResourceLocation textureReset;
	private PMPBiomeType biome;
	private PMPHabitat habitat;
	private PMPDataSetPlants dataPlants;
	private PMPDataFeature dataFeature;
	private PMPGuiListbox plantList;
	private PMPGuiButton buttonSettings;
	private PMPGuiButton buttonAdd;
	private PMPGuiButton buttonRemove;
	private PMPGuiButton buttonResetList;
	
	public PMPGuiDataPlants(GuiScreen parent, PMPBiomeType biome, PMPHabitat habitat, PMPDataSetPlants dataPlants, PMPDataFeature dataFeature) {
		super(parent, "", 0);getClass();this.LIST_BUTTON_WIDTH = ((/*'�'*/'0' - 4) / 2 - 24);
		this.biome = biome;
		this.habitat = habitat;
		this.dataPlants = dataPlants;
		this.dataFeature = dataFeature;
		if (this.dataFeature == null) {
			this.name = String.format("%s %s %s", new Object[] { this.biome.getLocalizedName(), this.habitat.getLocalizedName(), I18n.translateToLocal("gui.plants") });
		} else {
			this.name = String.format("%s %s %s - %s %s", new Object[] { this.biome.getLocalizedName(), dataFeature.getLocalizedName(), I18n.translateToLocal("gui.feature"), this.habitat
				.getLocalizedName(), I18n.translateToLocal("gui.plants") });
		}
		this.textureAdd = new ResourceLocation("plantmegapack:textures/gui/iconAdd.png");
		this.textureGears = new ResourceLocation("plantmegapack:textures/gui/iconGears.png");
		this.textureRemove = new ResourceLocation("plantmegapack:textures/gui/iconDelete.png");
		this.textureReset = new ResourceLocation("plantmegapack:textures/gui/iconReset.png");
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2 - (/*'Â´'*/'0' + 2);
		int yPos = 36;
		getClass();getClass();this.plantList = new PMPGuiListbox(this, xPos, yPos, 180, 116, getFontRenderer().FONT_HEIGHT + 2);
		
		getClass();yPos = 36 + 116 + 4;
		this.buttonAdd = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos + 4 + 20, yPos, this.LIST_BUTTON_WIDTH, 20, I18n.translateToLocal("gui.add") + "...");
		this.buttonList.add(this.buttonAdd);
		
		xPos += this.LIST_BUTTON_WIDTH + 12 + 40;
		this.buttonRemove = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, this.LIST_BUTTON_WIDTH, 20, I18n.translateToLocal("gui.remove"));
		this.buttonList.add(this.buttonRemove);
		
		getClass();xPos = this.width / 2 - (/*'Â´'*/'0' + 2);
		yPos += 24;
		getClass();this.buttonResetList = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos + 4 + 20, yPos, /*'Â´'*/'0' - 24, 20, I18n.translateToLocal("gui.resetDefaults"));
		this.buttonList.add(this.buttonResetList);
		
		xPos = this.width / 2 + 2 + 24;
		yPos = 84;
		this.buttonSettings = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.plantSpawnSettings") + "...");
		this.buttonList.add(this.buttonSettings);
		
		populatePlantList();
		updateControls();
		if (this.plantList.getSize() > 0) {
			this.plantList.selectListItem(0);
		}
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
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				break;
			case 3: 
				if ((this.dataFeature != null) && (this.dataFeature.feature == PMPWorldgenFeature.grou)) {
					this.mc.displayGuiScreen(new PMPGuiAddPlant(this, this.biome, this.habitat, this.dataPlants, PMPPlantCategory.grou));
				} else {
					this.mc.displayGuiScreen(new PMPGuiAddPlant(this, this.biome, this.habitat, this.dataPlants, null));
				}
				return;
			case 4: 
				int selectedIndex = this.plantList.getSelectedItemIndex();
				this.dataPlants.removePlant(this.plantList.getSelectedItemKey());
				populatePlantList();
				updateControls();
				if (this.plantList.getSize() > 0) {
					if (selectedIndex > this.plantList.getSize()) {
						selectedIndex = this.plantList.getSize();
					}
					this.plantList.selectListItem(selectedIndex);
				}
				break;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.resetAllPlants", "iconReset"));
				return;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiPlantSpawnParams(this, this.dataPlants, this.plantList.getSelectedItemKey()));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if (state) {
			switch (buttonID) {
			case 100: 
				if (this.dataFeature == null) {
					PlantMegaPack.worldgenProfile.getBiomeData(this.biome).getHabitatDataFromName(this.habitat.name()).applyDefaultPlants();
				} else {
					this.dataFeature.applyDefaultPlants();
				}
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				populatePlantList();
				updateControls();
				this.plantList.selectListItem(0);
				break;
			}
		}
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {
		actionPerformed(this.buttonSettings);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.plantList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		if (this.plantList.getSize() > 0) {
			int xPos = this.width / 2 + 2;
			int yPos = 40;
			String texture = this.plantList.getSelectedItemKey();
			PMPGuiCore.drawPlantTexture(texture, xPos, yPos, 32);
			drawPlantInfo();
			drawSpawnInfo();
		}
		drawTextures();
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		getClass();int yPos = 36 + 116 + 4;
		PMPGuiCore.drawTexture(this.textureAdd, xPos, yPos, 20);
		
		xPos += this.LIST_BUTTON_WIDTH + 8 + 20;
		PMPGuiCore.drawTexture(this.textureRemove, xPos, yPos, 20);
		
		getClass();xPos = this.width / 2;// - ('�' + 2);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		yPos = 84;
		PMPGuiCore.drawTexture(this.textureGears, xPos, yPos, 20);
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
			commonName = "Â§o" + latinName + "Â§r";
			yPos += getFontRenderer().FONT_HEIGHT + 2;
			drawString(getFontRenderer(), commonName, xPos, yPos, 8437888);
		}
	}
	
	private void drawSpawnInfo() {
		PMPDataSpawnPlant spawnData = this.dataPlants.getPlantSpawnData(this.plantList.getSelectedItemKey());
		if (spawnData != null) {
			int elements = 20;
			int xPos = this.width / 2 + 4 + 90;
			int yPos = 108;
			drawCenteredString(getFontRenderer(), I18n.translateToLocal("gui.spawnWeight"), xPos, yPos + 6, this.plantList.getSize() > 0 ? 5296160 : 6316128);
			
			xPos = this.width / 2 + 4 + (180 - 6 * elements) / 2;
			yPos += 24;
			PMPGuiCore.drawLEDBar(xPos, yPos, 0, 20, spawnData.spawnWeight > 0 ? spawnData.spawnWeight / (100 / elements) : 0);
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populatePlantList() {
		this.plantList.clearAllEntries();
		this.dataPlants.populatePlantGuiList(this.plantList);
	}
	
	private void updateControls() {
		boolean enabled = this.plantList.getSize() > 0;
		this.buttonSettings.enabled = enabled;
		this.buttonRemove.enabled = enabled;
	}
}
