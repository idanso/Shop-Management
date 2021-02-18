package Command;

import java.util.ArrayList;

import Observer.Receiver;

public class SendNotificationCommand implements Command {
	
	ArrayList<Receiver> allReceivers;

	public SendNotificationCommand(ArrayList<Receiver> allReceivers) {
		this.allReceivers = allReceivers;
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
