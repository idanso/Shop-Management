package command;

import javafx.scene.control.Label;
import model.EMassageFromShop;
import model.Shop;

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
