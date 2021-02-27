package shop_managment_project;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import observer.Receiver;

public class NotificationHandler extends Task<Integer> {
	
	private ArrayList<Customer> customers;
	private StringBuffer output; //the string with the customers name
	private Label textLabel;
	
	
	public NotificationHandler(Label label) {
		this.textLabel = label;
		output = new StringBuffer();
	}
	
	public void addCustomerAdReceiver(Customer customer) {
		customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public void printToLabel() {//to delete
		for (Customer customer : customers) {
			output.append(customer.getName() + "approved notification\n");
			this.textLabel.setText(output.toString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		for (Customer customer : customers) {
			output.append(customer.getName() + "approved notification\n");
			this.textLabel.setText(output.toString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected Integer call() throws Exception {
		for (Customer customer : customers) {
			output.append(customer.getName() + "approved notification\n");
			this.textLabel.setText(output.toString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 10;
	}


	
	

}
