package entities;

public class Menu {
	private int menu_id;
	private String item_name;
	private int stt;
	
	public Menu(){
		
	}
	public Menu(int id , String item, int tt){
		menu_id = id;
		item_name = item;
		stt = tt;
	}
	public Menu(int id , String item){
		menu_id = id;
		item_name = item;
	}
	
	
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	
	
}
