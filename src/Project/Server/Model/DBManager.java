package Project.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

//This class is simulating a database for our program
public class DBManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Course> courseList;

	public DBManager() {
		courseList = new ArrayList<Course>();
	}

	public ArrayList<Course> readFromDataBase() {
		courseList.add(new Course("ENGG", 200));
		courseList.add(new Course("ENGG", 201));
		courseList.add(new Course("ENGG", 202));
		courseList.add(new Course("ENGG", 225));
		courseList.add(new Course("ENGG", 233));
		courseList.add(new Course("MATH", 271));
		courseList.add(new Course("MATH", 275));
		courseList.add(new Course("MATH", 277));
		courseList.add(new Course("MATH", 211));
		courseList.add(new Course("PHYS", 259));
		courseList.add(new Course("PHYS", 369));
		courseList.add(new Course("CHEM", 209));
		courseList.add(new Course("ENGG", 319));
		courseList.add(new Course("ENGG", 513));
		courseList.add(new Course("ENSF", 337));
		courseList.add(new Course("ENSF", 409));

		return courseList;
	}
}