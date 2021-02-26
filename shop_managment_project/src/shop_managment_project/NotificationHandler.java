package shop_managment_project;

import java.util.ArrayList;

import observer.Receiver;

public class NotificationHandler extends Thread {
	
	private ArrayList<Receiver> customers;
	private StringBuffer output; //the string with the customers name
	
	
	public NotificationHandler() {
		customers = new ArrayList<>();
		output = new StringBuffer();
	}
	
	public void addCustomerAdReceiver(Customer customer) {
		customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}
	


	@Override
	public void run() {
		for (Receiver receiver : customers) {
			output.append(((Customer)receiver).getName() + "approved notification\n");
			//TODO need to add reference to the text box in the view
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
