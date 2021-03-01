package controller;

import command.AddProductCommand;
import command.CreateProductMapCommand;
import command.DeleteAllProductsCommand;
import command.DeleteLastCommand;
import command.DeleteProductCommand;
import command.GetProfitSummaryCommand;
import command.PrintAllProducts;
import command.SendNotificationCommand;
import model.Model;
import shop_managment_project.EMassageFromShop;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	
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
	
	public void sendNotification() {
		new SendNotificationCommand(model.getShop()).execute();
		view.getnHandler().setCustomers(model.getShop().getCostumersListNames());
		new Thread(view.getnHandler()).start();
	}
	
	public void printAllProducts() {
		new PrintAllProducts(view.getAllProductsLabel() ,model.getShop()).execute();
	}
	
	
}
