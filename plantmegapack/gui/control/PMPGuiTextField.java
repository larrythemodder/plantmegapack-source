package plantmegapack.gui.control;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class PMPGuiTextField
	extends GuiTextField
{
	private boolean useStrictEntry;
	
	public PMPGuiTextField(int ID, FontRenderer fontRenderer, int xPos, int yPos, int width, int height) {
		super(ID, fontRenderer, xPos, yPos, width, height);
		this.useStrictEntry = false;
	}
	
	public void setStrictEntry(boolean enable) {
		this.useStrictEntry = enable;
	}
	
	public boolean textboxKeyTyped(char typedChar, int keyCode) {
		if (!isFocused()) {
			return false;
		}
		if (this.useStrictEntry) {
			if ((!Character.isLetter(typedChar)) && (!Character.isDigit(typedChar)) && (typedChar != '_') && (typedChar != '\b')) {
				return false;
			}
		}
		return super.textboxKeyTyped(typedChar, keyCode);
	}
}
