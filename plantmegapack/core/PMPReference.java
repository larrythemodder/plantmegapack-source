package plantmegapack.core;

import plantmegapack.PlantMegaPack;
import plantmegapack.plant.PMPPlantCategory;

public class PMPReference
{
	public static final String MOD_ID = "plantmegapack";
	public static final String MOD_NAME = "Plant Mega Pack";
	public static final String MOD_VER = "1.9.8";
	public static final String MOD_GUICLASS = "plantmegapack.gui.core.PMPGuiFactory";
	public static final String PROXY_CLIENT = "plantmegapack.core.PMPProxyClient";
	public static final String PROXY_COMMON = "plantmegapack.core.PMPProxyCommon";
	public static final String PROXY_SERVER = "plantmegapack.core.PMPProxyServer";
	public static final boolean DEV_DEBUG_LOGGING = false;
	public static final boolean DEV_GEN_BIOME_LIST = false;
	public static final boolean DEV_GEN_JSON_FILES = false;
	public static final boolean DEV_GEN_WOOL_BLOCKS = false;
	private static int statRegisteredBlocks = 0;
	private static int statRegisteredItems = 0;
	private static int statRegisteredPlants = 0;
	private static int statRegisteredTrees = 0;
	private static int statRegisteredUniquePlants = 0;
	private static int statRegisteredRecipes = 0;
	private static int statRegisteredChestItems = 0;
	private static int statRegisteredBookEntries = 0;
	private static int statRegisteredBookEntryItems = 0;
	
	public static void addToRegisteredBlocks(int quantity) {
		if (quantity > 0) {
			statRegisteredBlocks += quantity;
		}
	}
	
	public static void addToRegisteredItems(int quantity) {
		if (quantity > 0) {
			statRegisteredItems += quantity;
		}
	}
	
	public static void addToRegisteredPlants(int quantity) {
		if (quantity > 0) {
			statRegisteredPlants += quantity;
		}
	}
	
	public static void addToRegisteredTrees(int quantity) {
		if (quantity > 0) {
			statRegisteredTrees += quantity;
		}
	}
	
	public static void addToRegisteredUniquePlants(int quantity) {
		if (quantity > 0) {
			statRegisteredUniquePlants += quantity;
		}
	}
	
	public static void addToRegisteredRecipes(int quantity) {
		if (quantity > 0) {
			statRegisteredRecipes += quantity;
		}
	}
	
	public static void addToRegisteredChestItems(int quantity) {
		if (quantity > 0) {
			statRegisteredChestItems += quantity;
		}
	}
	
	public static void addToRegisteredBookEntries(int quantity) {
		if (quantity > 0) {
			statRegisteredBookEntries += quantity;
		}
	}
	
	public static void addToRegisteredBookEntryItems(int quantity) {
		if (quantity > 0) {
			statRegisteredBookEntryItems += quantity;
		}
	}
	
	public static int getRegisteredCategories() {
		return PMPPlantCategory.values().length;
	}
	
	public static int getRegisteredBlocks() {
		return statRegisteredBlocks;
	}
	
	public static int getRegisteredItems() {
		return statRegisteredItems;
	}
	
	public static int getRegisteredPlants() {
		return statRegisteredPlants;
	}
	
	public static int getRegisteredTrees() {
		return statRegisteredTrees;
	}
	
	public static int getRegisteredUniquePlants() {
		return statRegisteredUniquePlants;
	}
	
	public static int getRegisteredRecipes() {
		return statRegisteredRecipes;
	}
	
	public static int getRegisteredChestItems() {
		return statRegisteredChestItems;
	}
	
	public static int getRegisteredBookEntries() {
		return statRegisteredBookEntries;
	}
	
	public static int getRegisteredBookEntryItems() {
		return statRegisteredBookEntryItems;
	}
	
	public static void outputModStatisticsToConsole() {
		PlantMegaPack.instance.logOutput(String.format("Plant categories: %d", new Object[] { Integer.valueOf(getRegisteredCategories()) }));
		PlantMegaPack.instance.logOutput(String.format("Plant blocks		: %d", new Object[] { Integer.valueOf(getRegisteredPlants()) }));
		PlantMegaPack.instance.logOutput(String.format("Unique plants	 : %d", new Object[] { Integer.valueOf(getRegisteredUniquePlants()) }));
		PlantMegaPack.instance.logOutput(String.format("Trees					 : %d", new Object[] { Integer.valueOf(getRegisteredTrees()) }));
		PlantMegaPack.instance.logOutput(String.format("Blocks					: %d", new Object[] { Integer.valueOf(getRegisteredBlocks()) }));
		PlantMegaPack.instance.logOutput(String.format("Items					 : %d", new Object[] { Integer.valueOf(getRegisteredItems()) }));
		PlantMegaPack.instance.logOutput(String.format("Recipes				 : %d", new Object[] { Integer.valueOf(getRegisteredRecipes()) }));
		PlantMegaPack.instance.logOutput(String.format("Chest items		 : %d", new Object[] { Integer.valueOf(getRegisteredChestItems()) }));
		PlantMegaPack.instance.logOutput(String.format("Book entries		: %d", new Object[] { Integer.valueOf(getRegisteredBookEntries()) }));
		PlantMegaPack.instance.logOutput(String.format("Book entry items: %d", new Object[] { Integer.valueOf(getRegisteredBookEntryItems()) }));
	}
}
