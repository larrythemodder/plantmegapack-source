package plantmegapack.block;

import net.minecraft.util.IStringSerializable;

public enum PMPBlockHalf
	implements IStringSerializable
{
	lower(0),	upper(1);
	
	private static final PMPBlockHalf[] META_LOOKUP;
	private final int meta;
	
	private PMPBlockHalf(int meta) {
		this.meta = meta;
	}
	
	public int getMetadata() {
		return this.meta;
	}
	
	public String getName() {
		return name();
	}
	
	public static PMPBlockHalf byMetadata(int meta) {
		if ((meta < 0) || (meta >= META_LOOKUP.length)) {
			meta = 0;
		}
		return META_LOOKUP[meta];
	}
	
	static
	{
		META_LOOKUP = new PMPBlockHalf[values().length];
		
		PMPBlockHalf[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPBlockHalf var3 = var0[var2];
			META_LOOKUP[var3.getMetadata()] = var3;
		}
	}
}
