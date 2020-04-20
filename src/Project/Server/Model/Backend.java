package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Backend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseCatalog catalog;
	private ArrayList<Student> students;

	public Backend() {
		setCatalog(new CourseCatalog());
		setStudents(new ArrayList<Student>()); 
	}
	
	

	public String courseExist(String courseName, int courseID) {
		if (getCourse(courseName, courseID) == null) {
			return ("1");
		}
		return ("0");
	}

	public String studentExist(String firstname, String lastname) {
		if (getStudent(firstname, lastname) == null) {
			return ("1");
		}
		return ("0");
	}

	public String studentCanEnroll(String firstname, String lastname) {
		if (getStudent(firstname, lastname).full() == true) {
			return ("1");
		}
		return ("0");
	}

	public String removeCourse(String firstname, String lastname, String courseName, int courseID) {
		Student student = getStudent(firstname, lastname);
		if (student.deleteOffering(courseName, courseID) == false) {
			return ("1");
		}
		return ("0");
	}

	public String canEnroll(String firstname, String lastname, String courseName, int courseID, int sectionID) {
		Course course = getCourse(courseName, courseID);
		if (course == null) {
			return ("1");
		}

		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);
		if (courseOffering == null) {
			return ("2");
		}

		Student student = getStudent(firstname, lastname);

		if (student.alreadyEnrolled(courseName, courseID)) {
			return ("3");
		}
		return ("0");
	}

	public String searchCatalogue(String courseName, int courseID) {
		Course course = getCourse(courseName, courseID);
		return ("" + course);
	}

	public void addCourse(String firstname, String lastname, String courseName, int courseID, int sectionID) {
		Student student = getStudent(firstname, lastname);
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

	public Student getStudent(String firstname, String lastname) {
		for (int i = 0; i < getStudents().size(); i++) {
			if (getStudents().get(i).getStudentFirstName().toLowerCase().equals(firstname.toLowerCase())) {
				if (getStudents().get(i).getStudentLastName().toLowerCase().equals(lastname.toLowerCase())) {
					return getStudents().get(i);
				}
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

	public int getMySecNum(String firstname, String lastname, String courseName, String courseId) {
		int index = getStudent(firstname, lastname).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(firstname, lastname).getStudentRegList().get(index).getTheOffering().getSecNum();
	}

	public int getMySecNum(int studentId, String courseName, String courseId) {
		int index = getStudent(studentId).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentId).getStudentRegList().get(index).getTheOffering().getSecNum();
	}

	public int getMySecCap(String firstname, String lastname, String courseName, String courseId) {
		int index = getStudent(firstname, lastname).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(firstname, lastname).getStudentRegList().get(index).getTheOffering().getSecCap();
	}

	public int getMySecCap(int studentId, String courseName, String courseId) {
		int index = getStudent(studentId).getCourseIndex(courseName.trim(), Integer.parseInt(courseId));
		return getStudent(studentId).getStudentRegList().get(index).getTheOffering().getSecCap();
	}

	public void addStudent(int id, String firstName, String lastName, String username, String password) {
		students.add(new Student(id, firstName, lastName, username, password));
	}

	public void addCourseOffering(String courseName, int courseID, int sectionID, int sectionCapacity) {
		Course myCourse = catalog.searchCat(courseName, courseID);
		catalog.createCourseOffering(myCourse, sectionID, sectionCapacity);
	}

	public void addCourse(String courseName, int courseID) {
		catalog.addCourse(courseName, courseID);
	}

}