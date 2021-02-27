package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;
import memento.ShopMemento;
import observer.Receiver;
import observer.Sender;

public class Shop implements Sender, Receiver {

	private Map<String,Product> allProducts;
	private EProductSortType productSortingType;
	private ProductsFile pFile;
	private int numOfProducts;
	private ShopMemento memento;
	private static Shop shop_Instance ;
	
	
	private Shop(File file) {
		try {
			pFile = new ProductsFile(file, "rw");
			numOfProducts = pFile.getNumOfProducts();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Shop getInstance(File file) {
		if(shop_Instance == null)
			shop_Instance = new Shop (file);
		return shop_Instance;
	}
	
	public void createProductsMap(EProductSortType eProductSortingType) {
		
		this.productSortingType = eProductSortingType;
		
		//create an empty map according to the sorting method specified
		if (this.productSortingType.equals(EProductSortType.FROM_UP))
			allProducts = new TreeMap<>(new ReverseAlphabeticMapCompare());
			
		else if (this.productSortingType.equals(EProductSortType.FROM_DOWN))
			allProducts = new TreeMap<>(new AlphabeticMapComparator());
		
		else
			allProducts = new LinkedHashMap<>();
		
		readAllProductsFromFile();
		saveAllProductsToFile();//to save the product in the file as the selected sorting type
	}
	
	public void readAllProductsFromFile() {
		try {
			if(!pFile.isEmpty()) {
				Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
				Entry<String, Product> entry;
				
				while(fIterator.hasNext()) {
					entry = fIterator.next();
					allProducts.put(entry.getKey(), entry.getValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addProduct(String productName, int valuePrice, int customerPrice, String productNumber, 
			String customerName, String customerNumber, boolean bNotification) {
		boolean productExist = false;
		
//		if(allProducts.containsKey(productNumber)) {//check if products exist in system
//			notificationHandler.removeCustomer(allProducts.get(productNumber).getCustomer()); //remove customer in case customer chnaged
//			productExist = true;
//		}
		
		
		allProducts.put(productNumber, new Product(productName, valuePrice, customerPrice,
				new Customer(customerName, customerNumber, bNotification)));
		
		//notificationHandler.addCustomerAdReceiver(allProducts.get(productNumber).getCustomer()); //add the customer to the receivers list
		
		if(!productExist) {//in case the new product not exist in system raise number of products
			numOfProducts++;
			try {
				pFile.setNumOfProducts(numOfProducts);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			saveLastProduct(productNumber); //save last product as memento only in case new product created and not updated
		}
		
		saveAllProductsToFile();
	}
	
	public boolean deleteProduct(String productNum) {
		try {
			if(!pFile.isEmpty()) {
				Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
				
				while(fIterator.hasNext()) {
					if(fIterator.next().getKey().equals(productNum)) {
						fIterator.remove();
						numOfProducts--;
						pFile.setNumOfProducts(numOfProducts);
						allProducts.clear();
						readAllProductsFromFile();
						return true;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public void deleteAllProducts() {
		try {
			pFile.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		allProducts.clear();
		
	}
	
	public int getProductProfit(String productNum) {
		Product product = allProducts.get(productNum);
		return product.getCostumerPrice() - product.getValuePrice();
		
	}
	
	public int getTotalProfit() {
		Set<Map.Entry<String, Product>> setProducts = allProducts.entrySet();
		int totalProfit = 0;	
		for (Entry<String, Product> entry : setProducts) {
			totalProfit += getProductProfit(entry.getKey());
		}
		
		return totalProfit;	
	}
	
	public void saveLastProduct(String productNum) {
		if(memento == null) {
			memento = new ShopMemento(productNum);
		}
		else
			memento.setProductNum(productNum);
	}
	
	public boolean undo(ShopMemento memento) {
		//check if first product added to system before undo can apply regards if there are products from first start from the file
		if(memento != null && allProducts.containsKey(memento.getProduct())) { 
			deleteProduct(memento.getProduct());
			saveLastProduct(null);
			System.out.println("***undo success***"); //to delete
			return true;
		}
		else {
			System.out.println("***undo fail***"); //to delete
			return false;
		}
			
	}
	
	public ArrayList<Customer> getAllCustomersWithNotification() {
		ArrayList<Customer> customersList = new ArrayList<Customer>();
		for (Entry<String, Product> entry : allProducts.entrySet()) {
			if (entry.getValue().getCustomer().getBNotification()) {
				customersList.add(entry.getValue().getCustomer());
			}
		}
		return customersList;
	}
	
	
	public ShopMemento getMemento() {
		return memento;
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
	
	
	public void receiveMSG(Sender s, String msg) {
		// TODO Auto-generated method stub
		
	}


	
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

	public void printAllProducts() {
		System.out.println("******************");
		for (Entry<String, Product> entry : allProducts.entrySet()) {
			System.out.println("\n" + "product number : " + entry.getKey());
			System.out.println(entry.toString());
		}
		
	}



}
