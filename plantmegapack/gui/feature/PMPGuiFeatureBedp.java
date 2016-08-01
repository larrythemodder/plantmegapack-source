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
import plantmegapack.data.feature.PMPDataFeatureBedp;
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
public class PMPGuiFeatureBedp
	extends PMPGuiBase
{
	private PMPDataBiome biomeData;
	private PMPDataFeatureBedp featureData;
	private PMPGuiButton enabled;
	private PMPGuiSlider genChance;
	private PMPGuiSlider genPlants;
	private PMPGuiButton plants;
	
	public PMPGuiFeatureBedp(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.featureData = ((PMPDataFeatureBedp)biomeData.getFeatureData(PMPWorldgenFeature.bedp));
		this.name = String.format("%s %s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("feature.bedp"), I18n.translateToLocal("gui.feature") });
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
		this.genPlants = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "gui.plants", 0, 0, 100, 1);
		this.genPlants.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genPlants);
		
		xPos = this.width / 2 + 4;
		yPos = 108;
		this.plants = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.bedg") + "...");
		this.buttonList.add(this.plants);
		
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
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.bedg, this.featureData.plants, this.featureData));
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
		this.genPlants.setIntValue(this.featureData.genPlants);
		this.genPlants.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		this.featureData.enabled = this.enabled.getButtonState();
		this.featureData.genChance = this.genChance.getIntValue();
		this.featureData.genPlants = this.genPlants.getIntValue();
	}
}
