package command;

import shop_managment_project.EMassageFromShop;
import shop_managment_project.Shop;

public class DeleteAllProductsCommand implements Command {
	
	private Shop shop;
	
	public DeleteAllProductsCommand(Shop shop) {
		this.shop = shop;
	}

	@Override
	public EMassageFromShop execute() {
		return this.shop.deleteAllProducts();
	}

}
