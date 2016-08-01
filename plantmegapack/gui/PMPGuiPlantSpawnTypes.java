package plantmegapack.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSpawnType;

@SideOnly(Side.CLIENT)
public class PMPGuiPlantSpawnTypes
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private PMPDataPlant plantData;
	private PMPGuiListbox spawnTypeList;
	private PMPGuiButton spawnSetting;
	
	public PMPGuiPlantSpawnTypes(GuiScreen parent, PMPDataPlant plantData) {
		super(parent, "gui.spawnTypes", 1);
		this.plantData = plantData;
		this.name = String.format("%s - %s", new Object[] { I18n.translateToLocal("gui.spawnTypes"), this.plantData.attributes.getLocalizedName() });
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		int yPos = 36;
		getClass();getClass();this.spawnTypeList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		xPos = this.width / 2 + 4;
		yPos = 108;
		this.spawnSetting = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.enabled"));
		this.buttonList.add(this.spawnSetting);
		
		populateSpawnTypeList();
		this.spawnTypeList.selectListItem(0);
		listItemSelected(this.spawnTypeList, 0);
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.spawnTypeList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.plantData.resetSpawnTypeSettings();
				break;
			case 2: 
				this.plantData.saveSettings();
				break;
			case 3: 
				this.plantData.setSpawnTypeSetting(this.spawnTypeList.getSelectedItemKey(), this.spawnSetting.getButtonState());
				break;
			}
		}
		super.actionPerformed(button);
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		this.spawnSetting.setButtonState(this.plantData.getSpawnTypeSetting(this.spawnTypeList.getSelectedItemKey()));
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.spawnTypeList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		drawHeadingText();
		int xPos = this.width / 2 + 2;
		int yPos = 40;
		PMPGuiCore.drawPlantTexture(this.plantData.attributes.name(), xPos, yPos, 32);
		drawPlantInfo();
	}
	
	private void drawHeadingText() {
		int xPos = this.width / 2 + 4;
		int yPos = 88;
		drawString(this.fontRendererObj, this.spawnTypeList.getSelectedItemText(), xPos, yPos, 9502624);
	}
	
	private void drawPlantInfo() {
		int xPos = this.width / 2 + 2 + 40;
		int yPos = 40;
		String commonName = this.plantData.attributes.getLocalizedName();
		String latinName = this.plantData.attributes.getLatinName();
		drawString(getFontRenderer(), commonName, xPos, yPos, 5296160);
		if (this.plantData.attributes.category != PMPPlantCategory.grou) {
			commonName = "�o" + latinName + "�r";
			yPos += getFontRenderer().FONT_HEIGHT + 2;
			drawString(getFontRenderer(), commonName, xPos, yPos, 8437888);
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populateSpawnTypeList() {
		for (PMPPlantSpawnType spawnType : PMPPlantSpawnType.values()) {
			this.spawnTypeList.addListboxEntry(spawnType.name(), spawnType.getLocalizedName());
		}
	}
}
