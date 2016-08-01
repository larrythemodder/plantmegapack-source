package plantmegapack.data;

import java.io.PrintWriter;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataSpawnTree
{
	public PMPSapling sapling;
	public int spawnWeight;
	public int clusterChance;
	public int clusterAmount;
	public int clusterRadius;
	public boolean restrictToBiome;
	
	public PMPDataSpawnTree(PMPSapling sapling) {
		this.sapling = sapling;
		resetSettings();
	}
	
	public void resetSettings() {
		this.spawnWeight = this.sapling.defaultSpawnWeight;
		this.clusterChance = this.sapling.clusterChance;
		this.clusterAmount = this.sapling.clusterAmount;
		this.clusterRadius = this.sapling.clusterRadius;
		this.restrictToBiome = true;
	}
	
	public void loadTreeData(String data) {
		String[] settings = data.split(",");
		this.spawnWeight = Integer.parseInt(settings[0]);
		this.clusterChance = Integer.parseInt(settings[1]);
		this.clusterAmount = Integer.parseInt(settings[2]);
		this.clusterRadius = Integer.parseInt(settings[3]);
		this.restrictToBiome = (Integer.parseInt(settings[4]) == 1);
	}
	
	public void saveHabitatTreeData(PMPBiomeType biome, PMPHabitat habitat, PrintWriter printwriter) {
		printwriter.println(String.format("%s=tree:%s:%s!%d,%d,%d,%d,%d", new Object[] { biome.name(), habitat.name(), this.sapling.name(), 
			Integer.valueOf(this.spawnWeight), Integer.valueOf(this.clusterChance), Integer.valueOf(this.clusterAmount), Integer.valueOf(this.clusterRadius), Integer.valueOf(this.restrictToBiome ? 1 : 0) }));
	}
	
	public void saveFeatureTreeData(PMPBiomeType biome, PMPHabitat habitat, PMPWorldgenFeature feature, PrintWriter printwriter) {
		printwriter.println(String.format("%s=featureTree:%s:%s:%s!%d,%d,%d,%d,%d", new Object[] { biome.name(), feature.name(), habitat.name(), this.sapling.name(), 
			Integer.valueOf(this.spawnWeight), Integer.valueOf(this.clusterChance), Integer.valueOf(this.clusterAmount), Integer.valueOf(this.clusterRadius), Integer.valueOf(this.restrictToBiome ? 1 : 0) }));
	}
}
