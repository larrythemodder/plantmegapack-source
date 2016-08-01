package plantmegapack.object;

public enum PMPSeed
{
	seedAsparagus("seedAsparagus"),	seedBellPepperOrange("seedBellPepperOrange"),	seedBellPepperRed("seedBellPepperRed"),	seedBellPepperYellow("seedBellPepperYellow"),	seedBroccoli("seedBroccoli"),	seedCassava("seedCassava"),	seedCauliflower("seedCauliflower"),	seedCelery("seedCelery"),	seedChicory("seedChicory"),	seedCorn("seedCorn"),	seedCucumber("seedCucumber"),	seedEggplant("seedEggplant"),	seedGarlic("seedGarlic"),	seedGreenBeans("seedGreenBeans"),	seedHorseradish("seedHorseradish"),	seedLeek("seedLeek"),	seedLettuce("seedLettuce"),	seedLicorice("seedLicorice"),	seedOnion("seedOnion"),	seedPineapple("seedPineapple"),	seedRadish("seedRadish"),	seedSorrel("seedSorrel"),	seedSpinach("seedSpinach"),	seedTomato("seedTomato"),	seedTurnip("seedTurnip");
	
	public final String oreDictName;
	
	private PMPSeed(String oreDictName) {
		this.oreDictName = oreDictName;
	}
}
