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
		setStudents(new ArrayList<Student>()); 

		// SOME EXAMPLE INPUTS
		Course myCourse = getCatalog().searchCat("ENSF", 409);
		getCatalog().createCourseOffering(myCourse, 1, 11);
		getCatalog().createCourseOffering(myCourse, 2, 22);

		myCourse = getCatalog().searchCat("PHYS", 369);
		getCatalog().createCourseOffering(myCourse, 1, 11);
		getCatalog().createCourseOffering(myCourse, 2, 22);

		myCourse = getCatalog().searchCat("MATH", 271);
		getCatalog().createCourseOffering(myCourse, 1, 11);
		getCatalog().createCourseOffering(myCourse, 2, 22);



		getStudents().add(new Student("Student A", 10000));
		addCourse("Student A", "PHYS", 369, 1);
		addCourse("Student A", "ENSF", 409, 2);
		addCourse("Student A", "MATH", 271, 1);
		getStudents().add(new Student("Student B", 20000));
		getStudents().add(new Student("Student C", 30000));
		getStudents().add(new Student("Student D", 40000));
		getStudents().add(new Student("Student E", 50000));
		getStudents().add(new Student("Student F", 60000));
		getStudents().add(new Student("Student G", 70000));
		getStudents().add(new Student("Student H", 80000));

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
		for (int i = 0; i < getStudents().size(); i++) {
			list += getStudents().get(i).viewAllCourses() + "\n";
		}
		return ("" + list);
	}

	public String viewStudentList() {
		String list = "";
		for (Student s : getStudents()) {
			list += s + "\n";
		}
		return list;
	}

	public Student getStudent(String name) {
		for (int i = 0; i < getStudents().size(); i++) {
			if (getStudents().get(i).getStudentName().toLowerCase().equals(name.toLowerCase())) {
				return getStudents().get(i);
			}
		}
		return null;
	}
	public Student getStudent(int id) {
		for (int i = 0; i < getStudents().size(); i++) {
			if (getStudents().get(i).getStudentId() == id) {
				return getStudents().get(i);
			}
		}
		return null;
	}

	public Course getCourse(String courseName, int id) {
//		return getCatalog().getCourse(courseName, id);
		return getCatalog().searchCat(courseName, id);

	}

	public CourseCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(CourseCatalog catalog) {
		this.catalog = catalog;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	public int getMySecNum(String studentName, String courseName, String courseId) {
		int index = getStudent(studentName).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentName).getStudentRegList().get(index).getTheOffering().getSecNum();
	}
	public int getMySecNum(int studentId, String courseName, String courseId) {
		int index = getStudent(studentId).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentId).getStudentRegList().get(index).getTheOffering().getSecNum();
	}
	public int getMySecCap(String studentName, String courseName, String courseId) {
		int index = getStudent(studentName).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentName).getStudentRegList().get(index).getTheOffering().getSecCap();
	}
	public int getMySecCap(int studentId, String courseName, String courseId) {
		int index = getStudent(studentId).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentId).getStudentRegList().get(index).getTheOffering().getSecCap();
	}
	

}