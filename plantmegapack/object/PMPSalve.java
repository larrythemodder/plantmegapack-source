package plantmegapack.object;

import net.minecraft.util.text.translation.I18n;

public enum PMPSalve
{
	salveFireResist(0, "fireResist", 12),	salveHealth(1, "healing", 6),	salveNightVision(2, "nightVision", 16),	salveStrength(3, "strength", 5),	salveSwiftness(4, "swiftness", 1),	salveWaterBreathing(5, "waterBreathing", 13);
	
	public static final int DEFAULT_SETTING = 30;
	public static final int DEFAULT_SETTING_HEAL = 20;
	public static final int MIN_SETTING = 10;
	public static final int MAX_SETTING = 120;
	private static final PMPSalve[] ID_LOOKUP;
	public final int ID;
	public final String shortName;
	public final int potionID;
	
	private PMPSalve(int ID, String shortName, int potionID) {
		this.ID = ID;
		this.shortName = shortName;
		this.potionID = potionID;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
	
	public String getShortLocalizedName() {
		return I18n.translateToLocal("gui." + this.shortName);
	}
	
	public static PMPSalve getSalveFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPSalve[values().length];
		
		PMPSalve[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPSalve var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}
