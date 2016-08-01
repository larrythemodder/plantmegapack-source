package plantmegapack.gui.control;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiButton
	extends GuiButton
{
	private PMPGuiButtonMode buttonMode;
	private boolean buttonState;
	private boolean selected;
	private int textColorMode;
	private boolean displayOnOff;
	
	public PMPGuiButton(int id, PMPGuiButtonMode mode, int xPosition, int yPosition, int width, int height, String label) {
		super(id, xPosition, yPosition, label);
		this.buttonMode = mode;
		this.buttonState = false;
		this.selected = false;
		this.textColorMode = 0;
		this.displayOnOff = true;
		this.width = width;
		this.height = height;
	}
	
	public void setDisplayOnOff(boolean state) {
		this.displayOnOff = state;
	}
	
	public void drawButton(Minecraft mc, int xPos, int yPos) {
		if (this.visible) {
			int buttonStartXPos = this.xPosition;
			int buttonWidth = this.width;
			if (this.buttonMode == PMPGuiButtonMode.TOGGLE) {
				PMPGuiCore.drawLED(this.xPosition, this.yPosition, 0, this.enabled, this.buttonState);
				buttonStartXPos += 6;
				buttonWidth -= 6;
			}
			FontRenderer fontrenderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(GuiButton.BUTTON_TEXTURES);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = ((xPos >= buttonStartXPos) && (yPos >= this.yPosition) && (xPos < buttonStartXPos + buttonWidth) && (yPos < this.yPosition + this.height));
			int k = getHoverState(this.hovered);
			GL11.glEnable(3042);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(770, 771);
			if (this.enabled) {
				if (this.selected) {
					k = 1;
					GL11.glColor4f(0.5F, 1.0F, 0.5F, 1.0F);
				} else {
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				}
			} else {
				GL11.glColor4f(0.5F, 0.55F, 0.5F, 1.0F);
			}
			drawTexturedModalRect(buttonStartXPos, this.yPosition, 0, 46 + k * 20, buttonWidth / 2, this.height);
			drawTexturedModalRect(buttonStartXPos + buttonWidth / 2, this.yPosition, 200 - buttonWidth / 2, 46 + k * 20, buttonWidth / 2, this.height);
			mouseDragged(mc, xPos, yPos);
			int textColor;
			switch (this.textColorMode) {
			case 0: 
			default: 
				textColor = 13696976;
				break;
			case 1: 
				textColor = 15761536;
			}
			if (this.selected) {
				switch (this.textColorMode) {
				case 0: 
				default: 
					textColor = 9502624;
					break;
				case 1: 
					textColor = 8405056;
					break;
				}
			} else if (!this.enabled) {
				switch (this.textColorMode) {
				case 0: 
				default: 
					textColor = 5267536;
					break;
				case 1: 
					textColor = 4194304;
					break;
				}
			} else if (this.hovered) {
				switch (this.textColorMode) {
				case 0: 
				default: 
					textColor = 16777088;
					break;
				case 1: 
					textColor = 16777088;
				}
			}
			drawCenteredString(fontrenderer, getDisplayString(), buttonStartXPos + buttonWidth / 2, this.yPosition + (this.height - 8) / 2, textColor);
		}
	}
	
	public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
		if ((!this.selected) && (super.mousePressed(mc, xPos, yPos))) {
			if (this.buttonMode != PMPGuiButtonMode.NORMAL) {
				if (xPos < this.xPosition + 6) {
					return false;
				}
				this.buttonState = (!this.buttonState);
			}
			return true;
		}
		return false;
	}
	
	private String getDisplayString() {
		if ((this.displayOnOff) && (this.buttonMode != PMPGuiButtonMode.NORMAL)) {
			return this.displayString + ": " + (this.buttonState ? I18n.format("options.on", new Object[0]) : I18n.format("options.off", new Object[0]));
		}
		return this.displayString;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setButtonMode(PMPGuiButtonMode mode) {
		this.buttonMode = mode;
	}
	
	public boolean getButtonState() {
		return this.buttonState;
	}
	
	public void setButtonState(boolean state) {
		this.buttonState = state;
	}
	
	public void setTextColorMode(int mode) {
		if ((mode < 0) || (mode > 1)) {
			return;
		}
		this.textColorMode = mode;
	}
}
