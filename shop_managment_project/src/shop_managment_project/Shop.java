package shop_managment_project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import Observer.Receiver;
import Observer.Sender;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;

public class Shop implements Sender, Receiver {

	Map<String,Product> allProducts;
	eProductSortType productSortingType;
	ArrayList<Receiver> allReceivers;
		
	
	
	public Shop(Map<String, Product> productsMap, eProductSortType productSortingType) {
		//creatingvan empty list of the costumers that want notifications
		allReceivers = new ArrayList<>();
		
		//create an empty map according to the sorting method specified
		if (productSortingType == eProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (productSortingType == eProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
		
	}
	
	public void addProduct(Product product) {
		
	}
                             

	@Override
	public void receiveMSG(Sender s, String msg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendMSG(Receiver r, String msg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
}
