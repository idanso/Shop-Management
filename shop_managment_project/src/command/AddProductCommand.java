package command;

import java.util.Map;

import shop_managment_project.Product;
import shop_managment_project.Shop;

public class AddProductCommand implements Command{
	
	private Shop shop;
	private String productName;
	private int valuePrice;
	private String productNumber;
	private int customerPrice;
	private String customerName;
	private String customerNumber;
	private boolean bNotification;

	public AddProductCommand(Shop shop, String productName, int valuePrice,String productNumber, int customerPrice,
			String customerName, String customerNumber, boolean bNotification) {
		this.shop = shop;
		this.productName = productName;
		this.valuePrice = valuePrice;
		this.productNumber = productNumber;
		this.customerPrice = customerPrice;
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.bNotification = bNotification;
	}



	@Override
	public void execute() {
		this.shop.addProduct(productName, valuePrice, customerPrice,productNumber, customerName, customerNumber, bNotification);;
		
	}

}
