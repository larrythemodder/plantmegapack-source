package plantmegapack.core;

import java.util.EnumSet;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.object.PMPFood;
import plantmegapack.plant.PMPPlant;

public class PMPOreDictionary {
	public static void init() {
		OreDictionary.registerOre("cropApple", Items.APPLE);
		OreDictionary.registerOre("cropCarrot", Items.CARROT);
		
		OreDictionary.registerOre("cropMushroom", Blocks.BROWN_MUSHROOM);
		OreDictionary.registerOre("cropMushroom", Blocks.RED_MUSHROOM);
		
		OreDictionary.registerOre("foodCookedMeat", Items.COOKED_BEEF);
		OreDictionary.registerOre("foodCookedMeat", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("foodCookedMeat", Items.COOKED_MUTTON);
		OreDictionary.registerOre("foodCookedMeat", Items.COOKED_PORKCHOP);
		OreDictionary.registerOre("foodCookedMeat", Items.COOKED_RABBIT);
		OreDictionary.registerOre("foodCookedFish", Items.COOKED_FISH);
		
		OreDictionary.registerOre("foodBread", Items.BREAD);
		OreDictionary.registerOre("foodBread", PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread));
		
		OreDictionary.registerOre("cropAcaiberry", PlantMegaPack.items.getFoodItem(PMPFood.foodAcaiberry));
		OreDictionary.registerOre("cropBeautyberry", PlantMegaPack.items.getFoodItem(PMPFood.foodBeautyberry));
		OreDictionary.registerOre("cropBlackberry", PlantMegaPack.items.getFoodItem(PMPFood.foodBlackberry));
		OreDictionary.registerOre("cropBlueberry", PlantMegaPack.items.getFoodItem(PMPFood.foodBlueberry));
		OreDictionary.registerOre("cropElderberry", PlantMegaPack.items.getFoodItem(PMPFood.foodElderberry));
		OreDictionary.registerOre("cropGooseberry", PlantMegaPack.items.getFoodItem(PMPFood.foodGooseberry));
		OreDictionary.registerOre("cropHuckleberry", PlantMegaPack.items.getFoodItem(PMPFood.foodHuckleberry));
		OreDictionary.registerOre("cropMulberry", PlantMegaPack.items.getFoodItem(PMPFood.foodMulberry));
		OreDictionary.registerOre("cropOrangeberry", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeberry));
		OreDictionary.registerOre("cropPorcelainberry", PlantMegaPack.items.getFoodItem(PMPFood.foodPorcelainberry));
		OreDictionary.registerOre("cropSnowberry", PlantMegaPack.items.getFoodItem(PMPFood.foodSnowberry));
		OreDictionary.registerOre("cropStrawberry", PlantMegaPack.items.getFoodItem(PMPFood.foodStrawberry));
		
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodAcaiberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodBeautyberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodBlackberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodBlueberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodElderberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodGooseberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodHuckleberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodMulberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodPorcelainberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodSnowberry));
		OreDictionary.registerOre("cropBerry", PlantMegaPack.items.getFoodItem(PMPFood.foodStrawberry));
		
		OreDictionary.registerOre("cropAppleGreen", PlantMegaPack.items.getFoodItem(PMPFood.foodAppleGreen));
		OreDictionary.registerOre("cropAppleYellow", PlantMegaPack.items.getFoodItem(PMPFood.foodAppleYellow));
		OreDictionary.registerOre("cropApricot", PlantMegaPack.items.getFoodItem(PMPFood.foodApricot));
		OreDictionary.registerOre("cropAvocado", PlantMegaPack.items.getFoodItem(PMPFood.cropAvocado));
		OreDictionary.registerOre("cropBanana", PlantMegaPack.items.getFoodItem(PMPFood.foodBanana));
		OreDictionary.registerOre("cropCherry", PlantMegaPack.items.getFoodItem(PMPFood.foodCherry));
		OreDictionary.registerOre("cropCoconut", PlantMegaPack.items.getFoodItem(PMPFood.foodCoconut));
		OreDictionary.registerOre("cropGrapefruit", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapefruit));
		OreDictionary.registerOre("cropGrapes", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapesPurple));
		OreDictionary.registerOre("cropKiwi", PlantMegaPack.items.getFoodItem(PMPFood.foodKiwi));
		OreDictionary.registerOre("cropMango", PlantMegaPack.items.getFoodItem(PMPFood.foodMango));
		OreDictionary.registerOre("cropOlive", PlantMegaPack.items.getFoodItem(PMPFood.foodOlive));
		OreDictionary.registerOre("cropOrange", PlantMegaPack.items.getFoodItem(PMPFood.foodOrange));
		OreDictionary.registerOre("cropPear", PlantMegaPack.items.getFoodItem(PMPFood.foodPear));
		OreDictionary.registerOre("cropPlum", PlantMegaPack.items.getFoodItem(PMPFood.foodPlum));
		OreDictionary.registerOre("cropCactusfruit", PlantMegaPack.items.getFoodItem(PMPFood.foodPricklyPearFruit));
		
		OreDictionary.registerOre("cropAsparagus", PlantMegaPack.items.getFoodItem(PMPFood.foodAsparagus));
		OreDictionary.registerOre("cropBellPepperOrange", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperOrange));
		OreDictionary.registerOre("cropBellPepperRed", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperRed));
		OreDictionary.registerOre("cropBellPepperYellow", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperYellow));
		OreDictionary.registerOre("cropBroccoli", PlantMegaPack.items.getFoodItem(PMPFood.foodBroccoli));
		OreDictionary.registerOre("cropCassava", PlantMegaPack.items.getFoodItem(PMPFood.foodCassavaRoot));
		OreDictionary.registerOre("cropCauliflower", PlantMegaPack.items.getFoodItem(PMPFood.foodCauliflower));
		OreDictionary.registerOre("cropCelery", PlantMegaPack.items.getFoodItem(PMPFood.foodCelery));
		OreDictionary.registerOre("cropCentella", PlantMegaPack.items.getFoodItem(PMPFood.foodCentellaLeaves));
		OreDictionary.registerOre("cropChicory", PlantMegaPack.items.getFoodItem(PMPFood.foodChicoryLeaves));
		OreDictionary.registerOre("cropCorn", PlantMegaPack.items.getFoodItem(PMPFood.foodCorn));
		OreDictionary.registerOre("cropCucumber", PlantMegaPack.items.getFoodItem(PMPFood.foodCucumber));
		OreDictionary.registerOre("cropEggplant", PlantMegaPack.items.getFoodItem(PMPFood.foodEggplant));
		OreDictionary.registerOre("cropGarlic", PlantMegaPack.items.getFoodItem(PMPFood.foodGarlic));
		OreDictionary.registerOre("cropGreenBeans", PlantMegaPack.items.getFoodItem(PMPFood.foodGreenBeans));
		OreDictionary.registerOre("cropHops", PlantMegaPack.items.getFoodItem(PMPFood.foodHops));
		OreDictionary.registerOre("cropHorseradish", PlantMegaPack.items.getFoodItem(PMPFood.foodHorseradish));
		OreDictionary.registerOre("cropHyacinthBeans", PlantMegaPack.items.getFoodItem(PMPFood.foodHyacinthBeans));
		OreDictionary.registerOre("cropLaksaLeaves", PlantMegaPack.items.getFoodItem(PMPFood.foodLaksaLeaves));
		OreDictionary.registerOre("cropLeek", PlantMegaPack.items.getFoodItem(PMPFood.foodLeek));
		OreDictionary.registerOre("cropLettuce", PlantMegaPack.items.getFoodItem(PMPFood.foodLettuce));
		OreDictionary.registerOre("cropLicoriceRoot", PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceRoot));
		OreDictionary.registerOre("cropMozuku", PlantMegaPack.items.getFoodItem(PMPFood.foodMozuku));
		OreDictionary.registerOre("cropOnion", PlantMegaPack.items.getFoodItem(PMPFood.foodOnion));
		OreDictionary.registerOre("cropPineapple", PlantMegaPack.items.getFoodItem(PMPFood.foodPineapple));
		OreDictionary.registerOre("cropRadish", PlantMegaPack.items.getFoodItem(PMPFood.foodRadish));
		OreDictionary.registerOre("cropSacredLotus", PlantMegaPack.items.getFoodItem(PMPFood.foodSacredLotusRoot));
		OreDictionary.registerOre("cropSorrel", PlantMegaPack.items.getFoodItem(PMPFood.foodSorrel));
		OreDictionary.registerOre("cropSpinach", PlantMegaPack.items.getFoodItem(PMPFood.foodSpinach));
		OreDictionary.registerOre("cropTomato", PlantMegaPack.items.getFoodItem(PMPFood.foodTomato));
		OreDictionary.registerOre("cropTaro", PlantMegaPack.items.getFoodItem(PMPFood.foodTaroRoot));
		OreDictionary.registerOre("cropTurnip", PlantMegaPack.items.getFoodItem(PMPFood.foodTurnip));
		OreDictionary.registerOre("cropWasabi", PlantMegaPack.items.getFoodItem(PMPFood.foodWasabiStem));
		OreDictionary.registerOre("cropWaterchestnut", PlantMegaPack.items.getFoodItem(PMPFood.foodWaterChestnut));
		OreDictionary.registerOre("cropWatercress", PlantMegaPack.items.getFoodItem(PMPFood.foodWatercress));
		
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodAsparagus));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperOrange));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperRed));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodBellPepperYellow));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodBroccoli));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCassavaRoot));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCauliflower));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCelery));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCentellaLeaves));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodChicoryLeaves));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCorn));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodCucumber));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodEggplant));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodGarlic));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodGreenBeans));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodHops));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodHorseradish));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodHyacinthBeans));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodLaksaLeaves));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodLeek));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodLettuce));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodMozuku));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodOnion));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodRadish));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodSacredLotusRoot));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodSorrel));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodSpinach));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodTomato));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodTaroRoot));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodTurnip));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodWasabiStem));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodWaterChestnut));
		OreDictionary.registerOre("cropVegetable", PlantMegaPack.items.getFoodItem(PMPFood.foodWatercress));
		
		OreDictionary.registerOre("cropLentil", PlantMegaPack.items.getFoodItem(PMPFood.foodLentil));
		OreDictionary.registerOre("cropPeanuts", PlantMegaPack.items.getFoodItem(PMPFood.foodPeanuts));
		OreDictionary.registerOre("cropQuinoa", PlantMegaPack.items.getFoodItem(PMPFood.foodQuinoaSeeds));
		OreDictionary.registerOre("cropRice", PlantMegaPack.items.getFoodItem(PMPFood.foodRice));
		OreDictionary.registerOre("cropWildRice", PlantMegaPack.items.getFoodItem(PMPFood.foodWildRice));
		
		OreDictionary.registerOre("foodCookedRice", PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice));
		OreDictionary.registerOre("foodCornFlour", PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour));
		OreDictionary.registerOre("foodCornTortilla", PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortilla));
		OreDictionary.registerOre("foodCornBread", PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread));
		OreDictionary.registerOre("foodJelly", PlantMegaPack.items.getFoodItem(PMPFood.foodJelly));
		OreDictionary.registerOre("foodPeanutButter", PlantMegaPack.items.getFoodItem(PMPFood.foodPeanutButter));
		
		OreDictionary.registerOre("foodLicoriceRed", PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceRed));
		OreDictionary.registerOre("foodLicoriceOrange", PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceOrange));
		OreDictionary.registerOre("foodLicoriceYellow", PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceYellow));
		OreDictionary.registerOre("foodLicoriceGreen", PlantMegaPack.items.getFoodItem(PMPFood.foodLicoriceGreen));
		
		OreDictionary.registerOre("foodAvocadoPudding", PlantMegaPack.items.getFoodItem(PMPFood.foodAvocadoPudding));
		OreDictionary.registerOre("foodBroccoliSoup", PlantMegaPack.items.getFoodItem(PMPFood.foodBroccoliSoup));
		OreDictionary.registerOre("foodElderberrySorbet", PlantMegaPack.items.getFoodItem(PMPFood.foodElderberrySorbet));
		OreDictionary.registerOre("foodFruitBowl", PlantMegaPack.items.getFoodItem(PMPFood.foodFruitBowl));
		OreDictionary.registerOre("foodGrapefruitSorbet", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapefruitSorbet));
		OreDictionary.registerOre("foodHorseradishSoup", PlantMegaPack.items.getFoodItem(PMPFood.foodHorseradishSoup));
		OreDictionary.registerOre("foodLentilSoup", PlantMegaPack.items.getFoodItem(PMPFood.foodLentilSoup));
		OreDictionary.registerOre("foodMozukuSoup", PlantMegaPack.items.getFoodItem(PMPFood.foodMozukuSoup));
		OreDictionary.registerOre("foodSnowberryCustard", PlantMegaPack.items.getFoodItem(PMPFood.foodSnowberryCustard));
		OreDictionary.registerOre("foodQuinoaCereal", PlantMegaPack.items.getFoodItem(PMPFood.foodQuinoaCereal));
		OreDictionary.registerOre("foodTomatoSoup", PlantMegaPack.items.getFoodItem(PMPFood.foodTomatoSoup));
		
		OreDictionary.registerOre("foodApplePie", PlantMegaPack.items.getFoodItem(PMPFood.foodApplePie));
		OreDictionary.registerOre("foodApricotGalette", PlantMegaPack.items.getFoodItem(PMPFood.foodApricotGalette));
		OreDictionary.registerOre("foodBananaCreamPie", PlantMegaPack.items.getFoodItem(PMPFood.foodBananaCreamPie));
		OreDictionary.registerOre("foodBeautyberryTurnover", PlantMegaPack.items.getFoodItem(PMPFood.foodBeautyberryTurnover));
		OreDictionary.registerOre("foodBlackberryDanish", PlantMegaPack.items.getFoodItem(PMPFood.foodBlackberryDanish));
		OreDictionary.registerOre("foodBlueberryMuffin", PlantMegaPack.items.getFoodItem(PMPFood.foodBlueberryMuffin));
		OreDictionary.registerOre("foodCherryPie", PlantMegaPack.items.getFoodItem(PMPFood.foodCherryPie));
		OreDictionary.registerOre("foodCoconutCreamPie", PlantMegaPack.items.getFoodItem(PMPFood.foodCoconutCreamPie));
		OreDictionary.registerOre("foodGooseberryCobbler", PlantMegaPack.items.getFoodItem(PMPFood.foodGooseberryCobbler));
		OreDictionary.registerOre("foodGrapeTart", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapeTart));
		OreDictionary.registerOre("foodHuckleberryTart", PlantMegaPack.items.getFoodItem(PMPFood.foodHuckleberryTart));
		OreDictionary.registerOre("foodKiwiPie", PlantMegaPack.items.getFoodItem(PMPFood.foodKiwiPie));
		OreDictionary.registerOre("foodMangoBrulee", PlantMegaPack.items.getFoodItem(PMPFood.foodMangoBrulee));
		OreDictionary.registerOre("foodOrangeMuffin", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeMuffin));
		OreDictionary.registerOre("foodOrangeberrySquare", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeberrySquare));
		
		OreDictionary.registerOre("foodCookiePeanutButter", PlantMegaPack.items.getFoodItem(PMPFood.foodCookiePeanutButter));
		OreDictionary.registerOre("foodPearCrumble", PlantMegaPack.items.getFoodItem(PMPFood.foodPearCrumble));
		OreDictionary.registerOre("foodPineappleCake", PlantMegaPack.items.getFoodItem(PMPFood.foodPineappleCake));
		OreDictionary.registerOre("foodPlumTart", PlantMegaPack.items.getFoodItem(PMPFood.foodPlumTart));
		OreDictionary.registerOre("foodPlumTart", PlantMegaPack.items.getFoodItem(PMPFood.foodPorcelainberryTart));
		OreDictionary.registerOre("foodStrawberryDelight", PlantMegaPack.items.getFoodItem(PMPFood.foodStrawberryDelight));

		OreDictionary.registerOre("foodStuffedPepperOrange", PlantMegaPack.items.getFoodItem(PMPFood.foodStuffedPepperOrange));
		OreDictionary.registerOre("foodStuffedPepperRed", PlantMegaPack.items.getFoodItem(PMPFood.foodStuffedPepperRed));
		OreDictionary.registerOre("foodStuffedPepperYellow", PlantMegaPack.items.getFoodItem(PMPFood.foodStuffedPepperYellow));
		
		OreDictionary.registerOre("foodSandwichPBJ", PlantMegaPack.items.getFoodItem(PMPFood.foodSandwichPBJ));
		OreDictionary.registerOre("foodSandwichFish", PlantMegaPack.items.getFoodItem(PMPFood.foodSandwichFish));
		OreDictionary.registerOre("foodSandwichMeat", PlantMegaPack.items.getFoodItem(PMPFood.foodSandwichMeat));
		
		OreDictionary.registerOre("foodAcaiberrySparkler", PlantMegaPack.items.getFoodItem(PMPFood.foodAcaiberrySparkler));
		OreDictionary.registerOre("foodAppleCider", PlantMegaPack.items.getFoodItem(PMPFood.foodAppleCider));
		OreDictionary.registerOre("foodApricotNectar", PlantMegaPack.items.getFoodItem(PMPFood.foodApricotNectar));
		OreDictionary.registerOre("foodAvocadoHurricane", PlantMegaPack.items.getFoodItem(PMPFood.foodAvocadoHurricane));
		OreDictionary.registerOre("foodBeautyberryBlazer", PlantMegaPack.items.getFoodItem(PMPFood.foodBeautyberryBlazer));
		OreDictionary.registerOre("foodBananaBreeze", PlantMegaPack.items.getFoodItem(PMPFood.foodBananaBreeze));
		OreDictionary.registerOre("foodBlackberryTumbler", PlantMegaPack.items.getFoodItem(PMPFood.foodBlackberryTumbler));
		OreDictionary.registerOre("foodBlueberrySlushie", PlantMegaPack.items.getFoodItem(PMPFood.foodBlueberrySlushie));
		OreDictionary.registerOre("foodCarrotJuice", PlantMegaPack.items.getFoodItem(PMPFood.foodCarrotJuice));
		OreDictionary.registerOre("foodCherryCrush", PlantMegaPack.items.getFoodItem(PMPFood.foodCherryCrush));
		OreDictionary.registerOre("foodCoconutCreamer", PlantMegaPack.items.getFoodItem(PMPFood.foodCoconutCreamer));
		OreDictionary.registerOre("foodElderberrySpritzer", PlantMegaPack.items.getFoodItem(PMPFood.foodElderberrySpritzer));
		OreDictionary.registerOre("foodGooseberryShake", PlantMegaPack.items.getFoodItem(PMPFood.foodGooseberryShake));
		OreDictionary.registerOre("foodGrapeInfusion", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapeInfusion));
		OreDictionary.registerOre("foodGrapefruitSunrise", PlantMegaPack.items.getFoodItem(PMPFood.foodGrapefruitSunrise));
		OreDictionary.registerOre("foodHuckleberryBubbler", PlantMegaPack.items.getFoodItem(PMPFood.foodHuckleberryBubbler));
		OreDictionary.registerOre("foodKiwiSplash", PlantMegaPack.items.getFoodItem(PMPFood.foodKiwiSplash));
		OreDictionary.registerOre("foodMangoDreamer", PlantMegaPack.items.getFoodItem(PMPFood.foodMangoDreamer));
		OreDictionary.registerOre("foodMulberryDazzler", PlantMegaPack.items.getFoodItem(PMPFood.foodMulberryDazzler));
		OreDictionary.registerOre("foodOrangeJulep", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeJulep));
		OreDictionary.registerOre("foodOrangeberryWhip", PlantMegaPack.items.getFoodItem(PMPFood.foodOrangeberryWhip));
		OreDictionary.registerOre("foodPearPuree", PlantMegaPack.items.getFoodItem(PMPFood.foodPearPuree));
		OreDictionary.registerOre("foodPinaColada", PlantMegaPack.items.getFoodItem(PMPFood.foodPinaColada));
		OreDictionary.registerOre("foodPlumSoda", PlantMegaPack.items.getFoodItem(PMPFood.foodPlumSoda));
		OreDictionary.registerOre("foodPorcelainberryMixer", PlantMegaPack.items.getFoodItem(PMPFood.foodPorcelainberryMixer));
		OreDictionary.registerOre("foodPricklyPearTwister", PlantMegaPack.items.getFoodItem(PMPFood.foodPricklyPearTwister));
		OreDictionary.registerOre("foodSnowberryCooler", PlantMegaPack.items.getFoodItem(PMPFood.foodSnowberryCooler));
		OreDictionary.registerOre("foodStrawberrySmoothie", PlantMegaPack.items.getFoodItem(PMPFood.foodStrawberrySmoothie));
		OreDictionary.registerOre("foodWasabiQuencher", PlantMegaPack.items.getFoodItem(PMPFood.foodWasabiQuencher));

		OreDictionary.registerOre("foodAcaiberryTea", PlantMegaPack.items.getFoodItem(PMPFood.foodAcaiberryTea));
		OreDictionary.registerOre("foodHopTea", PlantMegaPack.items.getFoodItem(PMPFood.foodHopTea));
		
		OreDictionary.registerOre("foodCornTortillaFish", PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortillaFish));
		OreDictionary.registerOre("foodCornTortillaMeat", PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortillaMeat));
		OreDictionary.registerOre("foodCornTortillaRice", PlantMegaPack.items.getFoodItem(PMPFood.foodCornTortillaRice));
		OreDictionary.registerOre("foodWrapCorn", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCorn));
		OreDictionary.registerOre("foodWrapCornFish", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCornFish));
		OreDictionary.registerOre("foodWrapCornMeat", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCornMeat));
		OreDictionary.registerOre("foodWrapCornRice", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapCornRice));
		OreDictionary.registerOre("foodWrapSeaweed", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed));
		OreDictionary.registerOre("foodWrapSeaweedFish", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweedFish));
		OreDictionary.registerOre("foodWrapSeaweedMeat", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweedMeat));
		OreDictionary.registerOre("foodWrapSeaweedRice", PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweedRice));
		
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusBlackPowderpuff.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusChanterelle.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusDeathCap.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusGiantClub.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusParasol.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusStinkhorn.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusWeepingMilkCap.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusWoodBlewit.name()));
		OreDictionary.registerOre("cropMushroom", PlantMegaPack.blocks.getPlantBlockByName(PMPPlant.fungusWoollyGomphus.name()));
		
		
	}
}
