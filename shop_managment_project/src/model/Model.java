package model;

import controller.Controller;
import shop_managment_project.Shop;

public class Model {
	
	private Shop shop;
	private Controller controller;

	public Model(Shop shop, Controller controller) {
		this.shop = shop;
		this.controller = controller;
	}

	public Shop getShop() {
		return shop;
	}
	
	

}
