package plantmegapack.data.feature;

import java.io.PrintWriter;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenFeature;

public abstract class PMPDataFeature
{
	public PMPWorldgenFeature feature;
	public PMPBiomeType biome;
	public boolean enabled;
	public int genChance;
	
	public PMPDataFeature(PMPWorldgenFeature feature, PMPBiomeType biome) {
		this.feature = feature;
		this.biome = biome;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("feature." + this.feature.name());
	}
	
	public abstract void resetDefaults();
	
	public abstract void applyDefaultPlants();
	
	public abstract void applyDefaultTrees();
	
	public abstract void loadSettingsDataLine(String paramString);
	
	public abstract void loadPlantDataLine(String paramString1, String paramString2);
	
	public abstract void loadTreeDataLine(String paramString1, String paramString2);
	
	public abstract void saveSettingsData(PrintWriter paramPrintWriter);
}
