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
public class PMPGuiGeneratedChestItems
	extends PMPGuiBase
{
	private ResourceLocation textureChest;
	private PMPGuiButton buttonBook;
	private PMPGuiButton buttonCropSeed;
	private PMPGuiButton buttonFlower;
	private PMPGuiButton buttonPowder;
	private PMPGuiButton buttonRoot;
	private PMPGuiButton buttonSalve;
	private PMPGuiButton buttonStem;
	
	public PMPGuiGeneratedChestItems(GuiScreen parent) {
		super(parent, "gui.chestItems", 1);
		
		this.textureChest = new ResourceLocation("plantmegapack:textures/gui/iconChest.png");
	}
	
	public void initGui() {
		super.initGui();
		int xPos = this.width / 2 - 154;
		int yPos = 84;
		this.buttonBook = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("item.bookPlantGuide.name"));
		yPos += 24;
		this.buttonCropSeed = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.cropSeeds"));
		yPos += 24;
		this.buttonFlower = new PMPGuiButton(5, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.flowers"));
		yPos += 24;
		this.buttonPowder = new PMPGuiButton(6, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.powders"));
		
		xPos = this.width / 2 + 4;
		yPos = 84;
		this.buttonRoot = new PMPGuiButton(10, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.roots"));
		yPos += 24;
		this.buttonSalve = new PMPGuiButton(11, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.salves"));
		yPos += 24;
		this.buttonStem = new PMPGuiButton(12, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.stems"));
		
		this.buttonList.add(this.buttonBook);
		this.buttonList.add(this.buttonCropSeed);
		this.buttonList.add(this.buttonFlower);
		this.buttonList.add(this.buttonPowder);
		this.buttonList.add(this.buttonRoot);
		this.buttonList.add(this.buttonSalve);
		this.buttonList.add(this.buttonStem);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetChestItemDefaults();
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
	
	private void drawHeadingText() {
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.restartRequired"), this.width / 2, 64, 16777088);
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureChest, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.buttonBook.setButtonState(PlantMegaPack.settings.chestItemBook);
		this.buttonCropSeed.setButtonState(PlantMegaPack.settings.chestItemCropSeed);
		this.buttonFlower.setButtonState(PlantMegaPack.settings.chestItemFlower);
		this.buttonPowder.setButtonState(PlantMegaPack.settings.chestItemPowder);
		this.buttonRoot.setButtonState(PlantMegaPack.settings.chestItemRoot);
		this.buttonSalve.setButtonState(PlantMegaPack.settings.chestItemSalve);
		this.buttonStem.setButtonState(PlantMegaPack.settings.chestItemStem);
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.chestItemBook = this.buttonBook.getButtonState();
		PlantMegaPack.settings.chestItemCropSeed = this.buttonCropSeed.getButtonState();
		PlantMegaPack.settings.chestItemFlower = this.buttonFlower.getButtonState();
		PlantMegaPack.settings.chestItemPowder = this.buttonPowder.getButtonState();
		PlantMegaPack.settings.chestItemRoot = this.buttonRoot.getButtonState();
		PlantMegaPack.settings.chestItemSalve = this.buttonSalve.getButtonState();
		PlantMegaPack.settings.chestItemStem = this.buttonStem.getButtonState();
	}
}
