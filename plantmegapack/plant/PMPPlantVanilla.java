package plantmegapack.plant;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

public enum PMPPlantVanilla {
	sapling_acacia(Blocks.SAPLING, 4, "sapling_acacia", false, -1, -1, 0, null),
	allium(Blocks.RED_FLOWER, 2, "flower_allium", false, 13, 119, 2, null),
	houstonia(Blocks.RED_FLOWER, 3, "flower_houstonia", false, 7, 193, 2, null),
	sapling_birch(Blocks.SAPLING, 2, "sapling_birch", false, -1, -1, 0, null),
	blue_orchid(Blocks.RED_FLOWER, 1, "flower_blue_orchid", false, 12, 190, 2, null),
	brown_mushroom(Blocks.BROWN_MUSHROOM, 0, "mushroom_brown", false, -1, 138, 2, Items.MUSHROOM_STEW),
	cactus(Blocks.CACTUS, -1, "cactus_side", false, 2, -1, 0, null),
	carrot(Blocks.CARROTS, -1, "carrots_stage_3", false, -1, 123, 2, Items.CARROT),
	cocoa(Blocks.COCOA, -1, "cocoa_stage_2", false, 3, 147, 0, null),
	dandelion(Blocks.YELLOW_FLOWER, 0, "flower_dandelion", false, 11, 131, 0, null),
	sapling_big_oak(Blocks.SAPLING, 5, "sapling_roofed_oak", false, -1, -1, 0, null),
	dead_bush(Blocks.DEADBUSH, 0, "deadbush", false, -1, -1, 0, null),
	double_grass(Blocks.DOUBLE_PLANT, 2, "double_plant_grass_top", true, -1, -1, 0, null),
	tall_grass(Blocks.TALLGRASS, 1, "tallgrass", true, -1, -1, 0, null),
	fern(Blocks.TALLGRASS, 2, "fern", true, -1, -1, 0, null),
	double_fern(Blocks.DOUBLE_PLANT, 3, "double_plant_fern_top", true, -1, -1, 0, null),
	sapling_jungle(Blocks.SAPLING, 3, "sapling_jungle", false, -1, -1, 0, null),
	double_syringa(Blocks.DOUBLE_PLANT, 1, "double_plant_syringa_top", false, 13, 197, 2, null),
	melon(Blocks.MELON_BLOCK, -1, "melon_side", false, -1, 198, 2, Items.MELON_SEEDS),
	sapling_oak(Blocks.SAPLING, 0, "sapling_oak", false, -1, -1, 0, null),
	tulipOrange(Blocks.RED_FLOWER, 5, "flower_tulip_orange", false, 14, 154, 2, null),
	oxeye_daisy(Blocks.RED_FLOWER, 8, "flower_oxeye_daisy", false, 7, 166, 2, null),
	double_paeonia(Blocks.DOUBLE_PLANT, 5, "double_plant_paeonia_top", false, 9, 1, 2, null),
	tulipPink(Blocks.RED_FLOWER, 7, "flower_tulip_pink", false, 9, 154, 2, null),
	poppy(Blocks.RED_FLOWER, 0, "flower_rose", false, 1, 115, 2, null),
	potato(Blocks.POTATOES, -1, "potatoes_stage_3", false, -1, 85, 2, Items.POTATO),
	pumpkin(Blocks.PUMPKIN, -1, "pumpkin_face_off", false, -1, 79, 2, Items.PUMPKIN_SEEDS),
	red_mushroom(Blocks.RED_MUSHROOM, 0, "mushroom_red", false, -1, 110, 2, Items.MUSHROOM_STEW),
	tulipRed(Blocks.RED_FLOWER, 4, "flower_tulip_red", false, 1, 154, 2, null),
	double_rose(Blocks.DOUBLE_PLANT, 4, "double_plant_rose_top", false, 1, 166, 2, null),
	reeds(Blocks.REEDS, -1, "reeds", false, -1, 129, 2, Items.REEDS),
	sapling_spruce(Blocks.SAPLING, 1, "sapling_spruce", false, -1, -1, 0, null),
	double_sunflower(Blocks.DOUBLE_PLANT, 0, "double_plant_sunflower_front", false, 11, 125, 2, null),
	vine(Blocks.VINE, -1, "vine", true, -1, -1, 0, null),
	waterlily(Blocks.WATERLILY, 0, "waterlily", true, -1, 34, 2, null),
	wheat(Blocks.WHEAT, -1, "wheat_stage_7", false, -1, 34, 2, Items.WHEAT_SEEDS),
	tulipWhite(Blocks.RED_FLOWER, 6, "flower_tulip_white", false, 7, 154, 2, null);
	
	public final Block block;
	public final int meta;
	public final String texture;
	public final boolean coloredTexture;
	public final int dyeMeta;
	public final int mapID;
	public final int conservationStatus;
	public final Item item;
	
	private PMPPlantVanilla(Block block, int meta, String texture, boolean coloredTexture, int dyeMeta, int mapID, int conservationStatus, Item item) {
		this.block = block;
		this.meta = meta;
		this.texture = texture;
		this.coloredTexture = coloredTexture;
		this.dyeMeta = dyeMeta;
		this.mapID = mapID;
		this.conservationStatus = conservationStatus;
		this.item = item;
	}
	
	public String getName() {
		return I18n.translateToLocal("mc." + name() + ".name");
	}
	
	public String getLatinName() {
		return I18n.translateToLocal("mc." + name() + ".snam");
	}
	
	public String getDescription() {
		return I18n.translateToLocal("mc." + name() + ".desc");
	}
}
