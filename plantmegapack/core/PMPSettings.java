package plantmegapack.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import plantmegapack.PlantMegaPack;

public class PMPSettings
{
	private File optionsFile;
	public boolean bookChatMessage;
	public boolean bookDropXPOnDiscovery;
	public boolean bookSaveProgress;
	public boolean bookShowAllPlants;
	public int chestGenBonus;
	public int chestGenDesertTemple;
	public int chestGenDungeonChest;
	public int chestGenJungleTemple;
	public int chestGenMineshaftCorridor;
	public int chestGenStronghold;
	public int chestGenVillageBlacksmith;
	public boolean chestItemBook;
	public boolean chestItemCropSeed;
	public boolean chestItemFlower;
	public boolean chestItemPowder;
	public boolean chestItemRoot;
	public boolean chestItemSalve;
	public boolean chestItemStem;
	public boolean debugMode;
	public int plantBambooMaxHeight;
	public int plantClimbingMaxHeight;
	public int plantHangingMaxHeight;
	public int plantSeaweedMaxHeight;
	public int powderConditionerElevation;
	public int powderConditionerRadius;
	public int powderConditionerStrength;
	public int powderConditionerUses;
	public int powderDefoliantElevation;
	public int powderDefoliantRadius;
	public int powderDefoliantStrength;
	public int powderDefoliantUses;
	public int powderFertilizerElevation;
	public int powderFertilizerRadius;
	public int powderFertilizerStrength;
	public int powderFertilizerUses;
	public boolean realismCropCentered;
	public boolean realismGravity;
	public boolean realismPlantCentered;
	public boolean realismPoison;
	public boolean realismThorns;
	public int salveFireResist;
	public int salveHealing;
	public int salveNightVision;
	public int salveStrength;
	public int salveSwiftness;
	public int salveWaterBreathing;
	public String startupProfile;
	public boolean tooltipCategory;
	public boolean tooltipConservation;
	public boolean tooltipCrafting;
	public boolean tooltipDrops;
	public boolean tooltipLatinName;
	public int worldgenPlants;
	public boolean worldgenRetrogen;
	public int worldgenTrees;
	
	public PMPSettings(String rootPath) {
		this.optionsFile = new File(rootPath + "settings.cfg");
		this.debugMode = false;
		resetBookDefaults();
		resetChestGeneration();
		resetChestItemDefaults();
		resetContentDefaults();
		resetPlantDefaults();
		resetPowderDefaults();
		resetRealismDefaults();
		resetSalveDefaults();
		applyDefaultStartupProfileName();
		resetTooltipDefaults();
		resetWorldgenDefaults();
		setupConfigDirs(rootPath);
		loadOptions();
	}
	
	private void setupConfigDirs(String rootPath) {
		PMPHelper.createDirectory(rootPath);
		PMPHelper.createDirectory(rootPath + "plants");
		PMPHelper.createDirectory(rootPath + "profiles");
		PMPHelper.createDirectory(rootPath + "trees");
		
		File jsonConfigDir = new File(rootPath + "json");
		if (jsonConfigDir.exists()) {
			try
			{
				jsonConfigDir.delete();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void resetBookDefaults() {
		this.bookChatMessage = true;
		this.bookDropXPOnDiscovery = true;
		this.bookSaveProgress = true;
		this.bookShowAllPlants = false;
	}
	
	public void resetChestGeneration() {
		this.chestGenBonus = 1;
		this.chestGenDesertTemple = 0;
		this.chestGenDungeonChest = 0;
		this.chestGenJungleTemple = 0;
		this.chestGenMineshaftCorridor = 1;
		this.chestGenStronghold = 0;
		this.chestGenVillageBlacksmith = 2;
	}
	
	public void resetChestItemDefaults() {
		this.chestItemBook = true;
		this.chestItemCropSeed = true;
		this.chestItemFlower = false;
		this.chestItemPowder = false;
		this.chestItemRoot = false;
		this.chestItemSalve = false;
		this.chestItemStem = false;
	}
	
	public void resetContentDefaults() {}
	
	public void resetPlantDefaults() {
		this.plantBambooMaxHeight = 12;
		this.plantClimbingMaxHeight = 4;
		this.plantHangingMaxHeight = 6;
		this.plantSeaweedMaxHeight = 16;
	}
	
	public void resetPowderDefaults() {
		this.powderConditionerElevation = 4;
		this.powderConditionerRadius = 3;
		this.powderConditionerStrength = 95;
		this.powderConditionerUses = 10;
		
		this.powderDefoliantElevation = 4;
		this.powderDefoliantRadius = 3;
		this.powderDefoliantStrength = 95;
		this.powderDefoliantUses = 10;
		
		this.powderFertilizerElevation = 4;
		this.powderFertilizerRadius = 3;
		this.powderFertilizerStrength = 95;
		this.powderFertilizerUses = 10;
	}
	
	public void resetRealismDefaults() {
		this.realismCropCentered = false;
		this.realismGravity = true;
		this.realismPlantCentered = false;
		this.realismPoison = false;
		this.realismThorns = true;
	}
	
	public void resetSalveDefaults() {
		this.salveFireResist = 30;
		this.salveHealing = 20;
		this.salveNightVision = 30;
		this.salveStrength = 30;
		this.salveSwiftness = 30;
		this.salveWaterBreathing = 30;
	}
	
	public void applyDefaultStartupProfileName() {
		this.startupProfile = getDefaultProfileName();
	}
	
	public String getDefaultProfileName() {
		return "Default";
	}
	
	public void resetTooltipDefaults() {
		this.tooltipCategory = true;
		this.tooltipConservation = true;
		this.tooltipCrafting = true;
		this.tooltipDrops = true;
		this.tooltipLatinName = true;
	}
	
	public void resetWorldgenDefaults() {
		this.worldgenPlants = 35;
		this.worldgenRetrogen = false;
		this.worldgenTrees = 35;
	}
	
	public boolean loadOptions() {
		try
		{
			if (!this.optionsFile.exists()) {
				return saveOptions();
			}
			BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
			String line = "";
			while ((line = bufferedreader.readLine()) != null) {
				try
				{
					String[] optionLine = line.split(":");
					if (optionLine[0].equals("bookChatMessage")) {
						this.bookChatMessage = optionLine[1].equals("true");
					} else if (optionLine[0].equals("bookDropXPOnDiscovery")) {
						this.bookDropXPOnDiscovery = optionLine[1].equals("true");
					} else if (optionLine[0].equals("bookSaveProgress")) {
						this.bookSaveProgress = optionLine[1].equals("true");
					} else if (optionLine[0].equals("bookShowAllPlants")) {
						this.bookShowAllPlants = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestGenBonus")) {
						this.chestGenBonus = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenDesertTemple")) {
						this.chestGenDesertTemple = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenDungeonChest")) {
						this.chestGenDungeonChest = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenJungleTemple")) {
						this.chestGenJungleTemple = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenMineshaftCorridor")) {
						this.chestGenMineshaftCorridor = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenStronghold")) {
						this.chestGenStronghold = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestGenVillageBlacksmith")) {
						this.chestGenVillageBlacksmith = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("chestItemBook")) {
						this.chestItemBook = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemCropSeed")) {
						this.chestItemCropSeed = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemFlower")) {
						this.chestItemFlower = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemPowder")) {
						this.chestItemPowder = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemRoot")) {
						this.chestItemRoot = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemSalve")) {
						this.chestItemSalve = optionLine[1].equals("true");
					} else if (optionLine[0].equals("chestItemStem")) {
						this.chestItemStem = optionLine[1].equals("true");
					} else if (optionLine[0].equals("debugMode")) {
						this.debugMode = optionLine[1].equals("true");
					} else if (optionLine[0].equals("plantBambooMaxHeight")) {
						this.plantBambooMaxHeight = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("plantClimbingMaxHeight")) {
						this.plantClimbingMaxHeight = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("plantHangingMaxHeight")) {
						this.plantHangingMaxHeight = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("plantSeaweedMaxHeight")) {
						this.plantSeaweedMaxHeight = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderConditionerElevation")) {
						this.powderConditionerElevation = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderConditionerRadius")) {
						this.powderConditionerRadius = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderConditionerStrength")) {
						this.powderConditionerStrength = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderConditionerUses")) {
						this.powderConditionerUses = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderDefoliantElevation")) {
						this.powderDefoliantElevation = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderDefoliantRadius")) {
						this.powderDefoliantRadius = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderDefoliantStrength")) {
						this.powderDefoliantStrength = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderDefoliantUses")) {
						this.powderDefoliantUses = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderFertilizerElevation")) {
						this.powderFertilizerElevation = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderFertilizerRadius")) {
						this.powderFertilizerRadius = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderFertilizerStrength")) {
						this.powderFertilizerStrength = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("powderFertilizerUses")) {
						this.powderFertilizerUses = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("realismCropCentered")) {
						this.realismCropCentered = optionLine[1].equals("true");
					} else if (optionLine[0].equals("realismGravity")) {
						this.realismGravity = optionLine[1].equals("true");
					} else if (optionLine[0].equals("realismPlantCentered")) {
						this.realismPlantCentered = optionLine[1].equals("true");
					} else if (optionLine[0].equals("realismPoison")) {
						this.realismPoison = optionLine[1].equals("true");
					} else if (optionLine[0].equals("realismThorns")) {
						this.realismThorns = optionLine[1].equals("true");
					} else if (optionLine[0].startsWith("salveFireResist")) {
						this.salveFireResist = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].startsWith("salveHealing")) {
						this.salveHealing = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].startsWith("salveNightVision")) {
						this.salveNightVision = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].startsWith("salveStrength")) {
						this.salveStrength = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].startsWith("salveSwiftness")) {
						this.salveSwiftness = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].startsWith("salveWaterBreathing")) {
						this.salveWaterBreathing = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("startupProfile")) {
						this.startupProfile = optionLine[1];
					} else if (optionLine[0].equals("tooltipCategory")) {
						this.tooltipCategory = optionLine[1].equals("true");
					} else if (optionLine[0].equals("tooltipConservation")) {
						this.tooltipConservation = optionLine[1].equals("true");
					} else if (optionLine[0].equals("tooltipCrafting")) {
						this.tooltipCrafting = optionLine[1].equals("true");
					} else if (optionLine[0].equals("tooltipDrops")) {
						this.tooltipDrops = optionLine[1].equals("true");
					} else if (optionLine[0].equals("tooltipLatinName")) {
						this.tooltipLatinName = optionLine[1].equals("true");
					} else if (optionLine[0].equals("worldgenPlants")) {
						this.worldgenPlants = Integer.parseInt(optionLine[1]);
					} else if (optionLine[0].equals("worldgenRetrogen")) {
						this.worldgenRetrogen = optionLine[1].equals("true");
					} else if (optionLine[0].equals("worldgenTrees")) {
						this.worldgenTrees = Integer.parseInt(optionLine[1]);
					} else {
						PlantMegaPack.instance.logOutput(String.format("Unrecognized config option \"%s\" ignored" + optionLine[0], new Object[0]));
					}
				}
				catch (Exception exception) {
					PlantMegaPack.instance.logOutput("Skipping bad config line: " + line);
				}
			}
			bufferedreader.close();
		}
		catch (Exception exception1) {
			PlantMegaPack.instance.logOutput("Load config file: settings.cfg FAILED");
			return false;
		}
		PlantMegaPack.instance.logOutput("Load config file: settings.cfg");
		return true;
	}
	
	public boolean saveOptions() {
		try
		{
			PrintWriter printwriter = new PrintWriter(new FileWriter(this.optionsFile));
			
			printwriter.println("bookChatMessage:" + this.bookChatMessage);
			printwriter.println("bookDropXPOnDiscovery:" + this.bookDropXPOnDiscovery);
			printwriter.println("bookSaveProgress:" + this.bookSaveProgress);
			printwriter.println("bookShowAllPlants:" + this.bookShowAllPlants);
			printwriter.println("chestGenBonus:" + this.chestGenBonus);
			printwriter.println("chestGenDesertTemple:" + this.chestGenDesertTemple);
			printwriter.println("chestGenDungeonChest:" + this.chestGenDungeonChest);
			printwriter.println("chestGenJungleTemple:" + this.chestGenJungleTemple);
			printwriter.println("chestGenMineshaftCorridor:" + this.chestGenMineshaftCorridor);
			printwriter.println("chestGenStronghold:" + this.chestGenStronghold);
			printwriter.println("chestGenVillageBlacksmith:" + this.chestGenVillageBlacksmith);
			printwriter.println("chestItemBook:" + this.chestItemBook);
			printwriter.println("chestItemCropSeed:" + this.chestItemCropSeed);
			printwriter.println("chestItemFlower:" + this.chestItemFlower);
			printwriter.println("chestItemPowder:" + this.chestItemPowder);
			printwriter.println("chestItemRoot:" + this.chestItemRoot);
			printwriter.println("chestItemSalve:" + this.chestItemSalve);
			printwriter.println("chestItemStem:" + this.chestItemStem);
			printwriter.println("debugMode:" + this.debugMode);
			printwriter.println("plantBambooMaxHeight:" + this.plantBambooMaxHeight);
			printwriter.println("plantClimbingMaxHeight:" + this.plantClimbingMaxHeight);
			printwriter.println("plantHangingMaxHeight:" + this.plantHangingMaxHeight);
			printwriter.println("plantSeaweedMaxHeight:" + this.plantSeaweedMaxHeight);
			printwriter.println("powderConditionerElevation:" + this.powderConditionerElevation);
			printwriter.println("powderConditionerRadius:" + this.powderConditionerRadius);
			printwriter.println("powderConditionerStrength:" + this.powderConditionerStrength);
			printwriter.println("powderConditionerUses:" + this.powderConditionerUses);
			printwriter.println("powderConditionerElevation:" + this.powderConditionerElevation);
			printwriter.println("powderDefoliantRadius:" + this.powderDefoliantRadius);
			printwriter.println("powderDefoliantStrength:" + this.powderDefoliantStrength);
			printwriter.println("powderDefoliantUses:" + this.powderDefoliantUses);
			printwriter.println("powderConditionerElevation:" + this.powderConditionerElevation);
			printwriter.println("powderFertilizerRadius:" + this.powderFertilizerRadius);
			printwriter.println("powderFertilizerStrength:" + this.powderFertilizerStrength);
			printwriter.println("powderFertilizerUses:" + this.powderFertilizerUses);
			printwriter.println("realismCropCentered:" + this.realismCropCentered);
			printwriter.println("realismGravity:" + this.realismGravity);
			printwriter.println("realismPlantCentered:" + this.realismPlantCentered);
			printwriter.println("realismPoison:" + this.realismPoison);
			printwriter.println("realismThorns:" + this.realismThorns);
			printwriter.println("salveFireResist:" + this.salveFireResist);
			printwriter.println("salveHealing:" + this.salveHealing);
			printwriter.println("salveNightVision:" + this.salveNightVision);
			printwriter.println("salveStrength:" + this.salveStrength);
			printwriter.println("salveSwiftness:" + this.salveSwiftness);
			printwriter.println("salveWaterBreathing:" + this.salveWaterBreathing);
			printwriter.println("startupProfile:" + this.startupProfile);
			printwriter.println("tooltipCategory:" + this.tooltipCategory);
			printwriter.println("tooltipConservation:" + this.tooltipConservation);
			printwriter.println("tooltipCrafting:" + this.tooltipCrafting);
			printwriter.println("tooltipDrops:" + this.tooltipDrops);
			printwriter.println("tooltipLatinName:" + this.tooltipLatinName);
			printwriter.println("worldgenPlants:" + this.worldgenPlants);
			printwriter.println("worldgenRetrogen:" + this.worldgenRetrogen);
			printwriter.println("worldgenTrees:" + this.worldgenTrees);
			printwriter.close();
		}
		catch (Exception exception) {
			PlantMegaPack.instance.logOutput("Failed to save settings.cfg");
			return false;
		}
		return true;
	}
}
