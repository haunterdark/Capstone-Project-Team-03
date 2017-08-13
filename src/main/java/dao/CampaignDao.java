package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import entities.Campaign;
import utils.Util;

public class CampaignDao {
	// Create database connection
	Util util = new Util();
	AdvertisementDao advertisementDao = new AdvertisementDao();
	private int noOfRecords = 6;
	RestaurantDao_Register register = new RestaurantDao_Register();

	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=yes&characterEncoding=UTF-8";

	// Print all customer for admin
	public ArrayList<Campaign> Print(int offset, int noOfRecords) {
		ArrayList<Campaign> listcam = new ArrayList<Campaign>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT SQL_CALC_FOUND_ROWS *,restaurant_name FROM campaign c INNER JOIN restaurant r ON (c.restaurant_id = r.restaurant_id) ORDER BY campaign_id DESC limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				updatestatusfromdate(id);
				String description = rs.getString("campaign_name");
				int value = rs.getInt("campaign_value");
				String start = rs.getString("campaign_start");
				String end = rs.getString("campaign_end");
				int status = rs.getInt("campaign_status");
				String restaurant = rs.getString("restaurant_name");
				listcam.add(new Campaign(id, description, value, start, end, status, restaurant));
			}
			rs.close();

			rs = statement.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next())
				this.noOfRecords = rs.getInt(1);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcam;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	// Print all customer from restaurant
	public ArrayList<Campaign> PrintCouponRes(int offset, int noOfRecords, String username) {
		ArrayList<Campaign> listcam = new ArrayList<Campaign>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS * from campaign c INNER JOIN restaurant r ON (c.restaurant_id = r.restaurant_id) where r.restaurant_username= ? ORDER BY campaign_id DESC limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				updatestatusfromdate(id);
				String description = rs.getString("campaign_name");
				int value = rs.getInt("campaign_value");
				String start = rs.getString("campaign_start");
				String end = rs.getString("campaign_end");
				int status = rs.getInt("campaign_status");
				String image = rs.getString("campaign_image");
				listcam.add(new Campaign(id, description, value, start, end, status,image));
			}
			rs.close();

			rs = statement.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next())
				this.noOfRecords = rs.getInt(1);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcam;
	}

	// Search campaign from status for admin
	public ArrayList<Campaign> SearchfromstatusAD(int enable) {
		ArrayList<Campaign> listcam = new ArrayList<Campaign>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT *,restaurant_name FROM campaign c INNER JOIN restaurant r ON (c.restaurant_id = r.restaurant_id) where campaign_status=? ORDER BY campaign_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				String description = rs.getString("campaign_name");
				int value = rs.getInt("campaign_value");
				String start = rs.getString("campaign_start");
				String end = rs.getString("campaign_end");
				int status = rs.getInt("campaign_status");
				String restaurant = rs.getString("restaurant_name");
				listcam.add(new Campaign(id, description, value, start, end, status, restaurant));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcam;
	}

	// Search campaign from status for restaurant
	public ArrayList<Campaign> SearchfromstatusRe(int enable, String username) {
		ArrayList<Campaign> listcam = new ArrayList<Campaign>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from campaign c INNER JOIN restaurant r ON (c.restaurant_id = r.restaurant_id) where r.restaurant_username= ? and campaign_status=? ORDER BY campaign_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(2, enable);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				String description = rs.getString("campaign_name");
				int value = rs.getInt("campaign_value");
				String start = rs.getString("campaign_start");
				String end = rs.getString("campaign_end");
				int status = rs.getInt("campaign_status");
				String image = rs.getString("campaign_image");
				listcam.add(new Campaign(id, description, value, start, end, status,image));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcam;
	}

	public boolean checkstatuscampaign(String id) {
		boolean exists = false;
		int statuscampaign = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_status from campaign c  where campaign_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				statuscampaign = rs.getInt("campaign_status");
			}
			if (statuscampaign != 1) {
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

	// Set status value
	public Campaign getcampaign(String campagin_id) {
		Campaign campaign = new Campaign();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "Select * from campaign where campaign_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, campagin_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				String description = rs.getString("campaign_name");
				int value = rs.getInt("campaign_value");
				String start = rs.getString("campaign_start");
				String end = rs.getString("campaign_end");
				String image = rs.getString("campaign_image");
				campaign = new Campaign(id, description, value, start, end,image);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return campaign;
	}

	public void DeleteCampaign(String campaign_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "DELETE FROM campaign WHERE campaign_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, campaign_id);
			statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void UpdateCampaign(Campaign campaign) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "Update Campaign set campaign_name =?, campaign_value = ?, campaign_start =?,campaign_end = ?,campaign_image = ? where campaign_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			String mota = util.decode(campaign.getCampaign_name());
			statement.setString(1, mota);
			statement.setInt(2, campaign.getCampaign_value());
			statement.setString(3, campaign.getCampaign_start());
			statement.setString(4, campaign.getCampaign_end());
			statement.setString(5, campaign.getCampaign_id());
			statement.setString(6, campaign.getCampaign_image());
			if (getstatuscampaign(campaign.getCampaign_id()) == 0) {
				updatestatuscampaignupdate(campaign.getCampaign_id());
				statement.executeUpdate();
			} else {
				statement.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int getstatuscampaign(String id) {
		int statuscampaign = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_status from campaign c  where campaign_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				statuscampaign = rs.getInt("campaign_status");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statuscampaign;
	}

	public void updatestatuscampaignupdate(String id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "update campaign set campaign_status=? where campaign_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 2);
			statement.setString(2, id);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCampaign(Campaign campaign, String username) {
		try {
			String lastid = register.GetLastID("campaign", "campaign_id");
			String nextid = register.NextID(lastid, "CA_");
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO campaign(campaign_name, campaign_value, campaign_start, campaign_end,campaign_status,restaurant_id,campaign_image,campaign_id) values(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			String mota = util.decode(campaign.getCampaign_name());
			statement.setString(1, mota);
			statement.setInt(2, campaign.getCampaign_value());
			statement.setString(3, campaign.getCampaign_start());
			statement.setString(4, campaign.getCampaign_end());
			statement.setInt(5, 2);
			statement.setString(6, advertisementDao.getUserid(username));
			statement.setString(7, campaign.getCampaign_image());
			statement.setString(8, nextid);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatestatusfromdate(String id) {
		Date date = new Date();
		String start = "", end = "";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select campaign_start,campaign_end from campaign c  where campaign_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				start = rs.getString("campaign_start");
				end = rs.getString("campaign_end");
			}
			String sql1 = "update campaign set campaign_status=? where campaign_id = ?";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			java.sql.Date startdate = util.convertdate(start);
			java.sql.Date enddate = util.convertdate(end);
			if ((date.after(startdate) || date.equals(startdate)) && date.before(enddate)) {
				statement1.setInt(1, 1);
				statement1.setString(2, id);
				statement1.executeUpdate();
			} else if (date.after(startdate) && date.after(enddate)) {
				statement1.setInt(1, 0);
				statement1.setString(2, id);
				statement1.executeUpdate();
			} else if (date.before(startdate)) {
				statement1.setInt(1, 2);
				statement1.setString(2, id);
				statement1.executeUpdate();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updatestatuscampaignprint(String id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "update campaign set campaign_status=? where campaign_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 2);
			statement.setString(2, id);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
