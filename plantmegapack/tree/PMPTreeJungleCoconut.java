package plantmegapack.tree;

import java.util.Random;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeJungleCoconut
	extends PMPTree
{
	public PMPTreeJungleCoconut(PMPSapling sapling) {
		super(sapling);
		this.canGrowOnSand = true;
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (5 + random.nextInt(5));
		
		BlockPos posStart = pos.offset(EnumFacing.DOWN, 1);
		
		int j = 2 + random.nextInt(2);
		int i;/*new*/
		for (i = 0; i < j; i++) {
			posStart = posStart.offset(EnumFacing.UP, 1);
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		EnumFacing trunkBend = getRandomDirection(random);
		posStart = posStart.offset(trunkBend, 1);
		j = this.height - i - 1;
		for (i = 0; i < j; i++) {
			posStart = posStart.offset(EnumFacing.UP, 1);
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		posStart = posStart.offset(trunkBend.getOpposite(), 1);
		for (i = 0; i < 2; i++) {
			posStart = posStart.offset(EnumFacing.UP, 1);
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
		}
		BlockPos posTemp = posStart.offset(EnumFacing.NORTH, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.NORTH);
		posTemp = posStart.offset(EnumFacing.SOUTH, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.SOUTH);
		posTemp = posStart.offset(EnumFacing.EAST, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.EAST);
		posTemp = posStart.offset(EnumFacing.WEST, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.WEST);
		
		posTemp = posStart.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posTemp);
		
		posTemp = posStart.offset(EnumFacing.NORTH, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.NORTH);
		
		posTemp = posStart.offset(EnumFacing.SOUTH, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.SOUTH);
		
		posTemp = posStart.offset(EnumFacing.EAST, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.EAST);
		
		posTemp = posStart.offset(EnumFacing.WEST, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.WEST);
	}
}
