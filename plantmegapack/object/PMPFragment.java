package plantmegapack.object;

import net.minecraft.util.text.translation.I18n;

public enum PMPFragment
{
	coralFragmentRed(0, "coralFragmentRed"),	coralFragmentOrange(1, "coralFragmentOrange"),	coralFragmentYellow(2, "coralFragmentYellow"),	coralFragmentGreen(3, "coralFragmentGreen"),	coralFragmentCyan(4, "coralFragmentCyan"),	coralFragmentBlue(5, "coralFragmentBlue"),	coralFragmentPurple(6, "coralFragmentPurple"),	coralFragmentPink(7, "coralFragmentPink"),	coralFragmentWhite(8, "coralFragmentWhite");
	
	private static final PMPFragment[] ID_LOOKUP;
	public final int ID;
	public final String oreDictName;
	
	private PMPFragment(int ID, String oreDictName) {
		this.ID = ID;
		this.oreDictName = oreDictName;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
	
	public static PMPFragment getFragmentFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPFragment[values().length];
		
		PMPFragment[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPFragment var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}
