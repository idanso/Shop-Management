package Command;

import shop_managment_project.Product;

public interface ProfitCommand {
	
	int getTotalProfit();
	int getProductProfit(Product product);

}
