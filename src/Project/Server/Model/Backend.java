package Project.Server.Model;

import java.util.ArrayList;

public class Backend {
    private CourseCatalogue cat;
	private ArrayList<Student> students;
	
	public Backend () {
		cat = new CourseCatalogue ();
		students = new ArrayList<Student>();
		
		//SOME EXAMPLE INPUTS
		Course myCourse = cat.searchCat("ENGG", 319);
		cat.createCourseOffering(myCourse, 1, 34);
		cat.createCourseOffering(myCourse, 2, 38);
		
		myCourse = cat.searchCat("ENSF", 409);
		cat.createCourseOffering(myCourse, 1, 42);
		cat.createCourseOffering(myCourse, 2, 61);
		
		myCourse = cat.searchCat("PHYS", 369);
		cat.createCourseOffering(myCourse, 1, 26);
		cat.createCourseOffering(myCourse, 2, 240);
		
		myCourse = cat.searchCat("MATH", 271);
		cat.createCourseOffering(myCourse, 1, 31);
		cat.createCourseOffering(myCourse, 2, 33);
		
		myCourse = cat.searchCat("ENCM", 369);
		cat.createCourseOffering(myCourse, 1, 76);
		cat.createCourseOffering(myCourse, 2, 51);
		
		myCourse = cat.searchCat("CPSC", 339);
		cat.createCourseOffering(myCourse, 1, 46);
		cat.createCourseOffering(myCourse, 2, 251);
		
		students.add(new Student ("Sara", 1));
		students.add(new Student ("Tom", 2));
		students.add(new Student ("Jared", 3));
		students.add(new Student ("David", 4));
		students.add(new Student ("Victoria", 5));
		students.add(new Student ("Moham", 6));
		students.add(new Student ("Adam", 7));
		students.add(new Student ("Macdonald", 8));
	}
	
	public String courseExist(String courseName, int courseID) {
		if (getCourse(courseName, courseID)==null) {
			return ("1");
		}
		return ("0");
	}
    
	public String studentExist(String studentName) {
		if (getStudent(studentName)==null) {
			return ("1");
		}
		return ("0");
	}
	
	public String studentCanEnroll(String studentName) {
		if (getStudent(studentName).full()==true) {
			return ("1");
		}
		return ("0");
	}
	
	public String removeCourse(String studentName, String courseName, int courseID) {
		Student student = getStudent(studentName);
		if (student.deleteOffering(courseName,courseID)==false) {
			
			return ("1");
		} 
		return ("0");
	}
	
	public String canEnroll(String studentName, String courseName, int courseID, int sectionID) {
		Course course = getCourse(courseName, courseID);

		if (course==null) {
			
			return ("1");
		}
		
		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);
		
		if (courseOffering==null) {
			
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
		return (""+course);
	}
	
	public void addCourse(String studentName, String courseName, int courseID, int sectionID) {
		Student student = getStudent(studentName);
		
		Course course = getCourse(courseName, courseID);

		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);

		student.addRegistration(new Registration(student,courseOffering));
	}
	
	public String viewCourseCatalogue() {
		return (""+cat);
	}
	
	public String viewCourseTaken() {
		String list = "";
		for (int i=0; i<students.size();i++) {
			list += students.get(i).viewAllCoursesTakenByStudent() + "\n";
		}
		return (""+list);
	}
	
	public String viewStudentList() {
		String list ="";
		for (Student s: students) {
			list += s + "\n";
		}
		return list;
	}

	private Student getStudent(String name) {
		for (int i=0; i< students.size(); i++) {
			if (students.get(i).getStudentName().toLowerCase().equals(name.toLowerCase())) {
				return students.get(i);
			}
		}
		return null;
	}
	
	private Course getCourse(String courseName, int id) {
		return cat.getCourse(courseName, id);
	}
}