package Project.Client.View;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.MatteBorder;

import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import Project.Server.Model.CourseOffering;
import Project.Server.Model.Registration;
import Project.Server.Model.Student;
import java.awt.Font;

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

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(609, 29, 150, 131);
		// uniLogo.setIcon(new ImageIcon(MyCourses.class.getResource("/uniLogoB.png")));
		add(uniLogo);
		
		// CREATE COURSE INFO TEXT LABELS
		JLabel courseSectionCapacityLabel = new JLabel("Section Capacity: "); // SECTION CAPACITY
		courseSectionCapacityLabel.setFont(new Font("Arial", Font.BOLD, 14));
		courseSectionCapacityLabel.setForeground(Color.WHITE);
		courseSectionCapacityLabel.setBounds(1009, 271, 251, 14);
		add(courseSectionCapacityLabel);
		JLabel courseSectionNumberLabel = new JLabel("Section Number: "); // SECTION NUMBER
		courseSectionNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));
		courseSectionNumberLabel.setForeground(Color.WHITE);
		courseSectionNumberLabel.setBounds(1009, 246, 251, 14);
		add(courseSectionNumberLabel);
		JLabel courseIDLabel = new JLabel("Course ID: "); // COURSE ID
		courseIDLabel.setForeground(Color.WHITE);
		courseIDLabel.setFont(new Font("Arial", Font.BOLD, 14));
		courseIDLabel.setBounds(1009, 221, 251, 14);
		add(courseIDLabel);
		JLabel courseNameLabel = new JLabel("Course Name: "); // COURSE NAME
		courseNameLabel.setForeground(Color.WHITE);
		courseNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
		courseNameLabel.setBounds(1009, 196, 251, 14);
		add(courseNameLabel);

		// CREATE COURSE-INFO CARD
		JLabel courseInfoCard = new JLabel("");
		courseInfoCard.setBounds(996, 169, 280, 151);
		courseInfoCard.setIcon(new ImageIcon(CourseCatalog.class.getResource("/smallCard.png")));
		add(courseInfoCard);

		// CREATE EXIT BUTTON
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

		// CREATE LOGOUT BUTTON
		JLabel logoutButton = new JLabel("");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutButton.setBounds(1206, 30, 50, 50);
		logoutButton.setIcon(new ImageIcon(MyCourses.class.getResource("/logoutButton.png")));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login loginPanel = new Login(frame, backend);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		add(logoutButton);

		// CREATE ADD COURSE BUTTON
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

		// CREATE BACK BUTTON
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
		for (Registration registration : backend.getStudent("Student A").getStudentRegList()) {
			theList.addElement(registration.getTheOffering().getTheCourse().toString());
		}
		JList<String> list = new JList<String>(theList);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		scrollPane.setBounds(400, 177, 568, 480);
		scrollPane.setVisible(true);

		// CREATE MOUSELISTENER FOR DOUBLE-CLICKING COURSE TO DISPLAY INFO
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String selectedCourse = (String) list.getSelectedValue();
					String[] courseDetail = selectedCourse.split(" ");
					int secNum = backend.getMySecNum("Student A", courseDetail[0], courseDetail[1]);
					int secCap = backend.getMySecCap("Student A", courseDetail[0], courseDetail[1]);
					Course result = backend.getCatalog().searchCat(courseDetail[0].trim(),
							Integer.parseInt(courseDetail[1]));

//					Course result = backend.getCatalog().searchCat("PHYS", 369);
					if (result == null) {
						JOptionPane.showMessageDialog(null, "\nAn error occurred!", " Warning",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						courseNameLabel.setText("Course Name: " + result.getCourseName());
						courseIDLabel.setText("Course ID: " + result.getCourseNum());
						courseSectionNumberLabel.setText("Section Number: " + secNum);
						courseSectionCapacityLabel.setText("Section Capacity: " + secCap);
						frame.revalidate();
					}
//					int index = backend.getStudent("Student A").getCourseIndex(courseDetail[0].trim(), Integer.parseInt(courseDetail[1]));
//					int secNum = backend.getStudent("Student A").getStudentRegList().get(index).getTheOffering().getSecNum();
//					int secCap = backend.getStudent("Student A").getStudentRegList().get(index).getTheOffering().getSecCap();
//					Course result = backend.getCatalog().searchCat(courseDetail[0].trim(), Integer.parseInt(courseDetail[1]));
//					
////					Course result = backend.getCatalog().searchCat("PHYS", 369);
//					if (result == null) {
//						JOptionPane.showMessageDialog(null, "\nAn error occurred!", " Warning",
//								JOptionPane.PLAIN_MESSAGE);
//
//					} else {
//						JOptionPane.showMessageDialog(null,
//								"Course name: " + result.getCourseName() + "\nCourse ID: " + result.getCourseNum()
//										+ " Offerings: " + result.getOfferingList(),
//								" Result", JOptionPane.INFORMATION_MESSAGE);
//					}
				}
			}
		};
		list.addMouseListener(mouseListener);

		// CREATE BACKGROUND VIEW
		JLabel myCourseBackground = new JLabel("");
		myCourseBackground.setBounds(0, 0, 1366, 768);
		myCourseBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));

		add(myCourseBackground);

	}
}
