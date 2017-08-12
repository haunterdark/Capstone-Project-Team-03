package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Menu;

public class MenuDaoAndroid {
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

	public List<Menu> loadlistMenu() {
		List<Menu> ArrayMenu = new ArrayList<Menu>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from country";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("country_id");
				String name = rs.getString("country_name");
				ArrayMenu.add(new Menu(id, name,0));
			}
			String sql1 = "select * from category";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			ResultSet rs1 = statement1.executeQuery();
			while (rs1.next()) {
				int id1 = rs1.getInt("category_id");
				String name1 = rs1.getString("category_name");
				ArrayMenu.add(new Menu(id1, name1,1));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayMenu;
	}
	public List<Menu> loadlistcity() {
		List<Menu> ArrayMenu = new ArrayList<Menu>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from city";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("city_id");
				String name = rs.getString("city_name");
				ArrayMenu.add(new Menu(id, name));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayMenu;
	}
	public List<Menu> loadlistdistrict(String city_name) {
		List<Menu> ArrayMenu = new ArrayList<Menu>();
		int city_id = getcityid(city_name);
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from district where city_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,city_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("district_id");
				String name = rs.getString("district_name");
				ArrayMenu.add(new Menu(id, name));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayMenu;
	}
	public int getcityid(String city_name) {
		int id = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select city_id from city where city_name = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, city_name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("city_id");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
}
