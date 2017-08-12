package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Promotion;

public class PromotionDaoAndroid {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone";
	AdvertisementDaoAndroid DaoAndroid = new AdvertisementDaoAndroid();

	public ArrayList<Promotion> PrintList() {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT promotion_id,promotion_image,promotion_name, restaurant_name FROM promotion  inner join restaurant  on (promotion.restaurant_id = restaurant.restaurant_id) order by promotion_id desc;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, resname));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPro;
	}
	public String getresidfromproid(String proid) {
		String id = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_id FROM promotion where promotion_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, proid);
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
	
	public ArrayList<Promotion> PrintProClicked(String proid) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		String resid = getresidfromproid(proid);
		String city = DaoAndroid.getcitynamefromresid(resid);
		String district = DaoAndroid.getdistrictnamefromresid(resid);
		String category = DaoAndroid.getcategorynamefromresid(resid);
		String country = DaoAndroid.getcountrynamefromresid(resid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT promotion_image,promotion_name,promotion_start,promotion_end,restaurant_name,restaurant_address,restaurant_phone,restaurant_image FROM promotion inner join restaurant on (promotion.restaurant_id=restaurant.restaurant_id) where promotion_id=?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, proid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				String resname = rs.getString("restaurant_name");
				String resadd = rs.getString("restaurant_address");
				String resphone = rs.getString("restaurant_phone");
				String resimage = rs.getString("restaurant_image");
				listPro.add(new Promotion(image, name,start,end, resname, resadd, resphone, resimage, city, district, category, country,resid));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPro;
	}
}
