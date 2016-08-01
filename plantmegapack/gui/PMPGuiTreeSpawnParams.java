package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataSetTrees;
import plantmegapack.data.PMPDataSpawnTree;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.object.PMPSapling;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiTreeSpawnParams
	extends PMPGuiBase
{
	private PMPDataSetTrees dataTrees;
	private PMPDataSpawnTree spawnData;
	private PMPGuiSlider spawnWeight;
	private PMPGuiSlider clusterChance;
	private PMPGuiSlider clusterAmount;
	private PMPGuiSlider clusterRadius;
	private PMPGuiButton restrictToBiome;
	private boolean spawnWeightOnly;
	
	public PMPGuiTreeSpawnParams(GuiScreen parent, PMPDataSetTrees dataTrees, String treeName, boolean spawnWeightOnly) {
		super(parent, "", 1);
		this.dataTrees = dataTrees;
		this.spawnWeightOnly = spawnWeightOnly;
		this.spawnData = this.dataTrees.getTreeSpawnData(treeName);
		this.name = String.format("%s %s", new Object[] { PMPSapling.getSaplingFromName(this.spawnData.sapling.name()).getLocalizedName(), I18n.translateToLocal("gui.spawnParameters") });
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 84;
		this.spawnWeight = new PMPGuiSlider(this, 3, xPos, yPos, 150, 20, "gui.spawnWeight", 1, 1, 100, 1);
		this.spawnWeight.setSliderMode(PMPGuiSliderMode.normal);
		this.buttonList.add(this.spawnWeight);
		if (!this.spawnWeightOnly) {
			yPos += 24;
			this.clusterChance = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.clusterChance", 0, 0, 100, 1);
			this.clusterChance.setSliderMode(PMPGuiSliderMode.percentage);
			this.buttonList.add(this.clusterChance);
			
			yPos += 24;
			this.clusterAmount = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "gui.clusterAmount", 1, 1, 16, 1);
			this.clusterAmount.setSliderMode(PMPGuiSliderMode.normal);
			this.buttonList.add(this.clusterAmount);
			
			yPos += 24;
			this.clusterRadius = new PMPGuiSlider(this, 6, xPos, yPos, 150, 20, "gui.clusterRadius", 2, 2, 16, 1);
			this.clusterRadius.setSliderMode(PMPGuiSliderMode.normal);
			this.buttonList.add(this.clusterRadius);
			
			yPos += 24;
			this.restrictToBiome = new PMPGuiButton(7, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.restrictToBiome"));
			this.buttonList.add(this.restrictToBiome);
		}
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PMPDataSpawnTree spawnData = this.dataTrees.getTreeSpawnData(this.spawnData.sapling.name());
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
		PMPGuiCore.drawTreeTexture(this.spawnData.sapling.name(), xPos, yPos, 32);
	}
	
	private void updateButtonStatesFromSettings() {
		this.spawnWeight.setIntValue(this.spawnData.spawnWeight);
		this.spawnWeight.updateDisplayString();
		if (!this.spawnWeightOnly) {
			this.clusterChance.setIntValue(this.spawnData.clusterChance);
			this.clusterChance.updateDisplayString();
			this.clusterAmount.setIntValue(this.spawnData.clusterAmount);
			this.clusterAmount.updateDisplayString();
			this.clusterRadius.setIntValue(this.spawnData.clusterRadius);
			this.clusterRadius.updateDisplayString();
			this.restrictToBiome.setButtonState(this.spawnData.restrictToBiome);
		}
	}
	
	private void updateSettingsFromButtonStates() {
		this.spawnData.spawnWeight = this.spawnWeight.getIntValue();
		if (!this.spawnWeightOnly) {
			this.spawnData.clusterChance = this.clusterChance.getIntValue();
			this.spawnData.clusterAmount = this.clusterAmount.getIntValue();
			this.spawnData.clusterRadius = this.clusterRadius.getIntValue();
			this.spawnData.restrictToBiome = this.restrictToBiome.getButtonState();
		}
	}
}
