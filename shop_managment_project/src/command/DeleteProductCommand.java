package command;

import shop_managment_project.Shop;

public class DeleteProductCommand implements Command {
	
	private Shop shop;
	String productNum;

	public DeleteProductCommand(Shop shop, String productNum) {
		this.shop = shop;
		this.productNum = productNum;
	}

	@Override
	public void execute() {
		this.shop.deleteProduct(productNum);	
	}

}
