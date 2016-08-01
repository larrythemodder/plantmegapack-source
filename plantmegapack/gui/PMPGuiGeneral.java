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
import plantmegapack.gui.book.PMPGuiBookOptions;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiGeneral
	extends PMPGuiBase
{
	private final int BUTTON_WIDTH = 129;
	private ResourceLocation textureChest;
	private ResourceLocation textureBook;
	private ResourceLocation textureInfo;
	private ResourceLocation texturePowder;
	private ResourceLocation textureSalve;
	private ResourceLocation textureWrench;
	private PMPGuiButton generatedChests;
	private PMPGuiButton plants;
	private PMPGuiButton realism;
	private PMPGuiButton tooltips;
	private PMPGuiButton buttonBook;
	private PMPGuiButton buttonSalves;
	private PMPGuiButton buttonPowders;
	
	public PMPGuiGeneral(GuiScreen parent) {
		super(parent, "gui.generalSettings", 0);
		
		this.textureChest = new ResourceLocation("plantmegapack:textures/gui/iconChest.png");
		this.textureBook = new ResourceLocation("plantmegapack:textures/items/bookPlantGuide.png");
		this.textureInfo = new ResourceLocation("plantmegapack:textures/gui/iconInfo.png");
		this.texturePowder = new ResourceLocation("plantmegapack:textures/items/powderFertilizer.png");
		this.textureSalve = new ResourceLocation("plantmegapack:textures/items/salveFireResist.png");
		this.textureWrench = new ResourceLocation("plantmegapack:textures/gui/iconWrench.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 154 + 21;
		int yPos = 60;
		getClass();this.generatedChests = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.generatedChests") + "...");
		yPos += 24;
		getClass();this.plants = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.plants") + "...");
		yPos += 24;
		getClass();this.realism = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.realism") + "...");
		yPos += 24;
		getClass();this.tooltips = new PMPGuiButton(6, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.tooltips") + "...");
		
		xPos = this.width / 2 + 4 + 21;
		yPos = 60;
		getClass();this.buttonBook = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.plantGuide0") + "...");
		yPos += 24;
		getClass();this.buttonPowders = new PMPGuiButton(11, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.powders") + "...");
		yPos += 24;
		getClass();this.buttonSalves = new PMPGuiButton(12, PMPGuiButtonMode.NORMAL, xPos, yPos, 129, 20, I18n.translateToLocal("gui.salves") + "...");
		
		this.buttonList.add(this.generatedChests);
		this.buttonList.add(this.plants);
		this.buttonList.add(this.realism);
		this.buttonList.add(this.tooltips);
		this.buttonList.add(this.buttonBook);
		this.buttonList.add(this.buttonSalves);
		this.buttonList.add(this.buttonPowders);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				PlantMegaPack.settings.saveOptions();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiGeneratedChests(this));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiGeneralPlants(this));
				return;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiRealism(this));
				return;
			case 6: 
				this.mc.displayGuiScreen(new PMPGuiTooltips(this));
				return;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiBookOptions(this));
				return;
			case 11: 
				this.mc.displayGuiScreen(new PMPGuiPowders(this));
				return;
			case 12: 
				this.mc.displayGuiScreen(new PMPGuiSalves(this));
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
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.settings"), this.width / 2 - 75 - 4, 44, 5296160);
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.items"), this.width / 2 + 75 + 4, 44, 5296160);
	}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 154;
		int yPos = 60;
		PMPGuiCore.drawTexture(this.textureChest, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureWrench, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureInfo, xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		PMPGuiCore.drawTexture(this.textureBook, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.texturePowder, xPos, yPos, 20);
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureSalve, xPos, yPos, 20);
	}
}
