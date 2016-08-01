package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeBirchWhite
	extends PMPTree
{
	public PMPTreeBirchWhite(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (11 + random.nextInt(11));
		
		generateTrunk(worldIn, pos);
		
		EnumFacing direction = null;
		EnumFacing prevDirection = null;
		BlockPos posStart = pos.offset(EnumFacing.UP, 3);
		
		int i = posStart.getY();
		int maxHeight = pos.getY() + this.height;
		while (i < maxHeight) {
			while (prevDirection == direction) {
				direction = getRandomDirection(random);
			}
			prevDirection = direction;
			generateAngledBranch(worldIn, posStart, direction, 2);
			BlockPos posLeaf = posStart.offset(EnumFacing.UP, 1).offset(direction, 1);
			generateSquareLeafLayer(worldIn, random, posLeaf, 1, 10);
			posLeaf = posStart.offset(EnumFacing.UP, 2).offset(direction, 2);
			generateLeafLayer(worldIn, random, posLeaf, 1, 1, 0);
			posLeaf = posLeaf.offset(EnumFacing.UP, 1);
			spawnLeafBlock(worldIn, posLeaf);
			posStart = posStart.offset(EnumFacing.UP, 1 + random.nextInt(2));
			i = posStart.getY();
		}
		BlockPos posLeaf = pos.offset(EnumFacing.UP, this.height - 1);
		generateLeafLayer(worldIn, random, posLeaf, 1, 1, 0);
		posLeaf = posLeaf.offset(EnumFacing.UP, 1);
		spawnLeafBlock(worldIn, posLeaf);
	}
}
