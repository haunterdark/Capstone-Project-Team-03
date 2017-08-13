
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.City;

public class CityDao {
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

	public List<City> loadlistCity() {
		List<City> ArrayCity = new ArrayList<City>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from city";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("city_id");
				String name = rs.getString("city_name");
				ArrayCity.add(new City(id, name));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayCity;
	}
	
	
}