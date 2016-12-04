package plantmegapack.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockCrop;
import plantmegapack.block.PMPBlockTreeFruit;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPFoodType;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPTab;

public class PMPItemFood extends ItemFood {
	private PMPFood food;
	
	public PMPItemFood(PMPFood food) {
		super(food.foodValue, food.saturation, false);
		this.food = food;
		if (this.food.foodType.returnObject != 0) {
			setMaxStackSize(1);
		}
		if (this.food.foodType == PMPFoodType.bott) {
			setContainerItem(Items.GLASS_BOTTLE);
		}
		setUnlocalizedName(this.food.name());
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerItem(this, this.food.name());
		
		for (int i = 0; i < this.food.oreDictNames.length; i++) {
			OreDictionary.registerOre(this.food.oreDictNames[i], this);
		}
	}
	
	public PMPFood getFood() {
		return this.food;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal(getUnlocalizedName() + ".name");
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return (this.food.foodType == PMPFoodType.fdrn) || (this.food.foodType == PMPFoodType.tea) ? EnumAction.DRINK : EnumAction.EAT;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		super.onItemUseFinish(stack, worldIn, entityLiving);
		if ((this.food.foodType == PMPFoodType.fdrn) || (this.food.foodType == PMPFoodType.tea)) {
			return new ItemStack(Items.GLASS_BOTTLE);
		}
		if (this.food.foodType == PMPFoodType.bowl) {
			return new ItemStack(Items.BOWL);
		}
		return stack;
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		}
		if ((this.food.canSpawnPlant()) && (facing == EnumFacing.UP)) {
			String blockName = "";
			boolean aquatic = false;
			if (this.food == PMPFood.foodLentil) {
				blockName = "cropLentil";
			} else if (this.food == PMPFood.foodPeanuts) {
				blockName = "cropPeanut";
			} else if (this.food == PMPFood.foodQuinoaSeeds) {
				blockName = "flowerQuinoa";
			} else if (this.food == PMPFood.foodRice) {
				blockName = "cropRice";
				aquatic = true;
			} else if (this.food == PMPFood.foodWildRice) {
				blockName = "cropWildRice";
				aquatic = true;
			} else
			{
				return EnumActionResult.FAIL;
			}
			Block block = PlantMegaPack.blocks.getPlantBlockByName(blockName);
			if (block != null) {
				if (spawnCropPlant(worldIn, pos.up(aquatic ? 2 : 1), block)) {
					stack.stackSize -= 1;
					return EnumActionResult.SUCCESS;
				}
			}
			return EnumActionResult.FAIL;
		}
		if ((this.food.isTreeFruit()) && (facing == EnumFacing.DOWN)) {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			if (!block.isWood(worldIn, pos)) {
				return EnumActionResult.FAIL;
			}
			pos = pos.offset(EnumFacing.DOWN);
			if (worldIn.isAirBlock(pos)) {
				PMPBlockTreeFruit fruitBlock = PlantMegaPack.blocks.getTreeFruitBlockFromFood(this.food);
				IBlockState newState = fruitBlock.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, 0, playerIn);
				worldIn.setBlockState(pos, newState, 2);
				if (!playerIn.capabilities.isCreativeMode) {
					stack.stackSize -= 1;
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	
	private boolean spawnCropPlant(World worldIn, BlockPos pos, Block block) {
		if ((block != null) && (((PMPBlockCrop)block).canPlaceBlockAt(worldIn, pos))) {
			worldIn.setBlockState(pos, ((PMPBlockCrop)block).getDefaultState(), 2);
			return true;
		}
		return false;
	}
}
