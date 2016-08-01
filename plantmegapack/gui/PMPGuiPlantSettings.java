package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.plant.PMPPlant;

@SideOnly(Side.CLIENT)
public class PMPGuiPlantSettings
	extends PMPGuiBase
{
	PMPDataPlant plantData;
	
	public PMPGuiPlantSettings(GuiScreen parent, PMPDataPlant plantData) {
		super(parent, "", 1);
		this.plantData = plantData;
		this.name = String.format("%s %s", new Object[] { plantData.attributes.getLocalizedName(), I18n.translateToLocal("gui.settings") });
	}
	
	public void initGui() {
		super.initGui();
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.plantData.resetSettingsDefaults();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				this.plantData.saveSettings();
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
		PMPGuiCore.drawPlantTexture(this.plantData.attributes.name(), xPos, yPos, 32);
	}
	
	private void updateButtonStatesFromSettings() {}
	
	private void updateSettingsFromButtonStates() {}
}
