package command;

import model.EMassageFromShop;
import model.EProductSortType;
import model.Shop;

public class CreateProductMapCommand implements Command {

	Shop shop;
	EProductSortType eProductSortType;
	
	public CreateProductMapCommand(Shop shop, EProductSortType eProductSortType) {
		this.shop = shop;
		this.eProductSortType = eProductSortType;
	}

	
	@Override
	public EMassageFromShop execute() {
		return shop.createProductsMap(eProductSortType);
		
	}

}
