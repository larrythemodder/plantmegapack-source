package plantmegapack.tree;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.object.PMPSapling;

public class PMPTreeDarkOakEvergreen
	extends PMPTree
{
	public PMPTreeDarkOakEvergreen(PMPSapling sapling) {
		super(sapling);
	}
	
	public void generate(World worldIn, Random random, BlockPos pos) {
		this.height = (12 + random.nextInt(6));
		
		generateDoubleTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 1);
		generateTrunkSpread(worldIn, random, posStart);
		
		posStart = pos.offset(EnumFacing.UP, this.height - 3);
		generateCanopy(worldIn, random, posStart);
	}
	
	protected void generateTrunkSpread(World worldIn, Random random, BlockPos posStart) {
		int j = 3 + random.nextInt(4);
		BlockPos posTemp = posStart.offset(EnumFacing.NORTH, 1);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.NORTH, 1).offset(EnumFacing.EAST, 1);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.WEST, 1);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.WEST, 1).offset(EnumFacing.SOUTH, 1);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.SOUTH, 2);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.EAST, 1);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.EAST, 2);
		spawnTrunkDownwards(worldIn, posTemp, j);
		
		j = 3 + random.nextInt(4);
		posTemp = posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 2);
		spawnTrunkDownwards(worldIn, posTemp, j);
	}
	
	private void spawnTrunkDownwards(World worldIn, BlockPos pos, int height) {
		for (int i = 0; i < height; i++) {
			worldIn.setBlockState(pos.offset(EnumFacing.DOWN, i), getWoodBlockState(), 3);
		}
	}
	
	protected void generateCanopy(World worldIn, Random random, BlockPos pos) {
		BlockPos posStart = pos;
		generateLeafLayer(worldIn, random, posStart, 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, 1), 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), 2, 1, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 3, 2, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), 3, 2, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, 1), 3, 2, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), 3, 2, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 3, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), 3, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, 1), 3, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), 3, 1, 0);
		
		posStart = posStart.offset(EnumFacing.UP, 1);
		generateLeafLayer(worldIn, random, posStart, 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1), 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.EAST, 1), 2, 1, 0);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), 2, 1, 0);
	}
}
