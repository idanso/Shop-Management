package shop_managment_project;

public class Product {
	
	String name;
	int valuePrice; 
	int customerPrice;
	int productNum; //catalog number
	Customer customer;
	
	
	public Product(String name, int valuePrice, int customerPrice, int productNum, Customer customer) {
		setName(name);
		setValuePrice(valuePrice);
		setCustomerPrice(customerPrice);
		setProductNum(productNum);
		setCustomer(customer);;
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
		return customerPrice;
	}

	public void setCustomerPrice(int costumerPrice) {
		this.customerPrice = costumerPrice;
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
