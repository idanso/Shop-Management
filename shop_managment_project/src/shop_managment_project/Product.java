package shop_managment_project;

public class Product {
	
	String name;
	int valuePrice; 
	int costumerPrice;
	int productNum; //catalog number
	Customer customer;
	
	
	public Product(String name, int valuePrice, int costumerPrice, int productNum, Customer customer) {
		this.name = name;
		this.valuePrice = valuePrice;
		this.costumerPrice = costumerPrice;
		this.productNum = productNum;
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValuePrice() {
		return valuePrice;
	}

	public void setValuePrice(int valuePrice) {
		this.valuePrice = valuePrice;
	}

	public int getCostumerPrice() {
		return costumerPrice;
	}

	public void setCostumerPrice(int costumerPrice) {
		this.costumerPrice = costumerPrice;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	

}
