package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Category;

public class CategoryDao {
	Connection conn = null;
	String userName = "root";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/capstone?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

	public List<Category> loadlistCategory() {
		List<Category> ArrayCategory = new ArrayList<Category>();

		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from category";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("category_id");
				String name = rs.getString("category_name");
				ArrayCategory.add(new Category(id, name));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArrayCategory;
	}
}
