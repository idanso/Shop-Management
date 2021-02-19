package Command;

import java.util.Map;

import shop_managment_project.Product;

public class GetProfitCommand implements ProfitCommand {
	
	Map<String,Product> allProducts;
	int profit;
	
	public GetProfitCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
		profit = 0;
	}

	@Override
	public int getTotalProfit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getProductProfit(String productNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void calculateTotalProfit() {
		//to fill
	}


}
