package Project.Server.Model;

import java.io.Serializable;

public class Registration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;

	Registration(Student student, CourseOffering offering) {
		completeRegistration(student, offering);
	}

	void completeRegistration(Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration();
	}

	void completeRemoval(Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		removeRegistration();
	}

	public void addRegistration() {
		theOffering.addRegistration(this);
	}

	public void removeRegistration() {
		theStudent.removeRegistration(this);
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

	public void terminate() {
		theOffering.removeRegistration(this);
	}

	public String viewAllCourses() {
		return theOffering.viewAllCourses();
	}
}