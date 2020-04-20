package Project.Client.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Project.Server.Model.Account;
import Project.Server.Model.Authenticator;
import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

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
	private ChooseSection choiceWindow;
	private JTextField searchTextField;
	private Backend backend;
	private JList<String> list;


	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 * @param auth
	 * @param acc
	 */
	public CourseCatalog(JFrame frame, Backend backend, Authenticator auth, Account acc) {
		setLayout(null);
		this.backend = backend;
		this.list = createJList(backend);

		// CREATE HELLO USER LABEL
		JLabel welcomeUserLabel = new JLabel("");
		welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 31));
		welcomeUserLabel.setForeground(Color.BLACK);
		if (acc != null) {
			welcomeUserLabel.setText("Hello, " + backend.getStudent(acc.getStudentId()).getStudentFirstName() + "!");
		}
		welcomeUserLabel.setBounds(90, 29, 278, 51);
		add(welcomeUserLabel);

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
				if (selectedCourse != null) {
					String[] courseDetail = selectedCourse.split(" ");
					String firstName = backend.getStudent(acc.getStudentId()).getStudentFirstName();
					String lastName = backend.getStudent(acc.getStudentId()).getStudentLastName();
					String courseName = courseDetail[0].trim();
					int courseNum = Integer.parseInt(courseDetail[1]);
					Course result = backend.getCatalog().searchCat(courseName, courseNum);
					choiceWindow = new ChooseSection(backend, result.getNumberOfOfferings(), result, firstName,
							lastName);
					choiceWindow.insertWindow();
					System.out.println(
							result.getOfferingList().toString().replace("[", "").replace("]", "").replace(",", ""));

				} else {
					JOptionPane.showMessageDialog(null,
							"\nAn error occurred! Please make sure you have selected a course to add.", " Warning",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/addToCoursesButton.png")));
		addCourseButton.setBounds(995, 291, 147, 50);
		add(addCourseButton);

		// CREATE SCROLLABLE LIST OF COURSES IN CATALOG
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(400, 177, 568, 480);
		scrollPane.setVisible(true);
		add(createTextField());
		add(scrollPane);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));


		// CREATE MOUSELISTENER FOR CLICKING COURSE TO DISPLAY INFO
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedCourse = (String) list.getSelectedValue();
				if (selectedCourse != null) {
					String[] courseDetail = selectedCourse.split(" ");
					Course result = backend.getCatalog().searchCat(courseDetail[0].trim(),
							Integer.parseInt(courseDetail[1]));
					if (result == null) {
						JOptionPane.showMessageDialog(null, "\nAn error occurred!", " Warning",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						courseNameLabel.setText("Course Name: " + result.getCourseName());
						courseIDLabel.setText("Course ID: " + result.getCourseNum());
						courseSectionNumberLabel.setText("Available Sections: " + result.getNumberOfOfferings());
						frame.revalidate();
					}
				}
				else {
					courseNameLabel.setText("Course Name: ");
					courseIDLabel.setText("Course ID: ");
					courseSectionNumberLabel.setText("Available Sections: ");
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
				StandardUserHome homePanel = new StandardUserHome(frame, backend, auth, acc);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/backButtonOutline.png")));
		add(backButton);

		// CREATE BACKGROUND VIEW
		JLabel courseCatalogBackground = new JLabel("");
		courseCatalogBackground.setBounds(0, 0, 1366, 768);
		courseCatalogBackground.setIcon(new ImageIcon(CourseCatalog.class.getResource("/loginBackground.png")));
		add(courseCatalogBackground);

	}

	public JTextField createTextField() {
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
		for (Course course : backend.getCatalog().getCourseList()) {
			String name = course.getCourseName().trim();
			String num = Integer.toString(course.getCourseNum());
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

	public JList<String> createJList(Backend backend) {
		JList<String> list = new JList<String>(createDefaultListModel(backend));
		list.setVisible(true);
		return list;
	}

	public DefaultListModel<String> createDefaultListModel(Backend backend) {
		DefaultListModel<String> theList = new DefaultListModel<>();

		for (Course course : backend.getCatalog().getCourseList()) {
//			String s = course.toString();
			String name = course.getCourseName().trim();
			String num = Integer.toString(course.getCourseNum());
			String s = name + " " + num;
			theList.addElement(s);
		}
		return theList;
	}

}
