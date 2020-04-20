package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseCatalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Course> courseList;

	public CourseCatalog() {
		courseList = new ArrayList<Course>();
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public void createCourseOffering(Course c, int secNum, int secCap) {
		if (c != null) {
			CourseOffering theOffering = new CourseOffering(secNum, secCap);
			c.addOffering(theOffering);
		}
	}

	public Course searchCat(String courseName, int courseNum) {

		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) {
				return c;
			}
		}
		displayCourseNotFoundError();
		return null;
	}

	private void displayCourseNotFoundError() {
		System.err.println("Course was not found!");
	}

	@Override
	public String toString() {
		String st = "";
		for (Course c : courseList) {
			st += c;
			st += "\n";
		}
		return st;
	}

	public Course getCourse(String courseName, int id) {
		for (int i = 0; i < courseList.size(); i++) {
			if (courseName.toLowerCase().equals(courseList.get(i).getCourseName().toLowerCase())
					&& id == courseList.get(i).getCourseNum()) {
				return courseList.get(i);
			}
		}
		return null;
	}

	public void addCourse(String courseName, int courseID) {
		courseList.add(new Course(courseName, courseID));
	}
}