package shop_managment_project;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import Command.AddProductCommand;
import Command.DeleteAllProductsCommand;
import Command.DeleteLastCommand;
import Command.DeleteProductCommand;
import Command.GetProfitCommand;
import Command.SendNotificationCommand;
import Observer.Receiver;
import Observer.Sender;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;

public class Shop implements Sender, Receiver {

	Map<String,Product> allProducts;
	eProductSortType productSortingType;
	GetProfitCommand getProfitCommand;
	AddProductCommand addProdauctCommand;
	DeleteAllProductsCommand deleteAllProductsCommand;
	DeleteLastCommand deleteLastCommand;
	DeleteProductCommand deleteProductCommand;
	SendNotificationCommand sendNotificationCommand;
	
	
	
	public Shop(Map<String, Product> productsMap, eProductSortType productSortingType) {
		
		
		//create an empty map according to the sorting method specified
		if (productSortingType == eProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (productSortingType == eProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
		
		//call commands constructors
		getProfitCommand = new GetProfitCommand(allProducts);
		addProdauctCommand = new AddProductCommand(allProducts);
		deleteAllProductsCommand = new DeleteAllProductsCommand(allProducts);
		deleteLastCommand = new DeleteLastCommand(allProducts);
		deleteProductCommand = new DeleteProductCommand(allProducts);
		sendNotificationCommand = new SendNotificationCommand();
	}
	
	public void addProduct(Product product) {
		addProdauctCommand.execute();
	}
	
	public void deleteProduct(String productNum) {
		deleteProductCommand.execute();
	}
	
	public void deleteAllProducts() {
		deleteAllProductsCommand.execute();
	}
	
	public void deleteLastProduct() {
		deleteLastCommand.execute();
	}
	
	public void getProductProfit(String productNum) {
		getProfitCommand.getProductProfit(productNum);
		
	}
	
	public void getTotalProfit() {
		getProfitCommand.getTotalProfit();
		
	}
	
	public void sendNotifications() {
		sendNotificationCommand.execute();
		vb
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
