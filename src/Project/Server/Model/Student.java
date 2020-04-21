package Project.Server.Model;

import java.io.Serializable;
import Project.Server.Model.Account;
import java.util.ArrayList;

/**
 * this class is dedicated to student information
 * 
 * @author Haseeb Khan and Muhammad Tariq
 *
 */
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The first name of the student
	 */
	private String studentFirstName;
	/**
	 * The last name of the student
	 */
	private String studentLastName;
	/**
	 * The id of the student
	 */
	private int studentId;
	/**
	 * The account of the student
	 */
	private Account account;
	/**
	 * A list of the course students registration
	 */
	private ArrayList<Registration> studentRegList;

	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}

	/**
	 * Constructs a student with the parameters for id, first name, last name,
	 * username and password
	 * 
	 * @param id        the student id
	 * @param firstName the first name of the student
	 * @param lastName  the last name of the student
	 * @param username  the username of student account
	 * @param password  the password of student account
	 */
	public Student(int id, String firstName, String lastName, String username, String password) {
		this.setStudentId(id);
		this.setStudentFirstName(firstName);
		this.setStudentLastName(lastName);
		setAccount(new Account(username, password, Integer.toString(id)));
		studentRegList = new ArrayList<Registration>();
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		String st = "Student Name: " + getStudentFirstName() + " " + getStudentLastName() + "\n" + "Student Id: "
				+ getStudentId() + "\n\n";
		return st;
	}
	/**
	 * adds a course registration to the student
	 * @param registration the registration.
	 */
	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
	/**
	 * removes a course registration from the student
	 * @param registration the registration.
	 */
	public void removeRegistration(Registration registration) {
		for (int i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == registration.getTheOffering()
					.getTheCourse().getCourseNum())
				studentRegList.remove(i);
		}
	}
	/**
	 * returns course index in course list
	 * @param courseName name of the course
	 * @param courseID id of the course
	 */
	public int getCourseIndex(String courseName, int courseID) {
		int b = 0;
		for (int i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase()
					.equals(courseName.toLowerCase())
					&& studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == courseID) {
				b = i;
			}
		}
		return b;
	}
	/**
	 * checks to see if a course offering from student list has been removed
	 * @param courseName name of the course
	 * @param courseID name
	 * @return true if course has been removed, false otherwise
	 */
	public boolean deleteOffering(String courseName, int courseID) {
		Registration temp = null;
		int b = 0;
		int i;
		for (i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase()
					.equals(courseName.toLowerCase())
					&& studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == courseID) {
				temp = studentRegList.get(i);
				b = i;
			}
		}
		if (temp != null) {
			temp.terminate();
			studentRegList.remove(b);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * checks to see if a student is enrolled in a course section
	 * @param courseName name of the course
	 * @param courseID id of the course
	 * @return true if already enrolled, false otherwise
	 */
	public boolean alreadyEnrolled(String courseName, int courseID) {
		for (int i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering() != null) {
				if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase()
						.equals(courseName.toLowerCase())
						&& studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == courseID) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * shows all the courses taken by the student
	 * @return return the string of all the courses taken by the student.
	 */
	public String viewAllCourses() {
		String s = "Student: " + getStudentFirstName() + "\nCourses:\n";
		for (int i = 0; i < studentRegList.size(); i++) {
			s += studentRegList.get(i).viewAllCourses() + "\n";
		}
		s += "\n-------\n";
		return s;
	}
	/**
	 * returns whether class is full or not 
	 * @return true if size is greater than 6, false otherwise
	 */
	public boolean full() {
		if (studentRegList.size() > 6) {
			return true;
		}
		return false;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}