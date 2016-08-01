package plantmegapack.gui;

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
import plantmegapack.core.PMPSettings;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiWorldgen
	extends PMPGuiBase
{
	private ResourceLocation textureGlobe;
	private ResourceLocation textureFeatures;
	private ResourceLocation textureProfiles;
	private PMPGuiSlider worldgenPlants;
	private PMPGuiSlider worldgenTrees;
	private PMPGuiButton worldgenProfiles;
	private PMPGuiButton worldgenCustomize;
	private PMPGuiButton buttonFeatures;
	
	public PMPGuiWorldgen(GuiScreen parent) {
		super(parent, "gui.worldGeneration", 1);
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureFeatures = new ResourceLocation("plantmegapack:textures/gui/iconFeatures.png");
		this.textureProfiles = new ResourceLocation("plantmegapack:textures/gui/iconProfiles.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 150 + 20;
		int yPos = 60;
		this.worldgenPlants = new PMPGuiSlider(this, 3, xPos, yPos, 126, 20, "gui.plants", 0, 0, 100, 1);
		this.worldgenPlants.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.worldgenPlants);
		
		yPos += 24;
		this.worldgenTrees = new PMPGuiSlider(this, 4, xPos, yPos, 126, 20, "gui.trees", 0, 0, 100, 1);
		this.worldgenTrees.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.worldgenTrees);
		
		xPos = this.width / 2 + 8 + 20;
		yPos = 60;
		this.buttonFeatures = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 126, 20, I18n.translateToLocal("gui.features") + "...");
		this.buttonList.add(this.buttonFeatures);
		
		xPos = this.width / 2 - 150 + 20;
		yPos = 132;
		this.worldgenProfiles = new PMPGuiButton(20, PMPGuiButtonMode.NORMAL, xPos, yPos, 126, 20, I18n.translateToLocal("gui.profiles") + "...");
		this.buttonList.add(this.worldgenProfiles);
		
		yPos += 24;
		this.worldgenCustomize = new PMPGuiButton(21, PMPGuiButtonMode.NORMAL, xPos, yPos, 126, 20, I18n.translateToLocal("gui.customize") + "...");
		this.buttonList.add(this.worldgenCustomize);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetWorldgenDefaults();
				PlantMegaPack.settings.saveOptions();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				PlantMegaPack.settings.saveOptions();
				break;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiFeatures(this));
				return;
			case 20: 
				this.mc.displayGuiScreen(new PMPGuiProfiles(this));
				return;
			case 21: 
				this.mc.displayGuiScreen(new PMPGuiBiomes(this));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawHeadingText();
		drawTextures();
	}
	
	private void drawHeadingText() {
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.masterControl"), this.width / 2 - 75 - 4, 44, 5296160);
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.settings"), this.width / 2 + 75 + 4, 44, 5296160);
		
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.worldgenProfiles"), this.width / 2, 115, 5296160);
		
		int yPos = 138;
		String activeText = I18n.translateToLocal("gui.active") + ": ";
		drawString(this.fontRendererObj, activeText, this.width / 2 + 4, yPos, 8437888);
		int xOffset = this.fontRendererObj.getStringWidth(activeText);
		drawString(this.fontRendererObj, PlantMegaPack.worldgenProfile.getActiveProfile(), this.width / 2 + 4 + xOffset, yPos, 16777088);
		if (PlantMegaPack.worldgenProfile.isActiveProfileDefault()) {
			yPos = 186;
			drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.defaultProfileMsg"), this.width / 2, yPos, 8437888);
		}
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 154;
		int yPos = 60;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTreeTexture("saplingDarkOakEvergreen", xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		PMPGuiCore.drawTexture(this.textureFeatures, xPos, yPos, 20);
		
		xPos = this.width / 2 - 154;
		yPos = 132;
		PMPGuiCore.drawTexture(this.textureProfiles, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.worldgenPlants.setIntValue(PlantMegaPack.settings.worldgenPlants);
		this.worldgenPlants.updateDisplayString();
		this.worldgenTrees.setIntValue(PlantMegaPack.settings.worldgenTrees);
		this.worldgenTrees.updateDisplayString();
		this.worldgenCustomize.enabled = (!PlantMegaPack.worldgenProfile.isActiveProfileDefault());
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.worldgenPlants = this.worldgenPlants.getIntValue();
		PlantMegaPack.settings.worldgenTrees = this.worldgenTrees.getIntValue();
	}
}
