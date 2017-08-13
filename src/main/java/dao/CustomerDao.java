package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Customer;

public class CustomerDao {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";
	private int noOfRecords = 6;

	// Print all customer for admin
	public ArrayList<Customer> Print(int offset, int noOfRecords) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS *,city_name, district_name  "
					+ "from customer, city , district "
					+ "where customer.city_id = city.city_id and customer.district_id= district.district_id ORDER BY customer_id DESC limit "
					+ offset + ", " + noOfRecords;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
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
		return listCus;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	// Print all customer from restaurant
	public ArrayList<Customer> PrintCustomer(int offset, int noOfRecords, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select SQL_CALC_FOUND_ROWS * ,city_name, district_name FROM customer inner join customer_restaurant_relationship on (customer.customer_id = customer_restaurant_relationship.customer_id) inner join restaurant on (customer_restaurant_relationship.restaurant_id = restaurant.restaurant_id) inner join city on (customer.city_id = city.city_id) inner join district on (customer.district_id = district.district_id) where restaurant.restaurant_username =? ORDER BY customer.customer_id DESC limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			rs.close();

			rs = statement.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next())
				this.noOfRecords = rs.getInt(1);
			String sql1 = "select SQL_CALC_FOUND_ROWS * FROM customer inner join customer_restaurant_relationship on (customer.customer_id = customer_restaurant_relationship.customer_id) inner join restaurant on (customer_restaurant_relationship.restaurant_id = restaurant.restaurant_id) where restaurant.restaurant_username =? and customer_address is null and customer.city_id is null and customer.district_id is null ORDER BY customer.customer_id DESC limit "
					+ offset + ", " + noOfRecords;
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setString(1, username);
			ResultSet rs1 = statement1.executeQuery();
			while (rs1.next()) {
				String id = rs1.getString("customer_id");
				String image = rs1.getString("customer_image");
				String name = rs1.getString("customer_name");
				String email = rs1.getString("customer_email");
				String phone = rs1.getString("customer_phone");
				int status = rs1.getInt("customer_status");
				listCus.add(new Customer(id, image, name, email, phone, status));
			}
			rs1.close();

			rs1 = statement1.executeQuery("SELECT FOUND_ROWS()");

			if (rs1.next())
				this.noOfRecords = rs1.getInt(1);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Update status for customer account
	public void Updatestatus(String customer_id) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE customer set customer_status = ? where customer_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			if (getstatuscustomer(customer_id) == 1) {
				statement.setInt(1, 0);
				statement.setString(2, customer_id);
				statement.executeUpdate();
			} else if (getstatuscustomer(customer_id) == 0) {
				statement.setInt(1, 1);
				statement.setString(2, customer_id);
				statement.executeUpdate();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get status value
	public int getstatuscustomer(String customer_id) {
		int status = 0;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select customer_status from customer where customer_id =? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				status = rs.getInt("customer_status");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	// Search customer from status for admin
	public ArrayList<Customer> SearchfromstatusAD(int enable) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer_status = ? and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from status for Restaurant
	public ArrayList<Customer> SearchfromstatusRe(int enable, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id "
					+ "and ca.customer_status =? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city for admin
	public ArrayList<Customer> SearchfromcityAD(int city_id) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and city_id = ? and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city for Restaurant
	public ArrayList<Customer> SearchfromcityRe(int city_id, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id "
					+ "and ca.city_id =? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setInt(2, city_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city - district for admin
	public ArrayList<Customer> SearchfromcitydistrictAD(int city_id, int district_id) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and city_id = ? and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  and district_id =? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			statement.setInt(2, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city -district for Restaurant
	public ArrayList<Customer> SearchfromcitydistrictRe(int city_id, int district_id, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id "
					+ "and ca.city_id =? and district_id  =? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setInt(2, city_id);
			statement.setInt(3, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city - district - status for admin
	public ArrayList<Customer> SearchfromcitydistrictstatusAD(int city_id, int district_id, int enable) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and city_id = ? and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  "
					+ "and district_id =?  and customer_status = ? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			statement.setInt(2, district_id);
			statement.setInt(3, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city -district - status for Restaurant
	public ArrayList<Customer> SearchfromcitydistrictstatusRe(int city_id, int district_id, String username,
			int enable) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id "
					+ "and ca.city_id =? and district_id  =? and customer_status = ? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setInt(2, city_id);
			statement.setInt(3, district_id);
			statement.setInt(4, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city - district - name for admin
	public ArrayList<Customer> SearchfromcitydistrictnameAD(int city_id, int district_id, String chuoi) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and city_id = ? and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  "
					+ "and district_id =?  and customer_name like ? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, city_id);
			statement.setInt(2, district_id);
			statement.setString(3, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from city -district - name for Restaurant
	public ArrayList<Customer> SearchfromcitydistrictnameRe(int city_id, int district_id, String username,
			String chuoi) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id "
					+ "and ca.city_id =? and district_id  =? and customer_name like ? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setInt(2, city_id);
			statement.setInt(3, district_id);
			statement.setString(4, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from customer_name or customer_phone for admin
	public ArrayList<Customer> SearchfromcusAD(String chuoi) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  and customer_name like ? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from customer_name or customer_phone for customer
	public ArrayList<Customer> SearchfromcusRe(String chuoi, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * ,city_name, district_name "
					+ "from customer ca ,restaurant r , city c , district d "
					+ "where r.restaurant_username =? and ca.restaurant_id =r.restaurant_id "
					+ "and ca.city_id = c.city_id and ca.district_id= d.district_id  "
					+ " and customer_name like ? ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, "%" + chuoi + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from status and customer_name or phone for admin
	public ArrayList<Customer> SearchfromnamestatusAD(String chuoi, int enable) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  and customer_name like ?  and customer_status = ? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from status and customer_name or phone for res
	public ArrayList<Customer> SearchfromnamestatusRE(String chuoi, int enable, String username) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  "
					+ "and customer_name like ?  and customer_status = ? and restaurant_username = ?  ORDER BY customer_id DESC";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			statement.setString(3, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from status and customer_name for admin
	public ArrayList<Customer> SearchfromallAD(String chuoi, int enable, int city_id, int district_id) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *, restaurant_name,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  "
					+ "and customer_name like ?  and customer_status = ? and city_id =? and "
					+ "district_id = ? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			statement.setInt(3, city_id);
			statement.setInt(4, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String resname = rs.getString("restaurant_name");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, resname, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}

	// Search customer from status and customer_name for res
	public ArrayList<Customer> SearchfromallRE(String chuoi, int enable, String username, int city_id,
			int district_id) {
		ArrayList<Customer> listCus = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select *,city_name, district_name  "
					+ " from customer, restaurant, city , district "
					+ "where customer.restaurant_id =restaurant.restaurant_id "
					+ "and customer.city_id = city.city_id "
					+ "and customer.district_id= district.district_id  "
					+ "and customer_name like ?  and customer_status = ? and restaurant_username = ? and city_id = ? and district_id = ? ORDER BY customer_id DESC ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + chuoi + "%");
			statement.setInt(2, enable);
			statement.setString(3, username);
			statement.setInt(4, city_id);
			statement.setInt(5, district_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("customer_id");
				String image = rs.getString("customer_image");
				String name = rs.getString("customer_name");
				String email = rs.getString("customer_email");
				String phone = rs.getString("customer_phone");
				String address = rs.getString("customer_address");
				int status = rs.getInt("customer_status");
				String city = rs.getString("city_name");
				String district = rs.getString("district_name");
				listCus.add(new Customer(id, image, name, email, phone, address, status, city, district));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCus;
	}
}
