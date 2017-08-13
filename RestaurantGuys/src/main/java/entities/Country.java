package entities;

public class Country {
	private int country_id;
	private String country_name;
	
	public Country(){
		
	}
	public Country(int id,String name){
		this.setCountry_id(id);
		this.setCountry_name(name);
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	
}
