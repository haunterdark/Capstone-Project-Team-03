package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAPI {
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

	public boolean loadcustomer(String username, String pass) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_username, customer_password from customer where customer_username =? and customer_password =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				exists = true;
			} else {
				exists = false;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	public boolean checkstatus(String username) {
		boolean exists = false;
		int status =0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_status from customer where customer_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while(rs.next()){
				status = rs.getInt("customer_status");
			}
			if(status ==1){
				exists = true;
			}else{
				exists = false;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

}
