package plantmegapack.core;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import plantmegapack.PlantMegaPack;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;

public class PMPIntegration
{
	public void init() {
		initButterflyMania("butterflymania");
	}
	
	private void initButterflyMania(String modID) {
		if (!Loader.isModLoaded(modID)) {
			if (PlantMegaPack.settings.debugMode) {
				PlantMegaPack.instance.logOutput("Butterfly Mania not installed: integration disabled");
			}
			return;
		}
		String msg = "butterflymania-register-foodplant";
		int plantCount = 0;
		for (PMPPlant plant : PMPPlant.values()) {
			if (plant.isButterflyPlant()) {
				Block plantBlock = PlantMegaPack.blocks.getPlantBlockByName(plant.name());
				NBTTagCompound tagCompound = new NBTTagCompound();
				if (tagCompound != null) {
					tagCompound.setInteger("blockID", Block.getIdFromBlock(plantBlock));
					tagCompound.setInteger("blockMeta", 0);
					tagCompound.setFloat("foodBlockHeight", (plant.blockType == PMPPlantBlockType.crpd) || (plant.blockType == PMPPlantBlockType.doub) ? 1.0F : 0.5F);
					tagCompound.setBoolean("onlyTopCampable", false);
					tagCompound.setString("foodBlockType", "ANY_FLOWER");
					FMLInterModComms.sendMessage(modID, msg, tagCompound);
					plantCount++;
				}
			}
		}
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput(String.format("Registered %d plants with Butterfly Mania", new Object[] { Integer.valueOf(plantCount) }));
		}
	}
}
