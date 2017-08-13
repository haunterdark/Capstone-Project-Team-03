package entities;

public class Customer {
	private String customer_id;
	private String customer_image;
	private String customer_name;
	private String customer_email;
	private String customer_phone;
	private String customer_address;
	private int customer_status;
	private String restaurant_name;
	private String city_name;
	private String district_name;
	private int city_id;
	private int district;

	public Customer() {

	}
	public Customer(String customer_id, String customer_image, String customer_name, String customer_email,
			String customer_phone, String customer_address,  int customer_status, String restaurant_name,String city, String distriict) {
		this.customer_id = customer_id;
		this.customer_image = customer_image;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.setCustomer_address(customer_address);
		this.customer_status = customer_status;
		this.restaurant_name = restaurant_name;
		this.setCity_name(city);
		this.setDistrict_name(distriict);
		
	}
	public Customer(String customer_id, String customer_image, String customer_name, String customer_email,
			String customer_phone,  int customer_status) {
		this.customer_id = customer_id;
		this.customer_image = customer_image;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.customer_status = customer_status;
		
	}
	public Customer(String customer_id, String customer_image, String customer_name, String customer_email,
			String customer_phone, String customer_address, int customer_status,String city, String distriict) {
		this.customer_id = customer_id;
		this.customer_image = customer_image;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.setCustomer_address(customer_address);
		this.customer_status = customer_status;
		this.setCity_name(city);
		this.setDistrict_name(distriict);
	}
	
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) {
		this.district = district;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_image() {
		return customer_image;
	}
	public void setCustomer_image(String customer_image) {
		this.customer_image = customer_image;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public int getCustomer_status() {
		return customer_status;
	}
	public void setCustomer_status(int customer_status) {
		this.customer_status = customer_status;
	}
	
}
