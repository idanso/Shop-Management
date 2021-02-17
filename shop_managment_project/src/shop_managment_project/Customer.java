package shop_managment_project;

public class Customer {

	String name;
	String number;
	boolean bNotification;
	
	
	public Customer(String name, String number, boolean bNotification) {
		super();
		this.name = name;
		this.number = number;
		this.bNotification = bNotification;
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
	
	
	
	
	
}
