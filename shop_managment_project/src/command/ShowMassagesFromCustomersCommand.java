package command;

import model.EMassageFromShop;
import model.NotificationHandler;

public class ShowMassagesFromCustomersCommand implements Command {
	
	NotificationHandler nHandler;

	public ShowMassagesFromCustomersCommand(NotificationHandler nHandler) {
		this.nHandler = nHandler;
	}

	@Override
	public EMassageFromShop execute() {
		new Thread(nHandler).start();
		return null;
	}
	
	
	
	
	
	

}
