package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeSpruceMartinez
	extends PMPTree
{
	public PMPTreeSpruceMartinez(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		int heightFactor = random.nextInt(3);
		switch (heightFactor) {
		case 0: 
			this.height = 19;
			heightFactor = 3;
			break;
		case 1: 
			this.height = 13;
			heightFactor = 2;
			break;
		case 2: 
		default: 
			this.height = 7;
			heightFactor = 1;
		}
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, 3);
		int i;
		for (i = 0; i < heightFactor; i++) {
			generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		for (i = 0; i < heightFactor; i++) {
			generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		for (i = 0; i < heightFactor - 1; i++) {
			generateCircularLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
		spawnLeafBlock(worldIn, posStart);
	}
}
