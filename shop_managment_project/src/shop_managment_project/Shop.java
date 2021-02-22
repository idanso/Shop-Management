package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import command.AddProductCommand;
import command.DeleteAllProductsCommand;
import command.DeleteLastCommand;
import command.DeleteProductCommand;
import command.GetProductProfitCommand;
import command.SendNotificationCommand;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;
import observer.Receiver;
import observer.Sender;

public class Shop implements Sender, Receiver {

	Map<String,Product> allProducts;
	eProductSortType productSortingType;
	File file;
	ProductsFile pFile;
	
	GetProductProfitCommand getProfitCommand;
	AddProductCommand addProdauctCommand;
	DeleteAllProductsCommand deleteAllProductsCommand;
	DeleteLastCommand deleteLastCommand;
	DeleteProductCommand deleteProductCommand;
	SendNotificationCommand sendNotificationCommand;
	
	
	
	public Shop(eProductSortType productSortingType, String fileName) {
		
		
		//create an empty map according to the sorting method specified
		if (productSortingType == eProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (productSortingType == eProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
		
		file = new File(fileName);
		try {
			pFile = new ProductsFile<>(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//call commands constructors
//		getProfitCommand = new GetProfitCommand(allProducts);
//		addProdauctCommand = new AddProductCommand(allProducts);
//		deleteAllProductsCommand = new DeleteAllProductsCommand(allProducts);
//		deleteLastCommand = new DeleteLastCommand(allProducts);
//		deleteProductCommand = new DeleteProductCommand(allProducts);
//		sendNotificationCommand = new SendNotificationCommand();
	}
	
	public void addProduct(Product product) {
		

	}
	
	public void deleteProduct(String productNum) {
		

	}
	
	public void deleteAllProducts() {

	}
	
	public void deleteLastProduct() {
		
	}
	
	public int getProductProfit(String productNum) {
		return 0;
		
	}
	
	public int getTotalProfit() {
		return 0;
		
	}
	
	public void sendNotifications() {
		
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
