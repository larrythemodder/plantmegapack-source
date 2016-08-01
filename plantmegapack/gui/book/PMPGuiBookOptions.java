package plantmegapack.gui.book;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.gui.PMPGuiBase;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiBookOptions
	extends PMPGuiBase
{
	private ResourceLocation textureBook;
	private PMPGuiButton buttonChatMessage;
	private PMPGuiButton buttonDropExperience;
	private PMPGuiButton buttonSaveProgress;
	private PMPGuiButton buttonShowAllPlants;
	
	public PMPGuiBookOptions(GuiScreen parent) {
		super(parent, "gui.plantGuide0", 1);
		this.textureBook = new ResourceLocation("plantmegapack:textures/items/bookPlantGuide.png");
	}
	
	public void initGui() {
		super.initGui();
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		this.buttonChatMessage = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.chatMessage"));
		
		yPos += 24;
		this.buttonDropExperience = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.dropExperience"));
		
		yPos += 24;
		this.buttonSaveProgress = new PMPGuiButton(5, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.saveProgress"));
		
		yPos += 24;
		this.buttonShowAllPlants = new PMPGuiButton(6, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.showAllPlants"));
		
		this.buttonList.add(this.buttonChatMessage);
		this.buttonList.add(this.buttonDropExperience);
		this.buttonList.add(this.buttonSaveProgress);
		this.buttonList.add(this.buttonShowAllPlants);
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetBookDefaults();
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
		drawBookIcon();
	}
	
	private void drawBookIcon() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureBook, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.buttonChatMessage.setButtonState(PlantMegaPack.settings.bookChatMessage);
		this.buttonDropExperience.setButtonState(PlantMegaPack.settings.bookDropXPOnDiscovery);
		this.buttonSaveProgress.setButtonState(PlantMegaPack.settings.bookSaveProgress);
		this.buttonShowAllPlants.setButtonState(PlantMegaPack.settings.bookShowAllPlants);
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.bookChatMessage = this.buttonChatMessage.getButtonState();
		PlantMegaPack.settings.bookDropXPOnDiscovery = this.buttonDropExperience.getButtonState();
		PlantMegaPack.settings.bookSaveProgress = this.buttonSaveProgress.getButtonState();
		PlantMegaPack.settings.bookShowAllPlants = this.buttonShowAllPlants.getButtonState();
	}
}
