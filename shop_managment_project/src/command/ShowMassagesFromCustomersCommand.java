package command;

import shop_managment_project.NotificationHandler;

public class ShowMassagesFromCustomersCommand implements Command {
	
	NotificationHandler nHandler;

	public ShowMassagesFromCustomersCommand(NotificationHandler nHandler) {
		this.nHandler = nHandler;
	}

	@Override
	public void execute() {
		//nHandler.start();
		//nHandler.printToLabel();
		new Thread(nHandler).start();
	}
	
	
	
	
	
	

}
