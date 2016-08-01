package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeBirchSilver
	extends PMPTree
{
	public PMPTreeBirchSilver(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		int baseHeight = 2 + random.nextInt(4);
		this.height = (7 + baseHeight);
		
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, baseHeight);
		generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 2);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.NORTH, 2, false);
		generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 2);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.EAST, 2, false);
		generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 2);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, 2, false);
		generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 2);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.WEST, 2, false);
		posStart = posStart.offset(EnumFacing.UP, 2);
		generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 1);
		generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 1, false);
		generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 1);
		generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 1, false);
		generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 1);
		generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 1, false);
		generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 1);
		generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 1, false);
		posStart = posStart.offset(EnumFacing.UP, 2);
		generateCircularLeafLayer(worldIn, random, posStart, 2, 10);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 2, 2, 10);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 1, 1, 10);
		posStart = posStart.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posStart);
	}
}
