package plantmegapack.tree;

import java.util.Random;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeAcaciaBaobob
	extends PMPTree
{
	public PMPTreeAcaciaBaobob(PMPSapling sapling) {
		super(sapling);
		this.spawnRadius = 4;
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (8 + random.nextInt(5));
		
		generateDoubleTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 1);
		BlockPos posBranch = offsetToBranchStartLocation(worldIn, random, EnumFacing.NORTH, posStart);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		worldIn.setBlockState(posBranch.offset(EnumFacing.NORTH, 1), getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		posBranch = posBranch.offset(EnumFacing.NORTH, 2).offset(EnumFacing.UP, 1);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		generateLeafLayer(worldIn, random, posBranch, 2, 1, 0);
		generateLeafLayer(worldIn, random, posBranch.offset(EnumFacing.UP, 1), 1, 1, 0);
		
		posBranch = offsetToBranchStartLocation(worldIn, random, EnumFacing.SOUTH, posStart);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		worldIn.setBlockState(posBranch.offset(EnumFacing.SOUTH, 1), getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		posBranch = posBranch.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.UP, 1);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		generateLeafLayer(worldIn, random, posBranch, 2, 1, 0);
		generateLeafLayer(worldIn, random, posBranch.offset(EnumFacing.UP, 1), 1, 1, 0);
		
		posBranch = offsetToBranchStartLocation(worldIn, random, EnumFacing.WEST, posStart);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		worldIn.setBlockState(posBranch.offset(EnumFacing.WEST, 1), getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		posBranch = posBranch.offset(EnumFacing.WEST, 2).offset(EnumFacing.UP, 1);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		generateLeafLayer(worldIn, random, posBranch, 2, 1, 0);
		generateLeafLayer(worldIn, random, posBranch.offset(EnumFacing.UP, 1), 1, 1, 0);
		
		posBranch = offsetToBranchStartLocation(worldIn, random, EnumFacing.EAST, posStart);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		worldIn.setBlockState(posBranch.offset(EnumFacing.EAST, 1), getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		posBranch = posBranch.offset(EnumFacing.EAST, 2).offset(EnumFacing.UP, 1);
		worldIn.setBlockState(posBranch, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		generateLeafLayer(worldIn, random, posBranch, 2, 1, 0);
		generateLeafLayer(worldIn, random, posBranch.offset(EnumFacing.UP, 1), 1, 1, 0);
	}
}
