package shop_managment_project;

import observer.ObserverCostumers;

import java.util.Formatter;

import observer.ObservableShop;

public class Customer implements ObserverCostumers {

	private String name;
	private String number;
	private boolean bNotification;
	
	
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


	public boolean getBNotification() {
		return bNotification;
	}


	public void setbNotification(boolean bNotification) {
		this.bNotification = bNotification;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Formatter formatter = new Formatter(sb);
		formatter.format("|%-13s|%-12s|%-11b|", getName(),getNumber(), getBNotification());
		formatter.close();
		return sb.toString();
	}


	@Override
	public void reciveMassage(ObservableShop obs, String discountMassage) {
		if(obs instanceof Shop) {
			((Shop) obs).addCostumerNameRecivedMassage(this.name);
		}
		
	}


	
	
	
	
	
	
}
