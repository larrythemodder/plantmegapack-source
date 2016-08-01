package plantmegapack.gui.book;

import net.minecraft.client.gui.FontRenderer;

public abstract class PMPGuiBookTab
{
	protected PMPGuiBook parent;
	protected int page;
	private int pageCount;
	
	public PMPGuiBookTab(PMPGuiBook parent, int pageCount) {
		this.parent = parent;
		this.page = 1;
		this.pageCount = pageCount;
	}
	
	public abstract void initGui();
	
	public abstract void updateGui();
	
	public abstract void handleMouseInput();
	
	public abstract void drawScreen(int paramInt1, int paramInt2, float paramFloat);
	
	public int getCurrentPage() {
		return this.page;
	}
	
	public void setPage(int page) {
		if ((page >= 1) && (page <= this.pageCount)) {
			this.page = page;
		}
	}
	
	public int getPageCount() {
		return this.pageCount;
	}
	
	public void previousPage() {
		if ((this.page > 1) && (this.pageCount > 1)) {
			this.page -= 1;
		}
	}
	
	public void nextPage() {
		if (this.page < this.pageCount) {
			this.page += 1;
		}
	}
	
	protected void drawBorderedText(String text, int x, int y, int colorBorder, int colorText) {
		FontRenderer fr = this.parent.getFontRenderer();
		fr.drawString(text, x - 1, y - 1, colorBorder);
		fr.drawString(text, x, y - 1, colorBorder);
		fr.drawString(text, x + 1, y - 1, colorBorder);
		fr.drawString(text, x - 1, y, colorBorder);
		fr.drawString(text, x + 1, y, colorBorder);
		fr.drawString(text, x - 1, y + 1, colorBorder);
		fr.drawString(text, x, y + 1, colorBorder);
		fr.drawString(text, x + 1, y + 1, colorBorder);
		fr.drawString(text, x, y, colorText);
	}
}
