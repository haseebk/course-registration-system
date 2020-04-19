package Project.Server.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class Authenticator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashMap<String, Account> accounts = new HashMap<String, Account>();
	private boolean successfulReg;
	
	public void importStudentAccounts() {
		try {
			File studentFile = new File("accounts.txt");
			studentFile.createNewFile();
			FileReader fReader = new FileReader(studentFile);
			BufferedReader bReader = new BufferedReader(fReader);
			
			String textLine;
			String studentAccounts[];
			
			while((textLine = bReader.readLine()) != null) {
				studentAccounts = textLine.split(" ");
				accounts.put(studentAccounts[0], new Account(studentAccounts[0], studentAccounts[1], studentAccounts[2]));
			}
			fReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Account loginAuth(String username, String password) {
		if (accounts.containsKey(username)) {
			if (accounts.get(username).getPassword().equals(password)) {
				return accounts.get(username);
			}
			else {
				System.out.println("Invalid password");
				return null;
			}
		}
		else {
			System.out.println("Invalid username and password");
			return null;
		}
	}
	public boolean isSuccessfulReg() {
		return successfulReg;
	}
	public void setSuccessfulReg(boolean successfulReg) {
		this.successfulReg = successfulReg;
	}
	

}
