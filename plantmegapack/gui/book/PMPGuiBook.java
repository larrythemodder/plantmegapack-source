package plantmegapack.gui.book;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.book.PMPBookEntry;

@SideOnly(Side.CLIENT)
public class PMPGuiBook
	extends GuiScreen
{
	public static final ResourceLocation bookBase = new ResourceLocation("plantmegapack:textures/gui/bookBase.png");
	public static final ResourceLocation bookElements = new ResourceLocation("plantmegapack:textures/gui/bookElements.png");
	protected final int BUTTON_DONE = 0;
	protected final int BUTTON_PREV_PAGE = 1;
	protected final int BUTTON_NEXT_PAGE = 2;
	public static final int BUTTON_TAB1 = 3;
	public static final int BUTTON_TAB2 = 4;
	public static final int BUTTON_TAB3 = 5;
	private PMPGuiBookTab currentTab;
	private PMPBookEntry selectedEntry;
	protected final int bookImageWidth = 256;
	protected final int bookImageHeight = 176;
	private GuiButton buttonDone;
	private PageButton buttonPrevPage;
	private PageButton buttonNextPage;
	private TabButton buttonTab1;
	private TabButton buttonTab2;
	private TabButton buttonTab3;
	private PMPGuiBookIndex tabIndex;
	private PMPGuiBookEntry tabEntry;
	private PMPGuiBookCollect tabCollect;
	
	public PMPGuiBook(EntityPlayer player, PMPBookEntry entry) {
		this.selectedEntry = entry;
		this.tabIndex = new PMPGuiBookIndex(this);
		this.tabEntry = new PMPGuiBookEntry(this);
		this.tabCollect = new PMPGuiBookCollect(this);
	}
	
	public void initGui() {
		this.buttonList.clear();
		getClass();int startX = (this.width - 256) / 2;
		int startY = 4;
		getClass();getClass();this.buttonDone = new GuiButton(0, this.width / 2 - 70, startY + 176 + 4, 140, 20, I18n.format("gui.done", new Object[0]));
		getClass();this.buttonPrevPage = new PageButton(1, startX + 22, startY + 152, true);
		getClass();this.buttonNextPage = new PageButton(2, startX + 198, startY + 152, false);
		this.buttonTab1 = new TabButton(3, startX + 236, startY + 14);
		this.buttonTab2 = new TabButton(4, startX + 236, startY + 52);
		this.buttonTab3 = new TabButton(5, startX + 236, startY + 90);
		this.buttonList.add(this.buttonDone);
		this.buttonList.add(this.buttonPrevPage);
		this.buttonList.add(this.buttonNextPage);
		this.buttonList.add(this.buttonTab1);
		this.buttonList.add(this.buttonTab2);
		this.buttonList.add(this.buttonTab3);
		
		this.tabIndex.initGui();
		this.tabEntry.initGui();
		this.tabCollect.initGui();
		
		switchToTab(this.tabEntry);
	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		if (this.currentTab != null) {
			this.currentTab.handleMouseInput();
		}
	}
	
	protected void actionPerformed(GuiButton button)
		throws IOException
	{
		if (button.enabled) {
			getClass();
			if (button.id == 0) {
				this.mc.displayGuiScreen((GuiScreen)null);
			} else
			{
				getClass();
				if (button.id == 1) {
					this.currentTab.previousPage();
					updateGui();
				} else {
					getClass();
					if (button.id == 2) {
						this.currentTab.nextPage();
						updateGui();
					} else if (button.id == 3) {
						switchToTab(this.tabIndex);
					} else if (button.id == 4) {
						switchToTab(this.tabEntry);
					} else if (button.id == 5) {
						switchToTab(this.tabCollect);
					}
				}
			}
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bookBase);
		getClass();int startX = (this.width - 256) / 2;
		getClass();getClass();drawTexturedModalRect(startX, 4, 0, 0, 256, 176);
		if (this.currentTab != null) {
			this.currentTab.drawScreen(mouseX, mouseY, partialTicks);
			if (this.currentTab.getPageCount() > 1) {
				String pageInfo = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.currentTab.getCurrentPage()), Integer.valueOf(this.currentTab.getPageCount()) });
				int textWidth = this.fontRendererObj.getStringWidth(pageInfo);
				getClass();
				getClass();
				getClass();
				this.fontRendererObj.drawString(pageInfo, (this.width - 256) / 2 + (256 - textWidth) / 2, 4 + /*'�'*/ - 22, 0);
			}
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void switchToTab(PMPGuiBookTab tab) {
		this.currentTab = tab;
		this.currentTab.setPage(1);
		this.currentTab.updateGui();
		updateGui();
	}
	
	public void updateGui() {
		this.buttonPrevPage.visible = ((this.currentTab.getPageCount() > 1) && (this.currentTab.getCurrentPage() > 1));
		this.buttonNextPage.visible = ((this.currentTab.getPageCount() > 1) && (this.currentTab.getCurrentPage() < this.currentTab.getPageCount()));
		this.buttonTab1.setSelected(this.currentTab == this.tabIndex);
		this.buttonTab2.setSelected(this.currentTab == this.tabEntry);
		this.buttonTab3.setSelected(this.currentTab == this.tabCollect);
	}
	
	public void switchToEntryTab() {
		switchToTab(this.tabEntry);
	}
	
	public PMPBookEntry getSelectedEntry() {
		return this.selectedEntry;
	}
	
	public void setSelectedEntry(PMPBookEntry entry) {
		this.selectedEntry = entry;
	}
	
	public FontRenderer getFontRenderer() {
		return this.fontRendererObj;
	}
	
	public void bindBookElementsTexture() {
		this.mc.getTextureManager().bindTexture(bookElements);
	}
	
	@SideOnly(Side.CLIENT)
	static class TabButton
		extends GuiButton
	{
		private boolean selected;
		
		public TabButton(int buttonId, int x, int y) {
			super(1, x, y, 14, 36, ""); /*************************************************/
		}
		
		public boolean getSelected() {
			return this.selected;
		}
		
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (this.visible) {
				boolean mouseOver = (mouseX >= this.xPosition) && (mouseY >= this.yPosition) && (mouseX < this.xPosition + this.width) && (mouseY < this.yPosition + this.height);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(PMPGuiBook.bookElements);
				int textureX = (this.id == 3) || (this.id == 5) ? 145 : 190;
				int textureY = (this.id == 3) || (this.id == 4) ? 1 : 38;
				if (this.selected) {
					textureX += 30;
				} else if (mouseOver) {
					textureX += 15;
				}
				drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, 14, 36);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	static class PageButton
		extends GuiButton
	{
		private final boolean prevPage;
		
		public PageButton(int buttonId, int x, int y, boolean prevPage) {
			super(2, x, y, 20, 10, ""); /**************************************/
			this.prevPage = prevPage;
			this.visible = true;
		}
		
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (this.visible) {
				boolean mouseOver = (mouseX >= this.xPosition) && (mouseY >= this.yPosition) && (mouseX < this.xPosition + this.width) && (mouseY < this.yPosition + this.height);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(PMPGuiBook.bookElements);
				int startX = 124;
				int startY = 1 + (!this.prevPage ? 22 : 0);
				if (mouseOver) {
					startY += 11;
				}
				drawTexturedModalRect(this.xPosition, this.yPosition, startX, startY, 20, 10);
			}
		}
	}
}
