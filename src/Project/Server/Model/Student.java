package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentName;
	private int studentId;
	private ArrayList<Registration> studentRegList;

	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}

	public Student(String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public String getStudentName() {
		return studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		String st = "Student Name: " + getStudentName() + "\n" + "Student Id: " + getStudentId() + "\n\n";
		return st;
	}

	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}

	public void removeRegistration(Registration registration) {
		for (int i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == registration.getTheOffering()
					.getTheCourse().getCourseNum())
				studentRegList.remove(i);
		}
	}

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

	public boolean alreadyEnrolled(String courseName, int courseID) {
		for (int i = 0; i < studentRegList.size(); i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase()
					.equals(courseName.toLowerCase())
					&& studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == courseID) {
				return true;
			}
		}
		return false;
	}

	public String viewAllCourses() {
		String s = "Student: " + getStudentName() + "\nCourses:\n";
		for (int i = 0; i < studentRegList.size(); i++) {
			s += studentRegList.get(i).viewAllCourses() + "\n";
		}
		s += "\n-------\n";
		return s;
	}

	public boolean full() {
		if (studentRegList.size() > 6) {
			return true;
		}
		return false;
	}

}