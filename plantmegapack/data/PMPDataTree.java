package plantmegapack.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import plantmegapack.PlantMegaPack;
import plantmegapack.object.PMPSapling;

public class PMPDataTree
{
	public PMPSapling sapling;
	public boolean worldgenEnabled;
	public boolean canSpawnOnSand;
	
	public PMPDataTree(PMPSapling sapling) {
		this.sapling = sapling;
		loadSettings();
	}
	
	public void resetAllDefaults() {
		this.worldgenEnabled = true;
		this.canSpawnOnSand = this.sapling.canSpawnOnSand;
	}
	
	public void loadSettings() {
		File optionsFile = new File(PlantMegaPack.configPathRoot + "/trees/" + this.sapling.name() + ".cfg");
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
					} else if (astring[0].equals("canSpawnOnSand")) {
						this.canSpawnOnSand = Boolean.parseBoolean(astring[1]);
					}
				}
			}
			bufferedreader.close();
		}
		catch (Exception localException) {}
	}
	
	public void saveSettings() {
		File optionsFile = new File(PlantMegaPack.configPathRoot + "/trees/" + this.sapling.name() + ".cfg");
		try
		{
			if (!optionsFile.exists()) {
				optionsFile.createNewFile();
				optionsFile.setWritable(true);
			}
			PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
			printwriter.println("worldgenEnabled:" + this.worldgenEnabled);
			printwriter.println("canSpawnOnSand:" + this.canSpawnOnSand);
			printwriter.close();
		}
		catch (Exception localException) {}
	}
}
