package model;

import java.io.File;

import shop_managment_project.Shop;


public class Model {
	
	private Shop shop;
	File file;

	public Model(String fileName) {
		this.file = new File(fileName);
		this.shop = Shop.getInstance(file);
	}

	public Shop getShop() {
		return shop;
	}
	
	

}
