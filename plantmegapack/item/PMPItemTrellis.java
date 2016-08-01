package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import plantmegapack.block.PMPBlockTrellis;

public class PMPItemTrellis
	extends ItemBlock
{
	private PMPBlockTrellis blockTrellis;
	
	public PMPItemTrellis(Block block) {
		super(block);
		this.blockTrellis = ((PMPBlockTrellis)block);
		setUnlocalizedName(block.getUnlocalizedName().substring(5));
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos posTarget = null;
		if (facing == EnumFacing.UP) {
			posTarget = pos.up();
		} else if (facing == EnumFacing.DOWN) {
			posTarget = pos.down();
		} else if (facing == EnumFacing.NORTH) {
			posTarget = pos.north();
		} else if (facing == EnumFacing.SOUTH) {
			posTarget = pos.south();
		} else if (facing == EnumFacing.WEST) {
			posTarget = pos.west();
		} else if (facing == EnumFacing.EAST) {
			posTarget = pos.east();
		}
		if ((posTarget == null) || (!worldIn.isAirBlock(posTarget))) {
			return EnumActionResult.FAIL;
		}
		int direction = MathHelper.floor_double(playerIn.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
		EnumFacing enumFacing = EnumFacing.getHorizontal(direction).getOpposite();
		if (playerIn.canPlayerEdit(posTarget, facing, stack)) {
			IBlockState iblockstate1 = this.blockTrellis.getDefaultState().withProperty(BlockDirectional.FACING, enumFacing);
			if (worldIn.setBlockState(posTarget, iblockstate1, 3)) {
				IBlockState iblockstate2 = iblockstate1.withProperty(PMPBlockTrellis.CONNECTED, Integer.valueOf(this.blockTrellis.getConnectValue(iblockstate1, worldIn, posTarget)));
				worldIn.setBlockState(posTarget, iblockstate2, 3);
			}
			SoundType soundtype = this.block.getSoundType();
			worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			stack.stackSize -= 1;
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
