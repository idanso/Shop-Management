package command;

import javafx.scene.control.Label;
import shop_managment_project.EMassageFromShop;
import shop_managment_project.Shop;

public class PrintAllProducts implements Command {
	
	Label textLabel;
	Shop shop;
	
	
	public PrintAllProducts(Label textLabel, Shop shop) {
		this.textLabel = textLabel;
		this.shop = shop;
	}
	
	@Override
	public EMassageFromShop execute() {
		textLabel.setText(shop.printAllProducts());
		return null;
	}


}
