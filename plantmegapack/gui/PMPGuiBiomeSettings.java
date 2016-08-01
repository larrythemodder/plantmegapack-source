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
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiBiomeSettings
	extends PMPGuiBase
{
	private ResourceLocation textureGlobe;
	private PMPDataBiome biomeData;
	private PMPGuiSlider plantEmptyChunks;
	private PMPGuiButton plantEnabled;
	private PMPGuiSlider plantPassesPerChunk;
	private PMPGuiSlider treeEmptyChunks;
	private PMPGuiButton treeEnabled;
	private PMPGuiSlider treePassesPerChunk;
	private boolean enableTreeControls;
	
	public PMPGuiBiomeSettings(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 1);
		this.biomeData = biomeData;
		this.name = String.format("%s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("gui.settings") });
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.enableTreeControls = this.biomeData.biome.canTreeSpawnInBiome();
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
		this.plantEnabled = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.worldgen"));
		this.buttonList.add(this.plantEnabled);
		
		yPos += 24;
		this.plantPassesPerChunk = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.density", 1, 1, 64, 1);
		this.plantPassesPerChunk.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.plantPassesPerChunk);
		
		yPos += 24;
		this.plantEmptyChunks = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "gui.emptyChunks", 0, 0, 100, 1);
		this.plantEmptyChunks.setSliderMode(PMPGuiSliderMode.percentage);
		this.buttonList.add(this.plantEmptyChunks);
		if (this.enableTreeControls) {
			xPos = this.width / 2 + 4;
			yPos = 84;
			this.treeEnabled = new PMPGuiButton(10, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.worldgen"));
			this.buttonList.add(this.treeEnabled);
			
			yPos += 24;
			this.treePassesPerChunk = new PMPGuiSlider(this, 11, xPos, yPos, 150, 20, "gui.density", 1, 1, 64, 1);
			this.treePassesPerChunk.setSliderMode(PMPGuiSliderMode.normal);
			this.buttonList.add(this.treePassesPerChunk);
			
			yPos += 24;
			this.treeEmptyChunks = new PMPGuiSlider(this, 12, xPos, yPos, 150, 20, "gui.emptyChunks", 0, 0, 100, 1);
			this.treeEmptyChunks.setSliderMode(PMPGuiSliderMode.percentage);
			this.buttonList.add(this.treeEmptyChunks);
		}
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.biomeData.applyDefaultSettings();
				PlantMegaPack.worldgenProfile.saveActiveProfile();
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
		this.plantEnabled.setButtonState(this.biomeData.plantEnabled);
		this.plantPassesPerChunk.setIntValue(this.biomeData.plantPassesPerChunk);
		this.plantPassesPerChunk.updateDisplayString();
		this.plantEmptyChunks.setIntValue(this.biomeData.plantEmptyChunks);
		this.plantEmptyChunks.updateDisplayString();
		if (this.enableTreeControls) {
			this.treeEnabled.setButtonState(this.biomeData.treeEnabled);
			this.treePassesPerChunk.setIntValue(this.biomeData.treePassesPerChunk);
			this.treePassesPerChunk.updateDisplayString();
			this.treeEmptyChunks.setIntValue(this.biomeData.treeEmptyChunks);
			this.treeEmptyChunks.updateDisplayString();
		}
	}
	
	private void updateSettingsFromButtonStates() {
		this.biomeData.plantEnabled = this.plantEnabled.getButtonState();
		this.biomeData.plantPassesPerChunk = this.plantPassesPerChunk.getIntValue();
		this.biomeData.plantEmptyChunks = this.plantEmptyChunks.getIntValue();
		if (this.enableTreeControls) {
			this.biomeData.treeEnabled = this.treeEnabled.getButtonState();
			this.biomeData.treePassesPerChunk = this.treePassesPerChunk.getIntValue();
			this.biomeData.treeEmptyChunks = this.treeEmptyChunks.getIntValue();
		}
	}
}
