package shop_managment_project;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Label;


public class NotificationHandler extends Label implements Runnable{
	
	private ArrayList<Customer> customers;
	private StringBuffer output; //the string with the customers name
	
	
	public NotificationHandler() {
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
			Platform.runLater(() -> setText(output.toString()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		for (Customer customer : customers) {
			output.append(customer.getName() + ": received massage\n");
			Platform.runLater(() -> setText(output.toString()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
