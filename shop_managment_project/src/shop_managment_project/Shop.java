package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;
import observer.Receiver;
import observer.Sender;

public class Shop implements Sender, Receiver {

	private Map<String,Product> allProducts;
	private EProductSortType productSortingType;
	private ProductsFile pFile;
	private int numOfProducts;
	
	
	public Shop(File file) {
		try {
			pFile = new ProductsFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void createProductsMap(EProductSortType eProductSortingType) {
		
		this.productSortingType = productSortingType;
		
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
		Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
		Entry<String, Product> entry;
		
		while(fIterator.hasNext()) {
			entry = fIterator.next();
			if(entry.getKey().equals(productNum)) {
				fIterator.remove();
				break;
			}
		}

	}
	
	public void deleteAllProducts() {
		try {
			pFile.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		allProducts.clear();
		
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

	@Override
	public void addProductToShop(Product product, String succeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProductFromShop(EMassageFromShop massage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void costumerNotification(boolean news) {
		// TODO Auto-generated method stub
		
	}

}
