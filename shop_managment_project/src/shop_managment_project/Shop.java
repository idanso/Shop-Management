package shop_managment_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Observer.Receiver;
import Observer.Sender;

public class Shop implements Sender, Receiver {

	Map<String,Product> allProducts;
	eProductSortType productSortingType;
	ArrayList<Receiver> allReceivers;
	
	public Shop(Map<String, Product> productsMap, eProductSortType productSortingType) {
		allReceivers = new ArrayList<>();
		
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
