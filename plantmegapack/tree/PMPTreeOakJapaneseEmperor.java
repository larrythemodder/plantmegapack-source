package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeOakJapaneseEmperor
	extends PMPTree
{
	public PMPTreeOakJapaneseEmperor(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		int baseHeight = 4 + random.nextInt(4);
		this.height = (4 + baseHeight);
		
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, baseHeight);
		generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 3);
		generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 3);
		generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 3);
		generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 3);
		
		posStart = posStart.offset(EnumFacing.UP, 3);
		generateAngledBranch(worldIn, posStart, EnumFacing.NORTH, 1);
		generateAngledBranch(worldIn, posStart, EnumFacing.EAST, 1);
		generateAngledBranch(worldIn, posStart, EnumFacing.SOUTH, 1);
		generateAngledBranch(worldIn, posStart, EnumFacing.WEST, 1);
		
		posStart = pos.offset(EnumFacing.UP, baseHeight);
		generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 4, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 3, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateCircularLeafLayer(worldIn, random, posStart, 2, 0);
	}
}
