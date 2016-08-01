package plantmegapack.json;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockTreeFruit;
import plantmegapack.core.PMPBlocks;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPLeaves;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPPlanter;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPSeed;
import plantmegapack.object.PMPStem;
import plantmegapack.object.PMPTrellis;
import plantmegapack.object.PMPWallBracket;
import plantmegapack.object.PMPWood;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantRender;

public abstract class PMPJsonModelItem
{
	public static int createItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		filesGenerated += createBookItemModelFiles(rootPath);
		filesGenerated += createCoralFragmentItemModelFiles(rootPath);
		filesGenerated += createFlowerItemModelFiles(rootPath);
		filesGenerated += createFoodItemModelFiles(rootPath);
		filesGenerated += createPlantBlockItemModelFiles(rootPath);
		filesGenerated += createPlantItemsModelFiles(rootPath);
		filesGenerated += createPowderItemModelFiles(rootPath);
		filesGenerated += createRootItemModelFiles(rootPath);
		filesGenerated += createSalveItemModelFiles(rootPath);
		filesGenerated += createSaplingItemModelFiles(rootPath);
		filesGenerated += createSeedItemModelFiles(rootPath);
		filesGenerated += createStemItemModelFiles(rootPath);
		filesGenerated += createWoodItemModelFiles(rootPath);
		filesGenerated += createLeavesItemModelFiles(rootPath);
		filesGenerated += createBambooItemModelFiles(rootPath);
		filesGenerated += createTreeFruitsItemModelFiles(rootPath);
		filesGenerated += createPlanterItemModelFiles(rootPath);
		filesGenerated += createTrellisItemModelFiles(rootPath);
		filesGenerated += createWallBracketItemModelFiles(rootPath);
		filesGenerated += createHangingPlantItemModelFiles(rootPath);
		return filesGenerated;
	}
	
	private static int createBookItemModelFiles(String rootPath) {
		String name = "bookPlantGuide";
		createItemModelFile(rootPath + name, name, "", true);
		return 1;
	}
	
	private static int createCoralFragmentItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPFragment coralFragment : PMPFragment.values()) {
			createItemModelFile(rootPath + coralFragment.name(), coralFragment.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createFlowerItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPFlower flower : PMPFlower.values()) {
			createItemModelFile(rootPath + flower.name(), flower.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createFoodItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPFood food : PMPFood.values()) {
			createItemModelFile(rootPath + food.name(), food.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createPlantBlockItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlant plant : PMPPlant.values()) {
			if (plant.blockType == PMPPlantBlockType.doub) {
				createItemModelFile(rootPath + plant.name(), plant.name(), "", false);
				filesGenerated++;
			} else if (plant.blockType == PMPPlantBlockType.fflo) {
				createItemModelFile(rootPath + plant.name(), plant.name(), "", false);
				filesGenerated++;
				for (PMPColor color : PMPColor.values()) {
					createItemModelFile(rootPath + plant.name() + "_" + color.name(), plant.name(), plant.name() + "_" + color.name(), false);
					filesGenerated++;
				}
			} else if (plant.renderType == PMPPlantRender.fpla) {
				createItemModelFile(rootPath + plant.name(), plant.name() + "0", plant.name() + "1", false);
				filesGenerated++;
			} else if (plant.blockType == PMPPlantBlockType.flow) {
				createItemModelFile(rootPath + plant.name(), plant.name() + "_white", "", false);
				filesGenerated++;
				for (PMPColor color : PMPColor.values()) {
					createItemModelFile(rootPath + plant.name() + "_" + color.name(), plant.name() + "_" + color.name(), "", false);
					filesGenerated++;
				}
			} else if (plant.renderType == PMPPlantRender.trpl) {
				createItemModelFile(rootPath + plant.name(), plant.name(), "", false);
				filesGenerated++;
			} else
			{
				createItemModelFile(rootPath + plant.name(), plant.name() + String.format("%d", new Object[] { Integer.valueOf(plant.blockType.itemModelIndex) }), "", false);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static int createPlantItemsModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlantItem plantItem : PMPPlantItem.values()) {
			createItemModelFile(rootPath + plantItem.name(), plantItem.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createPowderItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPowder powder : PMPPowder.values()) {
			createItemModelFile(rootPath + powder.name(), powder.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createRootItemModelFiles(String rootPath) {
		createItemModelFile(rootPath + "rootMedicinal", "rootMedicinal", "", true);
		return 1;
	}
	
	private static int createSalveItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSalve salve : PMPSalve.values()) {
			createItemModelFile(rootPath + salve.name(), salve.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createSaplingItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			createItemModelFile(rootPath + sapling.name(), sapling.name(), "", false);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createSeedItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSeed seed : PMPSeed.values()) {
			createItemModelFile(rootPath + seed.name(), seed.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createStemItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPStem stem : PMPStem.values()) {
			createItemModelFile(rootPath + stem.name(), stem.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createWoodItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWood wood : PMPWood.values()) {
			createWoodBlockItemModelFile(rootPath + wood.name(), wood.name());
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + wood.name() + "Block", wood.name() + "Block");
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + wood.name() + "Planks", wood.name() + "Planks");
			filesGenerated++;
			createWoodStairsItemModelFile(rootPath + wood.name() + "Stairs", wood.name() + "Stairs0");
			filesGenerated++;
			createItemModelFile(rootPath + wood.name() + "Door", wood.name() + "Door", "", true);
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + wood.name() + "Slab", wood.name() + "Slab0");
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + wood.name() + "DoubleSlab", wood.name() + "Slab0");
			filesGenerated++;
			createWoodFenceItemModelFile(rootPath + wood.name() + "Fence", wood.name() + "_fence_inventory");
			filesGenerated++;
			createWoodGateItemModelFile(rootPath + wood.name() + "Gate", wood.name() + "Gate0");
			filesGenerated++;
			createItemModelFile(rootPath + wood.name() + "Ladder", wood.name() + "Ladder", "", false);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createLeavesItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPLeaves leaves : PMPLeaves.values()) {
			createLeavesItemModelFile(rootPath + leaves.name(), leaves.name());
			filesGenerated++;
			filesGenerated += createLeavesModelFiles(rootPath, leaves);
		}
		return filesGenerated;
	}
	
	private static int createLeavesModelFiles(String rootPath, PMPLeaves leaves) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.leaves == leaves) {
				String variantName = sapling.name().toLowerCase().substring(7);
				createLeavesItemModelFile(rootPath + "leaves_" + variantName, variantName);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static int createBambooItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			createWoodBlockItemModelFile(rootPath + bamboo.name() + "Block", bamboo.name() + "Block");
			filesGenerated++;
			createWoodStairsItemModelFile(rootPath + bamboo.name() + "Stairs", bamboo.name() + "Stairs0");
			filesGenerated++;
			createItemModelFile(rootPath + bamboo.name() + "Door", bamboo.name() + "Door", "", true);
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + bamboo.name() + "Slab", bamboo.name() + "Slab0");
			filesGenerated++;
			createWoodBlockItemModelFile(rootPath + bamboo.name() + "DoubleSlab", bamboo.name() + "Slab0");
			filesGenerated++;
			createWoodFenceItemModelFile(rootPath + bamboo.name() + "Fence", bamboo.name() + "_fence_inventory");
			filesGenerated++;
			createWoodGateItemModelFile(rootPath + bamboo.name() + "Gate", bamboo.name() + "Gate0");
			filesGenerated++;
			createItemModelFile(rootPath + bamboo.name() + "Ladder", bamboo.name() + "Ladder", "", false);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createTreeFruitsItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.treeFruitType != null) {
				PMPBlockTreeFruit blockTreeFruit = PlantMegaPack.blocks.getTreeFruit(sapling);
				createItemModelFile(rootPath + blockTreeFruit.getUnlocalizedNameModified(), sapling.food.name(), "", true);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static void createItemModelFile(String file, String textureLayer0, String textureLayer1, boolean isItem) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"item/generated\",");
					printwriter.println("\"textures\":{");
					if (isItem) {
						printwriter.println(String.format("\"layer0\":\"plantmegapack:items/%s\"%s", new Object[] { textureLayer0, textureLayer1.length() == 0 ? "" : "," }));
						if (textureLayer1.length() > 0) {
							printwriter.println(String.format("\"layer1\":\"plantmegapack:items/%s\"", new Object[] { textureLayer1 }));
						}
					} else {
						printwriter.println(String.format("\"layer0\":\"plantmegapack:blocks/%s\"%s", new Object[] { textureLayer0, textureLayer1.length() == 0 ? "" : "," }));
						if (textureLayer1.length() > 0) {
							printwriter.println(String.format("\"layer1\":\"plantmegapack:blocks/%s\"", new Object[] { textureLayer1 }));
						}
					}
					printwriter.println("}");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createLeavesItemModelFile(String file, String leaves) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/leaves_" + leaves + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createPlanterItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlanter planter : PMPPlanter.values()) {
			createPlanterItemModelFile(rootPath + planter.name(), planter.name() + "6");
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createPlanterItemModelFile(String file, String parent) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/" + parent + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createTrellisItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPTrellis trellis : PMPTrellis.values()) {
			createItemModelFile(rootPath + trellis.name(), trellis.name(), "", false);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createWallBracketItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWallBracket bracket : PMPWallBracket.values()) {
			createItemModelFile(rootPath + bracket.name(), bracket.name() + "0", "", false);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createHangingPlantItemModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPHangingPlant hangingPlant : PMPHangingPlant.values()) {
			createItemModelFile(rootPath + hangingPlant.name(), hangingPlant.name(), "", true);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createWoodBlockItemModelFile(String file, String parent) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/" + parent + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createWoodStairsItemModelFile(String file, String parent) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/" + parent + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createWoodFenceItemModelFile(String file, String parent) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/" + parent + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createWoodGateItemModelFile(String file, String parent) {
		File jsonFile = new File(file + ".json");
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/" + parent + "\"");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
