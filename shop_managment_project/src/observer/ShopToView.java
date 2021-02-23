package observer;

import shop_managment_project.EMassageFromShop;
import shop_managment_project.Product;

public interface ShopToView {
	void addProductToShop(Product product, String succeed);//return product and massage if succeed
	void removeProductFromShop(EMassageFromShop massage);
	void costumerNotification(boolean news);
}
