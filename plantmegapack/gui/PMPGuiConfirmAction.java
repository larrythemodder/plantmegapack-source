package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;

@SideOnly(Side.CLIENT)
public class PMPGuiConfirmAction
	extends PMPGuiBase
{
	private ResourceLocation textureContext;
	private int screenType;
	private int parentButtonID;
	
	public PMPGuiConfirmAction(GuiScreen parent, int screenType, int parentButtonID, String titleKey, String texture) {
		super(parent, titleKey, 2);
		this.screenType = screenType;
		this.parentButtonID = parentButtonID;
		this.textureContext = null;
		if ((texture != null) && (!texture.isEmpty())) {
			this.textureContext = new ResourceLocation("plantmegapack:textures/gui/" + texture + ".png");
		}
	}
	
	public void initGui() {
		super.initGui();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				this.parentGuiScreen.confirmClicked(true, this.parentButtonID);
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
	
	private void drawTextures() {
		if (this.textureContext != null) {
			int xPos = this.width / 2 - 10;
			int yPos = 36;
			PMPGuiCore.drawTexture(this.textureContext, xPos, yPos, 20);
		}
	}
	
	private void drawHeadingText() {
		int xPos = this.width / 2;
		int yPos = this.height / 2 - 40;
		switch (this.screenType) {
		case 0: 
			drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.warning"), xPos, yPos, 16777088);
			break;
		}
		yPos += 24;
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.confirm"), xPos, yPos, 9502624);
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {}
	
	public void listItemSelected(PMPGuiListbox list, int index) {}
}
