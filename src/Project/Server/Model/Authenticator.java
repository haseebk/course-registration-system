package Project.Server.Model;

import java.io.Serializable;

public class Authenticator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Backend backend;
	private boolean successfulReg;

	public Authenticator(Backend backend) {
		this.backend = backend;
	}

	public Account loginAuth(String username, String password) {
		for (Student currentStudent : backend.getStudents()) {
			if (currentStudent.getAccount().getUsername().toLowerCase().compareTo(username.toLowerCase()) == 0) {
				if (currentStudent.getAccount().getPassword().compareTo(password) == 0) {
					return currentStudent.getAccount();
				} else {
					System.out.println("Invalid password");
				}
			} else {
				System.out.println("Invalid username and password");
			}
		}
		return null;
	}

	public String registerAuth(String username, String password, boolean confirmPass) {
		boolean validUser = Account.validateUsername(username);
		boolean validPass = Account.validatePassword(password);
		boolean uniqueUser = checkSameUser(username);
		

		if (validUser && validPass && uniqueUser && confirmPass) {
			return "Success";
		} else if (validUser && uniqueUser == false) {
			return "username";
		} else {
			return "password";
		}
	}

	public boolean checkSameUser(String username) {
		boolean returnVal = false;
		for (Student currentStudent : backend.getStudents()) {
			if (currentStudent.getAccount().getUsername().compareToIgnoreCase(username) == 0) {
				returnVal = true;
			}
		}
		return returnVal;
	}

	public boolean isSuccessfulReg() {
		return successfulReg;
	}

	public void setSuccessfulReg(boolean successfulReg) {
		this.successfulReg = successfulReg;
	}

}
