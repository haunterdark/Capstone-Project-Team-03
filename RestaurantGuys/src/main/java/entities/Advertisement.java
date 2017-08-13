package entities;

public class Advertisement {
	private String advertisement_id;
	private String advertisement_image;
	private String advertisement_name;
	private String advertisement_start;
	private String advertisement_end;
	private int advertisement_status;
	private String restaurant_name;
	private String restaurant_image;
	private String restaurant_address;
	private String restaurant_phone;
	private String city_name;
	private String district_name;
	private String category_name;
	private String country_name;
	private String restaurant_id;

	public Advertisement() {

	}

	public Advertisement(String ads_image, String ads_name, String ads_start, String ads_end, String restaurant_name,
			String restaurant_address, String restaurant_phone, String restauran_image, String city, String district,
			String category, String country, String id) {
		this.advertisement_image = ads_image;
		this.advertisement_name = ads_name;
		this.advertisement_start = ads_start;
		this.advertisement_end = ads_end;
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

	public Advertisement(String id, String ads_image, String ads_name, String ads_start, String ads_end, int ads_status,
			String restaurant_name) {
		this.advertisement_id = id;
		this.advertisement_image = ads_image;
		this.advertisement_name = ads_name;
		this.advertisement_start = ads_start;
		this.advertisement_end = ads_end;
		this.advertisement_status = ads_status;
		this.restaurant_name = restaurant_name;
	}

	public Advertisement(String id, String ads_image, String ads_name, String ads_start, String ads_end,
			int ads_status) {
		this.advertisement_id = id;
		this.advertisement_image = ads_image;
		this.advertisement_name = ads_name;
		this.advertisement_start = ads_start;
		this.advertisement_end = ads_end;
		this.advertisement_status = ads_status;

	}

	public Advertisement(String ads_id, String ads_image, String ads_name, String ads_start, String ads_end) {
		this.advertisement_id = ads_id;
		this.advertisement_image = ads_image;
		this.advertisement_name = ads_name;
		this.advertisement_start = ads_start;
		this.advertisement_end = ads_end;

	}

	public Advertisement(String ads_id, String ads_image, String ads_name, String resname) {
		this.advertisement_id = ads_id;
		this.advertisement_image = ads_image;
		this.advertisement_name = ads_name;
		this.restaurant_name = resname;

	}

	public String getAdvertisement_id() {
		return advertisement_id;
	}

	public void setAdvertisement_id(String advertisement_id) {
		this.advertisement_id = advertisement_id;
	}

	public String getAdvertisement_image() {
		return advertisement_image;
	}

	public void setAdvertisement_image(String advertisement_image) {
		this.advertisement_image = advertisement_image;
	}

	public String getAdvertisement_name() {
		return advertisement_name;
	}

	public void setAdvertisement_name(String advertisement_name) {
		this.advertisement_name = advertisement_name;
	}

	public String getAdvertisement_start() {
		return advertisement_start;
	}

	public void setAdvertisement_start(String advertisement_start) {
		this.advertisement_start = advertisement_start;
	}

	public String getAdvertisement_end() {
		return advertisement_end;
	}

	public void setAdvertisement_end(String advertisement_end) {
		this.advertisement_end = advertisement_end;
	}

	public int getAdvertisement_status() {
		return advertisement_status;
	}

	public void setAdvertisement_status(int advertisement_status) {
		this.advertisement_status = advertisement_status;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
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

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	
}