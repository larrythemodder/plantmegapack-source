package plantmegapack.data;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.core.PMPCompareDataTree;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.object.PMPSapling;
import plantmegapack.worldgen.PMPWorldgenFeature;

public class PMPDataSetTrees
{
	public TreeMap<String, PMPDataSpawnTree> trees = new TreeMap(new PMPCompareDataTree());
	
	public void clearAllTrees() {
		this.trees.clear();
	}
	
	public PMPDataSpawnTree getTreeSpawnData(String treeName) {
		return (PMPDataSpawnTree)this.trees.get(treeName);
	}
	
	@SuppressWarnings("unused")
	public PMPDataSpawnTree addTree(PMPSapling sapling, int spawnWeight) {
		if (this.trees.containsKey(sapling.name())) {
			return null;
		}
		PMPDataSpawnTree spawnData = new PMPDataSpawnTree(sapling);
		if (spawnData != null) {
			spawnData.spawnWeight = spawnWeight;
			this.trees.put(sapling.name(), spawnData);
			return spawnData;
		}
		return null;
	}
	
	public PMPDataSpawnTree addTree(String treeName) {
		if (this.trees.containsKey(treeName)) {
			return null;
		}
		PMPSapling treeAttributes = PMPSapling.getSaplingFromName(treeName);
		if (treeAttributes != null) {
			PMPDataSpawnTree spawnData = new PMPDataSpawnTree(treeAttributes);
			if (spawnData != null) {
				this.trees.put(treeName, spawnData);
				return spawnData;
			}
		}
		return null;
	}
	
	public void removeTree(String treeName) {
		this.trees.remove(treeName);
	}
	
	public int getTreeCount() {
		return this.trees.size();
	}
	
	public PMPDataSpawnTree getRandomTree(Random random) {
		if (this.trees.size() == 0) {
			return null;
		}
		HashSet<PMPDataSpawnTree> hs = new HashSet();
		
		int totalWeight = 0;
		for (Entry<String, PMPDataSpawnTree> entry : this.trees.entrySet()) {
			int spawnWeight = ((PMPDataSpawnTree)entry.getValue()).spawnWeight;
			if (spawnWeight > 0) {
				totalWeight += spawnWeight;
				hs.add(entry.getValue());
			}
		}
		if (totalWeight < 1) {
			return null;
		}
		int randomSelection = random.nextInt(totalWeight);
		int spawnWeight = 0;
		Iterator<PMPDataSpawnTree> iterator = hs.iterator();
		while (iterator.hasNext()) {
			PMPDataSpawnTree spawnData = (PMPDataSpawnTree)iterator.next();
			spawnWeight += spawnData.spawnWeight;
			if (spawnWeight >= randomSelection) {
				return spawnData;
			}
		}
		return null;
	}
	
	public boolean loadTreeDataLine(String line) {
		String[] data = line.split("!");
		PMPDataSpawnTree spawnData = (PMPDataSpawnTree)this.trees.get(data[0]);
		if (spawnData == null) {
			spawnData = addTree(data[0]);
			if (spawnData == null) {
				return false;
			}
		}
		spawnData.loadTreeData(data[1]);
		return true;
	}
	
	public void saveHabitatTreeData(PMPBiomeType biome, PMPHabitat habitat, PrintWriter printwriter) {
		for (Entry<String, PMPDataSpawnTree> entry : this.trees.entrySet()) {
			((PMPDataSpawnTree)entry.getValue()).saveHabitatTreeData(biome, habitat, printwriter);
		}
	}
	
	public void saveFeatureTreeData(PMPBiomeType biome, PMPHabitat habitat, PMPWorldgenFeature feature, PrintWriter printwriter) {
		for (Entry<String, PMPDataSpawnTree> entry : this.trees.entrySet()) {
			((PMPDataSpawnTree)entry.getValue()).saveFeatureTreeData(biome, habitat, feature, printwriter);
		}
	}
	
	public void populateTreeGuiList(PMPGuiListbox list) {
		for (Entry<String, PMPDataSpawnTree> entry : this.trees.entrySet()) {
			PMPSapling treeData = ((PMPDataSpawnTree)entry.getValue()).sapling;
			String label;
			if (treeData.isFruitTree()) {
				label = String.format("§6%s§r: %s", new Object[] { I18n.translateToLocal("gui.fruitTree"), treeData.getLocalizedName() });
			} else {
				label = String.format("§2%s§r: %s", new Object[] { I18n.translateToLocal("gui.tree"), treeData.getLocalizedName() });
			}
			list.addListboxEntry((String)entry.getKey(), label);
		}
	}
}
