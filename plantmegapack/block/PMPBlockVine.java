package plantmegapack.block;

import net.minecraft.block.BlockVine;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;

public class PMPBlockVine
	extends BlockVine
	implements IPMPPlant
{
	private PMPDataPlant plantData;
	
	public PMPBlockVine(PMPDataPlant plantData) {
		this.plantData = plantData;
		PMPBlockCore.initPlantBlock(this, this.plantData, PMPItemPlant.class);
	}
	
	public PMPDataPlant getPlantData() {
		return this.plantData;
	}
}
