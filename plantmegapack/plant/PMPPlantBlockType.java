package plantmegapack.plant;

import net.minecraft.block.Block;
import plantmegapack.block.PMPBlockBamboo;
import plantmegapack.block.PMPBlockBerrybush;
import plantmegapack.block.PMPBlockClimbing;
import plantmegapack.block.PMPBlockCrop;
import plantmegapack.block.PMPBlockCropAquatic;
import plantmegapack.block.PMPBlockCropDouble;
import plantmegapack.block.PMPBlockDouble;
import plantmegapack.block.PMPBlockEpiphyte;
import plantmegapack.block.PMPBlockFloating;
import plantmegapack.block.PMPBlockFloatingFlower;
import plantmegapack.block.PMPBlockFlower;
import plantmegapack.block.PMPBlockGroundcover;
import plantmegapack.block.PMPBlockImmersed;
import plantmegapack.block.PMPBlockImmersedDouble;
import plantmegapack.block.PMPBlockLand;
import plantmegapack.block.PMPBlockSeaweed;
import plantmegapack.block.PMPBlockSubmerged;
import plantmegapack.block.PMPBlockTriple;
import plantmegapack.block.PMPBlockVine;

public enum PMPPlantBlockType {
	bamb(PMPBlockBamboo.class, 5, 2),
	berr(PMPBlockBerrybush.class, 5, 4),
	clim(PMPBlockClimbing.class, 4, 3),
	crop(PMPBlockCrop.class, 5, 4),
	crpa(PMPBlockCropAquatic.class, 5, 4),
	crpd(PMPBlockCropDouble.class, 8, 7),
	doub(PMPBlockDouble.class, 2, 1),
	epip(PMPBlockEpiphyte.class, 1, 0),
	fflo(PMPBlockFloatingFlower.class, 9, 0),
	floa(PMPBlockFloating.class, 1, 0),
	flow(PMPBlockFlower.class, 9, 0),
	grou(PMPBlockGroundcover.class, 1, 0),
	immd(PMPBlockImmersedDouble.class, 2, 2),
	imme(PMPBlockImmersed.class, 1, 1),
	land(PMPBlockLand.class, 1, 0),
	seaw(PMPBlockSeaweed.class, 1, 0),
	subm(PMPBlockSubmerged.class, 1, 0),
	trpl(PMPBlockTriple.class, 3, 0),
	vine(PMPBlockVine.class, 11, 0);
	
	public final Class<? extends Block> blockClass;
	public final int models;
	public final int itemModelIndex;
	
	private PMPPlantBlockType(Class<? extends Block> blockClass, int models, int itemModelIndex) {
		this.blockClass = blockClass;
		this.models = models;
		this.itemModelIndex = itemModelIndex;
	}
	
	public boolean isTallPlant() {
		return (this == crpd) || (this == doub) || (this == immd) || (this == trpl);
	}
}
