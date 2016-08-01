package plantmegapack.data;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import plantmegapack.core.PMPCompareDataPlant;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataSetPlants
{
	public TreeMap<String, PMPDataSpawnPlant> plants = new TreeMap(new PMPCompareDataPlant());
	
	public void clearAllPlants() {
		this.plants.clear();
	}
	
	public PMPDataSpawnPlant getPlantSpawnData(String plantName) {
		return (PMPDataSpawnPlant)this.plants.get(plantName);
	}
	
	public PMPDataSpawnPlant addPlant(PMPPlant plant, int spawnWeight) {
		if (this.plants.containsKey(plant.name())) {
			return null;
		}
		PMPPlant plantAttributes = PMPPlant.getPlantFromName(plant.name());
		if (plantAttributes != null) {
			PMPDataSpawnPlant spawnData = new PMPDataSpawnPlant(plantAttributes);
			if (spawnData != null) {
				spawnData.spawnWeight = spawnWeight;
				this.plants.put(plant.name(), spawnData);
				return spawnData;
			}
		}
		return null;
	}
	
	public PMPDataSpawnPlant addPlant(String plantName) {
		if (this.plants.containsKey(plantName)) {
			return null;
		}
		PMPPlant plantAttributes = PMPPlant.getPlantFromName(plantName);
		if (plantAttributes != null) {
			PMPDataSpawnPlant spawnData = new PMPDataSpawnPlant(plantAttributes);
			if (spawnData != null) {
				this.plants.put(plantName, spawnData);
				return spawnData;
			}
		}
		return null;
	}
	
	public void removePlant(String plantName) {
		this.plants.remove(plantName);
	}
	
	public int getPlantCount() {
		return this.plants.size();
	}
	
	public PMPDataSpawnPlant getRandomPlant(Random random) {
		if (this.plants.size() == 0) {
			return null;
		}
		HashSet<PMPDataSpawnPlant> hs = new HashSet();
		
		int totalWeight = 0;
		for (Entry<String, PMPDataSpawnPlant> entry : this.plants.entrySet()) {
			int spawnWeight = ((PMPDataSpawnPlant)entry.getValue()).spawnWeight;
			if (spawnWeight > 0) {
				totalWeight += spawnWeight;
				hs.add(entry.getValue());
			}
		}
		if (totalWeight < 1) {
			return null;
		}
		int randomSelection = random.nextInt(totalWeight);
		int spawnWeight = 0;
		Iterator<PMPDataSpawnPlant> iterator = hs.iterator();
		while (iterator.hasNext()) {
			PMPDataSpawnPlant spawnData = (PMPDataSpawnPlant)iterator.next();
			spawnWeight += spawnData.spawnWeight;
			if (spawnWeight >= randomSelection) {
				return spawnData;
			}
		}
		return null;
	}
	
	public boolean loadPlantDataLine(String line) {
		String[] data = line.split("!");
		PMPDataSpawnPlant spawnData = (PMPDataSpawnPlant)this.plants.get(data[0]);
		if (spawnData == null) {
			spawnData = addPlant(data[0]);
			if (spawnData == null) {
				return false;
			}
		}
		spawnData.loadPlantData(data[1]);
		return true;
	}
	
	public void saveHabitatPlantData(PMPBiomeType biome, PMPHabitat habitat, PrintWriter printwriter) {
		for (Entry<String, PMPDataSpawnPlant> entry : this.plants.entrySet()) {
			((PMPDataSpawnPlant)entry.getValue()).saveHabitatPlantData(biome, habitat, printwriter);
		}
	}
	
	public void saveFeaturePlantData(PMPBiomeType biome, PMPHabitat habitat, PMPWorldgenFeature feature, PrintWriter printwriter) {
		for (Entry<String, PMPDataSpawnPlant> entry : this.plants.entrySet()) {
			((PMPDataSpawnPlant)entry.getValue()).saveFeaturePlantData(biome, habitat, feature, printwriter);
		}
	}
	
	public void populatePlantGuiList(PMPGuiListbox list) {
		for (Entry<String, PMPDataSpawnPlant> entry : this.plants.entrySet()) {
			PMPPlant plantData = ((PMPDataSpawnPlant)entry.getValue()).plantAttributes;
			String label = String.format("%s: %s", new Object[] { plantData.category.getLocalizedNameFormatted(), plantData.getLocalizedName() });
			list.addListboxEntry((String)entry.getKey(), label);
		}
	}
}
