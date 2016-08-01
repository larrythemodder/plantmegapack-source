package plantmegapack.core;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import plantmegapack.PlantMegaPack;
import plantmegapack.item.PMPItemFood;
import plantmegapack.item.PMPItemFragment;
import plantmegapack.item.PMPItemRootMedicinal;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPFoodType;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPStem;
import plantmegapack.object.PMPTrellis;
import plantmegapack.object.PMPWallBracket;
import plantmegapack.object.PMPWood;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;

public class PMPRecipes
{
	public void init() {
		int recipes = CraftingManager.getInstance().getRecipeList().size();
		initBambooBlocks();
		//initBook();
		initBowlFoods();
		initCoralFragments();
		initCorn();
		initDesserts();
		initFlowers();
		initFoodWraps();
		initFruitBowls();
		initFruitDrinks();
		initFurnaceRecipes();
		initHangingPlants();
		initJelly();
		initLicorice();
		initPeanutButter();
		initPlanters();
		initPowders();
		initRice();
		initSalves();
		initSandwiches();
		initSaplings();
		initStirFry();
		initStuffedPeppers();
		initTeaDrinks();
		initTortillas();
		initTrellis();
		initUniquePlantRecipes();
		initWallBrackets();
		initWoodBlocks();
		PMPReference.addToRegisteredRecipes(CraftingManager.getInstance().getRecipeList().size() - recipes);
	}
	
	private void initBambooBlocks() {
		for (PMPBamboo bamboo : PMPBamboo.values()) {
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0), new Object[] { "xxx", "xxx", "xxx", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooSlab(bamboo.name()), 6, 0), new Object[] { "   ", "xxx", "   ", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooStairs(bamboo.name()), 4, 0), new Object[] { "  x", " xx", "xxx", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooDoor(bamboo.name()), 3, 0), new Object[] { "xx ", "xx ", "xx ", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooFence(bamboo.name()), 3, 0), new Object[] { "   ", "xyx", "xyx", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0), Character.valueOf('y'), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooGate(bamboo.name()), 1, 0), new Object[] { "   ", "xyx", "xyx", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()), 1, 0), Character.valueOf('y'), new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooLadder(bamboo.name()), 1, 0), new Object[] { "x x", "xxx", "x x", Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()), 1, 0) }));
		}
	}
	
	/*private void initBook() {
		for (PMPFlower flower : PMPFlower.values()) {
			for (PMPStem stem : PMPStem.values()) {
				for (PMPRootMedicinal root : PMPRootMedicinal.values()) {
					createBookRecipe(flower, stem, root);
				}
			}
		}
	}
	
	private void createBookRecipe(PMPFlower flower, PMPStem stem, PMPRootMedicinal root) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getBookItem(), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFlowerItem(flower), 1, 0), new ItemStack(PlantMegaPack.items
			.getStemItem(stem), 1, 0), new ItemStack(PlantMegaPack.items
			.getMedicinalRootItem(), 1, root.ID), new ItemStack(Items.BOOK, 1, 0) }));
	}*/
	
	private void initBowlFoods() {
		createBowlCerealRecipe(PMPFood.foodQuinoaSeeds, PMPFood.foodQuinoaCereal);
		
		createBowlDessertRecipe(PMPFood.foodAvocado, PMPFood.foodAvocadoPudding);
		createBowlDessertRecipe(PMPFood.foodElderberry, PMPFood.foodElderberrySorbet);
		createBowlDessertRecipe(PMPFood.foodGrapefruit, PMPFood.foodGrapefruitSorbet);
		createBowlDessertRecipe(PMPFood.foodSnowberry, PMPFood.foodSnowberryCustard);
		
		createBowlSoupRecipe(PMPFood.foodBroccoli, PMPFood.foodBroccoliSoup);
		createBowlSoupRecipe(PMPFood.foodHorseradish, PMPFood.foodHorseradishSoup);
		createBowlSoupRecipe(PMPFood.foodLentil, PMPFood.foodLentilSoup);
		createBowlSoupRecipe(PMPFood.foodMozuku, PMPFood.foodMozukuSoup);
		createBowlSoupRecipe(PMPFood.foodTomato, PMPFood.foodTomatoSoup);
		for (PMPPlant plant1 : PMPPlant.values()) {
			if ((plant1.category == PMPPlantCategory.fung) && (!plant1.isPoisonPlant())) {
				Item item1 = Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName(plant1.name()));
				createBowlStewRecipe(item1, Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), Items.MUSHROOM_STEW);
				createBowlStewRecipe(item1, Item.getItemFromBlock(Blocks.RED_MUSHROOM), Items.MUSHROOM_STEW);
				for (PMPPlant plant2 : PMPPlant.values()) {
					if ((plant2.category == PMPPlantCategory.fung) && (!plant2.isPoisonPlant())) {
						createBowlStewRecipe(item1, Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName(plant2.name())), Items.MUSHROOM_STEW);
					}
				}
			}
		}
	}
	
	private void createBowlCerealRecipe(PMPFood input, PMPFood output) {
		Item ingredient = PlantMegaPack.items.getFoodItem(input);
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { new ItemStack(Items.BOWL, 1, 0), new ItemStack(ingredient), new ItemStack(ingredient), new ItemStack(ingredient) }));
	}
	
	private void createBowlDessertRecipe(PMPFood input, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { " w ", "xyx", " z ", 
		
			Character.valueOf('w'), new ItemStack(Items.SUGAR, 1, 0), 
			Character.valueOf('x'), new ItemStack(PlantMegaPack.items.getFoodItem(input), 1, 0), 
			Character.valueOf('y'), new ItemStack(Items.MILK_BUCKET, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.BOWL, 1, 0) }));
	}
	
	private void createBowlSoupRecipe(PMPFood input, PMPFood output) {
		Item ingredient = PlantMegaPack.items.getFoodItem(input);
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { new ItemStack(Items.BOWL, 1, 0), new ItemStack(ingredient), new ItemStack(ingredient) }));
	}
	
	private void createBowlStewRecipe(Item ingredient1, Item ingredient2, Item output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(Items.BOWL, 1, 0), new ItemStack(ingredient1), new ItemStack(ingredient2) }));
	}
	
	private void initCoralFragments() {
		PMPPlant[] arrayOfPMPPlant = PMPPlant.values();
		int i = arrayOfPMPPlant.length;
		for (int localPMPPlant1 = 0; localPMPPlant1 < i; localPMPPlant1++) {
			PMPPlant plant = arrayOfPMPPlant[localPMPPlant1];
			if (plant.category == PMPPlantCategory.cora) {
				createCoralFragmentRecipe(plant);
			}
		}
		PMPFragment[] arrayOfPMPFragment = PMPFragment.values();
		int localPMPPlant1 = arrayOfPMPFragment.length;
		for (int plant = 0; plant < localPMPPlant1; plant++) {
			PMPFragment fragment = arrayOfPMPFragment[plant];
			
			PMPItemFragment coralFragmentItem = PlantMegaPack.items.getCoralFragmentItem(fragment);
			
			CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.ARROW, 1, 0), new Object[] {
					new ItemStack(coralFragmentItem, 1, 0), new ItemStack(Items.STICK, 1, 0), new ItemStack(Items.FEATHER, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.DYE, 1, PMPColor.getColorFromID(fragment.ID).dyeID), new Object[] {
					new ItemStack(coralFragmentItem, 1, 0) }));
		}
	}
	
	private void createCoralFragmentRecipe(PMPPlant plant) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getCoralFragmentItem(PMPFragment.getFragmentFromID(plant.flowerID)), 2, 0), new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(plant.name()), 1, 0) }));
	}
	
	private void initCorn() {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour), 1, 0), new Object[] {
				new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCorn)) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortilla), 1, 4), new Object[] {
				" x ", "x x", " x ", Character.valueOf('x'), PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread), 1, 0), new Object[] {
				"   ", "   ", "xxx", Character.valueOf('x'), PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour) }));
	}
	
	private void initDesserts() {
		createDessertCookieRecipe(PMPFood.foodPeanutButter, PMPFood.foodCookiePeanutButter);
		
		createDessertRecipe(PMPFood.foodApricot, PMPFood.foodApricotGalette);
		createDessertRecipe(PMPFood.foodBeautyberry, PMPFood.foodBeautyberryTurnover);
		createDessertRecipe(PMPFood.foodBlackberry, PMPFood.foodBlackberryDanish);
		createDessertRecipe(PMPFood.foodGooseberry, PMPFood.foodGooseberryCobbler);
		createDessertRecipe(PMPFood.foodGrapesPurple, PMPFood.foodGrapeTart);
		createDessertRecipe(PMPFood.foodHuckleberry, PMPFood.foodHuckleberryTart);
		createDessertRecipe(PMPFood.foodMango, PMPFood.foodMangoBrulee);
		createDessertRecipe(PMPFood.foodOrangeberry, PMPFood.foodOrangeberrySquare);
		createDessertRecipe(PMPFood.foodPear, PMPFood.foodPearCrumble);
		createDessertRecipe(PMPFood.foodPlum, PMPFood.foodPlumTart);
		createDessertRecipe(PMPFood.foodPorcelainberry, PMPFood.foodPorcelainberryTart);
		createDessertRecipe(PMPFood.foodStrawberry, PMPFood.foodStrawberryDelight);
		
		createDessertMuffinRecipes(PMPFood.foodBlueberry, PMPFood.foodBlueberryMuffin);
		createDessertMuffinRecipes(PMPFood.foodOrange, PMPFood.foodOrangeMuffin);
		
		createDessertPieRecipe(Items.APPLE, PMPFood.foodApplePie);
		createDessertPieRecipe(PMPFood.foodAppleGreen, PMPFood.foodApplePie);
		createDessertPieRecipe(PMPFood.foodAppleYellow, PMPFood.foodApplePie);
		createDessertPieRecipe(PMPFood.foodBanana, PMPFood.foodBananaCreamPie);
		createDessertPieRecipe(PMPFood.foodCherry, PMPFood.foodCherryPie);
		createDessertPieRecipe(PMPFood.foodCoconut, PMPFood.foodCoconutCreamPie);
		createDessertPieRecipe(PMPFood.foodKiwi, PMPFood.foodKiwiPie);
		createDessertPieRecipe(PMPFood.foodPineapple, PMPFood.foodPineappleCake);
	}
	
	private void createDessertRecipe(PMPFood input, PMPFood output) {
		Item ingredient = PlantMegaPack.items.getFoodItem(input);
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { "   ", "xwx", "yzy", 
			Character.valueOf('w'), new ItemStack(ingredient, 1, 0), 
			Character.valueOf('x'), new ItemStack(Items.SUGAR, 1, 0), 
			Character.valueOf('y'), new ItemStack(Items.WHEAT, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.EGG, 1, 0) }));
	}
	
	private void createDessertCookieRecipe(PMPFood input, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 6, 0), new Object[] { "   ", "zyz", "   ", 
			Character.valueOf('y'), new ItemStack(PlantMegaPack.items.getFoodItem(input), 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.WHEAT, 1, 0) }));
	}
	
	private void createDessertMuffinRecipes(PMPFood input, PMPFood output) {
		createDessertMuffinRecipe(input, Items.BREAD, output);
		createDessertMuffinRecipe(input, PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread), output);
	}
	
	private void createDessertMuffinRecipe(PMPFood input, Item bread, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 4, 0), new Object[] { "   ", "xyx", "   ", 
		
			Character.valueOf('x'), new ItemStack(PlantMegaPack.items.getFoodItem(input), 1, 0), 
			Character.valueOf('y'), new ItemStack(bread, 1, 0) }));
	}
	
	private void createDessertPieRecipe(PMPFood input, PMPFood output) {
		createDessertPieRecipe(PlantMegaPack.items.getFoodItem(input), output);
	}
	
	private void createDessertPieRecipe(Item input, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { "   ", "xy ", "z  ", 
			Character.valueOf('x'), new ItemStack(input, 1, 0), 
			Character.valueOf('y'), new ItemStack(Items.SUGAR, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.EGG, 1, 0) }));
	}
	
	private void initFlowers() {
		for (PMPFlower flower : PMPFlower.values()) {
			CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.DYE, 1, flower.dyeMeta), new Object[] { new ItemStack(PlantMegaPack.items.getFlowerItem(flower), 1, 0) }));
		}
	}
	
	private void initFoodWraps() {
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour), 1, 0), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCorn), 3, 0));
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.saltwaterKelpGiantGRN.name()), 1, 0), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 3, 0));
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.saltwaterKelpGiantYEL.name()), 1, 0), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 3, 0));
		for (PMPFood food1 : PMPFood.values()) {
			if (food1.foodType == PMPFoodType.vege) {
				for (PMPFood food2 : PMPFood.values()) {
					if (food2.foodType == PMPFoodType.vege) {
						createFoodWrapRecipes(food1, food2);
					}
				}
			}
		}
	}
	
	private void createEmptyFoodWrapRecipe(ItemStack input, ItemStack output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, new Object[] { "   ", "x x", " x ", 
			Character.valueOf('x'), input }));
	}
	
	private void createFoodWrapRecipes(PMPFood input1, PMPFood input2) {
		createFoodWrapRecipe(input1, input2, Items.COOKED_FISH, PMPFood.foodWrapCorn, PMPFood.foodWrapCornFish);
		
		createFoodWrapRecipe(input1, input2, Items.COOKED_BEEF, PMPFood.foodWrapCorn, PMPFood.foodWrapCornMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_CHICKEN, PMPFood.foodWrapCorn, PMPFood.foodWrapCornMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_MUTTON, PMPFood.foodWrapCorn, PMPFood.foodWrapCornMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_PORKCHOP, PMPFood.foodWrapCorn, PMPFood.foodWrapCornMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_RABBIT, PMPFood.foodWrapCorn, PMPFood.foodWrapCornMeat);
		
		createFoodWrapRecipe(input1, input2, PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice), PMPFood.foodWrapCorn, PMPFood.foodWrapCornRice);
		
		createFoodWrapRecipe(input1, input2, Items.COOKED_FISH, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedFish);
		
		createFoodWrapRecipe(input1, input2, Items.COOKED_BEEF, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_CHICKEN, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_MUTTON, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_PORKCHOP, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedMeat);
		createFoodWrapRecipe(input1, input2, Items.COOKED_RABBIT, PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedMeat);
		
		createFoodWrapRecipe(input1, input2, PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice), PMPFood.foodWrapSeaweed, PMPFood.foodWrapSeaweedRice);
	}
	
	private void createFoodWrapRecipe(PMPFood input1, PMPFood input2, Item meat, PMPFood wrapInput, PMPFood wrapOutput) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(wrapOutput), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(input1), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(input2), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(wrapInput), 1, 0), new ItemStack(meat, 1, 0) }));
	}
	
	private void initFruitBowls() {
		for (PMPFood fruit1 : PMPFood.values()) {
			if ((fruit1.foodType == PMPFoodType.berr) || (fruit1.foodType == PMPFoodType.frui)) {
				for (PMPFood fruit2 : PMPFood.values()) {
					if ((fruit2.foodType == PMPFoodType.berr) || (fruit2.foodType == PMPFoodType.frui)) {
						for (PMPFood fruit3 : PMPFood.values()) {
							if ((fruit3.foodType == PMPFoodType.berr) || (fruit3.foodType == PMPFoodType.frui)) {
								createFruitBowlRecipe(fruit1, fruit2, fruit3, PlantMegaPack.items.getFoodItem(PMPFood.foodFruitBowl));
							}
						}
					}
				}
			}
		}
	}
	
	private void createFruitBowlRecipe(PMPFood input1, PMPFood input2, PMPFood input3, PMPItemFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(Items.BOWL, 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(input1)), new ItemStack(PlantMegaPack.items.getFoodItem(input2)), new ItemStack(PlantMegaPack.items.getFoodItem(input3)) }));
	}
	
	private void initFruitDrinks() {
		createFruitDrinkRecipe(PMPFood.foodAcaiberry, PMPFood.foodAcaiberrySparkler);
		createFruitDrinkRecipe(PMPFood.foodBeautyberry, PMPFood.foodBeautyberryBlazer);
		createFruitDrinkRecipe(PMPFood.foodBlackberry, PMPFood.foodBlackberryTumbler);
		createFruitDrinkRecipe(PMPFood.foodBlueberry, PMPFood.foodBlueberrySlushie);
		createFruitDrinkRecipe(PMPFood.foodElderberry, PMPFood.foodElderberrySpritzer);
		createFruitDrinkRecipe(PMPFood.foodGooseberry, PMPFood.foodGooseberryShake);
		createFruitDrinkRecipe(PMPFood.foodHuckleberry, PMPFood.foodHuckleberryBubbler);
		createFruitDrinkRecipe(PMPFood.foodMulberry, PMPFood.foodMulberryDazzler);
		createFruitDrinkRecipe(PMPFood.foodOrangeberry, PMPFood.foodOrangeberryWhip);
		createFruitDrinkRecipe(PMPFood.foodPorcelainberry, PMPFood.foodPorcelainberryMixer);
		createFruitDrinkRecipe(PMPFood.foodSnowberry, PMPFood.foodSnowberryCooler);
		createFruitDrinkRecipe(PMPFood.foodStrawberry, PMPFood.foodStrawberrySmoothie);
		
		createFruitDrinkRecipe(PMPFood.foodAppleGreen, PMPFood.foodAppleCider);
		createFruitDrinkRecipe(PMPFood.foodAppleYellow, PMPFood.foodAppleCider);
		createFruitDrinkRecipe(PMPFood.foodApricot, PMPFood.foodApricotNectar);
		createFruitDrinkRecipe(PMPFood.foodAvocado, PMPFood.foodAvocadoHurricane);
		createFruitDrinkRecipe(PMPFood.foodBanana, PMPFood.foodBananaBreeze);
		createFruitDrinkRecipe(PMPFood.foodCherry, PMPFood.foodCherryCrush);
		createFruitDrinkRecipe(PMPFood.foodCoconut, PMPFood.foodCoconutCreamer);
		createFruitDrinkRecipe(PMPFood.foodGrapefruit, PMPFood.foodGrapefruitSunrise);
		createFruitDrinkRecipe(PMPFood.foodGrapesPurple, PMPFood.foodGrapeInfusion);
		createFruitDrinkRecipe(PMPFood.foodKiwi, PMPFood.foodKiwiSplash);
		createFruitDrinkRecipe(PMPFood.foodMango, PMPFood.foodMangoDreamer);
		
		createFruitDrinkRecipe(PMPFood.foodOrange, PMPFood.foodOrangeJulep);
		createFruitDrinkRecipe(PMPFood.foodPear, PMPFood.foodPearPuree);
		createFruitDrinkRecipe(PMPFood.foodPineapple, PMPFood.foodPinaColada);
		createFruitDrinkRecipe(PMPFood.foodPlum, PMPFood.foodPlumSoda);
		createFruitDrinkRecipe(PMPFood.foodPricklyPearFruit, PMPFood.foodPricklyPearTwister);
		
		createFruitDrinkRecipe(PMPFood.foodWasabiStem, PMPFood.foodWasabiQuencher);
		
		createFruitDrinkRecipe(Items.APPLE, PMPFood.foodAppleCider);
		createFruitDrinkRecipe(Items.CARROT, PMPFood.foodCarrotJuice);
	}
	
	private void createFruitDrinkRecipe(PMPFood input, PMPFood output) {
		createFruitDrinkRecipe(PlantMegaPack.items.getFoodItem(input), output);
	}
	
	private void createFruitDrinkRecipe(Item input, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { "www", "xyx", " z ", 
			Character.valueOf('w'), new ItemStack(input, 1, 0), 
			Character.valueOf('x'), new ItemStack(Items.SUGAR, 1, 0), 
			Character.valueOf('y'), new ItemStack(Items.MILK_BUCKET, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.GLASS_BOTTLE, 1, 0) }));
	}
	
	private void initFurnaceRecipes() {
		GameRegistry.addSmelting(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(PMPFood.foodCornTortilla), 4, 0), 0.1F);
	}
	
	private void initHangingPlants() {
		for (PMPPlant plant : PMPPlant.values()) {
			if ((plant.flowerID >= 0) && ((plant.category == PMPPlantCategory.clim) || (plant.category == PMPPlantCategory.vine))) {
				createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(plant.name()), PlantMegaPack.blocks.getHangingPlant(PMPHangingPlant.getHangingPlantFromFlowerID(plant.flowerID).name()));
			}
		}
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineBlueSkyflower.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingBlueSkyflower.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.climbingBlushingPhilodendron.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingBlushingPhilodendron.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.climbingBridalCreeper.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingBridalCreeper.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.climbingJapaneseCreeper.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingJapaneseCreeper.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineJapaneseIvy.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingJapaneseIvy.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineMadeiraVine.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingMadeiraVine.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineSilverveinCreeper.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingSilverveinCreeper.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineSwedishIvy.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingSwedishIvy.name()));
		createHangingPlantRecipe(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.vineVariegatedPersianIvy.name()), PlantMegaPack.blocks
			.getHangingPlant(PMPHangingPlant.hangingVariegatedPersianIvy.name()));
	}
	
	private void createHangingPlantRecipe(Block input, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 1, 0), new Object[] { " x ", " y ", " z ", 
		
			Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0), 
			Character.valueOf('y'), new ItemStack(input, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.BOWL, 1, 0) }));
	}
	
	private void initJelly() {
		for (PMPFood berry1 : PMPFood.values()) {
			if (berry1.foodType == PMPFoodType.berr) {
				for (PMPFood berry2 : PMPFood.values()) {
					if (berry2.foodType == PMPFoodType.berr) {
						for (PMPFood berry3 : PMPFood.values()) {
							if (berry3.foodType == PMPFoodType.berr) {
								createJellyRecipe(berry1, berry2, berry3);
							}
						}
					}
				}
			}
		}
	}
	
	/** TODO: SPECIFIC FRUIT JELLIES, NOT 7000 JELLY RECIPES**/
	private void createJellyRecipe(PMPFood input1, PMPFood input2, PMPFood input3) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodJelly), 1, 0), new Object[] { new ItemStack(Items.SUGAR, 1, 0), new ItemStack(Items.GLASS_BOTTLE, 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(input1)), new ItemStack(PlantMegaPack.items
			.getFoodItem(input2)), new ItemStack(PlantMegaPack.items
			.getFoodItem(input3)) }));
	}
	
	private void initLicorice() {
		createLicoriceRecipe(PMPFood.foodLicoriceRed, 1);
		createLicoriceRecipe(PMPFood.foodLicoriceRed, 9);
		createLicoriceRecipe(PMPFood.foodLicoriceGreen, 2);
		createLicoriceRecipe(PMPFood.foodLicoriceGreen, 10);
		createLicoriceRecipe(PMPFood.foodLicoriceYellow, 11);
		createLicoriceRecipe(PMPFood.foodLicoriceOrange, 14);
	}
	
	private void createLicoriceRecipe(PMPFood output, int dyeMeta) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 4, 0), new Object[] { new ItemStack(Items.SUGAR, 1, 0), new ItemStack(Items.SUGAR, 1, 0), new ItemStack(Items.DYE, 1, dyeMeta), new ItemStack(PlantMegaPack.items
		
			.getFoodItem(PMPFood.foodLicoriceRoot), 1, 0) }));
	}
	
	private void initPeanutButter() {
		Item peanuts = PlantMegaPack.items.getFoodItem(PMPFood.foodPeanuts);
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodPeanutButter), 1, 0), new Object[] { new ItemStack(Items.SUGAR, 1, 0), new ItemStack(Items.GLASS_BOTTLE, 1, 0), new ItemStack(peanuts), new ItemStack(peanuts), new ItemStack(peanuts) }));
	}
	
	private void initPlanters() {
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooAsper.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareAsper"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooFargesiaRobusta.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareFargesiaRobusta"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGiantTimber.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareGiantTimber"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGolden.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareGolden"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooMoso.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareMoso"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooShortTassled.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareShortTassled"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTimorBlack.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareTimorBlack"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTropicalBlue.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareTropicalBlue"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooWetForest.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareWetForest"));
		
		createSquarePlanterRecipe(Blocks.PLANKS, 4, PlantMegaPack.blocks.getPlanter("planterSquareAcacia"));
		createSquarePlanterRecipe(Blocks.PLANKS, 2, PlantMegaPack.blocks.getPlanter("planterSquareBirch"));
		createSquarePlanterRecipe(Blocks.PLANKS, 5, PlantMegaPack.blocks.getPlanter("planterSquareDarkOak"));
		createSquarePlanterRecipe(Blocks.PLANKS, 3, PlantMegaPack.blocks.getPlanter("planterSquareJungle"));
		createSquarePlanterRecipe(Blocks.PLANKS, 0, PlantMegaPack.blocks.getPlanter("planterSquareOak"));
		createSquarePlanterRecipe(Blocks.PLANKS, 1, PlantMegaPack.blocks.getPlanter("planterSquareSpruce"));
		createSquarePlanterRecipe(PlantMegaPack.blocks.getWoodPlanks(PMPWood.woodfruitgray.name()), 0, PlantMegaPack.blocks.getPlanter("planterSquareFruitwoodGray"));
		
		createSquarePlanterRecipe(Blocks.COBBLESTONE, 0, PlantMegaPack.blocks.getPlanter("planterSquareCobblestone"));
		createSquarePlanterRecipe(Blocks.STONE, 0, PlantMegaPack.blocks.getPlanter("planterSquareStone"));
		createSquarePlanterRecipe(Blocks.STONE, 1, PlantMegaPack.blocks.getPlanter("planterSquareGranite"));
		createSquarePlanterRecipe(Blocks.STONE, 5, PlantMegaPack.blocks.getPlanter("planterSquareAndesite"));
		createSquarePlanterRecipe(Blocks.STONE, 3, PlantMegaPack.blocks.getPlanter("planterSquareDiorite"));
		createSquarePlanterRecipe(Blocks.LAPIS_BLOCK, 0, PlantMegaPack.blocks.getPlanter("planterSquareLapis"));
		createSquarePlanterRecipe(Blocks.OBSIDIAN, 0, PlantMegaPack.blocks.getPlanter("planterSquareObsidian"));
		createSquarePlanterRecipe(Blocks.PRISMARINE, 0, PlantMegaPack.blocks.getPlanter("planterSquarePrismarine"));
		createSquarePlanterRecipe(Blocks.QUARTZ_BLOCK, 0, PlantMegaPack.blocks.getPlanter("planterSquareQuartz"));
		createSquarePlanterRecipe(Blocks.QUARTZ_BLOCK, 1, PlantMegaPack.blocks.getPlanter("planterSquareQuartz"));
		createSquarePlanterRecipe(Blocks.QUARTZ_BLOCK, 2, PlantMegaPack.blocks.getPlanter("planterSquareQuartz"));
		createSquarePlanterRecipe(Blocks.RED_SANDSTONE, 0, PlantMegaPack.blocks.getPlanter("planterSquareRedSandstone"));
		createSquarePlanterRecipe(Blocks.SANDSTONE, 0, PlantMegaPack.blocks.getPlanter("planterSquareSandstone"));
		
		createMetalPlanterRecipe(Items.IRON_INGOT, 0, PlantMegaPack.blocks.getPlanter("planterSquareIron"));
		createMetalPlanterRecipe(Items.GOLD_INGOT, 0, PlantMegaPack.blocks.getPlanter("planterSquareGold"));
		
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooAsper.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnAsper"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooFargesiaRobusta.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnFargesiaRobusta"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGiantTimber.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnGiantTimber"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGolden.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnGolden"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooMoso.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnMoso"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooShortTassled.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnShortTassled"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTimorBlack.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnTimorBlack"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTropicalBlue.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnTropicalBlue"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooWetForest.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnWetForest"));
		
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 0, PlantMegaPack.blocks.getPlanter("planterColumnClayWhite"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 1, PlantMegaPack.blocks.getPlanter("planterColumnClayOrange"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 2, PlantMegaPack.blocks.getPlanter("planterColumnClayMagenta"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 3, PlantMegaPack.blocks.getPlanter("planterColumnClayLightBlue"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 4, PlantMegaPack.blocks.getPlanter("planterColumnClayYellow"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 5, PlantMegaPack.blocks.getPlanter("planterColumnClayLime"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 6, PlantMegaPack.blocks.getPlanter("planterColumnClayPink"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 7, PlantMegaPack.blocks.getPlanter("planterColumnClayGray"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 8, PlantMegaPack.blocks.getPlanter("planterColumnClayLightGray"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 9, PlantMegaPack.blocks.getPlanter("planterColumnClayCyan"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 10, PlantMegaPack.blocks.getPlanter("planterColumnClayPurple"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 11, PlantMegaPack.blocks.getPlanter("planterColumnClayBlue"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 12, PlantMegaPack.blocks.getPlanter("planterColumnClayBrown"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 13, PlantMegaPack.blocks.getPlanter("planterColumnClayGreen"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 14, PlantMegaPack.blocks.getPlanter("planterColumnClayRed"));
		createColumnPlanterRecipe(Blocks.STAINED_HARDENED_CLAY, 15, PlantMegaPack.blocks.getPlanter("planterColumnClayBlack"));
		
		createColumnPlanterRecipe(Blocks.COBBLESTONE, 0, PlantMegaPack.blocks.getPlanter("planterColumnCobblestone"));
		createColumnPlanterRecipe(Blocks.STONE, 0, PlantMegaPack.blocks.getPlanter("planterColumnStone"));
		createColumnPlanterRecipe(Blocks.STONE, 1, PlantMegaPack.blocks.getPlanter("planterColumnGranite"));
		createColumnPlanterRecipe(Blocks.STONE, 5, PlantMegaPack.blocks.getPlanter("planterColumnAndesite"));
		createColumnPlanterRecipe(Blocks.STONE, 3, PlantMegaPack.blocks.getPlanter("planterColumnDiorite"));
		createColumnPlanterRecipe(Blocks.LAPIS_BLOCK, 0, PlantMegaPack.blocks.getPlanter("planterColumnLapis"));
		createColumnPlanterRecipe(Blocks.OBSIDIAN, 0, PlantMegaPack.blocks.getPlanter("planterColumnObsidian"));
		createColumnPlanterRecipe(Blocks.PRISMARINE, 0, PlantMegaPack.blocks.getPlanter("planterColumnPrismarine"));
		createColumnPlanterRecipe(Blocks.QUARTZ_BLOCK, 0, PlantMegaPack.blocks.getPlanter("planterColumnQuartz"));
		createColumnPlanterRecipe(Blocks.QUARTZ_BLOCK, 1, PlantMegaPack.blocks.getPlanter("planterColumnQuartz"));
		createColumnPlanterRecipe(Blocks.QUARTZ_BLOCK, 2, PlantMegaPack.blocks.getPlanter("planterColumnQuartz"));
		createColumnPlanterRecipe(Blocks.RED_SANDSTONE, 0, PlantMegaPack.blocks.getPlanter("planterColumnRedSandstone"));
		createColumnPlanterRecipe(Blocks.SANDSTONE, 0, PlantMegaPack.blocks.getPlanter("planterColumnSandstone"));
		
		createColumnPlanterRecipe(Blocks.PLANKS, 4, PlantMegaPack.blocks.getPlanter("planterColumnAcacia"));
		createColumnPlanterRecipe(Blocks.PLANKS, 2, PlantMegaPack.blocks.getPlanter("planterColumnBirch"));
		createColumnPlanterRecipe(Blocks.PLANKS, 5, PlantMegaPack.blocks.getPlanter("planterColumnDarkOak"));
		createColumnPlanterRecipe(Blocks.PLANKS, 3, PlantMegaPack.blocks.getPlanter("planterColumnJungle"));
		createColumnPlanterRecipe(Blocks.PLANKS, 0, PlantMegaPack.blocks.getPlanter("planterColumnOak"));
		createColumnPlanterRecipe(Blocks.PLANKS, 1, PlantMegaPack.blocks.getPlanter("planterColumnSpruce"));
		createColumnPlanterRecipe(PlantMegaPack.blocks.getWoodPlanks(PMPWood.woodfruitgray.name()), 0, PlantMegaPack.blocks.getPlanter("planterColumnFruitwoodGray"));
	}
	
	private void createColumnPlanterRecipe(Block input, int inputMeta, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 1, 0), new Object[] { "xyx", "xzx", " x ", 
			Character.valueOf('x'), new ItemStack(input, 1, inputMeta), 
			Character.valueOf('y'), new ItemStack(Items.DYE, 1, 15), 
			Character.valueOf('z'), Blocks.DIRT }));
	}
	
	private void createMetalPlanterRecipe(Item input, int inputMeta, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 1, 0), new Object[] { "xyx", "xxx", "xzx", 
			Character.valueOf('x'), new ItemStack(input, 1, inputMeta), 
			Character.valueOf('y'), new ItemStack(Items.DYE, 1, 15), 
			Character.valueOf('z'), Blocks.DIRT }));
	}
	
	private void createSquarePlanterRecipe(Block input, int inputMeta, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 1, 0), new Object[] { "xyx", "xzx", "xxx", 
			Character.valueOf('x'), new ItemStack(input, 1, inputMeta), 
			Character.valueOf('y'), new ItemStack(Items.DYE, 1, 15), 
			Character.valueOf('z'), Blocks.DIRT }));
	}
	
	private void initPowders() {
		createPowderRecipe(PlantMegaPack.items.getStemItem(PMPStem.stemHard), PMPPowder.powderConditioner);
		createPowderRecipe(Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("fungusDeathCap")), PMPPowder.powderDefoliant);
		createPowderRecipe(Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("fungusWoollyGomphus")), PMPPowder.powderDefoliant);
		createPowderRecipe(PlantMegaPack.items.getStemItem(PMPStem.stemSoft), PMPPowder.powderFertilizer);
	}
	
	private void createPowderRecipe(Item input, PMPPowder output) {
		int dyeMeta = output == PMPPowder.powderDefoliant ? 0 : 15;
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getPowderItem(output), 1, 0), new Object[] { "xyx", "wzw", "www", 
			Character.valueOf('w'), new ItemStack(input, 1, 0), 
			Character.valueOf('x'), new ItemStack(Items.DYE, 1, dyeMeta), 
			Character.valueOf('y'), new ItemStack(Items.STRING, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.LEATHER, 1, 0) }));
	}
	
	private void initRice() {
		Item rice1 = PlantMegaPack.items.getFoodItem(PMPFood.foodRice);
		Item rice2 = PlantMegaPack.items.getFoodItem(PMPFood.foodWildRice);
		Item output = PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice);
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(rice1, 1, 0), new ItemStack(rice1, 1, 0), new ItemStack(rice1, 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(rice1, 1, 0), new ItemStack(rice1, 1, 0), new ItemStack(rice2, 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(rice1, 1, 0), new ItemStack(rice2, 1, 0), new ItemStack(rice2, 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[] { new ItemStack(rice2, 1, 0), new ItemStack(rice2, 1, 0), new ItemStack(rice2, 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.PAPER, 3, 0), new Object[] { "xxx", "xxx", "xxx", 
			Character.valueOf('x'), new ItemStack(rice1, 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.PAPER, 3, 0), new Object[] { "xxx", "xxx", "xxx", 
			Character.valueOf('x'), new ItemStack(rice2, 1, 0) }));
	}
	
	private void initSalves() {
		PMPItemRootMedicinal root = PlantMegaPack.items.getMedicinalRootItem();
		for (PMPRootMedicinal rootMedicinal : PMPRootMedicinal.values()) {
			createSalveRecipe(root, rootMedicinal.ID, PMPSalve.getSalveFromID(rootMedicinal.ID));
		}
		for (PMPFragment fragment1 : PMPFragment.values()) {
			for (PMPFragment fragment2 : PMPFragment.values()) {
				createSalveRecipe(PlantMegaPack.items.getCoralFragmentItem(fragment1), 0, PlantMegaPack.items.getCoralFragmentItem(fragment2), 0, PMPSalve.salveWaterBreathing);
			}
		}
		for (PMPFood food : PMPFood.values()) {
			if (food.salveID >= 0) {
				createSalveRecipe(PlantMegaPack.items.getFoodItem(food), 0, PMPSalve.getSalveFromID(food.salveID));
			}
		}
		createSalveRecipe(PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike), 0, PMPSalve.salveHealth);
		createSalveRecipe(PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceRoot), 0, PMPSalve.salveHealth);
		createSalveRecipe(PlantMegaPack.items.getStemItem(PMPStem.stemCactus), 0, PMPSalve.salveStrength);
	}
	
	private void createSalveRecipe(Item item, int meta, PMPSalve salve) {
		createSalveRecipe(item, meta, item, meta, salve);
	}
	
	private void createSalveRecipe(Item item1, int meta1, Item item2, int meta2, PMPSalve salve) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getSalveItem(salve), 1, 0), new Object[] { "   ", "wyx", " z ", 
			Character.valueOf('w'), new ItemStack(item1, 1, meta1), 
			Character.valueOf('x'), new ItemStack(item2, 1, meta2), 
			Character.valueOf('y'), new ItemStack(Items.SUGAR, 1, 0), 
			Character.valueOf('z'), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 1, 0) }));
	}
	
	private void initSandwiches() {
		for (PMPFood food1 : PMPFood.values()) {
			if (food1.foodType == PMPFoodType.vege) {
				for (PMPFood food2 : PMPFood.values()) {
					if (food2.foodType == PMPFoodType.vege) {
						createSandwichRecipes(food1, food2, Items.BREAD);
						createSandwichRecipes(food1, food2, PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread));
					}
				}
			}
		}
		createPBJSandwichRecipe(Items.BREAD);
		createPBJSandwichRecipe(PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread));
	}
	
	private void createSandwichRecipes(PMPFood input1, PMPFood input2, Item bread) {
		createSandwichRecipe(input1, input2, Items.COOKED_FISH, bread, PMPFood.foodSandwichFish);
		createSandwichRecipe(input1, input2, Items.COOKED_BEEF, bread, PMPFood.foodSandwichMeat);
		createSandwichRecipe(input1, input2, Items.COOKED_CHICKEN, bread, PMPFood.foodSandwichMeat);
		createSandwichRecipe(input1, input2, Items.COOKED_MUTTON, bread, PMPFood.foodSandwichMeat);
		createSandwichRecipe(input1, input2, Items.COOKED_PORKCHOP, bread, PMPFood.foodSandwichMeat);
		createSandwichRecipe(input1, input2, Items.COOKED_RABBIT, bread, PMPFood.foodSandwichMeat);
	}
	
	private void createSandwichRecipe(PMPFood input1, PMPFood input2, Item meat, Item bread, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 4, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(input1), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(input2), 1, 0), new ItemStack(meat, 1, 0), new ItemStack(bread, 1, 0) }));
	}
	
	private void createPBJSandwichRecipe(Item bread) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodSandwichPBJ), 4, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(PMPFood.foodPeanutButter), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(PMPFood.foodJelly), 1, 0), new ItemStack(bread, 1, 0) }));
	}
	
	private void initSaplings() {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.food != null) {
				createSaplingRecipe(sapling);
			}
		}
	}
	
	private void createSaplingRecipe(PMPSapling sapling) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.blocks.getSapling(sapling), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(sapling.food), 1, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15) }));
	}
	
	private void initStirFry() {
		for (PMPFood veg1 : PMPFood.values()) {
			if (veg1.foodType == PMPFoodType.vege) {
				for (PMPFood veg2 : PMPFood.values()) {
					if (veg2.foodType == PMPFoodType.vege) {
						createStirFryRecipe(veg1, veg2, Items.COOKED_BEEF);
						createStirFryRecipe(veg1, veg2, Items.COOKED_CHICKEN);
						createStirFryRecipe(veg1, veg2, Items.COOKED_FISH);
						createStirFryRecipe(veg1, veg2, Items.COOKED_MUTTON);
						createStirFryRecipe(veg1, veg2, Items.COOKED_PORKCHOP);
						createStirFryRecipe(veg1, veg2, Items.COOKED_RABBIT);
					}
				}
			}
		}
	}
	
	private void createStirFryRecipe(PMPFood input1, PMPFood input2, Item meat) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodStirFry), 1, 0), new Object[] { new ItemStack(meat, 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(PMPFood.foodCookedRice)), new ItemStack(PlantMegaPack.items
			.getFoodItem(input1)), new ItemStack(PlantMegaPack.items
			.getFoodItem(input2)), new ItemStack(Items.BOWL, 1, 0) }));
	}
	
	private void initStuffedPeppers() {
		initStuffedPepperReceipes(Items.BAKED_POTATO);
		initStuffedPepperReceipes(PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice));
		initStuffedPepperReceipes(Items.COOKED_BEEF);
		initStuffedPepperReceipes(Items.COOKED_CHICKEN);
		initStuffedPepperReceipes(Items.COOKED_FISH);
		initStuffedPepperReceipes(Items.COOKED_MUTTON);
		initStuffedPepperReceipes(Items.COOKED_PORKCHOP);
		initStuffedPepperReceipes(Items.COOKED_RABBIT);
	}
	
	private void initStuffedPepperReceipes(Item ingredient) {
		createStuffedPepperRecipe(PMPFood.foodBellPepperOrange, ingredient, PMPFood.foodStuffedPepperOrange);
		createStuffedPepperRecipe(PMPFood.foodBellPepperRed, ingredient, PMPFood.foodStuffedPepperRed);
		createStuffedPepperRecipe(PMPFood.foodBellPepperYellow, ingredient, PMPFood.foodStuffedPepperYellow);
	}
	
	private void createStuffedPepperRecipe(PMPFood pepperIn, Item ingredient, PMPFood pepperOut) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(pepperOut), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(pepperIn), 1, 0), new ItemStack(ingredient, 1, 0) }));
	}
	
	private void initTeaDrinks() {
		createTeaRecipe(PMPFood.foodAcaiberry, PMPFood.foodAcaiberryTea);
		createTeaRecipe(PMPFood.foodHops, PMPFood.foodHopTea);
	}
	
	private void createTeaRecipe(PMPFood input, PMPFood output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 1, 0), new Object[] { "   ", "xyx", " z ", 
			Character.valueOf('x'), new ItemStack(PlantMegaPack.items.getFoodItem(input), 1, 0), 
			Character.valueOf('y'), new ItemStack(Items.WATER_BUCKET, 1, 0), 
			Character.valueOf('z'), new ItemStack(Items.GLASS_BOTTLE, 1, 0) }));
	}
	
	private void initTortillas() {
		for (PMPFood veg1 : PMPFood.values()) {
			if (veg1.foodType == PMPFoodType.vege) {
				for (PMPFood veg2 : PMPFood.values()) {
					if (veg2.foodType == PMPFoodType.vege) {
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_FISH, veg1, veg2, PMPFood.foodCornTortillaFish);
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_BEEF, veg1, veg2, PMPFood.foodCornTortillaMeat);
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_CHICKEN, veg1, veg2, PMPFood.foodCornTortillaMeat);
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_MUTTON, veg1, veg2, PMPFood.foodCornTortillaMeat);
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_PORKCHOP, veg1, veg2, PMPFood.foodCornTortillaMeat);
						createTortillaRecipe(PMPFood.foodCornTortilla, Items.COOKED_RABBIT, veg1, veg2, PMPFood.foodCornTortillaMeat);
						createTortillaRecipe(PMPFood.foodCornTortilla, PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice), veg1, veg2, PMPFood.foodCornTortillaRice);
					}
				}
			}
		}
	}
	
	private void createTortillaRecipe(PMPFood tortillaIn, Item meat, PMPFood veg1, PMPFood veg2, PMPFood tortillaOut) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(tortillaOut), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFoodItem(tortillaIn), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(veg1), 1, 0), new ItemStack(PlantMegaPack.items
			.getFoodItem(veg2), 1, 0), new ItemStack(meat, 1, 0) }));
	}
	
	private void initTrellis() {
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooAsper.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooAsper.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooAsper.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooFargesiaRobusta.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooFargesiaRobusta.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooFargesiaRobusta.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGiantTimber.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooGiantTimber.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooGiantTimber.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGolden.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooGolden.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooGolden.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooMoso.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooMoso.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooMoso.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooShortTassled.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooShortTassled.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooShortTassled.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTimorBlack.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooTimorBlack.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooTimorBlack.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTropicalBlue.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooTropicalBlue.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooTropicalBlue.name()));
		createBambooTrellisRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooWetForest.name()), PlantMegaPack.blocks.getPlantBlockByName(PMPBamboo.bambooWetForest.name()), PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisBambooWetForest.name()));
		
		createWoodTrellisRecipe(Blocks.PLANKS, 4, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodAcacia.name()));
		createWoodTrellisRecipe(Blocks.PLANKS, 2, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodBirch.name()));
		createWoodTrellisRecipe(Blocks.PLANKS, 5, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodDarkOak.name()));
		createWoodTrellisRecipe(Blocks.PLANKS, 3, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodJungle.name()));
		createWoodTrellisRecipe(Blocks.PLANKS, 0, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodOak.name()));
		createWoodTrellisRecipe(Blocks.PLANKS, 1, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodSpruce.name()));
		createWoodTrellisRecipe(PlantMegaPack.blocks.getWoodPlanks(PMPWood.woodfruitgray.name()), 0, PlantMegaPack.blocks.getTrellis(PMPTrellis.trellisWoodFruitGray.name()));
	}
	
	private void createBambooTrellisRecipe(Block input1, Block input2, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 6, 0), new Object[] { "xyx", "xyx", "xyx",
			Character.valueOf('x'), new ItemStack(input1, 1, 0), 
			Character.valueOf('y'), new ItemStack(input2, 1, 0) }));
	}
	
	private void createWoodTrellisRecipe(Block input, int blockMeta, Block output) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(output, 6, 0), new Object[] { "xyx", "xyx", "xyx", 
			Character.valueOf('x'), new ItemStack(input, 1, blockMeta), 
			Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
	}
	
	private void initUniquePlantRecipes() {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("groundcoverTwig"), 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike), 2, 0), new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("wetlandsCattails"), 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Blocks.TORCH, 1, 0), new Object[] { new ItemStack(Items.STICK, 1, 0), new ItemStack(PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike), 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.STRING, 1, 0), new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("vineSpanishMoss"), 1, 0) }));
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.GUNPOWDER, 1, 0), new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), 1, 0), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), 1, 0), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), 1, 0), new ItemStack(PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), 1, 0) }));
	}
	
	private void initWallBrackets() {
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooAsper.name()), 0, PMPWallBracket.wallBracketAsper);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooFargesiaRobusta.name()), 0, PMPWallBracket.wallBracketFargesiaRobusta);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGiantTimber.name()), 0, PMPWallBracket.wallBracketGiantTimber);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooGolden.name()), 0, PMPWallBracket.wallBracketGolden);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooMoso.name()), 0, PMPWallBracket.wallBracketMoso);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooShortTassled.name()), 0, PMPWallBracket.wallBracketShortTassled);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTimorBlack.name()), 0, PMPWallBracket.wallBracketTimorBlack);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooTropicalBlue.name()), 0, PMPWallBracket.wallBracketTropicalBlue);
		createWallBracketRecipe(PlantMegaPack.blocks.getBambooBlock(PMPBamboo.bambooWetForest.name()), 0, PMPWallBracket.wallBracketWetForest);
		
		createWallBracketRecipe(Blocks.PLANKS, 4, PMPWallBracket.wallBracketAcacia);
		createWallBracketRecipe(Blocks.PLANKS, 2, PMPWallBracket.wallBracketBirch);
		createWallBracketRecipe(Blocks.PLANKS, 5, PMPWallBracket.wallBracketDarkOak);
		createWallBracketRecipe(Blocks.PLANKS, 3, PMPWallBracket.wallBracketJungle);
		createWallBracketRecipe(Blocks.PLANKS, 0, PMPWallBracket.wallBracketOak);
		createWallBracketRecipe(Blocks.PLANKS, 1, PMPWallBracket.wallBracketSpruce);
		createWallBracketRecipe(PlantMegaPack.blocks.getWoodPlanks(PMPWood.woodfruitgray.name()), 0, PMPWallBracket.wallBracketFruitwoodGray);
		
		createWallBracketRecipe(Blocks.STONE, 1, PMPWallBracket.wallBracketGranite);
		createWallBracketRecipe(Blocks.LAPIS_BLOCK, 0, PMPWallBracket.wallBracketLapis);
		createWallBracketRecipe(Blocks.OBSIDIAN, 0, PMPWallBracket.wallBracketObsidian);
		createWallBracketRecipe(Blocks.PRISMARINE, 0, PMPWallBracket.wallBracketPrismarine);
		createWallBracketRecipe(Blocks.QUARTZ_BLOCK, 0, PMPWallBracket.wallBracketQuartz);
		createWallBracketRecipe(Blocks.QUARTZ_BLOCK, 1, PMPWallBracket.wallBracketQuartz);
		createWallBracketRecipe(Blocks.QUARTZ_BLOCK, 2, PMPWallBracket.wallBracketQuartz);
		createWallBracketRecipe(Blocks.RED_SANDSTONE, 0, PMPWallBracket.wallBracketRedSandstone);
		createWallBracketRecipe(Blocks.SANDSTONE, 0, PMPWallBracket.wallBracketSandstone);
		createWallBracketRecipe(Blocks.COBBLESTONE, 0, PMPWallBracket.wallBracketStone);
		createWallBracketRecipe(Blocks.STONE, 0, PMPWallBracket.wallBracketStone);
		createWallBracketRecipe(Blocks.STONE, 3, PMPWallBracket.wallBracketStone);
		createWallBracketRecipe(Blocks.STONE, 5, PMPWallBracket.wallBracketStone);
		
		createWallBracketRecipe(Items.GOLD_INGOT, 0, PMPWallBracket.wallBracketGold);
		createWallBracketRecipe(Items.IRON_INGOT, 0, PMPWallBracket.wallBracketIron);
		
		createWallBracketRecipe(Items.DIAMOND, 0, PMPWallBracket.wallBracketDiamond);
		createWallBracketRecipe(Items.EMERALD, 0, PMPWallBracket.wallBracketEmerald);
	}
	
	private void createWallBracketRecipe(Block input, int inputMeta, PMPWallBracket bracket) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWallBracket(bracket.name()), 3, 0), new Object[] { "xx ", " x ", "   ", 
			Character.valueOf('x'), new ItemStack(input, 1, inputMeta) }));
	}
	
	private void createWallBracketRecipe(Item input, int inputMeta, PMPWallBracket bracket) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWallBracket(bracket.name()), 1, 0), new Object[] { "xx ", " x ", "   ", 
			Character.valueOf('x'), new ItemStack(input, 1, inputMeta) }));
	}
	
	private void initWoodBlocks() {
		for (PMPWood wood : PMPWood.values()) {
			CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 4, 0), new Object[] { new ItemStack(PlantMegaPack.blocks
				.getWoodBlock(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodSlab(wood.name()), 6, 0), new Object[] { "   ", "xxx", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodStairs(wood.name()), 4, 0), new Object[] { "  x", " xx", "xxx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodDoor(wood.name()), 3, 0), new Object[] { "xx ", "xx ", "xx ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodFence(wood.name()), 3, 0), new Object[] { "   ", "xyx", "xyx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodGate(wood.name()), 1, 0), new Object[] { "   ", "xyx", "xyx", 
				Character.valueOf('x'), new ItemStack(Items.STICK, 1, 0), 
				Character.valueOf('y'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getWoodLadder(wood.name()), 4, 0), new Object[] { "x x", "xyx", "x x",
				Character.valueOf('x'), new ItemStack(Items.STICK, 1, 0), 
				Character.valueOf('y'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.CRAFTING_TABLE, 1, 0), new Object[] { "xx ", "xx ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.CRAFTING_TABLE, 1, 0), new Object[] { " xx", " xx", "   ",
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.CRAFTING_TABLE, 1, 0), new Object[] { "   ", "xx ", "xx ",
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.CRAFTING_TABLE, 1, 0), new Object[] { "   ", " xx", " xx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.CHEST, 1, 0), new Object[] { "xxx", "x x", "xxx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.BOAT, 1, 0), new Object[] { "   ", "x x", "xxx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.BOWL, 4, 0), new Object[] { "   ", "x x", " x ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_BUTTON, 1, 0), new Object[] { "   ", " x ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.SIGN, 3, 0), new Object[] { "xxx", "xxx", " y ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { "x  ", "x  ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			/*CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { " x ", " x ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { "  x", "  x", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { "   ", "x  ", "x  ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { "   ", " x ", " x ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] { "   ", "  x", "  x", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.WOODEN_AXE, 1, 0), new Object[] { "xx ", "xy ", " y ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.WOODEN_HOE, 1, 0), new Object[] { "xx ", " y ", " y ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.WOODEN_PICKAXE, 1, 0), new Object[] { "xxx", " y ", " y ",
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.WOODEN_SHOVEL, 1, 0), new Object[] { " x ", " y ", " y ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.WOODEN_SWORD, 1, 0), new Object[] { " x ", " x ", " y ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0), 
				Character.valueOf('y'), new ItemStack(Items.STICK, 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { "xx ", "   ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			/*CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { " xx", "   ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { "   ", "xx ", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { "   ", " xx", "   ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { "   ", "   ", "xx ", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1, 0), new Object[] { "   ", "   ", " xx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Blocks.TRAPDOOR, 2, 0), new Object[] { "   ", "xxx", "xxx", 
				Character.valueOf('x'), new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 1, 0) }));
		}
	}
}