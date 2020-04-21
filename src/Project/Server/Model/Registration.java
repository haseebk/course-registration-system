package Project.Server.Model;

import java.io.Serializable;

/**
 * The purpose of this class is to handle student course registrations.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class Registration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * object of the student
	 */
	private Student theStudent;
	/**
	 * object of the course offering
	 */
	private CourseOffering theOffering;
	/**
	 * student's grade
	 */
	private char grade;

	/**
	 * Contructs a registration
	 * 
	 * @param student  the student in registration
	 * @param offering the offering in registration
	 */
	Registration(Student student, CourseOffering offering) {
		completeRegistration(student, offering);
	}

	/**
	 * Completes registration by populating class fields
	 * 
	 * @param st the student of the registration
	 * @param of the offering of the registration
	 */
	void completeRegistration(Student student, CourseOffering offering) {
		this.theStudent = student;
		this.theOffering = offering;
	}

	/**
	 * Remove registration from student
	 * 
	 * @param st
	 * @param of
	 */
	void completeRemoval(Student student, CourseOffering offering) {
		theStudent = student;
		theOffering = offering;
		removeRegistration();
	}

	/**
	 * Add student registration to course offering
	 */
	public void addRegistration() {
		theOffering.addRegistration(this);
	}

	/**
	 * Remove registration using course offering
	 */
	public void removeRegistration() {
		theOffering.removeRegistration(this);
	}

	public Student getTheStudent() {
		return theStudent;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public CourseOffering getTheOffering() {
		return theOffering;
	}

	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}

	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}

	@Override
	public String toString() {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering() + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;

	}
	/**
	 * remove courses using the offering
	 */
	public void terminate() {
		theOffering.removeRegistration(this);
	}

	/**
	 * View string of all courses
	 * 
	 * @return all courses in offering
	 */
	public String viewAllCourses() {
		return theOffering.viewAllCourses();
	}
}