package plantmegapack.gui.feature;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.data.feature.PMPDataFeatureKelp;
import plantmegapack.gui.PMPGuiBase;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiFeatureKelp
	extends PMPGuiBase
{
	private PMPDataBiome biomeData;
	private PMPDataFeatureKelp featureData;
	private PMPGuiButton enabled;
	private PMPGuiSlider genChance;
	private PMPGuiSlider limitHigh;
	private PMPGuiSlider limitLow;
	
	public PMPGuiFeatureKelp(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.featureData = ((PMPDataFeatureKelp)biomeData.getFeatureData(PMPWorldgenFeature.kelp));
		this.name = String.format("%s %s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("feature.kelp"), I18n.translateToLocal("gui.feature") });
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		this.enabled = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.enabled"));
		this.buttonList.add(this.enabled);
		
		yPos += 24;
		this.genChance = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.genRate", 0, 0, 100, 1);
		this.genChance.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.genChance);
		
		yPos += 24;
		this.limitHigh = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "gui.limitHigh", 58, 58, 61, 1);
		this.limitHigh.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.limitHigh);
		
		yPos += 24;
		this.limitLow = new PMPGuiSlider(this, 6, xPos, yPos, 150, 20, "gui.limitLow", 52, 52, 58, 1);
		this.limitLow.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.limitLow);
		
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
	
	private void drawHeadingText() {}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawPlantTexture(PMPPlant.saltwaterKelpGiantGRN.name(), xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.enabled.setButtonState(this.featureData.enabled);
		this.genChance.setIntValue(this.featureData.genChance);
		this.genChance.updateDisplayString();
		this.limitHigh.setIntValue(this.featureData.limitHigh);
		this.limitHigh.updateDisplayString();
		this.limitLow.setIntValue(this.featureData.limitLow);
		this.limitLow.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		this.featureData.enabled = this.enabled.getButtonState();
		this.featureData.genChance = this.genChance.getIntValue();
		this.featureData.limitHigh = this.limitHigh.getIntValue();
		this.featureData.limitLow = this.limitLow.getIntValue();
	}
}
