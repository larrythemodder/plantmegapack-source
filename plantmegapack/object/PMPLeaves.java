package plantmegapack.object;

public enum PMPLeaves {
	leavesAcacia01("leavesAcacia01"),
	leavesBirch01("leavesBirch01"),
	leavesDarkOak01("leavesDarkOak01"),
	leavesJungle01("leavesJungle01"),
	leavesOak01("leavesOak01"),
	leavesSpruce01("leavesSpruce01"),
	leavesFruit01("leavesFruitwood01"),
	leavesFruit02("leavesFruitwood02"),
	leavesFruit03("leavesFruitwood03");
	
	public final String oreDictName;
	
	private PMPLeaves(String oreDictName) {
		this.oreDictName = oreDictName;
	}
	
	public static int getVariantCount(PMPLeaves leaves) {
		int count = 0;
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.leaves == leaves) {
				count++;
			}
		}
		return count;
	}
}
