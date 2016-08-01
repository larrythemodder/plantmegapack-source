package plantmegapack.gui.book;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookEntry;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiListboxState;

public class PMPGuiBookIndex
	extends PMPGuiBookTab
	implements IPMPListBoxOwner
{
	private PMPGuiListbox plantList;
	private static PMPGuiListboxState plantListState = new PMPGuiListboxState();
	private final int PLANT_LIST_HEIGHT = 146;
	
	public PMPGuiBookIndex(PMPGuiBook parent) {
		super(parent, 1);
	}
	
	public void initGui() {
		this.parent.getClass();int startX = (this.parent.width - 256) / 2;
		int startY = 4;
		this.plantList = new PMPGuiListbox(this, startX + 22, startY + 15, 206, 130, this.parent.getFontRenderer().FONT_HEIGHT + 2);
		this.plantList.setListboxState(plantListState);
		this.plantList.setCustomDrawMode(true);
		PlantMegaPack.bookRegistry.populateGuiList(this.plantList);
		if (this.parent.getSelectedEntry() == null) {
			this.plantList.setEnabled(false);
		} else {
			this.plantList.scrollToAndSelectEntry(this.plantList.getSlotFromKey(this.parent.getSelectedEntry().getGuiKey()));
		}
	}
	
	public void updateGui() {
		this.plantList.visible = true;
	}
	
	public void handleMouseInput() {
		this.plantList.handleMouseInput();
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.plantList.drawScreen(mouseX, mouseY, partialTicks);
		String text1 = I18n.translateToLocal("gui.plantGuide1");
		String text2 = I18n.translateToLocal("gui.plantGuide2");
		String plantsCollected = String.format("%d %s %d %s", new Object[] { Integer.valueOf(PlantMegaPack.bookRegistry.getPlantsCollected()), text1, Integer.valueOf(PlantMegaPack.bookRegistry.getPlantsTotal()), text2 });
		int textWidth = this.parent.getFontRenderer().getStringWidth(plantsCollected);
		this.parent.getClass();this.parent.getClass();this.parent.getClass();this.parent.getFontRenderer().drawString(plantsCollected, (this.parent.width - 256) / 2 + (256 - textWidth) / 2, 4 + /*'°'*/'0' - 22, 0);
	}
	
	public int getHeight() {
		getClass();return 146;
	}
	
	public void listItemSelected(PMPGuiListbox list, int slotIndex) {
		this.parent.setSelectedEntry(PlantMegaPack.bookRegistry.getEntryFromGuiKey(list.getSelectedItemKey()));
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int slotIndex) {
		this.plantList.visible = false;
		this.parent.switchToEntryTab();
	}
}
