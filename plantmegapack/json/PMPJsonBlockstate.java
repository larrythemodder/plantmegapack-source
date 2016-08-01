package plantmegapack.json;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.minecraft.util.EnumFacing;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockTreeFruit;
import plantmegapack.core.PMPBlocks;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPLeaves;
import plantmegapack.object.PMPPlanter;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPTrellis;
import plantmegapack.object.PMPWallBracket;
import plantmegapack.object.PMPWood;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;

public abstract class PMPJsonBlockstate
{
	public static int createBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		filesGenerated += createPlantBlockstateFiles(rootPath);
		filesGenerated += createSaplingBlockstateFiles(rootPath);
		filesGenerated += createWoodBlockstateFiles(rootPath);
		filesGenerated += createLeavesBlockstateFiles(rootPath);
		filesGenerated += createBambooBlockstateFiles(rootPath);
		filesGenerated += createTreeFruitBlockstateFiles(rootPath);
		filesGenerated += createPlanterBlockstateFiles(rootPath);
		filesGenerated += createTrellisBlockstateFiles(rootPath);
		filesGenerated += createWallBracketBlockstateFiles(rootPath);
		filesGenerated += createHangingPlantBlockstateFiles(rootPath);
		return filesGenerated;
	}
	
	private static int createPlantBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlant plant : PMPPlant.values()) {
			try
			{
				File jsonFile = new File(rootPath + plant.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						printwriter.println("{");
						printwriter.println("\"variants\":{");
						if (plant.category == PMPPlantCategory.bamb) {
							addVariantsBamboo(printwriter, plant);
						} else if ((plant.blockType == PMPPlantBlockType.berr) || (plant.blockType == PMPPlantBlockType.crop) || (plant.blockType == PMPPlantBlockType.crpa)) {
							addVariantsAge(printwriter, plant);
						} else if (plant.blockType == PMPPlantBlockType.crpd) {
							addVariantsDoubleCrop(printwriter, plant);
						} else if (plant.blockType == PMPPlantBlockType.clim) {
							addVariantsAgeDirectional(printwriter, plant);
						} else if ((plant.blockType == PMPPlantBlockType.doub) || (plant.blockType == PMPPlantBlockType.immd)) {
							addVariantsDoublePlant(printwriter, plant);
						} else if (plant.blockType == PMPPlantBlockType.trpl) {
							addVariantsTriplePlant(printwriter, plant);
						} else if (plant.blockType == PMPPlantBlockType.epip) {
							addVariantsDirectional(printwriter, plant);
						} else if ((plant.blockType == PMPPlantBlockType.floa) || (plant.blockType == PMPPlantBlockType.grou)) {
							addVariantsRotatable(printwriter, plant);
						} else if (PMPPlant.hasColorVariants(plant)) {
							addVariantsColored(printwriter, plant);
						} else if ((plant.blockType == PMPPlantBlockType.seaw) || (plant.blockType == PMPPlantBlockType.subm)) {
							addVariantsSubmerged(printwriter, plant);
						} else if (plant.blockType == PMPPlantBlockType.vine) {
							addVariantsVine(printwriter, plant);
						} else {
							printwriter.println(String.format("\"normal\":{\"model\":\"plantmegapack:%s0\"}", new Object[] { plant.name() }));
						}
						printwriter.println("}");
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
	
	private static void addVariantsAge(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println(String.format("\"age=0\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=1\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=2\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=3\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=4\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=5\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=6\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=7\":{\"model\":\"plantmegapack:%s4\"}", new Object[] { plant.name() }));
	}
	
	private static void addVariantsBamboo(PrintWriter printwriter, PMPPlant plant) {
		int models = plant.blockType.models;
		for (int index = 0; index < models; index++) {
			printwriter.println(String.format("\"segment=%d\":{\"model\":\"plantmegapack:%s%s\"}%s", new Object[] { Integer.valueOf(index), plant.name(), Integer.valueOf(index), index == models - 1 ? "" : "," }));
		}
	}
	
	private static void addVariantsAgeDirectional(PrintWriter printwriter, PMPPlant plant) {
		int models = plant.blockType.models;
		for (int facing = 0; facing < 4; facing++) {
			String rotate;
			switch (facing) {
			case 0: 
				rotate = ",\"y\":180";
				break;
			case 1: 
				rotate = ",\"y\":270";
				break;
			case 3: 
				rotate = ",\"y\":90";
				break;
			case 2: 
			default: 
				rotate = "";
			}
			for (int index = 0; index < models; index++) {
				printwriter.println(String.format("\"age=%d,facing=%s\":{\"model\":\"plantmegapack:%s%d\"%s}%s", new Object[] { Integer.valueOf(index), EnumFacing.getHorizontal(facing).getName(), plant.name(), Integer.valueOf(index), rotate, (index == models - 1) && (facing == 3) ? "" : "," }));
			}
		}
	}
	
	private static void addVariantsColored(PrintWriter printwriter, PMPPlant plant) {
		int count = 0;
		for (PMPColor color : PMPColor.values()) {
			printwriter.println(String.format("\"variant=%s\":{\"model\":\"plantmegapack:%s_%s\"}%s", new Object[] { color.name(), plant.name(), color.name(), count == PMPColor.values().length - 1 ? "" : "," }));
			count++;
		}
	}
	
	private static void addVariantsDirectional(PrintWriter printwriter, PMPPlant plant) {
		for (int facing = 0; facing < 4; facing++) {
			String rotate;
			switch (facing) {
			case 0: 
				rotate = ",\"y\":180";
				break;
			case 1: 
				rotate = ",\"y\":270";
				break;
			case 3: 
				rotate = ",\"y\":90";
				break;
			case 2: 
			default: 
				rotate = "";
			}
			printwriter.println(String.format("\"facing=%s\":{\"model\":\"plantmegapack:%s0\"%s}%s", new Object[] { EnumFacing.getHorizontal(facing).getName(), plant.name(), rotate, facing == 3 ? "" : "," }));
		}
	}
	
	private static void addVariantsDoubleCrop(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println(String.format("\"age=0,half=lower\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=1,half=lower\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=2,half=lower\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=3,half=lower\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=4,half=lower\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=5,half=lower\":{\"model\":\"plantmegapack:%s4\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=6,half=lower\":{\"model\":\"plantmegapack:%s4\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=7,half=lower\":{\"model\":\"plantmegapack:%s6\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=0,half=upper\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=1,half=upper\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=2,half=upper\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=3,half=upper\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=4,half=upper\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=5,half=upper\":{\"model\":\"plantmegapack:%s5\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=6,half=upper\":{\"model\":\"plantmegapack:%s5\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"age=7,half=upper\":{\"model\":\"plantmegapack:%s7\"}", new Object[] { plant.name() }));
	}
	
	private static void addVariantsDoublePlant(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println(String.format("\"half=lower\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"half=upper\":{\"model\":\"plantmegapack:%s1\"}", new Object[] { plant.name() }));
	}
	
	private static void addVariantsTriplePlant(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println(String.format("\"segment=lower\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"segment=middle\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"segment=upper\":{\"model\":\"plantmegapack:%s2\"}", new Object[] { plant.name() }));
	}
	
	private static void addVariantsRotatable(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println("\"normal\":[");
		printwriter.println(String.format("{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("{\"model\":\"plantmegapack:%s0\",\"y\":90},", new Object[] { plant.name() }));
		printwriter.println(String.format("{\"model\":\"plantmegapack:%s0\",\"y\":180},", new Object[] { plant.name() }));
		printwriter.println(String.format("{\"model\":\"plantmegapack:%s0\",\"y\":270}", new Object[] { plant.name() }));
		printwriter.println("]");
	}
	
	private static void addVariantsSubmerged(PrintWriter printwriter, PMPPlant plant) {
		int models = 16;
		for (int index = 0; index < models; index++) {
			printwriter.println(String.format("\"level=%d\":{\"model\":\"plantmegapack:%s0\"}%s", new Object[] { Integer.valueOf(index), plant.name(), index == models - 1 ? "" : "," }));
		}
	}
	
	private static void addVariantsVine(PrintWriter printwriter, PMPPlant plant) {
		printwriter.println(String.format("\"east=false,north=false,south=false,up=false,west=false\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=true,up=false,west=false\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=false,up=false,west=true\":{\"model\":\"plantmegapack:%s0\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=false,up=false,west=false\":{\"model\":\"plantmegapack:%s0\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=false,up=false,west=false\":{\"model\":\"plantmegapack:%s0\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=false,up=false,west=false\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=true,up=false,west=false\":{\"model\":\"plantmegapack:%s1\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=true,up=false,west=true\":{\"model\":\"plantmegapack:%s1\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=false,up=false,west=true\":{\"model\":\"plantmegapack:%s1\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=false,up=false,west=true\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=true,up=false,west=false\":{\"model\":\"plantmegapack:%s2\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=true,up=false,west=false\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=true,up=false,west=true\":{\"model\":\"plantmegapack:%s3\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=true,up=false,west=true\":{\"model\":\"plantmegapack:%s3\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=false,up=false,west=true\":{\"model\":\"plantmegapack:%s3\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=true,up=false,west=true\":{\"model\":\"plantmegapack:%s4\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=false,up=true,west=false\":{\"model\":\"plantmegapack:%s5\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=true,up=true,west=false\":{\"model\":\"plantmegapack:%s6\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=false,up=true,west=true\":{\"model\":\"plantmegapack:%s6\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=false,up=true,west=false\":{\"model\":\"plantmegapack:%s6\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=false,up=true,west=false\":{\"model\":\"plantmegapack:%s6\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=false,up=true,west=false\":{\"model\":\"plantmegapack:%s7\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=true,up=true,west=false\":{\"model\":\"plantmegapack:%s7\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=false,south=true,up=true,west=true\":{\"model\":\"plantmegapack:%s7\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=false,up=true,west=true\":{\"model\":\"plantmegapack:%s7\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=false,up=true,west=true\":{\"model\":\"plantmegapack:%s8\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=true,up=true,west=false\":{\"model\":\"plantmegapack:%s8\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=true,up=true,west=false\":{\"model\":\"plantmegapack:%s9\"},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=false,south=true,up=true,west=true\":{\"model\":\"plantmegapack:%s9\", \"y\": 90},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=false,north=true,south=true,up=true,west=true\":{\"model\":\"plantmegapack:%s9\", \"y\": 180},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=false,up=true,west=true\":{\"model\":\"plantmegapack:%s9\", \"y\": 270},", new Object[] { plant.name() }));
		printwriter.println(String.format("\"east=true,north=true,south=true,up=true,west=true\":{\"model\":\"plantmegapack:%s10\"}", new Object[] { plant.name() }));
	}
	
	private static int createSaplingBlockstateFiles(String rootPath) {
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
						printwriter.println("\"variants\":{");
						printwriter.println(String.format("\"normal\":{\"model\":\"plantmegapack:%s\"},", new Object[] { sapling.name() }));
						printwriter.println(String.format("\"stage=0\":{\"model\":\"plantmegapack:%s\"},", new Object[] { sapling.name() }));
						printwriter.println(String.format("\"stage=1\":{\"model\":\"plantmegapack:%s\"}", new Object[] { sapling.name() }));
						printwriter.println("}");
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
	
	private static int createLeavesBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPLeaves leaves : PMPLeaves.values()) {
			filesGenerated += createLeavesFile(rootPath, leaves);
		}
		return filesGenerated;
	}
	
	private static int createLeavesFile(String rootPath, PMPLeaves leaves) {
		int filesGenerated = 0;
		int variantCount = 0;
		int variantTotal = PMPLeaves.getVariantCount(leaves);
		
		String terminator = "";
		try
		{
			File jsonFile = new File(rootPath + leaves.name() + ".json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					for (PMPSapling sapling : PMPSapling.values()) {
						if (sapling.leaves == leaves) {
							variantCount++;
							String variantName = sapling.name().toLowerCase().substring(7);
							printwriter.println(String.format("\"check_decay=false,decayable=false,variant=%d\":{\"model\":\"plantmegapack:leaves_%s\"},", new Object[] { Integer.valueOf(sapling.leafMeta), variantName }));
							printwriter.println(String.format("\"check_decay=false,decayable=true,variant=%d\":{\"model\":\"plantmegapack:leaves_%s\"},", new Object[] { Integer.valueOf(sapling.leafMeta), variantName }));
							printwriter.println(String.format("\"check_decay=true,decayable=false,variant=%d\":{\"model\":\"plantmegapack:leaves_%s\"},", new Object[] { Integer.valueOf(sapling.leafMeta), variantName }));
							terminator = variantCount == variantTotal ? "" : ",";
							printwriter.println(String.format("\"check_decay=true,decayable=true,variant=%d\":{\"model\":\"plantmegapack:leaves_%s\"}%s", new Object[] { Integer.valueOf(sapling.leafMeta), variantName, terminator }));
						}
					}
					printwriter.println("}");
					printwriter.println("}");
					printwriter.close();
					filesGenerated++;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return filesGenerated;
	}
	
	private static int createTreeFruitBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.treeFruitType != null) {
				createTreeFruitFile(rootPath, sapling);
				filesGenerated++;
			}
		}
		return filesGenerated;
	}
	
	public static void createTreeFruitFile(String rootPath, PMPSapling sapling) {
		PMPBlockTreeFruit blockTreeFruit = PlantMegaPack.blocks.getTreeFruit(sapling);
		try
		{
			String blockName = blockTreeFruit.getUnlocalizedNameModified();
			File jsonFile = new File(rootPath + blockName + ".json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"age=0,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=0,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=0,facing=south\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=0,facing=west\":{\"model\":\"plantmegapack:%s0\",\"y\":90},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=0,facing=north\":{\"model\":\"plantmegapack:%s0\",\"y\":180},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=0,facing=east\":{\"model\":\"plantmegapack:%s0\",\"y\":270},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=south\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=west\":{\"model\":\"plantmegapack:%s1\",\"y\":90},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=north\":{\"model\":\"plantmegapack:%s1\",\"y\":180},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=1,facing=east\":{\"model\":\"plantmegapack:%s1\",\"y\":270},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=south\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=west\":{\"model\":\"plantmegapack:%s2\",\"y\":90},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=north\":{\"model\":\"plantmegapack:%s2\",\"y\":180},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=2,facing=east\":{\"model\":\"plantmegapack:%s2\",\"y\":270},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=south\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=west\":{\"model\":\"plantmegapack:%s3\",\"y\":90},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=north\":{\"model\":\"plantmegapack:%s3\",\"y\":180},", new Object[] { blockName }));
					printwriter.println(String.format("\"age=3,facing=east\":{\"model\":\"plantmegapack:%s3\",\"y\":270}", new Object[] { blockName }));
				}
				printwriter.println("}");
				printwriter.println("}");
				printwriter.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int createPlanterBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPPlanter planter : PMPPlanter.values()) {
			try
			{
				File jsonFile = new File(rootPath + planter.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						filesGenerated++;
						printwriter.println("{");
						printwriter.println("\"variants\":{");
						printwriter.println(String.format("\"east=false,north=false,south=false,west=false\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=true,south=false,west=false\":{\"model\":\"plantmegapack:%s1\",\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=false,south=false,west=false\":{\"model\":\"plantmegapack:%s1\",\"y\":90,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=false,south=true,west=false\":{\"model\":\"plantmegapack:%s1\",\"y\":180,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=false,south=false,west=true\":{\"model\":\"plantmegapack:%s1\",\"y\":270,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=true,south=false,west=false\":{\"model\":\"plantmegapack:%s2\",\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=false,south=true,west=false\":{\"model\":\"plantmegapack:%s2\",\"y\":90,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=false,south=true,west=true\":{\"model\":\"plantmegapack:%s2\",\"y\":180,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=true,south=false,west=true\":{\"model\":\"plantmegapack:%s2\",\"y\":270,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=true,south=true,west=false\":{\"model\":\"plantmegapack:%s3\",\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=false,south=false,west=true\":{\"model\":\"plantmegapack:%s3\",\"y\":90,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=true,south=true,west=false\":{\"model\":\"plantmegapack:%s4\",\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=false,south=true,west=true\":{\"model\":\"plantmegapack:%s4\",\"y\":90,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=false,north=true,south=true,west=true\":{\"model\":\"plantmegapack:%s4\",\"y\":180,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=true,south=false,west=true\":{\"model\":\"plantmegapack:%s4\",\"y\":270,\"uvlock\":true},", new Object[] { planter.name() }));
						printwriter.println(String.format("\"east=true,north=true,south=true,west=true\":{\"model\":\"plantmegapack:%s5\",\"uvlock\":true}", new Object[] { planter.name() }));
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
		return filesGenerated;
	}
	
	private static int createTrellisBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPTrellis trellis : PMPTrellis.values()) {
			try
			{
				File jsonFile = new File(rootPath + trellis.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						filesGenerated++;
						printwriter.println("{");
						printwriter.println("\"variants\":{");
						printwriter.println(String.format("\"connected=0,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=0,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=0,facing=north\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=0,facing=south\":{\"model\":\"plantmegapack:%s0\",\"y\":180},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=0,facing=west\":{\"model\":\"plantmegapack:%s0\",\"y\":270},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=0,facing=east\":{\"model\":\"plantmegapack:%s0\",\"y\":90},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=north\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=south\":{\"model\":\"plantmegapack:%s1\",\"y\":180},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=west\":{\"model\":\"plantmegapack:%s1\",\"y\":270},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=1,facing=east\":{\"model\":\"plantmegapack:%s1\",\"y\":90},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=north\":{\"model\":\"plantmegapack:%s2\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=south\":{\"model\":\"plantmegapack:%s2\",\"y\":180},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=west\":{\"model\":\"plantmegapack:%s2\",\"y\":270},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=2,facing=east\":{\"model\":\"plantmegapack:%s2\",\"y\":90},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=up\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=down\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=north\":{\"model\":\"plantmegapack:%s3\"},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=south\":{\"model\":\"plantmegapack:%s3\",\"y\":180},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=west\":{\"model\":\"plantmegapack:%s3\",\"y\":270},", new Object[] { trellis.name() }));
						printwriter.println(String.format("\"connected=3,facing=east\":{\"model\":\"plantmegapack:%s3\",\"y\":90}", new Object[] { trellis.name() }));
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
		return filesGenerated;
	}
	
	private static int createWoodBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWood wood : PMPWood.values()) {
			createWoodBlockFile(rootPath, wood.name());
			filesGenerated++;
			createWoodPlanksFile(rootPath, wood.name());
			filesGenerated++;
			createWoodStairsFile(rootPath, wood.name());
			filesGenerated++;
			createWoodDoorFile(rootPath, wood.name());
			filesGenerated++;
			createWoodSlabFile(rootPath, wood.name());
			filesGenerated++;
			createWoodDoubleSlabFile(rootPath, wood.name());
			filesGenerated++;
			createWoodFenceFile(rootPath, wood.name());
			filesGenerated++;
			createWoodGateFile(rootPath, wood.name());
			filesGenerated++;
			createWoodLadderFile(rootPath, wood.name());
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createBambooBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			createWoodBlockFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodStairsFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodDoorFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodSlabFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodDoubleSlabFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodFenceFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodGateFile(rootPath, bamboo.name());
			filesGenerated++;
			createWoodLadderFile(rootPath, bamboo.name());
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static void createWoodBlockFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Block.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"axis=y\":{\"model\":\"plantmegapack:%sBlock\"},", new Object[] { name }));
					printwriter.println(String.format("\"axis=z\":{\"model\":\"plantmegapack:%sBlockSide\"},", new Object[] { name }));
					printwriter.println(String.format("\"axis=x\":{\"model\":\"plantmegapack:%sBlockSide\", \"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"axis=none\":{\"model\":\"plantmegapack:%sBlockAll\"}", new Object[] { name }));
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
	
	private static void createWoodPlanksFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Planks.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"normal\":{\"model\":\"plantmegapack:%sPlanks\"}", new Object[] { name }));
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
	
	private static void createWoodStairsFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Stairs.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"facing=east,half=bottom,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=bottom,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=bottom,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=bottom,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=bottom,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=bottom,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=bottom,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=bottom,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=bottom,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=bottom,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=bottom,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=bottom,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=bottom,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=bottom,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=bottom,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=bottom,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=bottom,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=bottom,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=bottom,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=bottom,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=top,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"x\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=top,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=top,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=top,shape=straight\":{\"model\":\"plantmegapack:%sStairs0\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=top,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=top,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=top,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=top,shape=outer_right\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=top,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=top,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=top,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=top,shape=outer_left\":{\"model\":\"plantmegapack:%sStairs1\",\"x\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=top,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=top,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=top,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=top,shape=inner_right\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=top,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=top,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=top,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=top,shape=inner_left\":{\"model\":\"plantmegapack:%sStairs2\",\"x\":180,\"uvlock\":true}", new Object[] { name }));
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
	
	private static void createWoodDoorFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Door.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"facing=east,half=lower,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=left,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=right,open=false,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=left,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=right,open=true,powered=false\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor1\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=lower,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=lower,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=lower,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=lower,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor0\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=left,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=right,open=false,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor3\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=left,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,half=upper,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":270},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,half=upper,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,half=upper,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,half=upper,hinge=right,open=true,powered=true\":{\"model\":\"plantmegapack:%sDoor2\",\"y\":180}", new Object[] { name }));
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
	
	private static void createWoodSlabFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Slab.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"half=bottom,variant=false\":{\"model\":\"plantmegapack:%sSlab0\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=bottom,variant=true\":{\"model\":\"plantmegapack:%sSlab0\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=top,variant=false\":{\"model\":\"plantmegapack:%sSlab1\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=top,variant=true\":{\"model\":\"plantmegapack:%sSlab1\"}", new Object[] { name }));
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
	
	private static void createWoodDoubleSlabFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "DoubleSlab.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"half=bottom,variant=false\":{\"model\":\"plantmegapack:%sSlab2\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=bottom,variant=true\":{\"model\":\"plantmegapack:%sSlab2\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=top,variant=false\":{\"model\":\"plantmegapack:%sSlab2\"},", new Object[] { name }));
					printwriter.println(String.format("\"half=top,variant=true\":{\"model\":\"plantmegapack:%sSlab2\"}", new Object[] { name }));
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
	
	private static void createWoodFenceFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Fence.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"multipart\":[");
					printwriter.println(String.format("{\"apply\":{\"model\":\"plantmegapack:%s_fence_post\"}},", new Object[] { name }));
					printwriter.println("{\"when\":{\"north\":\"true\"},");
					printwriter.println(String.format(" \"apply\":{\"model\":\"plantmegapack:%s_fence_side\",\"uvlock\":true}", new Object[] { name }));
					printwriter.println("},");
					printwriter.println("{\"when\":{\"east\":\"true\"},");
					printwriter.println(String.format(" \"apply\":{\"model\":\"plantmegapack:%s_fence_side\",\"y\":90,\"uvlock\":true}", new Object[] { name }));
					printwriter.println("},");
					printwriter.println("{\"when\":{\"south\":\"true\"},");
					printwriter.println(String.format(" \"apply\":{\"model\":\"plantmegapack:%s_fence_side\",\"y\":180,\"uvlock\":true}", new Object[] { name }));
					printwriter.println("},");
					printwriter.println("{\"when\":{\"west\":\"true\"},");
					printwriter.println(String.format(" \"apply\":{\"model\":\"plantmegapack:%s_fence_side\",\"y\":270,\"uvlock\":true}", new Object[] { name }));
					printwriter.println("}");
					printwriter.println("]");
					printwriter.println("}");
					printwriter.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createWoodGateFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Gate.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"facing=south,in_wall=false,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=false,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate0\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=false,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate0\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=false,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate0\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=false,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=false,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate1\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=false,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate1\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=false,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate1\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=true,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=true,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate2\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=true,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate2\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=true,open=false,powered=false\":{\"model\":\"plantmegapack:%sGate2\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=true,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=true,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate3\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=true,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate3\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=true,open=true,powered=false\":{\"model\":\"plantmegapack:%sGate3\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=false,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate0\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=false,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate0\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=false,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate0\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=false,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate0\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=false,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate1\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=false,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate1\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=false,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate1\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=false,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate1\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=true,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate2\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=true,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate2\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=true,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate2\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=true,open=false,powered=true\":{\"model\":\"plantmegapack:%sGate2\",\"y\":270,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south,in_wall=true,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate3\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west,in_wall=true,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate3\",\"y\":90,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=north,in_wall=true,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate3\",\"y\":180,\"uvlock\":true},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east,in_wall=true,open=true,powered=true\":{\"model\":\"plantmegapack:%sGate3\",\"y\":270,\"uvlock\":true}", new Object[] { name }));
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
	
	private static void createWoodLadderFile(String rootPath, String name) {
		try
		{
			File jsonFile = new File(rootPath + name + "Ladder.json");
			if (jsonFile != null) {
				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}
				PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				if (printwriter != null) {
					printwriter.println("{");
					printwriter.println("\"variants\":{");
					printwriter.println(String.format("\"facing=north\":{\"model\":\"plantmegapack:%sLadder\"},", new Object[] { name }));
					printwriter.println(String.format("\"facing=east\":{\"model\":\"plantmegapack:%sLadder\",\"y\":90},", new Object[] { name }));
					printwriter.println(String.format("\"facing=south\":{\"model\":\"plantmegapack:%sLadder\",\"y\":180},", new Object[] { name }));
					printwriter.println(String.format("\"facing=west\":{\"model\":\"plantmegapack:%sLadder\",\"y\":270}", new Object[] { name }));
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
	
	private static int createWallBracketBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPWallBracket bracket : PMPWallBracket.values()) {
			try
			{
				File jsonFile = new File(rootPath + bracket.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						filesGenerated++;
						printwriter.println("{");
						printwriter.println("\"variants\":{");
						printwriter.println(String.format("\"facing=north\":{\"model\":\"plantmegapack:%s\"},", new Object[] { bracket.name() }));
						printwriter.println(String.format("\"facing=east\":{\"model\":\"plantmegapack:%s\",\"y\":90},", new Object[] { bracket.name() }));
						printwriter.println(String.format("\"facing=south\":{\"model\":\"plantmegapack:%s\",\"y\":180},", new Object[] { bracket.name() }));
						printwriter.println(String.format("\"facing=west\":{\"model\":\"plantmegapack:%s\",\"y\":270}", new Object[] { bracket.name() }));
						printwriter.println("}");
						printwriter.println("}");
						printwriter.close();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			filesGenerated++;
		}
		return filesGenerated;
	}
	
	private static int createHangingPlantBlockstateFiles(String rootPath) {
		int filesGenerated = 0;
		for (PMPHangingPlant hangingPlant : PMPHangingPlant.values()) {
			try {
				File jsonFile = new File(rootPath + hangingPlant.name() + ".json");
				if (jsonFile != null) {
					if (!jsonFile.exists()) {
						jsonFile.createNewFile();
					}
					PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
					if (printwriter != null) {
						filesGenerated++;
						printwriter.println("{");
						printwriter.println("\"variants\":{");
						printwriter.println(String.format("\"segment=top\":{\"model\":\"plantmegapack:%s0\"},", new Object[] { hangingPlant.name() }));
						printwriter.println(String.format("\"segment=middle\":{\"model\":\"plantmegapack:%s1\"},", new Object[] { hangingPlant.name() }));
						printwriter.println(String.format("\"segment=bottom\":{\"model\":\"plantmegapack:%s2\"}", new Object[] { hangingPlant.name() }));
						printwriter.println("}");
						printwriter.println("}");
						printwriter.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			filesGenerated++;
		}
		return filesGenerated;
	}
}
