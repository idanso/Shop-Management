package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;


public class Shop  {

	private Map<String,Product> allProducts;
	private EProductSortType productSortingType;
	
	private static Shop shop_Instance = null;
	private ProductsFile pFile;
	private int numOfProducts;
	
	private  Shop(File file) {
		try {
			pFile = new ProductsFile(file, "rw");
			numOfProducts =0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public static Shop getInstanceOf(File file) {
		if(shop_Instance == null)
			shop_Instance = new Shop(file);
		return shop_Instance;
	}

	
	
	public void createProductsMap(EProductSortType eProductSortingType) {
		
		this.productSortingType = eProductSortingType;
		
		//create an empty map according to the sorting method specified
		if (productSortingType == EProductSortType.FROM_UP)
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (productSortingType == EProductSortType.FROM_DOWN)
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
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
	
//	@Override
//	public void receiveMSG(Sender s, String msg) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void sendMSG(Receiver r, String msg) {
//		// TODO Auto-generated method stub
//		
//	}

}
