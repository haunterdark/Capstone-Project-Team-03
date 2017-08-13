package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Promotion;
import utils.Util;

public class PromotionDao {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";
	Util util = new Util();
	RestaurantDao_Register register = new RestaurantDao_Register();
	private int noOfRecords = 6;

	// Print all promotion for admin
	public ArrayList<Promotion> Print(int offset, int noOfRecords) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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
		return listPro;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	// Print all promotion from restaurant
	public ArrayList<Promotion> PrintPro(int offset, int noOfRecords, String username) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS * from promotion, user, restaurant where restaurant_username =? and restaurant_username = username and promotion.restaurant_id =restaurant.restaurant_id limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				listPro.add(new Promotion(id, image, name, start, end, status));
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
		return listPro;
	}

	// Get value promotion
	public Promotion Printproedit(String proid) {
		Promotion Pro = new Promotion();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select promotion_id, promotion_image, promotion_name, promotion_start, promotion_end from promotion where promotion_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, proid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				Pro = new Promotion(id, image, name, start, end);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Pro;
	}

	public int getstatuspromotion(String promotion_id) {
		int status = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select promotion_status from promotion where promotion_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promotion_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				status = rs.getInt("promotion_status");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public String getRes_id(String username) {
		String status = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id from restaurant where restaurant_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				status = rs.getString("restaurant_id");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public void Updatestatus(String promotion_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE promotion set promotion_status = ? where promotion_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			if (getstatuspromotion(promotion_id) == 1) {
				statement.setInt(1, 0);
				statement.setString(2, promotion_id);
				statement.executeUpdate();
			} else if (getstatuspromotion(promotion_id) == 0) {
				statement.setInt(1, 1);
				statement.setString(2, promotion_id);
				statement.executeUpdate();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// insert a new promotion
	public void insertPro(Promotion pro, String username, String pathimage) {
		try {
			String lastid = register.GetLastID("promotion", "promotion_id");
			String nextid = register.NextID(lastid, "KM_");
			String name = util.decode(pro.getPromotion_name());
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO promotion(promotion_image, promotion_name, promotion_start, promotion_end, restaurant_id, promotion_id,promotion_status) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			// String path= System.getProperty("user.dir") +
			// "/webapps/restaurant-guys/";
			statement.setString(1, pathimage);
			statement.setString(2, name);
			statement.setString(3, pro.getPromotion_start());
			statement.setString(4, pro.getPromotion_end());
			statement.setString(5, getRes_id(username));
			statement.setString(6, nextid);
			statement.setInt(7, 1);

			statement.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// update a promotion
	public void updatepro(Promotion pro) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE promotion SET promotion_image=?, promotion_name=?, promotion_start=?, promotion_end=? where promotion_id=?";

			PreparedStatement statement = conn.prepareStatement(sql);
			String name = util.decode(pro.getPromotion_name());
			statement.setString(1, pro.getPromotion_image());
			statement.setString(2, name);
			statement.setString(3, pro.getPromotion_start());
			statement.setString(4, pro.getPromotion_end());
			statement.setString(5, pro.getPromotion_id());
			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.print("Sucessed!");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Search promotion from status for admin
	public ArrayList<Promotion> SearchProfromstatusAD(int enable) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id and promotion_status =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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

	// Search promotion from status for restaurant
	public ArrayList<Promotion> SearchProfromstatusRe(int enable, String username) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from promotion, user, restaurant where promotion.restaurant_id =restaurant.restaurant_id and promotion_status =? and restaurant.restaurant_username = user.username and restaurant_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				listPro.add(new Promotion(id, image, name, start, end, status));
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

	// Search promotion from promotion_name and restaurant_name for admin
	public ArrayList<Promotion> SearchfromproAD(String chuoi) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id and (promotion_name like ? or restaurant_name like ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setString(2, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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

	// Search promotion from promotion_name for restaurant
	public ArrayList<Promotion> SearchfromproRe(String chuoi, String username) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from promotion, restaurant where promotion.restaurant_id =restaurant.restaurant_id and promotion_name like ?  and restaurant_username =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				listPro.add(new Promotion(id, image, name, start, end, status));
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

	// Search promotion from status and promotion_name for admin
	public ArrayList<Promotion> SearchAllProAD(String chuoi, int enable) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id and (promotion_name like ? and promotion_status like ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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

	// Search promotion from status and restaurant_name for admin
	public ArrayList<Promotion> SearchAllProAD1(String chuoi, int enable) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id and (restaurant_name like ? and promotion_status like ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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

	// Search promotion from status and promotion_name for restaurant
	public ArrayList<Promotion> SearchAllProRe(String chuoi, int enable, String username) {
		ArrayList<Promotion> listPro = new ArrayList<Promotion>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name  from promotion, restaurant where promotion.restaurant_id = restaurant.restaurant_id and (promotion_name like ? and promotion_status like ?) and restaurant_username =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			statement.setString(3, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("promotion_id");
				String image = rs.getString("promotion_image");
				String name = rs.getString("promotion_name");
				String start = rs.getString("promotion_start");
				String end = rs.getString("promotion_end");
				int status = rs.getInt("promotion_status");
				String resname = rs.getString("restaurant_name");
				listPro.add(new Promotion(id, image, name, start, end, status, resname));
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
