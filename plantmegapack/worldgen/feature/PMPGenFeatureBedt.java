package plantmegapack.worldgen.feature;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataSpawnTree;
import plantmegapack.data.feature.PMPDataFeatureBedt;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.tree.PMPTree;
import plantmegapack.worldgen.PMPWorldGenerator;
import plantmegapack.worldgen.PMPWorldgenSpawn;
import plantmegapack.worldgen.spawn.PMPSpawnTree;

public abstract class PMPGenFeatureBedt {
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureBedt featureData) {
		int RADIUS = 3;
		for (int i = 0; i < featureData.genDensity; i++) {
			BlockPos posInitial = new BlockPos(posChunk.getX() + 3 + random.nextInt(13), 0, posChunk.getZ() + 3 + random.nextInt(13));
			if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (spawnData.habitat == PMPHabitat.bedg)) {
				PMPDataSpawnTree treeSpawnData = featureData.getRandomTreeSpawnData(random);
				if (treeSpawnData != null) {
					PMPTree tree = PlantMegaPack.worldGenerator.getTree(treeSpawnData.sapling.name());
					if ((tree != null) && (PMPSpawnTree.canTreeSpawnHere(worldIn, spawnData.spawnPos, tree))) {
						tree.generate(worldIn, random, spawnData.spawnPos);
					}
				}
			}
		}
	}
}
