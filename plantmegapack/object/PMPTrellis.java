package plantmegapack.object;

public enum PMPTrellis
{
	trellisBambooAsper("bambooTrellisAsper", "plantmegapack:blocks/bambooAsperBlock", "plantmegapack:blocks/bambooAsperBlock1"),	trellisBambooFargesiaRobusta("bambooTrellisFargesiaRobusta", "plantmegapack:blocks/bambooFargesiaRobustaBlock", "plantmegapack:blocks/bambooFargesiaRobustaBlock1"),	trellisBambooGiantTimber("bambooTrellisGiantTimber", "plantmegapack:blocks/bambooGiantTimberBlock", "plantmegapack:blocks/bambooGiantTimberBlock1"),	trellisBambooGolden("bambooTrellisGolden", "plantmegapack:blocks/bambooGoldenBlock", "plantmegapack:blocks/bambooGoldenBlock1"),	trellisBambooMoso("bambooTrellisMoso", "plantmegapack:blocks/bambooMosoBlock", "plantmegapack:blocks/bambooMosoBlock1"),	trellisBambooShortTassled("bambooTrellisShortTassled", "plantmegapack:blocks/bambooShortTassledBlock", "plantmegapack:blocks/bambooShortTassledBlock1"),	trellisBambooTimorBlack("bambooTrellisTimorBlack", "plantmegapack:blocks/bambooTimorBlackBlock", "plantmegapack:blocks/bambooTimorBlackBlock1"),	trellisBambooTropicalBlue("bambooTrellisTropicalBlue", "plantmegapack:blocks/bambooTropicalBlueBlock", "plantmegapack:blocks/bambooTropicalBlueBlock1"),	trellisBambooWetForest("bambooTrellisWetForest", "plantmegapack:blocks/bambooWetForestBlock", "plantmegapack:blocks/bambooWetForestBlock1"),	trellisWoodAcacia("woodTrellisAcacia", "blocks/planks_acacia", "blocks/log_acacia"),	trellisWoodBirch("woodTrellisBirch", "blocks/planks_birch", "blocks/log_birch"),	trellisWoodDarkOak("woodTrellisDarkOak", "blocks/planks_big_oak", "blocks/log_big_oak"),	trellisWoodJungle("woodTrellisJungle", "blocks/planks_jungle", "blocks/log_jungle"),	trellisWoodOak("woodTrellisOak", "blocks/planks_oak", "blocks/log_oak"),	trellisWoodSpruce("woodTrellisSpruce", "blocks/planks_spruce", "blocks/log_spruce"),	trellisWoodFruitGray("woodTrellisFruitwoodGray", "plantmegapack:blocks/woodfruitgrayPlanks", "plantmegapack:blocks/woodfruitgrayBlock");
	
	public final String oreDictName;
	public final String textureMaterial;
	public final String textureTrim;
	
	private PMPTrellis(String oreDictName, String textureMaterial, String textureTrim) {
		this.oreDictName = oreDictName;
		this.textureMaterial = textureMaterial;
		this.textureTrim = textureTrim;
	}
}
