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
import plantmegapack.data.PMPDataPlant;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiListboxState;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;

@SideOnly(Side.CLIENT)
public class PMPGuiPlants
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private ResourceLocation textureGlobe;
	private ResourceLocation textureLevels;
	private ResourceLocation textureReset;
	private PMPGuiListbox plantList;
	private static PMPGuiListboxState plantListState = new PMPGuiListboxState();
	private PMPGuiButton buttonWorldgen;
	private PMPGuiButton buttonSpawnTypes;
	private PMPGuiButton buttonResetSelectedPlant;
	private PMPGuiButton buttonResetAllPlants;
	
	public PMPGuiPlants(GuiScreen parent) {
		super(parent, "gui.plants", 0);
		
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureLevels = new ResourceLocation("plantmegapack:textures/gui/iconLevels.png");
		this.textureReset = new ResourceLocation("plantmegapack:textures/gui/iconReset.png");
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		int yPos = 36;
		getClass();getClass();this.plantList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		if (this.plantList != null) {
			loadPlantNames();
		}
		xPos += 24;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetAllPlants = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetAllPlants") + "...");
		
		xPos = this.width / 2 + 12 + 20;
		yPos = 84;
		this.buttonWorldgen = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.worldgen"));
		
		yPos += 24;
		this.buttonSpawnTypes = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.spawnTypes") + "...");
		
		yPos = 180;
		this.buttonResetSelectedPlant = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.resetSelectedPlant") + "...");
		
		this.buttonList.add(this.buttonResetAllPlants);
		this.buttonList.add(this.buttonWorldgen);
		this.buttonList.add(this.buttonSpawnTypes);
		
		this.buttonList.add(this.buttonResetSelectedPlant);
		
		this.plantList.setListboxState(plantListState);
		listItemSelected(null, plantListState.selectedElement);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(this.plantList.getSelectedItemKey())).getPlantData();
			if (plantData == null) {
				return;
			}
			switch (button.id) {
			case 2: 
				plantListState.resetState();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.resetAllPlants", "iconReset"));
				return;
			case 4: 
				updateSettingsFromButtonStates();
				plantData.saveSettings();
				break;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiPlantSpawnTypes(this, plantData)); return;
			case 6: 
				break;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 101, "gui.resetSelectedPlant", "iconReset"));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if ((buttonID == 100) && (state)) {
			PlantMegaPack.blocks.resetAllPlants();
		}
		if ((buttonID == 101) && (state)) {
			PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(this.plantList.getSelectedItemKey())).getPlantData();
			plantData.resetAllDefaults();
			plantData.saveSettings();
			updateButtonStatesFromSettings();
		}
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.plantList.handleMouseInput();
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.plantList.drawScreen(par1, par2, par3);
		drawTextures();
		super.drawScreen(par1, par2, par3);
		int xPos = this.width / 2 + 2;
		int yPos = 40;
		String texture = this.plantList.getSelectedItemKey();
		PMPGuiCore.drawPlantTexture(texture, xPos, yPos, 32);
		drawPlantInfo();
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		getClass();int yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
		
		xPos = this.width / 2 + 8;
		yPos = 84;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureLevels, xPos, yPos, 20);
		
		yPos = 180;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {
		actionPerformed(this.buttonSpawnTypes);
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		this.plantList.saveListboxState(plantListState);
		updateButtonStatesFromSettings();
	}
	
	private void loadPlantNames() {
		for (PMPPlant plants : PMPPlant.values()) {
			if (plants.category != PMPPlantCategory.crop) {
				this.plantList.addListboxEntry(plants.name(), "Â§" + plants.category.colorCode + plants.category.getLocalizedName() + "Â§7:Â§r " + plants.getLocalizedName());
			}
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
			commonName = /*"Â§o" +*/ latinName /*+ "Â§r"*/;
			yPos += getFontRenderer().FONT_HEIGHT + 2;
			drawString(getFontRenderer(), commonName, xPos, yPos, 8437888);
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void updateButtonStatesFromSettings() {
		PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(this.plantList.getSelectedItemKey())).getPlantData();
		this.buttonWorldgen.setButtonState(plantData.worldgenEnabled);
	}
	
	private void updateSettingsFromButtonStates() {
		PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(this.plantList.getSelectedItemKey())).getPlantData();
		plantData.worldgenEnabled = this.buttonWorldgen.getButtonState();
	}
}
