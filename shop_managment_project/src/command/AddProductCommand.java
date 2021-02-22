package command;

import java.util.Map;

import shop_managment_project.Product;
import shop_managment_project.Shop;

public class AddProductCommand implements Command{
	
	private Shop shop;
	private Product product;
	

	public AddProductCommand(Shop shop, Product product) {
		this.shop = shop;
		this.product = product;
	}

	@Override
	public void execute() {
		this.shop.addProduct(product);
		
	}

}
