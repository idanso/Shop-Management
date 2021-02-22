package command;

import shop_managment_project.Shop;

public class GetProductProfitCommand implements Command {
	
	private Shop shop;
	private int profit;
	private String productNum;
	
	
	public GetProductProfitCommand(Shop shop, String productNum) {
		this.shop = shop;
	}

	@Override
	public void execute() {
		profit = shop.getProductProfit(productNum);
		
	}

	public int getProfit() {
		return profit;
	}


}
