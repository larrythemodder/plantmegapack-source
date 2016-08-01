package plantmegapack.tree;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeSpruceRed
	extends PMPTree
{
	public PMPTreeSpruceRed(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (6 + random.nextInt(5));
		if (this.height % 2 == 0) {
			this.height += 1;
		}
		generateTrunk(worldIn, pos);
		
		IBlockState woodBlockState = getWoodBlockState();
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height / 5 + 1);
		for (int i = 0; i < this.height / 5; i++) {
			if (i == 0) {
				worldIn.setBlockState(posStart.offset(EnumFacing.NORTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.EAST), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.WEST), woodBlockState, 3);
			}
			generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		int i;
		for (i = 0; i < this.height / 6; i++) {
			if (i == 0) {
				worldIn.setBlockState(posStart.offset(EnumFacing.NORTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.EAST), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.WEST), woodBlockState, 3);
			}
			generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		int j = posStart.getY() - pos.getY();
		for (i = 0; i < (this.height - j) / 2; i++) {
			if (i == 0) {
				worldIn.setBlockState(posStart.offset(EnumFacing.NORTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.EAST), woodBlockState, 3);
				worldIn.setBlockState(posStart.offset(EnumFacing.WEST), woodBlockState, 3);
			}
			generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		generateLeafLayer(worldIn, random, posStart, 1, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posStart);
	}
}
