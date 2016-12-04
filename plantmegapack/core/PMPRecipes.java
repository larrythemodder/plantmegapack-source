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

public class PMPRecipes {
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
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 1), new Object[] { "xxx", "xxx", "xxx", 'x',PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooSlab(bamboo.name()), 6), new Object[] { "xxx", 'x', PlantMegaPack.blocks.getBambooBlock(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooStairs(bamboo.name()), 4), new Object[] { "  x", " xx", "xxx", 'x', PlantMegaPack.blocks.getBambooBlock(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooDoor(bamboo.name()), 3), new Object[] { "xx", "xx", "xx", 'x', PlantMegaPack.blocks.getBambooBlock(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooFence(bamboo.name()), 3), new Object[] { "xyx", "xyx", 'x', PlantMegaPack.blocks.getBambooBlock(bamboo.name()), 'y', PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooGate(bamboo.name())), new Object[] { "xyx", "xyx", 'x', PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()), 'y', PlantMegaPack.blocks.getBambooBlock(bamboo.name()) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.blocks.getBambooLadder(bamboo.name())), new Object[] { "x x", "xxx", "x x", 'x', PlantMegaPack.blocks.getPlantBlockByName(bamboo.name()) }));
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getBookItem(), 1, 0), new Object[] { new ItemStack(PlantMegaPack.items
			.getFlowerItem(flower), 1, 0), new ItemStack(PlantMegaPack.items
			.getStemItem(stem), 1, 0), new ItemStack(PlantMegaPack.items
			.getMedicinalRootItem(), 1, root.ID), new ItemStack(Items.BOOK, 1, 0) }));
	}*/
	
	private void initBowlFoods() {
		createBowlCerealRecipe("cropQuinoa", PMPFood.foodQuinoaCereal);
		
		createBowlDessertRecipe("cropAvocado", PMPFood.foodAvocadoPudding);
		createBowlDessertRecipe("cropElderberry", PMPFood.foodElderberrySorbet);
		createBowlDessertRecipe("cropGrapefruit", PMPFood.foodGrapefruitSorbet);
		createBowlDessertRecipe("cropSnowberry", PMPFood.foodSnowberryCustard);
		
		createBowlSoupRecipe("cropBroccoli", PMPFood.foodBroccoliSoup);
		createBowlSoupRecipe("cropHorseradish", PMPFood.foodHorseradishSoup);
		createBowlSoupRecipe("cropLentil", PMPFood.foodLentilSoup);
		createBowlSoupRecipe("cropMozuku", PMPFood.foodMozukuSoup);
		createBowlSoupRecipe("cropTomato", PMPFood.foodTomatoSoup);
		
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
	
	private void createBowlCerealRecipe(String input, PMPFood output) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output)), new Object[] { Items.BOWL, input, input, input }));
	}
	
	private void createBowlDessertRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{" w ", "xyx", " z ",
			'w', Items.SUGAR,
			'x', input, 
			'y', Items.MILK_BUCKET,
			'z', Items.BOWL
		}));
	}
	
	private void createBowlSoupRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addRecipe(new ShapelessOreRecipe(iOut, new Object[]{
			Items.BOWL, input, input
		}));
	}
	
	private void createBowlSoupRecipe(PMPFood input, PMPFood output) {
		Item ingredient = PlantMegaPack.items.getFoodItem(input);
		GameRegistry.addShapelessRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output)), new Object[] { Items.BOWL, ingredient, ingredient });
	}
	
	private void createBowlStewRecipe(Item ingredient1, Item ingredient2, Item output) {
		GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[] { Items.BOWL, ingredient1, ingredient2 });
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
			GameRegistry.addShapelessRecipe(new ItemStack(Items.ARROW), new Object[] { coralFragmentItem, Items.STICK, Items.FEATHER });
			GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 1, PMPColor.getColorFromID(fragment.ID).dyeID), new Object[] { coralFragmentItem });
		}
	}
	
	private void createCoralFragmentRecipe(PMPPlant plant) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getCoralFragmentItem(PMPFragment.getFragmentFromID(plant.flowerID)), 2), new Object[] { PlantMegaPack.blocks.getPlantBlockByName(plant.name()) }));
	}
	
	private void initCorn() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour)), new Object[] {
			"foodCorn"
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortilla), 1, 4), new Object[] {
			" x ", "x x", " x ", 'x', "foodCornFlour"
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread)), new Object[]{
			"xxx", 'x', "foodCornFlour"
		}));
	}
	
	private void initDesserts() {
		createDessertCookieRecipe("foodPeanutButter", PMPFood.foodCookiePeanutButter);
		
		createDessertRecipe("cropApricot", PMPFood.foodApricotGalette);
		createDessertRecipe("cropBeautyberry", PMPFood.foodBeautyberryTurnover);
		createDessertRecipe("cropBlackberry", PMPFood.foodBlackberryDanish);
		createDessertRecipe("cropGooseberry", PMPFood.foodGooseberryCobbler);
		createDessertRecipe("cropGrapes", PMPFood.foodGrapeTart);
		createDessertRecipe("cropHuckleberry", PMPFood.foodHuckleberryTart);
		createDessertRecipe("cropMango", PMPFood.foodMangoBrulee);
		createDessertRecipe("cropOrangeberry", PMPFood.foodOrangeberrySquare);
		createDessertRecipe("cropPear", PMPFood.foodPearCrumble);
		createDessertRecipe("cropPlum", PMPFood.foodPlumTart);
		createDessertRecipe("cropPorcelainberry", PMPFood.foodPorcelainberryTart);
		createDessertRecipe("cropStrawberry", PMPFood.foodStrawberryDelight);
		
		createDessertMuffinRecipes("cropBlueberry", PMPFood.foodBlueberryMuffin);
		createDessertMuffinRecipes("cropOrange", PMPFood.foodOrangeMuffin);
		
		createDessertPieRecipe("cropApple", PMPFood.foodApplePie);
		createDessertPieRecipe("cropAppleGreen", PMPFood.foodApplePie);
		createDessertPieRecipe("cropAppleYellow", PMPFood.foodApplePie);
		createDessertPieRecipe("cropBanana", PMPFood.foodBananaCreamPie);
		createDessertPieRecipe("cropCherry", PMPFood.foodCherryPie);
		createDessertPieRecipe("cropCoconut", PMPFood.foodCoconutCreamPie);
		createDessertPieRecipe("cropKiwi", PMPFood.foodKiwiPie);
		createDessertPieRecipe("cropPineapple", PMPFood.foodPineappleCake);
	}
	
	private void createDessertRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{ "xwx", "yzy",
			'w', input,
			'x', Items.SUGAR,
			'y', Items.WHEAT,
			'z', Items.EGG
		}));
	}
	
	private void createDessertCookieRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output), 6);
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{ "zyz",
			'y', input, 
			'z', Items.WHEAT
		}));
	}
	
	private void createDessertMuffinRecipes(String input, PMPFood output) {
		createDessertMuffinRecipe(input, Items.BREAD, output);
		createDessertMuffinRecipe(input, PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread), output);
	}
	
	private void createDessertMuffinRecipe(String input, Item bread, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output), 4);
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{ "xyx",
			'x', input,
			'y', bread
		}));
	}
	
	private void createDessertPieRecipe(PMPFood input, PMPFood output) {
		createDessertPieRecipe(PlantMegaPack.items.getFoodItem(input), output);
	}
	
	private void createDessertPieRecipe(Item input, PMPFood output) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output)), new Object[]{ "xy ", "z  ", 
			'x', input, 
			'y', Items.SUGAR, 
			Character.valueOf('z'), Items.EGG
		}));
	}
	
	private void createDessertPieRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{ "xy", "z ",
			'x', input,
			'y', Items.SUGAR,
			'z', Items.EGG
		}));
	}
	
	private void initFlowers() {
		for (PMPFlower flower : PMPFlower.values()) {
			GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 1, flower.dyeMeta), new Object[] { PlantMegaPack.items.getFlowerItem(flower) });
		}
	}
	
	private void initFoodWraps() {
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour)), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCorn), 3));
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.saltwaterKelpGiantGRN.name())), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 3));
		createEmptyFoodWrapRecipe(new ItemStack(PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.saltwaterKelpGiantYEL.name())), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 3));
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
		GameRegistry.addShapedRecipe(output, new Object[]{ "x x", " x ", 
			'x', input
		});
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(wrapOutput)), new Object[]{
			PlantMegaPack.items.getFoodItem(input1), PlantMegaPack.items.getFoodItem(input2), PlantMegaPack.items.getFoodItem(wrapInput), meat
		}));
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(output, 1, 0), new Object[]{
			Items.BOWL, PlantMegaPack.items.getFoodItem(input1), PlantMegaPack.items.getFoodItem(input2), PlantMegaPack.items.getFoodItem(input3)
		}));
	}
	
	private void initFruitDrinks() {
		createFruitDrinkRecipe("cropAcaiberry", PMPFood.foodAcaiberrySparkler);
		createFruitDrinkRecipe("cropBeautyberry", PMPFood.foodBeautyberryBlazer);
		createFruitDrinkRecipe("cropBlackberry", PMPFood.foodBlackberryTumbler);
		createFruitDrinkRecipe("cropBlueberry", PMPFood.foodBlueberrySlushie);
		createFruitDrinkRecipe("cropElderberry", PMPFood.foodElderberrySpritzer);
		createFruitDrinkRecipe("cropGooseberry", PMPFood.foodGooseberryShake);
		createFruitDrinkRecipe("cropHuckleberry", PMPFood.foodHuckleberryBubbler);
		createFruitDrinkRecipe("cropMulberry", PMPFood.foodMulberryDazzler);
		createFruitDrinkRecipe("cropOrangeberry", PMPFood.foodOrangeberryWhip);
		createFruitDrinkRecipe("cropPorcelainberry", PMPFood.foodPorcelainberryMixer);
		createFruitDrinkRecipe("cropSnowberry", PMPFood.foodSnowberryCooler);
		createFruitDrinkRecipe("cropStrawberry", PMPFood.foodStrawberrySmoothie);
		
		createFruitDrinkRecipe("cropAppleGreen", PMPFood.foodAppleCider);
		createFruitDrinkRecipe("cropAppleYellow", PMPFood.foodAppleCider);
		createFruitDrinkRecipe("cropApricot", PMPFood.foodApricotNectar);
		createFruitDrinkRecipe("cropAvocado", PMPFood.foodAvocadoHurricane);
		createFruitDrinkRecipe("cropBanana", PMPFood.foodBananaBreeze);
		createFruitDrinkRecipe("cropCherry", PMPFood.foodCherryCrush);
		createFruitDrinkRecipe("cropCoconut", PMPFood.foodCoconutCreamer);
		createFruitDrinkRecipe("cropGrapefruit", PMPFood.foodGrapefruitSunrise);
		createFruitDrinkRecipe("cropGrapes", PMPFood.foodGrapeInfusion);
		createFruitDrinkRecipe("cropKiwi", PMPFood.foodKiwiSplash);
		createFruitDrinkRecipe("cropMango", PMPFood.foodMangoDreamer);
		
		createFruitDrinkRecipe("cropOrange", PMPFood.foodOrangeJulep);
		createFruitDrinkRecipe("cropPear", PMPFood.foodPearPuree);
		createFruitDrinkRecipe("cropPineapple", PMPFood.foodPinaColada);
		createFruitDrinkRecipe("cropPlum", PMPFood.foodPlumSoda);
		createFruitDrinkRecipe("cropPricklyPearFruit", PMPFood.foodPricklyPearTwister);
		
		createFruitDrinkRecipe("cropWasabi", PMPFood.foodWasabiQuencher);
		
		createFruitDrinkRecipe("cropApple", PMPFood.foodAppleCider);
		createFruitDrinkRecipe("cropCarrot", PMPFood.foodCarrotJuice);
	}
	
	private void createFruitDrinkRecipe(Item input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addShapedRecipe(iOut, new Object[]{"www", "xyx", " z ", 
			'w', input,
			'x', Items.SUGAR, 
			'y', Items.MILK_BUCKET, 
			'z', Items.GLASS_BOTTLE
		});
	}
	
	private void createFruitDrinkRecipe(String input, PMPFood output) {
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output));
		GameRegistry.addRecipe(new ShapedOreRecipe(iOut, new Object[]{"www", "xyx", " z ",
			'w', input,
			'x', Items.SUGAR,
			'y', Items.MILK_BUCKET,
			'z', Items.GLASS_BOTTLE
		}));
	}
	
	private void initFurnaceRecipes() {
		GameRegistry.addSmelting(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour)), new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortilla), 4), 0.1F);
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 1, 0), new Object[] { "x", "y", "z", 
			'x', Items.IRON_INGOT, 
			'y', input, 
			'z', Items.BOWL
		}));
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodJelly)), new Object[]{
			Items.SUGAR, Items.GLASS_BOTTLE, PlantMegaPack.items.getFoodItem(input1), PlantMegaPack.items.getFoodItem(input2), PlantMegaPack.items.getFoodItem(input3)
		}));
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
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(output), 4);
		GameRegistry.addShapelessRecipe(iOut, new Object[]{
			Items.SUGAR, Items.SUGAR,
			new ItemStack(Items.DYE, 1, dyeMeta),
			PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceRoot)
		});
	}
	
	private void initPeanutButter() {
		Item peanuts = PlantMegaPack.items.getFoodItem(PMPFood.foodPeanuts);
		ItemStack iOut = new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodPeanutButter));
		GameRegistry.addShapelessRecipe(iOut, new Object[]{
			Items.SUGAR, Items.GLASS_BOTTLE,
			peanuts, peanuts, peanuts
		});
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
		GameRegistry.addShapedRecipe(new ItemStack(output), new Object[]{ "xyx", "xzx", " x ", 
			'x', new ItemStack(input, 1, inputMeta), 
			'y', new ItemStack(Items.DYE, 1, 15), 
			'z', Blocks.DIRT
		});
	}
	
	private void createMetalPlanterRecipe(Item input, int inputMeta, Block output) {
		GameRegistry.addShapedRecipe(new ItemStack(output), new Object[]{ "xyx", "xxx", "xzx", 
			'x', new ItemStack(input, 1, inputMeta), 
			'y', new ItemStack(Items.DYE, 1, 15), 
			'z', Blocks.DIRT
		});
	}
	
	private void createSquarePlanterRecipe(Block input, int inputMeta, Block output) {
		GameRegistry.addShapedRecipe(new ItemStack(output), new Object[]{ "xyx", "xzx", "xxx", 
			'x', new ItemStack(input, 1, inputMeta), 
			'y', new ItemStack(Items.DYE, 1, 15), 
			'z', Blocks.DIRT
		});
	}
	
	private void initPowders() {
		createPowderRecipe(PlantMegaPack.items.getStemItem(PMPStem.stemHard), PMPPowder.powderConditioner);
		createPowderRecipe(Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("fungusDeathCap")), PMPPowder.powderDefoliant);
		createPowderRecipe(Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("fungusWoollyGomphus")), PMPPowder.powderDefoliant);
		createPowderRecipe(PlantMegaPack.items.getStemItem(PMPStem.stemSoft), PMPPowder.powderFertilizer);
	}
	
	private void createPowderRecipe(Item input, PMPPowder output) {
		int dyeMeta = output == PMPPowder.powderDefoliant ? 0 : 15;
		GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.items.getPowderItem(output)), new Object[]{ "xyx", "wzw", "www", 
			'w', input, 
			'x', new ItemStack(Items.DYE, 1, dyeMeta), 
			'y', Items.STRING, 
			'z', Items.LEATHER
		});
	}
	
	private void initRice() {
		Item rice1 = PlantMegaPack.items.getFoodItem(PMPFood.foodRice);
		Item rice2 = PlantMegaPack.items.getFoodItem(PMPFood.foodWildRice);
		Item output = PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice);
		GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[] {rice1, rice1, rice1 });
		GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[] {rice1, rice1, rice2 });
		GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[] {rice1, rice2, rice2 });
		GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[] {rice2, rice2, rice2 });
		GameRegistry.addShapedRecipe(new ItemStack(Items.PAPER, 3), new Object[] { "xxx", "xxx", "xxx", 'x', rice1 });
		GameRegistry.addShapedRecipe(new ItemStack(Items.PAPER, 3), new Object[] { "xxx", "xxx", "xxx", 'x', rice2 });
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getSalveItem(salve)), new Object[]{ "wyx", " z ", 
			'w', new ItemStack(item1, 1, meta1), 
			'x', new ItemStack(item2, 1, meta2), 
			'y', Items.SUGAR, 
			'z', PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed)
		}));
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
		GameRegistry.addShapelessRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output), 4), new Object[]{
				PlantMegaPack.items.getFoodItem(input1), PlantMegaPack.items.getFoodItem(input2), meat, bread
		});
	}
	
	private void createPBJSandwichRecipe(Item bread) {
		GameRegistry.addShapelessRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(PMPFood.foodSandwichPBJ), 4), new Object[]{
			PlantMegaPack.items.getFoodItem(PMPFood.foodPeanutButter), PlantMegaPack.items.getFoodItem(PMPFood.foodJelly), bread
		});
	}
	
	private void initSaplings() {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.food != null) {
				createSaplingRecipe(sapling);
			}
		}
	}
	
	private void createSaplingRecipe(PMPSapling sapling) {
		GameRegistry.addShapelessRecipe(new ItemStack(PlantMegaPack.blocks.getSapling(sapling)), new Object[]{
			PlantMegaPack.items.getFoodItem(sapling.food), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15)
		});
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(pepperOut)), new Object[]{
			PlantMegaPack.items.getFoodItem(pepperIn), ingredient
		}));
	}
	
	private void initTeaDrinks() {
		createTeaRecipe("cropAcaiberry", PMPFood.foodAcaiberryTea);
		createTeaRecipe("cropHops", PMPFood.foodHopTea);
	}
	
	private void createTeaRecipe(String input, PMPFood output) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(output)), new Object[]{ "xyx", " z ", 
			'x', input,
			'y', Items.WATER_BUCKET, 
			'z', Items.GLASS_BOTTLE
		}));
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getFoodItem(tortillaOut)), new Object[] {
			PlantMegaPack.items.getFoodItem(tortillaIn), PlantMegaPack.items.getFoodItem(veg1), PlantMegaPack.items.getFoodItem(veg2), meat
		}));
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 6), new Object[]{ "xyx", "xyx", "xyx",
			'x', input1, 
			'y', input2
		}));
	}
	
	private void createWoodTrellisRecipe(Block input, int blockMeta, Block output) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 6), new Object[]{ "xyx", "xyx", "xyx", 
			'x', new ItemStack(input, 1, blockMeta), 
			'y', Items.STICK
		}));
	}
	
	private void initUniquePlantRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.STICK), new Object[] { PlantMegaPack.blocks.getPlantBlockByName("groundcoverTwig") }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike), 2), new Object[] { PlantMegaPack.blocks.getPlantBlockByName("wetlandsCattails") }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.TORCH), new Object[] { Items.STICK, PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.STRING), new Object[] { PlantMegaPack.blocks.getPlantBlockByName("vineSpanishMoss") }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.GUNPOWDER), new Object[] { PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss"), PlantMegaPack.blocks.getPlantBlockByName("forestWolfsFootClubmoss") }));
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
		GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWallBracket(bracket.name()), 3), new Object[]{ "xx", " x",
			'x', new ItemStack(input, 1, inputMeta)
		});
	}
	
	private void createWallBracketRecipe(Item input, int inputMeta, PMPWallBracket bracket) {
		GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWallBracket(bracket.name())), new Object[]{ "xx", " x",
			'x', new ItemStack(input, 1, inputMeta)
		});
	}
	
	private void initWoodBlocks() {
		for (PMPWood wood : PMPWood.values()) {
			GameRegistry.addShapelessRecipe(new ItemStack(PlantMegaPack.blocks.getWoodPlanks(wood.name()), 4), new Object[]{
				PlantMegaPack.blocks.getWoodBlock(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodSlab(wood.name()), 6), new Object[]{ "xxx",
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodStairs(wood.name()), 4), new Object[]{ "  x", " xx", "xxx", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodDoor(wood.name()), 3), new Object[]{ "xx ", "xx ", "xx ", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodFence(wood.name()), 3), new Object[]{ "xyx", "xyx", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodGate(wood.name())), new Object[]{ "xyx", "xyx", 
				'x', Items.STICK,
				'y', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(PlantMegaPack.blocks.getWoodLadder(wood.name()), 4), new Object[]{ "x x", "xyx", "x x",
				'x', Items.STICK, 
				'y', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.CRAFTING_TABLE), new Object[]{ "xx", "xx",
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.CHEST), new Object[]{ "xxx", "x x", "xxx", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.BOAT), new Object[]{ "x x", "xxx", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.BOWL, 4), new Object[]{ "x x", " x ", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.WOODEN_BUTTON), new Object[]{ "x",
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.SIGN, 3), new Object[]{ "xxx", "xxx", " y ", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.STICK, 4), new Object[] { "x", "x",
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.WOODEN_AXE), new Object[] { "xx", "xy", " y", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK,
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.WOODEN_HOE), new Object[] { "xx", " y", " y", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.WOODEN_PICKAXE), new Object[] { "xxx", " y ", " y ",
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.WOODEN_SHOVEL), new Object[] { "x", "y", "y", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK
			});
			GameRegistry.addShapedRecipe(new ItemStack(Items.WOODEN_SWORD), new Object[] { "x", "x", "y", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name()), 
				'y', Items.STICK,
			});
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE), new Object[] { "xx ", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
			GameRegistry.addShapedRecipe(new ItemStack(Blocks.TRAPDOOR, 2), new Object[] { "xxx", "xxx", 
				'x', PlantMegaPack.blocks.getWoodPlanks(wood.name())
			});
		}
	}
}