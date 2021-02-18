package comparators;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import shop_managment_project.Product;

public class ReverseAlphabeticMapCompare  implements Comparator<Map.Entry<String, Product>> {

	@Override
	public int compare(Entry<String, Product> o1, Entry<String, Product> o2) {
		return o2.getKey().compareTo(o1.getKey());
	}

}
