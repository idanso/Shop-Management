package Command;

import java.util.Map;

import shop_managment_project.Product;
import shop_managment_project.Shop;

public class AddProductCommand implements Command{
	
	private Shop shop;
	private Product product;
	private r
	

	public AddProductCommand(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
