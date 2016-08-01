package plantmegapack.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiTextField;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiProfileNew
	extends PMPGuiBase
{
	private PMPGuiButton newEmpty;
	private PMPGuiButton newCopyDefault;
	private PMPGuiButton newCopyActive;
	private PMPGuiTextField profileName;
	private int newProfileMode;
	private boolean isFilenameValid;
	
	public PMPGuiProfileNew(GuiScreen parent) {
		super(parent, "gui.createNewProfile", 3);
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 154;
		int yPos = 60;
		this.newEmpty = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.emptyProfile"));
		this.newEmpty.setDisplayOnOff(false);
		this.buttonList.add(this.newEmpty);
		
		yPos += 24;
		this.newCopyDefault = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.copyDefault"));
		this.newCopyDefault.setDisplayOnOff(false);
		this.buttonList.add(this.newCopyDefault);
		
		yPos += 24;
		this.newCopyActive = new PMPGuiButton(5, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.copyActive"));
		this.newCopyActive.setDisplayOnOff(false);
		this.buttonList.add(this.newCopyActive);
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		this.profileName = new PMPGuiTextField(10, this.fontRendererObj, xPos, yPos, 150, 20);
		this.profileName.setMaxStringLength(32);
		this.profileName.setText("NewProfile");
		this.profileName.setStrictEntry(true);
		this.profileName.setTextColor(8437888);
		
		this.newProfileMode = 0;
		this.isFilenameValid = true;
		updateButtons();
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
		throws IOException
	{
		this.profileName.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	protected void keyTyped(char typedChar, int keyCode)
		throws IOException
	{
		if (!this.profileName.textboxKeyTyped(typedChar, keyCode)) {
			super.keyTyped(typedChar, keyCode);
		}
		this.isFilenameValid = (this.profileName.getText().length() > 0);
		updateButtons();
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				createNewProfile();
				break;
			case 3: 
				this.newProfileMode = 0;
				updateButtons();
				return;
			case 4: 
				this.newProfileMode = 1;
				updateButtons();
				return;
			case 5: 
				this.newProfileMode = 2;
				updateButtons();
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		this.profileName.drawTextBox();
		drawHeadingText();
	}
	
	public void updateScreen() {
		this.profileName.updateCursorCounter();
		super.updateScreen();
	}
	
	private void drawHeadingText() {
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.content"), this.width / 2 - 75 - 4, 44, 5296160);
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.name"), this.width / 2 + 75 + 4, 44, 5296160);
		
		int yPos = 156;
		drawCenteredString(this.fontRendererObj, I18n.translateToLocal("gui.filenameInfo"), this.width / 2, yPos, 8437888);
	}
	
	private void updateButtons() {
		this.newEmpty.setButtonState(this.newProfileMode == 0);
		this.newCopyDefault.setButtonState(this.newProfileMode == 1);
		this.newCopyActive.setButtonState(this.newProfileMode == 2);
		this.buttonDone.enabled = this.isFilenameValid;
	}
	
	private void createNewProfile() {
		String newProfileName = this.profileName.getText();
		switch (this.newProfileMode) {
		case 0: 
			PlantMegaPack.worldgenProfile.clearCurrentProfile();
			break;
		case 1: 
			PlantMegaPack.worldgenProfile.applyDefaultProfile();
			break;
		}
		PlantMegaPack.worldgenProfile.setActiveProfileName(newProfileName);
		PlantMegaPack.worldgenProfile.saveActiveProfile();
	}
}
