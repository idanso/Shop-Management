package command;

import javafx.scene.control.Label;
import shop_managment_project.Shop;

public class GetProductProfitCommand implements Command { //maybe to delete
	
	private Shop shop;
	private int profit;
	private String productNum;
	private Label textLabel;
	
	
	public GetProductProfitCommand(Shop shop, String productNum) {
		this.shop = shop;
	}

	@Override
	public void execute() {
	//	profit = shop.getProductProfit(productNum);
		
	}

	public int getProfit() {
		return profit;
	}


}
