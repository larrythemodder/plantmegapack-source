package plantmegapack.gui.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerGrass;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookEntry;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPHelper;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;

@SideOnly(Side.CLIENT)
public abstract class PMPGuiCore
{
	public static final ResourceLocation imageLogo = new ResourceLocation("plantmegapack:textures/gui/logoPMP.png");
	public static final ResourceLocation ledGRNOff = new ResourceLocation("plantmegapack:textures/gui/ledGRN0.png");
	public static final ResourceLocation ledGRNOn = new ResourceLocation("plantmegapack:textures/gui/ledGRN1.png");
	public static final String TEXNAME_PLANT = "jungleConeHeadedGuzmania";
	public static final String TEXNAME_TREE = "saplingDarkOakEvergreen";
	public static final int BORDER = 6;
	public static final int BUTTON_HEIGHT = 20;
	public static final int BUTTON_WIDTH_LARGE = 180;
	public static final int BUTTON_WIDTH_MED = 150;
	public static final int BUTTON_WIDTH_SMALL = 88;
	public static final int BUTTON_SPACING = 4;
	public static final int LED_COLOR_GREEN = 0;
	public static final int LED_COLOR_MIXED = 1;
	public static final int LED_COLOR_RED = 2;
	public static final int LED_HEIGHT = 20;
	public static final int LED_SPACING = 0;
	public static final int LED_WIDTH = 6;
	public static final int LOGO_HEIGHT = 16;
	public static final int LOGO_WIDTH = 128;
	public static final int MIN_ICON_DRAW_SIZE = 16;
	public static final int MOD_LOGO_HEIGHT = 40;
	public static final int MOD_LOGO_WIDTH = 150;
	public static final int START_Y = 36;
	public static final int TEXT_COLOR_BLUE = 2121952;
	public static final int TEXT_COLOR_DKGRN = 2134048;
	public static final int TEXT_COLOR_GRAY = 6316128;
	public static final int TEXT_COLOR_GREEN = 5296160;
	public static final int TEXT_COLOR_MEDGRN = 8437888;
	public static final int TEXT_COLOR_LTGRN = 9502624;
	public static final int TEXT_COLOR_RED = 16732224;
	public static final int TEXT_COLOR_YELLOW = 16777088;
	
	public static void drawTexture(ResourceLocation texture, int x, int y, int width, int height) {
		if (texture == null) {
			return;
		}
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrenderer = tessellator.getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float zLevel = 1.0F;
		GlStateManager.enableBlend();
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos(x, y + height, zLevel).tex(0.0D, 1.0D).endVertex();
		worldrenderer.pos(x + width, y + height, zLevel).tex(1.0D, 1.0D).endVertex();
		worldrenderer.pos(x + width, y, zLevel).tex(1.0D, 0.0D).endVertex();
		worldrenderer.pos(x, y, zLevel).tex(0.0D, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.disableBlend();
	}
	
	public static void drawColoredTexture(ResourceLocation texture, int x, int y, int width, int height) {
		if (texture == null) {
			return;
		}
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrenderer = tessellator.getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		int i = ColorizerGrass.getGrassColor(0.5D, 1.0D);
		float f1 = (i >> 16 & 0xFF) / 255.0F;
		float f2 = (i >> 8 & 0xFF) / 255.0F;
		float f3 = (i & 0xFF) / 255.0F;
		GlStateManager.color(f1, f2, f3, 1.0F);
		float zLevel = 1.0F;
		GlStateManager.enableBlend();
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos(x, y + height, zLevel).tex(0.0D, 1.0D).endVertex();
		worldrenderer.pos(x + width, y + height, zLevel).tex(1.0D, 1.0D).endVertex();
		worldrenderer.pos(x + width, y, zLevel).tex(1.0D, 0.0D).endVertex();
		worldrenderer.pos(x, y, zLevel).tex(0.0D, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.disableBlend();
	}
	
	public static void drawLED(int x, int y, int color, boolean enabled, boolean indicatorOn) {
		ResourceLocation texture;
		switch (color) {
		case 0: 
			texture = (enabled & indicatorOn) ? ledGRNOn : ledGRNOff;
			break;
		default: 
			texture = ledGRNOff;
			return;
		}
		drawTexture(texture, x, y, 6, 20);
	}
	
	public static void drawLEDBar(int x, int y, int color, int elements, int lightedElements) {
		if ((elements < 1) || (elements > 20) || (lightedElements < 0) || (lightedElements > elements)) {
			return;
		}
		for (int index = 1; index <= elements; index++) {
			drawLED(x + 6 * (index - 1), y, color, index <= lightedElements, true);
		}
	}
	
	public static void drawBlockTexture(String unlocalizedName, int xPos, int yPos, int size) {
		if ((unlocalizedName == null) || ((!unlocalizedName.isEmpty()) && (size >= 16))) {
			ResourceLocation itemTextureFile = new ResourceLocation("plantmegapack:textures/blocks/" + unlocalizedName + ".png");
			if (itemTextureFile != null) {
				drawTexture(itemTextureFile, xPos, yPos, size);
			}
		}
	}
	
	public static void drawItemTexture(String unlocalizedName, int xPos, int yPos, int size) {
		if ((unlocalizedName == null) || (unlocalizedName.isEmpty())) {
			return;
		}
		ResourceLocation itemTextureFile = new ResourceLocation("plantmegapack:textures/items/" + unlocalizedName + ".png");
		drawTexture(itemTextureFile, xPos, yPos, size);
	}
	
	public static void drawPlantTexture(String unlocalizedName, int xPos, int yPos, int size) {
		if ((unlocalizedName == null) || (unlocalizedName.isEmpty())) {
			return;
		}
		PMPDataPlant plantData = ((IPMPPlant)PlantMegaPack.blocks.getPlantBlockByName(unlocalizedName)).getPlantData();
		if ((plantData == null) || (size < 16)) {
			return;
		}
		String texture = plantData.attributes.name();
		if (plantData.attributes.category == PMPPlantCategory.bamb) {
			texture = texture + "2";
		} else if ((plantData.attributes.category == PMPPlantCategory.berr) || (plantData.attributes.category == PMPPlantCategory.crop) || (plantData.attributes.category == PMPPlantCategory.crpa)) {
			texture = texture + "4";
		} else if (plantData.attributes.category == PMPPlantCategory.clim) {
			texture = texture + "3";
		} else if (PMPPlant.hasColorVariants(plantData.attributes)) {
			texture = texture + "_red";
		} else if (plantData.attributes.blockType == PMPPlantBlockType.imme) {
			texture = texture + "1";
		} else if (plantData.attributes.blockType == PMPPlantBlockType.immd) {
			texture = texture + "2";
		} else if (plantData.attributes.blockType != PMPPlantBlockType.trpl) {
			if (plantData.attributes.blockType != PMPPlantBlockType.doub) {
				texture = texture + "0";
			}
		}
		ResourceLocation itemTextureFile = new ResourceLocation("plantmegapack:textures/blocks/" + texture + ".png");
		if (itemTextureFile != null) {
			drawTexture(itemTextureFile, xPos, yPos, size);
		}
	}
	
	public static void drawTreeTexture(String unlocalizedName, int xPos, int yPos, int size) {
		if ((unlocalizedName == null) || (unlocalizedName.isEmpty())) {
			return;
		}
		ResourceLocation itemTextureFile = new ResourceLocation("plantmegapack:textures/blocks/" + unlocalizedName + ".png");
		drawTexture(itemTextureFile, xPos, yPos, size);
	}
	
	public static void drawBookEntryTexture(PMPBookEntry entry, int xPos, int yPos, int size) {
		if (entry == null) {
			return;
		}
		String[] s = entry.textureName.split(":");
		if (s[1] == null) {
			s[1] = entry.textureName;
		}
		ResourceLocation itemTextureFile = new ResourceLocation(s[0] + ":textures/blocks/" + s[1] + ".png");
		if (entry.coloredTexture) {
			drawColoredTexture(itemTextureFile, xPos, yPos, size, size);
		} else {
			drawTexture(itemTextureFile, xPos, yPos, size);
		}
	}
	
	public static void drawItemObjectTexture(String objectName, Item item, int meta, int xPos, int yPos, int size) {
		if ((objectName == null) || (objectName.isEmpty())) {
			return;
		}
		String[] s = objectName.split(":");
		if (s[0].matches("minecraft")) {
			s[1] = PMPHelper.correctMojangItemName(s[1], item, meta);
		}
		ResourceLocation itemTextureFile = new ResourceLocation(s[0] + ":textures/items/" + s[1] + ".png");
		drawTexture(itemTextureFile, xPos, yPos, size);
	}
	
	public static void drawVanillaBlockTexture(String textureName, int xPos, int yPos, int size) {
		if ((textureName == null) || (textureName.isEmpty())) {
			return;
		}
		ResourceLocation itemTextureFile = new ResourceLocation("minecraft:textures/blocks/" + textureName + ".png");
		drawTexture(itemTextureFile, xPos, yPos, size);
	}
	
	public static void drawVanillaItemTexture(String textureName, int xPos, int yPos, int size) {
		if ((textureName == null) || (textureName.isEmpty())) {
			return;
		}
		ResourceLocation itemTextureFile = new ResourceLocation("minecraft:textures/items/" + textureName + ".png");
		drawTexture(itemTextureFile, xPos, yPos, size);
	}
	
	public static void drawTexture(ResourceLocation texture, int xPos, int yPos, int size) {
		if (texture == null) {
			return;
		}
		drawTexture(texture, xPos, yPos, size, size);
	}
}
