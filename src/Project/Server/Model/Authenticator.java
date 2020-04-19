package Project.Server.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Authenticator {
	protected HashMap<String, Account> accounts = new HashMap<String, Account>();
	private boolean successfulReg;
	
	public void importStudentAccounts() {
		try {
			File studentFile = new File("accounts.txt");
			studentFile.createNewFile();
			FileReader fReader = new FileReader(studentFile);
			BufferedReader bReader = new BufferedReader(fReader);
			
			
		}
	}
	

}
