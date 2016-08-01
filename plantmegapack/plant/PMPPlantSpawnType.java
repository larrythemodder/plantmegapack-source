package plantmegapack.plant;

import net.minecraft.util.text.translation.I18n;

public enum PMPPlantSpawnType {
	sing(1, 1),
	clvs(3, 2),
	clsm(4, 3),
	clmd(8, 4),
	cllg(16, 5),
	clvl(32, 6);
	
	public final int clusterAmount;
	public final int clusterRadius;
	
	private PMPPlantSpawnType(int clusterAmount, int clusterRadius) {
		this.clusterAmount = clusterAmount;
		this.clusterRadius = clusterRadius;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("spawnType." + name());
	}
}
