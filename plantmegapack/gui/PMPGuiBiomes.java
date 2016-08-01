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
import plantmegapack.data.PMPDataBiome;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiListboxState;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldGenerator;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiBiomes
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private ResourceLocation textureGlobe;
	private ResourceLocation textureFeatures;
	private ResourceLocation textureLevels;
	private ResourceLocation textureReset;
	private PMPDataBiome biomeData;
	private PMPGuiListbox biomeTypeList;
	private static PMPGuiListboxState biomeListState = new PMPGuiListboxState();
	private PMPGuiButton biomeSettings;
	private PMPGuiButton biomeHabitats;
	private PMPGuiButton biomeFeatures;
	private PMPGuiButton buttonResetAllBiomes;
	private PMPGuiButton buttonResetSelectedBiome;
	
	public PMPGuiBiomes(GuiScreen parent) {
		super(parent, "gui.biomes", 0);
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureFeatures = new ResourceLocation("plantmegapack:textures/gui/iconFeatures.png");
		this.textureLevels = new ResourceLocation("plantmegapack:textures/gui/iconLevels.png");
		this.textureReset = new ResourceLocation("plantmegapack:textures/gui/iconReset.png");
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2;// - ('0'/*'Â´'*/ + 2);
		int yPos = 36;
		getClass();getClass();this.biomeTypeList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		xPos += 24;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetAllBiomes = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetAllBiomes") + "...");
		this.buttonList.add(this.buttonResetAllBiomes);
		
		xPos = this.width / 2 + 8 + 20;
		yPos = 60;
		this.biomeSettings = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.settings") + "...");
		this.buttonList.add(this.biomeSettings);
		
		yPos += 24;
		this.biomeHabitats = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.habitats") + "...");
		this.buttonList.add(this.biomeHabitats);
		
		yPos += 24;
		this.biomeFeatures = new PMPGuiButton(6, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.features") + "...");
		this.buttonList.add(this.biomeFeatures);
		
		xPos = this.width / 2 + 8 + 20;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetSelectedBiome = new PMPGuiButton(7, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetSelectedBiome") + "...");
		this.buttonList.add(this.buttonResetSelectedBiome);
		
		populateBiomeTypeList();
		this.biomeTypeList.setListboxState(biomeListState);
		listItemSelected(this.biomeTypeList, biomeListState.selectedElement);
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.biomeTypeList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				biomeListState.resetState();
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.resetAllBiomes", "iconReset"));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiBiomeSettings(this, this.biomeData));
				return;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiHabitats(this, this.biomeData));
				return;
			case 6: 
				this.mc.displayGuiScreen(new PMPGuiBiomeFeatures(this, this.biomeData));
				return;
			case 7: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 101, "gui.resetSelectedBiome", "iconReset"));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if (state) {
			switch (buttonID) {
			case 100: 
				PlantMegaPack.worldGenerator.resetAllBiomeSettings();
				break;
			case 101: 
				this.biomeData.applyHabitatDefaultsAll();
				this.biomeData.applyDefaultSettings();
				break;
			}
		}
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		this.biomeTypeList.saveListboxState(biomeListState);
		this.biomeData = PlantMegaPack.worldgenProfile.getBiomeData(this.biomeTypeList.getSelectedItemKey());
	}
	
	public void listItemDoubleClicked(PMPGuiListbox list, int index) {
		actionPerformed(this.biomeSettings);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.biomeTypeList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		drawHeadingText();
		drawTextures();
	}
	
	private void drawHeadingText() {
		int xPos = this.width / 2 + 4;
		int yPos = 40;
		drawString(this.fontRendererObj, this.biomeTypeList.getSelectedItemText(), xPos, yPos, 9502624);
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		getClass();int yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureLevels, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawTexture(this.textureFeatures, xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		getClass();yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populateBiomeTypeList() {
		for (PMPBiomeType biome : PMPBiomeType.values()) {
			this.biomeTypeList.addListboxEntry(biome.name(), biome.getLocalizedName());
		}
	}
}
