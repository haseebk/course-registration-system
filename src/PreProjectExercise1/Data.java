package PreProjectExercise1;

/**
 * Represents data within a node
 */
public class Data {

	/**
	 * id is the student's id faculty is the student's faculty
	 */
	String id, faculty, major, year;

	public Data(String i, String f, String m, String y) {
		id = i;
		faculty = f;
		major = m;
		year = y;
	}

	public String toString() {
		return ("ID: " + id + " Faculty: " + faculty + " Major: " + major + " Year: " + year);
	}
}
