package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Advertisement;

public class AdvertisementDaoAndroid {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone";

	public ArrayList<Advertisement> PrintList() {
		ArrayList<Advertisement> listAd = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT advertisement_id,advertisement_image,advertisement_name, restaurant_name FROM advertisement  inner join restaurant  on (advertisement.restaurant_id = restaurant.restaurant_id) order by advertisement_id desc;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String resname = rs.getString("restaurant_name");
				listAd.add(new Advertisement(id, image, name, resname));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAd;
	}

	public ArrayList<Advertisement> PrintAdsClicked(String adsid) {
		ArrayList<Advertisement> listAd = new ArrayList<Advertisement>();
		String resid = getresidfromadsid(adsid);
		String city = getcitynamefromresid(resid);
		String district = getdistrictnamefromresid(resid);
		String category = getcategorynamefromresid(resid);
		String country = getcountrynamefromresid(resid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT advertisement_image,advertisement_name,advertisement_start,advertisement_end,restaurant_name,restaurant_address,restaurant_phone,restaurant_image FROM advertisement inner join restaurant on (advertisement.restaurant_id=restaurant.restaurant_id) where advertisement_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, adsid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				String resname = rs.getString("restaurant_name");
				String resadd = rs.getString("restaurant_address");
				String resphone = rs.getString("restaurant_phone");
				String resimage = rs.getString("restaurant_image");
				listAd.add(new Advertisement(image, name,start,end, resname, resadd, resphone, resimage, city, district, category, country, resid));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAd;
	}

	public String getresidfromadsid(String adsid) {
		String id = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_id FROM advertisement where advertisement_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, adsid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				 id = rs.getString("restaurant_id");
			}
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	public String getcitynamefromresid(String resid) {
		String cityname = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT city_name FROM city c inner join restaurant r on (c.city_id = r.city_id) where restaurant_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, resid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				cityname = rs.getString("city_name");
			}
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityname;
	}
	
	public String getdistrictnamefromresid(String resid) {
		String district = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT district_name FROM district d inner join restaurant r on (d.district_id = r.district_id) where restaurant_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, resid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				district = rs.getString("district_name");
			}
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return district;
	}
	public String getcategorynamefromresid(String resid) {
		String category = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT category_name FROM category c inner join restaurant r on (c.category_id = r.category_id) where restaurant_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, resid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				category = rs.getString("category_name");
			}
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
	public String getcountrynamefromresid(String resid) {
		String country = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT country_name FROM country c inner join restaurant r on (c.country_id = r.country_id) where restaurant_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, resid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				country = rs.getString("country_name");
			}
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return country;
	}

}
