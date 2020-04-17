package Project.Client.View;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import Project.Server.Model.CourseOffering;
import Project.Server.Model.Registration;
import Project.Server.Model.Student;

/**
 * This class runs the user's Courses panel view. It displays the courses that
 * the user is currently enrolled in with the option to add/remove courses,
 * return to homepage, logout, or exit the application.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class MyCourses extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel backButton;

	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 */
	public MyCourses(JFrame frame, Backend backend) {
		setLayout(null);
		JLabel exitButton = new JLabel("");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setBounds(1286, 30, 50, 50);
		exitButton.setIcon(new ImageIcon(MyCourses.class.getResource("/exitButton.png")));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		add(exitButton);
		
		JLabel addCourseButton = new JLabel("");
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(MyCourses.class.getResource("/addCourseButton.png")));
		addCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseCatalog courseCatalogPanel = new CourseCatalog(frame, backend);
				frame.setContentPane(courseCatalogPanel);
				frame.revalidate();
			}
		});
		addCourseButton.setBounds(824, 668, 152, 50);
		add(addCourseButton);
			

		backButton = new JLabel("");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StandardUserHome homePanel = new StandardUserHome(frame, backend);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(MyCourses.class.getResource("/backButton.png")));
		add(backButton);

		// CREATE SCROLLABLE LIST OF COURSES IN CATALOG
		DefaultListModel<String> theList = new DefaultListModel<String>();
		for (Registration registration : backend.getStudent("Student A").getStudentRegList()){
			theList.addElement(registration.getTheOffering().getTheCourse().toString());
			System.out.println("count");
		}
		JList<String> list = new JList<String>(theList);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		scrollPane.setBounds(399, 148, 568, 480);
		scrollPane.setVisible(true);
		
		JLabel myCourseBackground = new JLabel("");
		myCourseBackground.setBounds(0, 0, 1366, 768);
		myCourseBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(myCourseBackground);

	}
}
