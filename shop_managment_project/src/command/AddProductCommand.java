package command;

import java.util.Map;

import shop_managment_project.Product;
import shop_managment_project.Shop;
import view.View;
public class AddProductCommand implements Command{
	
	private Shop shop;
	private String productName;
	private int valuePrice;
	private String productNumber;
	private int customerPrice;
	private String customerName;
	private String customerNumber;
	private boolean bNotification;
	


	public AddProductCommand(Shop shop, View view) {
		this.shop = shop;
		this.productName = view.getProductName();
		this.valuePrice = view.getPriceForShop();
		this.productNumber =view.getProductNumber();
		this.customerPrice = view.getPriceForCostumer();
		this.customerName = view.getCostumerName();
		this.customerNumber = view.getCostumerPhoneNumber();
		this.bNotification = view.getNewsCostumer();
	}



	@Override
	public void execute() {
		this.shop.addProduct(productName, valuePrice, customerPrice,productNumber, customerName, customerNumber, bNotification);;
		
	}

}
