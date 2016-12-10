package plantmegapack.object;

import java.util.EnumSet;
import net.minecraft.util.text.translation.I18n;

public enum PMPFood {
	foodAcaiberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tead, PMPFoodUse.tree)),
	foodBeautyberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlackberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodBlueberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff)),
	foodElderberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodGooseberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodHuckleberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodMulberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOrangeberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodPorcelainberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodSnowberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr)),
	foodStrawberry(PMPFoodType.berr, 1, 0.1F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	
	foodAppleGreen(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodAppleYellow(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodApricot(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	cropAvocado(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.desb, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodBanana(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCherry(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodCoconut(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies, PMPFoodUse.tree)),
	foodGrapefruit(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodGrapesPurple(PMPFoodType.frui, 1, 0.2F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodKiwi(PMPFoodType.frui, 1, 0.2F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.pies)),
	foodMango(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodOlive(PMPFoodType.frui, 1, 0.2F, EnumSet.of(PMPFoodUse.tree)),
	foodOrange(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.muff, PMPFoodUse.tree)),
	foodPear(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPlum(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr, PMPFoodUse.tree)),
	foodPricklyPearFruit(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.frdr)),
	
	foodAsparagus(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodBellPepperOrange(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperRed(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodBellPepperYellow(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodBroccoli(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodCassavaRoot(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodCauliflower(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodCelery(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodCentellaLeaves(PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vegl)),
	foodChicoryLeaves(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodCorn(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodCucumber(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodEggplant(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vege)),
	foodGarlic(PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodGreenBeans(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodHops(PMPFoodType.vege, 1, 0.1F, 1, EnumSet.of(PMPFoodUse.tead)),
	foodHorseradish(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vege, PMPFoodUse.soup)),
	foodHyacinthBeans(PMPFoodType.vege, 1, 0.2F, EnumSet.of(PMPFoodUse.vege)),
	foodLaksaLeaves(PMPFoodType.vege, 1, 0.1F, EnumSet.of(PMPFoodUse.vegl)),
	foodLeek(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodLettuce(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodLicoriceRoot(PMPFoodType.flav, 1, 0.2F, 1, EnumSet.noneOf(PMPFoodUse.class)),
	foodMozuku(PMPFoodType.vege, 1, 0.2F, 1, EnumSet.of(PMPFoodUse.vegl, PMPFoodUse.soup)),
	foodOnion(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vege)),
	foodPineapple(PMPFoodType.frui, 2, 0.4F, EnumSet.of(PMPFoodUse.dess, PMPFoodUse.frdr)),
	foodRadish(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vege)),
	foodSacredLotusRoot(PMPFoodType.vege, 2, 0.3F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodSorrel(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.vegl)),
	foodSpinach(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodTomato(PMPFoodType.vege, 2, 0.4F, EnumSet.of(PMPFoodUse.soup, PMPFoodUse.vege)),
	foodTaroRoot(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	foodTurnip(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vege)),
	foodWasabiStem(PMPFoodType.vege, 2, 0.4F, 1, EnumSet.of(PMPFoodUse.vege)),
	foodWaterChestnut(PMPFoodType.vege, 1, 0.1F, EnumSet.of(PMPFoodUse.vege)),
	foodWatercress(PMPFoodType.vege, 2, 0.3F, EnumSet.of(PMPFoodUse.vegl)),
	
	foodLentil(PMPFoodType.seed, 2, 0.4F, EnumSet.of(PMPFoodUse.plsp, PMPFoodUse.soup)),
	foodPeanuts(PMPFoodType.seed, 2, 0.3F, EnumSet.of(PMPFoodUse.plsp)),
	foodQuinoaSeeds(PMPFoodType.seed, 1, 0.1F, EnumSet.of(PMPFoodUse.cere, PMPFoodUse.plsp)),
	foodRice(PMPFoodType.seed, 1, 0.1F, EnumSet.of(PMPFoodUse.plsp)),
	foodWildRice(PMPFoodType.seed, 1, 0.1F, EnumSet.of(PMPFoodUse.plsp)),
	
	foodCookedRice(PMPFoodType.food, 4, 0.4F),
	foodCornFlour(PMPFoodType.food, 2, 0.6F),
	foodCornTortilla(PMPFoodType.food, 1, 0.1F),
	foodCornBread(PMPFoodType.food, 5, 0.6F),
	foodJelly(PMPFoodType.bott, 4, 0.6F),
	foodPeanutButter(PMPFoodType.bott, 4, 0.6F),
	
	foodLicoriceRed(PMPFoodType.food, 2, 0.3F),
	foodLicoriceOrange(PMPFoodType.food, 2, 0.3F),
	foodLicoriceYellow(PMPFoodType.food, 2, 0.3F),
	foodLicoriceGreen(PMPFoodType.food, 2, 0.3F),
	
	foodAvocadoPudding(PMPFoodType.bowl, 7, 0.8F),
	foodBroccoliSoup(PMPFoodType.bowl, 4, 0.6F),
	foodElderberrySorbet(PMPFoodType.bowl, 7, 0.8F),
	foodFruitBowl(PMPFoodType.bowl, 3, 0.3F),
	foodGrapefruitSorbet(PMPFoodType.bowl, 7, 0.8F),
	foodHorseradishSoup(PMPFoodType.bowl, 4, 0.6F),
	foodLentilSoup(PMPFoodType.bowl, 3, 0.3F),
	foodMozukuSoup(PMPFoodType.bowl, 4, 0.6F),
	foodSnowberryCustard(PMPFoodType.bowl, 7, 0.8F),
	foodQuinoaCereal(PMPFoodType.bowl, 4, 0.4F),
	foodTomatoSoup(PMPFoodType.bowl, 4, 0.6F),
	
	foodApplePie(PMPFoodType.food, 7, 0.8F),
	foodApricotGalette(PMPFoodType.food, 7, 0.8F),
	foodBananaCreamPie(PMPFoodType.food, 7, 0.8F),
	foodBeautyberryTurnover(PMPFoodType.food, 7, 0.8F),
	foodBlackberryDanish(PMPFoodType.food, 7, 0.8F),
	foodBlueberryMuffin(PMPFoodType.food, 3, 0.3F),
	foodCherryPie(PMPFoodType.food, 7, 0.8F),
	foodCoconutCreamPie(PMPFoodType.food, 7, 0.8F),
	foodGooseberryCobbler(PMPFoodType.food, 7, 0.8F),
	foodGrapeTart(PMPFoodType.food, 7, 0.8F),
	foodHuckleberryTart(PMPFoodType.food, 7, 0.8F),
	foodKiwiPie(PMPFoodType.food, 7, 0.8F),
	foodMangoBrulee(PMPFoodType.food, 6, 0.6F),
	foodOrangeMuffin(PMPFoodType.food, 3, 0.3F),
	foodOrangeberrySquare(PMPFoodType.food, 7, 0.8F),
	
	foodCookiePeanutButter(PMPFoodType.food, 2, 0.1F),
	foodPearCrumble(PMPFoodType.food, 6, 0.6F),
	foodPineappleCake(PMPFoodType.food, 6, 0.6F),
	foodPlumTart(PMPFoodType.food, 7, 0.8F),
	foodPorcelainberryTart(PMPFoodType.food, 7, 0.8F),
	foodStrawberryDelight(PMPFoodType.food, 7, 0.8F),
	
	foodStuffedPepperOrange(PMPFoodType.food, 7, 0.6F),
	foodStuffedPepperRed(PMPFoodType.food, 7, 0.6F),
	foodStuffedPepperYellow(PMPFoodType.food, 7, 0.6F),
	
	foodSandwichPBJ(PMPFoodType.food, 8, 0.6F),
	foodSandwichFish(PMPFoodType.food, 8, 0.6F),
	foodSandwichMeat(PMPFoodType.food, 8, 0.6F),
	
	foodAcaiberrySparkler(PMPFoodType.fdrn, 7, 0.6F),
	foodAppleCider(PMPFoodType.fdrn, 7, 0.6F),
	foodApricotNectar(PMPFoodType.fdrn, 7, 0.6F),
	foodAvocadoHurricane(PMPFoodType.fdrn, 7, 0.6F),
	foodBeautyberryBlazer(PMPFoodType.fdrn, 7, 0.6F),
	foodBananaBreeze(PMPFoodType.fdrn, 7, 0.6F),
	foodBlackberryTumbler(PMPFoodType.fdrn, 7, 0.6F),
	foodBlueberrySlushie(PMPFoodType.fdrn, 7, 0.6F),
	foodCarrotJuice(PMPFoodType.fdrn, 7, 0.6F),
	foodCherryCrush(PMPFoodType.fdrn, 7, 0.6F),
	foodCoconutCreamer(PMPFoodType.fdrn, 7, 0.6F),
	foodElderberrySpritzer(PMPFoodType.fdrn, 7, 0.6F),
	foodGooseberryShake(PMPFoodType.fdrn, 7, 0.6F),
	foodGrapeInfusion(PMPFoodType.fdrn, 7, 0.6F),
	foodGrapefruitSunrise(PMPFoodType.fdrn, 7, 0.6F),
	foodHuckleberryBubbler(PMPFoodType.fdrn, 7, 0.6F),
	foodKiwiSplash(PMPFoodType.fdrn, 7, 0.6F),
	foodMangoDreamer(PMPFoodType.fdrn, 7, 0.6F),
	foodMulberryDazzler(PMPFoodType.fdrn, 7, 0.6F),
	foodOrangeJulep(PMPFoodType.fdrn, 7, 0.6F),
	foodOrangeberryWhip(PMPFoodType.fdrn, 7, 0.6F),
	foodPearPuree(PMPFoodType.fdrn, 7, 0.6F),
	foodPinaColada(PMPFoodType.fdrn, 7, 0.6F),
	foodPlumSoda(PMPFoodType.fdrn, 7, 0.6F),
	foodPorcelainberryMixer(PMPFoodType.fdrn, 7, 0.6F),
	foodPricklyPearTwister(PMPFoodType.fdrn, 7, 0.6F),
	foodSnowberryCooler(PMPFoodType.fdrn, 7, 0.6F),
	foodStrawberrySmoothie(PMPFoodType.fdrn, 7, 0.6F),
	foodWasabiQuencher(PMPFoodType.fdrn, 7, 0.6F),
	
	foodAcaiberryTea(PMPFoodType.tea, 6, 0.4F),
	foodHopTea(PMPFoodType.tea, 6, 0.4F),
	
	foodCornTortillaFish(PMPFoodType.food, 9, 1.0F),
	foodCornTortillaMeat(PMPFoodType.food, 9, 1.0F),
	foodCornTortillaRice(PMPFoodType.food, 8, 1.0F),
	foodWrapCorn(PMPFoodType.food, 2, 0.4F),
	foodWrapCornFish(PMPFoodType.food, 10, 1.0F),
	foodWrapCornMeat(PMPFoodType.food, 10, 1.0F),
	foodWrapCornRice(PMPFoodType.food, 9, 1.0F),
	foodWrapSeaweed(PMPFoodType.food, 1, 0.3F),
	foodWrapSeaweedFish(PMPFoodType.food, 10, 1.0F),
	foodWrapSeaweedMeat(PMPFoodType.food, 10, 1.0F),
	foodWrapSeaweedRice(PMPFoodType.food, 9, 1.0F);
	
	public final String[] oreDictNames;
	public final PMPFoodType foodType;
	public final int foodValue;
	public final float saturation;
	public final int salveID;
	public final EnumSet<PMPFoodUse> foodUses;
	
	private PMPFood(PMPFoodType foodType, int foodValue, float saturation, EnumSet<PMPFoodUse> foodUses) {
		this.oreDictNames = new String[]{};
		this.foodType = foodType;
		this.foodValue = foodValue;
		this.saturation = saturation;
		this.salveID = -1;
		this.foodUses = foodUses;
	}
	
	private PMPFood(PMPFoodType foodType, int foodValue, float saturation) {
		this.oreDictNames = new String[]{};
		this.foodType = foodType;
		this.foodValue = foodValue;
		this.saturation = saturation;
		this.salveID = -1;
		this.foodUses = EnumSet.noneOf(PMPFoodUse.class);
	}
	
	private PMPFood(PMPFoodType foodType, int foodValue, float saturation, int salveID, EnumSet<PMPFoodUse> foodUses) {
		this.oreDictNames = new String[]{};
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
