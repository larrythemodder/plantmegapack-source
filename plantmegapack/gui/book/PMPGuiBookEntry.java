package plantmegapack.gui.book;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.book.PMPBookEntry;
import plantmegapack.book.PMPBookMapOverlay;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.plant.PMPPlantConStat;

public class PMPGuiBookEntry
	extends PMPGuiBookTab
{
	private ResourceLocation bookMapOverlay0;
	private ResourceLocation bookMapOverlay1;
	private ResourceLocation bookMapOverlay2;
	private ResourceLocation bookMapOverlay8;
	private ResourceLocation bookMapOverlay9;
	
	public PMPGuiBookEntry(PMPGuiBook parent) {
		super(parent, 4);
		String path = "plantmegapack:textures/gui/bookMapOverlay%d.png";
		this.bookMapOverlay0 = new ResourceLocation(String.format(path, new Object[] { Integer.valueOf(0) }));
		this.bookMapOverlay1 = new ResourceLocation(String.format(path, new Object[] { Integer.valueOf(1) }));
		this.bookMapOverlay2 = new ResourceLocation(String.format(path, new Object[] { Integer.valueOf(2) }));
		this.bookMapOverlay8 = new ResourceLocation(String.format(path, new Object[] { Integer.valueOf(8) }));
		this.bookMapOverlay9 = new ResourceLocation(String.format(path, new Object[] { Integer.valueOf(9) }));
	}
	
	public void initGui() {}
	
	public void updateGui() {}
	
	public void handleMouseInput() {}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (this.parent.getSelectedEntry() == null) {
			return;
		}
		this.parent.getClass();int startX = (this.parent.width - 256) / 2;
		int startY = 4;
		int lineHeight = this.parent.getFontRenderer().FONT_HEIGHT + 2;
		int curLine = startY + 16;
		int color = 5296160;
		drawBorderedText(this.parent.getSelectedEntry().commonName, startX + 55, curLine, 0, color);
		PMPGuiCore.drawBookEntryTexture(this.parent.getSelectedEntry(), startX + 20, startY + 11, 32);
		curLine += lineHeight + 1;
		if (this.page == 1) {
			this.parent.getFontRenderer().drawString(this.parent.getSelectedEntry().latinName, startX + 54, curLine, 0);
			curLine += lineHeight * 2;
			this.parent.getFontRenderer().drawSplitString(this.parent.getSelectedEntry().description, startX + 22, curLine, 206, 0);
		} else if (this.page == 2) {
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.itemsProduced"), startX + 54, curLine, 0);
			curLine += lineHeight + 7;
			drawCraftableItems(startX + 26, curLine);
		} else if (this.page == 3) {
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.nativeHabitat"), startX + 54, curLine, 0);
			drawEarthMap(startX + 68, startY + 50);
		} else if (this.page == 4) {
			this.parent.getFontRenderer().drawString(I18n.translateToLocal("gui.conservation"), startX + 54, curLine, 0);
			drawConservationStatus(startX, startY);
		}
	}
	
	private void drawEarthMap(int startX, int startY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.parent.bindBookElementsTexture();
		this.parent.drawTexturedModalRect(startX, startY, 1, 1, 122, 62);
		
		PMPBookEntry selectedEntry = this.parent.getSelectedEntry();
		String areaName;
		if ((selectedEntry != null) && (selectedEntry.mapID >= 0)) {
			PMPBookMapOverlay overlay = PMPBookMapOverlay.getSectionByID(this.parent.getSelectedEntry().mapID);
			areaName = overlay.getLocalizedName();
			if (overlay != null) {
				if (selectedEntry.mapID < 100) {
					this.parent.mc.getTextureManager().bindTexture(this.bookMapOverlay0);
				} else if ((selectedEntry.mapID >= 100) && (selectedEntry.mapID < 200)) {
					this.parent.mc.getTextureManager().bindTexture(this.bookMapOverlay1);
				} else if ((selectedEntry.mapID >= 200) && (selectedEntry.mapID < 300)) {
					this.parent.mc.getTextureManager().bindTexture(this.bookMapOverlay2);
				} else if ((selectedEntry.mapID >= 800) && (selectedEntry.mapID < 900)) {
					this.parent.mc.getTextureManager().bindTexture(this.bookMapOverlay8);
				} else if ((selectedEntry.mapID >= 900) && (selectedEntry.mapID < 1000)) {
					this.parent.mc.getTextureManager().bindTexture(this.bookMapOverlay9);
				}
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				this.parent.drawTexturedModalRect(startX + overlay.targetX, startY + overlay.targetY, overlay.sourceX, overlay.sourceY, overlay.width, overlay.height);
			}
		} else {
			areaName = I18n.translateToLocal("gui.unknown");
		}
		int textWidth = this.parent.getFontRenderer().getStringWidth(areaName);
		if (textWidth < 168) {
			this.parent.getClass();this.parent.getClass();this.parent.getClass();this.parent.getFontRenderer().drawString(areaName, (this.parent.width - 256) / 2 + (256 - textWidth) / 2, 4 + /*'°'*/'0' - 50, 0);
		} else {
			this.parent.getClass();this.parent.getClass();this.parent.getFontRenderer().drawSplitString(areaName, (this.parent.width - 256) / 2 + 22, 4 + /*'°'*/'0' - 50, 168, 0);
		}
	}
	
	private void drawCraftableItems(int startX, int startY) {
		int count = this.parent.getSelectedEntry().items.size();
		if (count == 0) {
			return;
		}
		int curLine = startY;
		int lineHeight = 17;
		for (int i = 0; i < count; i++) {
			Item object = ((ItemStack)this.parent.getSelectedEntry().items.get(i)).getItem();
			String objectName = String.valueOf(Item.REGISTRY.getNameForObject(object));
			PMPGuiCore.drawItemObjectTexture(objectName, object, ((ItemStack)this.parent.getSelectedEntry().items.get(i)).getItemDamage(), startX + 8, curLine, 16);
			this.parent.getFontRenderer().drawString(((ItemStack)this.parent.getSelectedEntry().items.get(i)).getDisplayName(), startX + 28, curLine + 5, 0);
			curLine += lineHeight;
		}
	}
	
	private void drawConservationStatus(int startX, int startY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.parent.bindBookElementsTexture();
		this.parent.drawTexturedModalRect(startX + 56, startY + 51, 1, 64, 143, 9);
		PMPBookEntry selectedEntry = this.parent.getSelectedEntry();
		PMPPlantConStat conStat = PMPPlantConStat.getStatusByID(selectedEntry.conStat);
		if (conStat != null) {
			if ((conStat.getID() >= 0) && (conStat.getID() < PMPPlantConStat.values().length)) {
				this.parent.drawTexturedModalRect(startX + 56 + selectedEntry.conStat * 16, startY + 51, 1 + selectedEntry.conStat * 16, 74, 15, 9);
			}
			String name = conStat.getLocalizedName();
			int textWidth = this.parent.getFontRenderer().getStringWidth(name);
			this.parent.getClass();this.parent.getClass();this.parent.getFontRenderer().drawString(name, (this.parent.width - 256) / 2 + (256 - textWidth) / 2, 70, 0);
			
			this.parent.getFontRenderer().drawSplitString(conStat.getLocalizedDescription(), startX + 22, 86, 206, 0);
		}
	}
}
