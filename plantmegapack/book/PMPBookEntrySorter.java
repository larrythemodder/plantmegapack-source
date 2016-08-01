package plantmegapack.book;

import java.util.Comparator;

public class PMPBookEntrySorter
	implements Comparator<PMPBookEntry>
{
	public int compare(PMPBookEntry entry1, PMPBookEntry entry2) {
		return entry1.commonName.compareTo(entry2.commonName) > 0 ? 1 : -1;
	}
}
