package plantmegapack.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import plantmegapack.block.PMPBlockHangingPlant;
import plantmegapack.block.PMPBlockLeaves;
import plantmegapack.block.PMPBlockPlanter;
import plantmegapack.block.PMPBlockSapling;
import plantmegapack.block.PMPBlockSubmerged;
import plantmegapack.block.PMPBlockTreeFruit;
import plantmegapack.block.PMPBlockTrellis;
import plantmegapack.block.PMPBlockWallBracket;
import plantmegapack.block.PMPBlockWoodDoor;
import plantmegapack.block.PMPBlockWoodFence;
import plantmegapack.block.PMPBlockWoodGate;
import plantmegapack.block.PMPBlockWoodLadder;
import plantmegapack.block.PMPBlockWoodLog;
import plantmegapack.block.PMPBlockWoodPlanks;
import plantmegapack.block.PMPBlockWoodSlabDouble;
import plantmegapack.block.PMPBlockWoodSlabHalf;
import plantmegapack.block.PMPBlockWoodStairs;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.data.PMPDataTree;
import plantmegapack.item.PMPItemWoodSlab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFood;
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
import plantmegapack.tree.PMPTree;

public class PMPBlocks
{
	private Map<String, Block> plants = new HashMap(PMPPlant.values().length);
	private Map<String, PMPBlockSapling> saplings = new HashMap();
	private Map<String, PMPBlockLeaves> leavesBlocks = new HashMap();
	private Map<String, PMPBlockTreeFruit> treeFruits = new HashMap();
	private Map<String, PMPBlockWoodLog> woodBlocks = new HashMap();
	private Map<String, PMPBlockWoodPlanks> woodPlanks = new HashMap();
	private Map<String, PMPBlockWoodStairs> woodStairs = new HashMap();
	private Map<String, PMPBlockWoodSlabHalf> woodSlabs = new HashMap();
	private Map<String, PMPBlockWoodSlabDouble> woodDoubleSlabs = new HashMap();
	private Map<String, PMPBlockWoodDoor> woodDoors = new HashMap();
	private Map<String, PMPBlockWoodFence> woodFences = new HashMap();
	private Map<String, PMPBlockWoodGate> woodGates = new HashMap();
	private Map<String, PMPBlockWoodLadder> woodLadders = new HashMap();
	private Map<String, PMPBlockWoodLog> bambooBlocks = new HashMap();
	private Map<String, PMPBlockWoodStairs> bambooStairs = new HashMap();
	private Map<String, PMPBlockWoodSlabHalf> bambooSlabs = new HashMap();
	private Map<String, PMPBlockWoodSlabDouble> bambooDoubleSlabs = new HashMap();
	private Map<String, PMPBlockWoodDoor> bambooDoors = new HashMap();
	private Map<String, PMPBlockWoodFence> bambooFences = new HashMap();
	private Map<String, PMPBlockWoodGate> bambooGates = new HashMap();
	private Map<String, PMPBlockWoodLadder> bambooLadders = new HashMap();
	private Map<String, PMPBlockPlanter> planters = new HashMap();
	private Map<String, PMPBlockTrellis> trellis = new HashMap();
	private Map<String, PMPBlockWallBracket> wallBrackets = new HashMap();
	private Map<String, PMPBlockHangingPlant> hangingPlants = new HashMap();
	public ArrayList<PMPBlockSubmerged> corals = new ArrayList();
	public ArrayList<Block> crops = new ArrayList();
	public ArrayList<PMPBlockSubmerged> kelps = new ArrayList();
	
	public PMPBlocks() {
		createPlants();
		createBambooBlocks();
		createWoodBlocks();
		createPlanters();
		createTrellisBlocks();
		createWallBrackets();
		createHangingPlants();
		createSaplings();
		createLeavesBlocks();
		createTreeFruits();
	}
	
	private void createPlants() {
		for (PMPPlant plant : PMPPlant.values()) {
			try
			{
				PMPDataPlant plantData = new PMPDataPlant(plant);
				if (plantData != null) {
					Constructor<? extends Block> bCon = plant.blockType.blockClass.getConstructor(new Class[] { PMPDataPlant.class });
					Block plantBlock = (Block)bCon.newInstance(new Object[] { plantData });
					if (plantBlock != null) {
						plantData.loadSettings();
						this.plants.put(plant.name(), plantBlock);
						PMPReference.addToRegisteredPlants(PMPPlant.hasColorVariants(plant) ? PMPColor.values().length : 1);
						PMPReference.addToRegisteredUniquePlants(1);
						if (plantData.attributes.category == PMPPlantCategory.cora) {
							this.corals.add((PMPBlockSubmerged)plantBlock);
						} else if (plantData.attributes.category == PMPPlantCategory.crop) {
							this.crops.add(plantBlock);
						} else if (plantData.attributes.blockType == PMPPlantBlockType.seaw) {
							this.kelps.add((PMPBlockSubmerged)plantBlock);
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createSaplings() {
		for (PMPSapling sapling : PMPSapling.values()) {
			try
			{
				Constructor<? extends PMPTree> bCon = sapling.treeClass.getConstructor(new Class[] { PMPSapling.class });
				PMPDataTree treeData = new PMPDataTree(sapling);
				PMPTree treeBlock = (PMPTree)bCon.newInstance(new Object[] { sapling });
				if ((treeData != null) && (treeBlock != null)) {
					PMPBlockSapling saplingBlock = new PMPBlockSapling(treeData, treeBlock);
					if (saplingBlock != null) {
						treeData.loadSettings();
						this.saplings.put(sapling.name(), saplingBlock);
						PMPReference.addToRegisteredTrees(1);
						PMPReference.addToRegisteredUniquePlants(1);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createWoodBlocks() {
		for (PMPWood wood : PMPWood.values()) {
			this.woodBlocks.put(wood.name() + "Block", new PMPBlockWoodLog(wood));
			PMPReference.addToRegisteredBlocks(1);
			this.woodPlanks.put(wood.name() + "Planks", new PMPBlockWoodPlanks(wood));
			PMPReference.addToRegisteredBlocks(1);
			this.woodStairs.put(wood.name() + "Stairs", new PMPBlockWoodStairs(wood));
			PMPReference.addToRegisteredBlocks(1);
			PMPBlockWoodSlabHalf slabHalf = new PMPBlockWoodSlabHalf(wood);
			PMPBlockWoodSlabDouble slabDouble = new PMPBlockWoodSlabDouble(wood);
			this.woodSlabs.put(wood.name() + "Slab", slabHalf);
			PMPReference.addToRegisteredBlocks(1);
			this.woodDoubleSlabs.put(wood.name() + "DoubleSlab", slabDouble);
			PMPReference.addToRegisteredBlocks(1);
			GameRegistry.registerBlock(slabHalf, PMPItemWoodSlab.class, wood.name() + "Slab", new Object[] { slabHalf, slabDouble });
			GameRegistry.registerBlock(slabDouble, PMPItemWoodSlab.class, wood.name() + "DoubleSlab", new Object[] { slabHalf, slabDouble });
			PMPReference.addToRegisteredBlocks(2);
			this.woodDoors.put(wood.name() + "Door", new PMPBlockWoodDoor(wood));
			PMPReference.addToRegisteredBlocks(1);
			this.woodFences.put(wood.name() + "Fence", new PMPBlockWoodFence(wood));
			PMPReference.addToRegisteredBlocks(1);
			this.woodGates.put(wood.name() + "Gate", new PMPBlockWoodGate(wood));
			PMPReference.addToRegisteredBlocks(1);
			this.woodLadders.put(wood.name() + "Ladder", new PMPBlockWoodLadder(wood));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createLeavesBlocks() {
		for (PMPLeaves leaves : PMPLeaves.values()) {
			this.leavesBlocks.put(leaves.name(), new PMPBlockLeaves(leaves));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createBambooBlocks() {
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			this.bambooBlocks.put(bamboo.name() + "Block", new PMPBlockWoodLog(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			this.bambooStairs.put(bamboo.name() + "Stairs", new PMPBlockWoodStairs(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
		PMPBamboo[] arrayOfPMPBamboo2 = PMPBamboo.values();
		int bambooMax = arrayOfPMPBamboo2.length;
		for (int localPMPBamboo1 = 0; localPMPBamboo1 < bambooMax; localPMPBamboo1++) {
			PMPBamboo bamboo = arrayOfPMPBamboo2[localPMPBamboo1];
			
			PMPBlockWoodSlabHalf slabHalf = new PMPBlockWoodSlabHalf(bamboo);
			PMPBlockWoodSlabDouble slabDouble = new PMPBlockWoodSlabDouble(bamboo);
			if ((slabHalf != null) && (slabDouble != null)) {
				this.bambooSlabs.put(bamboo.name() + "Slab", slabHalf);
				this.bambooDoubleSlabs.put(bamboo.name() + "DoubleSlab", slabDouble);
				GameRegistry.registerBlock(slabHalf, PMPItemWoodSlab.class, bamboo.name() + "Slab", new Object[] { slabHalf, slabDouble });
				GameRegistry.registerBlock(slabDouble, PMPItemWoodSlab.class, bamboo.name() + "DoubleSlab", new Object[] { slabHalf, slabDouble });
				PMPReference.addToRegisteredBlocks(1);
			}
		}
		arrayOfPMPBamboo2 = PMPBamboo.values();
		bambooMax = arrayOfPMPBamboo2.length;
		for (int localPMPBamboo2 = 0; localPMPBamboo2 < bambooMax; localPMPBamboo2++) {
			PMPBamboo bamboo = arrayOfPMPBamboo2[localPMPBamboo2];
			
			this.bambooDoors.put(bamboo.name() + "Door", new PMPBlockWoodDoor(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
		arrayOfPMPBamboo2 = PMPBamboo.values();
		bambooMax = arrayOfPMPBamboo2.length;
		for (int localPMPBamboo3 = 0; localPMPBamboo3 < bambooMax; localPMPBamboo3++) {
			PMPBamboo bamboo = arrayOfPMPBamboo2[localPMPBamboo3];
			
			this.bambooFences.put(bamboo.name() + "Fence", new PMPBlockWoodFence(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
		arrayOfPMPBamboo2 = PMPBamboo.values();
		bambooMax = arrayOfPMPBamboo2.length;
		for (int localPMPBamboo4 = 0; localPMPBamboo4 < bambooMax; localPMPBamboo4++) {
			PMPBamboo bamboo = arrayOfPMPBamboo2[localPMPBamboo4];
			
			this.bambooGates.put(bamboo.name() + "Gate", new PMPBlockWoodGate(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
		arrayOfPMPBamboo2 = PMPBamboo.values();
		bambooMax = arrayOfPMPBamboo2.length;
		for (int localPMPBamboo5 = 0; localPMPBamboo5 < bambooMax; localPMPBamboo5++) {
			PMPBamboo bamboo = arrayOfPMPBamboo2[localPMPBamboo5];
			
			this.bambooLadders.put(bamboo.name() + "Ladder", new PMPBlockWoodLadder(bamboo));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createPlanters() {
		for (PMPPlanter planter : PMPPlanter.values()) {
			this.planters.put(planter.name(), new PMPBlockPlanter(planter));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createTrellisBlocks() {
		for (PMPTrellis trell : PMPTrellis.values()) {
			this.trellis.put(trell.name(), new PMPBlockTrellis(trell));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createWallBrackets() {
		for (PMPWallBracket bracket : PMPWallBracket.values()) {
			this.wallBrackets.put(bracket.name(), new PMPBlockWallBracket(bracket));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createHangingPlants() {
		for (PMPHangingPlant hangingPlant : PMPHangingPlant.values()) {
			this.hangingPlants.put(hangingPlant.name(), new PMPBlockHangingPlant(hangingPlant));
			PMPReference.addToRegisteredBlocks(1);
		}
	}
	
	private void createTreeFruits() {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.treeFruitType != null) {
				this.treeFruits.put(sapling.name(), new PMPBlockTreeFruit(sapling));
				PMPReference.addToRegisteredBlocks(1);
			}
		}
	}
	
	public void preInit() {
		preInitLeaves();
	}
	
	public void initClient() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		if (renderItem == null) {
			return;
		}
		for (PMPPlant plant : PMPPlant.values()) {
			if ((plant.blockType == PMPPlantBlockType.fflo) || (plant.blockType == PMPPlantBlockType.flow)) {
				for (PMPColor color : PMPColor.values()) {
					renderItem.getItemModelMesher().register(Item.getItemFromBlock((Block)this.plants.get(plant.name())), color.ID, new ModelResourceLocation("plantmegapack:" + plant.name() + "_" + color.name(), "inventory"));
					ModelBakery.registerItemVariants(Item.getItemFromBlock((Block)this.plants.get(plant.name())), new ResourceLocation[] { new ResourceLocation("plantmegapack:" + plant.name() + "_" + color.name()) });
				}
			} else {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock((Block)this.plants.get(plant.name())), 0, new ModelResourceLocation("plantmegapack:" + plant.name(), "inventory"));
			}
		}
		PMPSapling[] arrayOfPMPSapling = PMPSapling.values();
		int plantMax = arrayOfPMPSapling.length;
		for (int localPMPPlant1 = 0; localPMPPlant1 < plantMax; localPMPPlant1++) {
			PMPSapling sapling = arrayOfPMPSapling[localPMPPlant1];
			
			PMPBlockSapling saplingBlock = (PMPBlockSapling)this.saplings.get(sapling.name());
			for (int index = 0; index < 4; index++) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(saplingBlock), index, new ModelResourceLocation("plantmegapack:" + sapling.name(), "inventory"));
			}
		}
		initWoodBlocks(renderItem);
		initBambooBlocks(renderItem);
		initPlanters(renderItem);
		initTrellis(renderItem);
		initWallBrackets(renderItem);
		initHangingPlants(renderItem);
		initLeaves(renderItem);
	}
	
	private void preInitLeaves() {
		for (PMPLeaves leaves : PMPLeaves.values()) {
			PMPBlockLeaves leavesBlock = (PMPBlockLeaves)this.leavesBlocks.get(leaves.name());
			for (PMPSapling sapling : PMPSapling.values()) {
				if (sapling.leaves == leaves) {
					String leavesName = "leaves_" + sapling.name().toLowerCase().substring(7);
					ModelBakery.registerItemVariants(Item.getItemFromBlock(leavesBlock), new ResourceLocation[] { new ResourceLocation("plantmegapack:" + leavesName) });
				}
			}
		}
	}
	
	private void initLeaves(RenderItem renderItem) {
		for (PMPLeaves leaves : PMPLeaves.values()) {
			PMPBlockLeaves leavesBlock = (PMPBlockLeaves)this.leavesBlocks.get(leaves.name());
			if (leavesBlock != null) {
				leavesBlock.init();
				for (PMPSapling sapling : PMPSapling.values()) {
					if (sapling.leaves == leaves) {
						String leavesName = "leaves_" + sapling.name().toLowerCase().substring(7);
						renderItem.getItemModelMesher().register(Item.getItemFromBlock(leavesBlock), sapling.leafMeta, new ModelResourceLocation("plantmegapack:" + leavesName, "inventory"));
						Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesBlock.getBlockColor(), new Block[] { leavesBlock });
						Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesBlock.getItemColor(), new Block[] { leavesBlock });
					}
				}
			}
		}
	}
	
	private void initWoodBlocks(RenderItem renderItem) {
		for (PMPWood wood : PMPWood.values()) {
			PMPBlockWoodLog woodBlock = (PMPBlockWoodLog)this.woodBlocks.get(wood.name() + "Block");
			if (woodBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodBlock), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Block", "inventory"));
			}
			PMPBlockWoodPlanks woodPlanks = (PMPBlockWoodPlanks)this.woodPlanks.get(wood.name() + "Planks");
			if (woodPlanks != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodPlanks), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Planks", "inventory"));
			}
			PMPBlockWoodDoor woodDoor = (PMPBlockWoodDoor)this.woodDoors.get(wood.name() + "Door");
			if (woodDoor != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodDoor), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Door", "inventory"));
			}
			PMPBlockWoodStairs woodStairs = (PMPBlockWoodStairs)this.woodStairs.get(wood.name() + "Stairs");
			if (woodStairs != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodStairs), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Stairs", "inventory"));
			}
			PMPBlockWoodSlabHalf woodSlab = (PMPBlockWoodSlabHalf)this.woodSlabs.get(wood.name() + "Slab");
			if (woodSlab != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodSlab), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Slab", "inventory"));
			}
			PMPBlockWoodFence woodFence = (PMPBlockWoodFence)this.woodFences.get(wood.name() + "Fence");
			if (woodFence != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodFence), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Fence", "inventory"));
			}
			PMPBlockWoodGate woodGate = (PMPBlockWoodGate)this.woodGates.get(wood.name() + "Gate");
			if (woodFence != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodGate), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Gate", "inventory"));
			}
			PMPBlockWoodLadder woodLadder = (PMPBlockWoodLadder)this.woodLadders.get(wood.name() + "Ladder");
			if (woodLadder != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(woodLadder), 0, new ModelResourceLocation("plantmegapack:" + wood.name() + "Ladder", "inventory"));
			}
		}
	}
	
	private void initBambooBlocks(RenderItem renderItem) {
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			PMPBlockWoodLog bambooBlock = (PMPBlockWoodLog)this.bambooBlocks.get(bamboo.name() + "Block");
			if (bambooBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooBlock), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Block", "inventory"));
			}
			PMPBlockWoodDoor bambooDoor = (PMPBlockWoodDoor)this.bambooDoors.get(bamboo.name() + "Door");
			if (bambooDoor != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooDoor), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Door", "inventory"));
			}
			PMPBlockWoodStairs bambooStairs = (PMPBlockWoodStairs)this.bambooStairs.get(bamboo.name() + "Stairs");
			if (bambooStairs != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooStairs), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Stairs", "inventory"));
			}
			PMPBlockWoodSlabHalf bambooSlab = (PMPBlockWoodSlabHalf)this.bambooSlabs.get(bamboo.name() + "Slab");
			if (bambooSlab != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooSlab), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Slab", "inventory"));
			}
			PMPBlockWoodFence bambooFence = (PMPBlockWoodFence)this.bambooFences.get(bamboo.name() + "Fence");
			if (bambooFence != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooFence), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Fence", "inventory"));
			}
			PMPBlockWoodGate bambooGate = (PMPBlockWoodGate)this.bambooGates.get(bamboo.name() + "Gate");
			if (bambooFence != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooGate), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Gate", "inventory"));
			}
			PMPBlockWoodLadder bambooLadder = (PMPBlockWoodLadder)this.bambooLadders.get(bamboo.name() + "Ladder");
			if (bambooLadder != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bambooLadder), 0, new ModelResourceLocation("plantmegapack:" + bamboo.name() + "Ladder", "inventory"));
			}
		}
	}
	
	private void initPlanters(RenderItem renderItem) {
		PMPBlockPlanter planterBlock = null;
		for (PMPPlanter planter : PMPPlanter.values()) {
			planterBlock = (PMPBlockPlanter)this.planters.get(planter.name());
			if (planterBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(planterBlock), 0, new ModelResourceLocation("plantmegapack:" + planter.name(), "inventory"));
			}
		}
	}
	
	private void initTrellis(RenderItem renderItem) {
		PMPBlockTrellis trellisBlock = null;
		for (PMPTrellis trel : PMPTrellis.values()) {
			trellisBlock = (PMPBlockTrellis)this.trellis.get(trel.name());
			if (trellisBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(trellisBlock), 0, new ModelResourceLocation("plantmegapack:" + trel.name(), "inventory"));
			}
		}
	}
	
	private void initWallBrackets(RenderItem renderItem) {
		PMPBlockWallBracket bracketBlock = null;
		for (PMPWallBracket bracket : PMPWallBracket.values()) {
			bracketBlock = (PMPBlockWallBracket)this.wallBrackets.get(bracket.name());
			if (bracketBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(bracketBlock), 0, new ModelResourceLocation("plantmegapack:" + bracket.name(), "inventory"));
			}
		}
	}
	
	private void initHangingPlants(RenderItem renderItem) {
		PMPBlockHangingPlant hangingPlantBlock = null;
		for (PMPHangingPlant hangingPlant : PMPHangingPlant.values()) {
			hangingPlantBlock = (PMPBlockHangingPlant)this.hangingPlants.get(hangingPlant.name());
			if (hangingPlantBlock != null) {
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(hangingPlantBlock), 0, new ModelResourceLocation("plantmegapack:" + hangingPlant.name(), "inventory"));
			}
		}
	}
	
	public Block getPlantBlockByName(String name) {
		return (Block)this.plants.get(name);
	}
	
	public PMPBlockSapling getSapling(PMPSapling sapling) {
		return (PMPBlockSapling)this.saplings.get(sapling.name());
	}
	
	public PMPBlockSapling getSapling(String name) {
		return (PMPBlockSapling)this.saplings.get(name);
	}
	
	public Block getWoodBlockFromName(String name) {
		return (Block)this.woodBlocks.get(name);
	}
	
	public PMPBlockLeaves getLeavesBlock(PMPLeaves leaves) {
		return (PMPBlockLeaves)this.leavesBlocks.get(leaves.name());
	}
	
	public PMPBlockTreeFruit getTreeFruit(PMPSapling sapling) {
		return (PMPBlockTreeFruit)this.treeFruits.get(sapling.name());
	}
	
	public PMPBlockTreeFruit getTreeFruitBlockFromFood(PMPFood food) {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.food == food) {
				return (PMPBlockTreeFruit)this.treeFruits.get(sapling.name());
			}
		}
		return null;
	}
	
	public PMPBlockWoodLog getBambooBlock(String unlocalizedName) {
		return (PMPBlockWoodLog)this.bambooBlocks.get(unlocalizedName + "Block");
	}
	
	public PMPBlockWoodDoor getBambooDoor(String unlocalizedName) {
		return (PMPBlockWoodDoor)this.bambooDoors.get(unlocalizedName + "Door");
	}
	
	public PMPBlockWoodFence getBambooFence(String unlocalizedName) {
		return (PMPBlockWoodFence)this.bambooFences.get(unlocalizedName + "Fence");
	}
	
	public PMPBlockWoodGate getBambooGate(String unlocalizedName) {
		return (PMPBlockWoodGate)this.bambooGates.get(unlocalizedName + "Gate");
	}
	
	public PMPBlockWoodSlabHalf getBambooSlab(String unlocalizedName) {
		return (PMPBlockWoodSlabHalf)this.bambooSlabs.get(unlocalizedName + "Slab");
	}
	
	public PMPBlockWoodStairs getBambooStairs(String unlocalizedName) {
		return (PMPBlockWoodStairs)this.bambooStairs.get(unlocalizedName + "Stairs");
	}
	
	public PMPBlockWoodLadder getBambooLadder(String unlocalizedName) {
		return (PMPBlockWoodLadder)this.bambooLadders.get(unlocalizedName + "Ladder");
	}
	
	public PMPBlockWoodLog getWoodBlock(String unlocalizedName) {
		return (PMPBlockWoodLog)this.woodBlocks.get(unlocalizedName + "Block");
	}
	
	public PMPBlockWoodPlanks getWoodPlanks(String unlocalizedName) {
		return (PMPBlockWoodPlanks)this.woodPlanks.get(unlocalizedName + "Planks");
	}
	
	public PMPBlockWoodDoor getWoodDoor(String unlocalizedName) {
		return (PMPBlockWoodDoor)this.woodDoors.get(unlocalizedName + "Door");
	}
	
	public PMPBlockWoodFence getWoodFence(String unlocalizedName) {
		return (PMPBlockWoodFence)this.woodFences.get(unlocalizedName + "Fence");
	}
	
	public PMPBlockWoodGate getWoodGate(String unlocalizedName) {
		return (PMPBlockWoodGate)this.woodGates.get(unlocalizedName + "Gate");
	}
	
	public PMPBlockWoodSlabHalf getWoodSlab(String unlocalizedName) {
		return (PMPBlockWoodSlabHalf)this.woodSlabs.get(unlocalizedName + "Slab");
	}
	
	public PMPBlockWoodStairs getWoodStairs(String unlocalizedName) {
		return (PMPBlockWoodStairs)this.woodStairs.get(unlocalizedName + "Stairs");
	}
	
	public PMPBlockWoodLadder getWoodLadder(String unlocalizedName) {
		return (PMPBlockWoodLadder)this.woodLadders.get(unlocalizedName + "Ladder");
	}
	
	public PMPBlockPlanter getPlanter(String name) {
		return (PMPBlockPlanter)this.planters.get(name);
	}
	
	public PMPBlockTrellis getTrellis(String name) {
		return (PMPBlockTrellis)this.trellis.get(name);
	}
	
	public PMPBlockWallBracket getWallBracket(String name) {
		return (PMPBlockWallBracket)this.wallBrackets.get(name);
	}
	
	public PMPBlockHangingPlant getHangingPlant(String name) {
		return (PMPBlockHangingPlant)this.hangingPlants.get(name);
	}
	
	public void resetAllPlants() {
		for (PMPPlant plant : PMPPlant.values()) {
			resetPlant(plant.name());
		}
	}
	
	public void resetPlant(String name) {
		if ((name != null) && (!name.isEmpty())) {
			IPMPPlant plantBlock = (IPMPPlant)this.plants.get(name);
			if (plantBlock != null) {
				PMPDataPlant plantData = plantBlock.getPlantData();
				if (plantData != null) {
					plantData.resetAllDefaults();
					plantData.saveSettings();
				}
			}
		}
	}
	
	public void resetAllTrees() {
		for (PMPSapling sapling : PMPSapling.values()) {
			resetTree(sapling.name());
		}
	}
	
	public void resetTree(String name) {
		if ((name != null) && (!name.isEmpty())) {
			PMPBlockSapling saplingBlock = (PMPBlockSapling)this.saplings.get(name);
			if (saplingBlock != null) {
				PMPDataTree treeData = saplingBlock.getTreeData();
				if (treeData != null) {
					treeData.resetAllDefaults();
					treeData.saveSettings();
				}
			}
		}
	}
}
