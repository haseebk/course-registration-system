package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * The purpose of this class is to handle the different offerings of a course, ie its different sections and data.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class CourseOffering implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the section number
	 */
	private int secNum;
	/**
	 * the section capacity
	 */
	private int secCap;
	/**
	 * the offered course
	 */
	private Course theCourse;
	/**
	 * list of registrations of the course
	 */
	private ArrayList<Registration> offeringRegList;
	/**
	 * Construct a course offering using provided parameters
	 * @param secNum section number
	 * @param secCap section capacity
	 */
	public CourseOffering(int secNum, int secCap) {
		this.setSecCap(secCap);
		this.setSecNum(secNum);
		offeringRegList = new ArrayList<Registration>();
	}

	public int getSecNum() {
		return secNum;
	}

	public int getSecCap() {
		return secCap;
	}

	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}

	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}

	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}

	public Course getTheCourse() {
		return theCourse;

	}
	/**
	 * remove registration from student courses
	 * @param registration the registration to be removed
	 */
	public void removeRegistration(Registration registration) {
		for (int i = 0; i < offeringRegList.size(); i++) {
			if (offeringRegList.get(i).getTheOffering().getTheCourse().getCourseNum() == registration.getTheOffering()
					.getTheCourse().getCourseNum())
				offeringRegList.remove(i);
		}
	}

	@Override
	public String toString() {
		String st = "\n";

		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + " | Section Capacity: " + getSecCap() + "\n";
		return st;
	}
	/**
	 * view all the courses
	 * @return string of courses
	 */
	public String viewAllCourses() {
		return theCourse.getCourseName() + " " + theCourse.getCourseNum() + ", Section Number: " + secNum;
	}
	/**
	 * add registration to student courses
	 * @param registration the registration to be added
	 */
	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);
	}

}