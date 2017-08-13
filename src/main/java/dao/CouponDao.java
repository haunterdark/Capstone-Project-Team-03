package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import utils.Util;

public class CouponDao {
	// Create database connection
	Util util = new Util();
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=yes&characterEncoding=UTF-8";

	public boolean checkcodecoupon(String coupon_id, String username) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select coupon_id from coupon c INNER JOIN campaign ca ON (c.campaign_id = ca.campaign_id) where coupon_id = ? and ca.campaign_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, coupon_id);
			statement.setInt(2, getidcampaign(username));
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
	public int getidcampaign(String username) {
		int id = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_id from campaign c INNER JOIN restaurant r ON (c.restaurant_id = r.restaurant_id) where restaurant_username = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			if(rs.next()){
				id = rs.getInt("campaign_id");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public int getcouponvalue(String coupon_id) {
		int coupon_value = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_value from campaign c INNER JOIN coupon co ON (c.campaign_id = co.campaign_id) where coupon_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, coupon_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			if(rs.next()){
				coupon_value = rs.getInt("campaign_value");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coupon_value;
	}
	public boolean checkdate(String coupon_id,Date currentdate) {
		boolean exists = false;
		String start = "", end = "";
		java.util.Date util_CurrentDate = currentdate;
		java.sql.Date sql_CurrentDate = new java.sql.Date( util_CurrentDate.getTime() );
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_start, campaign_end from campaign c INNER JOIN coupon co ON (c.campaign_id = co.campaign_id) where coupon_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, coupon_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				start = rs.getString("campaign_start");
				end = rs.getString("campaign_end");
			}
			java.sql.Date startdate = util.convertdate(start);
			java.sql.Date enddate = util.convertdate(end);
			if((sql_CurrentDate.after(startdate)|| sql_CurrentDate.equals(startdate))&& sql_CurrentDate.before(enddate)){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	public boolean checkcustomer(String coupon_id, String customer_id) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_id from coupon c  where coupon_id = ? and customer_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, coupon_id);
			statement.setString(2, customer_id);
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
	public boolean checkstatuscoupon(String coupon_id) {
		boolean exists = false;
		String statuscoupon ="";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select coupon_status from coupon c  where coupon_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, coupon_id);
			ResultSet rs = statement.executeQuery();
			String sql1 = "UPDATE coupon set coupon_status = ? where coupon_id =? ";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setString(1, "Đã dùng");
			statement1.setString(2, coupon_id);
			rs.beforeFirst();
			while (rs.next()) {
				statuscoupon = rs.getString("coupon_status");
			}
			if(statuscoupon.equals("Chưa Dùng")){
				exists= true;
				statement1.executeUpdate();
			}else{
				exists= false;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
}
