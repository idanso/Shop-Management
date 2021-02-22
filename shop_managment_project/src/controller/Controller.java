package controller;

import command.AddProductCommand;
import model.Model;
import shop_managment_project.Shop;
import view.MainWindow;

public class Controller {
	
	private MainWindow view;
	private Model model;
	
	
	public Controller(MainWindow view, Model model) {
		this.view = view;
		this.model = model;
	}



	public void addProduct() {
		
		//new AddProductCommand(shop, valuePrice, productNumber, customerPrice, customerName, customerNumber, bNotification);
		
	}
	
	public void deleteProduct() {
		
	}
	
	public void undoProduct() {
		
	}
	
	public void deleteAllProducts() {
		
	}
	
	public void getProductProfit() {
		
	}
	
	public void getTotalProfit() {
		
	}
	
	public void sendNotification() {
		
	}
	
	

}
