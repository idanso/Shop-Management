package shop_managment_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import comparators.AlphabeticMapComparator;
import comparators.ReverseAlphabeticMapCompare;
import memento.ShopMemento;
import observer.ObserverCostumers;
import observer.ObservableShop;

public class Shop implements ObservableShop {

	private Map<String,Product> allProducts;
	
	private List <ObserverCostumers> costumersListNotification;//List of the costumer that want to receive news from the shop
	private List <String> costumersListNames;//List of the names of the costumers that received the massage from the shop
	
	private EProductSortType productSortingType;
	private EMassageFromShop Emassage;
	
	private ProductsFile pFile;
	
	private int numOfProducts;
	
	private ShopMemento memento;
	
	private ShopProfit shopProfit;
	
	private static Shop shop_Instance;
	
	private final String MASSAGE_TO_COSTUMER= "Discount";//String massage for costumers to know the news from the shop
	
	
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
		shopProfit = new ShopProfit(allProducts);
		shopProfit.calculateTotalProfit();
	}
	
	public void readAllProductsFromFile() {
		allProducts.clear();
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
	
	public void addProduct(String productName, 
						   int valuePrice, 
						   int customerPrice, 
						   String productNumber, 
						   String customerName, 
						   String customerNumber, 
						   boolean bNotification) {
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
		shopProfit.calculateTotalProfit();
	}
	
	public EMassageFromShop deleteProduct(String productNumber) {
		try {
			if(!pFile.isEmpty()) {
				Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
				
				while(fIterator.hasNext()) {
					if(fIterator.next().getKey().equals(productNumber)) {
						fIterator.remove();
						numOfProducts--;
						pFile.setNumOfProducts(numOfProducts);
						allProducts.clear();
						readAllProductsFromFile();
						return Emassage.SUCCEES;
					}
				}

			}
		
		} catch (IOException e) {
			e.printStackTrace();
			return Emassage.FAILE;
		}
		shopProfit.calculateTotalProfit();
		return	checkIfProductExist(productNumber);
		
	}
	
	public EMassageFromShop checkIfProductExist(String productNumber) {
		try {
			if(!pFile.isEmpty()) {
				Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
				
				while(fIterator.hasNext()) {
					if(fIterator.next().getKey().equals(productNumber)) 
						return Emassage.SUCCEES;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return Emassage.FAILE;
		}
		return Emassage.DOESNT_EXIST;
	}
	
	public void deleteAllProducts() {
		Iterator<Map.Entry<String, Product>> fIterator = pFile.iterator();
		
		for (int i = 0; i < numOfProducts; i++) {
			fIterator.next();
			fIterator.remove();
		}
		numOfProducts = 0;
		try {
			pFile.setNumOfProducts(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		readAllProductsFromFile();
		shopProfit.calculateTotalProfit();
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
	
	public String profitSummaryToString() {
		return shopProfit.toString();
		
	}
	
	
	


	public void printAllProducts() {
		System.out.println("******************");
		for (Entry<String, Product> entry : allProducts.entrySet()) {
			System.out.println("\n" + "product number : " + entry.getKey());
			System.out.println(entry.toString());
		}
		
	}

	@Override
	public void addCostumerObserver(ObserverCostumers o) {
		this.costumersListNotification.add(o);
		
	}

	@Override
	public void deleteCostumerObserver(ObserverCostumers o) {
		this.costumersListNotification.remove(o);
		
	}

	@Override
	public void sendNewsToCostumer(ObservableShop shop, String discountMassage) {
		for(ObserverCostumers o : costumersListNotification) {
			o.reciveMassage(this, MASSAGE_TO_COSTUMER);
		}
		
	}
	
	public void addCostumerNameRecivedMassage (String name) {
		this.costumersListNames.add(name);
	}

	



}
