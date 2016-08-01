package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeDarkOakItalian
	extends PMPTree
{
	public PMPTreeDarkOakItalian(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (5 + random.nextInt(3));
		
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 1);
		for (int i = 0; i < 2; i++) {
			worldIn.setBlockState(posStart.offset(EnumFacing.NORTH, 1), getWoodBlockState(), 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1), getWoodBlockState(), 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 1), getWoodBlockState(), 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.WEST, 1), getWoodBlockState(), 3);
			posStart = posStart.offset(EnumFacing.DOWN, 1);
		}
		if (random.nextInt(100) < 50) {
			worldIn.setBlockState(posStart.offset(EnumFacing.NORTH, 1), getWoodBlockState(), 3);
		}
		if (random.nextInt(100) < 50) {
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1), getWoodBlockState(), 3);
		}
		if (random.nextInt(100) < 50) {
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 1), getWoodBlockState(), 3);
		}
		if (random.nextInt(100) < 50) {
			worldIn.setBlockState(posStart.offset(EnumFacing.WEST, 1), getWoodBlockState(), 3);
		}
		posStart = pos.offset(EnumFacing.UP, this.height - 2);
		generateLeafLayer(worldIn, random, posStart, 2, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 3, 2, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 2, 1, 0);
	}
}
