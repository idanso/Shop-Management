package Command;

import java.util.Map;

import shop_managment_project.Product;

public class DeleteLastCommand implements Command {
	
	Map<String,Product> allProducts;
	
	public DeleteLastCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
