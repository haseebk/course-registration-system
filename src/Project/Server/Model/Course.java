package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The purpose of this class is to handle the different offerings of a course,
 * ie its different sections and data.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name of the course
	 */
	private String courseName;
	/**
	 * course number
	 */
	private int courseNum;
	/**
	 * list of prereqs of the course
	 */
	private ArrayList<Course> preRequisite;
	/**
	 * list of course offerings of the course
	 */
	private ArrayList<CourseOffering> offeringList;

	/**
	 * constructs course with specified parameters
	 * 
	 * @param courseName name of course
	 * @param courseNum  course number
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		setPreRequisite(new ArrayList<Course>());
		setOfferingList(new ArrayList<CourseOffering>());
	}

	/**
	 * adds offering of the course
	 * 
	 * @param offering the offering to be added
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error: This section belongs to another course!");
				return;
			}
			if (getOfferingList().contains(offering) == false) {
				getOfferingList().add(offering);
			}
		}
	}

	public int getCourseNum() {
		return courseNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	@Override
	public String toString() {
		String st = "\n";
		st += getCourseName().trim() + " " + getCourseNum();
		return st;
	}

	/**
	 * returns number of offerings in course
	 * 
	 * @return number of offerings
	 */
	public int getNumberOfOfferings() {
		return offeringList.size();
	}

	/**
	 * gets courseoffering at specified index
	 * 
	 * @param i desired index
	 * @return course offering at specified index
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		i = i - 1;
		if (i < 0 || i >= getOfferingList().size())
			return null;
		else
			return getOfferingList().get(i);
	}

	public ArrayList<CourseOffering> getOfferingList() {
		return offeringList;
	}

	public void setOfferingList(ArrayList<CourseOffering> offeringList) {
		this.offeringList = offeringList;
	}

	public ArrayList<Course> getPreRequisite() {
		return preRequisite;
	}

	public void setPreRequisite(ArrayList<Course> preRequisite) {
		this.preRequisite = preRequisite;
	}
}