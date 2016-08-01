package plantmegapack.object;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.translation.I18n;

public enum PMPPowder
{
	powderConditioner(0, "powderConditioner", EnumParticleTypes.VILLAGER_HAPPY),	powderDefoliant(1, "powderDefoliant", EnumParticleTypes.SMOKE_LARGE),	powderFertilizer(2, "powderFertilizer", EnumParticleTypes.VILLAGER_HAPPY);
	
	public static final int DEFAULT_ELEVATION = 4;
	public static final int DEFAULT_RADIUS = 3;
	public static final int DEFAULT_STRENGTH = 95;
	public static final int DEFAULT_USES = 10;
	public static final int MIN_ELEVATION = 1;
	public static final int MAX_ELEVATION = 6;
	public static final int MIN_RADIUS = 1;
	public static final int MAX_RADIUS = 6;
	public static final int MIN_USES = 5;
	public static final int MAX_USES = 50;
	public final int ID;
	public final String oreDictName;
	public final EnumParticleTypes particleType;
	
	private PMPPowder(int ID, String oreDictName, EnumParticleTypes particleType) {
		this.ID = ID;
		this.oreDictName = oreDictName;
		this.particleType = particleType;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
}
