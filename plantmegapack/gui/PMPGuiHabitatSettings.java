package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiHabitatSettings
	extends PMPGuiBase
{
	private ResourceLocation textureGlobe;
	private PMPDataBiome biomeData;
	private PMPDataHabitat habitatData;
	private PMPGuiSlider genRatePlant;
	private PMPGuiSlider genRateTree;
	private boolean enableTreeControls;
	
	public PMPGuiHabitatSettings(GuiScreen parent, PMPDataBiome biomeData, PMPDataHabitat habitatData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.habitatData = habitatData;
		this.name = String.format("%s %s %s", new Object[] { this.biomeData.biome.getLocalizedName(), habitatData.habitat.getLocalizedName(), I18n.translateToLocal("gui.habitatSettings") });
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.enableTreeControls = ((this.biomeData.biome.canTreeSpawnInBiome()) && (this.habitatData.habitat.canTreeSpawnInHabitat()));
	}
	
	public void initGui() {
		super.initGui();
		
		int yPos = 84;
		int xPos;
		if (this.enableTreeControls) {
			xPos = this.width / 2 - 154;
		} else {
			xPos = this.width / 2 - 75;
		}
		this.genRatePlant = new PMPGuiSlider(this, 3, xPos, yPos, 150, 20, "gui.worldgen", 0, 0, 100, 1);
		this.genRatePlant.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.genRatePlant);
		if (this.enableTreeControls) {
			xPos = this.width / 2 + 4;
			yPos = 84;
			this.genRateTree = new PMPGuiSlider(this, 10, xPos, yPos, 150, 20, "gui.worldgen", 0, 0, 100, 1);
			this.genRateTree.setSliderMode(PMPGuiSliderMode.percentage);
			this.buttonList.add(this.genRateTree);
		}
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.habitatData.applyDefaultSettings();
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
	
	private void drawHeadingText() {
		if (this.enableTreeControls) {
			drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.plants"), this.width / 2 - 75 - 4, 68, 5296160);
			drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.trees"), this.width / 2 + 75 + 4, 68, 5296160);
		} else {
			drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.plants"), this.width / 2, 68, 5296160);
		}
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.genRatePlant.setIntValue(this.habitatData.genRatePlant);
		this.genRatePlant.updateDisplayString();
		if (this.enableTreeControls) {
			this.genRateTree.setIntValue(this.habitatData.genRateTree);
			this.genRateTree.updateDisplayString();
		}
	}
	
	private void updateSettingsFromButtonStates() {
		this.habitatData.genRatePlant = this.genRatePlant.getIntValue();
		if (this.enableTreeControls) {
			this.habitatData.genRateTree = this.genRateTree.getIntValue();
		}
	}
}
