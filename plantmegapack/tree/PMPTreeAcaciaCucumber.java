package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeAcaciaCucumber
	extends PMPTree
{
	public PMPTreeAcaciaCucumber(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (7 + random.nextInt(3));
		
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 6);
		int i = 2 + random.nextInt(3);
		generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, i);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.NORTH, i).offset(EnumFacing.UP, i), 1, 0, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.NORTH, i).offset(EnumFacing.UP, i + 1), 1, 1, 0);
		i = 2 + random.nextInt(3);
		generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, i);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, i).offset(EnumFacing.UP, i), 1, 0, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, i).offset(EnumFacing.UP, i + 1), 1, 1, 0);
		i = 2 + random.nextInt(3);
		generateAngledBranch(worldIn, posStart, EnumFacing.EAST, i);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, i).offset(EnumFacing.UP, i), 1, 0, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, i).offset(EnumFacing.UP, i + 1), 1, 1, 0);
		i = 2 + random.nextInt(3);
		generateAngledBranch(worldIn, posStart, EnumFacing.WEST, i);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.WEST, i).offset(EnumFacing.UP, i), 1, 0, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.WEST, i).offset(EnumFacing.UP, i + 1), 1, 1, 0);
		
		posStart = pos.offset(EnumFacing.UP, this.height - 1);
		generateLeafLayer(worldIn, random, posStart, 1, 0, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.UP, 1), 1, 1, 0);
	}
}
