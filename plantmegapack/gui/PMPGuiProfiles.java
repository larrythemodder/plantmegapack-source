package plantmegapack.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiProfiles
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private final int LIST_WIDTH = 220;
	private ResourceLocation textureActive;
	private ResourceLocation textureStartup;
	private ResourceLocation textureNew;
	private ResourceLocation textureDelete;
	private PMPGuiListbox profileList;
	private PMPGuiButton setAsActive;
	private PMPGuiButton setAsStartup;
	private PMPGuiButton profileNew;
	private PMPGuiButton profileDelete;
	
	public PMPGuiProfiles(GuiScreen parent) {
		super(parent, "gui.worldgenProfiles", 0);
		this.textureActive = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureStartup = new ResourceLocation("plantmegapack:textures/gui/iconOk.png");
		this.textureNew = new ResourceLocation("plantmegapack:textures/gui/iconAdd.png");
		this.textureDelete = new ResourceLocation("plantmegapack:textures/gui/iconDelete.png");
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 184;
		int yPos = 36;
		getClass();getClass();this.profileList = new PMPGuiListbox(this, xPos, yPos, 220, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		getClass();xPos += /*'Ü'*/'0' + 12 + 20;
		this.setAsActive = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 122, 20, I18n.translateToLocal("gui.setActive"));
		this.buttonList.add(this.setAsActive);
		
		yPos += 24;
		this.setAsStartup = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 122, 20, I18n.translateToLocal("gui.setStartup"));
		this.buttonList.add(this.setAsStartup);
		
		yPos += 48;
		this.profileNew = new PMPGuiButton(10, PMPGuiButtonMode.NORMAL, xPos, yPos, 122, 20, I18n.translateToLocal("gui.createNew") + "...");
		this.buttonList.add(this.profileNew);
		
		yPos += 24;
		this.profileDelete = new PMPGuiButton(11, PMPGuiButtonMode.NORMAL, xPos, yPos, 122, 20, I18n.translateToLocal("gui.delete") + "...");
		this.buttonList.add(this.profileDelete);
		
		populateProfileList();
		this.profileList.selectListItem(0);
		updateButtons();
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.profileList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		int selectedIndex = this.profileList.getSelectedItemIndex();
		if (button.enabled) {
			switch (button.id) {
			case 2: 
				break;
			case 3: 
				PlantMegaPack.worldgenProfile.loadProfile(this.profileList.getSelectedItemKey(), false);
				populateProfileList();
				this.profileList.selectListItem(selectedIndex);
				updateButtons();
				break;
			case 4: 
				PlantMegaPack.settings.startupProfile = this.profileList.getSelectedItemKey();
				PlantMegaPack.settings.saveOptions();
				populateProfileList();
				this.profileList.selectListItem(selectedIndex);
				updateButtons();
				break;
			case 10: 
				this.mc.displayGuiScreen(new PMPGuiProfileNew(this));
				return;
			case 11: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.deleteProfile", "iconDelete"));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if (state) {
			switch (buttonID) {
			case 100: 
				deleteSelectedProfile();
				break;
			}
		}
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		updateButtons();
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.profileList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		drawTextures();
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2 - 180 + 220 + 4;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureActive, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureStartup, xPos, yPos, 20);
		
		yPos += 48;
		PMPGuiCore.drawTexture(this.textureNew, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureDelete, xPos, yPos, 20);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populateProfileList() {
		this.profileList.clearAllEntries();
		PlantMegaPack.worldgenProfile.populateGuiListWithProfiles(this.profileList);
	}
	
	private void updateButtons() {
		if (this.profileList.getSize() == 0) {
			this.setAsActive.enabled = false;
			this.setAsStartup.enabled = false;
			this.profileNew.enabled = true;
			this.profileDelete.enabled = false;
			return;
		}
		String profileName = this.profileList.getSelectedItemKey();
		boolean enabled = (profileName != null) && (profileName.length() > 0);
		this.setAsActive.enabled = ((enabled) && (!profileName.matches(PlantMegaPack.worldgenProfile.getActiveProfile())));
		this.setAsStartup.enabled = ((enabled) && (!profileName.matches(PlantMegaPack.settings.startupProfile)));
		this.profileNew.enabled = true;
		this.profileDelete.enabled = ((enabled) && (!profileName.matches("Default")));
	}
	
	private void deleteSelectedProfile() {
		PlantMegaPack.worldgenProfile.deleteProfile(this.profileList.getSelectedItemKey());
		populateProfileList();
		this.profileList.selectListItem(0);
		updateButtons();
	}
}
