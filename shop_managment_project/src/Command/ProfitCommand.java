package Command;

import shop_managment_project.Product;

public interface ProfitCommand {
	
	public int getTotalProfit();
	public int getProductProfit(String productNum);
	public void calculateTotalProfit();

}
