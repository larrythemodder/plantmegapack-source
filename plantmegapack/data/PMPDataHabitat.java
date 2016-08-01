package plantmegapack.data;

import java.io.PrintWriter;
import java.util.Random;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.plant.PMPPlant;

public class PMPDataHabitat
{
	public PMPBiomeType biome;
	public PMPHabitat habitat;
	public int genRatePlant;
	public int genRateTree;
	private PMPDataSetPlants plants;
	private PMPDataSetTrees trees;
	
	public PMPDataHabitat(PMPBiomeType biome, PMPHabitat habitat) {
		this.biome = biome;
		this.habitat = habitat;
		this.plants = new PMPDataSetPlants();
		this.trees = new PMPDataSetTrees();
		applyDefaultSettings();
	}
	
	public void applyDefaultsAll() {
		applyDefaultSettings();
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void applyDefaultSettings() {
		this.genRatePlant = this.habitat.defaultPlantRate;
		this.genRateTree = this.habitat.defaultTreeRate;
		if (this.biome == PMPBiomeType.plai) {
			this.genRateTree = (this.habitat == PMPHabitat.bedg ? 75 : 0);
		} else if (this.biome == PMPBiomeType.moun) {
			this.genRateTree = ((this.habitat == PMPHabitat.bedg) || (this.habitat == PMPHabitat.slop) ? 75 : 50);
		} else if (this.biome == PMPBiomeType.rive) {
			this.genRateTree = ((this.habitat == PMPHabitat.open) || (this.habitat == PMPHabitat.slop) || (this.habitat == PMPHabitat.wedg) ? 75 : 0);
		} else if (this.biome == PMPBiomeType.sava) {
			this.genRateTree = (this.habitat == PMPHabitat.wedg ? 75 : 20);
		}
	}
	
	public void applyDefaultPlants() {
		this.plants.clearAllPlants();
		for (PMPPlant plant : PMPPlant.values()) {
			if (plant.canSpawnInHabitat(this.biome, this.habitat)) {
				this.plants.addPlant(plant.name());
			}
		}
	}
	
	public void applyDefaultTrees() {
		this.trees.clearAllTrees();
		for (PMPSapling sapling : PMPSapling.values()) {
			if ((sapling.canSpawnInHabitat(this.biome, this.habitat)) && (canAddDefaultTree(sapling))) {
				this.trees.addTree(sapling.name());
			}
		}
	}
	
	public void clearAll() {
		removeAllPlants();
		removeAllTrees();
		applyDefaultSettings();
	}
	
	public PMPDataSetPlants getPlantSet() {
		return this.plants;
	}
	
	public PMPDataSetTrees getTreeSet() {
		return this.trees;
	}
	
	private boolean canAddDefaultTree(PMPSapling sapling) {
		if (this.biome == PMPBiomeType.plai) {
			return this.habitat == PMPHabitat.bedg;
		}
		return true;
	}
	
	public void removeAllPlants() {
		this.plants.clearAllPlants();
	}
	
	public void removeAllTrees() {
		this.trees.clearAllTrees();
	}
	
	public PMPDataSpawnPlant getPlantSpawnData(String plantName) {
		return this.plants.getPlantSpawnData(plantName);
	}
	
	public PMPDataSpawnTree getTreeSpawnData(String treeName) {
		return this.trees.getTreeSpawnData(treeName);
	}
	
	public PMPDataSpawnPlant addPlant(String plantName) {
		return this.plants.addPlant(plantName);
	}
	
	public void removePlant(String plantName) {
		this.plants.removePlant(plantName);
	}
	
	public PMPDataSpawnTree addTree(String treeName) {
		return this.trees.addTree(treeName);
	}
	
	public void removeTree(String treeName) {
		this.trees.removeTree(treeName);
	}
	
	public PMPDataSpawnPlant getRandomPlant(Random random) {
		return this.plants.getRandomPlant(random);
	}
	
	public PMPDataSpawnTree getRandomTree(Random random) {
		return this.trees.getRandomTree(random);
	}
	
	public long getTotalHabitatSpawnCount() {
		return this.plants.getPlantCount() + this.trees.getTreeCount();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.genRatePlant = Integer.parseInt(data[0]);
		this.genRateTree = Integer.parseInt(data[1]);
	}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=data:%s:%d,%d", new Object[] { this.biome.name(), this.habitat.name(), Integer.valueOf(this.genRatePlant), Integer.valueOf(this.genRateTree) }));
	}
	
	public boolean loadPlantDataLine(String line) {
		return this.plants.loadPlantDataLine(line);
	}
	
	public void savePlantData(PrintWriter printwriter) {
		this.plants.saveHabitatPlantData(this.biome, this.habitat, printwriter);
	}
	
	public boolean loadTreeDataLine(String line) {
		return this.trees.loadTreeDataLine(line);
	}
	
	public void saveTreeData(PrintWriter printwriter) {
		this.trees.saveHabitatTreeData(this.biome, this.habitat, printwriter);
	}
	
	public void populatePlantGuiList(PMPGuiListbox list) {
		this.plants.populatePlantGuiList(list);
	}
	
	public void populateTreeGuiList(PMPGuiListbox list) {
		this.trees.populateTreeGuiList(list);
	}
}
