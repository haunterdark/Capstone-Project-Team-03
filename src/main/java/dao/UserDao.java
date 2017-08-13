package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
	// Create database connection
		Connection conn = null;
		String userName = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8";

		//Get username value
		public String getuser(String restaurant_id) {
			String status = "";
			try {
				conn = DriverManager.getConnection(url, userName, password);
				String sql = "select restaurant_username from restaurant where restaurant_id =? ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, restaurant_id);
				ResultSet rs = statement.executeQuery();
				rs.beforeFirst();
				while (rs.next()) {
					status = rs.getString("restaurant_username");
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}
		//Set status value
		public void Updatestatus(String restaurant_id) {
			try {
				conn = DriverManager.getConnection(url, userName, password);
				String sql = "UPDATE  user u INNER JOIN restaurant r ON u.username = r.restaurant_username SET u.status = r.restaurant_status where r.restaurant_username =? ";
				PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, getuser(restaurant_id));
					statement.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void insertuser(String username) {
			String sql = "INSERT INTO user (username,password,status) values (?,md5(?),?)";
			try {
				conn = DriverManager.getConnection(url, userName, password);
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, "123456");
				statement.setInt(3, 1);
				statement.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void insertuserrole(String username) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO user_roles(username,role) values(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, "ROLE_USER");
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		public boolean checkusername(String username){
			boolean exists = false;
			try {
				conn = DriverManager.getConnection(url, userName, password);
				String sql = "select username from user where username = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				ResultSet rs = statement.executeQuery();
				rs.beforeFirst();
				if(rs.next()){
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
		public String checkpassword(String username){
			String pass =null;
			try {
				conn = DriverManager.getConnection(url, userName, password);
				String sql = "select password from user where username = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					pass= rs.getString("password");
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return pass;
		}
}
