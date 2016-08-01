package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiGeneralPlants
	extends PMPGuiBase
{
	PMPGuiSlider plantBambooMaxHeight;
	PMPGuiSlider plantHangingMaxHeight;
	PMPGuiSlider plantClimbingMaxHeight;
	PMPGuiSlider plantSeaweedMaxHeight;
	
	public PMPGuiGeneralPlants(GuiScreen parent) {
		super(parent, "gui.plantSettings", 1);
	}
	
	public void initGui() {
		super.initGui();
		int xPos = this.width / 2 - 90;
		int yPos = 60;
		this.plantBambooMaxHeight = new PMPGuiSlider(this, 3, xPos, yPos, 180, 20, "gui.bambooMaxHeight", 2, 2, 30, 1);
		this.plantBambooMaxHeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.plantBambooMaxHeight);
		
		yPos += 24;
		this.plantClimbingMaxHeight = new PMPGuiSlider(this, 4, xPos, yPos, 180, 20, "gui.climbingMaxHeight", 2, 2, 30, 1);
		this.plantClimbingMaxHeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.plantClimbingMaxHeight);
		
		yPos += 24;
		this.plantHangingMaxHeight = new PMPGuiSlider(this, 5, xPos, yPos, 180, 20, "gui.hangingMaxHeight", 2, 2, 30, 1);
		this.plantHangingMaxHeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.plantHangingMaxHeight);
		
		yPos += 24;
		this.plantSeaweedMaxHeight = new PMPGuiSlider(this, 6, xPos, yPos, 180, 20, "gui.seaweedMaxHeight", 4, 4, 30, 1);
		this.plantSeaweedMaxHeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.plantSeaweedMaxHeight);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetPlantDefaults();
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
		drawTextures();
	}
	
	private void drawHeadingText() {}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.plantBambooMaxHeight.setIntValue(PlantMegaPack.settings.plantBambooMaxHeight);
		this.plantBambooMaxHeight.updateDisplayString();
		this.plantClimbingMaxHeight.setIntValue(PlantMegaPack.settings.plantClimbingMaxHeight);
		this.plantClimbingMaxHeight.updateDisplayString();
		this.plantHangingMaxHeight.setIntValue(PlantMegaPack.settings.plantHangingMaxHeight);
		this.plantHangingMaxHeight.updateDisplayString();
		this.plantSeaweedMaxHeight.setIntValue(PlantMegaPack.settings.plantSeaweedMaxHeight);
		this.plantSeaweedMaxHeight.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.plantBambooMaxHeight = this.plantBambooMaxHeight.getIntValue();
		PlantMegaPack.settings.plantClimbingMaxHeight = this.plantClimbingMaxHeight.getIntValue();
		PlantMegaPack.settings.plantHangingMaxHeight = this.plantHangingMaxHeight.getIntValue();
		PlantMegaPack.settings.plantSeaweedMaxHeight = this.plantSeaweedMaxHeight.getIntValue();
	}
}
