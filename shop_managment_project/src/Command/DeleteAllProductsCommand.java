package Command;

import java.util.Map;

import shop_managment_project.Product;

public class DeleteAllProductsCommand implements Command {
	
	Map<String,Product> allProducts;
	
	public DeleteAllProductsCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}

	@Override
	public void execute() {
		
	}

}
