package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Advertisement;
import utils.Util;

public class AdvertisementDao {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";
	Util util = new Util();
	RestaurantDao_Register register = new RestaurantDao_Register();
	private int noOfRecords = 6;

	public ArrayList<Advertisement> Print(int offset, int noOfRecords) {
		ArrayList<Advertisement> listAd = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS *,restaurant_name from advertisement, restaurant where advertisement.restaurant_id =restaurant.restaurant_id limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				String resname = rs.getString("restaurant_name");
				listAd.add(new Advertisement(id, image, name, start, end, status, resname));
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
		return listAd;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public ArrayList<Advertisement> PrintAdvertisement(int offset, int noOfRecords, String username) {
		ArrayList<Advertisement> listAd = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM advertisement a, restaurant r  where restaurant_username = ? and a.restaurant_id= r.restaurant_id limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				listAd.add(new Advertisement(id, image, name, start, end, status));
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
		return listAd;
	}

	// get advertisementid
	public Advertisement Printrads(String adsid) {
		Advertisement Ads = new Advertisement();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from advertisement where advertisement_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, adsid);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				Ads = new Advertisement(id, image, name, start, end);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ads;
	}

	public int getstatusadvertisement(String advertisement_id) {
		int status = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select advertisement_status from advertisement where advertisement_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, advertisement_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				status = rs.getInt("advertisement_status");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public void Updatestatus(String advertisement_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE advertisement set advertisement_status = ? where advertisement_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			if (getstatusadvertisement(advertisement_id) == 1) {
				statement.setInt(1, 0);
				statement.setString(2, advertisement_id);
				statement.executeUpdate();
			} else if (getstatusadvertisement(advertisement_id) == 0) {
				statement.setInt(1, 1);
				statement.setString(2, advertisement_id);
				statement.executeUpdate();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertAds(Advertisement Ads, String username) {
		try {
			String lastid = register.GetLastID("advertisement", "advertisement_id");
			String nextid = register.NextID(lastid, "QC_");
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO advertisement(advertisement_image, advertisement_name, advertisement_start, advertisement_end,restaurant_id,advertisement_id,advertisement_status) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			String name = util.decode(Ads.getAdvertisement_name());
			statement.setString(1, Ads.getAdvertisement_image());
			statement.setString(2, name);
			statement.setString(3, Ads.getAdvertisement_start());
			statement.setString(4, Ads.getAdvertisement_end());
			statement.setString(5, getUserid(username));
			statement.setString(6, nextid);
			statement.setInt(7, 1);
			int row = statement.executeUpdate();
			if (row > 0) {
				// System.out.println("A restaurant was inserted");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUserid(String username) {
		String id = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id from restaurant where restaurant_username = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				id = rs.getString("restaurant_id");

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void updateAds(Advertisement Ad) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE advertisement set advertisement_image=?, advertisement_name=?, advertisement_start=?, advertisement_end=? where advertisement_id=? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			String name = util.decode(Ad.getAdvertisement_name());
			statement.setString(1, Ad.getAdvertisement_image());
			statement.setString(2, name);
			statement.setString(3, Ad.getAdvertisement_start());
			statement.setString(4, Ad.getAdvertisement_end());
			statement.setString(5, Ad.getAdvertisement_id());
			int row = statement.executeUpdate();
			if (row > 0) {
				// System.out.println("A restaurant was inserted");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Search promotion from status for admin
	public ArrayList<Advertisement> SearchAdsfromstatusAD(int enable) {
		ArrayList<Advertisement> listAds = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from advertisement, restaurant where advertisement.restaurant_id = restaurant.restaurant_id and advertisement_status =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				String resname = rs.getString("restaurant_name");
				listAds.add(new Advertisement(id, image, name, start, end, status, resname));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAds;
	}

	// Search promotion from status for restaurant
	public ArrayList<Advertisement> SearchAdsfromstatusRe(int enable, String username) {
		ArrayList<Advertisement> listAds = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from advertisement, restaurant where advertisement.restaurant_id =restaurant.restaurant_id and advertisement_status =? and restaurant_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				listAds.add(new Advertisement(id, image, name, start, end, status));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAds;
	}

	// Search promtion from promotion_name for admin
	public ArrayList<Advertisement> SearchfromadsAD(String chuoi) {
		ArrayList<Advertisement> listAds = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from advertisement, restaurant where advertisement.restaurant_id = restaurant.restaurant_id and advertisement_name like ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				String resname = rs.getString("restaurant_name");
				listAds.add(new Advertisement(id, image, name, start, end, status, resname));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAds;
	}

	// Search promotion from promotion_name for restaurant
	public ArrayList<Advertisement> SearchfromadsRe(String chuoi, String username) {
		ArrayList<Advertisement> listAds = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from advertisement, restaurant where advertisement.restaurant_id =restaurant.restaurant_id and advertisement_name like ?  and restaurant_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				listAds.add(new Advertisement(id, image, name, start, end, status));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAds;
	}

	// Search promtion from status and promotion_name for admin
	public ArrayList<Advertisement> SearchAllAdsAD(String chuoi, int enable) {
		ArrayList<Advertisement> listPro = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from advertisement, restaurant where advertisement.restaurant_id = restaurant.restaurant_id and (advertisement_name like ? and advertisement_status like ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Advertisement(id, image, name, start, end, status, resname));
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

	// Search promtion from status and promotion_name for restaurant
	public ArrayList<Advertisement> SearchAllAdsRe(String chuoi, int enable, String username) {
		ArrayList<Advertisement> listAds = new ArrayList<Advertisement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from advertisement, restaurant where advertisement.restaurant_id = restaurant.restaurant_id and (advertisement_name like ? and advertisement_status like ?) and restaurant_username =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			statement.setString(3, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("advertisement_id");
				String image = rs.getString("advertisement_image");
				String name = rs.getString("advertisement_name");
				String start = rs.getString("advertisement_start");
				String end = rs.getString("advertisement_end");
				int status = rs.getInt("advertisement_status");
				String resname = rs.getString("restaurant_name");
				listAds.add(new Advertisement(id, image, name, start, end, status, resname));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAds;
	}

	public boolean checkvalidateadsname(String advertisement_name) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select distinct advertisement_name from advertisement where advertisement_name = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			String name = util.decode(advertisement_name);
			statement.setString(1, name);
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
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	
}
