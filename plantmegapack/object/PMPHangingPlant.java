package plantmegapack.object;

public enum PMPHangingPlant
{
	hangingBlueSkyflower("hangingBlueSkyflower"),	hangingBlushingPhilodendron("hangingBlushingPhilodendron"),	hangingBridalCreeper("hangingBridalCreeper"),	hangingJapaneseCreeper("hangingJapaneseCreeper"),	hangingJapaneseIvy("hangingJapaneseIvy"),	hangingMadeiraVine("hangingMadeiraVine"),	hangingSilverveinCreeper("hangingSilverveinCreeper"),	hangingSwedishIvy("hangingSwedishIvy"),	hangingVariegatedPersianIvy("hangingVariegatedPersianIvy"),	hangingFlowersRed("hangingFlowersRed"),	hangingFlowersOra("hangingFlowersOrange"),	hangingFlowersYel("hangingFlowersYellow"),	hangingFlowersGrn("hangingFlowersGreen"),	hangingFlowersCya("hangingFlowersCyan"),	hangingFlowersBlu("hangingFlowersBlue"),	hangingFlowersPur("hangingFlowersPurple"),	hangingFlowersPnk("hangingFlowersPink"),	hangingFlowersWht("hangingFlowersWhite");
	
	public final String oreDictName;
	
	private PMPHangingPlant(String oreDictName) {
		this.oreDictName = oreDictName;
	}
	
	public static PMPHangingPlant getHangingPlantFromFlowerID(int flowerID) {
		if ((flowerID < 0) || (flowerID > 8)) {
			return null;
		}
		switch (flowerID) {
		case 0: 
			return hangingFlowersRed;
		case 1: 
			return hangingFlowersOra;
		case 2: 
			return hangingFlowersYel;
		case 3: 
			return hangingFlowersGrn;
		case 4: 
			return hangingFlowersCya;
		case 5: 
			return hangingFlowersBlu;
		case 6: 
			return hangingFlowersPur;
		case 7: 
			return hangingFlowersPnk;
		case 8: 
			return hangingFlowersWht;
		}
		return null;
	}
}
