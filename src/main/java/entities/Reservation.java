package entities;

public class Reservation {
	private String reservation_id;
	private String reservation_cusname;
	private String reservation_cusphone;
	private String reservation_contents;
	private String reservation_time;
	private String reservation_status;

	public Reservation() {

	}

	public Reservation(String reservation_id, String reservation_cusname, String reservation_cusphone,
			String reservation_contents, String reservation_time, String reservation_status) {
		this.reservation_id = reservation_id;
		this.reservation_cusname = reservation_cusname;
		this.reservation_cusphone = reservation_cusphone;
		this.reservation_contents = reservation_contents;
		this.reservation_time = reservation_time;
		this.reservation_status = reservation_status;
	}

	public Reservation(String reservation_cusname, String reservation_cusphone, String reservation_contents,
			String reservation_time) {
		this.reservation_cusname = reservation_cusname;
		this.reservation_cusphone = reservation_cusphone;
		this.reservation_contents = reservation_contents;
		this.reservation_time = reservation_time;

	}

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getReservation_cusname() {
		return reservation_cusname;
	}

	public void setReservation_cusname(String reservation_cusname) {
		this.reservation_cusname = reservation_cusname;
	}

	public String getReservation_cusphone() {
		return reservation_cusphone;
	}

	public void setReservation_cusphone(String reservation_cusphone) {
		this.reservation_cusphone = reservation_cusphone;
	}

	public String getReservation_contents() {
		return reservation_contents;
	}

	public void setReservation_contents(String reservation_contents) {
		this.reservation_contents = reservation_contents;
	}

	public String getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
	}

	public String getReservation_status() {
		return reservation_status;
	}

	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}
}