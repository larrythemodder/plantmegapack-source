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
public class PMPGuiTooltips
	extends PMPGuiBase
{
	private ResourceLocation textureInfo;
	private PMPGuiButton tooltipLatinName;
	private PMPGuiButton tooltipCategory;
	private PMPGuiButton tooltipConservation;
	private PMPGuiButton tooltipDrops;
	private PMPGuiButton tooltipCrafting;
	
	public PMPGuiTooltips(GuiScreen parent) {
		super(parent, "gui.tooltips", 1);
		
		this.textureInfo = new ResourceLocation("plantmegapack:textures/gui/iconInfo.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		this.tooltipLatinName = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.latinName"));
		yPos += 24;
		this.tooltipCategory = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.category"));
		yPos += 24;
		this.tooltipConservation = new PMPGuiButton(5, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.conservation"));
		yPos += 24;
		this.tooltipDrops = new PMPGuiButton(6, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.drops"));
		yPos += 24;
		this.tooltipCrafting = new PMPGuiButton(7, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.crafting"));
		
		this.buttonList.add(this.tooltipLatinName);
		this.buttonList.add(this.tooltipCategory);
		this.buttonList.add(this.tooltipConservation);
		this.buttonList.add(this.tooltipDrops);
		this.buttonList.add(this.tooltipCrafting);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetTooltipDefaults();
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
		PMPGuiCore.drawTexture(this.textureInfo, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.tooltipLatinName.setButtonState(PlantMegaPack.settings.tooltipLatinName);
		this.tooltipCategory.setButtonState(PlantMegaPack.settings.tooltipCategory);
		this.tooltipConservation.setButtonState(PlantMegaPack.settings.tooltipConservation);
		this.tooltipDrops.setButtonState(PlantMegaPack.settings.tooltipDrops);
		this.tooltipCrafting.setButtonState(PlantMegaPack.settings.tooltipCrafting);
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.tooltipLatinName = this.tooltipLatinName.getButtonState();
		PlantMegaPack.settings.tooltipCategory = this.tooltipCategory.getButtonState();
		PlantMegaPack.settings.tooltipConservation = this.tooltipConservation.getButtonState();
		PlantMegaPack.settings.tooltipDrops = this.tooltipDrops.getButtonState();
		PlantMegaPack.settings.tooltipCrafting = this.tooltipCrafting.getButtonState();
	}
}
