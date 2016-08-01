package plantmegapack.object;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum PMPWallBracketType
{
	bamboo(Material.WOOD, SoundType.WOOD),	metal(Material.IRON, SoundType.METAL),	mineral(Material.ROCK, SoundType.STONE),	stone(Material.ROCK, SoundType.STONE),	wood(Material.WOOD, SoundType.WOOD);
	
	public final Material material;
	public final SoundType sound;
	
	private PMPWallBracketType(Material material, SoundType sound) {
		this.material = material;
		this.sound = sound;
	}
}
