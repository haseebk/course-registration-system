package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseName;
	private int courseNum;
	private ArrayList<Course> preRequisite;
	private ArrayList<CourseOffering> offeringList;

	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preRequisite = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			offeringList.add(offering);
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
		st += getCourseName() + " " + getCourseNum();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}

	public CourseOffering getCourseOfferingAt(int i) {
		if (i < 0 || i >= offeringList.size())
			return null;
		else
			return offeringList.get(i);
	}
}