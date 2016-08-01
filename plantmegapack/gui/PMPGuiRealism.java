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
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiRealism
	extends PMPGuiBase
{
	private ResourceLocation textureWrench;
	private PMPGuiButton realismCropCentered;
	private PMPGuiButton realismGravity;
	private PMPGuiButton realismPlantCentered;
	private PMPGuiButton realismPoison;
	private PMPGuiButton realismThorns;
	
	public PMPGuiRealism(GuiScreen parent) {
		super(parent, "gui.realism", 1);
		
		this.textureWrench = new ResourceLocation("plantmegapack:textures/gui/iconWrench.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 154;
		int yPos = 60;
		this.realismGravity = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.gravity"));
		yPos += 24;
		this.realismPoison = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.poison"));
		yPos += 24;
		this.realismThorns = new PMPGuiButton(5, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.thorns"));
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		this.realismPlantCentered = new PMPGuiButton(10, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.plantRenderCentered"));
		yPos += 24;
		this.realismCropCentered = new PMPGuiButton(11, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.cropRenderCentered"));
		
		this.buttonList.add(this.realismGravity);
		this.buttonList.add(this.realismPoison);
		this.buttonList.add(this.realismThorns);
		this.buttonList.add(this.realismPlantCentered);
		this.buttonList.add(this.realismCropCentered);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetRealismDefaults();
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
		drawTextures();
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureWrench, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.realismCropCentered.setButtonState(PlantMegaPack.settings.realismCropCentered);
		this.realismGravity.setButtonState(PlantMegaPack.settings.realismGravity);
		this.realismPlantCentered.setButtonState(PlantMegaPack.settings.realismPlantCentered);
		this.realismPoison.setButtonState(PlantMegaPack.settings.realismPoison);
		this.realismThorns.setButtonState(PlantMegaPack.settings.realismThorns);
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.realismCropCentered = this.realismCropCentered.getButtonState();
		PlantMegaPack.settings.realismGravity = this.realismGravity.getButtonState();
		PlantMegaPack.settings.realismPlantCentered = this.realismPlantCentered.getButtonState();
		PlantMegaPack.settings.realismPoison = this.realismPoison.getButtonState();
		PlantMegaPack.settings.realismThorns = this.realismThorns.getButtonState();
	}
}
