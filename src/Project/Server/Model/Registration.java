package Project.Server.Model;

public class Registration {

    private Student theStudent;
    private CourseOffering theOffering;
    private char grade;
    
    Registration (Student student, CourseOffering offering) {
        completeRegistration(student, offering);
    }
    //new
    void completeRegistration(Student st, CourseOffering of) {
        theStudent = st;
        theOffering = of;
        addRegistration();
    }
    
    void completeRemoval(Student st, CourseOffering of) {
        theStudent = st;
        theOffering = of;
        removeRegistration();
    }

    //public Registration() {
    //}
    
    public void addRegistration() {
        theStudent.addRegistration(this);
        theOffering.addRegistration(this);
    }

    public void removeRegistration() {
        theStudent.removeRegistration(this);
        theOffering.removeRegistration(this);
    }

    public Student getTheStudent() {
        return theStudent;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public CourseOffering getTheOffering() {
        return theOffering;
    }

    public void setTheOffering(CourseOffering theOffering) {
        this.theOffering = theOffering;
    }

    public void setTheStudent(Student theStudent) {
        this.theStudent = theStudent;
    }

    //new
    @Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}

	public void terminate() {
        theOffering.removeRegistration(this);
    }
    
    public String viewAllCourses() {
        return theOffering.viewAllCourses();
    }
}