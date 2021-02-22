package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

	private Map<String,Product> allProducts;
	private eProductSortType productSortingType;
	private File file;
	private ProductsFile pFile;
	private int numOfProducts;
	
	
	public Shop(eProductSortType productSortingType, String fileName) {
		
		this.productSortingType = productSortingType;
		//create an empty map according to the sorting method specified
		if (productSortingType == eProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (productSortingType == eProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
		
		file = new File(fileName);
		try {
			pFile = new ProductsFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void addProduct(String productName, int valuePrice, int customerPrice, String productNumber, 
			String customerName, String customerNumber, boolean bNotification) {
		
		allProducts.put(productNumber, new Product(productName, valuePrice, customerPrice,
				new Customer(customerName, customerNumber, bNotification)));
		
		numOfProducts++;
		pFile.setNumOfProducts(numOfProducts);
		saveAllProductsToFile();
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
	
	public void saveAllProductsToFile() {
		Set<Map.Entry<String, Product>> setProducts = allProducts.entrySet();
		try {
			pFile.saveAllProducts(setProducts);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
