package plantmegapack.data.feature;

import java.io.PrintWriter;
import java.util.Random;
import plantmegapack.data.PMPDataSetPlants;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataFeatureWedg
	extends PMPDataFeature
{
	public int genFloating;
	public int genImmersed;
	public int genLand;
	public int genSlope;
	public PMPDataSetPlants plantsFloa;
	public PMPDataSetPlants plantsImme;
	public PMPDataSetPlants plantsLand;
	public PMPDataSetPlants plantsSlop;
	
	public PMPDataFeatureWedg(PMPWorldgenFeature feature, PMPBiomeType biome) {
		super(feature, biome);
		this.plantsFloa = new PMPDataSetPlants();
		this.plantsImme = new PMPDataSetPlants();
		this.plantsLand = new PMPDataSetPlants();
		this.plantsSlop = new PMPDataSetPlants();
		resetDefaults();
	}
	
	public void resetDefaults() {
		this.enabled = true;
		this.genChance = 25;
		this.genFloating = 50;
		this.genImmersed = 50;
		this.genLand = 50;
		this.genSlope = 50;
		applyDefaultPlants();
		applyDefaultTrees();
	}
	
	public void loadSettingsDataLine(String line) {
		String[] data = line.split(",");
		this.enabled = (Integer.parseInt(data[0]) == 1);
		this.genChance = Integer.parseInt(data[1]);
		this.genFloating = Integer.parseInt(data[2]);
		this.genImmersed = Integer.parseInt(data[3]);
		this.genLand = Integer.parseInt(data[4]);
		this.genSlope = Integer.parseInt(data[5]);
	}
	
	public void loadPlantDataLine(String habitat, String data) {
		if (habitat.matches(PMPHabitat.frfl.name())) {
			this.plantsFloa.loadPlantDataLine(data);
		} else if (habitat.matches(PMPHabitat.frmr.name())) {
			this.plantsImme.loadPlantDataLine(data);
		} else if (habitat.matches(PMPHabitat.wedg.name())) {
			this.plantsLand.loadPlantDataLine(data);
		} else if (habitat.matches(PMPHabitat.slop.name())) {
			this.plantsSlop.loadPlantDataLine(data);
		}
	}
	
	public void loadTreeDataLine(String habitat, String line) {}
	
	public void saveSettingsData(PrintWriter printwriter) {
		printwriter.println(String.format("%s=feature:%s:%d,%d,%d,%d,%d,%d", new Object[] { this.biome.name(), this.feature.name(), Integer.valueOf(this.enabled ? 1 : 0), 
			Integer.valueOf(this.genChance), Integer.valueOf(this.genFloating), Integer.valueOf(this.genImmersed), Integer.valueOf(this.genLand), Integer.valueOf(this.genSlope) }));
		this.plantsFloa.saveFeaturePlantData(this.biome, PMPHabitat.frfl, this.feature, printwriter);
		this.plantsImme.saveFeaturePlantData(this.biome, PMPHabitat.frmr, this.feature, printwriter);
		this.plantsLand.saveFeaturePlantData(this.biome, PMPHabitat.wedg, this.feature, printwriter);
		this.plantsSlop.saveFeaturePlantData(this.biome, PMPHabitat.slop, this.feature, printwriter);
	}
	
	public PMPDataSpawnPlant getRandomPlantSpawnData(PMPHabitat habitat, Random random) {
		switch (habitat) {
		case frfl: 
			return this.plantsFloa.getRandomPlant(random);
		case frmr: 
			return this.plantsImme.getRandomPlant(random);
		case slop: 
			return this.plantsSlop.getRandomPlant(random);
		case wedg: 
			return this.plantsLand.getRandomPlant(random);
		default:
			break;
		}
		return null;
	}
	
	public void applyDefaultPlants() {
		this.plantsFloa.clearAllPlants();
		this.plantsImme.clearAllPlants();
		this.plantsLand.clearAllPlants();
		this.plantsSlop.clearAllPlants();
		switch (this.biome) {
		case beac: 
			break;
		case desc: 
			break;
		case desw: 
			break;
		case forc: 
			this.plantsFloa.addPlant(PMPPlant.floatingRedRootFloater, 50);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.flowerStreamsideBluebells, 15);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsFloweringRush, 15);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 5);
			
			this.plantsSlop.addPlant(PMPPlant.fernMaidenhairSpleenwort, 50);
			this.plantsSlop.addPlant(PMPPlant.fernOstrich, 50);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 80);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case forw: 
			this.plantsFloa.addPlant(PMPPlant.floatingArrowLeavedLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingCrestedFloatingheart, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingDuckweed, 50);
			this.plantsFloa.addPlant(PMPPlant.floatingRedRootFloater, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLettuce, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedArrowArum, 5);
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedDuckPotato, 5);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 5);
			this.plantsImme.addPlant(PMPPlant.immersedWaterMannagrass, 30);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.flowerBlueStar, 5);
			this.plantsLand.addPlant(PMPPlant.flowerNewGuineaImpatiens, 5);
			this.plantsLand.addPlant(PMPPlant.flowerWoodlandPinkroot, 10);
			this.plantsLand.addPlant(PMPPlant.forestBloodroot, 5);
			this.plantsLand.addPlant(PMPPlant.forestGiantAngelica, 5);
			this.plantsLand.addPlant(PMPPlant.largeDrakensbergCycad, 15);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsPickerelweed, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 5);
			
			//this.plantsSlop.addPlant(PMPPlant.berrybushStraw, 5);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 80);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case jung: 
			this.plantsFloa.addPlant(PMPPlant.floatingDuckweed, 90);
			this.plantsFloa.addPlant(PMPPlant.floatingRedRootFloater, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterChestnut, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLettuce, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 20);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSoftstemBulrush, 10);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.flowerBlueStar, 5);
			this.plantsLand.addPlant(PMPPlant.flowerNewGuineaImpatiens, 5);
			this.plantsLand.addPlant(PMPPlant.forestBloodroot, 5);
			this.plantsLand.addPlant(PMPPlant.largeBigLeafPalm, 20);
			this.plantsLand.addPlant(PMPPlant.largeDrakensbergCycad, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsPickerelweed, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 40);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 5);
			
			//this.plantsSlop.addPlant(PMPPlant.berrybushStraw, 5);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 30);
			this.plantsLand.addPlant(PMPPlant.largeBigLeafPalm, 50);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case mesa: 
			break;
		case moun: 
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 80);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterChestnut, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedGreySedge, 80);
			this.plantsImme.addPlant(PMPPlant.immersedEuropeanBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSoftstemBulrush, 10);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.flowerStreamsideBluebells, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsFloweringRush, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 20);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 5);
			
			//this.plantsSlop.addPlant(PMPPlant.berrybushStraw, 5);
			this.plantsSlop.addPlant(PMPPlant.fernMaidenhairSpleenwort, 20);
			this.plantsSlop.addPlant(PMPPlant.fernOstrich, 50);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 80);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case mush: 
			break;
		case ocea: 
			break;
		case plai: 
			this.plantsFloa.addPlant(PMPPlant.floatingCrestedFloatingheart, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingDuckweed, 50);
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterChestnut, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLettuce, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedEuropeanBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSoftstemBulrush, 10);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.forestGiantAngelica, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCommonRush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsPickerelweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 20);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 5);
			
			//this.plantsSlop.addPlant(PMPPlant.berrybushStraw, 5);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 50);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case rive: 
			this.plantsFloa.addPlant(PMPPlant.floatingArrowLeavedLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingCrestedFloatingheart, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingDuckweed, 90);
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterChestnut, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLettuce, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedArrowArum, 10);
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedSimplestemBurReed, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSoftstemBulrush, 10);
			this.plantsImme.addPlant(PMPPlant.immersedWaterMannagrass, 30);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.forestGiantAngelica, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCommonRush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsPickerelweed, 5);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 15);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 5);
			
			//this.plantsSlop.addPlant(PMPPlant.berrybushStraw, 5);
			this.plantsSlop.addPlant(PMPPlant.fernOstrich, 20);
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 80);
			this.plantsSlop.addPlant(PMPPlant.shrubMeadowsweet, 10);
			break;
		case sava: 
			break;
		case swam: 
			this.plantsFloa.addPlant(PMPPlant.floatingArrowLeavedLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingDuckweed, 90);
			this.plantsFloa.addPlant(PMPPlant.floatingRedRootFloater, 30);
			this.plantsFloa.addPlant(PMPPlant.floatingVariegatedPondLily, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterChestnut, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterHyacinth, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLettuce, 10);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterLily, 5);
			this.plantsFloa.addPlant(PMPPlant.floatingWaterShield, 5);
			
			this.plantsImme.addPlant(PMPPlant.immersedArrowArum, 10);
			this.plantsImme.addPlant(PMPPlant.immersedCommonReed, 80);
			this.plantsImme.addPlant(PMPPlant.immersedGreySedge, 10);
			this.plantsImme.addPlant(PMPPlant.immersedSoftstemBulrush, 10);
			this.plantsImme.addPlant(PMPPlant.immersedWaterMannagrass, 30);
			this.plantsImme.addPlant(PMPPlant.immersedYellowFlag, 5);
			
			this.plantsLand.addPlant(PMPPlant.flowerBlueStar, 5);
			this.plantsLand.addPlant(PMPPlant.largeDrakensbergCycad, 10);
			this.plantsLand.addPlant(PMPPlant.largeGiantCane, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsCattails, 90);
			this.plantsLand.addPlant(PMPPlant.wetlandsClubrush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsCommonRush, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsPickerelweed, 20);
			this.plantsLand.addPlant(PMPPlant.wetlandsReedMannagrass, 30);
			this.plantsLand.addPlant(PMPPlant.wetlandsSwampMilkweed, 10);
			this.plantsLand.addPlant(PMPPlant.wetlandsWaterHorsetail, 25);
			this.plantsLand.addPlant(PMPPlant.wetlandsWhiteTurtlehead, 10);
			
			this.plantsSlop.addPlant(PMPPlant.grassMaiden, 50);
			break;
		}
	}
	
	public void applyDefaultTrees() {}
}
