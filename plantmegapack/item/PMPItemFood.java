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
import plantmegapack.core.PMPCreativeTab;
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
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerItem(this, this.food.name());
		OreDictionary.registerOre(this.food.oreDictName, this);
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
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if ((this.food.foodType == PMPFoodType.berr) || (this.food.foodType == PMPFoodType.flav) || (this.food.foodType == PMPFoodType.frui) || (this.food.foodType == PMPFoodType.seed) || (this.food.foodType == PMPFoodType.vege)) {
			list.add(/*"§6" +*/ I18n.translateToLocal(new StringBuilder().append("foodType.").append(this.food.foodType.name()).toString()) /*+ "§r"*/);
		}
		if (this.food.isTreeFruit()) {
			list.add(/*"§7" +*/ I18n.translateToLocal("gui.treeFruitTooltip") /*+ "§r"*/);
		}
		if (this.food.canCraftCereal()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.cereal");
		}
		if ((this.food == PMPFood.foodRice) || (this.food == PMPFood.foodWildRice)) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodCookedRice));
		}
		if (this.food == PMPFood.foodCornFlour) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodCornBread));
		}
		if (this.food == PMPFood.foodCorn) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodCornFlour));
		}
		if (this.food == PMPFood.foodCornTortilla) {
			PMPHelper.addCraftingItemTooltip(list, "item.foodCornTortillaFish.name");
			PMPHelper.addCraftingItemTooltip(list, "item.foodCornTortillaMeat.name");
			PMPHelper.addCraftingItemTooltip(list, "item.foodCornTortillaRice.name");
		}
		if (this.food.canCraftDessert()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.dessert");
		}
		if (this.food.canCraftDessertBowl()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.dessertBowl");
		}
		if ((this.food.foodType == PMPFoodType.vege) || (this.food == PMPFood.foodWrapCorn) || (this.food == PMPFood.foodWrapSeaweed)) {
			PMPHelper.addCraftingItemTooltip(list, "gui.foodWrap");
		}
		if ((this.food.foodType == PMPFoodType.berr) || (this.food.foodType == PMPFoodType.frui)) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodFruitBowl));
		}
		if (this.food.canCraftFruitDrink()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.fruitDrink");
		}
		if (this.food.foodType == PMPFoodType.berr) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodJelly));
		}
		if (this.food == PMPFood.foodWrapSeaweed) {
			PMPHelper.addCraftingItemTooltip(list, "gui.medicinalSalve");
		}
		if (this.food.salveID >= 0) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getSalveItem(PMPSalve.getSalveFromID(this.food.salveID)));
		}
		if (this.food == PMPFood.foodLicoriceRoot) {
			PMPHelper.addCraftingItemTooltip(list, "gui.licorice");
		}
		if ((this.food.canCraftMuffin()) || (this.food == PMPFood.foodCornBread)) {
			PMPHelper.addCraftingItemTooltip(list, "gui.muffin");
		}
		if ((this.food == PMPFood.foodRice) || (this.food == PMPFood.foodWildRice)) {
			PMPHelper.addCraftingItemTooltip(list, Items.PAPER);
		}
		if (this.food == PMPFood.foodPeanuts) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodPeanutButter));
		}
		if (this.food == PMPFood.foodPeanutButter) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodCookiePeanutButter));
		}
		if (this.food.canCraftPie()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.pie");
		}
		if ((this.food.foodType == PMPFoodType.vege) || (this.food == PMPFood.foodCornBread) || (this.food == PMPFood.foodJelly) || (this.food == PMPFood.foodPeanutButter)) {
			PMPHelper.addCraftingItemTooltip(list, "gui.sandwich");
		}
		if (this.food.canCraftSoup()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.soup");
		}
		if ((this.food.foodType == PMPFoodType.vege) || (this.food == PMPFood.foodCookedRice)) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodStirFry));
		}
		if ((this.food == PMPFood.foodBellPepperOrange) || (this.food == PMPFood.foodBellPepperRed) || (this.food == PMPFood.foodBellPepperYellow) || (this.food == PMPFood.foodCookedRice)) {
			PMPHelper.addCraftingItemTooltip(list, "gui.stuffedPepper");
		}
		if (this.food.canCraftTea()) {
			PMPHelper.addCraftingItemTooltip(list, "gui.tea");
		}
		if ((this.food.foodType == PMPFoodType.vege) || (this.food == PMPFood.foodCookedRice)) {
			PMPHelper.addCraftingItemTooltip(list, "item.foodTortilla.name");
		}
		if (this.food == PMPFood.foodCornFlour) {
			PMPHelper.addSmeltingItemTooltip(list, "item.foodCornTortilla.name");
		}
	}
}
