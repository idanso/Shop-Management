package controller;

import java.util.Map;

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
import shop_managment_project.Product;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	private EMassageFromShop Emassage;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	public EMassageFromShop createProductsMap() {
		return new CreateProductMapCommand(model.getShop(), view.getTypeOfSorting()).execute();
	}

	public EMassageFromShop addProduct() {
		return new AddProductCommand(model.getShop(), view).execute();
		
	}
	
	public EMassageFromShop deleteProduct() {
		return new DeleteProductCommand(model.getShop(), view.getDeleteProductNumber()).execute();
	}
	
	public EMassageFromShop undoProduct() {
		return new DeleteLastCommand(model.getShop()).execute();
	}
	
	public EMassageFromShop deleteAllProducts() {
		return new DeleteAllProductsCommand(model.getShop()).execute();
	}
	
	public EMassageFromShop getProfitSummary() {
		return new GetProfitSummaryCommand(model.getShop(),view.getProfitSummaryLabel()).execute();
	}
	
	public void showCustomersMassages() {
		view.getnHandler().setCustomers(model.getShop().getAllCustomersWithNotification());
		new ShowMassagesFromCustomersCommand(view.getnHandler()).execute();
	}
	
//	public EMassageFromShop sendNotification() { need to delete**
//		return new SendNotificationCommand(model.getShop());
//	}
	
	public void printAllProducts() { // to delete for testing
		model.getShop().printAllProducts();
	}
	
	public Map<String,Product> getProductMap(){
		return model.getShop().getallProducts();
	}
	
}
