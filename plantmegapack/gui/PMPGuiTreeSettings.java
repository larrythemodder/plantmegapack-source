package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.data.PMPDataTree;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.object.PMPSapling;

@SideOnly(Side.CLIENT)
public class PMPGuiTreeSettings
	extends PMPGuiBase
{
	PMPDataTree treeData;
	private PMPGuiButton worldgenEnabled;
	private PMPGuiButton canSpawnOnSand;
	
	public PMPGuiTreeSettings(GuiScreen parent, PMPDataTree treeData) {
		super(parent, "", 1);
		this.treeData = treeData;
		this.name = String.format("%s %s", new Object[] { treeData.sapling.getLocalizedName(), I18n.translateToLocal("gui.settings") });
	}
	
	public void initGui() {
		super.initGui();
		
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		this.worldgenEnabled = new PMPGuiButton(3, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.worldgen"));
		this.buttonList.add(this.worldgenEnabled);
		
		yPos += 24;
		this.canSpawnOnSand = new PMPGuiButton(4, PMPGuiButtonMode.TOGGLE, xPos, yPos, 150, 20, I18n.translateToLocal("gui.spawnOnSand"));
		this.buttonList.add(this.canSpawnOnSand);
		
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				this.treeData.resetAllDefaults();
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				this.treeData.saveSettings();
				break;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		int xPos = this.width / 2 - 16;
		int yPos = 36;
		PMPGuiCore.drawTreeTexture(this.treeData.sapling.name(), xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {
		this.worldgenEnabled.setButtonState(this.treeData.worldgenEnabled);
		this.canSpawnOnSand.setButtonState(this.treeData.canSpawnOnSand);
	}
	
	private void updateSettingsFromButtonStates() {
		this.treeData.worldgenEnabled = this.worldgenEnabled.getButtonState();
		this.treeData.canSpawnOnSand = this.canSpawnOnSand.getButtonState();
	}
}
