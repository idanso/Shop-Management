package command;

import shop_managment_project.Shop;

public class GetTotalProfitCommand implements Command {
	
	private Shop shop;
	private int totalProfit;
	
	public GetTotalProfitCommand(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void execute() {
		totalProfit = shop.getTotalProfit();
		
	}

	public int getTotalProfit() {
		return totalProfit;
	}
	
	
	

	
	
	

}
