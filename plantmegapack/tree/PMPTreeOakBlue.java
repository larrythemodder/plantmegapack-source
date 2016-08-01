package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeOakBlue
	extends PMPTree
{
	public PMPTreeOakBlue(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (14 + random.nextInt(5));
		
		generateTrunk(worldIn, pos);
		
		BlockPos posBranch = pos.offset(EnumFacing.UP, this.height - 12);
		generateAngledBranch(worldIn, posBranch, EnumFacing.NORTH, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.EAST, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.SOUTH, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.WEST, 2);
		
		posBranch = pos.offset(EnumFacing.UP, this.height - 9);
		generateAngledBranch(worldIn, posBranch, EnumFacing.NORTH, 3);
		generateAngledBranch(worldIn, posBranch, EnumFacing.EAST, 3);
		generateAngledBranch(worldIn, posBranch, EnumFacing.SOUTH, 3);
		generateAngledBranch(worldIn, posBranch, EnumFacing.WEST, 3);
		
		posBranch = pos.offset(EnumFacing.UP, this.height - 6);
		generateAngledBranch(worldIn, posBranch, EnumFacing.NORTH, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.EAST, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.SOUTH, 2);
		generateAngledBranch(worldIn, posBranch, EnumFacing.WEST, 2);
		
		posBranch = pos.offset(EnumFacing.UP, this.height - 3);
		generateAngledBranch(worldIn, posBranch, EnumFacing.NORTH, 1);
		generateAngledBranch(worldIn, posBranch, EnumFacing.EAST, 1);
		generateAngledBranch(worldIn, posBranch, EnumFacing.SOUTH, 1);
		generateAngledBranch(worldIn, posBranch, EnumFacing.WEST, 1);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 11);
		generateSquareLeafLayer(worldIn, random, posStart, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 1, 0);
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 1, 0);
	}
}
