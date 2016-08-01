package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeSpruceNorway
	extends PMPTree
{
	public PMPTreeSpruceNorway(PMPSapling sapling) {
		super(sapling);
		this.spawnRadius = 3;
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		int heightFactor = random.nextInt(4);
		switch (heightFactor) {
		case 0: 
			this.height = 31;
			heightFactor = 4;
			break;
		case 1: 
			this.height = 24;
			heightFactor = 3;
			break;
		case 2: 
			this.height = 19;
			heightFactor = 2;
			break;
		case 3: 
		default: 
			this.height = 11;
			heightFactor = 1;
		}
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, 2);
		int i;
		for (i = 0; i < heightFactor; i++) {
			generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 3);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.NORTH, 3, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 3);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.EAST, 3, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 3);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, 3, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 3);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.WEST, 3, true);
			posStart = posStart.offset(EnumFacing.UP, 2);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 2);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 2, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 2);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 2, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 2);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 2, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 2);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 2, true);
			posStart = posStart.offset(EnumFacing.UP, 2);
		}
		for (i = 0; i < heightFactor - 1; i++) {
			generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 2);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.NORTH, 2, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 2);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.EAST, 2, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 2);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, 2, true);
			generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 2);
			generateAngledBranchLeaves(worldIn, posStart, EnumFacing.WEST, 2, true);
			posStart = posStart.offset(EnumFacing.UP, 2);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 1);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.WEST, 1, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 1);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.NORTH, EnumFacing.EAST, 1, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 1);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.WEST, 1, true);
			generateDiagonalBranch(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 1);
			generateDiagonalBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, EnumFacing.EAST, 1, true);
			posStart = posStart.offset(EnumFacing.UP, 2);
		}
		generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 1);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.NORTH, 1, true);
		generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 1);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.EAST, 1, true);
		generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 1);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.SOUTH, 1, true);
		generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 1);
		generateAngledBranchLeaves(worldIn, posStart, EnumFacing.WEST, 1, true);
		posStart = posStart.offset(EnumFacing.UP, 1);
		if (worldIn.isAirBlock(posStart)) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		if (worldIn.isAirBlock(posStart)) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		if (worldIn.isAirBlock(posStart)) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		generateLeafLayer(worldIn, random, posStart, 1, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		if (worldIn.isAirBlock(posStart)) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		generateLeafLayer(worldIn, random, posStart, 1, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		worldIn.setBlockToAir(posStart);
		spawnLeafBlock(worldIn, posStart);
		posStart = posStart.offset(EnumFacing.UP, 1);
		worldIn.setBlockToAir(posStart);
		spawnLeafBlock(worldIn, posStart);
	}
}
