package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeDarkOakBur
	extends PMPTreeDarkOakEvergreen
{
	public PMPTreeDarkOakBur(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (14 + random.nextInt(8));
		
		generateDoubleTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 1);
		generateTrunkSpread(worldIn, random, posStart);
		
		posStart = pos.offset(EnumFacing.UP, this.height - 3);
		generateCanopy(worldIn, random, posStart);
		
		posStart = pos.offset(EnumFacing.UP, 3 + random.nextInt(2));
		
		EnumFacing direction = null;
		EnumFacing prevDirection = null;
		while (posStart.getY() < pos.getY() + (this.height - 2)) {
			while (direction == prevDirection) {
				direction = getRandomDirection(random);
			}
			prevDirection = direction;
			BlockPos posBranch = offsetToBranchStartLocation(worldIn, random, direction, posStart);
			generateAngledBranch(worldIn, posBranch.offset(direction.getOpposite(), 1), direction, 2);
			worldIn.setBlockState(posBranch.offset(EnumFacing.UP, 2).offset(direction, 2), getWoodBlockState(), 3);
			posBranch = posBranch.offset(EnumFacing.UP, 1).offset(direction, 2);
			generateLeafLayer(worldIn, random, posBranch, 1, 1, 0);
			posBranch = posBranch.offset(EnumFacing.UP, 1);
			generateLeafLayer(worldIn, random, posBranch, 2, 1, 0);
			posBranch = posBranch.offset(EnumFacing.UP, 1);
			generateLeafLayer(worldIn, random, posBranch, 1, 1, 0);
			posStart = posStart.offset(EnumFacing.UP, 1 + random.nextInt(2));
		}
	}
}
