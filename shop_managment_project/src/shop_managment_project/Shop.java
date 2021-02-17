package shop_managment_project;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Observer.Receiver;
import Observer.Sender;

public class Shop implements Sender, Receiver {

	Map<String,Product> productsMap;
	Set<Entry<String,Product>> productsSet; //set to be sorted
	eProductSortType productSortingType;
	
	
	public Shop(Map<String, Product> productsMap, eProductSortType productSortingType) {
		this.productsMap = productsMap;
		this.productSortingType = productSortingType;
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
