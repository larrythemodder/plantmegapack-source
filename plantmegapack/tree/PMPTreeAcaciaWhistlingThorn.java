package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeAcaciaWhistlingThorn
	extends PMPTree
{
	public PMPTreeAcaciaWhistlingThorn(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (2 + random.nextInt(3));
		
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height);
		int pctLeafGen = 60;
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.NORTH, 1), EnumFacing.NORTH, 2 + random.nextInt(2), 2, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), EnumFacing.SOUTH, 2 + random.nextInt(2), 2, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.EAST, 1), EnumFacing.EAST, 2 + random.nextInt(2), 2, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.WEST, 1), EnumFacing.WEST, 2 + random.nextInt(2), 2, pctLeafGen);
	}
}
