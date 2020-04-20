package Project.Server.Controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		} catch (SQLException e) {
			System.err.println("Error: Unable to import data!");
			e.printStackTrace();
			System.exit(1);
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
					resultSet.getString("last_name"), resultSet.getString("user_name"), resultSet.getString("pass_word"));
		}


	}

	private void importCourseData(Backend backend) throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from course");

		while (resultSet.next()) {
			backend.addCourse(resultSet.getString("course_name"), resultSet.getInt("course_id"));
		}
	}

}
