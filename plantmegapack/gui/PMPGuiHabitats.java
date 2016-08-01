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
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.gui.control.IPMPListBoxOwner;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.gui.control.PMPGuiListboxState;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.habitat.PMPHabitatType;
import plantmegapack.worldgen.PMPWorldgenProfile;

@SideOnly(Side.CLIENT)
public class PMPGuiHabitats
	extends PMPGuiBase
	implements IPMPListBoxOwner
{
	private ResourceLocation textureGlobe;
	private ResourceLocation textureReset;
	private PMPDataBiome biomeData;
	private PMPGuiListbox habitatList;
	private static PMPGuiListboxState habitatListState = new PMPGuiListboxState();
	private PMPGuiButton settings;
	private PMPGuiButton plants;
	private PMPGuiButton trees;
	private PMPGuiButton buttonResetAllHabitats;
	private PMPGuiButton buttonResetSelectedHabitat;
	
	public PMPGuiHabitats(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "gui.habitats", 0);
		this.biomeData = biomeData;
		this.name = String.format("%s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("gui.habitats") });
		this.textureGlobe = new ResourceLocation("plantmegapack:textures/gui/iconGlobe.png");
		this.textureReset = new ResourceLocation("plantmegapack:textures/gui/iconReset.png");
	}
	
	public void initGui() {
		super.initGui();
		
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		int yPos = 36;
		getClass();getClass();this.habitatList = new PMPGuiListbox(this, xPos, yPos, 180, 140, getFontRenderer().FONT_HEIGHT + 2);
		
		xPos += 24;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetAllHabitats = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetAllHabitats") + "...");
		this.buttonList.add(this.buttonResetAllHabitats);
		
		xPos = this.width / 2 + 8 + 20;
		yPos = 60;
		this.settings = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.settings") + "...");
		this.buttonList.add(this.settings);
		
		yPos += 24;
		this.plants = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.plants") + "...");
		this.buttonList.add(this.plants);
		
		yPos += 24;
		this.trees = new PMPGuiButton(6, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.trees") + "...");
		this.buttonList.add(this.trees);
		
		xPos = this.width / 2 + 8 + 20;
		getClass();yPos = 36 + '?' + 4;
		this.buttonResetSelectedHabitat = new PMPGuiButton(7, PMPGuiButtonMode.NORMAL, xPos, yPos, 156, 20, I18n.translateToLocal("gui.resetSelectedHabitat") + "...");
		this.buttonList.add(this.buttonResetSelectedHabitat);
		
		populateHabitatList();
		this.habitatList.setListboxState(habitatListState);
		listItemSelected(this.habitatList, habitatListState.selectedElement);
	}
	
	public void handleMouseInput()
		throws IOException
	{
		super.handleMouseInput();
		this.habitatList.handleMouseInput();
	}
	
	protected void actionPerformed(GuiButton button) {
		String habitatName = this.habitatList.getSelectedItemKey();
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				break;
			case 2: 
				PlantMegaPack.worldgenProfile.saveActiveProfile();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 100, "gui.resetAllHabitats", "iconReset"));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiHabitatSettings(this, this.biomeData, this.biomeData.getHabitatDataFromName(habitatName)));
				return;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiDataPlants(this, this.biomeData.biome, PMPHabitat.getHabitatFromName(habitatName), this.biomeData.getHabitatDataFromName(habitatName).getPlantSet(), null));
				return;
			case 6: 
				this.mc.displayGuiScreen(new PMPGuiDataTrees(this, this.biomeData.biome, PMPHabitat.getHabitatFromName(habitatName), this.biomeData.getHabitatDataFromName(habitatName).getTreeSet(), null));
				return;
			case 7: 
				this.mc.displayGuiScreen(new PMPGuiConfirmAction(this, 0, 101, "gui.resetSelectedHabitat", "iconReset"));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void confirmClicked(boolean state, int buttonID) {
		if ((buttonID == 100) && (state)) {
			this.biomeData.applyHabitatDefaultsAll();
		} else if ((buttonID == 101) && (state)) {
			this.biomeData.applyHabitatSettingDefaults(this.habitatList.getSelectedItemKey());
			this.biomeData.applyHabitatPlantDefaults(this.habitatList.getSelectedItemKey());
			this.biomeData.applyHabitatTreeDefaults(this.habitatList.getSelectedItemKey());
		}
	}
	
	public void listItemSelected(PMPGuiListbox list, int index) {
		this.habitatList.saveListboxState(habitatListState);
		boolean enabled = PMPHabitat.canTreeSpawnInHabitat(this.habitatList.getSelectedItemKey());
		this.trees.visible = enabled;
		this.trees.enabled = enabled;
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.habitatList.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
		drawHeadingText();
		drawTextures();
	}
	
	private void drawHeadingText() {
		int xPos = this.width / 2 + 4;
		int yPos = 40;
		drawString(this.fontRendererObj, this.habitatList.getSelectedItemText(), xPos, yPos, 9502624);
	}
	
	private void drawTextures() {
		getClass();int xPos = this.width / 2;// - ('�' + 2);
		getClass();int yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
		
		xPos = this.width / 2 + 4;
		yPos = 60;
		PMPGuiCore.drawTexture(this.textureGlobe, xPos, yPos, 20);
		
		yPos += 24;
		PMPGuiCore.drawPlantTexture("jungleConeHeadedGuzmania", xPos, yPos, 20);
		if (PMPHabitat.canTreeSpawnInHabitat(this.habitatList.getSelectedItemKey())) {
			yPos += 24;
			PMPGuiCore.drawTreeTexture("saplingDarkOakEvergreen", xPos, yPos, 20);
		}
		xPos = this.width / 2 + 4;
		getClass();yPos = 36 + '?' + 4;
		PMPGuiCore.drawTexture(this.textureReset, xPos, yPos, 20);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private void populateHabitatList() {
		for (PMPHabitat habitat : PMPHabitat.values()) {
			boolean addToList = false;
			switch (habitat) {
			case frdp: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case frfl: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case frmr: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case shad: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case slop: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case bedg: 
			case open: 
			case wedg: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case madp: 
			case mamr: 
				addToList = this.biomeData.biome == PMPBiomeType.ocea;
				break;
			case epip: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			case vine: 
				addToList = this.biomeData.biome != PMPBiomeType.ocea;
				break;
			default:
				break;
			}
			if (addToList) {
				this.habitatList.addListboxEntry(habitat.name(), habitat.habitatType.getLocalizedNameFormatted() + ": " + habitat.getLocalizedName());
			}
		}
	}
}
