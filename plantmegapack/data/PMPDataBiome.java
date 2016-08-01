package plantmegapack.data;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataBiome
{
	public PMPBiomeType biome;
	private Map<String, PMPDataHabitat> habitats = new HashMap(PMPHabitat.values().length);
	private Map<String, PMPDataFeature> features = new HashMap();
	public int plantEmptyChunks;
	public boolean plantEnabled;
	public int plantPassesPerChunk;
	public int treeEmptyChunks;
	public boolean treeEnabled;
	public int treePassesPerChunk;
	
	public PMPDataBiome(PMPBiomeType biome) {
		this.biome = biome;
		applyDefaultSettings();
		initHabitatMap();
		initFeaturesMap();
	}
	
	private void initHabitatMap() {
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = new PMPDataHabitat(this.biome, habitat);
			if (habitatData != null) {
				habitatData.applyDefaultSettings();
				this.habitats.put(habitat.name(), habitatData);
			}
		}
	}
	
	private void initFeaturesMap() {
		for (PMPWorldgenFeature features : PMPWorldgenFeature.values()) {
			if (features.isFeatureInBiome(this.biome)) {
				try
				{
					Constructor<? extends PMPDataFeature> bCon = features.dataClass.getConstructor(new Class[] { PMPWorldgenFeature.class, PMPBiomeType.class });
					PMPDataFeature featureObject = (PMPDataFeature)bCon.newInstance(new Object[] { features, this.biome });
					if (featureObject != null) {
						this.features.put(features.name(), featureObject);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void applyDefaultSettings() {
		this.plantEmptyChunks = Math.max(0, this.biome.plantEmptyChunks);
		this.plantEnabled = (this.biome.plantPassesPerChunk > 0);
		this.plantPassesPerChunk = Math.max(0, this.biome.plantPassesPerChunk);
		this.treeEmptyChunks = Math.max(0, this.biome.treeEmptyChunks);
		this.treeEnabled = (this.biome.treePassesPerChunk > 0);
		this.treePassesPerChunk = Math.max(0, this.biome.treePassesPerChunk);
	}
	
	public void applyHabitatDefaultsAll() {
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				habitatData.applyDefaultsAll();
			}
		}
	}
	
	public void applyFeatureDefaultsAll() {
		for (PMPWorldgenFeature features : PMPWorldgenFeature.values()) {
			PMPDataFeature featureObject = (PMPDataFeature)this.features.get(features.name());
			if (featureObject != null) {
				featureObject.resetDefaults();
			}
		}
	}
	
	public void applyHabitatSettingDefaults(String habitatName) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitatName);
		if (habitatData != null) {
			habitatData.applyDefaultSettings();
		}
	}
	
	public void applyHabitatPlantDefaults(String habitatName) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitatName);
		if (habitatData != null) {
			habitatData.applyDefaultPlants();
		}
	}
	
	public void applyHabitatTreeDefaults(String habitatName) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitatName);
		if (habitatData != null) {
			habitatData.applyDefaultTrees();
		}
	}
	
	public void clearAll() {
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				habitatData.clearAll();
			}
		}
	}
	
	public PMPDataHabitat getHabitatDataFromName(String habitatName) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitatName);
		if (habitatData != null) {
			return habitatData;
		}
		return null;
	}
	
	public PMPDataSpawnPlant getRandomPlant(Random random, PMPHabitat habitat) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
		if (habitatData != null) {
			return habitatData.getRandomPlant(random);
		}
		return null;
	}
	
	public PMPDataSpawnTree getRandomTree(Random random, PMPHabitat habitat) {
		PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
		if (habitatData != null) {
			return habitatData.getRandomTree(random);
		}
		return null;
	}
	
	public long getTotalBiomeSpawnCount() {
		long count = 0L;
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				count += habitatData.getTotalHabitatSpawnCount();
			}
		}
		return count;
	}
	
	public boolean doesBiomeHaveFeature(PMPWorldgenFeature feature) {
		return this.features.containsKey(feature.name());
	}
	
	public PMPDataFeature getFeatureData(PMPWorldgenFeature feature) {
		return (PMPDataFeature)this.features.get(feature.name());
	}
	
	public void resetFeature(PMPWorldgenFeature feature) {
		if (feature == null) {}
	}
	
	public void loadSettingsDataLine(String data) {
		String[] astring = data.split(":");
		if (astring[0].equals("plantEmptyChunks")) {
			this.plantEmptyChunks = Integer.parseInt(astring[1]);
		} else if (astring[0].equals("plantEnabled")) {
			this.plantEnabled = Boolean.parseBoolean(astring[1]);
		} else if (astring[0].equals("plantPassesPerChunk")) {
			this.plantPassesPerChunk = Integer.parseInt(astring[1]);
		} else if (astring[0].equals("treeEmptyChunks")) {
			this.treeEmptyChunks = Integer.parseInt(astring[1]);
		} else if (astring[0].equals("treeEnabled")) {
			this.treeEnabled = Boolean.parseBoolean(astring[1]);
		} else if (astring[0].equals("treePassesPerChunk")) {
			this.treePassesPerChunk = Integer.parseInt(astring[1]);
		} else if (astring[0].equals("feature")) {
			PMPDataFeature featureData = (PMPDataFeature)this.features.get(astring[1]);
			if (featureData != null) {
				featureData.loadSettingsDataLine(astring[2]);
			}
		} else if (astring[0].equals("featurePlant")) {
			PMPDataFeature featureData = (PMPDataFeature)this.features.get(astring[1]);
			if (featureData != null) {
				featureData.loadPlantDataLine(astring[2], astring[3]);
			}
		} else if (astring[0].equals("featureTree")) {
			PMPDataFeature featureData = (PMPDataFeature)this.features.get(astring[1]);
			if (featureData != null) {
				featureData.loadTreeDataLine(astring[2], astring[3]);
			}
		} else if (astring[0].equals("data")) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(astring[1]);
			if (habitatData != null) {
				habitatData.loadSettingsDataLine(astring[2]);
			}
		} else if (astring[0].equals("plant")) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(astring[1]);
			if (habitatData != null) {
				habitatData.loadPlantDataLine(astring[2]);
			}
		} else if (astring[0].equals("tree")) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(astring[1]);
			if (habitatData != null) {
				habitatData.loadTreeDataLine(astring[2]);
			}
		}
	}
	
	public void saveSettings(PrintWriter printwriter) {
		printwriter.println(this.biome.name() + "=plantEmptyChunks:" + this.plantEmptyChunks);
		printwriter.println(this.biome.name() + "=plantEnabled:" + this.plantEnabled);
		printwriter.println(this.biome.name() + "=plantPassesPerChunk:" + this.plantPassesPerChunk);
		printwriter.println(this.biome.name() + "=treeEmptyChunks:" + this.treeEmptyChunks);
		printwriter.println(this.biome.name() + "=treeEnabled:" + this.treeEnabled);
		printwriter.println(this.biome.name() + "=treePassesPerChunk:" + this.treePassesPerChunk);
		Iterator localIterator;
		if (!this.features.isEmpty()) {
			for (localIterator = this.features.values().iterator(); localIterator.hasNext();) {
				PMPDataFeature feature = (PMPDataFeature)localIterator.next();
				
				feature.saveSettingsData(printwriter);
			}
		}
		PMPDataFeature feature;
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				habitatData.saveSettingsData(printwriter);
			}
		}
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				habitatData.savePlantData(printwriter);
			}
		}
		for (PMPHabitat habitat : PMPHabitat.values()) {
			PMPDataHabitat habitatData = (PMPDataHabitat)this.habitats.get(habitat.name());
			if (habitatData != null) {
				habitatData.saveTreeData(printwriter);
			}
		}
	}
}
