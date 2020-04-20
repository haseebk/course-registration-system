package Project.Client.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Project.Server.Model.Authenticator;
import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class runs the Course Catalog panel view. It displays the courses that
 * are in the catalog with the option for user to add courses, return to
 * homepage, logout, or exit the application.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class CourseCatalog extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel backButton;
	protected JList<String> list;

	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 * @param auth 
	 */
	public CourseCatalog(JFrame frame, Backend backend, Authenticator auth) {
		setLayout(null);

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(609, 29, 150, 131);
		uniLogo.setIcon(new ImageIcon(CourseCatalog.class.getResource("/uniLogoB.png")));
		add(uniLogo);
		JLabel courseSectionNumberLabel = new JLabel("Available Sections: "); // SECTION NUMBER
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
		courseInfoCard.setBounds(996, 177, 280, 111);
		courseInfoCard.setIcon(new ImageIcon(CourseCatalog.class.getResource("/smallerCard.png")));
		add(courseInfoCard);

		// CREATE EXIT BUTTON
		JLabel exitButton = new JLabel("");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setBounds(1286, 30, 50, 50);
		exitButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/exitButtonOutline.png")));
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
		logoutButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/logoutButtonOutline.png")));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login loginPanel = new Login(frame, backend, auth);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		add(logoutButton);

		// CREATE ADD TO COURSES BUTTON
		JLabel addCourseButton = new JLabel("");
		addCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedCourse = (String) list.getSelectedValue();
				String[] courseDetail = selectedCourse.split(" ");
				Course result = backend.getCatalog().searchCat(courseDetail[0].trim(),
						Integer.parseInt(courseDetail[1]));
				if (result == null) {
					JOptionPane.showMessageDialog(null,
							"\nAn error occurred! Please make sure you have selected a course to add.", " Warning",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					System.out.println(result.getOfferingList().toString().replace("[", "").replace("]", "").replace(",", ""));

				}
			}
		});
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/addToCoursesButton.png")));
		addCourseButton.setBounds(995, 291, 147, 50);
		add(addCourseButton);

		// CREATE SCROLLABLE LIST OF COURSES IN CATALOG
		DefaultListModel<String> theList = new DefaultListModel<String>();
		for (Course course : backend.getCatalog().getCourseList()) {
			theList.addElement(course.toString());
		}
		this.list = new JList<String>(theList);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		scrollPane.setBounds(400, 177, 568, 480);
		scrollPane.setVisible(true);

		// CREATE MOUSELISTENER FOR DOUBLE-CLICKING COURSE TO DISPLAY INFO
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedCourse = (String) list.getSelectedValue();
				String[] courseDetail = selectedCourse.split(" ");
				Course result = backend.getCatalog().searchCat(courseDetail[0].trim(),
						Integer.parseInt(courseDetail[1]));
				if (result == null) {
					JOptionPane.showMessageDialog(null, "\nAn error occurred!", " Warning",
							JOptionPane.PLAIN_MESSAGE);
				} else {
//					System.out.println(result.getNumberOfOfferings());
					courseNameLabel.setText("Course Name: " + result.getCourseName());
					courseIDLabel.setText("Course ID: " + result.getCourseNum());
					courseSectionNumberLabel.setText("Available Sections: " + result.getNumberOfOfferings());
					frame.revalidate();
				}
			}
		});

		// CREATE BACK BUTTON
		backButton = new JLabel("");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StandardUserHome homePanel = new StandardUserHome(frame, backend, auth);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/backButton.png")));
		add(backButton);

		// CREATE BACKGROUND VIEW
		JLabel courseCatalogBackground = new JLabel("");
		courseCatalogBackground.setBounds(0, 0, 1366, 768);
		courseCatalogBackground.setIcon(new ImageIcon(CourseCatalog.class.getResource("/loginBackground.png")));
		add(courseCatalogBackground);

	}
}
