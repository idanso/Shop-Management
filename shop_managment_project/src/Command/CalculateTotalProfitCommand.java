package Command;

import java.util.Map;

import shop_managment_project.Product;

public class CalculateTotalProfitCommand implements Command {
	
	Map<String,Product> allProducts;
	
	public CalculateTotalProfitCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
