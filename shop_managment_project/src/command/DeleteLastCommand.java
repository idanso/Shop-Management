package command;

import shop_managment_project.Shop;

public class DeleteLastCommand implements Command {
	
	private Shop shop;
	private boolean bResult;
	
	public DeleteLastCommand(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void execute() {
		bResult = this.shop.undo(shop.getMemento());
		
	}
	
}
