package shop_managment_project;

public class Product {
	
	private String name;
	private int valuePrice; 
	private int customerPrice;
	private Customer customer;
	
	
	public Product(String name, int valuePrice, int customerPrice, Customer customer) {
		setName(name);
		setValuePrice(valuePrice);
		setCustomerPrice(customerPrice);
		setCustomer(customer);
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Product:" +
				"Name: " + name + 
				"Price for the Shop: " + valuePrice + 
				"Price for costumer: " + customerPrice +
				"Customer: " + customer + "\n";
	}
	
	
	
	
	
	
	

}
