package command;

import shop_managment_project.Shop;

public class DeleteProductCommand implements Command {
	
	private Shop shop;
	String productNumber;
	
	private boolean bResult;

	public DeleteProductCommand(Shop shop, String productNumber) {
		this.shop = shop;
		this.productNumber = productNumber;
	}

	@Override
	public void execute() {
		bResult = this.shop.deleteProduct(productNumber);	
	}

}
