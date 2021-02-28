package command;

import javafx.scene.control.Label;
import shop_managment_project.Shop;
import shop_managment_project.ShopProfit;

public class GetProfitSummaryCommand implements Command {
	
	private Shop shop;
	private Label textLabel;
	
	public GetProfitSummaryCommand(Shop shop, Label textLabel) {
		this.shop = shop;
		this.textLabel = textLabel;
	}

	@Override
	public void execute() {
		textLabel.setText(shop.profitSummaryToString());
	}
}
