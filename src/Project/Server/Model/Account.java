package Project.Server.Model;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private int studentId;

	public Account(String username, String password, String studentId) {
		this.setUsername(username);
		this.setPassword(password);
		this.setStudentId(Integer.parseInt(studentId));
	}
	
	public static boolean validateUsername(String username) {
		if (username.length() >= 4 && username.length() <= 25) {
			return true;
		}
		return false;
	}
	public static boolean validatePassword(String password) {
		if (password.length() >= 5) {
			return true;
		}
		return false;
	}
	

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Boolean setUsername(String username) {
		if (Account.validateUsername(username)) {
			this.username = username;
			return true;
		}
		return false;
	}

	public Boolean setPassword(String password) {
		if (Account.validatePassword(password)) {
			this.password = password;
			return true;
		}
		return false;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
