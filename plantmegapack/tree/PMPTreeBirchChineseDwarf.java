package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeBirchChineseDwarf
	extends PMPTree
{
	public PMPTreeBirchChineseDwarf(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (9 + random.nextInt(7));
		if (this.height % 2 == 0) {
			this.height -= 1;
		}
		generateTrunk(worldIn, pos);
		
		BlockPos posLeaf = pos.offset(EnumFacing.UP, this.height + 1);
		spawnLeafBlock(worldIn, posLeaf);
		posLeaf = posLeaf.offset(EnumFacing.DOWN, 1);
		spawnLeafBlock(worldIn, posLeaf);
		posLeaf = posLeaf.offset(EnumFacing.DOWN, 1);
		generateLeafLayer(worldIn, random, posLeaf, 1, 1, 0);
		int stop = this.height - (4 + random.nextInt(3));
		for (int index = 0; index < stop; index++) {
			posLeaf = posLeaf.offset(EnumFacing.DOWN, 1);
			generateSquareLeafLayer(worldIn, random, posLeaf, 1, 20);
		}
		posLeaf = posLeaf.offset(EnumFacing.DOWN, 1);
		generateLeafLayer(worldIn, random, posLeaf, 1, 1, 0);
	}
}
