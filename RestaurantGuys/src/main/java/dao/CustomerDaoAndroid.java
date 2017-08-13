package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoAndroid {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone";

	// Get status value
	public boolean checkcustomerid(String customer_id) {
		boolean found= false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_id from customer where customer_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
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
	public void addcustomeraccount(String customer_id, String customer_phone) {
		String image_url = "upload/customer_avatar/people-512.png";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO customer(customer_id,customer_image,customer_phone,customer_status) values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
			statement.setString(2, image_url);
			statement.setString(3, customer_phone);
			statement.setInt(4,1);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}