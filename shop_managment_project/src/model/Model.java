package model;

import java.io.File;

import command.Command;
import shop_managment_project.Shop;


public class Model {
	
	private Shop shop;
	private Command command; //Maybe to delete
	File file;

	public Model(String fileName) {
		this.file = new File(fileName);
		this.shop = Shop.getInstance(file);
	}

	public Shop getShop() {
		return shop;
	}
	
	

}
