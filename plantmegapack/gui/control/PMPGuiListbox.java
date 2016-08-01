package plantmegapack.gui.control;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class PMPGuiListbox
	extends GuiListExtended
{
	private static final ResourceLocation listBackground = new ResourceLocation("plantmegapack:textures/gui/bookBackground.png");
	private IPMPListBoxOwner parent;
	private List<PMPGuiListboxEntry> listEntries = Lists.newArrayList();
	public boolean visible;
	private boolean drawCustom;
	
	public PMPGuiListbox(IPMPListBoxOwner parent, int xPos, int yPos, int width, int height, int slotHeight) {
		super(Minecraft.getMinecraft(), width, parent.getHeight(), yPos, height, slotHeight);
		this.parent = parent;
		this.visible = true;
		this.drawCustom = false;
		this.left = xPos;
		this.right = (xPos + width);
		this.bottom = (yPos + height);
		setHasListHeader(false, 0);
		this.showSelectionBox = true;
		registerScrollButtons(7, 8);
	}
	
	public void addListboxEntry(String name, String localizedName) {
		this.listEntries.add(new PMPGuiListboxEntry(name, localizedName, this));
	}
	
	public boolean getCustomDrawMode() {
		return this.drawCustom;
	}
	
	public void setCustomDrawMode(boolean customDraw) {
		this.drawCustom = customDraw;
	}
	
	public void scrollToAndSelectEntry(int slotID) {
		selectListItem(slotID);
		this.amountScrolled = (slotID * this.slotHeight + this.height / this.slotHeight - 4 - this.slotHeight);
		bindAmountScrolled();
	}
	
	public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
		if (this.visible) {
			drawScreenCustom(mouseXIn, mouseYIn, partialTicks);
		}
	}
	
	private void drawScreenCustom(int mouseXIn, int mouseYIn, float partialTicks) {
		if (this.visible) {
			this.mouseX = mouseXIn;
			this.mouseY = mouseYIn;
			drawBackground();
			int i = getScrollBarX();
			int j = i + 6;
			bindAmountScrolled();
			GlStateManager.disableLighting();
			GlStateManager.disableFog();
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer worldrenderer = tessellator.getBuffer();
			drawContainerBackground(tessellator);
			int k = this.left + this.width / 2 - getListWidth() / 2 + 2;
			int l = this.top + 4 - (int)this.amountScrolled;
			drawSelectionBox(k, l, mouseXIn, mouseYIn);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
			GlStateManager.disableAlpha();
			GlStateManager.shadeModel(7425);
			GlStateManager.disableTexture2D();
			int j1 = getMaxScroll();
			if (j1 > 0) {
				int k1 = (this.bottom - this.top) * (this.bottom - this.top) / getContentHeight();
				k1 = MathHelper.clamp_int(k1, 32, this.bottom - this.top - 8);
				int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;
				if (l1 < this.top) {
					l1 = this.top;
				}
				int color = this.drawCustom ? 9729114 : 0;
				int r = color >> 16 & 0xFF;
				int g = color >> 8 & 0xFF;
				int b = color & 0xFF;
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos(i, this.bottom, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j, this.bottom, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j, this.top, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(i, this.top, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
				tessellator.draw();
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos(i, l1 + k1, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j, l1 + k1, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j, l1, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(i, l1, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
				tessellator.draw();
				color = this.drawCustom ? 13746081 : 6316128;
				r = color >> 16 & 0xFF;
				g = color >> 8 & 0xFF;
				b = color & 0xFF;
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos(i, l1 + k1 - 1, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j - 1, l1 + k1 - 1, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j - 1, l1, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(i, l1, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
				tessellator.draw();
			}
			renderDecorations(mouseXIn, mouseYIn);
			GlStateManager.enableTexture2D();
			GlStateManager.shadeModel(7424);
			GlStateManager.enableAlpha();
			GlStateManager.disableBlend();
		}
	}
	
	protected void drawContainerBackground(Tessellator tessellator) {
		VertexBuffer worldrenderer = tessellator.getBuffer();
		this.mc.getTextureManager().bindTexture(this.drawCustom ? listBackground : Gui.OPTIONS_BACKGROUND);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 32.0F;
		int color = this.drawCustom ? 16777215 : 0;
		int r = color >> 16 & 0xFF;
		int g = color >> 8 & 0xFF;
		int b = color & 0xFF;
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		worldrenderer.pos(this.left, this.bottom, 0.0D).tex(this.left / f, (this.bottom + (int)this.amountScrolled) / f).color(r, g, b, 255).endVertex();
		worldrenderer.pos(this.right, this.bottom, 0.0D).tex(this.right / f, (this.bottom + (int)this.amountScrolled) / f).color(r, g, b, 255).endVertex();
		worldrenderer.pos(this.right, this.top, 0.0D).tex(this.right / f, (this.top + (int)this.amountScrolled) / f).color(r, g, b, 255).endVertex();
		worldrenderer.pos(this.left, this.top, 0.0D).tex(this.left / f, (this.top + (int)this.amountScrolled) / f).color(r, g, b, 255).endVertex();
		tessellator.draw();
	}
	
	protected void drawSelectionBox(int par1, int par2, int mouseXIn, int mouseYIn) {
		int i = getSize();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrenderer = tessellator.getBuffer();
		for (int j = 0; j < i; j++) {
			int k = par2 + j * this.slotHeight + this.headerPadding;
			int l = this.slotHeight - 4;
			if ((k > this.bottom) || (k + l < this.top)) {
				updateItemPos(j, par1, k);
			}
			if ((this.showSelectionBox) && (isSelected(j)) && (k > this.top + 2) && (k < this.bottom - 8)) {
				int i1 = this.left + (this.width / 2 - getListWidth() / 2);
				int j1 = this.left + this.width / 2 + getListWidth() / 2;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableTexture2D();
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				
				int color = getEnabled() ? 9502624 : this.drawCustom ? 12165763 : 6316128;
				int r = color >> 16 & 0xFF;
				int g = color >> 8 & 0xFF;
				int b = color & 0xFF;
				worldrenderer.pos(i1, k + l + 2, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j1, k + l + 2, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j1, k - 2, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(i1, k - 2, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
				
				color = getEnabled() ? 2134048 : this.drawCustom ? 13746081 : 6316128;
				r = color >> 16 & 0xFF;
				g = color >> 8 & 0xFF;
				b = color & 0xFF;
				worldrenderer.pos(i1 + 1, k + l + 1, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j1 - 1, k + l + 1, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(j1 - 1, k - 1, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
				worldrenderer.pos(i1 + 1, k - 1, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
				tessellator.draw();
				GlStateManager.enableTexture2D();
			}
			drawSlot(j, par1, k, l, mouseXIn, mouseYIn);
		}
	}
	
	protected void drawSlot(int entryID, int x, int y, int slotHeight, int mouseX, int mouseY) {
		if ((y > this.top + 2) && (y < this.bottom - 8)) {
			getListEntry(entryID).drawEntry(entryID, x, y, getListWidth(), slotHeight, mouseX, mouseY, getSlotIndexFromScreenCoords(mouseX, mouseY) == entryID);
		}
	}
	
	protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
		if (getEnabled()) {
			super.elementClicked(slotIndex, isDoubleClick, mouseX, mouseY);
			this.selectedElement = slotIndex;
			if (this.parent != null) {
				if (isDoubleClick) {
					this.parent.listItemDoubleClicked(this, slotIndex);
				} else {
					this.parent.listItemSelected(this, slotIndex);
				}
			}
		}
	}
	
	public PMPGuiListboxEntry getListEntry(int slotIndex) {
		return (PMPGuiListboxEntry)this.listEntries.get(slotIndex);
	}
	
	public int getListWidth() {
		return this.width;
	}
	
	protected int getScrollBarX() {
		return this.right - 6;
	}
	
	public int getSize() {
		return this.listEntries.size();
	}
	
	public void handleMouseInput() {
		if ((isMouseYWithinSlotBounds(this.mouseY)) && (getEnabled())) {
			if (Mouse.isButtonDown(0)) {
				if (this.initialClickY == -1.0F) {
					boolean flag = true;
					if ((this.mouseY >= this.top) && (this.mouseY <= this.bottom)) {
						int i = this.left + 4;
						int j = getScrollBarX() - 1;
						int k = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
						int l = k / this.slotHeight;
						if ((this.mouseX >= i) && (this.mouseX <= j) && (l >= 0) && (k >= 0) && (l < getSize())) {
							boolean flag1 = (l == this.selectedElement) && (Minecraft.getSystemTime() - this.lastClicked < 250L);
							elementClicked(l, flag1, this.mouseX, this.mouseY);
							this.selectedElement = l;
							this.lastClicked = Minecraft.getSystemTime();
						} else if ((this.mouseX >= i) && (this.mouseX <= j) && (k < 0)) {
							clickedHeader(this.mouseX - i, this.mouseY - this.top + (int)this.amountScrolled - 4);
							flag = false;
						}
						int i2 = getScrollBarX();
						int i1 = this.right;
						if ((this.mouseX >= i2) && (this.mouseX <= i1)) {
							this.scrollMultiplier = -1.0F;
							int j1 = getMaxScroll();
							if (j1 < 1) {
								j1 = 1;
							}
							int k1 = (int)((this.bottom - this.top) * (this.bottom - this.top) / getContentHeight());
							k1 = MathHelper.clamp_int(k1, 32, this.bottom - this.top - 8);
							this.scrollMultiplier /= (this.bottom - this.top - k1) / j1;
						} else {
							this.scrollMultiplier = 1.0F;
						}
						if (flag) {
							this.initialClickY = this.mouseY;
						} else {
							this.initialClickY = -2;
						}
					} else {
						this.initialClickY = -2;
					}
				} else if (this.initialClickY >= 0.0F) {
					this.amountScrolled -= (this.mouseY - this.initialClickY) * this.scrollMultiplier;
					this.initialClickY = this.mouseY;
				}
			} else {
				this.initialClickY = -1;
			}
			int l1 = Mouse.getEventDWheel();
			if (l1 != 0) {
				if (l1 > 0) {
					l1 = -1;
				} else if (l1 < 0) {
					l1 = 1;
				}
				this.amountScrolled += l1 * this.slotHeight;
			}
		}
	}
	
	protected boolean isSelected(int slotIndex) {
		return this.selectedElement == slotIndex;
	}
	
	public int getSelectedItemIndex() {
		return this.selectedElement;
	}
	
	public String getSelectedItemKey() {
		if ((this.selectedElement >= 0) && (!this.listEntries.isEmpty())) {
			if (this.selectedElement > this.listEntries.size() - 1) {
				this.selectedElement = 0;
			}
			return ((PMPGuiListboxEntry)this.listEntries.get(this.selectedElement)).unlocalizedName;
		}
		return "";
	}
	
	public String getSelectedItemText() {
		if ((this.selectedElement >= 0) && (!this.listEntries.isEmpty())) {
			return ((PMPGuiListboxEntry)this.listEntries.get(this.selectedElement)).localizedName;
		}
		return "";
	}
	
	public void selectListItem(int slotIndex) {
		if ((slotIndex < 0) || (slotIndex >= this.listEntries.size())) {
			return;
		}
		this.selectedElement = slotIndex;
	}
	
	public void saveListboxState(PMPGuiListboxState state) {
		state.amountScrolled = this.amountScrolled;
		state.scrollMultiplier = this.scrollMultiplier;
		state.selectedElement = this.selectedElement;
	}
	
	public void setListboxState(PMPGuiListboxState state) {
		this.amountScrolled = state.amountScrolled;
		this.scrollMultiplier = state.scrollMultiplier;
		this.selectedElement = state.selectedElement;
	}
	
	public void clearAllEntries() {
		this.listEntries.clear();
		this.selectedElement = 0;
	}
	
	public List<PMPGuiListboxEntry> getListEntries() {
		return this.listEntries;
	}
	
	public int getSlotFromKey(String key) {
		int size = this.listEntries.size();
		for (int i = 0; i < size; i++) {
			if (((PMPGuiListboxEntry)this.listEntries.get(i)).unlocalizedName.matches(key)) {
				return i;
			}
		}
		return -1;
	}
}
