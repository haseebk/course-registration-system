package Project.Server.Controller;

import Project.Server.Model.Backend;

import java.sql.*;

public class MySQLJDBC implements IDBCredentials {

	// Attributes
	private Connection dbConnection;

	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
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

	public void obtainBackendData(Backend backend) {
		try {
			importStudentData(backend);
			importCourseData(backend);
			importCourseOfferingData(backend);
			importStudentCourseData(backend);
		} catch (SQLException e) {
			System.err.println("Error: Unable to import data!");
			e.printStackTrace();
			System.exit(1);
		}
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

	public void removeStudentCourseData(String firstName, String lastName, String courseName, int courseId) {
		Statement statement;
		try {
			statement = dbConnection.createStatement();
			String delete = "DELETE FROM student_courses WHERE id = '" + firstName + lastName + courseName + courseId
					+ "'";
			statement.executeUpdate(delete);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to delete student course!");
		}
		System.out.println("Course removed.");
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

	private void importCourseOfferingData(Backend backend) throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from course_offering");

		while (resultSet.next()) {
			backend.addCourseOffering(resultSet.getString("course_name"), resultSet.getInt("course_id"),
					resultSet.getInt("section_id"), resultSet.getInt("section_capacity"));
		}

	}

	private void importStudentData(Backend backend) throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from student");

		while (resultSet.next()) {
			backend.addStudent(resultSet.getInt("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("user_name"),
					resultSet.getString("pass_word"));
		}

	}

	private void importCourseData(Backend backend) throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from course");

		while (resultSet.next()) {
			backend.addCourse(resultSet.getString("course_name"), resultSet.getInt("course_id"));
		}
	}

	private void importStudentCourseData(Backend backend) throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from student_courses");

		while (resultSet.next()) {
			backend.addCourse(resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("course_name"), resultSet.getInt("course_ID"), resultSet.getInt("section_ID"));
		}
	}

}
