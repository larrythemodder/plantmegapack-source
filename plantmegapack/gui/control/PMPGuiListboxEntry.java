package plantmegapack.gui.control;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiListboxEntry
	implements IGuiListEntry
{
	private PMPGuiListbox parent;
	private final Minecraft mc = Minecraft.getMinecraft();
	public String localizedName;
	public String unlocalizedName;
	
	public PMPGuiListboxEntry(String unlocalizedName, String localizedName, PMPGuiListbox parent) {
		this.localizedName = localizedName;
		this.unlocalizedName = unlocalizedName;
		this.parent = parent;
	}
	
	public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
	
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
		if (isSelected) {
			int color = 16777088;
			if (this.localizedName.charAt(0) == '0'/*'�'*/) {
				this.mc.fontRendererObj.drawStringWithShadow(this.mc.fontRendererObj.trimStringToWidth(this.localizedName.substring(2), listWidth - 12), x + 2, y, color);
			} else {
				this.mc.fontRendererObj.drawString(this.mc.fontRendererObj.trimStringToWidth(this.localizedName, listWidth - 12), x + 2, y, color);
			}
		} else {
			int color = this.parent.getCustomDrawMode() ? 9007699 : 8437888;
			this.mc.fontRendererObj.drawString(this.mc.fontRendererObj.trimStringToWidth(this.localizedName, listWidth - 12), x + 2, y, color);
		}
	}
	
	public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
		return false;
	}
	
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
}
