package plantmegapack.block;

import net.minecraft.util.IStringSerializable;

public enum PMPBlockThird
	implements IStringSerializable
{
	lower(0),	middle(1),	upper(2);
	
	private static final PMPBlockThird[] META_LOOKUP;
	private final int meta;
	
	private PMPBlockThird(int meta) {
		this.meta = meta;
	}
	
	public int getMetadata() {
		return this.meta;
	}
	
	public String getName() {
		return name();
	}
	
	public static PMPBlockThird byMetadata(int meta) {
		if ((meta < 0) || (meta >= META_LOOKUP.length)) {
			meta = 0;
		}
		return META_LOOKUP[meta];
	}
	
	static
	{
		META_LOOKUP = new PMPBlockThird[values().length];
		
		PMPBlockThird[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPBlockThird var3 = var0[var2];
			META_LOOKUP[var3.getMetadata()] = var3;
		}
	}
}
