package plantmegapack.data.feature;

import java.io.PrintWriter;
import java.util.Random;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureGrou
	extends PMPDataFeature
{
	public int genOpen;
	public int genShade;
	public PMPDataSetPlants open;
	public PMPDataSetPlants shade;
	
	public PMPDataFeatureGrou(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		this.open = new PMPDataSetPlants();
		this.shade = new PMPDataSetPlants();
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 25;
		this.genOpen = 50;
		this.genShade = 50;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.genOpen = Integer.parseInt(data[2]);
		this.genShade = Integer.parseInt(data[3]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {
		if (habitat.matches(PMPHabitat.open.name())) {
			this.open.loadPlantDataLine(data);
		} else if (habitat.matches(PMPHabitat.shad.name())) {
			this.shade.loadPlantDataLine(data);
		}
	}
	
	public void loadTreeDataLine(String habitat, String line) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.genOpen), Integer.valueOf(this.genShade) }));
		this.open.saveFeaturePlantData(this.biome, PMPHabitat.open, this.feature, printwriter);
		this.shade.saveFeaturePlantData(this.biome, PMPHabitat.shad, this.feature, printwriter);
	}
	
	public PMPDataSpawnPlant getRandomPlantSpawnData(PMPHabitat habitat, Random random) {
		switch (habitat) {
		case open: 
			return this.open.getRandomPlant(random);
		case shad: 
			return this.shade.getRandomPlant(random);
		default:
			break;
		}
		return null;
	}
	
	public void applyDefaultPlants() {
		this.open.clearAllPlants();
		this.shade.clearAllPlants();
		switch (this.biome) {
		case beac: 
			break;
		case desc: 
			break;
		case desw: 
			break;
		case forc: 
			this.open.addPlant(PMPPlant.groundcoverClover, 15);
			this.open.addPlant(PMPPlant.groundcoverGrassDark, 85);
			
			this.shade.addPlant(PMPPlant.groundcoverLeavesSpruce, 80);
			this.shade.addPlant(PMPPlant.groundcoverTwig, 20);
			break;
		case forw: 
			this.open.addPlant(PMPPlant.groundcoverClover, 20);
			this.open.addPlant(PMPPlant.groundcoverGrassDark, 50);
			this.open.addPlant(PMPPlant.groundcoverGrassLight, 50);
			
			this.shade.addPlant(PMPPlant.groundcoverLeavesBrown, 30);
			this.shade.addPlant(PMPPlant.groundcoverLeavesGreen, 30);
			this.shade.addPlant(PMPPlant.groundcoverLeavesRed, 30);
			this.shade.addPlant(PMPPlant.groundcoverLeavesYellow, 30);
			this.shade.addPlant(PMPPlant.groundcoverTwig, 50);
			break;
		case jung: 
			break;
		case mesa: 
			break;
		case moun: 
			this.open.addPlant(PMPPlant.groundcoverClover, 20);
			
			this.shade.addPlant(PMPPlant.groundcoverTwig, 50);
			break;
		case mush: 
			break;
		case ocea: 
			break;
		case plai: 
			break;
		case rive: 
			break;
		case sava: 
			break;
		case swam: 
			this.open.addPlant(PMPPlant.groundcoverGrassDark, 50);
			this.open.addPlant(PMPPlant.groundcoverGrassLight, 50);
			
			this.shade.addPlant(PMPPlant.groundcoverGrassDark, 25);
			this.shade.addPlant(PMPPlant.groundcoverGrassLight, 25);
			this.shade.addPlant(PMPPlant.groundcoverTwig, 50);
			break;
		}
	}
	
	public void applyDefaultTrees() {}
}
