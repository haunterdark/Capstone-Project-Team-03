package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Restaurant;
import utils.Util;

public class RestaurantDao_Register {
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";
	Util util = new Util();

	public void insertdata(Restaurant restaurant) {
		try {
			String lastid = GetLastID("restaurant", "restaurant_id");
			String nextid = NextID(lastid, "NH_");
			String ten = util.decode(restaurant.getRestaurant_name()).trim();
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO restaurant(restaurant_id, restaurant_name,restaurant_email,restaurant_phone,restaurant_status,restaurant_image) values(?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nextid);
			statement.setString(2, ten);
			statement.setString(3, restaurant.getRestaurant_email());
			statement.setString(4, restaurant.getRestaurant_phone());
			statement.setString(5, "2");
			statement.setString(6, "upload/restaurant_avatar/hat-Chef-512.png");
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String GetLastID(String nameTable, String nameSelectColumn) {
		String id = "";
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, userName, password);

			String sql = "SELECT " + nameSelectColumn + " FROM " + nameTable + " ORDER BY " + nameSelectColumn
					+ " DESC LIMIT 1";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				id = rs.getString(nameSelectColumn);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public String NextID(String lastID, String prefixID) {
		int nextID = 0;
		try {
			if (lastID == null) {
				return prefixID + "0001"; // fixwidth default
			}
			StringBuilder bulid = new StringBuilder(lastID);
			bulid.deleteCharAt(2 - 0);
			bulid.deleteCharAt(1 - 0);
			bulid.deleteCharAt(0 - 0);
			String string = bulid.toString();

			nextID = Integer.parseInt(string) + 1;
			int lengthNumerID = lastID.length() - prefixID.length();
			String zeroNumber = "";
			for (int i = 1; i <= lengthNumerID; i++) {
				if (nextID < Math.pow(10, i)) {
					for (int j = 1; j <= lengthNumerID - i; i++) {
						zeroNumber += "0";
					}
					return prefixID + zeroNumber + Integer.toString(nextID);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return prefixID + nextID;
	}
}