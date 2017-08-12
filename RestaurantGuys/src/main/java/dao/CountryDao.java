package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Country;

public class CountryDao {
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

	public List<Country> loadlistCountry() {
		List<Country> ArrayCountry = new ArrayList<Country>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from country";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("country_id");
				String name = rs.getString("country_name");
				ArrayCountry.add(new Country(id, name));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayCountry;
	}
}
