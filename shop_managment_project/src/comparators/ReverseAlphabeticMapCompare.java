package comparators;

import java.util.Comparator;

public class ReverseAlphabeticMapCompare  implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o2.toLowerCase().compareTo(o1.toLowerCase());
	}

}
