package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeAcaciaRiverBushwillow
	extends PMPTree
{
	public PMPTreeAcaciaRiverBushwillow(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		int segments = 2 + random.nextInt(2);
		int segmentHeight = 2;
		int pctLeafGen = 90;
		
		this.height = (3 + segments * segmentHeight);
		
		generateTrunk(worldIn, pos);
		
		int startHeight = 3 + random.nextInt(1);
		BlockPos posStart = pos.offset(EnumFacing.UP, startHeight);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.NORTH, 1), EnumFacing.NORTH, segments, segmentHeight, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), EnumFacing.SOUTH, segments, segmentHeight, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.EAST, 1), EnumFacing.EAST, segments, segmentHeight, pctLeafGen);
		generateBranchWithLeavesSegmented(worldIn, random, posStart.offset(EnumFacing.WEST, 1), EnumFacing.WEST, segments, segmentHeight, pctLeafGen);
		for (int i = startHeight + 2; i < this.height; i++) {
			generateCircularLeafLayer(worldIn, random, pos.offset(EnumFacing.UP, i), 3, 25);
		}
		generateCircularLeafLayer(worldIn, random, pos.offset(EnumFacing.UP, this.height), 2, 25);
	}
}
