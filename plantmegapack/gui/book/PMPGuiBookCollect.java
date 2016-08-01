package plantmegapack.gui.book;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.core.PMPItems;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.item.PMPItemFlower;
import plantmegapack.item.PMPItemFragment;
import plantmegapack.item.PMPItemPowder;
import plantmegapack.item.PMPItemRootMedicinal;
import plantmegapack.item.PMPItemSalve;
import plantmegapack.item.PMPItemStem;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPStem;

public class PMPGuiBookCollect
	extends PMPGuiBookTab
{
	public PMPGuiBookCollect(PMPGuiBook parent) {
		super(parent, 6);
	}
	
	public void initGui() {}
	
	public void updateGui() {}
	
	public void handleMouseInput() {}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.parent.getClass();int startX = (this.parent.width - 256) / 2 + 22;
		int startY = 4;
		int curLine = startY + 16;
		int lineHeight = this.parent.getFontRenderer().FONT_HEIGHT + 2;
		drawBorderedText(I18n.translateToLocal(this.page > 3 ? "gui.plantItemsCrafted" : "gui.plantItemsCollected"), startX, curLine, 0, 5296160);
		curLine += lineHeight + 1;
		switch (this.page) {
		case 1: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.flowers"), startX, curLine, 0);
			drawFlowerParts(startX, curLine + lineHeight + 4);
			break;
		case 2: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.stems"), startX, curLine, 0);
			drawStemParts(startX, curLine + lineHeight + 4);
			break;
		case 3: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.roots"), startX, curLine, 0);
			drawRootMedicinalParts(startX, curLine + lineHeight + 4);
			break;
		case 4: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.powders"), startX, curLine, 0);
			drawPowderParts(startX, curLine + lineHeight + 4);
			break;
		case 5: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.salves"), startX, curLine, 0);
			drawSalveParts(startX, curLine + lineHeight + 4);
			break;
		case 6: 
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.coralFragments"), startX, curLine, 0);
			drawCoralParts(startX, curLine + lineHeight + 4);
			break;
		}
	}
	
	private void drawFlowerParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPFlower flower : PMPFlower.values()) {
			PMPItemFlower flowerItem = PlantMegaPack.items.getFlowerItem(flower);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(flowerItem.getUnlocalizedName())) {
				PMPGuiCore.drawItemTexture(flower.name(), xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				this.parent.drawTexturedModalRect(xOffset, yOffset, 1, 86, 16, 16);
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(PMPColor.getColorFromID(flower.ID).getLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 5) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
	
	private void drawStemParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPStem stem : PMPStem.values()) {
			PMPItemStem stemItem = PlantMegaPack.items.getStemItem(stem);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(stemItem.getUnlocalizedName())) {
				PMPGuiCore.drawItemTexture(stem.name(), xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				if (stem == PMPStem.stemCactus) {
					this.parent.drawTexturedModalRect(xOffset, yOffset, 103, 86, 16, 16);
				} else {
					this.parent.drawTexturedModalRect(xOffset, yOffset, 18, 86, 16, 16);
				}
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(stem.getLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 5) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
	
	private void drawRootMedicinalParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPRootMedicinal root : PMPRootMedicinal.values()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			String key = String.format("%s:%d", new Object[] { PlantMegaPack.items.getMedicinalRootItem().getUnlocalizedName(), Integer.valueOf(root.ID) });
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(key)) {
				PMPGuiCore.drawItemTexture("rootMedicinal", xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				this.parent.drawTexturedModalRect(xOffset, yOffset, 35, 86, 16, 16);
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(root.getLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 3) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
	
	private void drawPowderParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPPowder powder : PMPPowder.values()) {
			PMPItemPowder powderItem = PlantMegaPack.items.getPowderItem(powder);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(powderItem.getUnlocalizedName())) {
				PMPGuiCore.drawItemTexture(powder.name(), xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				this.parent.drawTexturedModalRect(xOffset, yOffset, 52, 86, 16, 16);
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(powder.getLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 3) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
	
	private void drawSalveParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPSalve salve : PMPSalve.values()) {
			PMPItemSalve salveItem = PlantMegaPack.items.getSalveItem(salve);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(salveItem.getUnlocalizedName())) {
				PMPGuiCore.drawItemTexture(salve.name(), xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				this.parent.drawTexturedModalRect(xOffset, yOffset, 69, 86, 16, 16);
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(salve.getShortLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 3) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
	
	private void drawCoralParts(int startX, int startY) {
		int xOffset = startX;
		int yOffset = startY;
		int count = 0;
		for (PMPFragment fragment : PMPFragment.values()) {
			PMPItemFragment fragmentItem = PlantMegaPack.items.getCoralFragmentItem(fragment);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int color;
			if (PlantMegaPack.bookRegistry.isItemCollected(fragmentItem.getUnlocalizedName())) {
				PMPGuiCore.drawItemTexture(fragment.name(), xOffset, yOffset, 16);
				color = 0;
			} else
			{
				this.parent.bindBookElementsTexture();
				this.parent.drawTexturedModalRect(xOffset, yOffset, 86, 86, 16, 16);
				color = 13746081;
			}
			this.parent.getFontRenderer().drawString(PMPColor.getColorFromID(fragment.ID).getLocalizedName(), xOffset + 20, yOffset + 5, color);
			count++;
			yOffset += 20;
			if (count == 5) {
				yOffset = startY;
				xOffset += 100;
			}
		}
	}
}
