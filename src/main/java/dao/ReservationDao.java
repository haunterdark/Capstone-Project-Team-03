package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Reservation;

public class ReservationDao {
	// Create database connection
	Connection conn = null;
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/capstone";

	public ArrayList<Reservation> Print() {
		ArrayList<Reservation> listReser = new ArrayList<Reservation>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Make connection
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "select * from reservation";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("reservation_id");
				String name = rs.getString("reservation_cusname");
				String phone = rs.getString("reservation_cusphone");
				String contents = rs.getString("reservation_contents");
				String time = rs.getString("reservation_time");
				String status = rs.getString("reservation_status");
				listReser.add(new Reservation(id, name, phone, contents, time, status));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listReser;
	}

	public void insertdata(Reservation reser) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO reservation(reservation_cusname, reservation_cusphone,  reservation_contents, reservation_time) values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, reser.getReservation_cusname());
			statement.setString(2, reser.getReservation_cusphone());
			statement.setString(3, reser.getReservation_contents());
			statement.setString(4, reser.getReservation_time());

			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.println("A reservation was inserted");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
