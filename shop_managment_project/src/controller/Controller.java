package controller;

import command.AddProductCommand;
import command.CreateProductMapCommand;
import command.DeleteAllProductsCommand;
import command.DeleteLastCommand;
import command.DeleteProductCommand;
import command.GetProfitSummaryCommand;
import command.SendNotificationCommand;
import command.ShowMassagesFromCustomersCommand;
import model.Model;
import shop_managment_project.EMassageFromShop;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	private EMassageFromShop Emassage;
	
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
	
	public EMassageFromShop deleteProduct() {
		return new DeleteProductCommand(model.getShop(), view.getDeleteProductNumber()).execute();
	}
	
	public void undoProduct() {
		new DeleteLastCommand(model.getShop()).execute();
	}
	
	public void deleteAllProducts() {
		new DeleteAllProductsCommand(model.getShop()).execute();
	}
	
	public void getProfitSummary() {
		new GetProfitSummaryCommand(model.getShop(),view.getProfitSummaryLabel()).execute();
	}
	
	public void sendNotification() {
		new SendNotificationCommand(model.getShop());
	}
	
	public void printAllProducts() { // to delete for testing
		model.getShop().printAllProducts();
	}
	
	public void showCustomersMassages() {
		view.getnHandler().setCustomers(model.getShop().getAllCustomersWithNotification());
		new ShowMassagesFromCustomersCommand(view.getnHandler()).execute();
	}

	
}
