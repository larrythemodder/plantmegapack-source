package plantmegapack.object;

public enum PMPTreeFruitType
{
	banana(true, "_tfba"),	berries(false, "_tfbe"),	oval(false, "_tfov"),	roundlarge(false, "_tfrl"),	roundsmall(false, "_tfrs");
	
	public final boolean texturePerState;
	public final String model;
	
	private PMPTreeFruitType(boolean texturePerState, String model) {
		this.texturePerState = texturePerState;
		this.model = model;
	}
}
