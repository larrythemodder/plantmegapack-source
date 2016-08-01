package plantmegapack.data.feature;

import java.io.PrintWriter;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureKelp
	extends PMPDataFeature
{
	public int limitHigh;
	public int limitLow;
	
	public PMPDataFeatureKelp(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 25;
		this.limitHigh = 61;
		this.limitLow = 58;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.limitHigh = Integer.parseInt(data[2]);
		this.limitLow = Integer.parseInt(data[3]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {}
	
	public void loadTreeDataLine(String habitat, String data) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.limitHigh), Integer.valueOf(this.limitLow) }));
	}
	
	public void applyDefaultPlants() {}
	
	public void applyDefaultTrees() {}
}
