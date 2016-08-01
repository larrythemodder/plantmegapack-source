package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeSpruceBlack
	extends PMPTree
{
	public PMPTreeSpruceBlack(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (7 + random.nextInt(13));
		if (this.height % 2 == 0) {
			this.height -= 1;
		}
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, 1 + random.nextInt(3));
		for (int x = 0; x < this.height - 4; x += 2) {
			posStart = posStart.offset(EnumFacing.UP, 2);
			generateLeafLayer(worldIn, random, posStart, 1, 1, 0);
		}
		posStart = posStart.offset(EnumFacing.UP, 1);
		worldIn.setBlockState(posStart, getLeafBlockState(), 3);
	}
}
