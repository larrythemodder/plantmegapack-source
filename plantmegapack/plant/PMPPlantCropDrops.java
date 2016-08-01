package plantmegapack.plant;

import java.util.HashMap;
import java.util.Map;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPItems;
import plantmegapack.item.PMPItemFood;
import plantmegapack.item.PMPItemSeed;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPSeed;

public class PMPPlantCropDrops {
	private Map<String, PMPFood> food = new HashMap();
	private Map<String, PMPSeed> seed = new HashMap();
	
	public PMPPlantCropDrops() {
		initFood();
		initSeed();
	}
	
	private void initFood() {
		this.food.put("berrybushBeauty", PMPFood.foodBeautyberry);
		this.food.put("berrybushBlack", PMPFood.foodBlackberry);
		this.food.put("berrybushBlue", PMPFood.foodBlueberry);
		this.food.put("berrybushElder", PMPFood.foodElderberry);
		this.food.put("berrybushGoose", PMPFood.foodGooseberry);
		this.food.put("berrybushHuckle", PMPFood.foodHuckleberry);
		this.food.put("berrybushOrange", PMPFood.foodOrangeberry);
		this.food.put("berrybushSnow", PMPFood.foodSnowberry);
		this.food.put("berrybushStraw", PMPFood.foodStrawberry);
		
		this.food.put("cactusPricklyPear", PMPFood.foodPricklyPearFruit);
		
		this.food.put("climbingCommonGrapeVine", PMPFood.foodGrapesPurple);
		this.food.put("climbingGoldenHop", PMPFood.foodHops);
		this.food.put("climbingHyacinthBean", PMPFood.foodHyacinthBeans);
		this.food.put("climbingKiwi", PMPFood.foodKiwi);
		this.food.put("climbingPorcelainBerry", PMPFood.foodPorcelainberry);
		
		this.food.put("cropCentella", PMPFood.foodCentellaLeaves);
		this.food.put("cropLaksaLeaf", PMPFood.foodLaksaLeaves);
		this.food.put("cropRice", PMPFood.foodRice);
		this.food.put("cropSacredLotus", PMPFood.foodSacredLotusRoot);
		this.food.put("cropTaro", PMPFood.foodTaroRoot);
		this.food.put("cropWasabi", PMPFood.foodWasabiStem);
		this.food.put("cropWatercress", PMPFood.foodWatercress);
		this.food.put("cropWaterSpinach", PMPFood.foodSpinach);
		this.food.put("cropWildRice", PMPFood.foodWildRice);
		
		this.food.put("cropAsparagus", PMPFood.foodAsparagus);
		this.food.put("cropBellPepperOrange", PMPFood.foodBellPepperOrange);
		this.food.put("cropBellPepperRed", PMPFood.foodBellPepperRed);
		this.food.put("cropBellPepperYellow", PMPFood.foodBellPepperYellow);
		this.food.put("cropBroccoli", PMPFood.foodBroccoli);
		this.food.put("cropCassava", PMPFood.foodCassavaRoot);
		this.food.put("cropCauliflower", PMPFood.foodCauliflower);
		this.food.put("cropCelery", PMPFood.foodCelery);
		this.food.put("cropChicory", PMPFood.foodChicoryLeaves);
		this.food.put("cropCorn", PMPFood.foodCorn);
		this.food.put("cropCucumber", PMPFood.foodCucumber);
		this.food.put("cropEggplant", PMPFood.foodEggplant);
		this.food.put("cropGarlic", PMPFood.foodGarlic);
		this.food.put("cropGreenBeans", PMPFood.foodGreenBeans);
		this.food.put("cropHorseradish", PMPFood.foodHorseradish);
		this.food.put("cropLeek", PMPFood.foodLeek);
		this.food.put("cropLentil", PMPFood.foodLentil);
		this.food.put("cropLettuce", PMPFood.foodLettuce);
		this.food.put("cropLicorice", PMPFood.foodLicoriceRoot);
		this.food.put("cropOnion", PMPFood.foodOnion);
		this.food.put("cropPeanut", PMPFood.foodPeanuts);
		this.food.put("cropPineapple", PMPFood.foodPineapple);
		this.food.put("cropRadish", PMPFood.foodRadish);
		this.food.put("cropSorrel", PMPFood.foodSorrel);
		this.food.put("cropSpinach", PMPFood.foodSpinach);
		this.food.put("cropTomato", PMPFood.foodTomato);
		this.food.put("cropTurnip", PMPFood.foodTurnip);
		
		this.food.put("floatingWaterChestnut", PMPFood.foodWaterChestnut);
		
		this.food.put("flowerQuinoa", PMPFood.foodQuinoaSeeds);
		
		this.food.put("saltwaterMozuku", PMPFood.foodMozuku);
	}
	
	private void initSeed() {
		this.seed.put("cropAsparagus", PMPSeed.seedAsparagus);
		this.seed.put("cropBellPepperOrange", PMPSeed.seedBellPepperOrange);
		this.seed.put("cropBellPepperRed", PMPSeed.seedBellPepperRed);
		this.seed.put("cropBellPepperYellow", PMPSeed.seedBellPepperYellow);
		this.seed.put("cropBroccoli", PMPSeed.seedBroccoli);
		this.seed.put("cropCassava", PMPSeed.seedCassava);
		this.seed.put("cropCauliflower", PMPSeed.seedCauliflower);
		this.seed.put("cropCelery", PMPSeed.seedCelery);
		this.seed.put("cropChicory", PMPSeed.seedChicory);
		this.seed.put("cropCorn", PMPSeed.seedCorn);
		this.seed.put("cropCucumber", PMPSeed.seedCucumber);
		this.seed.put("cropEggplant", PMPSeed.seedEggplant);
		this.seed.put("cropGarlic", PMPSeed.seedGarlic);
		this.seed.put("cropGreenBeans", PMPSeed.seedGreenBeans);
		this.seed.put("cropHorseradish", PMPSeed.seedHorseradish);
		this.seed.put("cropLeek", PMPSeed.seedLeek);
		this.seed.put("cropLettuce", PMPSeed.seedLettuce);
		this.seed.put("cropLicorice", PMPSeed.seedLicorice);
		this.seed.put("cropOnion", PMPSeed.seedOnion);
		this.seed.put("cropPineapple", PMPSeed.seedPineapple);
		this.seed.put("cropRadish", PMPSeed.seedRadish);
		this.seed.put("cropSorrel", PMPSeed.seedSorrel);
		this.seed.put("cropSpinach", PMPSeed.seedSpinach);
		this.seed.put("cropTomato", PMPSeed.seedTomato);
		this.seed.put("cropTurnip", PMPSeed.seedTurnip);
	}
	
	public PMPItemFood getFoodItem(String plantName) {
		if (!this.food.containsKey(plantName)) {
			return null;
		}
		return PlantMegaPack.items.getFoodItem((PMPFood)this.food.get(plantName));
	}
	
	public PMPItemSeed getSeedItem(String plantName) {
		if (!this.seed.containsKey(plantName)) {
			return null;
		}
		return PlantMegaPack.items.getSeedItem((PMPSeed)this.seed.get(plantName));
	}
}
