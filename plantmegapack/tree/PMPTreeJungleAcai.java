package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeJungleAcai
	extends PMPTree
{
	public PMPTreeJungleAcai(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (8 + random.nextInt(6));
		
		BlockPos posStart = pos;
		for (int i = 0; i < this.height; i++) {
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
			posStart = posStart.up();
		}
		posStart = pos.offset(EnumFacing.UP, this.height - 4);
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
		
		posTemp = posStart.offset(EnumFacing.NORTH, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.NORTH);
		posTemp = posStart.offset(EnumFacing.SOUTH, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.SOUTH);
		posTemp = posStart.offset(EnumFacing.EAST, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.EAST);
		posTemp = posStart.offset(EnumFacing.WEST, 2);
		generatePalmLeaf(worldIn, posTemp, EnumFacing.WEST);
		
		posStart = pos.offset(EnumFacing.UP, this.height - 2);
		worldIn.setBlockState(posStart.offset(EnumFacing.NORTH).offset(EnumFacing.EAST), getWoodBlockState(), 3);
		worldIn.setBlockState(posStart.offset(EnumFacing.NORTH).offset(EnumFacing.WEST), getWoodBlockState(), 3);
		worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH).offset(EnumFacing.EAST), getWoodBlockState(), 3);
		worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH).offset(EnumFacing.WEST), getWoodBlockState(), 3);
		
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.NORTH, 1).offset(EnumFacing.EAST, 1), 1, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.NORTH, 1).offset(EnumFacing.WEST, 1), 1, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), 1, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.WEST, 1), 1, 1, 0);
		posStart = pos.offset(EnumFacing.UP, this.height);
		spawnLeafBlock(worldIn, posStart);
		
		posStart = pos.offset(EnumFacing.UP, this.height - 1);
		spawnLeafBlock(worldIn, posStart.offset(EnumFacing.NORTH).offset(EnumFacing.EAST));
		spawnLeafBlock(worldIn, posStart.offset(EnumFacing.NORTH).offset(EnumFacing.WEST));
		spawnLeafBlock(worldIn, posStart.offset(EnumFacing.SOUTH).offset(EnumFacing.EAST));
		spawnLeafBlock(worldIn, posStart.offset(EnumFacing.SOUTH).offset(EnumFacing.WEST));
	}
}
