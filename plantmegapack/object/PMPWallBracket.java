package plantmegapack.object;

public enum PMPWallBracket
{
	wallBracketAsper("bambooWallBracketAsper", PMPWallBracketType.bamboo),	wallBracketFargesiaRobusta("bambooWallBracketFargesiaRobusta", PMPWallBracketType.bamboo),	wallBracketGiantTimber("bambooWallBracketGiantTimber", PMPWallBracketType.bamboo),	wallBracketGolden("bambooWallBracketGolden", PMPWallBracketType.bamboo),	wallBracketMoso("bambooWallBracketMoso", PMPWallBracketType.bamboo),	wallBracketShortTassled("bambooWallBracketShortTassled", PMPWallBracketType.bamboo),	wallBracketTimorBlack("bambooWallBracketTimorBlack", PMPWallBracketType.bamboo),	wallBracketTropicalBlue("bambooWallBracketTropicalBlue", PMPWallBracketType.bamboo),	wallBracketWetForest("bambooWallBracketWetForest", PMPWallBracketType.bamboo),	wallBracketAcacia("woodWallBracketAcacia", PMPWallBracketType.wood),	wallBracketBirch("woodWallBracketBirch", PMPWallBracketType.wood),	wallBracketDarkOak("woodWallBracketDarkOak", PMPWallBracketType.wood),	wallBracketJungle("woodWallBracketJungle", PMPWallBracketType.wood),	wallBracketOak("woodWallBracketOak", PMPWallBracketType.wood),	wallBracketSpruce("woodWallBracketSpruce", PMPWallBracketType.wood),	wallBracketFruitwoodGray("woodWallBracketFruitwoodGray", PMPWallBracketType.wood),	wallBracketGranite("stoneWallBracketGranite", PMPWallBracketType.stone),	wallBracketLapis("stoneWallBracketLapis", PMPWallBracketType.stone),	wallBracketObsidian("stoneWallBracketObsidian", PMPWallBracketType.stone),	wallBracketPrismarine("stoneWallBracketPrismarine", PMPWallBracketType.stone),	wallBracketQuartz("stoneWallBracketQuartz", PMPWallBracketType.stone),	wallBracketRedSandstone("stoneWallBracketRedSandstone", PMPWallBracketType.stone),	wallBracketSandstone("stoneWallBracketSandstone", PMPWallBracketType.stone),	wallBracketStone("stoneWallBracketStone", PMPWallBracketType.stone),	wallBracketGold("metalWallBracketGold", PMPWallBracketType.metal),	wallBracketIron("metalWallBracketIron", PMPWallBracketType.metal),	wallBracketDiamond("mineralWallBracketDiamond", PMPWallBracketType.mineral),	wallBracketEmerald("mineralWallBracketEmerald", PMPWallBracketType.mineral);
	
	public final String oreDictName;
	public final PMPWallBracketType type;
	
	private PMPWallBracket(String oreDictName, PMPWallBracketType type) {
		this.oreDictName = oreDictName;
		this.type = type;
	}
}
