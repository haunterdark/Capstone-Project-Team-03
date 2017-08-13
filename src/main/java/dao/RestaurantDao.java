package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Restaurant;
import utils.Checkrole;
import utils.Util;

public class RestaurantDao {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=yes&characterEncoding=UTF-8";
	Util util = new Util();
	Checkrole check = new Checkrole();
	RestaurantDao_Register register = new RestaurantDao_Register();
	private int noOfRecords = 6;
	
	// Print all restaurant
	public ArrayList<Restaurant> Print(int offset, int noOfRecords) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			// String sql = "SELECT *,city_name,district_name,country_name,
			// category_name FROM restaurant r, city c, district
			// d,category c1 , country c2 where
			// r.city_id = c.city_id and r.district_id =
			// d.district_id and r.category_id = c1.category_id and
			// r.country_id = c2.country_id ORDER BY restaurant_id
			// DESC";
			String sql = "SELECT SQL_CALC_FOUND_ROWS *,city_name,district_name,country_name, category_name FROM restaurant r INNER JOIN city c ON (r.city_id = c.city_id) INNER JOIN district d ON (r.district_id = d.district_id) INNER JOIN category c1 ON (r.category_id = c1.category_id) INNER JOIN country c2 ON (r.country_id = c2.country_id) ORDER BY restaurant_id DESC limit "
					+ offset + ", " + noOfRecords;
			String sql1 = "Select SQL_CALC_FOUND_ROWS restaurant_id, restaurant_name, restaurant_email, restaurant_phone, restaurant_status, restaurant_image from restaurant where city_id is null and district_id is null and category_id is null and country_id is null ORDER BY restaurant_id DESC limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			ResultSet rs1 = statement1.executeQuery();
			while (rs1.next()) {
				String id = rs1.getString("restaurant_id");
				String name = rs1.getString("restaurant_name");
				String email = rs1.getString("restaurant_email");
				String phone = rs1.getString("restaurant_phone");
				int status = rs1.getInt("restaurant_status");
				String image = rs1.getString("restaurant_image");
				listRes.add(new Restaurant(id, name, email, phone, status, image));
			}
			rs1.close();
			rs1 = statement.executeQuery("SELECT FOUND_ROWS()");
			if (rs1.next())
				this.noOfRecords = rs1.getInt(1);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String image = rs.getString("restaurant_image");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country, image));
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
		return listRes;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	// Get status value
	public int getstatusrestaurant(String restaurant_id) {
		int status = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_status from restaurant where restaurant_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, restaurant_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				status = rs.getInt("restaurant_status");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	// Set status value
	public void Updatestatus(String restaurant_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE restaurant set restaurant_status = ? where restaurant_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			if (getstatusrestaurant(restaurant_id) == 1) {
				statement.setInt(1, 0);
				statement.setString(2, restaurant_id);
				statement.executeUpdate();
			} else if (getstatusrestaurant(restaurant_id) == 0) {
				statement.setInt(1, 1);
				statement.setString(2, restaurant_id);
				statement.executeUpdate();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get value register
	public Restaurant Printres(String resid) {
		Restaurant Res = new Restaurant();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id,restaurant_name,restaurant_email,restaurant_phone from restaurant where restaurant_id = ?  ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, resid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				Res = new Restaurant(id, name, email, phone);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Res;
	}

	// insert database
	public void insertdata(Restaurant res) {
		try {
			String lastid = register.GetLastID("restaurant", "restaurant_id");
			String nextid = register.NextID(lastid, "NH_");
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO restaurant(restaurant_id,restaurant_username,restaurant_address,restaurant_name,restaurant_phone,restaurant_email,restaurant_status,city_id,district_id,category_id,country_id,restaurant_image) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nextid);
			statement.setString(2, util.decode(res.getRestaurant_username().trim()));
			statement.setString(3, util.decode(res.getRestaurant_address().trim()));
			statement.setString(4, util.decode(res.getRestaurant_name().trim()));
			statement.setString(5, res.getRestaurant_phone().trim());
			statement.setString(6, res.getRestaurant_email().trim());
			statement.setInt(7, 1);
			statement.setInt(8, res.getCity_id());
			statement.setInt(9, res.getDistrict_id());
			statement.setInt(10, res.getCategory_id());
			statement.setInt(11, res.getCountry_id());
			statement.setString(12, "upload/restaurant_avatar/hat-Chef-512.png");
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// update database
	public int updatedata(String id, String username, String address, int city, int district, int category,
			int country) {
		int row = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE restaurant SET restaurant_username=? , restaurant_status=? , restaurant_address=?, city_id =? , district_id =? , category_id = ?, country_id =? WHERE restaurant_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username.trim());
			statement.setInt(2, 1);
			statement.setString(3, address.trim());
			statement.setInt(4, city);
			statement.setInt(5, district);
			statement.setInt(6, category);
			statement.setInt(7, country);
			statement.setString(8, id);
			row = statement.executeUpdate();
			if (row > 0) {
				System.out.print("Sucessed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	// search city
	public ArrayList<Restaurant> Searchfromcity(int city_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name "
					+ "from restaurant r , city c, district  d, category c1 , country c2 "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id and city_id = ? "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// search category
	public ArrayList<Restaurant> Searchfromcategory(int category_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name "
					+ "from restaurant r , city c, district  d, category c1 , country c2 "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id and category_id = ? "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, category_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// search country
	public ArrayList<Restaurant> Searchfromcountry(int country_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name "
					+ "from restaurant r , city c, district  d, category c1 , country c2 "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id and country_id = ? "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, country_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// search city and district
	public ArrayList<Restaurant> Searchfromcitydistrict(int city_id, int district_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name  "
					+ "from restaurant r , city c, district  d, category c1 , country c2  "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and( r.city_id = ? and r.district_id = ?) "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			statement.setInt(2, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// search category and country
	public ArrayList<Restaurant> Searchfromcategorycountry(int Restaurant_catrgory_id, int country_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name  "
					+ "from restaurant r , city c, district  d, category c1 , country c2  "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and( r.category_id = ? and r.country_id = ?) "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, Restaurant_catrgory_id);
			statement.setInt(2, country_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// Search restaurant from status
	public ArrayList<Restaurant> SearchfromstatusRe(int enable) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name , category_name,country_name "
					+ "from restaurant r , city c, district  d, category c1 , country c2 "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id and restaurant_status = ? and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}

	// search city and district and status
	public ArrayList<Restaurant> Searchfromcitydistrictstatus(int city_id, int district_id, int enable) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name  "
					+ "from restaurant r , city c, district  d, category c1 , country c2 "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and( r.city_id = ? and r.district_id = ?)and restaurant_status = ? "
					+ " and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			statement.setInt(2, district_id);
			statement.setInt(3, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRes;
	}

	// search restaurant name
	public ArrayList<Restaurant> Searchfromname(String chuoi) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name , category_name,country_name   "
					+ "from restaurant r , city c, district  d, category c1 , country c2  "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and restaurant_name like ?  and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}

	// search restaurant name and status
	public ArrayList<Restaurant> Searchfromnamestatus(String chuoi, int enable) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name    "
					+ "from restaurant r , city c, district  d, category c1 , country c2   "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and restaurant_name like ? and restaurant_status = ? "
					+ "and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}

	// search restaurant name and city
	public ArrayList<Restaurant> Searchfromnamecity(String chuoi, int city_id, int district_id) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name, category_name,country_name   "
					+ "from restaurant r , city c, district  d, category c1 , country c2  "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id and r.category_id = c1.category_id and r.country_id = c2.country_id "
					+ "and restaurant_name like ? and city_id = ? and  district_id =? ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, city_id);
			statement.setInt(3, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}

	// search all
	public ArrayList<Restaurant> Searchall(String chuoi, int city_id, int district_id, int enable) {
		ArrayList<Restaurant> listRes = new ArrayList<Restaurant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, district_name, city_name , category_name,country_name "
					+ "from restaurant r , city c, district  d, category c1 , country c2  "
					+ "where r.city_id = c.city_id and r.district_id = d.district_id "
					+ "and restaurant_name like ? and city_id = ? and  district_id =? "
					+ "and restaurant_status = ? and r.category_id = c1.category_id and r.country_id = c2.country_id ORDER BY restaurant_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, city_id);
			statement.setInt(3, district_id);
			statement.setInt(4, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String username = rs.getString("restaurant_username");
				String address = rs.getString("restaurant_address");
				int status = rs.getInt("restaurant_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String category = rs.getString("category_name");
				String country = rs.getString("country_name");
				listRes.add(new Restaurant(id, name, email, phone, status, username, address, city, district, category,
						country));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}

	public String getidprofiles(String username) {

		String idlogin = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select restaurant_id from restaurant where restaurant_username=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idlogin = rs.getString("restaurant_id");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idlogin;
	}

	public Restaurant Printall(String username) {
		Restaurant Res = new Restaurant();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,country_name , category_name , city_name , district_name from restaurant r, city c, district d, country o, category a where restaurant_id = ? and r.city_id = c.city_id and r.district_id = d.district_id and r.country_id = o.country_id and r.category_id = a.category_id";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, getidprofiles(username));
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				String id = rs.getString("restaurant_id");
				String name = rs.getString("restaurant_name");
				String email = rs.getString("restaurant_email");
				String phone = rs.getString("restaurant_phone");
				String address = rs.getString("restaurant_address");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				String country = rs.getString("country_name");
				String category = rs.getString("category_name");
				int city_id = rs.getInt("city_id");
				int district_id = rs.getInt("district_id");
				int country_id = rs.getInt("country_id");
				int category_id = rs.getInt("category_id");
				String image = rs.getString("restaurant_image");
				Res = new Restaurant(id, username, name, email, phone, address, city, district, country, category,
						city_id, district_id, country_id, category_id, image);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Res;
	}

	public int updateprofiles(Restaurant res) {
		int row = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE restaurant SET restaurant_phone=?, restaurant_address=?, city_id=?, district_id=?, country_id=?, category_id=?, restaurant_name = ? WHERE restaurant_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			String diachi = util.decode(res.getRestaurant_address());
			String ten = util.decode(res.getRestaurant_name());
			statement.setString(1, res.getRestaurant_phone());
			statement.setString(2, diachi);
			statement.setInt(3, res.getCity_id());
			statement.setInt(4, res.getDistrict_id());
			statement.setInt(5, res.getCountry_id());
			statement.setInt(6, res.getCategory_id());
			statement.setString(7,ten);
			statement.setString(8, res.getRestaurant_id());
			row = statement.executeUpdate();
			if (row > 0) {
				System.out.print("Sucessed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public String getname(String role, String username) {
		String name = "";
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			name = "Admin";
		} else if (check.PrintRole().equals("ROLE_USER")) {
			try {
				conn = DriverManager.getConnection(url, userName, password);
				String sql = "select restaurant_name from restaurant where restaurant_username =? ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				ResultSet rs = statement.executeQuery();
				rs.beforeFirst();
				while (rs.next()) {
					name = rs.getString("restaurant_name");
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public boolean checkresname(String name) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_name FROM restaurant where restaurant_name = ?";
			PreparedStatement statement = conn.prepareStatement(sql);

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
		}
		return exists;
	}

	public boolean checkemail(String email) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_email FROM restaurant where restaurant_email = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
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

	public boolean checkphone(String phone) {
		boolean exists = false;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_phone FROM restaurant where restaurant_phone = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, phone);
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

	public String loadavatarres(String username) {
		String chuoi = "";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_image FROM restaurant where restaurant_username = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				chuoi = rs.getString("restaurant_image");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chuoi;
	}

	public String loadresname(String username) {
		String chuoi = "";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT restaurant_name FROM restaurant where restaurant_username = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				chuoi = rs.getString("restaurant_name");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chuoi;
	}

	public void updateimage(String filename, String username) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE restaurant SET restaurant_image=? WHERE restaurant_username=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, filename);
			statement.setString(2, username);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updatepass(String username, String pass) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE user SET password=? WHERE username=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, pass);
			statement.setString(2, username);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
