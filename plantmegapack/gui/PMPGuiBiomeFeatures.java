package plantmegapack.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.gui.control.PMPGuiButton;
import plantmegapack.gui.control.PMPGuiButtonMode;
import plantmegapack.gui.core.PMPGuiCore;
import plantmegapack.gui.feature.PMPGuiFeatureBedp;
import plantmegapack.gui.feature.PMPGuiFeatureBedt;
import plantmegapack.gui.feature.PMPGuiFeatureFarm;
import plantmegapack.gui.feature.PMPGuiFeatureGrou;
import plantmegapack.gui.feature.PMPGuiFeatureKelp;
import plantmegapack.gui.feature.PMPGuiFeatureReef;
import plantmegapack.gui.feature.PMPGuiFeatureWedg;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenFeature;

@SideOnly(Side.CLIENT)
public class PMPGuiBiomeFeatures
	extends PMPGuiBase
{
	private ResourceLocation textureFeatures;
	private PMPDataBiome biomeData;
	public PMPGuiButton biomeEdgePlants;
	public PMPGuiButton biomeEdgeThicket;
	public PMPGuiButton abandonedFarm;
	public PMPGuiButton coralReefs;
	public PMPGuiButton groundcover;
	public PMPGuiButton marineKelp;
	public PMPGuiButton waterEdge;
	
	public PMPGuiBiomeFeatures(GuiScreen parent, PMPDataBiome biomeData) {
		super(parent, "", 0);
		this.biomeData = biomeData;
		this.name = String.format("%s %s", new Object[] { this.biomeData.biome.getLocalizedName(), I18n.translateToLocal("gui.features") });
		this.textureFeatures = new ResourceLocation("plantmegapack:textures/gui/iconFeatures.png");
	}
	
	public void initGui() {
		super.initGui();
		int xPos = this.width / 2 - 75;
		int yPos = 60;
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.bedp)) {
			this.biomeEdgePlants = new PMPGuiButton(3, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.bedp") + "...");
			this.buttonList.add(this.biomeEdgePlants);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.bedt)) {
			this.biomeEdgeThicket = new PMPGuiButton(4, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.bedt") + "...");
			this.buttonList.add(this.biomeEdgeThicket);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.farm)) {
			this.abandonedFarm = new PMPGuiButton(5, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.farm") + "...");
			this.buttonList.add(this.abandonedFarm);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.grou)) {
			this.groundcover = new PMPGuiButton(6, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.grou") + "...");
			this.buttonList.add(this.groundcover);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.kelp)) {
			this.marineKelp = new PMPGuiButton(7, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.kelp") + "...");
			this.buttonList.add(this.marineKelp);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.reef)) {
			this.coralReefs = new PMPGuiButton(8, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.reef") + "...");
			this.buttonList.add(this.coralReefs);
			yPos += 24;
		}
		if (this.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.wedg)) {
			this.waterEdge = new PMPGuiButton(9, PMPGuiButtonMode.NORMAL, xPos, yPos, 150, 20, I18n.translateToLocal("feature.wedg") + "...");
			this.buttonList.add(this.waterEdge);
			yPos += 24;
		}
		updateButtonStatesFromSettings();
	}
	
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
			case 1: 
				updateButtonStatesFromSettings();
				break;
			case 2: 
				updateSettingsFromButtonStates();
				break;
			case 3: 
				this.mc.displayGuiScreen(new PMPGuiFeatureBedp(this, this.biomeData));
				return;
			case 4: 
				this.mc.displayGuiScreen(new PMPGuiFeatureBedt(this, this.biomeData));
				return;
			case 5: 
				this.mc.displayGuiScreen(new PMPGuiFeatureFarm(this, this.biomeData));
				return;
			case 6: 
				this.mc.displayGuiScreen(new PMPGuiFeatureGrou(this, this.biomeData));
				return;
			case 7: 
				this.mc.displayGuiScreen(new PMPGuiFeatureKelp(this, this.biomeData));
				return;
			case 8: 
				this.mc.displayGuiScreen(new PMPGuiFeatureReef(this, this.biomeData));
				return;
			case 9: 
				this.mc.displayGuiScreen(new PMPGuiFeatureWedg(this, this.biomeData));
				return;
			}
		}
		super.actionPerformed(button);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawHeadingText();
		drawTextures();
	}
	
	private void drawHeadingText() {}
	
	private void drawTextures() {
		int xPos = this.width / 2 - 10;
		int yPos = 36;
		PMPGuiCore.drawTexture(this.textureFeatures, xPos, yPos, 20);
	}
	
	private void updateButtonStatesFromSettings() {}
	
	private void updateSettingsFromButtonStates() {}
}
