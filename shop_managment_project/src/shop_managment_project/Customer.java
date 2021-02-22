package shop_managment_project;

import observer.Receiver;
import observer.Sender;

public class Customer implements Sender, Receiver {

	String name;
	String number;
	boolean bNotification;
	
	
	public Customer(String name, String number, boolean bNotification) {
		setName(name);
		setNumber(number);
		setbNotification(bNotification);
	}


	public String getName() {
		return name;
	}

  
	public void setName(String name) {
		this.name = name;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public boolean isbNotification() {
		return bNotification;
	}


	public void setbNotification(boolean bNotification) {
		this.bNotification = bNotification;
	}


	@Override
	public void receiveMSG(Sender s, String msg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendMSG(Receiver r, String msg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
