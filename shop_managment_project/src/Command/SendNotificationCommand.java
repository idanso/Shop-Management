package Command;

import java.util.ArrayList;

import Observer.Receiver;

public class SendNotificationCommand implements Command {
	
	ArrayList<Receiver> allReceivers;

	public SendNotificationCommand() {
		allReceivers = new ArrayList<>();
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	public void addReceiver(Receiver receiver) {
		//to fill
		
	}

}
