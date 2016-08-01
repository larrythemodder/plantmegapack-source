package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiPlantSpawnParams
	extends PMPGuiBase
{
	private PMPDataSetPlants dataPlants;
	private PMPDataSpawnPlant spawnData;
	public PMPGuiSlider spawnWeight;
	public PMPGuiSlider elevationVariance;
	
	public PMPGuiPlantSpawnParams(GuiScreen parent, PMPDataSetPlants dataPlants, String plantName) {
		super(parent, "", 1);
		this.dataPlants = dataPlants;
		this.spawnData = this.dataPlants.getPlantSpawnData(plantName);
		this.name = String.format("%s %s", new Object[] { PMPPlant.getPlantFromName(this.spawnData.plantAttributes.name()).getLocalizedName(), I18n.translateToLocal("gui.spawnParameters") });
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 84;
		this.spawnWeight = new PMPGuiSlider(this, 3, xPos, yPos, 150, 20, "gui.spawnWeight", 1, 1, 100, 1);
		this.spawnWeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.spawnWeight);
		
		yPos += 24;
		this.elevationVariance = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.elevationVariance", 0, 0, 16, 1);
		this.elevationVariance.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.elevationVariance);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PMPDataSpawnPlant spawnData = this.dataPlants.getPlantSpawnData(this.spawnData.plantAttributes.name());
				if (spawnData != null) {
					spawnData.resetSettings();
				}
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
		int xPos = this.width / 2 - 16;
		int yPos = 36;
		PMPGuiCore.drawPlantTexture(this.spawnData.plantAttributes.name(), xPos, yPos, 32);
	}
	
	private void updateButtonStatesFromSettings() {
		this.spawnWeight.setIntValue(this.spawnData.spawnWeight);
		this.spawnWeight.updateDisplayString();
		if (this.spawnData.plantAttributes.elevationVariance == -1) {
			this.elevationVariance.setIntValue(0);
			this.elevationVariance.updateDisplayString();
			this.elevationVariance.enabled = false;
			this.elevationVariance.visible = false;
		} else {
			this.elevationVariance.setIntValue(this.spawnData.elevationVariance);
			this.elevationVariance.updateDisplayString();
			this.elevationVariance.enabled = true;
			this.elevationVariance.visible = true;
		}
	}
	
	private void updateSettingsFromButtonStates() {
		this.spawnData.spawnWeight = this.spawnWeight.getIntValue();
		this.spawnData.elevationVariance = this.elevationVariance.getIntValue();
	}
}
