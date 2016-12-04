package plantmegapack.object;

import java.util.EnumSet;
import net.minecraft.block.Block;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.tree.PMPTree;
import plantmegapack.tree.PMPTreeAcaciaBaobob;
import plantmegapack.tree.PMPTreeAcaciaCucumber;
import plantmegapack.tree.PMPTreeAcaciaRiverBushwillow;
import plantmegapack.tree.PMPTreeAcaciaWhistlingThorn;
import plantmegapack.tree.PMPTreeBirchChineseDwarf;
import plantmegapack.tree.PMPTreeBirchSilver;
import plantmegapack.tree.PMPTreeBirchWater;
import plantmegapack.tree.PMPTreeBirchWhite;
import plantmegapack.tree.PMPTreeDarkOakBur;
import plantmegapack.tree.PMPTreeDarkOakEvergreen;
import plantmegapack.tree.PMPTreeDarkOakItalian;
import plantmegapack.tree.PMPTreeDarkOakSessile;
import plantmegapack.tree.PMPTreeFruitAppleGreen;
import plantmegapack.tree.PMPTreeFruitAppleYellow;
import plantmegapack.tree.PMPTreeFruitApricot;
import plantmegapack.tree.PMPTreeFruitAvocado;
import plantmegapack.tree.PMPTreeFruitCherry;
import plantmegapack.tree.PMPTreeFruitGrapefruit;
import plantmegapack.tree.PMPTreeFruitMango;
import plantmegapack.tree.PMPTreeFruitMulberry;
import plantmegapack.tree.PMPTreeFruitOlive;
import plantmegapack.tree.PMPTreeFruitOrange;
import plantmegapack.tree.PMPTreeFruitPear;
import plantmegapack.tree.PMPTreeFruitPlum;
import plantmegapack.tree.PMPTreeJungleAcai;
import plantmegapack.tree.PMPTreeJungleBanana;
import plantmegapack.tree.PMPTreeJungleBombona;
import plantmegapack.tree.PMPTreeJungleCoconut;
import plantmegapack.tree.PMPTreeOakBlue;
import plantmegapack.tree.PMPTreeOakEnglish;
import plantmegapack.tree.PMPTreeOakGray;
import plantmegapack.tree.PMPTreeOakJapaneseEmperor;
import plantmegapack.tree.PMPTreeSpruceBlack;
import plantmegapack.tree.PMPTreeSpruceMartinez;
import plantmegapack.tree.PMPTreeSpruceNorway;
import plantmegapack.tree.PMPTreeSpruceRed;

public enum PMPSapling {
	saplingAcaciaBaobob("saplingAcaciaBaobob", 2, 5, 205, PMPTreeAcaciaBaobob.class, PMPLogType.acacia, PMPLeaves.leavesAcacia01, 0, 7910174, null, null, false, 50, EnumSet.of(PMPBiomeType.sava), 10, 4, 8),
	saplingAcaciaCucumber("saplingAcaciaCucumber", 1, 0, 112, PMPTreeAcaciaCucumber.class, PMPLogType.acacia, PMPLeaves.leavesAcacia01, 1, 7910174, null, null, false, 50, EnumSet.of(PMPBiomeType.sava), 10, 4, 8),
	saplingAcaciaRiverBushwillow("saplingAcaciaRiverBushwillow", 1, 0, 186, PMPTreeAcaciaRiverBushwillow.class, PMPLogType.acacia, PMPLeaves.leavesAcacia01, 2, 7910174, null, null, false, 50, EnumSet.of(PMPBiomeType.sava), 10, 4, 8),
	saplingAcaciaWhistlingThorn("saplingAcaciaWhistlingThorn", 1, 0, 194, PMPTreeAcaciaWhistlingThorn.class, PMPLogType.acacia, PMPLeaves.leavesAcacia01, 3, 7910174, null, null, false, 50, EnumSet.of(PMPBiomeType.sava), 10, 4, 8),
	saplingBirchChineseDwarf("saplingBirchChineseDwarf", 1, 2, 36, PMPTreeBirchChineseDwarf.class, PMPLogType.birch, PMPLeaves.leavesBirch01, 0, 6982471, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 10, 4, 8),
	saplingBirchSilver("saplingBirchSilver", 1, 2, 206, PMPTreeBirchSilver.class, PMPLogType.birch, PMPLeaves.leavesBirch01, 1, 9669958, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 10, 4, 8),
	saplingBirchWater("saplingBirchWater", 1, 2, 192, PMPTreeBirchWater.class, PMPLogType.birch, PMPLeaves.leavesBirch01, 2, 9669958, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 10, 4, 8),
	saplingBirchWhite("saplingBirchWhite", 1, 2, 195, PMPTreeBirchWhite.class, PMPLogType.birch, PMPLeaves.leavesBirch01, 3, 9412693, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.moun), 20, 4, 8),
	saplingDarkOakBur("saplingDarkOakBur", 2, 2, 193, PMPTreeDarkOakBur.class, PMPLogType.darkOak, PMPLeaves.leavesDarkOak01, 0, 4764952, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.rive), 10, 4, 8),
	saplingDarkOakEvergreen("saplingDarkOakEvergreen", 2, 0, 113, PMPTreeDarkOakEvergreen.class, PMPLogType.darkOak, PMPLeaves.leavesDarkOak01, 1, 4764952, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.rive), 20, 4, 8),
	saplingDarkOakItalian("saplingDarkOakItalian", 1, 0, 113, PMPTreeDarkOakItalian.class, PMPLogType.darkOak, PMPLeaves.leavesDarkOak01, 2, 4764952, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.rive), 10, 4, 8),
	saplingDarkOakSessile("saplingDarkOakSessile", 1, 0, 158, PMPTreeDarkOakSessile.class, PMPLogType.darkOak, PMPLeaves.leavesDarkOak01, 3, 4764952, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.rive), 10, 4, 8),
	saplingJungleAcai("saplingJungleAcai", 1, 0, 84, PMPTreeJungleAcai.class, PMPLogType.jungle, PMPLeaves.leavesJungle01, 0, 4764952, PMPTreeFruitType.berries, PMPFood.foodAcaiberry, true, 50, EnumSet.of(PMPBiomeType.jung), 10, 4, 8),
	saplingJungleBanana("saplingJungleBanana", 1, 0, 42, PMPTreeJungleBanana.class, PMPLogType.jungle, PMPLeaves.leavesJungle01, 1, 4764952, PMPTreeFruitType.banana, PMPFood.foodBanana, true, 50, EnumSet.of(PMPBiomeType.beac), 10, 4, 8),
	saplingJungleBombona("saplingJungleBombona", 1, 2, 147, PMPTreeJungleBombona.class, PMPLogType.jungle, PMPLeaves.leavesJungle01, 2, 3044111, null, null, true, 50, EnumSet.of(PMPBiomeType.beac, PMPBiomeType.jung), 20, 8, 12),
	saplingJungleCoconut("saplingJungleCoconut", 1, 0, 42, PMPTreeJungleCoconut.class, PMPLogType.jungle, PMPLeaves.leavesJungle01, 3, 4764952, PMPTreeFruitType.roundlarge, PMPFood.foodCoconut, true, 50, EnumSet.of(PMPBiomeType.beac), 10, 4, 8),
	saplingOakBlue("saplingOakBlue", 1, 0, 76, PMPTreeOakBlue.class, PMPLogType.oak, PMPLeaves.leavesOak01, 0, 4765022, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 15, 8, 12),
	saplingOakEnglish("saplingOakEnglish", 1, 2, 13, PMPTreeOakEnglish.class, PMPLogType.oak, PMPLeaves.leavesOak01, 1, 4764952, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive, PMPBiomeType.swam), 20, 8, 12),
	saplingOakGray("saplingOakGray", 1, 0, 76, PMPTreeOakGray.class, PMPLogType.oak, PMPLeaves.leavesOak01, 2, 4765022, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 15, 8, 12),
	saplingOakJapaneseEmperor("saplingOakJapaneseEmperor", 1, 2, 191, PMPTreeOakJapaneseEmperor.class, PMPLogType.oak, PMPLeaves.leavesOak01, 3, 11147064, null, null, false, 50, EnumSet.of(PMPBiomeType.forw, PMPBiomeType.plai, PMPBiomeType.rive), 15, 4, 12),
	saplingSpruceBlack("saplingSpruceBlack", 1, 0, 195, PMPTreeSpruceBlack.class, PMPLogType.spruce, PMPLeaves.leavesSpruce01, 0, 5341265, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.moun), 15, 4, 8),
	saplingSpruceMartinez("saplingSpruceMartinez", 1, 5, 79, PMPTreeSpruceMartinez.class, PMPLogType.spruce, PMPLeaves.leavesSpruce01, 1, 6390372, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.moun), 15, 4, 12),
	saplingSpruceNorway("saplingSpruceNorway", 1, 2, 1, PMPTreeSpruceNorway.class, PMPLogType.spruce, PMPLeaves.leavesSpruce01, 2, 6527311, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.moun), 15, 4, 8),
	saplingSpruceRed("saplingSpruceRed", 1, 2, 100, PMPTreeSpruceRed.class, PMPLogType.spruce, PMPLeaves.leavesSpruce01, 3, 6527311, null, null, false, 50, EnumSet.of(PMPBiomeType.forc, PMPBiomeType.moun), 15, 4, 8),
	saplingFruitAppleGreen("saplingFruitAppleGreen", 1, 1, 1, PMPTreeFruitAppleGreen.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit01, 0, 4764952, PMPTreeFruitType.roundsmall, PMPFood.foodAppleGreen, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitAppleYellow("saplingFruitAppleYellow", 1, 0, 154, PMPTreeFruitAppleYellow.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit01, 1, 4764952, PMPTreeFruitType.roundsmall, PMPFood.foodAppleYellow, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitApricot("saplingFruitApricot", 1, 0, 34, PMPTreeFruitApricot.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit01, 2, 4764952, PMPTreeFruitType.roundsmall, PMPFood.foodApricot, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitAvocado("saplingFruitAvocado", 1, 0, 145, PMPTreeFruitAvocado.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit01, 3, 4764952, PMPTreeFruitType.oval, PMPFood.cropAvocado, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitCherry("saplingFruitCherry", 1, 0, 115, PMPTreeFruitCherry.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit02, 0, 14515615, PMPTreeFruitType.berries, PMPFood.foodCherry, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitGrapefruit("saplingFruitGrapefruit", 1, 0, 196, PMPTreeFruitGrapefruit.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit02, 1, 4764952, PMPTreeFruitType.roundlarge, PMPFood.foodGrapefruit, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitMango("saplingFruitMango", 1, 1, 40, PMPTreeFruitMango.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit02, 2, 4764952, PMPTreeFruitType.roundlarge, PMPFood.foodMango, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitMulberry("saplingFruitMulberry", 1, 0, 36, PMPTreeFruitMulberry.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit02, 3, 4764952, PMPTreeFruitType.berries, PMPFood.foodMulberry, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitOlive("saplingFruitOlive", 1, 0, 149, PMPTreeFruitOlive.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit03, 0, 4764952, PMPTreeFruitType.berries, PMPFood.foodOlive, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitOrange("saplingFruitOrange", 1, 0, 121, PMPTreeFruitOrange.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit03, 1, 4764952, PMPTreeFruitType.roundsmall, PMPFood.foodOrange, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitPear("saplingFruitPear", 1, 0, 123, PMPTreeFruitPear.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit03, 2, 4764952, PMPTreeFruitType.oval, PMPFood.foodPear, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0),
	saplingFruitPlum("saplingFruitPlum", 1, 2, 153, PMPTreeFruitPlum.class, PMPLogType.woodfruitgray, PMPLeaves.leavesFruit03, 3, 4764952, PMPTreeFruitType.roundsmall, PMPFood.foodPlum, false, 10, EnumSet.of(PMPBiomeType.forw), 0, 0, 0);
	
	public final String oreDictName;
	public final int trunkSize;
	public final int conservationStatus;
	public final int mapID;
	public final Class<? extends PMPTree> treeClass;
	public final PMPLogType logType;
	public final PMPLeaves leaves;
	public final int leafMeta;
	public final int renderColor;
	public final PMPTreeFruitType treeFruitType;
	public final PMPFood food;
	public final boolean canSpawnOnSand;
	public final int defaultSpawnWeight;
	public final EnumSet<PMPBiomeType> biomes;
	public final int clusterChance;
	public final int clusterAmount;
	public final int clusterRadius;
	
	private PMPSapling(String oreDictName, int trunkSize, int conservationStatus, int mapID, Class<? extends PMPTree> treeClass, PMPLogType logType, PMPLeaves leaves, int leafMeta, int renderColor, PMPTreeFruitType treeFruitType, PMPFood food, boolean canSpawnOnSand, int defaultSpawnWeight, EnumSet<PMPBiomeType> biomes, int clusterChance, int clusterAmount, int clusterRadius) {
		this.oreDictName = oreDictName;
		this.trunkSize = trunkSize;
		this.conservationStatus = conservationStatus;
		this.mapID = mapID;
		this.treeClass = treeClass;
		this.logType = logType;
		this.leaves = leaves;
		this.leafMeta = leafMeta;
		this.renderColor = renderColor;
		this.treeFruitType = treeFruitType;
		this.food = food;
		this.canSpawnOnSand = canSpawnOnSand;
		this.defaultSpawnWeight = defaultSpawnWeight;
		this.biomes = biomes;
		this.clusterChance = clusterChance;
		this.clusterAmount = clusterAmount;
		this.clusterRadius = clusterRadius;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("tile." + name() + ".name");
	}
	
	public String getLatinName() {
		return I18n.translateToLocal("tile." + name() + ".snam");
	}
	
	public static PMPSapling getSaplingFromName(String saplingName) {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (saplingName.matches(sapling.name())) {
				return sapling;
			}
		}
		return null;
	}
	
	public Block getLogBlock() {
		return this.logType.getLogBlock();
	}
	
	public int getLogMeta() {
		return this.logType.logMeta;
	}
	
	public Block getLeavesBlock() {
		return PlantMegaPack.blocks.getLeavesBlock(this.leaves);
	}
	
	public boolean isFruitTree() {
		return this.treeFruitType != null;
	}
	
	public boolean canSpawnInBiome(PMPBiomeType biome) {
		if (biome == null) {
			return false;
		}
		return this.biomes.contains(biome);
	}
	
	public boolean canSpawnInHabitat(PMPBiomeType biome, PMPHabitat habitat) {
		if (habitat == null) {
			return false;
		}
		return (canSpawnInBiome(biome)) && ((habitat == PMPHabitat.bedg) || (habitat == PMPHabitat.open) || (habitat == PMPHabitat.slop) || (habitat == PMPHabitat.wedg));
	}
	
	public int getDefaultSpawnWeight(PMPBiomeType biome, PMPHabitat habitat) {
		return canSpawnInHabitat(biome, habitat) ? this.defaultSpawnWeight : 0;
	}
}
