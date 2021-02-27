package controller;

import java.awt.event.ActionListener;

import command.AddProductCommand;
import command.CreateProductMapCommand;
import command.DeleteAllProductsCommand;
import command.DeleteLastCommand;
import command.DeleteProductCommand;
import command.GetProductProfitCommand;
import command.GetTotalProfitCommand;
import command.SendNotificationCommand;
import command.ShowMassagesFromCustomersCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Model;
import shop_managment_project.NotificationHandler;
import shop_managment_project.Shop;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	public void createProductsMap() {
		new CreateProductMapCommand(model.getShop(), view.getTypeOfSorting()).execute();
	}

	public void addProduct() {
		new AddProductCommand(model.getShop(), view).execute();
		
	}
	
	public void deleteProduct() {
		new DeleteProductCommand(model.getShop(), view.getDeleteProductNumber()).execute();
	}
	
	public void undoProduct() {
		new DeleteLastCommand(model.getShop()).execute();
	}
	
	public void deleteAllProducts() {
		new DeleteAllProductsCommand(model.getShop());
	}
	
	public void getProductProfit() {
//		new GetProductProfitCommand(model.getShop(), view.getProfitProduct());
	}
	
	public void getTotalProfit() {
		new GetTotalProfitCommand(model.getShop());
	}
	
	public void sendNotification() {
		new SendNotificationCommand(model.getShop());
	}
	
	public void printAllProducts() { // to delete for testing
		model.getShop().printAllProducts();
	}
	
	public void showCustomersMassages() {
		NotificationHandler	nHandler = new NotificationHandler(view.getMassagesLabel());
		nHandler.setCustomers(model.getShop().getAllCustomersWithNotification());
		new ShowMassagesFromCustomersCommand(nHandler).execute();
	}
	
//	EventHandler<ActionEvent> addProduct = new EventHandler<ActionEvent>() {
//		@Override
//		public void handle(ActionEvent addProduct) {
//			System.out.println("idannnnnnnnnnnnnnnnnnnnnnnnnn");
//			new AddProductCommand(model.getShop(), view);
//			
//		}
//	};
	
//	class AddProductListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(java.awt.event.ActionEvent e) {
//			
//			
//		}
//		
//	}

}
