package plantmegapack.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import plantmegapack.PlantMegaPack;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantSpawnType;

public class PMPDataPlant
{
	public PMPPlant attributes;
	public boolean worldgenEnabled;
	public Map<String, Boolean> spawnTypes = new HashMap();
	
	public PMPDataPlant(PMPPlant attributes) {
		this.attributes = attributes;
		loadSettings();
	}
	
	public void resetAllDefaults() {
		this.worldgenEnabled = true;
		resetSettingsDefaults();
		resetSpawnTypeSettings();
	}
	
	public void resetSettingsDefaults() {}
	
	public boolean getSpawnTypeSetting(String key) {
		return ((Boolean)this.spawnTypes.get(key)).booleanValue();
	}
	
	public void setSpawnTypeSetting(String key, boolean value) {
		this.spawnTypes.put(key, Boolean.valueOf(value));
	}
	
	public void resetSpawnTypeSettings() {
		for (PMPPlantSpawnType spawnType : PMPPlantSpawnType.values()) {
			this.spawnTypes.put(spawnType.name(), Boolean.valueOf(this.attributes.canSpawnType(spawnType)));
		}
	}
	
	public void loadSettings() {
		File optionsFile = new File(PlantMegaPack.configPathRoot + "/plants/" + this.attributes.name() + ".cfg");
		if (!optionsFile.exists()) {
			resetAllDefaults();
			saveSettings();
			return;
		}
		try
		{
			BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
			String line = "";
			while ((line = bufferedreader.readLine()) != null) {
				if ((line.length() > 0) && (!line.startsWith("#"))) {
					String[] astring = line.split(":");
					if (astring[0].equals("worldgenEnabled")) {
						this.worldgenEnabled = Boolean.parseBoolean(astring[1]);
					} else if (astring[0].startsWith("spawn.")) {
						this.spawnTypes.put(astring[0].substring(6), Boolean.valueOf(Boolean.parseBoolean(astring[1])));
					}
				}
			}
			bufferedreader.close();
		}
		catch (Exception localException) {}
	}
	
	public void saveSettings() {
		File optionsFile = new File(PlantMegaPack.configPathRoot + "/plants/" + this.attributes.name() + ".cfg");
		try
		{
			if (!optionsFile.exists()) {
				optionsFile.createNewFile();
				optionsFile.setWritable(true);
			}
			PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
			printwriter.println("worldgenEnabled:" + this.worldgenEnabled);
			for (Map.Entry<String, Boolean> spawnType : this.spawnTypes.entrySet()) {
				printwriter.println("spawn." + (String)spawnType.getKey() + ":" + spawnType.getValue());
			}
			printwriter.close();
		}
		catch (Exception localException) {}
	}
}
