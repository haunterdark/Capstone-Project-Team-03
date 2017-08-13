package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.District;

public class DistrictDao {
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";

	public List<District> loadlistDistrict(int id) {
		List<District> ArrayDistrict = new ArrayList<District>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from district,city where city.city_id = district.city_id and district.city_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int cityid = rs.getInt("district_id");
				String name = rs.getString("district_name");
				ArrayDistrict.add(new District(cityid,name));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayDistrict;
	}
	
	
}