package plantmegapack.object;

import net.minecraft.util.text.translation.I18n;

public enum PMPRootMedicinal
{
	fireResist(0, 'c'),	health(1, 'a'),	nightVision(2, '9'),	strength(3, '6'),	swiftness(4, 'd'),	waterBreathing(5, 'b');
	
	private static final PMPRootMedicinal[] ID_LOOKUP;
	public final int ID;
	public final char formatCode;
	
	private PMPRootMedicinal(int ID, char formatCode) {
		this.ID = ID;
		this.formatCode = formatCode;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("root." + name());
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s%s§r", new Object[] { Character.valueOf(this.formatCode), getLocalizedName() });
	}
	
	public static PMPRootMedicinal getRootFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPRootMedicinal[values().length];
		
		PMPRootMedicinal[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPRootMedicinal var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}
