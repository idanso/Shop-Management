package model;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Label;


public class NotificationHandler extends Label implements Runnable{
	
	private ArrayList<String> customersName;
	private StringBuffer output; //the string with the customers name
	
	
	public NotificationHandler() {
		output = new StringBuffer();
	}

	public void setCustomers(ArrayList<String> customersName) {
		this.customersName = customersName;
	}

	@Override
	public void run() {
		for (String customer : customersName) {
			output.append(customer + ": received massage\n");
			Platform.runLater(() -> setText(output.toString()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
