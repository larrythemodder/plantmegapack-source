package plantmegapack.data;

import java.io.PrintWriter;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataSpawnPlant
{
	public PMPPlant plantAttributes;
	public int spawnWeight;
	public int elevationVariance;
	
	public PMPDataSpawnPlant(PMPPlant plantAttributes) {
		this.plantAttributes = plantAttributes;
		resetSettings();
	}
	
	public void resetSettings() {
		this.spawnWeight = this.plantAttributes.defaultSpawnWeight;
		this.elevationVariance = Math.max(0, this.plantAttributes.elevationVariance);
	}
	
	public void loadPlantData(String data) {
		String[] settings = data.split(",");
		this.spawnWeight = Integer.parseInt(settings[0]);
		this.elevationVariance = Integer.parseInt(settings[1]);
	}
	
	public void saveHabitatPlantData(PMPBiomeType biome, PMPHabitat habitat, PrintWriter printwriter) {
		printwriter.println(String.format("%s=plant:%s:%s!%d,%d", new Object[] { biome.name(), habitat.name(), this.plantAttributes.name(), Integer.valueOf(this.spawnWeight), Integer.valueOf(this.elevationVariance) }));
	}
	
	public void saveFeaturePlantData(PMPBiomeType biome, PMPHabitat habitat, PMPWorldgenFeature feature, PrintWriter printwriter) {
		printwriter.println(String.format("%s=featurePlant:%s:%s:%s!%d,%d", new Object[] { biome.name(), feature.name(), habitat.name(), this.plantAttributes.name(), Integer.valueOf(this.spawnWeight), Integer.valueOf(this.elevationVariance) }));
	}
}
