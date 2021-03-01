package model;

import java.io.File;


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
