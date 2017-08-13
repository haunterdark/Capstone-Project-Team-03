package entities;

public class Customer_User {
	private String customer_id;
	private String customer_username;
	private String customer_password;
	private int customer_status;
	
	public Customer_User(String username, String password){
		this.setCustomer_username(username);
		this.setCustomer_password(password);
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public int getCustomer_status() {
		return customer_status;
	}
	public void setCustomer_status(int customer_status) {
		this.customer_status = customer_status;
	}

	
}
