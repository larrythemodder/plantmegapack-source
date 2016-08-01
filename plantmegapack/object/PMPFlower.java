package plantmegapack.object;

import net.minecraft.util.text.translation.I18n;

public enum PMPFlower
{
	flowerRed(0, "flowerRed", 1),	flowerOrange(1, "flowerOrange", 14),	flowerYellow(2, "flowerYellow", 11),	flowerGreen(3, "flowerGreen", 10),	flowerCyan(4, "flowerCyan", 6),	flowerBlue(5, "flowerBlue", 12),	flowerPurple(6, "flowerPurple", 5),	flowerPink(7, "flowerPink", 9),	flowerWhite(8, "flowerWhite", 7);
	
	private static final PMPFlower[] ID_LOOKUP;
	public final int ID;
	public final String oreDictName;
	public final int dyeMeta;
	
	private PMPFlower(int ID, String oreDictName, int dyeID) {
		this.ID = ID;
		this.oreDictName = oreDictName;
		this.dyeMeta = dyeID;
	}
	
	@Deprecated
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
	
	public static PMPFlower getFlowerFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPFlower[values().length];
		
		PMPFlower[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPFlower var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}
