package plantmegapack.habitat;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.text.translation.I18n;

public enum PMPHabitat {
	frdp(PMPHabitatType.fres, EnumDyeColor.WHITE, 50, -1),
	frfl(PMPHabitatType.fres, EnumDyeColor.BLUE, 50, -1),
	frmr(PMPHabitatType.fres, EnumDyeColor.LIGHT_BLUE, 50, -1),
	bedg(PMPHabitatType.land, EnumDyeColor.ORANGE, 50, 50),
	open(PMPHabitatType.land, EnumDyeColor.RED, 50, 50),
	shad(PMPHabitatType.land, EnumDyeColor.BROWN, 50, -1),
	slop(PMPHabitatType.land, EnumDyeColor.PURPLE, 50, 50),
	wedg(PMPHabitatType.land, EnumDyeColor.CYAN, 50, 50),
	madp(PMPHabitatType.mari, EnumDyeColor.WHITE, 50, -1),
	mafl(PMPHabitatType.mari, EnumDyeColor.LIGHT_BLUE, 50, -1),
	mamr(PMPHabitatType.mari, EnumDyeColor.YELLOW, 50, -1),
	epip(PMPHabitatType.tree, EnumDyeColor.MAGENTA, 50, -1),
	vine(PMPHabitatType.tree, EnumDyeColor.LIME, 50, -1);
	
	public final PMPHabitatType habitatType;
	public final EnumDyeColor dyeColor;
	public final int defaultPlantRate;
	public final int defaultTreeRate;
	
	private PMPHabitat(PMPHabitatType habitatType, EnumDyeColor dyeColor, int defaultPlantRate, int defaultTreeRate) {
		this.habitatType = habitatType;
		this.dyeColor = dyeColor;
		this.defaultPlantRate = defaultPlantRate;
		this.defaultTreeRate = defaultTreeRate;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("habitat." + name());
	}
	
	public static PMPHabitat getHabitatFromName(String name) {
		for (PMPHabitat hab : PMPHabitat.values()) {
			if (name.matches(hab.name())) {
				return hab;
			}
		}
		return null;
	}
	
	public static boolean canTreeSpawnInHabitat(String habitatName) {
		return (habitatName.matches("bedg")) || (habitatName.matches("open")) || (habitatName.matches("slop")) || (habitatName.matches("wedg"));
	}
	
	public boolean canTreeSpawnInHabitat() {
		return this.defaultTreeRate >= 0;
	}
}
