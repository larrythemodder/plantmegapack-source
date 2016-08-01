package plantmegapack.data.feature;

import java.io.PrintWriter;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureReef
	extends PMPDataFeature
{
	public int clusterAmount;
	public int clusterSize;
	
	public PMPDataFeatureReef(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 10;
		this.clusterAmount = 20;
		this.clusterSize = 8;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.clusterAmount = Integer.parseInt(data[2]);
		this.clusterSize = Integer.parseInt(data[3]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {}
	
	public void loadTreeDataLine(String habitat, String data) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.clusterAmount), Integer.valueOf(this.clusterSize) }));
	}
	
	public void applyDefaultPlants() {}
	
	public void applyDefaultTrees() {}
}
