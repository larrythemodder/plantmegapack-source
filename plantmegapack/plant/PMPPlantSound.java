package plantmegapack.plant;

import net.minecraft.block.SoundType;

public enum PMPPlantSound {
	gras(SoundType.PLANT),
	grav(SoundType.GROUND),
	seaw(SoundType.PLANT),
	wood(SoundType.WOOD);
	
	public final SoundType soundType;
	
	private PMPPlantSound(SoundType soundType) {
		this.soundType = soundType;
	}
}
