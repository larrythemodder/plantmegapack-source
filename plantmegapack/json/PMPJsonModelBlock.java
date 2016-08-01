package plantmegapack.json;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import plantmegapack.block.PMPBlockHangingPlant;
import plantmegapack.block.PMPBlockHangingPlant.Segment;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPLeaves;
import plantmegapack.object.PMPPlanter;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPTreeFruitType;
import plantmegapack.object.PMPTrellis;
import plantmegapack.object.PMPWallBracket;
import plantmegapack.object.PMPWood;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantRender;

public abstract class PMPJsonModelBlock
{
	public static int createBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		filesGenerated += createPlantBlockModelFiles(rootPath);
		filesGenerated += createSaplingBlockModelFiles(rootPath);
		filesGenerated += createWoodBlockModelFiles(rootPath);
		filesGenerated += createLeavesBlockModelFiles(rootPath);
		filesGenerated += createBambooBlockModelFiles(rootPath);
		filesGenerated += createTreeFruitBlockModelFiles(rootPath);
		filesGenerated += createPlanterBlockModelFiles(rootPath);
		filesGenerated += createTrellisBlockModelFiles(rootPath);
		filesGenerated += createWallBracketBlockModelFiles(rootPath);
		filesGenerated += createHangingPlantBlockModelFiles(rootPath);
		return filesGenerated;
	}
	
	private static int createPlantBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlant plant : PMPPlant.values()) {
			for (int index = 0; index < plant.blockType.models; index++) {
				createPlantModelFile(rootPath, plant, index);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static void createPlantModelFile(String rootPath, PMPPlant plant, int variant) {
		String fileName;
		if (PMPPlant.hasColorVariants(plant)) {
			fileName = String.format("%s%s_%s.json", new Object[] { rootPath, plant.name(), PMPColor.getColorFromID(variant).name() });
		} else {
			if (variant == -1) {
				fileName = String.format("%s%s.json", new Object[] { rootPath, plant.name() });
			} else {
				fileName = String.format("%s%s%d.json", new Object[] { rootPath, plant.name(), Integer.valueOf(variant) });
			}
		}
		File jsonFile = new File(fileName);
		try
		{
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					if (plant.category == PMPPlantCategory.clim) {
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s%d\",", new Object[] { plant.renderType.name(), Integer.valueOf(variant) }));
					} else if (plant.blockType == PMPPlantBlockType.crpa) {
						if (plant.category == PMPPlantCategory.crpa) {
							printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s0\",", new Object[] { plant.renderType.name() }));
						} else if (plant.category == PMPPlantCategory.floa) {
							printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s\",", new Object[] { plant.renderType.name() }));
						}
					} else if ((plant.blockType == PMPPlantBlockType.immd) || (plant.blockType == PMPPlantBlockType.imme)) {
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s%d\",", new Object[] { plant.renderType.name(), Integer.valueOf(variant) }));
					} else if (plant.category == PMPPlantCategory.vine) {
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s%d\",", new Object[] { plant.renderType.name(), Integer.valueOf(variant) }));
					} else {
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_%s\",", new Object[] { plant.renderType.name() }));
					}
					printwriter.println("\"textures\":{");
					if (plant.blockType == PMPPlantBlockType.crpa) {
						if (plant.category == PMPPlantCategory.crpa) {
							printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s%d\",", new Object[] { plant.name(), Integer.valueOf(variant + 1) }));
							printwriter.println(String.format("\"immersed\":\"plantmegapack:blocks/%s0\"", new Object[] { plant.name() }));
						} else if (plant.category == PMPPlantCategory.floa) {
							printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s%d\"", new Object[] { plant.name(), Integer.valueOf(variant) }));
						}
					} else if (plant.category == PMPPlantCategory.epip) {
						if (plant.renderType == PMPPlantRender.epih) {
							printwriter.println(String.format("\"top\":\"plantmegapack:blocks/%s2\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"middle\":\"plantmegapack:blocks/%s1\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"bottom\":\"plantmegapack:blocks/%s0\"", new Object[] { plant.name() }));
						} else {
							printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s0\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"side\":\"plantmegapack:blocks/%s1\"", new Object[] { plant.name() }));
						}
					} else if (plant.blockType == PMPPlantBlockType.fflo) {
						printwriter.println(String.format("\"top\":\"plantmegapack:blocks/%s_%s\",", new Object[] { plant.name(), PMPColor.getColorFromID(variant).name() }));
						printwriter.println(String.format("\"bottom\":\"plantmegapack:blocks/%s\"", new Object[] { plant.name() }));
					} else if (plant.blockType == PMPPlantBlockType.flow) {
						printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s_%s\"", new Object[] { plant.name(), PMPColor.getColorFromID(variant).name() }));
					} else if (plant.renderType == PMPPlantRender.fpla) {
						printwriter.println(String.format("\"top\":\"plantmegapack:blocks/%s1\",", new Object[] { plant.name() }));
						printwriter.println(String.format("\"bottom\":\"plantmegapack:blocks/%s0\"", new Object[] { plant.name() }));
					} else if ((plant.blockType == PMPPlantBlockType.immd) || (plant.blockType == PMPPlantBlockType.imme)) {
						if (variant == 0) {
							printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s1\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"immersed\":\"plantmegapack:blocks/%s0\"", new Object[] { plant.name() }));
						} else {
							printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s2\"", new Object[] { plant.name() }));
						}
					} else if (plant.renderType == PMPPlantRender.trpl) {
						if (variant == 0) {
							printwriter.println(String.format("\"east\":\"plantmegapack:blocks/%s_be\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"west\":\"plantmegapack:blocks/%s_bw\"", new Object[] { plant.name() }));
						} else if (variant == 1) {
							printwriter.println(String.format("\"east\":\"plantmegapack:blocks/%s_me\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"west\":\"plantmegapack:blocks/%s_mw\"", new Object[] { plant.name() }));
						} else if (variant == 2) {
							printwriter.println(String.format("\"east\":\"plantmegapack:blocks/%s_te\",", new Object[] { plant.name() }));
							printwriter.println(String.format("\"west\":\"plantmegapack:blocks/%s_tw\"", new Object[] { plant.name() }));
						}
					} else if (plant.category == PMPPlantCategory.vine) {
						printwriter.println("\"vine\":\"plantmegapack:blocks/" + plant.name() + "0\"");
					} else {
						printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s%s\"", new Object[] { plant.name(), variant == -1 ? "" : String.format("%d", new Object[] { Integer.valueOf(variant) }) }));
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
	
	private static int createSaplingBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			try
			{
				File jsonFile = new File(rootPath + sapling.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						printwriter.println("{");
						printwriter.println("\"parent\":\"plantmegapack:block/_sapl\",");
						printwriter.println("\"textures\":");
						printwriter.println(String.format("{\"cross\":\"plantmegapack:blocks/%s\"}", new Object[] { sapling.name() }));
						printwriter.println("}");
						printwriter.close();
						filesGenerated++;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filesGenerated;
	}
	
	private static int createWoodBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWood wood : PMPWood.values()) {
			createWoodBlockModelFile(wood.name(), rootPath, "");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Block");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "BlockSide");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "BlockAll");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Planks");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Stairs0");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Stairs1");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Stairs2");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Slab0");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Slab1");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Slab2");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Door0");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Door1");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Door2");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Door3");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "_fence_inventory");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "_fence_post");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "_fence_side");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Gate0");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Gate1");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Gate2");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Gate3");
			filesGenerated++;
			createWoodBlockModelFile(wood.name(), rootPath, "Ladder");
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createLeavesBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPLeaves leaves : PMPLeaves.values()) {
			filesGenerated += createLeavesModelFiles(rootPath, leaves);
		}
		return filesGenerated;
	}
	
	private static int createLeavesModelFiles(String rootPath, PMPLeaves leaves) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.leaves == leaves) {
				String variantName = sapling.name().toLowerCase().substring(7);
				createLeavesFile(rootPath, variantName);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static void createLeavesFile(String rootPath, String leaves) {
		try
		{
			File jsonFile = new File(rootPath + "leaves_" + leaves + ".json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					
					printwriter.println("\"parent\":\"minecraft:block/leaves\",");
					printwriter.println("\"textures\":{");
					printwriter.println(String.format("\"all\":\"plantmegapack:blocks/leaves_%s\"}", new Object[] { leaves }));
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createTreeFruitBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		int state = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.treeFruitType != null) {
				for (state = 0; state < 4; state++) {
					createTreeFruitFile(rootPath, sapling, state);
					filesGenerated++;
				}
			}
		}
		return filesGenerated;
	}
	
	private static void createTreeFruitFile(String rootPath, PMPSapling sapling, int state) {
		try
		{
			File jsonFile = new File(String.format("%streeFruit%s%d.json", new Object[] { rootPath, sapling.food.name().substring(4), Integer.valueOf(state) }));
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println(String.format("\"parent\":\"plantmegapack:block/%s%d\",", new Object[] { sapling.treeFruitType.model, Integer.valueOf(state) }));
					printwriter.println("\"textures\":{");
					if (sapling.treeFruitType.texturePerState) {
						printwriter.println(String.format("\"fruit\":\"plantmegapack:blocks/treeFruit%s%d\"}", new Object[] { sapling.food.name().substring(4), Integer.valueOf(state) }));
					} else {
						printwriter.println(String.format("\"fruit\":\"plantmegapack:blocks/treeFruit%s\"}", new Object[] { sapling.food.name().substring(4) }));
					}
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createPlanterBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlanter planter : PMPPlanter.values()) {
			for (int i = 0; i < 7; i++) {
				createPlanterModelFile(planter, rootPath, i);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static void createPlanterModelFile(PMPPlanter planter, String rootPath, int variant) {
		File jsonFile = new File(String.format("%s%s%d.json", new Object[] { rootPath, planter.name(), Integer.valueOf(variant) }));
		try
		{
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			if (jsonFile != null) {
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					switch (planter.planterType) {
					case squareBamboo: 
					case squareWood: 
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_plsw%d\",", new Object[] { Integer.valueOf(variant) }));
						break;
					case squareStone: 
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_plss%d\",", new Object[] { Integer.valueOf(variant) }));
						break;
					case squareMetal: 
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_plsm%d\",", new Object[] { Integer.valueOf(variant) }));
						break;
					case columnBamboo: 
					case columnClay: 
					case columnStone: 
					case columnWood: 
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_plco%d\",", new Object[] { Integer.valueOf(variant) }));
						break;
					default: 
						printwriter.println(String.format("\"parent\":\"plantmegapack:block/_plco%d\",", new Object[] { Integer.valueOf(variant) }));
					}
					printwriter.println("\"textures\":{");
					printwriter.println("\"bottom\":\"" + planter.textureBottom + "\",");
					printwriter.println("\"material\":\"" + planter.textureMaterial + "\",");
					printwriter.println("\"soil\":\"plantmegapack:blocks/planterSoil\",");
					printwriter.println("\"trim\":\"" + planter.textureTrim + "\"}");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createTrellisBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPTrellis trellis : PMPTrellis.values()) {
			for (int i = 0; i < 4; i++) {
				createTrellisModelFile(trellis, rootPath, i);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	private static void createTrellisModelFile(PMPTrellis trellis, String rootPath, int variant) {
		File jsonFile = new File(String.format("%s%s%d.json", new Object[] { rootPath, trellis.name(), Integer.valueOf(variant) }));
		try
		{
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			if (jsonFile != null) {
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println(String.format("\"parent\":\"plantmegapack:block/_trel%d\",", new Object[] { Integer.valueOf(variant) }));
					printwriter.println("\"textures\":{");
					printwriter.println("\"material\":\"" + trellis.textureMaterial + "\",");
					printwriter.println("\"trim\":\"" + trellis.textureTrim + "\"}");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createBambooBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			createWoodBlockModelFile(bamboo.name(), rootPath, "Block");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "BlockSide");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "BlockAll");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Stairs0");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Stairs1");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Stairs2");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Slab0");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Slab1");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Slab2");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Door0");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Door1");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Door2");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Door3");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "_fence_inventory");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "_fence_post");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "_fence_side");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Gate0");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Gate1");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Gate2");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Gate3");
			filesGenerated++;
			createWoodBlockModelFile(bamboo.name(), rootPath, "Ladder");
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createWoodBlockModelFile(String blockName, String rootPath, String filenameSuffix) {
		String fileName = "";
		if (!filenameSuffix.isEmpty()) {
			fileName = String.format("%s%s%s.json", new Object[] { rootPath, blockName, filenameSuffix });
		} else {
			fileName = String.format("%s%s.json", new Object[] { rootPath, blockName });
		}
		File jsonFile = new File(fileName);
		try
		{
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			if (jsonFile != null) {
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					if ((filenameSuffix.matches("Block")) || (filenameSuffix.isEmpty())) {
						printwriter.println("\"parent\":\"block/cube_column\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"end\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("BlockSide")) {
						printwriter.println("\"parent\":\"block/column_side\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"end\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("BlockAll")) {
						printwriter.println("\"parent\":\"block/cube_all\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"all\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Planks")) {
						printwriter.println("\"parent\":\"block/cube_all\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"all\":\"plantmegapack:blocks/" + blockName + "Planks\"}");
					} else if (filenameSuffix.matches("Stairs0")) {
						printwriter.println("\"parent\":\"block/stairs\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Stairs1")) {
						printwriter.println("\"parent\":\"block/outer_stairs\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Stairs2")) {
						printwriter.println("\"parent\":\"block/inner_stairs\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Slab0")) {
						printwriter.println("\"parent\":\"block/half_slab\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Slab\"}");
					} else if (filenameSuffix.matches("Slab1")) {
						printwriter.println("\"parent\":\"block/upper_slab\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Slab\"}");
					} else if (filenameSuffix.matches("Slab2")) {
						printwriter.println("\"parent\":\"plantmegapack:block/_dbsl\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Block1\",");
						printwriter.println("\"side\":\"plantmegapack:blocks/" + blockName + "Slab\"}");
					} else if (filenameSuffix.matches("Door0")) {
						printwriter.println("\"parent\":\"block/door_bottom\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Door1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Door2\"}");
					} else if (filenameSuffix.matches("Door1")) {
						printwriter.println("\"parent\":\"block/door_bottom_rh\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Door1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Door2\"}");
					} else if (filenameSuffix.matches("Door2")) {
						printwriter.println("\"parent\":\"block/door_top\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Door1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Door2\"}");
					} else if (filenameSuffix.matches("Door3")) {
						printwriter.println("\"parent\":\"block/door_top_rh\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"bottom\":\"plantmegapack:blocks/" + blockName + "Door1\",");
						printwriter.println("\"top\":\"plantmegapack:blocks/" + blockName + "Door2\"}");
					} else if (filenameSuffix.matches("_fence_inventory")) {
						printwriter.println("\"parent\":\"block/fence_inventory\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("_fence_post")) {
						printwriter.println("\"parent\":\"block/fence_post\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("_fence_side")) {
						printwriter.println("\"parent\":\"block/fence_side\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Gate0")) {
						printwriter.println("\"parent\":\"block/fence_gate_closed\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Gate1")) {
						printwriter.println("\"parent\":\"block/fence_gate_open\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Gate2")) {
						printwriter.println("\"parent\":\"block/wall_gate_closed\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Gate3")) {
						printwriter.println("\"parent\":\"block/fence_gate_open\",");
						printwriter.println("\"textures\":{");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Block\"}");
					} else if (filenameSuffix.matches("Ladder")) {
						printwriter.println("\"ambientocclusion\":false,");
						printwriter.println("\"textures\":{");
						printwriter.println("\"particle\":\"plantmegapack:blocks/" + blockName + "Ladder\",");
						printwriter.println("\"texture\":\"plantmegapack:blocks/" + blockName + "Ladder\"},");
						printwriter.println("\"elements\":[");
						printwriter.println("{\"from\":[0,0,15.2],");
						printwriter.println("\"to\": [16,16,15.2],");
						printwriter.println("\"shade\":false,");
						printwriter.println("\"faces\":{");
						printwriter.println("\"north\":{\"uv\":[0,0,16,16],\"texture\":\"#texture\"},");
						printwriter.println("\"south\":{\"uv\":[0,0,16,16],\"texture\":\"#texture\"}}}]");
					}
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createWallBracketBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWallBracket bracket : PMPWallBracket.values()) {
			createWallBracketModelFile(bracket, rootPath);
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createWallBracketModelFile(PMPWallBracket bracket, String rootPath) {
		File jsonFile = new File(String.format("%s%s.json", new Object[] { rootPath, bracket.name() }));
		try
		{
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			if (jsonFile != null) {
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"parent\":\"plantmegapack:block/_wabr\",");
					printwriter.println("\"textures\":{");
					printwriter.println("\"bracket\":\"plantmegapack:blocks/" + bracket.name() + "1\"}");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createHangingPlantBlockModelFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPHangingPlant hangingPlant : PMPHangingPlant.values()) {
			for (PMPBlockHangingPlant.Segment segment : PMPBlockHangingPlant.Segment.values()) {
				createHangingPlantModelFile(hangingPlant, rootPath, segment);
			}
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createHangingPlantModelFile(PMPHangingPlant hangingPlant, String rootPath, PMPBlockHangingPlant.Segment segment) {
		File jsonFile = new File(String.format("%s%s%d.json", new Object[] { rootPath, hangingPlant.name(), Integer.valueOf(segment.getMetadata()) }));
		try
		{
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			if (jsonFile != null) {
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println(String.format("\"parent\":\"plantmegapack:block/%s\",", new Object[] { segment.modelName }));
					printwriter.println("\"textures\":{");
					printwriter.println(String.format("\"plant\":\"plantmegapack:blocks/%s%d\"", new Object[] { hangingPlant.name(), Integer.valueOf(segment.getMetadata()) }));
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
}
