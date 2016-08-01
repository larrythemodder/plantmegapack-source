package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeJungleBanana
	extends PMPTree
{
	public PMPTreeJungleBanana(PMPSapling sapling) {
		super(sapling);
		this.canGrowOnSand = true;
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (4 + random.nextInt(2));
		
		BlockPos posStart = pos;
		for (int i = 0; i < this.height; i++) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		posStart = pos.offset(EnumFacing.UP, this.height - 2);
		BlockPos posTemp = posStart.offset(EnumFacing.NORTH, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState(), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.NORTH);
		posTemp = posStart.offset(EnumFacing.SOUTH, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState(), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.SOUTH);
		posTemp = posStart.offset(EnumFacing.EAST, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState(), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.EAST);
		posTemp = posStart.offset(EnumFacing.WEST, 1);
		worldIn.setBlockState(posTemp, getWoodBlockState(), 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.WEST);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		posTemp = posStart.offset(EnumFacing.NORTH, 1);
		generateUprightPalmLeaf(worldIn, posTemp, EnumFacing.NORTH);
		posTemp = posStart.offset(EnumFacing.SOUTH, 1);
		generateUprightPalmLeaf(worldIn, posTemp, EnumFacing.SOUTH);
		posTemp = posStart.offset(EnumFacing.EAST, 1);
		generateUprightPalmLeaf(worldIn, posTemp, EnumFacing.EAST);
		posTemp = posStart.offset(EnumFacing.WEST, 1);
		generateUprightPalmLeaf(worldIn, posTemp, EnumFacing.WEST);
		posStart = posStart.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posStart);
		posStart = posStart.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posStart);
	}
}
