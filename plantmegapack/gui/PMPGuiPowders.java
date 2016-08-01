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
public class PMPGuiPowders
	extends PMPGuiBase
{
	private ResourceLocation texturePowderConditioner;
	private ResourceLocation texturePowderDefoliant;
	private ResourceLocation texturePowderFertilizer;
	private final int BUTTON_WIDTH_POWDER = 120;
	private final int BUTTON_SPACING_POWDER = 8;
	private PMPGuiSlider powderConditionerElevation;
	private PMPGuiSlider powderConditionerRadius;
	private PMPGuiSlider powderConditionerStrength;
	private PMPGuiSlider powderConditionerUses;
	private PMPGuiSlider powderDefoliantElevation;
	private PMPGuiSlider powderDefoliantRadius;
	private PMPGuiSlider powderDefoliantStrength;
	private PMPGuiSlider powderDefoliantUses;
	private PMPGuiSlider powderFertilizerElevation;
	private PMPGuiSlider powderFertilizerRadius;
	private PMPGuiSlider powderFertilizerStrength;
	private PMPGuiSlider powderFertilizerUses;
	
	public PMPGuiPowders(GuiScreen parent) {
		super(parent, "gui.powders", 1);
		this.texturePowderConditioner = new ResourceLocation("plantmegapack:textures/items/powderConditioner.png");
		this.texturePowderDefoliant = new ResourceLocation("plantmegapack:textures/items/powderDefoliant.png");
		this.texturePowderFertilizer = new ResourceLocation("plantmegapack:textures/items/powderFertilizer.png");
	}
	
	public void initGui() {
		this.buttonList.clear();
		super.initGui();
		
		getClass();getClass();getClass();int xPos = this.width / 2 - (120 / 2 + 120 + 8);
		int yPos = 84;
		getClass();this.powderConditionerStrength = new PMPGuiSlider(this, 3, xPos, yPos, 120, 20, "gui.strength", 1, 95, 100, 1);
		this.powderConditionerStrength.setSliderMode(PMPGuiSliderMode.percentage);
		yPos += 24;
		getClass();this.powderConditionerRadius = new PMPGuiSlider(this, 4, xPos, yPos, 120, 20, "gui.radius", 1, 3, 6, 1);
		yPos += 24;
		getClass();this.powderConditionerElevation = new PMPGuiSlider(this, 5, xPos, yPos, 120, 20, "gui.elevation", 1, 4, 6, 1);
		yPos += 24;
		getClass();this.powderConditionerUses = new PMPGuiSlider(this, 5, xPos, yPos, 120, 20, "gui.usesInitial", 5, 10, 50, 1);
		
		getClass();xPos = this.width / 2 - 120 / 2;
		yPos = 84;
		getClass();this.powderDefoliantStrength = new PMPGuiSlider(this, 9, xPos, yPos, 120, 20, "gui.strength", 1, 95, 100, 1);
		this.powderDefoliantStrength.setSliderMode(PMPGuiSliderMode.percentage);
		yPos += 24;
		getClass();this.powderDefoliantRadius = new PMPGuiSlider(this, 10, xPos, yPos, 120, 20, "gui.radius", 1, 3, 6, 1);
		yPos += 24;
		getClass();this.powderDefoliantElevation = new PMPGuiSlider(this, 11, xPos, yPos, 120, 20, "gui.elevation", 1, 4, 6, 1);
		yPos += 24;
		getClass();this.powderDefoliantUses = new PMPGuiSlider(this, 11, xPos, yPos, 120, 20, "gui.usesInitial", 5, 10, 50, 1);
		
		getClass();getClass();xPos = this.width / 2 + (120 / 2 + 8);
		yPos = 84;
		getClass();this.powderFertilizerStrength = new PMPGuiSlider(this, 15, xPos, yPos, 120, 20, "gui.strength", 1, 95, 100, 1);
		this.powderFertilizerStrength.setSliderMode(PMPGuiSliderMode.percentage);
		yPos += 24;
		getClass();this.powderFertilizerRadius = new PMPGuiSlider(this, 16, xPos, yPos, 120, 20, "gui.radius", 1, 3, 6, 1);
		yPos += 24;
		getClass();this.powderFertilizerElevation = new PMPGuiSlider(this, 17, xPos, yPos, 120, 20, "gui.elevation", 1, 4, 6, 1);
		yPos += 24;
		getClass();this.powderFertilizerUses = new PMPGuiSlider(this, 17, xPos, yPos, 120, 20, "gui.usesInitial", 5, 10, 50, 1);
		
		updateButtonStatesFromSettings();
		this.buttonList.add(this.powderConditionerElevation);
		this.buttonList.add(this.powderConditionerRadius);
		this.buttonList.add(this.powderConditionerStrength);
		this.buttonList.add(this.powderConditionerUses);
		this.buttonList.add(this.powderDefoliantElevation);
		this.buttonList.add(this.powderDefoliantRadius);
		this.buttonList.add(this.powderDefoliantStrength);
		this.buttonList.add(this.powderDefoliantUses);
		this.buttonList.add(this.powderFertilizerElevation);
		this.buttonList.add(this.powderFertilizerRadius);
		this.buttonList.add(this.powderFertilizerStrength);
		this.buttonList.add(this.powderFertilizerUses);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetPowderDefaults();
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
		drawHeadingText();
		drawPowderIcons();
	}
	
	private void drawHeadingText() {
		getClass();getClass();int xPos = this.width / 2 - (120 + 8);
		int yPos = 64;
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("item.powderConditioner.name"), xPos, yPos, 5296160);
		getClass();getClass();xPos += 120 + 8;
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("item.powderDefoliant.name"), xPos, yPos, 5296160);
		getClass();getClass();xPos += 120 + 8;
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("item.powderFertilizer.name"), xPos, yPos, 5296160);
	}
	
	private void drawPowderIcons() {
		getClass();getClass();int xPos = this.width / 2 - (120 + 8 + 10);
		int yPos = 36;
		PMPGuiCore.drawTexture(this.texturePowderConditioner, xPos, yPos, 20);
		getClass();getClass();xPos += 120 + 8;
		PMPGuiCore.drawTexture(this.texturePowderDefoliant, xPos, yPos, 20);
		getClass();getClass();xPos += 120 + 8;
		PMPGuiCore.drawTexture(this.texturePowderFertilizer, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.powderConditionerElevation.setIntValue(PlantMegaPack.settings.powderConditionerElevation);
		this.powderConditionerElevation.updateDisplayString();
		this.powderConditionerRadius.setIntValue(PlantMegaPack.settings.powderConditionerRadius);
		this.powderConditionerRadius.updateDisplayString();
		this.powderConditionerStrength.setIntValue(PlantMegaPack.settings.powderConditionerStrength);
		this.powderConditionerStrength.updateDisplayString();
		this.powderConditionerUses.setIntValue(PlantMegaPack.settings.powderConditionerUses);
		this.powderConditionerUses.updateDisplayString();
		
		this.powderDefoliantElevation.setIntValue(PlantMegaPack.settings.powderDefoliantElevation);
		this.powderDefoliantElevation.updateDisplayString();
		this.powderDefoliantRadius.setIntValue(PlantMegaPack.settings.powderDefoliantRadius);
		this.powderDefoliantRadius.updateDisplayString();
		this.powderDefoliantStrength.setIntValue(PlantMegaPack.settings.powderDefoliantStrength);
		this.powderDefoliantStrength.updateDisplayString();
		this.powderDefoliantUses.setIntValue(PlantMegaPack.settings.powderDefoliantUses);
		this.powderDefoliantUses.updateDisplayString();
		
		this.powderFertilizerElevation.setIntValue(PlantMegaPack.settings.powderFertilizerElevation);
		this.powderFertilizerElevation.updateDisplayString();
		this.powderFertilizerRadius.setIntValue(PlantMegaPack.settings.powderFertilizerRadius);
		this.powderFertilizerRadius.updateDisplayString();
		this.powderFertilizerStrength.setIntValue(PlantMegaPack.settings.powderFertilizerStrength);
		this.powderFertilizerStrength.updateDisplayString();
		this.powderFertilizerUses.setIntValue(PlantMegaPack.settings.powderFertilizerUses);
		this.powderFertilizerUses.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.powderConditionerElevation = this.powderConditionerElevation.getIntValue();
		PlantMegaPack.settings.powderConditionerRadius = this.powderConditionerRadius.getIntValue();
		PlantMegaPack.settings.powderConditionerStrength = this.powderConditionerStrength.getIntValue();
		PlantMegaPack.settings.powderConditionerUses = this.powderConditionerUses.getIntValue();
		
		PlantMegaPack.settings.powderDefoliantElevation = this.powderDefoliantElevation.getIntValue();
		PlantMegaPack.settings.powderDefoliantRadius = this.powderDefoliantRadius.getIntValue();
		PlantMegaPack.settings.powderDefoliantStrength = this.powderDefoliantStrength.getIntValue();
		PlantMegaPack.settings.powderDefoliantUses = this.powderDefoliantUses.getIntValue();
		
		PlantMegaPack.settings.powderFertilizerElevation = this.powderFertilizerElevation.getIntValue();
		PlantMegaPack.settings.powderFertilizerRadius = this.powderFertilizerRadius.getIntValue();
		PlantMegaPack.settings.powderFertilizerStrength = this.powderFertilizerStrength.getIntValue();
		PlantMegaPack.settings.powderFertilizerUses = this.powderFertilizerUses.getIntValue();
	}
}
