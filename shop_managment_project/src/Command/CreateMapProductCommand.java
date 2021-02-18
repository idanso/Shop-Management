package Command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;
import shop_managment_project.Product;
import shop_managment_project.eProductSortType;

public class CreateMapProductCommand implements Command {
	
	Map<String,Product> allProducts;

	public CreateMapProductCommand(eProductSortType sortType) {
		if (sortType == eProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (sortType == eProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	
}
