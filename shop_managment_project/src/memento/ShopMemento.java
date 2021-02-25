package memento;

public class ShopMemento {

	private String productNum;

	public ShopMemento(String productNum) {
		setProductNum(productNum);
	}

	public String getProduct() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	
}
