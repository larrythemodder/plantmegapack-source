package plantmegapack.object;

import net.minecraft.block.material.Material;
import net.minecraft.util.text.translation.I18n;

public enum PMPPlanterType
{
	squareBamboo(Material.WOOD),	squareWood(Material.WOOD),	squareMetal(Material.IRON),	squareStone(Material.ROCK),	columnBamboo(Material.WOOD),	columnClay(Material.CLAY),	columnStone(Material.ROCK),	columnWood(Material.WOOD);
	
	public final Material material;
	
	private PMPPlanterType(Material material) {
		this.material = material;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("planterType." + name());
	}
}
