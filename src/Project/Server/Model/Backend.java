
package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is responsible for controlling the entire backend and allowing
 * frontend interaction
 * 
 * @author Haseeb Khan and Muhammad Tariq
 *
 */
public class Backend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * attribute for the course catalogue
	 */
	private CourseCatalog catalog;
	/**
	 * attribute for the list of students
	 */
	private ArrayList<Student> students;

	/**
	 * constructor for backend that initializes the catalogue and the list of
	 * students as empty or null.
	 */
	public Backend() {
		setCatalog(new CourseCatalog());
		setStudents(new ArrayList<Student>());
	}

	/**
	 * the purpose of this method is to check if the course specified exists. It
	 * returns 0 if it exists and 1 if it does not exist.
	 * 
	 * @param courseName name of course
	 * @param courseID   id of course
	 * @return truth value of course existing
	 */
	public int courseExist(String courseName, int courseID) {
		if (getCourse(courseName, courseID) == null) {
			return (1);
		}
		return (0);
	}

	/**
	 * the purpose of this method is to check if the student specified exists. It
	 * returns 0 if it exists and 1 if it does not exist.
	 * 
	 * @param firstname first name of student
	 * @param lastname  last name of student
	 * @return true value of student existing
	 */
	public String studentExist(String firstname, String lastname) {
		if (getStudent(firstname, lastname) == null) {
			return ("1");
		}
		return ("0");
	}

	/**
	 * the purpose of this method is to check if the specified student can enroll in
	 * a certain course or not. It returns 0 if the student can enroll and 1 if it
	 * cannot enroll.
	 * 
	 * @param firstname first name of student
	 * @param lastname  last name of student
	 * @return truth value of student being able to enroll.
	 */
	public String studentCanEnroll(String firstname, String lastname) {
		if (getStudent(firstname, lastname).full() == true) {
			return ("1");
		}
		return ("0");
	}

	/**
	 * the purpose of this method is to remove the specified course from a student's
	 * profile. It returns 0 if successful and 1 if unsuccessful.
	 * 
	 * @param firstname  first name of student
	 * @param lastname   last name of student
	 * @param courseName name of course
	 * @param courseID   id of course
	 * @return truth value of success in removal
	 */
	public String removeCourse(String firstname, String lastname, String courseName, int courseID) {
		Student student = getStudent(firstname, lastname);
		if (student.deleteOffering(courseName, courseID) == false) {
			return ("1");
		}
		return ("0");
	}

	/**
	 * the purpose of this method is to check if the spciefied student can enroll in
	 * a specified course. It returns 0 if the student can enroll and 1 or 2 if an
	 * error occurs and the student cannot enroll.
	 * 
	 * @param firstname  first name of student
	 * @param lastname   last name of student
	 * @param courseName name of course
	 * @param courseID   id of course
	 * @param sectionID  section number of course
	 * @return truth value of student being able to enroll
	 */
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

	/**
	 * the purpose of this method is to search the catalogue. It returns the name of
	 * course being searched for in the catalogue.
	 * 
	 * @param courseName name of course
	 * @param courseID   id of course
	 * @return name of course found
	 */
	public String searchCatalogue(String courseName, int courseID) {
		Course course = getCourse(courseName, courseID);
		return ("" + course);
	}

	/**
	 * the purpose of this method is to add a course to the specified student's
	 * profile. It is void so does not return anything.
	 * 
	 * @param firstname  first name of student
	 * @param lastname   last name of student
	 * @param courseName name of course
	 * @param courseID   id of course
	 * @param sectionID  section number of course
	 */
	public void addCourse(String firstname, String lastname, String courseName, int courseID, int sectionID) {
		Student student = getStudent(firstname, lastname);
		Course course = getCourse(courseName, courseID);
		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);
		if (getStudent(firstname, lastname).alreadyEnrolled(courseName, courseID) == false) {
			student.addRegistration(new Registration(student, courseOffering));
		}
	}

	/**
	 * the purpose of this method is to view the course catalogue. returns the
	 * catalog using a getter of it.
	 * 
	 * @return the catalogue
	 */
	public String viewCourseCatalogue() {
		return ("" + getCatalog());
	}

	/**
	 * the purpose of this method is to view the courses taken by all students
	 * 
	 * @return the list of courses taken by all students
	 */
	public String viewCourseTaken() {
		String list = "";
		for (int i = 0; i < getStudents().size(); i++) {
			list += getStudents().get(i).viewAllCourses() + "\n";
		}
		return ("" + list);
	}

	/**
	 * the purpose of this method is to view the list of all students
	 * 
	 * @return the list of all students
	 */
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

	/**
	 * the purpose of this method is to add the student to database
	 * 
	 * @param id        student id
	 * @param firstName first name of student
	 * @param lastName  last name of student
	 * @param username  username of student
	 * @param password  password of student
	 */
	public void addStudent(int id, String firstName, String lastName, String username, String password) {
		if (getStudent(id) == null) {
			students.add(new Student(id, firstName, lastName, username, password));

		}
	}

	/**
	 * the purpose of this method is to add a course offering to database
	 * 
	 * @param courseName      name of course
	 * @param courseID        id of course
	 * @param sectionID       section number of course
	 * @param sectionCapacity section capacity of course
	 */
	public void addCourseOffering(String courseName, int courseID, int sectionID, int sectionCapacity) {
		Course myCourse = catalog.searchCat(courseName, courseID);
		catalog.createCourseOffering(myCourse, sectionID, sectionCapacity);
	}

	/**
	 * the purpose of this method is to add course to database
	 * 
	 * @param courseName name of course
	 * @param courseID   id of course
	 */
	public void addCourse(String courseName, int courseID) {
		catalog.addCourse(courseName, courseID);
	}

}