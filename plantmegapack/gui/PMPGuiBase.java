package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiSlider;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public abstract class PMPGuiBase
	extends GuiScreen
{
	protected final int COMMON_LIST_HEIGHT = 140;
	protected final int COMMON_LIST_WIDTH = 180;
	protected GuiButton buttonDefault;
	protected GuiButton buttonDone;
	protected String name;
	protected GuiScreen parentGuiScreen;
	private int buttonLayout;
	
	public PMPGuiBase(GuiScreen parentGuiScreen, String screenName, int buttonLayout) {
		this.parentGuiScreen = parentGuiScreen;
		this.name = I18n.translateToLocal(screenName);
		this.buttonLayout = ((buttonLayout >= -1) && (buttonLayout <= 3) ? buttonLayout : 0);
	}
	
	public void initGui() {
		int yPos = this.height - 27;
		if (this.buttonLayout >= 0) {
			if (this.buttonLayout == 0) {
				int xPos = this.width / 2 - 90;
				this.buttonDone = new GuiButton(2, xPos, this.height - 27, 180, 20, I18n.translateToLocal("gui.done"));
				this.buttonList.add(this.buttonDone);
			} else if (this.buttonLayout == 1) {
				int xPos = this.width / 2 - 121;
				this.buttonDefault = new GuiButton(1, xPos, yPos, 88, 20, I18n.translateToLocal("gui.defaults"));
				xPos += 92;
				this.buttonDone = new GuiButton(2, xPos, yPos, 150, 20, I18n.translateToLocal("gui.done"));
				this.buttonList.add(this.buttonDefault);
				this.buttonList.add(this.buttonDone);
			} else if (this.buttonLayout == 2) {
				int xPos = this.width / 2 - 121;
				this.buttonDefault = new GuiButton(1, xPos, yPos, 88, 20, I18n.translateToLocal("gui.cancel"));
				xPos += 92;
				this.buttonDone = new GuiButton(2, xPos, yPos, 150, 20, I18n.translateToLocal("gui.proceed"));
				this.buttonList.add(this.buttonDefault);
				this.buttonList.add(this.buttonDone);
			} else if (this.buttonLayout == 3) {
				int xPos = this.width / 2 - 121;
				this.buttonDefault = new GuiButton(1, xPos, yPos, 88, 20, I18n.translateToLocal("gui.cancel"));
				xPos += 92;
				this.buttonDone = new GuiButton(2, xPos, yPos, 150, 20, I18n.translateToLocal("gui.createNewProfile"));
				this.buttonList.add(this.buttonDefault);
				this.buttonList.add(this.buttonDone);
			}
		}
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		drawLogoImage();
		drawTitleText();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			if ((this.buttonLayout == 2) || (this.buttonLayout == 3)) {
				this.mc.displayGuiScreen(this.parentGuiScreen);
				return;
			}
			switch (button.id) {
			case 1: 
				this.mc.displayGuiScreen(this);
				return;
			case 2: 
				this.mc.displayGuiScreen(this.parentGuiScreen);
				return;
			}
		}
	}
	
	protected void drawLogoImage() {
		if (PMPGuiCore.imageLogo != null) {
			int x = (this.width - 128) / 2;
			int y = 3;
			PMPGuiCore.drawTexture(PMPGuiCore.imageLogo, x, y, 128, 16);
		}
	}
	
	protected void drawTitleText() {
		drawCenteredString(this.fontRendererObj, this.name, this.width / 2, 20, 9502624);
	}
	
	protected FontRenderer getFontRenderer() {
		return this.fontRendererObj;
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {}
	
	public void listItemSelected(PMPGuiListbox list, int index) {}
	
	public void onSliderUpdate(PMPGuiSlider slider, int value) {}
}
