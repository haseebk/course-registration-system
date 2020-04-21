
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Project.Server.Controller.IDBCredentials;;

public class createDatabase implements IDBCredentials {
	private Connection dbConnection;

	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Create a connection
			dbConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("An error has occurred while connecting.");
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createStudentUserTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first_name VARCHAR(255), "
				+ " last_name VARCHAR(255), " + "user_name VARCHAR(255), " + "pass_word VARCHAR(255), "
				+ " PRIMARY KEY ( id ))";
		try {
			Statement statement = dbConnection.createStatement(); // construct a statement
			statement.executeUpdate(sql); // execute my query (i.e. sql)
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to create student user table!");
		}
		System.out.println("Student table created.");
	}

	private void createStudentCourseTable() {
		String sql = "CREATE TABLE student_courses " + "(id VARCHAR(255) not NULL, " + " first_name VARCHAR(255), "
				+ " last_name VARCHAR(255), " + " course_name VARCHAR(255), " + " course_ID INTEGER, "
				+ " section_ID INTEGER, " + " PRIMARY KEY ( id ))";
		try {
			Statement statement = dbConnection.createStatement(); // construct a statement
			statement.executeUpdate(sql); // execute my query (i.e. sql)
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to create student courses table!");
		}
		System.out.println("Student course table created.");
	}

	public void createCourseTable() {
		String sql = "CREATE TABLE COURSE " + "(id INTEGER not NULL, " + " course_name VARCHAR(255), "
				+ " course_id INTEGER, " + " PRIMARY KEY ( id ))";
		try {
			Statement statement = dbConnection.createStatement(); // construct a statement
			statement.executeUpdate(sql); // execute my query (i.e. sql)
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to create course table!");
		}
		System.out.println("Course table created.");
	}

	public void createCourseOfferingTable() {
		String sql = "CREATE TABLE COURSE_OFFERING " + "(id INTEGER not NULL, " + " course_name VARCHAR(255), "
				+ " course_id INTEGER, " + " section_id INTEGER, " + " section_capacity INTEGER, "
				+ " PRIMARY KEY ( id ))";

		try {
			Statement statement = dbConnection.createStatement(); // construct a statement
			statement.executeUpdate(sql); // execute my query (i.e. sql)
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to create course offering table!");
		}
		System.out.println("Course offering table created.");
	}

	public void insertStudentUser(int id, String firstName, String lastName, String userName, String password) {
		try {
			String query = "INSERT INTO STUDENT (ID, first_name , last_name, user_name, pass_word) values(?,?,?,?,?)";
			PreparedStatement pStatement = dbConnection.prepareStatement(query);
			pStatement.setInt(1, id);
			pStatement.setString(2, firstName);
			pStatement.setString(3, lastName);
			pStatement.setString(4, userName);
			pStatement.setString(5, password);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			System.out.println("Error: Unable to insert student user to table!");
			e.printStackTrace();
		}
	}
	public void insertStudentCourseData(String firstName, String lastName, String courseName, int courseID, int sectionID) {
		try {
			String query = "INSERT INTO student_courses (ID, first_name, last_name, course_name, course_id, section_id) values(?,?,?,?,?,?)";
			PreparedStatement pStatement = dbConnection.prepareStatement(query);
			pStatement.setString(1, firstName+lastName+courseName+courseID);
			pStatement.setString(2, firstName);
			pStatement.setString(3, lastName);
			pStatement.setString(4, courseName);
			pStatement.setInt(5, courseID);
			pStatement.setInt(6,  sectionID);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			System.out.println("Error: Unable to insert student course to table!");
			e.printStackTrace();
		}
	}

	public void insertCourse(int id, String courseName, int courseID) {
		try {
			String query = "INSERT INTO COURSE (ID, course_name , course_id) values(?,?,?)";
			PreparedStatement pStatement = dbConnection.prepareStatement(query);
			pStatement.setInt(1, id);
			pStatement.setString(2, courseName);
			pStatement.setInt(3, courseID);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			System.out.println("Error: Unable to insert course to table!");
			e.printStackTrace();
		}
	}

	public void insertCourseOffering(int id, String courseName, int courseID, int sectionID, int sectionCapacity) {
		try {
			String query = "INSERT INTO COURSE_OFFERING (ID, course_name , course_id, section_id, section_capacity) values(?,?,?,?,?)";
			PreparedStatement pStatement = dbConnection.prepareStatement(query);
			pStatement.setInt(1, id);
			pStatement.setString(2, courseName);
			pStatement.setInt(3, courseID);
			pStatement.setInt(4, sectionID);
			pStatement.setInt(5, sectionCapacity);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			System.out.println("Error: Unable to insert course offering to table!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		createDatabase myApp = new createDatabase();
		myApp.initializeConnection();
		myApp.createStudentUserTable();
		myApp.createCourseTable();
		myApp.createCourseOfferingTable();
		myApp.createStudentCourseTable();

		// Inserting student
		myApp.insertStudentUser(301, "Haseeb", "Khan", "haseebkhan", "password1");
		myApp.insertStudentUser(302, "Muhammad", "Tariq", "hassantariq", "password1");
		myApp.insertStudentUser(303, "Mohammad", "Moshirpour", "moshirpour", "password1");
		myApp.insertStudentUser(304, "Norm", "Bartley", "bartley", "password1");
		myApp.insertStudentUser(305, "Mahmoud", "Moussavi", "moussavi", "password1");
		myApp.insertStudentUser(306, "Johnathon", "Hudson", "hudson", "password1");
		myApp.insertStudentUser(307, "Jerrod", "Smith", "smith", "password1");
		myApp.insertStudentUser(308, "Yunlong", "Xiao", "xiao", "password1");
		myApp.insertStudentUser(309, "Sepideh", "Goli", "goli", "password1");

		// Inserting courses and their offerings
		myApp.insertCourse(1, "ENGG", 225);
		myApp.insertCourseOffering(1, "ENGG", 225, 1, 150);
		myApp.insertCourseOffering(2, "ENGG", 225, 2, 115);
		
		myApp.insertCourse(2, "ENGG", 233);
		myApp.insertCourseOffering(3, "ENGG", 233, 1, 125);
		myApp.insertCourseOffering(4, "ENGG", 233, 2, 155);
		
		myApp.insertCourse(3, "ENSF", 337);
		myApp.insertCourseOffering(5, "ENSF", 337, 1, 100);
		myApp.insertCourseOffering(6, "ENSF", 337, 2, 55);

		myApp.insertCourse(4, "ENSF", 409);
		myApp.insertCourseOffering(7, "ENSF", 409, 1, 100);
		myApp.insertCourseOffering(8, "ENSF", 409, 2, 55);

		myApp.insertCourse(5, "PHYS", 259);
		myApp.insertCourseOffering(9, "PHYS", 259, 1, 26);
		myApp.insertCourseOffering(10, "PHYS", 259, 2, 234);

		myApp.insertCourse(6, "MATH", 271);
		myApp.insertCourseOffering(11, "MATH", 271, 1, 31);
		myApp.insertCourseOffering(12, "MATH", 271, 2, 33);

		myApp.insertCourse(7, "ENCM", 353);
		myApp.insertCourseOffering(13, "ENCM", 353, 1, 76);
		myApp.insertCourseOffering(14, "ENCM", 353, 2, 51);
		
		myApp.insertCourse(8, "ENCM", 369);
		myApp.insertCourseOffering(15, "ENCM", 369, 1, 76);
		myApp.insertCourseOffering(16, "ENCM", 369, 2, 51);

		myApp.insertCourse(9, "CPSC", 319);
		myApp.insertCourseOffering(17, "CPSC", 319, 1, 58);
		myApp.insertCourseOffering(18, "CPSC", 319, 2, 65);
		
		myApp.insertCourse(10, "MATH", 275);
		myApp.insertCourseOffering(19, "MATH", 275, 1, 55);
		myApp.insertCourseOffering(20, "MATH", 275, 2, 45);
		myApp.insertCourseOffering(21, "MATH", 275, 3, 71);


		myApp.insertStudentCourseData("Haseeb", "Khan", "ENSF", 409, 1);
		myApp.insertStudentCourseData("Muhammad", "Tariq", "ENSF", 409, 1);
		myApp.insertStudentCourseData("Mohammad", "Moshirpour", "ENSF", 409, 1);
		myApp.insertStudentCourseData("Norm", "Bartley", "ENCM", 369, 1);
		myApp.insertStudentCourseData("Mahmoud", "Moussavi", "ENSF", 337, 1);
		myApp.insertStudentCourseData("Johnathon", "Hudson", "CPSC", 319, 1);
		myApp.insertStudentCourseData("Jerrod", "Smith", "MATH", 271, 1);
		myApp.insertStudentCourseData("Yunlong", "Xiao", "MATH", 275, 1);
		myApp.insertStudentCourseData("Sepideh", "Goli", "ENGG", 233, 1);

		myApp.closeConnection();
	}

}
