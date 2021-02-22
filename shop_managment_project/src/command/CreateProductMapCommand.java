package command;

import shop_managment_project.EProductSortType;
import shop_managment_project.Shop;

public class CreateProductMapCommand implements Command {

	Shop shop;
	EProductSortType eProductSortType;
	
	public CreateProductMapCommand(Shop shop, EProductSortType eProductSortType) {
		this.shop = shop;
		this.eProductSortType = eProductSortType;
	}

	
	@Override
	public void execute() {
		shop.createProductsMap(eProductSortType);
		
	}

}
