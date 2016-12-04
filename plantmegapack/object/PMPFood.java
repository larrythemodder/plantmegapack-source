package plantmegapack.object;

import java.util.EnumSet;
import net.minecraft.util.text.translation.I18n;

public enum PMPFood {
	foodAcaiberry(new String[]{"cropAcaiberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tead, PMPFoodUse.tree)),
	foodBeautyberry(new String[]{"cropBeautyberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlackberry(new String[]{"cropBlackberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlueberry(new String[]{"cropBlueberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff)),
	foodElderberry(new String[]{"cropElderberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodGooseberry(new String[]{"cropGooseberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodHuckleberry(new String[]{"cropHuckleberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodMulberry(new String[]{"cropMulberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOrangeberry(new String[]{"cropOrangeberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodPorcelainberry(new String[]{"cropPorcelainberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodSnowberry(new String[]{"cropSnowberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodStrawberry(new String[]{"cropStrawberry"}, PMPFoodType.berr, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodAppleGreen(new String[]{"cropAppleGreen"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodAppleYellow(new String[]{"cropAppleYellow"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodApricot(new String[]{"cropApricot"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	cropAvocado(new String[]{"cropAvocado"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodBanana(new String[]{"cropBanana"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCherry(new String[]{"cropCherry"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCoconut(new String[]{"cropCoconut"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodGrapefruit(new String[]{"cropGrapefruit"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodGrapesPurple(new String[]{"cropGrapes"}, PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodKiwi(new String[]{"cropKiwi"}, PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies)),
	foodMango(new String[]{"cropMango"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOlive(new String[]{"cropOlive"}, PMPFoodType.frui, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.tree)),
	foodOrange(new String[]{"cropOrange"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff, PMPFoodUse.tree)),
	foodPear(new String[]{"cropPear"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPlum(new String[]{"cropPlum"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPricklyPearFruit(new String[]{"cropCactusfruit"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.frdr)),
	foodAsparagus(new String[]{"cropAsparagus"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodBellPepperOrange(new String[]{"cropBellPepperOrange"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperRed(new String[]{"cropBellPepperRed"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperYellow(new String[]{"cropBellPepperYellow"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodBroccoli(new String[]{"cropBroccoli"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodCassavaRoot(new String[]{"cropCassava"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCauliflower(new String[]{"cropCauliflower"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCelery(new String[]{"cropCelery"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodCentellaLeaves(new String[]{"cropCentella"}, PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vegl)),
	foodChicoryLeaves(new String[]{"cropChicory"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodCorn(new String[]{"cropCorn"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodCucumber(new String[]{"cropCucumber"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodEggplant(new String[]{"cropEggplant"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodGarlic(new String[]{"cropGarlic"}, PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodGreenBeans(new String[]{"cropGreenBeans"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodHops(new String[]{"cropHops"}, PMPFoodType.vege, 1, 0.1F, 1, EnumSet.of(PMPFoodUse.tead)),
	foodHorseradish(new String[]{"cropHorseradish"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege, PMPFoodUse.soup)),
	foodHyacinthBeans(new String[]{"cropHyacinthBeans"}, PMPFoodType.vege, 1, 0.2F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodLaksaLeaves(new String[]{"cropLaksaLeaves"}, PMPFoodType.vege, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLeek(new String[]{"cropLeek"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLettuce(new String[]{"cropLettuce"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLicoriceRoot(new String[]{"cropLicoriceRoot"}, PMPFoodType.flav, 1, 0.2F, 1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMozuku(new String[]{"cropMozuku"}, PMPFoodType.vege, 1, 0.2F, 1, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodOnion(new String[]{"cropOnion"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodPineapple(new String[]{"cropPineapple"}, PMPFoodType.frui, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodRadish(new String[]{"cropRadish"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodSacredLotusRoot(new String[]{"cropSacredLotus"}, PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodSorrel(new String[]{"cropSorrel"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodSpinach(new String[]{"cropSpinach"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodTomato(new String[]{"cropTomato"}, PMPFoodType.vege, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.soup, PMPFoodUse.vege)),
	foodTaroRoot(new String[]{"cropTaro"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodTurnip(new String[]{"cropTurnip"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodWasabiStem(new String[]{"cropWasabi"}, PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodWaterChestnut(new String[]{"cropWaterchestnut"}, PMPFoodType.vege, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.vege)),
	foodWatercress(new String[]{"cropWatercress"}, PMPFoodType.vege, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.vegl)),
	foodLentil(new String[]{"cropLentil"}, PMPFoodType.seed, 2, 0.4F, -1, EnumSet.of(PMPFoodUse.plsp, PMPFoodUse.soup)),
	foodPeanuts(new String[]{"cropPeanuts"}, PMPFoodType.seed, 2, 0.3F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodQuinoaSeeds(new String[]{"cropQuinoa"}, PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.cere, PMPFoodUse.plsp)),
	foodRice(new String[]{"cropRice"}, PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodWildRice(new String[]{"cropWildRice"}, PMPFoodType.seed, 1, 0.1F, -1, EnumSet.of(PMPFoodUse.plsp)),
	foodCookedRice(new String[]{"foodCookedRice"}, PMPFoodType.food, 4, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornFlour(new String[]{"foodCornFlour"}, PMPFoodType.food, 2, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortilla(new String[]{"foodCornTortilla"}, PMPFoodType.food, 1, 0.1F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornBread(new String[]{"foodCornBread"}, PMPFoodType.food, 5, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodJelly(new String[]{"foodJelly"}, PMPFoodType.bott, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPeanutButter(new String[]{"foodPeanutButter"}, PMPFoodType.bott, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceRed(new String[]{"foodLicoriceRed"}, PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceOrange(new String[]{"foodLicoriceOrange"}, PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceYellow(new String[]{"foodLicoriceYellow"}, PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLicoriceGreen(new String[]{"foodLicoriceGreen"}, PMPFoodType.food, 2, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAvocadoPudding(new String[]{"foodAvocadoPudding"}, PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBroccoliSoup(new String[]{"foodBroccoliSoup"}, PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodElderberrySorbet(new String[]{"foodElderberrySorbet"}, PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodFruitBowl(new String[]{"foodFruitBowl"}, PMPFoodType.bowl, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapefruitSorbet(new String[]{"foodGrapefruitSorbet"}, PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHorseradishSoup(new String[]{"foodHorseradishSoup"}, PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodLentilSoup(new String[]{"foodLentilSoup"}, PMPFoodType.bowl, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMozukuSoup(new String[]{"foodMozukuSoup"}, PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSnowberryCustard(new String[]{"foodSnowberryCustard"}, PMPFoodType.bowl, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodQuinoaCereal(new String[]{"foodQuinoaCereal"}, PMPFoodType.bowl, 4, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodTomatoSoup(new String[]{"foodTomatoSoup"}, PMPFoodType.bowl, 4, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApplePie(new String[]{"foodApplePie"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApricotGalette(new String[]{"foodApricotGalette"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBananaCreamPie(new String[]{"foodBananaCreamPie"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBeautyberryTurnover(new String[]{"foodBeautyberryTurnover"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlackberryDanish(new String[]{"foodBlackberryDanish"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlueberryMuffin(new String[]{"foodBlueberryMuffin"}, PMPFoodType.food, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCherryPie(new String[]{"foodCherryPie"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCoconutCreamPie(new String[]{"foodCoconutCreamPie"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGooseberryCobbler(new String[]{"foodGooseberryCobbler"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapeTart(new String[]{"foodGrapeTart"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHuckleberryTart(new String[]{"foodHuckleberryTart"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodKiwiPie(new String[]{"foodKiwiPie"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMangoBrulee(new String[]{"foodMangoBrulee"}, PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeMuffin(new String[]{"foodOrangeMuffin"}, PMPFoodType.food, 3, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeberrySquare(new String[]{"foodOrangeberrySquare"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCookiePeanutButter(new String[]{"foodCookiePeanutButter"}, PMPFoodType.food, 2, 0.1F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPearCrumble(new String[]{"foodPearCrumble"}, PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPineappleCake(new String[]{"foodPineappleCake"}, PMPFoodType.food, 6, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPlumTart(new String[]{"foodPlumTart"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPorcelainberryTart(new String[]{"foodPorcelainberryTart"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStrawberryDelight(new String[]{"foodStrawberryDelight"}, PMPFoodType.food, 7, 0.8F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperOrange(new String[]{"foodStuffedPepperOrange"}, PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperRed(new String[]{"foodStuffedPepperRed"}, PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStuffedPepperYellow(new String[]{"foodStuffedPepperYellow"}, PMPFoodType.food, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichPBJ(new String[]{"foodSandwichPBJ"}, PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichFish(new String[]{"foodSandwichFish"}, PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSandwichMeat(new String[]{"foodSandwichMeat"}, PMPFoodType.food, 8, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAcaiberrySparkler(new String[]{"foodAcaiberrySparkler"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAppleCider(new String[]{"foodAppleCider"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodApricotNectar(new String[]{"foodApricotNectar"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAvocadoHurricane(new String[]{"foodAvocadoHurricane"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBeautyberryBlazer(new String[]{"foodBeautyberryBlazer"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBananaBreeze(new String[]{"foodBananaBreeze"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlackberryTumbler(new String[]{"foodBlackberryTumbler"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodBlueberrySlushie(new String[]{"foodBlueberrySlushie"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCarrotJuice(new String[]{"foodCarrotJuice"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCherryCrush(new String[]{"foodCherryCrush"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCoconutCreamer(new String[]{"foodCoconutCreamer"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodElderberrySpritzer(new String[]{"foodElderberrySpritzer"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGooseberryShake(new String[]{"foodGooseberryShake"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapeInfusion(new String[]{"foodGrapeInfusion"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodGrapefruitSunrise(new String[]{"foodGrapefruitSunrise"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHuckleberryBubbler(new String[]{"foodHuckleberryBubbler"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodKiwiSplash(new String[]{"foodKiwiSplash"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMangoDreamer(new String[]{"foodMangoDreamer"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMulberryDazzler(new String[]{"foodMulberryDazzler"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeJulep(new String[]{"foodOrangeJulep"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodOrangeberryWhip(new String[]{"foodOrangeberryWhip"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPearPuree(new String[]{"foodPearPuree"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPinaColada(new String[]{"foodPinaColada"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPlumSoda(new String[]{"foodPlumSoda"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPorcelainberryMixer(new String[]{"foodPorcelainberryMixer"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodPricklyPearTwister(new String[]{"foodPricklyPearTwister"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodSnowberryCooler(new String[]{"foodSnowberryCooler"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodStrawberrySmoothie(new String[]{"foodStrawberrySmoothie"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWasabiQuencher(new String[]{"foodWasabiQuencher"}, PMPFoodType.fdrn, 7, 0.6F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodAcaiberryTea(new String[]{"foodAcaiberryTea"}, PMPFoodType.tea, 6, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodHopTea(new String[]{"foodHopTea"}, PMPFoodType.tea, 6, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaFish(new String[]{"foodCornTortillaFish"}, PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaMeat(new String[]{"foodCornTortillaMeat"}, PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodCornTortillaRice(new String[]{"foodCornTortillaRice"}, PMPFoodType.food, 8, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCorn(new String[]{"foodWrapCorn"}, PMPFoodType.food, 2, 0.4F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornFish(new String[]{"foodWrapCornFish"}, PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornMeat(new String[]{"foodWrapCornMeat"}, PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapCornRice(new String[]{"foodWrapCornRice"}, PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweed(new String[]{"foodWrapSeaweed"}, PMPFoodType.food, 1, 0.3F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedFish(new String[]{"foodWrapSeaweedFish"}, PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedMeat(new String[]{"foodWrapSeaweedMeat"}, PMPFoodType.food, 10, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class)),
	foodWrapSeaweedRice(new String[]{"foodWrapSeaweedRice"}, PMPFoodType.food, 9, 1.0F, -1, EnumSet.noneOf(PMPFoodUse.class));
	
	public final String[] oreDictNames;
	public final PMPFoodType foodType;
	public final int foodValue;
	public final float saturation;
	public final int salveID;
	public final EnumSet<PMPFoodUse> foodUses;
	
	private PMPFood(String[] oreDictNames, PMPFoodType foodType, int foodValue, float saturation, int salveID, EnumSet<PMPFoodUse> foodUses) {
		this.oreDictNames = oreDictNames;
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
