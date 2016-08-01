package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiMainMenu
	extends PMPGuiBase
{
	private ResourceLocation textureGlobe;
	private ResourceLocation textureSettings;
	private PMPGuiButton buttonGeneral;
	private PMPGuiButton buttonPlants;
	private PMPGuiButton buttonTrees;
	private PMPGuiButton buttonWorldgen;
	
	public PMPGuiMainMenu(GuiScreen parent) {
		super(parent, "gui.mainMenu", 0);
		
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureSettings = new ResourceLocation("plantmegapack:textures/gui/iconSettings.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 68;
		this.buttonGeneral = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("gui.generalSettings") + "...");
		
		xPos = this.width / 2 - 136;
		yPos = 132;
		this.buttonPlants = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 88, 20, I18n.translateToLocal("gui.plants") + "...");
		
		xPos = this.width / 2 - 44;
		this.buttonTrees = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 88, 20, I18n.translateToLocal("gui.trees") + "...");
		
		xPos = this.width / 2 + 4 + 44;
		this.buttonWorldgen = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 88, 20, I18n.translateToLocal("gui.worldgen") + "...");
		
		this.buttonList.add(this.buttonGeneral);
		this.buttonList.add(this.buttonPlants);
		this.buttonList.add(this.buttonTrees);
		this.buttonList.add(this.buttonWorldgen);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiGeneral(this));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiPlants(this));
				return;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiTrees(this));
				return;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiWorldgen(this));
				return;
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
		int iconSize = 24;
		int xPos = this.width / 2 - iconSize / 2;
		int yPos = 40;
		PMPGuiCore.drawTexture(this.textureSettings, xPos, yPos, 20);
		
		xPos = this.width / 2 - 92 - iconSize / 2;
		yPos = 104;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, iconSize);
		
		xPos = this.width / 2 - iconSize / 2;
		PMPGuiCore.drawTreeTexture("saplingDarkOakEvergreen", xPos, yPos, iconSize);
		
		xPos = this.width / 2 + 92 - iconSize / 2;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
	}
}
