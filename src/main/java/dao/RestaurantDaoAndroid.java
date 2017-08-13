package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Restaurant;
import entities.RestaurantAndroid;

public class RestaurantDaoAndroid {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=yes&characterEncoding=UTF-8";

	// Get status value
	public ArrayList<Restaurant> loadlistresapi(String restaurant_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *,city_name,district_name,category_name,country_name from restaurant r INNER JOIN city c ON (r.city_id = c.city_id) INNER JOIN district d ON (r.district_id = d.district_id) INNER JOIN category c1 ON (r.category_id = c1.category_id) INNER JOIN country c2 ON (r.country_id = c2.country_id) where restaurant_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, restaurant_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				String name = rs.getString("restaurant_name");
				String image = rs.getString("restaurant_image");
				String phone = rs.getString("restaurant_phone");
				String address = rs.getString("restaurant_address");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(name, phone, address, city, district, category, country, image));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRes;
	}

	public ArrayList<RestaurantAndroid> loadlistresapiforsearch(int iddanhmuc, int sttdanhmuc) {
		ArrayList<RestaurantAndroid> listRes = new ArrayList<RestaurantAndroid>();
		try {
			if (sttdanhmuc == 0) {
				listRes = loadlistrescounapiforsearch(iddanhmuc);
			} else if (sttdanhmuc == 1) {
				listRes = loadlistrescateapiforsearch(iddanhmuc);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRes;
	}

	public ArrayList<RestaurantAndroid> loadlistrescounapiforsearch(int iddanhmuc) {
		ArrayList<RestaurantAndroid> listRes = new ArrayList<RestaurantAndroid>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id, restaurant_name, restaurant_image, country_name, restaurant_address, city_name, district_name from restaurant r INNER JOIN country c1 ON (r.country_id = c1.country_id) "
					+ "INNER JOIN city c2 ON (r.city_id = c2.city_id) INNER JOIN district d ON (r.district_id = d.district_id) where r.country_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, iddanhmuc);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("restaurant_name");
				String image = rs.getString("restaurant_image");
				String coun = rs.getString("country_name");
				String id = rs.getString("restaurant_id");
				String add = rs.getString("restaurant_address");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String address = add+", "+district+", "+city;
				listRes.add(new RestaurantAndroid(name, image, coun,id,address));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRes;
	}
	public ArrayList<RestaurantAndroid> loadlistrescateapiforsearch(int iddanhmuc) {
		ArrayList<RestaurantAndroid> listRes = new ArrayList<RestaurantAndroid>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id, restaurant_name, restaurant_image, category_name, restaurant_address, city_name, district_name  "
					+ "from restaurant r INNER JOIN category c1 ON (r.category_id = c1.category_id) INNER JOIN city c2 ON (r.city_id = c2.city_id) INNER JOIN district d ON (r.district_id = d.district_id) where r.category_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, iddanhmuc);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("restaurant_name");
				String image = rs.getString("restaurant_image");
				String coun = rs.getString("category_name");
				String id = rs.getString("restaurant_id");
				String add = rs.getString("restaurant_address");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String address = add+", "+district+", "+city;
				listRes.add(new RestaurantAndroid(name, image, coun,id,address));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRes;
	}
	public boolean checkconnectres(String customer_id, String restaurant_id) {
		boolean found= false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_id, restaurant_id from customer_restaurant_relationship where customer_id =? and restaurant_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
			statement.setString(2, restaurant_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				found = true;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
	public void addconnectres(String customer_id, String restaurant_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO customer_restaurant_relationship(customer_id,restaurant_id) values(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
			statement.setString(2, restaurant_id);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void disconnectres(String customer_id, String restaurant_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "DELETE FROM customer_restaurant_relationship where customer_id =? && restaurant_id =?  ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
			statement.setString(2, restaurant_id);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
