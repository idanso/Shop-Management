package command;

import shop_managment_project.Shop;

public class SendNotificationCommand implements Command {
	
	Shop shop;
	
	public SendNotificationCommand(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void execute() {
		shop.sendNotifications();
		
	}
}
