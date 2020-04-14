package Project.Server.Model;

import java.util.ArrayList;

public class CourseOffering {

    private int secNum;
    private int secCap;
    private Course theCourse;
    //private ArrayList<Student> studentList;
    private ArrayList<Registration> offeringRegList;

    public CourseOffering(int secNum, int secCap) {
        this.setSecCap(secCap);
        this.setSecNum(secNum);
        
        //new
        offeringRegList = new ArrayList <Registration>();
    }

    public int getSecNum() {
        return secNum;
    }

    public int getSecCap() {
        return secCap;
    }

    public void setSecCap(int secCap) {
        this.secCap = secCap;
    }

    public void setSecNum(int secNum) {
        this.secNum = secNum;
    }

    public void setTheCourse(Course theCourse) {
        this.theCourse = theCourse;
    }

    public Course getTheCourse() {
        return theCourse;

    }

    public void removeRegistration(Registration registration) {
        for (int i = 0; i < offeringRegList.size(); i++) {
            if(offeringRegList.get(i).getTheOffering().getTheCourse().getCourseNum()==registration.getTheOffering().getTheCourse().getCourseNum())
                offeringRegList.remove(i);
        }
    }

    @Override
    public String toString() {
        String st = "\n";
        
        //new
        st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";

        st += "Section Num: " + getSecNum() + ", Section Cap: " + getSecCap() + "\n\n";
        //We also want to print the names of all students in the section
        //for(int i = 0; i <= getSecCap(); i++)
        //    st+= studentList(i) + "\n";
        return st;
    }

    public String viewAllCourses() {
        return theCourse.getCourseName()+" "+theCourse.getCourseNum()+", Section Number: "+secNum;
    }
    

    public void addRegistration(Registration registration) {
        offeringRegList.add(registration);
    }

    
}