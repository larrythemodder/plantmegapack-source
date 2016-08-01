package plantmegapack.data.feature;

import java.io.PrintWriter;
import java.util.Random;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureBedp
	extends PMPDataFeature
{
	public int genPlants;
	public PMPDataSetPlants plants;
	
	public PMPDataFeatureBedp(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		this.plants = new PMPDataSetPlants();
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 25;
		this.genPlants = 50;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.genPlants = Integer.parseInt(data[2]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {
		if (habitat.matches(PMPHabitat.bedg.name())) {
			this.plants.loadPlantDataLine(data);
		}
	}
	
	public void loadTreeDataLine(String habitat, String line) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.genPlants) }));
		this.plants.saveFeaturePlantData(this.biome, PMPHabitat.bedg, this.feature, printwriter);
	}
	
	public PMPDataSpawnPlant getRandomPlantSpawnData(PMPHabitat habitat, Random random) {
		switch (habitat) {
		case bedg: 
			return this.plants.getRandomPlant(random);
		default:
			break;
		}
		return null;
	}
	
	public void applyDefaultPlants() {
		this.plants.clearAllPlants();
		this.plants.clearAllPlants();
		switch (this.biome) {
		case beac: 
			this.plants.addPlant(PMPPlant.beachPrideOfMadeira, 10);
			this.plants.addPlant(PMPPlant.beachHighTideBush, 25);
			this.plants.addPlant(PMPPlant.beachSeaOats, 75);
			break;
		case desc: 
			break;
		case desw: 
			this.plants.addPlant(PMPPlant.desertApachePlume, 70);
			this.plants.addPlant(PMPPlant.desertOcotillo, 30);
			this.plants.addPlant(PMPPlant.desertSeepwood, 70);
			break;
		case forc: 
			this.plants.addPlant(PMPPlant.forestArcticGentian, 30);
			this.plants.addPlant(PMPPlant.shrubHolly, 70);
			break;
		case forw: 
			this.plants.addPlant(PMPPlant.flowerColumbine, 15);
			this.plants.addPlant(PMPPlant.forestHorseweed, 35);
			this.plants.addPlant(PMPPlant.forestWoodlandKnotweed, 45);
			this.plants.addPlant(PMPPlant.shrubBoxwood, 75);
			this.plants.addPlant(PMPPlant.shrubDwarfElder, 15);
			break;
		case jung: 
			this.plants.addPlant(PMPPlant.largeBigLeafPalm, 40);
			this.plants.addPlant(PMPPlant.largeDrakensbergCycad, 40);
			this.plants.addPlant(PMPPlant.largeDwarfSugarPalm, 70);
			this.plants.addPlant(PMPPlant.largeRedSealingWaxPalm, 30);
			break;
		case mesa: 
			break;
		case moun: 
			this.plants.addPlant(PMPPlant.forestArcticGentian, 15);
			this.plants.addPlant(PMPPlant.grassWhiteFountain, 70);
			this.plants.addPlant(PMPPlant.shrubAlpineCurrant, 40);
			this.plants.addPlant(PMPPlant.shrubJuniperSavin, 40);
			this.plants.addPlant(PMPPlant.shrubLavender, 50);
			this.plants.addPlant(PMPPlant.shrubNinebark, 40);
			this.plants.addPlant(PMPPlant.shrubWinterberry, 40);
			break;
		case mush: 
			this.plants.addPlant(PMPPlant.fungusBlackPowderpuff, 50);
			this.plants.addPlant(PMPPlant.fungusDeathCap, 50);
			this.plants.addPlant(PMPPlant.fungusStinkhorn, 50);
			this.plants.addPlant(PMPPlant.fungusWeepingMilkCap, 50);
			this.plants.addPlant(PMPPlant.fungusWoodBlewit, 50);
			break;
		case ocea: 
			break;
		case plai: 
			this.plants.addPlant(PMPPlant.grassPrairie, 50);
			this.plants.addPlant(PMPPlant.largeGiantFeatherGrass, 25);
			this.plants.addPlant(PMPPlant.plainsStingingNettle, 50);
			this.plants.addPlant(PMPPlant.shrubButterfly, 50);
			this.plants.addPlant(PMPPlant.shrubWeepingForsythia, 50);
			break;
		case rive: 
			this.plants.addPlant(PMPPlant.bambooFargesiaRobusta, 3);
			this.plants.addPlant(PMPPlant.bambooGolden, 10);
			this.plants.addPlant(PMPPlant.bambooWetForest, 3);
			//this.plants.addPlant(PMPPlant.berrybushBeauty, 5);
			this.plants.addPlant(PMPPlant.fernHayScented, 30);
			this.plants.addPlant(PMPPlant.flowerBlueStar, 5);
			this.plants.addPlant(PMPPlant.flowerLupine, 5);
			this.plants.addPlant(PMPPlant.flowerNewGuineaImpatiens, 5);
			this.plants.addPlant(PMPPlant.flowerWoodlandPinkroot, 5);
			this.plants.addPlant(PMPPlant.grassMaiden, 90);
			this.plants.addPlant(PMPPlant.shrubMeadowsweet, 15);
			this.plants.addPlant(PMPPlant.shrubNinebark, 5);
			this.plants.addPlant(PMPPlant.wetlandsClubrush, 30);
			this.plants.addPlant(PMPPlant.wetlandsPickerelweed, 30);
			break;
		case sava: 
			break;
		case swam: 
			this.plants.addPlant(PMPPlant.fernOstrich, 50);
			this.plants.addPlant(PMPPlant.wetlandsCommonRush, 80);
			this.plants.addPlant(PMPPlant.wetlandsFloweringRush, 25);
			this.plants.addPlant(PMPPlant.wetlandsPickerelweed, 25);
			this.plants.addPlant(PMPPlant.wetlandsReedMannagrass, 50);
			this.plants.addPlant(PMPPlant.wetlandsSwampMilkweed, 30);
			this.plants.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 25);
			break;
		}
	}
	
	public void applyDefaultTrees() {}
}
