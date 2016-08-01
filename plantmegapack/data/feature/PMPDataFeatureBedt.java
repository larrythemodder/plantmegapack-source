package plantmegapack.data.feature;

import java.io.PrintWriter;
import java.util.Random;
import plantmegapack.data.PMPDataSetTrees;
import plantmegapack.data.PMPDataSpawnTree;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureBedt
	extends PMPDataFeature
{
	public int genDensity;
	public PMPDataSetTrees trees;
	
	public PMPDataFeatureBedt(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		this.trees = new PMPDataSetTrees();
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 25;
		this.genDensity = (this.biome == PMPBiomeType.moun ? 10 : 16);
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.genDensity = Integer.parseInt(data[2]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {}
	
	public void loadTreeDataLine(String habitat, String data) {
		this.trees.loadTreeDataLine(data);
	}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.genDensity) }));
		this.trees.saveFeatureTreeData(this.biome, PMPHabitat.bedg, this.feature, printwriter);
	}
	
	public PMPDataSpawnTree getRandomTreeSpawnData(Random random) {
		return this.trees.getRandomTree(random);
	}
	
	public void applyDefaultPlants() {}
	
	public void applyDefaultTrees() {
		this.trees.clearAllTrees();
		switch (this.biome) {
		case beac: 
			this.trees.addTree(PMPSapling.saplingJungleBombona, 50);
			break;
		case desc: 
			break;
		case desw: 
			break;
		case forc: 
			break;
		case forw: 
			break;
		case jung: 
			this.trees.addTree(PMPSapling.saplingJungleBombona, 50);
			break;
		case mesa: 
			break;
		case moun: 
			this.trees.addTree(PMPSapling.saplingBirchWhite, 50);
			break;
		case mush: 
			break;
		case ocea: 
			break;
		case plai: 
			this.trees.addTree(PMPSapling.saplingBirchChineseDwarf, 50);
			break;
		case rive: 
			this.trees.addTree(PMPSapling.saplingBirchWater, 50);
			break;
		case sava: 
			break;
		case swam: 
			break;
		}
	}
}
