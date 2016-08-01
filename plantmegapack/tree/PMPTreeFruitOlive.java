package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeFruitOlive
	extends PMPTree
{
	public PMPTreeFruitOlive(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (4 + random.nextInt(2));
		
		generateFruitTree(worldIn, random, pos);
	}
}
