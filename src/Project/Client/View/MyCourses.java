package Project.Client.View;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Project.Server.Controller.MySQLJDBC;
import Project.Server.Model.Account;
import Project.Server.Model.Authenticator;
import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import Project.Server.Model.Registration;

import java.awt.Font;
import javax.swing.SwingConstants;

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
	private JTextField searchTextField;
	private JList<String> list;
	private Backend backend;
	private Account acc;

	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 * @param auth    authorizer
	 * @param acc     student account
	 */
	public MyCourses(JFrame frame, Backend backend, Authenticator auth, Account acc) {
		setLayout(null);
		this.backend = backend;
		this.acc = acc;
		this.list = createJList(backend, acc);

		// CREATE HELLO USER LABEL
		JLabel welcomeUserLabel = new JLabel("");
		welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 31));
		welcomeUserLabel.setForeground(Color.BLACK);
		if (acc != null) {
			welcomeUserLabel.setText("Hello, " + backend.getStudent(acc.getStudentId()).getStudentFirstName() + "!");
		}

		// CREATE REMOVE COURSE BUTTON
		JLabel removeCourseButton = new JLabel("");
		removeCourseButton.setHorizontalAlignment(SwingConstants.CENTER);
		removeCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeCourseButton.setIcon(new ImageIcon(MyCourses.class.getResource("/removeCourseButton.png")));
		removeCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedCourse = (String) list.getSelectedValue();
				if (selectedCourse != null) {
					String[] courseDetail = selectedCourse.split(" ");
					String firstName = backend.getStudent(acc.getStudentId()).getStudentFirstName();
					String lastName = backend.getStudent(acc.getStudentId()).getStudentLastName();
					String courseName = courseDetail[0].trim();
					int courseNum = Integer.parseInt(courseDetail[1]);
					Course result = backend.getCatalog().searchCat(courseName, courseNum);
					MySQLJDBC reader = new MySQLJDBC();
					reader.initializeConnection();
					reader.removeStudentCourseData(firstName, lastName, result.getCourseName(), result.getCourseNum());
					backend.getStudent(firstName,lastName).getStudentRegList().clear();
					try {
						reader.importStudentCourseData(backend);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					reader.closeConnection();
				} else {
					JOptionPane.showMessageDialog(null,
							"\nAn error occurred! Please make sure you have selected a course to remove.", " Warning",
							JOptionPane.PLAIN_MESSAGE);
				}
				MyCourses myCoursesPanel = new MyCourses(frame, backend, auth, acc);
				frame.setContentPane(myCoursesPanel);
				frame.revalidate();
			}
		});
		removeCourseButton.setBounds(998, 318, 96, 36);
		add(removeCourseButton);
		welcomeUserLabel.setBounds(90, 29, 278, 51);
		add(welcomeUserLabel);

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(609, 29, 150, 131);
		uniLogo.setIcon(new ImageIcon(MyCourses.class.getResource("/uniLogoB.png")));
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
		courseInfoCard.setBounds(996, 177, 280, 136);
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
				Login loginPanel = new Login(frame, backend, auth);
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
				CourseCatalog courseCatalogPanel = new CourseCatalog(frame, backend, auth, acc);
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
				StandardUserHome homePanel = new StandardUserHome(frame, backend, auth, acc);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(MyCourses.class.getResource("/backButtonOutline.png")));
		add(backButton);

		// CREATE SCROLLABLE LIST OF COURSES IN CATALOG
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(400, 177, 568, 480);
		scrollPane.setVisible(true);
		add(createTextField(acc));
		add(scrollPane);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));

		// CREATE MOUSELISTENER FOR CLICKING COURSE TO DISPLAY INFO
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedCourse = (String) list.getSelectedValue();
				if (selectedCourse != null) {
					String[] courseDetail = selectedCourse.split(" ");
					String firstName = backend.getStudent(acc.getStudentId()).getStudentFirstName();
					String lastName = backend.getStudent(acc.getStudentId()).getStudentLastName();
					int secNum = backend.getMySecNum(firstName, lastName, courseDetail[0], courseDetail[1]);
					int secCap = backend.getMySecCap(firstName, lastName, courseDetail[0], courseDetail[1]);
					Course result = backend.getCatalog().searchCat(courseDetail[0].trim(),
							Integer.parseInt(courseDetail[1]));
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
				} else {
					courseNameLabel.setText("Course Name: ");
					courseIDLabel.setText("Course ID: ");
					courseSectionNumberLabel.setText("Section Number: ");
					courseSectionCapacityLabel.setText("Section Capacity: ");
					frame.revalidate();
				}
			}
		});

		// CREATE BACKGROUND VIEW
		JLabel myCourseBackground = new JLabel("");
		myCourseBackground.setBounds(0, 0, 1366, 768);
		myCourseBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));

		add(myCourseBackground);

	}

	public JTextField createTextField(Account acc) {
		searchTextField = new JTextField();
		searchTextField.setBorder(new MatteBorder(0, 5, 0, 0, (Color) Color.YELLOW));
		searchTextField.setBackground(new Color(240, 255, 240));
		searchTextField.setBounds(400, 158, 568, 20);
		searchTextField.setColumns(10);
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void filter() {
				String filter = searchTextField.getText();
				filterTheList((DefaultListModel<String>) list.getModel(), filter);
			}
		});
		return searchTextField;
	}

	public void filterTheList(DefaultListModel<String> theList, String filter) {
		if (acc != null) {
			for (Registration registration : backend.getStudent(acc.getStudentId()).getStudentRegList()) {
				String name = registration.getTheOffering().getTheCourse().getCourseName().trim();
				String num = Integer.toString(registration.getTheOffering().getTheCourse().getCourseNum());
				String s = name + " " + num;

				if (!s.contains(filter.toUpperCase())) {
					if (theList.contains(s)) {
						theList.removeElement(s);
					}
				} else {
					if (!theList.contains(s)) {
						theList.addElement(s);
					}
				}
			}
		}
	}

	public JList<String> createJList(Backend backend, Account acc) {
		JList<String> list = new JList<String>(createDefaultListModel(backend, acc));
		list.setVisible(true);
		return list;
	}

	public DefaultListModel<String> createDefaultListModel(Backend backend, Account acc) {
		DefaultListModel<String> theList = new DefaultListModel<>();
		if (acc != null) {
			for (Registration registration : backend.getStudent(acc.getStudentId()).getStudentRegList()) {
				String name = registration.getTheOffering().getTheCourse().getCourseName().trim();
				String num = Integer.toString(registration.getTheOffering().getTheCourse().getCourseNum());
				String s = name + " " + num;
				theList.addElement(s);
			}
		}

		return theList;
	}
}
