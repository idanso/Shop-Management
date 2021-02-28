package shop_managment_project;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ShopProfit {
	
	private Map<String,Product> allProducts;
	private int totalProfit;
	
	public ShopProfit(Map<String, Product> allProducts) {
		setAllProducts(allProducts);
	}

	public void setAllProducts(Map<String, Product> allProducts) {
		this.allProducts = allProducts;
	}
	
	public void calculateTotalProfit() {
		Set<Map.Entry<String, Product>> setProducts = allProducts.entrySet();
		totalProfit = 0;
		for (Entry<String, Product> entry : setProducts) {
			totalProfit += getProductProfit(entry.getKey());
		}
	}
	
	public int getProductProfit(String productNum) {
		Product product = allProducts.get(productNum);
		return product.getCostumerPrice() - product.getValuePrice();
		
	}
	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("**shop total profit: " + totalProfit + "\n\n");
		Set<Map.Entry<String, Product>> setProducts = allProducts.entrySet();
		for (Entry<String, Product> entry : setProducts) {
			sb.append("*Product number: " + entry.getKey() +
					"\n Product name: " + entry.getValue().getName() + 
					"\n Profit: " + getProductProfit(entry.getKey()) + "\n\n");
		}
		return sb.toString();	
	}
}
