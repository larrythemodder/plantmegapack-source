package plantmegapack.gui.feature;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.data.feature.PMPDataFeatureGrou;
import plantmegapack.gui.PMPGuiBase;
import plantmegapack.gui.PMPGuiDataPlants;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenFeature;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiFeatureGrou
	extends PMPGuiBase
{
	private PMPDataBiome biomeData;
	private PMPDataFeatureGrou featureData;
	private PMPGuiButton enabled;
	private PMPGuiSlider genChance;
	private PMPGuiSlider genOpen;
	private PMPGuiSlider genShade;
	private PMPGuiButton plantsOpen;
	private PMPGuiButton plantsShade;
	
	public PMPGuiFeatureGrou(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.featureData = ((PMPDataFeatureGrou)biomeData.getFeatureData(PMPWorldgenFeature.grou));
		this.name = String.format("%s %s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("feature.grou"), I18n.translateToLocal("gui.feature") });
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 154;
		int yPos = 60;
		this.enabled = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.enabled"));
		this.buttonList.add(this.enabled);
		
		yPos += 24;
		this.genChance = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.genRate", 1, 1, 100, 1);
		this.genChance.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genChance);
		
		yPos += 24;
		this.genOpen = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "habitat.open", 0, 0, 100, 1);
		this.genOpen.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genOpen);
		
		yPos += 24;
		this.genShade = new PMPGuiSlider(this, 6, xPos, yPos, 150, 20, "habitat.shad", 0, 0, 100, 1);
		this.genShade.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genShade);
		
		xPos = this.width / 2 + 4;
		yPos = 108;
		this.plantsOpen = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.open") + "...");
		this.buttonList.add(this.plantsOpen);
		
		yPos += 24;
		this.plantsShade = new PMPGuiButton(11, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.shad") + "...");
		this.buttonList.add(this.plantsShade);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.featureData.resetDefaults();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				break;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.open, this.featureData.open, this.featureData));
				break;
			case 11: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.shad, this.featureData.shade, this.featureData));
				break;
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
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.settings"), this.width / 2 - 75 - 4, 44, 5296160);
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.plants"), this.width / 2 + 75 + 4, 44, 5296160);
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.enabled.setButtonState(this.featureData.enabled);
		this.genChance.setIntValue(this.featureData.genChance);
		this.genChance.updateDisplayString();
		this.genOpen.setIntValue(this.featureData.genOpen);
		this.genOpen.updateDisplayString();
		this.genShade.setIntValue(this.featureData.genShade);
		this.genShade.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		this.featureData.enabled = this.enabled.getButtonState();
		this.featureData.genChance = this.genChance.getIntValue();
		this.featureData.genOpen = this.genOpen.getIntValue();
		this.featureData.genShade = this.genShade.getIntValue();
	}
}
