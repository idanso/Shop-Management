package command;

import shop_managment_project.EMassageFromShop;
import shop_managment_project.Shop;

public class DeleteProductCommand implements Command {
	
	private Shop shop;
	String productNumber;

	public DeleteProductCommand(Shop shop, String productNumber) {
		this.shop = shop;
		this.productNumber = productNumber;
	}

	@Override
	public EMassageFromShop execute() {
		return this.shop.deleteProduct(productNumber);	
	}

}
