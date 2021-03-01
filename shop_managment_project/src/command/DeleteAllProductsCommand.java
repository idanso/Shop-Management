package command;

import model.EMassageFromShop;
import model.Shop;

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
