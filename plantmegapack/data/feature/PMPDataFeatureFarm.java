package plantmegapack.data.feature;

import java.io.PrintWriter;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureFarm
	extends PMPDataFeature
{
	public int cropRate;
	public int fenceRate;
	
	public PMPDataFeatureFarm(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = (this.biome == PMPBiomeType.plai);
		this.genChance = (this.biome == PMPBiomeType.plai ? 1 : 0);
		this.cropRate = 75;
		this.fenceRate = 75;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.cropRate = Integer.parseInt(data[2]);
		this.fenceRate = Integer.parseInt(data[3]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {}
	
	public void loadTreeDataLine(String habitat, String data) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.cropRate), Integer.valueOf(this.fenceRate) }));
	}
	
	public void applyDefaultPlants() {}
	
	public void applyDefaultTrees() {}
}
