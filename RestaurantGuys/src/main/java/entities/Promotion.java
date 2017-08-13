package entities;

public class Promotion {
	private String promotion_id;
	private String promotion_image;
	private String promotion_name;
	private String promotion_start;
	private String promotion_end;
	private int promotion_status;
	private String restaurant_id;
	private String restaurant_name;
	private String restaurant_image;
	private String restaurant_address;
	private String restaurant_phone;
	private String city_name;
	private String district_name;
	private String category_name;
	private String country_name;

	public Promotion() {

	}

	public Promotion(String promotion_image, String promotion_name, String promotion_start, String promotion_end,
			String restaurant_name, String restaurant_address, String restaurant_phone, String restauran_image,
			String city, String district, String category, String country, String id) {
		this.promotion_image = promotion_image;
		this.promotion_name = promotion_name;
		this.promotion_start = promotion_start;
		this.promotion_end = promotion_end;
		this.restaurant_name = restaurant_name;
		this.setRestaurant_address(restaurant_address);
		this.setRestaurant_phone(restaurant_phone);
		this.setRestaurant_image(restauran_image);
		this.setCity_name(city);
		this.setDistrict_name(district);
		this.setCategory_name(category);
		this.setCountry_name(country);
		this.setRestaurant_id(id);
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Promotion(String promotion_id, String promotion_image, String promtion_name, String promotion_start,
			String promotion_end) {
		this.promotion_id = promotion_id;
		this.promotion_image = promotion_image;
		this.promotion_name = promtion_name;
		this.promotion_start = promotion_start;
		this.promotion_end = promotion_end;
	}

	// public Promotion(String promotion_id, String promtion_name, String
	// promotion_start, String promotion_end) {
	// this.promotion_id = promotion_id;
	// this.promotion_name = promtion_name;
	// this.promotion_start = promotion_start;
	// this.promotion_end = promotion_end;
	// }
	public Promotion(String promotion_id, String promotion_image, String promotion_name, String restaurant_name) {
		this.promotion_id = promotion_id;
		this.promotion_image = promotion_image;
		this.promotion_name = promotion_name;
		this.restaurant_name = restaurant_name;
	}

	public Promotion(String promotion_id, String promotion_image, String promtion_name, String promotion_start,
			String promotion_end, int promotion_status) {
		this.promotion_id = promotion_id;
		this.promotion_image = promotion_image;
		this.promotion_name = promtion_name;
		this.promotion_start = promotion_start;
		this.promotion_end = promotion_end;
		this.promotion_status = promotion_status;
	}

	public Promotion(String promotion_id, String promotion_image, String promtion_name, String promotion_start,
			String promotion_end, int promotion_status, String restaurant_name) {
		this.promotion_id = promotion_id;
		this.promotion_image = promotion_image;
		this.promotion_name = promtion_name;
		this.promotion_start = promotion_start;
		this.promotion_end = promotion_end;
		this.promotion_status = promotion_status;
		this.restaurant_name = restaurant_name;
	}

	public String getRestaurant_image() {
		return restaurant_image;
	}

	public void setRestaurant_image(String restaurant_image) {
		this.restaurant_image = restaurant_image;
	}

	public String getRestaurant_address() {
		return restaurant_address;
	}

	public void setRestaurant_address(String restaurant_address) {
		this.restaurant_address = restaurant_address;
	}

	public String getRestaurant_phone() {
		return restaurant_phone;
	}

	public void setRestaurant_phone(String restaurant_phone) {
		this.restaurant_phone = restaurant_phone;
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

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(String promotion_id) {
		this.promotion_id = promotion_id;
	}

	public String getPromotion_image() {
		return promotion_image;
	}

	public void setPromotion_image(String promotion_image) {
		this.promotion_image = promotion_image;
	}

	public String getPromotion_name() {
		return promotion_name;
	}

	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}

	public String getPromotion_start() {
		return promotion_start;
	}

	public void setPromotion_start(String promotion_start) {
		this.promotion_start = promotion_start;
	}

	public String getPromotion_end() {
		return promotion_end;
	}

	public void setPromotion_end(String promotion_end) {
		this.promotion_end = promotion_end;
	}

	public int getPromotion_status() {
		return promotion_status;
	}

	public void setPromotion_status(int promotion_status) {
		this.promotion_status = promotion_status;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

}
