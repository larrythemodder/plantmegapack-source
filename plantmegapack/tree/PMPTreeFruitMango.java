package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeFruitMango
	extends PMPTree
{
	public PMPTreeFruitMango(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (4 + random.nextInt(2));
		
		generateFruitTree(worldIn, random, pos);
	}
}
