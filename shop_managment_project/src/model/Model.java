package model;

import java.io.File;

import command.Command;
import shop_managment_project.Shop;


public class Model {
	
	private Shop shop;
	private Command command;
	File file;

	public Model(String fileName) {
		file = new File(fileName);
		Shop shop = Shop.getInstance(file);
	}

	public Shop getShop() {
		return shop;
	}
	
	

}
