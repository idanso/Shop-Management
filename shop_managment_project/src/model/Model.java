package model;

import java.io.File;
import shop_managment_project.Shop;


public class Model {
	
	private Shop shop;
	File file;

	public Model(String fileName) {
		file = new File(fileName);
		shop = new Shop(file);
	}

	public Shop getShop() {
		return shop;
	}
	
	

}
