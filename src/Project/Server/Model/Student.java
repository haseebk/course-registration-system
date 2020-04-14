package Project.Server.Model;

import java.util.ArrayList;

public class Student {

    private String studentName;
    private int studentId;
    private ArrayList<Registration> studentRegList;


	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}
	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}

    public Student(String studentName, int studentId){
        this.setStudentName(studentName);
        this.setStudentId(studentId);
        //new
        studentRegList = new ArrayList<Registration>();
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        String st = "Student Name: " + getStudentName() + "\n" + "Student Id: " + getStudentId() + "\n\n";
        return st;
    }

    public void addRegistration(Registration registration) {
        studentRegList.add(registration);
    }

    public void removeRegistration(Registration registration) {
        for (int i = 0; i < studentRegList.size(); i++) {
            if(studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum()==registration.getTheOffering().getTheCourse().getCourseNum())
                studentRegList.remove(i);
        }
    }
    
}