package plantmegapack.worldgen;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.data.PMPDataSpawnTree;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.data.feature.PMPDataFeatureBedp;
import plantmegapack.data.feature.PMPDataFeatureBedt;
import plantmegapack.data.feature.PMPDataFeatureFarm;
import plantmegapack.data.feature.PMPDataFeatureGrou;
import plantmegapack.data.feature.PMPDataFeatureKelp;
import plantmegapack.data.feature.PMPDataFeatureReef;
import plantmegapack.data.feature.PMPDataFeatureWedg;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.tree.PMPTree;
import plantmegapack.worldgen.feature.PMPGenFeatureBedp;
import plantmegapack.worldgen.feature.PMPGenFeatureBedt;
import plantmegapack.worldgen.feature.PMPGenFeatureFarm;
import plantmegapack.worldgen.feature.PMPGenFeatureGrou;
import plantmegapack.worldgen.feature.PMPGenFeatureKelp;
import plantmegapack.worldgen.feature.PMPGenFeatureReef;
import plantmegapack.worldgen.feature.PMPGenFeatureWedg;
import plantmegapack.worldgen.spawn.PMPSpawnEpiphyte;
import plantmegapack.worldgen.spawn.PMPSpawnPlant;
import plantmegapack.worldgen.spawn.PMPSpawnTree;
import plantmegapack.worldgen.spawn.PMPSpawnVine;

public class PMPWorldGenerator
	implements IWorldGenerator
{
	private PMPWorldgenSpawn spawnData = new PMPWorldgenSpawn();
	private Map<String, PMPTree> trees = new HashMap(PMPSapling.values().length);
	
	public void init() {
		initTrees();
	}
	
	private void initTrees() {
		for (PMPSapling sapling : PMPSapling.values()) {
			try
			{
				Constructor<? extends PMPTree> bCon = sapling.treeClass.getConstructor(new Class[] { PMPSapling.class });
				PMPTree tree = (PMPTree)bCon.newInstance(new Object[] { sapling });
				if (tree != null) {
					this.trees.put(sapling.name(), tree);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void postInit() {}
	
	public void resetAllBiomeSettings() {
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			PMPDataBiome biomeData = PlantMegaPack.worldgenProfile.getBiomeData(biomeType);
			if (biomeData != null) {
				biomeData.applyDefaultSettings();
				biomeData.applyHabitatDefaultsAll();
				biomeData.applyFeatureDefaultsAll();
			}
		}
		PlantMegaPack.worldgenProfile.saveActiveProfile();
	}
	
	public PMPTree getTree(String saplingName) {
		return (PMPTree)this.trees.get(saplingName);
	}
	
	public void generate(Random random, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if ((worldIn.getWorldType() == WorldType.FLAT) || (worldIn.provider.getDimension() == -1) || (worldIn.provider.getDimension() == 1)) {
			return;
		}
		BlockPos posChunk = new BlockPos(chunkX * 16, 64, chunkZ * 16);
		if (!this.spawnData.initBiomeInfo(worldIn, posChunk)) {
			return;
		}
		if ((this.spawnData.biomeData.biome.canTreeSpawnInBiome()) && (random.nextInt(100) < PlantMegaPack.settings.worldgenTrees)) {
			if ((this.spawnData.biomeData.treeEnabled) && (random.nextInt(100) > this.spawnData.biomeData.treeEmptyChunks)) {
				generateTrees(worldIn, random, posChunk);
			}
		}
		if ((this.spawnData.biomeData.biome.canPlantSpawnInBiome()) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
			if ((this.spawnData.biomeData.plantEnabled) && (random.nextInt(100) > this.spawnData.biomeData.plantEmptyChunks)) {
				generatePlants(worldIn, random, posChunk);
			}
		}
		if ((this.spawnData.biomeData.biome.canPlantSpawnInBiome()) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
			if ((this.spawnData.biomeData.plantEnabled) && (random.nextInt(100) > this.spawnData.biomeData.plantEmptyChunks)) {
				generateEpiphytes(worldIn, random, posChunk);
			}
		}
		if ((this.spawnData.biomeData.biome.canPlantSpawnInBiome()) && (this.spawnData.biomeData.plantEnabled) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
			if (random.nextInt(100) > this.spawnData.biomeData.plantEmptyChunks) {
				generateVines(worldIn, random, posChunk);
			}
		}
		boolean marineBiome = this.spawnData.biomeData.biome.isMarineBiome();
		if ((marineBiome) || ((this.spawnData.biomeData.biome == PMPBiomeType.beac) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants))) {
			PMPDataFeatureKelp featureKelp = (PMPDataFeatureKelp)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.kelp);
			if ((featureKelp.enabled) && (random.nextInt(100) < featureKelp.genChance)) {
				PMPGenFeatureKelp.generateFeature(worldIn, posChunk, random, this.spawnData, featureKelp);
			}
		}
		if ((marineBiome) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
			PMPDataFeatureReef featureReef = (PMPDataFeatureReef)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.reef);
			if ((featureReef.enabled) && (random.nextInt(100) < featureReef.genChance)) {
				PMPGenFeatureReef.generateFeature(worldIn, posChunk, random, this.spawnData, featureReef);
			}
		}
		if (!marineBiome) {
			if ((this.spawnData.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.grou)) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
				PMPDataFeatureGrou featureGrou = (PMPDataFeatureGrou)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.grou);
				if ((featureGrou.enabled) && (random.nextInt(100) < featureGrou.genChance)) {
					PMPGenFeatureGrou.generateFeature(worldIn, posChunk, random, this.spawnData, featureGrou);
				}
			}
			if ((this.spawnData.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.bedt)) && (random.nextInt(100) < PlantMegaPack.settings.worldgenTrees)) {
				PMPDataFeatureBedt featureBedt = (PMPDataFeatureBedt)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.bedt);
				if ((featureBedt.enabled) && (random.nextInt(100) < featureBedt.genChance)) {
					PMPGenFeatureBedt.generateFeature(worldIn, posChunk, random, this.spawnData, featureBedt);
				}
			}
			if ((this.spawnData.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.bedp)) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
				PMPDataFeatureBedp featureBedp = (PMPDataFeatureBedp)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.bedp);
				if ((featureBedp.enabled) && (random.nextInt(100) < featureBedp.genChance)) {
					PMPGenFeatureBedp.generateFeature(worldIn, posChunk, random, this.spawnData, featureBedp);
				}
			}
			if (this.spawnData.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.farm)) {
				PMPDataFeatureFarm featureFarm = (PMPDataFeatureFarm)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.farm);
				if ((featureFarm.enabled) && (random.nextInt(100) < featureFarm.genChance)) {
					PMPGenFeatureFarm.generateFeature(worldIn, posChunk, random, this.spawnData, featureFarm);
				}
			}
			if ((this.spawnData.biomeData.doesBiomeHaveFeature(PMPWorldgenFeature.wedg)) && (random.nextInt(100) < PlantMegaPack.settings.worldgenPlants)) {
				PMPDataFeatureWedg featureWedg = (PMPDataFeatureWedg)this.spawnData.biomeData.getFeatureData(PMPWorldgenFeature.wedg);
				if ((featureWedg.enabled) && (random.nextInt(100) < featureWedg.genChance)) {
					PMPGenFeatureWedg.generateFeature(worldIn, posChunk, random, this.spawnData, featureWedg);
				}
			}
		}
	}
	
	private void generateTrees(World worldIn, Random random, BlockPos posChunk) {
		for (int pass = 0; pass < this.spawnData.biomeData.treePassesPerChunk; pass++) {
			BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
			if ((this.spawnData.initSpawnLocation(worldIn, posInitial)) && (this.spawnData.isTreeSpawn)) {
				PMPDataHabitat habitatData = this.spawnData.biomeData.getHabitatDataFromName(this.spawnData.habitat.name());
				if ((habitatData != null) && (random.nextInt(100) < habitatData.genRateTree)) {
					PMPDataSpawnTree treeSpawnData = this.spawnData.biomeData.getRandomTree(random, this.spawnData.habitat);
					if (treeSpawnData != null) {
						PMPTree tree = (PMPTree)this.trees.get(treeSpawnData.sapling.name());
						if (tree != null) {
							PMPSpawnTree.generateTreeCluster(worldIn, random, this.spawnData, treeSpawnData, tree, habitatData.genRateTree);
						}
					}
				}
			}
		}
	}
	
	private void generatePlants(World worldIn, Random random, BlockPos posChunk) {
		for (int pass = 0; pass < this.spawnData.biomeData.plantPassesPerChunk; pass++) {
			BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
			if (this.spawnData.initSpawnLocation(worldIn, posInitial)) {
				PMPDataHabitat habitatData = this.spawnData.biomeData.getHabitatDataFromName(this.spawnData.habitat.name());
				if ((habitatData != null) && (random.nextInt(100) < habitatData.genRatePlant)) {
					PMPDataSpawnPlant plantSpawnData = this.spawnData.biomeData.getRandomPlant(random, this.spawnData.habitat);
					if (plantSpawnData != null) {
						PMPSpawnPlant.generatePlantCluster(worldIn, random, this.spawnData, plantSpawnData, 85 + random.nextInt(15));
					}
				}
			}
		}
	}
	
	private void generateEpiphytes(World worldIn, Random random, BlockPos posChunk) {
		for (int pass = 0; pass < this.spawnData.biomeData.plantPassesPerChunk * 3; pass++) {
			BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
			if (this.spawnData.initEpiphyteSpawnLocation(worldIn, random, posInitial)) {
				PMPDataHabitat habitatData = this.spawnData.biomeData.getHabitatDataFromName(this.spawnData.habitat.name());
				if ((habitatData != null) && (random.nextInt(100) < habitatData.genRatePlant)) {
					PMPDataSpawnPlant plantSpawnData = this.spawnData.biomeData.getRandomPlant(random, this.spawnData.habitat);
					if (plantSpawnData != null) {
						PMPSpawnEpiphyte.generateEpiphyteCluster(worldIn, random, this.spawnData, habitatData, plantSpawnData);
					}
				}
			}
		}
	}
	
	private void generateVines(World worldIn, Random random, BlockPos posChunk) {
		for (int pass = 0; pass < this.spawnData.biomeData.plantPassesPerChunk; pass++) {
			BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
			if (this.spawnData.initVineSpawnLocation(worldIn, random, posInitial)) {
				PMPDataHabitat habitatData = this.spawnData.biomeData.getHabitatDataFromName(this.spawnData.habitat.name());
				if ((habitatData != null) && (random.nextInt(100) < habitatData.genRatePlant)) {
					PMPDataSpawnPlant plantSpawnData = this.spawnData.biomeData.getRandomPlant(random, this.spawnData.habitat);
					if (plantSpawnData != null) {
						PMPSpawnVine.generateVineCluster(worldIn, random, this.spawnData, habitatData, plantSpawnData);
					}
				}
			}
		}
	}
}
