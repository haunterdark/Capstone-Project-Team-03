package entities;

public class Campaign {
	private String campaign_id;
	private String campaign_image;
	private String campaign_name;
	private int campaign_value;
	private String campaign_start;
	private String campaign_end;
	private int campaign_status;
	private String restaurant_name;
	private String restaurant_id;
	private String restaurant_image;
	private String restaurant_address;
	private String restaurant_phone;
	private String city_name;
	private String district_name;
	private String category_name;
	private String country_name;

	

	public Campaign() {

	}
	
	public Campaign(String id, String description, int value, String start, String end, int status, String name, String image) {
		this.setCampaign_id(id);
		this.setCampaign_name(description);
		this.setCampaign_value(value);
		this.setCampaign_start(start);
		this.setCampaign_end(end);
		this.setCampaign_status(status);
		this.setRestaurant_name(name);
		this.setCampaign_image(image);
	}

	public Campaign(String id, String description, int value, String start, String end, int status, String image) {
		this.setCampaign_id(id);
		this.setCampaign_name(description);
		this.setCampaign_value(value);
		this.setCampaign_start(start);
		this.setCampaign_end(end);
		this.setCampaign_status(status);
		this.setCampaign_image(image);
	}
	public Campaign(String campaign_image, int campaign_value, String campaign_name, String campaign_start,
			String campaign_end, String restaurant_name, String restaurant_address, String restaurant_phone,
			String restauran_image, String city, String district, String category, String country, String restaurant_id) {
		this.campaign_value = campaign_value;
		this.campaign_image = campaign_image;
		this.campaign_name = campaign_name;
		this.campaign_start = campaign_start;
		this.campaign_end = campaign_end;
		this.restaurant_name = restaurant_name;
		this.setRestaurant_address(restaurant_address);
		this.setRestaurant_phone(restaurant_phone);
		this.setRestaurant_image(restauran_image);
		this.setCity_name(city);
		this.setDistrict_name(district);
		this.setCategory_name(category);
		this.setCountry_name(country);
		this.restaurant_id = restaurant_id;
	}
	public Campaign(String campaign_id, String campaign_image, String campaign_name, String restaurant_name) {
		this.campaign_id = campaign_id;
		this.campaign_image = campaign_image;
		this.campaign_name = campaign_name;
		this.restaurant_name = restaurant_name;
	}

	public Campaign(String campaign_id, int campaign_value, String campaign_image, String campaign_name,
			String restaurant_name) {
		this.campaign_value = campaign_value;
		this.campaign_id = campaign_id;
		this.campaign_image = campaign_image;
		this.campaign_name = campaign_name;
		this.restaurant_name = restaurant_name;
	}

	public Campaign(String id, String description, int value, String start, String end, String image) {
		this.setCampaign_id(id);
		this.setCampaign_name(description);
		this.setCampaign_value(value);
		this.setCampaign_start(start);
		this.setCampaign_end(end);
		this.setCampaign_image(image);
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
	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	

	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public int getCampaign_value() {
		return campaign_value;
	}

	public void setCampaign_value(int campaign_value) {
		this.campaign_value = campaign_value;
	}

	

	public String getCampaign_start() {
		return campaign_start;
	}

	public void setCampaign_start(String campaign_start) {
		this.campaign_start = campaign_start;
	}

	public String getCampaign_end() {
		return campaign_end;
	}

	public void setCampaign_end(String campaign_end) {
		this.campaign_end = campaign_end;
	}

	public int getCampaign_status() {
		return campaign_status;
	}

	public void setCampaign_status(int campaign_status) {
		this.campaign_status = campaign_status;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getCampaign_image() {
		return campaign_image;
	}

	public void setCampaign_image(String campaign_image) {
		this.campaign_image = campaign_image;
	}
	
}