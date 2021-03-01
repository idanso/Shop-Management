package command;

import javafx.scene.control.Label;
import shop_managment_project.EMassageFromShop;
import shop_managment_project.Shop;

public class GetProfitSummaryCommand implements Command {
	
	private Shop shop;
	private Label textLabel;
	
	public GetProfitSummaryCommand(Shop shop, Label textLabel) {
		this.shop = shop;
		this.textLabel = textLabel;
	}

	@Override
	public EMassageFromShop execute() {
		textLabel.setText(shop.profitSummaryToString());
		return null;
	}
}
