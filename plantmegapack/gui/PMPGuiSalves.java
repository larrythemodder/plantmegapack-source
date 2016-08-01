package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiSalves
	extends PMPGuiBase
{
	private ResourceLocation textureSalveFireResist;
	private ResourceLocation textureSalveHealth;
	private ResourceLocation textureSalveNightVision;
	private ResourceLocation textureSalveStrength;
	private ResourceLocation textureSalveSwiftness;
	private ResourceLocation textureSalveWaterBreathing;
	private PMPGuiSlider salveFireResist;
	private PMPGuiSlider salveHealing;
	private PMPGuiSlider salveNightVision;
	private PMPGuiSlider salveStrength;
	private PMPGuiSlider salveSwiftness;
	private PMPGuiSlider salveWaterBreathing;
	
	public PMPGuiSalves(GuiScreen parent) {
		super(parent, "gui.salves", 1);
		this.textureSalveFireResist = new ResourceLocation("plantmegapack:textures/items/salveFireResist.png");
		this.textureSalveHealth = new ResourceLocation("plantmegapack:textures/items/salveHealth.png");
		this.textureSalveNightVision = new ResourceLocation("plantmegapack:textures/items/salveNightVision.png");
		this.textureSalveStrength = new ResourceLocation("plantmegapack:textures/items/salveStrength.png");
		this.textureSalveSwiftness = new ResourceLocation("plantmegapack:textures/items/salveSwiftness.png");
		this.textureSalveWaterBreathing = new ResourceLocation("plantmegapack:textures/items/salveWaterBreathing.png");
	}
	
	public void initGui() {
		this.buttonList.clear();
		super.initGui();
		
		int xPos = this.width / 2 - 104;
		xPos += 24;
		int yPos = 36;
		this.salveFireResist = new PMPGuiSlider(this, 3, xPos, yPos, 180, 20, "gui.fireResist", 10, 30, 120, 1);
		this.salveFireResist.setLabelSuffix(" " + I18n.translateToLocal("gui.seconds"));
		yPos += 24;
		this.salveHealing = new PMPGuiSlider(this, 4, xPos, yPos, 180, 20, "gui.healing", 1, 20, 100, 1);
		this.salveHealing.setSliderMode(PMPGuiSliderMode.percentage);
		yPos += 24;
		this.salveNightVision = new PMPGuiSlider(this, 5, xPos, yPos, 180, 20, "gui.nightVision", 10, 30, 120, 1);
		this.salveNightVision.setLabelSuffix(" " + I18n.translateToLocal("gui.seconds"));
		yPos += 24;
		this.salveStrength = new PMPGuiSlider(this, 6, xPos, yPos, 180, 20, "gui.strength", 10, 30, 120, 1);
		this.salveStrength.setLabelSuffix(" " + I18n.translateToLocal("gui.seconds"));
		yPos += 24;
		this.salveSwiftness = new PMPGuiSlider(this, 7, xPos, yPos, 180, 20, "gui.swiftness", 10, 30, 120, 1);
		this.salveSwiftness.setLabelSuffix(" " + I18n.translateToLocal("gui.seconds"));
		yPos += 24;
		this.salveWaterBreathing = new PMPGuiSlider(this, 8, xPos, yPos, 180, 20, "gui.waterBreathing", 10, 30, 120, 1);
		this.salveWaterBreathing.setLabelSuffix(" " + I18n.translateToLocal("gui.seconds"));
		
		this.buttonList.add(this.salveFireResist);
		this.buttonList.add(this.salveHealing);
		this.buttonList.add(this.salveNightVision);
		this.buttonList.add(this.salveStrength);
		this.buttonList.add(this.salveSwiftness);
		this.buttonList.add(this.salveWaterBreathing);
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetSalveDefaults();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				PlantMegaPack.settings.saveOptions();
				break;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawSalveIcons();
	}
	
	private void drawSalveIcons() {
		int xPos = this.width / 2 - 104;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureSalveFireResist, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalveHealth, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalveNightVision, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalveStrength, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalveSwiftness, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalveWaterBreathing, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.salveFireResist.setIntValue(PlantMegaPack.settings.salveFireResist);
		this.salveHealing.setIntValue(PlantMegaPack.settings.salveHealing);
		this.salveNightVision.setIntValue(PlantMegaPack.settings.salveNightVision);
		this.salveStrength.setIntValue(PlantMegaPack.settings.salveStrength);
		this.salveSwiftness.setIntValue(PlantMegaPack.settings.salveSwiftness);
		this.salveWaterBreathing.setIntValue(PlantMegaPack.settings.salveWaterBreathing);
		
		this.salveFireResist.updateDisplayString();
		this.salveHealing.updateDisplayString();
		this.salveNightVision.updateDisplayString();
		this.salveStrength.updateDisplayString();
		this.salveSwiftness.updateDisplayString();
		this.salveWaterBreathing.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.salveFireResist = this.salveFireResist.getIntValue();
		PlantMegaPack.settings.salveHealing = this.salveHealing.getIntValue();
		PlantMegaPack.settings.salveNightVision = this.salveNightVision.getIntValue();
		PlantMegaPack.settings.salveStrength = this.salveStrength.getIntValue();
		PlantMegaPack.settings.salveSwiftness = this.salveSwiftness.getIntValue();
		PlantMegaPack.settings.salveWaterBreathing = this.salveWaterBreathing.getIntValue();
	}
}
