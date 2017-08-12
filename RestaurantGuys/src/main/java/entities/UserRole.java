package entities;

public class UserRole {

	private int userrole_id;
	private String userrole_name;
	private String userrole_role;

	public UserRole(String userrole_name) {
		this.userrole_name = userrole_name;
	}

	public UserRole() {

	}

	public int getUserrole_id() {
		return userrole_id;
	}

	public void setUserrole_id(int userrole_id) {
		this.userrole_id = userrole_id;
	}

	public String getUserrole_name() {
		return userrole_name;
	}

	public void setUserrole_name(String userrole_name) {
		this.userrole_name = userrole_name;
	}

	public String getUserrole_role() {
		return userrole_role;
	}

	public void setUserrole_role(String userrole_role) {
		this.userrole_role = userrole_role;
	}
}
