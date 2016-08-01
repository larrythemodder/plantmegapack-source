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
import plantmegapack.data.feature.PMPDataFeatureWedg;
import plantmegapack.gui.PMPGuiBase;
import plantmegapack.gui.PMPGuiDataPlants;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiFeatureWedg
	extends PMPGuiBase
{
	private PMPDataBiome biomeData;
	private PMPDataFeatureWedg featureData;
	private PMPGuiButton enabled;
	private PMPGuiSlider genChance;
	private PMPGuiSlider genFloating;
	private PMPGuiSlider genImmersed;
	private PMPGuiSlider genLand;
	private PMPGuiSlider genSlope;
	private PMPGuiButton plantsFloating;
	private PMPGuiButton plantsImmersed;
	private PMPGuiButton plantsSlope;
	private PMPGuiButton plantsWaterEdge;
	
	public PMPGuiFeatureWedg(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.featureData = ((PMPDataFeatureWedg)biomeData.getFeatureData(PMPWorldgenFeature.wedg));
		this.name = String.format("%s %s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("feature.wedg"), I18n.translateToLocal("gui.feature") });
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
		this.genFloating = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "habitat.frfl", 0, 0, 100, 1);
		this.genFloating.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genFloating);
		
		yPos += 24;
		this.genImmersed = new PMPGuiSlider(this, 6, xPos, yPos, 150, 20, "habitat.frmr", 0, 0, 100, 1);
		this.genImmersed.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genImmersed);
		
		yPos += 24;
		this.genSlope = new PMPGuiSlider(this, 7, xPos, yPos, 150, 20, "habitat.slop", 0, 0, 100, 1);
		this.genSlope.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genSlope);
		
		yPos += 24;
		this.genLand = new PMPGuiSlider(this, 8, xPos, yPos, 150, 20, "habitat.wedg", 0, 0, 100, 1);
		this.genLand.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genLand);
		
		xPos = this.width / 2 + 4;
		yPos = 108;
		this.plantsFloating = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.frfl") + "...");
		this.buttonList.add(this.plantsFloating);
		
		yPos += 24;
		this.plantsImmersed = new PMPGuiButton(11, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.frmr") + "...");
		this.buttonList.add(this.plantsImmersed);
		
		yPos += 24;
		this.plantsSlope = new PMPGuiButton(12, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.slop") + "...");
		this.buttonList.add(this.plantsSlope);
		
		yPos += 24;
		this.plantsWaterEdge = new PMPGuiButton(13, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("habitat.wedg") + "...");
		this.buttonList.add(this.plantsWaterEdge);
		
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
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.frfl, this.featureData.plantsFloa, this.featureData));
				break;
			case 11: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.frmr, this.featureData.plantsImme, this.featureData));
				break;
			case 12: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.slop, this.featureData.plantsSlop, this.featureData));
				break;
			case 13: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.wedg, this.featureData.plantsLand, this.featureData));
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
		PMPGuiCore.drawPlantTexture(PMPPlant.wetlandsCattails.name(), xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.enabled.setButtonState(this.featureData.enabled);
		this.genChance.setIntValue(this.featureData.genChance);
		this.genChance.updateDisplayString();
		this.genLand.setIntValue(this.featureData.genLand);
		this.genLand.updateDisplayString();
		this.genFloating.setIntValue(this.featureData.genFloating);
		this.genFloating.updateDisplayString();
		this.genImmersed.setIntValue(this.featureData.genImmersed);
		this.genImmersed.updateDisplayString();
		this.genSlope.setIntValue(this.featureData.genSlope);
		this.genSlope.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		this.featureData.enabled = this.enabled.getButtonState();
		this.featureData.genChance = this.genChance.getIntValue();
		this.featureData.genLand = this.genLand.getIntValue();
		this.featureData.genFloating = this.genFloating.getIntValue();
		this.featureData.genImmersed = this.genImmersed.getIntValue();
		this.featureData.genSlope = this.genSlope.getIntValue();
	}
}
