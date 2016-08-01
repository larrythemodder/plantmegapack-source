package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeOakEnglish
	extends PMPTree
{
	public PMPTreeOakEnglish(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (7 + random.nextInt(7));
		if (this.height % 2 == 0) {
			this.height -= 1;
		}
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, 2);
		for (int x = 0; x < this.height - 3; x++) {
			posStart = posStart.offset(EnumFacing.UP, 1);
			generateRandomLeafLayer(worldIn, random, posStart, 1, 1, 0, 65);
		}
		worldIn.setBlockState(pos.up(this.height), getLeafBlockState(), 3);
	}
}
