package command;

import javafx.scene.control.Label;
import model.EMassageFromShop;
import model.Shop;

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
