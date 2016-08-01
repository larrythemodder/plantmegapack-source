package plantmegapack.gui;

import java.util.List;
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
public class PMPGuiVillage
	extends PMPGuiBase
{
	private ResourceLocation textureHome;
	public PMPGuiButton worldgenVillager;
	
	public PMPGuiVillage(GuiScreen parent) {
		super(parent, "gui.worldgenVillages", 1);
		this.name = (I18n.translateToLocal("gui.worldgenFeatures") + " - " + I18n.translateToLocal("gui.worldgenVillages"));
		
		this.textureHome = new ResourceLocation("plantmegapack:textures/gui/iconHome.png");
	}
	
	public void initGui() {
		super.initGui();
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		this.worldgenVillager = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.villager"));
		
		updateButtonStatesFromSettings();
		this.buttonList.add(this.worldgenVillager);
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				
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
	
	private void drawHeadingText() {}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureHome, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {}
	
	private void updateSettingsFromButtonStates() {}
}
