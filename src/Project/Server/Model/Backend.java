package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Backend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseCatalog catalog;
	private ArrayList<Student> students;

	public Backend() {
		setCatalog(new CourseCatalog());
		students = new ArrayList<Student>();

		// SOME EXAMPLE INPUTS
		Course myCourse = getCatalog().searchCat("ENGG", 319);
		getCatalog().createCourseOffering(myCourse, 1, 34);
		getCatalog().createCourseOffering(myCourse, 2, 38);

		myCourse = getCatalog().searchCat("ENSF", 409);
		getCatalog().createCourseOffering(myCourse, 1, 42);
		getCatalog().createCourseOffering(myCourse, 2, 61);

		myCourse = getCatalog().searchCat("PHYS", 369);
		getCatalog().createCourseOffering(myCourse, 1, 26);
		getCatalog().createCourseOffering(myCourse, 2, 240);

		myCourse = getCatalog().searchCat("MATH", 271);
		getCatalog().createCourseOffering(myCourse, 1, 31);
		getCatalog().createCourseOffering(myCourse, 2, 33);

		myCourse = getCatalog().searchCat("ENCM", 369);
		getCatalog().createCourseOffering(myCourse, 1, 76);
		getCatalog().createCourseOffering(myCourse, 2, 51);

		myCourse = getCatalog().searchCat("CPSC", 339);
		getCatalog().createCourseOffering(myCourse, 1, 46);
		getCatalog().createCourseOffering(myCourse, 2, 251);

		students.add(new Student("StudentA", 1));
		students.add(new Student("StudentB", 2));
		students.add(new Student("StudentC", 3));
		students.add(new Student("StudentD", 4));
		students.add(new Student("StudentE", 5));
		students.add(new Student("StudentF", 6));
		students.add(new Student("StudentG", 7));
		students.add(new Student("StudentH", 8));
	}

	public String courseExist(String courseName, int courseID) {
		if (getCourse(courseName, courseID) == null) {
			return ("1");
		}
		return ("0");
	}

	public String studentExist(String studentName) {
		if (getStudent(studentName) == null) {
			return ("1");
		}
		return ("0");
	}

	public String studentCanEnroll(String studentName) {
		if (getStudent(studentName).full() == true) {
			return ("1");
		}
		return ("0");
	}

	public String removeCourse(String studentName, String courseName, int courseID) {
		Student student = getStudent(studentName);
		if (student.deleteOffering(courseName, courseID) == false) {

			return ("1");
		}
		return ("0");
	}

	public String canEnroll(String studentName, String courseName, int courseID, int sectionID) {
		Course course = getCourse(courseName, courseID);

		if (course == null) {

			return ("1");
		}

		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);

		if (courseOffering == null) {

			return ("2");
		}

		Student student = getStudent(studentName);

		if (student.alreadyEnrolled(courseName, courseID)) {

			return ("3");
		}

		return ("0");
	}

	public String searchCatalogue(String courseName, int courseID) {
		Course course = getCourse(courseName, courseID);
		return ("" + course);
	}

	public void addCourse(String studentName, String courseName, int courseID, int sectionID) {
		Student student = getStudent(studentName);

		Course course = getCourse(courseName, courseID);

		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);

		student.addRegistration(new Registration(student, courseOffering));
	}

	public String viewCourseCatalogue() {
		return ("" + getCatalog());
	}

	public String viewCourseTaken() {
		String list = "";
		for (int i = 0; i < students.size(); i++) {
			list += students.get(i).viewAllCourses() + "\n";
		}
		return ("" + list);
	}

	public String viewStudentList() {
		String list = "";
		for (Student s : students) {
			list += s + "\n";
		}
		return list;
	}

	private Student getStudent(String name) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getStudentName().toLowerCase().equals(name.toLowerCase())) {
				return students.get(i);
			}
		}
		return null;
	}

	private Course getCourse(String courseName, int id) {
		return getCatalog().getCourse(courseName, id);
	}

	public CourseCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(CourseCatalog catalog) {
		this.catalog = catalog;
	}
}