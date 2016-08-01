package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiFeatures
	extends PMPGuiBase
{
	public PMPGuiFeatures(GuiScreen parent) {
		super(parent, "gui.features", 0);
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
				break;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawTextures();
	}
	
	private void drawTextures() {}
}
