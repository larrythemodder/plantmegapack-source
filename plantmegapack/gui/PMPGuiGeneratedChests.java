package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
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
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.control.PMPGuiSliderMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiGeneratedChests
	extends PMPGuiBase
{
	private ResourceLocation textureChest;
	private PMPGuiSlider chestGenBonus;
	private PMPGuiSlider chestGenDesertTemple;
	private PMPGuiSlider chestGenDungeonChest;
	private PMPGuiSlider chestGenJungleTemple;
	private PMPGuiSlider chestGenMineshaftCorridor;
	private PMPGuiSlider chestGenStronghold;
	private PMPGuiSlider chestGenVillageBlacksmith;
	private PMPGuiButton buttonChestItems;
	
	public PMPGuiGeneratedChests(GuiScreen parent) {
		super(parent, "gui.generatedChests", 1);
		
		this.textureChest = new ResourceLocation("plantmegapack:textures/gui/iconChest.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 152;
		int yPos = 84;
		this.chestGenBonus = new PMPGuiSlider(this, 3, xPos, yPos, 150, 20, "gui.bonusChest", 0, 0, 5, 1);
		this.chestGenBonus.setSliderMode(PMPGuiSliderMode.normal);
		
		yPos += 24;
		this.chestGenDesertTemple = new PMPGuiSlider(this, 4, xPos, yPos, 150, 20, "gui.desertTemple", 0, 0, 5, 1);
		this.chestGenDesertTemple.setSliderMode(PMPGuiSliderMode.normal);
		
		yPos += 24;
		this.chestGenDungeonChest = new PMPGuiSlider(this, 5, xPos, yPos, 150, 20, "gui.dungeonChest", 0, 0, 5, 1);
		this.chestGenDungeonChest.setSliderMode(PMPGuiSliderMode.normal);
		
		yPos += 24;
		this.chestGenJungleTemple = new PMPGuiSlider(this, 6, xPos, yPos, 150, 20, "gui.jungleTemple", 0, 0, 5, 1);
		this.chestGenJungleTemple.setSliderMode(PMPGuiSliderMode.normal);
		
		xPos = this.width / 2 + 2;
		yPos = 84;
		this.chestGenMineshaftCorridor = new PMPGuiSlider(this, 10, xPos, yPos, 150, 20, "gui.mineshaft", 0, 0, 5, 1);
		this.chestGenMineshaftCorridor.setSliderMode(PMPGuiSliderMode.normal);
		
		yPos += 24;
		this.chestGenStronghold = new PMPGuiSlider(this, 11, xPos, yPos, 150, 20, "gui.stronghold", 0, 0, 5, 1);
		this.chestGenStronghold.setSliderMode(PMPGuiSliderMode.normal);
		
		yPos += 24;
		this.chestGenVillageBlacksmith = new PMPGuiSlider(this, 12, xPos, yPos, 150, 20, "gui.villageBlacksmith", 0, 0, 5, 1);
		this.chestGenVillageBlacksmith.setSliderMode(PMPGuiSliderMode.normal);
		
		xPos = this.width / 2 - 75;
		yPos += 48;
		this.buttonChestItems = new PMPGuiButton(20, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.chestItems") + "...");
		
		updateButtonStatesFromSettings();
		this.buttonList.add(this.chestGenBonus);
		this.buttonList.add(this.chestGenDesertTemple);
		this.buttonList.add(this.chestGenDungeonChest);
		this.buttonList.add(this.chestGenJungleTemple);
		this.buttonList.add(this.chestGenMineshaftCorridor);
		this.buttonList.add(this.chestGenStronghold);
		this.buttonList.add(this.chestGenVillageBlacksmith);
		this.buttonList.add(this.buttonChestItems);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				PlantMegaPack.settings.resetChestGeneration();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				PlantMegaPack.settings.saveOptions();
				break;
			case 20: 
				this.mc.displayGuiScreen(new PMPGuiGeneratedChestItems(this));
				return;
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
		this.chestGenBonus.setIntValue(PlantMegaPack.settings.chestGenBonus);
		this.chestGenDesertTemple.setIntValue(PlantMegaPack.settings.chestGenDesertTemple);
		this.chestGenDungeonChest.setIntValue(PlantMegaPack.settings.chestGenDungeonChest);
		this.chestGenJungleTemple.setIntValue(PlantMegaPack.settings.chestGenJungleTemple);
		this.chestGenMineshaftCorridor.setIntValue(PlantMegaPack.settings.chestGenMineshaftCorridor);
		this.chestGenStronghold.setIntValue(PlantMegaPack.settings.chestGenStronghold);
		this.chestGenVillageBlacksmith.setIntValue(PlantMegaPack.settings.chestGenVillageBlacksmith);
		
		this.chestGenBonus.updateDisplayString();
		this.chestGenDesertTemple.updateDisplayString();
		this.chestGenDungeonChest.updateDisplayString();
		this.chestGenJungleTemple.updateDisplayString();
		this.chestGenMineshaftCorridor.updateDisplayString();
		this.chestGenStronghold.updateDisplayString();
		this.chestGenVillageBlacksmith.updateDisplayString();
	}
	
	private void updateSettingsFromButtonStates() {
		PlantMegaPack.settings.chestGenBonus = this.chestGenBonus.getIntValue();
		PlantMegaPack.settings.chestGenDesertTemple = this.chestGenDesertTemple.getIntValue();
		PlantMegaPack.settings.chestGenDungeonChest = this.chestGenDungeonChest.getIntValue();
		PlantMegaPack.settings.chestGenJungleTemple = this.chestGenJungleTemple.getIntValue();
		PlantMegaPack.settings.chestGenMineshaftCorridor = this.chestGenMineshaftCorridor.getIntValue();
		PlantMegaPack.settings.chestGenStronghold = this.chestGenStronghold.getIntValue();
		PlantMegaPack.settings.chestGenVillageBlacksmith = this.chestGenVillageBlacksmith.getIntValue();
	}
}
