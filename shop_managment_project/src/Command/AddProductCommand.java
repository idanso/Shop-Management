package Command;

import java.util.Map;

import shop_managment_project.Product;

public class AddProductCommand implements Command{
	
	Map<String,Product> allProducts;
	

	public AddProductCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
