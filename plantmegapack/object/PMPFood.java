package plantmegapack.object;

import java.util.EnumSet;
import net.minecraft.util.text.translation.I18n;

public enum PMPFood {
	foodAcaiberry("foodAcaiberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tead, PMPFoodUse.tree)),
	foodBeautyberry("foodBeautyberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlackberry("foodBlackberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlueberry("foodBlueberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff)),
	foodElderberry("foodElderberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodGooseberry("foodGooseberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodHuckleberry("foodHuckleberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodMulberry("foodMulberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOrangeberry("foodOrangeberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodPorcelainberry("foodPorcelainberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodSnowberry("foodSnowberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodStrawberry("foodStrawberry", PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodAppleGreen("foodAppleGreen", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodAppleYellow("foodAppleYellow", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodApricot("foodApricot", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodAvocado("foodAvocado", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodBanana("foodBanana", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCherry("foodCherry", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCoconut("foodCoconut", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodGrapefruit("foodGrapefruit", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodGrapesPurple("foodGrapesPurple", PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodKiwi("foodKiwi", PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies)),
	foodMango("foodMango", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOlive("foodOlive", PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.tree)),
	foodOrange("foodOrange", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff, PMPFoodUse.tree)),
	foodPear("foodPear", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPlum("foodPlum", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPricklyPearFruit("foodPricklyPearFruit", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.frdr)),
	foodAsparagus("foodAsparagus", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodBellPepperOrange("foodBellPepperOrange", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperRed("foodBellPepperRed", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperYellow("foodBellPepperYellow", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBroccoli("foodBroccoli", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodCassavaRoot("foodCassavaRoot", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCauliflower("foodCauliflower", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCelery("foodCelery", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodCentellaLeaves("foodCentellaLeaves", PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vegl)),
	foodChicoryLeaves("foodChicoryLeaves", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodCorn("foodCorn", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCucumber("foodCucumber", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodEggplant("foodEggplant", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodGarlic("foodGarlic", PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodGreenBeans("foodGreenBeans", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodHops("foodHops", PMPFoodType.vege, 1, 0.1F, 1, EnumSet.of(PMPFoodUse.tead)),
	foodHorseradish("foodHorseradish", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege, PMPFoodUse.soup)),
	foodHyacinthBeans("foodHyacinthBeans", PMPFoodType.vege, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodLaksaLeaves("foodLaksaLeaves", PMPFoodType.vege, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLeek("foodLeek", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLettuce("foodLettuce", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLicoriceRoot("foodLicoriceRoot", PMPFoodType.flav, 1, 0.2F, 1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMozuku("foodMozuku", PMPFoodType.vege, 1, 0.2F, 1, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodOnion("foodOnion", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodPineapple("foodPineapple", PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodRadish("foodRadish", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodSacredLotusRoot("foodSacredLotusRoot", PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodSorrel("foodSorrel", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodSpinach("foodSpinach", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodTomato("foodTomato", PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.soup, PMPFoodUse.vege)),
	foodTaroRoot("foodTaroRoot", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodTurnip("foodTurnip", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodWasabiStem("foodWasabiStem", PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodWaterChestnut("foodWaterChestnut", PMPFoodType.vege, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodWatercress("foodWatercress", PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLentil("foodLentil", PMPFoodType.seed, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.plsp, PMPFoodUse.soup)),
	foodPeanuts("foodPeanuts", PMPFoodType.seed, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodQuinoaSeeds("foodQuinoaSeeds", PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.cere, PMPFoodUse.plsp)),
	foodRice("foodRice", PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodWildRice("foodWildRice", PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodCookedRice("foodCookedRice", PMPFoodType.food, 4, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornFlour("foodCornFlour", PMPFoodType.food, 2, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortilla("foodCornTortilla", PMPFoodType.food, 1, 0.1F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornBread("foodCornBread", PMPFoodType.food, 5, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodJelly("foodJelly", PMPFoodType.bott, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPeanutButter("foodPeanutButter", PMPFoodType.bott, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceRed("foodLicoriceRed", PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceOrange("foodLicoriceOrange", PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceYellow("foodLicoriceYellow", PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceGreen("foodLicoriceGreen", PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAvocadoPudding("foodAvocadoPudding", PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBroccoliSoup("foodBroccoliSoup", PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodElderberrySorbet("foodElderberrySorbet", PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodFruitBowl("foodFruitBowl", PMPFoodType.bowl, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapefruitSorbet("foodGrapefruitSorbet", PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHorseradishSoup("foodHorseradishSoup", PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLentilSoup("foodLentilSoup", PMPFoodType.bowl, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMozukuSoup("foodMozukuSoup", PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSnowberryCustard("foodSnowberryCustard", PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodQuinoaCereal("foodQuinoaCereal", PMPFoodType.bowl, 4, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodTomatoSoup("foodTomatoSoup", PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApplePie("foodApplePie", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApricotGalette("foodApricotGalette", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBananaCreamPie("foodBananaCreamPie", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBeautyberryTurnover("foodBeautyberryTurnover", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlackberryDanish("foodBlackberryDanish", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlueberryMuffin("foodBlueberryMuffin", PMPFoodType.food, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCherryPie("foodCherryPie", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCoconutCreamPie("foodCoconutCreamPie", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGooseberryCobbler("foodGooseberryCobbler", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapeTart("foodGrapeTart", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHuckleberryTart("foodHuckleberryTart", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodKiwiPie("foodKiwiPie", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMangoBrulee("foodMangoBrulee", PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeMuffin("foodOrangeMuffin", PMPFoodType.food, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeberrySquare("foodOrangeberrySquare", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCookiePeanutButter("foodCookiePeanutButter", PMPFoodType.food, 2, 0.1F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPearCrumble("foodPearCrumble", PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPineappleCake("foodPineappleCake", PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPlumTart("foodPlumTart", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPorcelainberryTart("foodPorcelainberryTart", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStrawberryDelight("foodStrawberryDelight", PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperOrange("foodStuffedPepperOrange", PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperRed("foodStuffedPepperRed", PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperYellow("foodStuffedPepperYellow", PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichPBJ("foodSandwichPBJ", PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichFish("foodSandwichFish", PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichMeat("foodSandwichMeat", PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAcaiberrySparkler("foodAcaiberrySparkler", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAppleCider("foodAppleCider", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApricotNectar("foodApricotNectar", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAvocadoHurricane("foodAvocadoHurricane", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBeautyberryBlazer("foodBeautyberryBlazer", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBananaBreeze("foodBananaBreeze", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlackberryTumbler("foodBlackberryTumbler", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlueberrySlushie("foodBlueberrySlushie", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCarrotJuice("foodCarrotJuice", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCherryCrush("foodCherryCrush", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCoconutCreamer("foodCoconutCreamer", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodElderberrySpritzer("foodElderberrySpritzer", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGooseberryShake("foodGooseberryShake", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapeInfusion("foodGrapeInfusion", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapefruitSunrise("foodGrapefruitSunrise", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHuckleberryBubbler("foodHuckleberryBubbler", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodKiwiSplash("foodKiwiSplash", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMangoDreamer("foodMangoDreamer", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMulberryDazzler("foodMulberryDazzler", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeJulep("foodOrangeJulep", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeberryWhip("foodOrangeberryWhip", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPearPuree("foodPearPuree", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPinaColada("foodPinaColada", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPlumSoda("foodPlumSoda", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPorcelainberryMixer("foodPorcelainberryMixer", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPricklyPearTwister("foodPricklyPearTwister", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSnowberryCooler("foodSnowberryCooler", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStrawberrySmoothie("foodStrawberrySmoothie", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWasabiQuencher("foodWasabiQuencher", PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAcaiberryTea("foodAcaiberryTea", PMPFoodType.tea, 6, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHopTea("foodHopTea", PMPFoodType.tea, 6, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaFish("foodCornTortillaFish", PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaMeat("foodCornTortillaMeat", PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaRice("foodCornTortillaRice", PMPFoodType.food, 8, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCorn("foodWrapCorn", PMPFoodType.food, 2, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornFish("foodWrapCornFish", PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornMeat("foodWrapCornMeat", PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornRice("foodWrapCornRice", PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweed("foodWrapSeaweed", PMPFoodType.food, 1, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedFish("foodWrapSeaweedFish", PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedMeat("foodWrapSeaweedMeat", PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedRice("foodWrapSeaweedRice", PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStirFry("foodStirFry", PMPFoodType.bowl, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class));
	
	public final String oreDictName;
	public final PMPFoodType foodType;
	public final int foodValue;
	public final float saturation;
	public final int salveID;
	public final EnumSet<PMPFoodUse> foodUses;
	
	private PMPFood(String oreDictName, PMPFoodType foodType, int foodValue, float saturation, int salveID, EnumSet<PMPFoodUse> foodUses) {
		this.oreDictName = oreDictName;
		this.foodType = foodType;
		this.foodValue = foodValue;
		this.saturation = saturation;
		this.salveID = salveID;
		this.foodUses = foodUses;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
	
	public boolean isTreeFruit() {
		return this.foodUses.contains(PMPFoodUse.tree);
	}
	
	public boolean canSpawnPlant() {
		return this.foodUses.contains(PMPFoodUse.plsp);
	}
	
	public boolean isLeafyVegetable() {
		return this.foodUses.contains(PMPFoodUse.vegl);
	}
	
	public boolean canCraftTea() {
		return this.foodUses.contains(PMPFoodUse.tead);
	}
	
	public boolean canCraftMuffin() {
		return this.foodUses.contains(PMPFoodUse.muff);
	}
	
	public boolean canCraftPie() {
		return this.foodUses.contains(PMPFoodUse.pies);
	}
	
	public boolean canCraftDessertBowl() {
		return this.foodUses.contains(PMPFoodUse.desb);
	}
	
	public boolean canCraftCereal() {
		return this.foodUses.contains(PMPFoodUse.cere);
	}
	
	public boolean canCraftSoup() {
		return this.foodUses.contains(PMPFoodUse.soup);
	}
	
	public boolean canCraftDessert() {
		return this.foodUses.contains(PMPFoodUse.dess);
	}
	
	public boolean canCraftFruitDrink() {
		return this.foodUses.contains(PMPFoodUse.frdr);
	}
}
