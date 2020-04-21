package Project.Client.View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import Project.Server.Controller.MySQLJDBC;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;

/**
 * This class is responsible for creating a window that provides the users with
 * options of course sections to choose from. It appropriately responds by
 * adding the desired section to student courses if possible.
 * 
 * @author Haseeb Khan and Muhammad Tariq
 * @since April 5, 2020
 * @version 3.0
 * 
 * 
 */
public class ChooseSection extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This is the main window of this class
	 */
	private JPanel contentPane;

	private int choices;
	private Course course;
	private String lastName;
	private String firstName;
	private Backend backend;
	private String success;


	/**
	 * Launch the application.
	 */
	public void insertWindow() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					ChooseSection frame = new ChooseSection(backend, choices, course, firstName, lastName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param lastName  last name of student
	 * @param firstName first name of student
	 * @param result    course
	 */
	public ChooseSection(Backend backend, int choices, Course result, String firstName, String lastName) {
		this.choices = choices;
		this.course = result;
		this.firstName = firstName;
		this.lastName = lastName;
		this.backend = backend;

		// CREATE SELECT COURSE SECTION TITLE
		setTitle("Select course section");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		// CREATE SECTION NUMBER LABELS
		JLabel section3Label = new JLabel("3");
		section3Label.setForeground(Color.WHITE);
		section3Label.setHorizontalAlignment(SwingConstants.CENTER);
		section3Label.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel section2Label = new JLabel("2");
		section2Label.setForeground(Color.WHITE);
		section2Label.setHorizontalAlignment(SwingConstants.CENTER);
		section2Label.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel section1Label = new JLabel("1");
		section1Label.setHorizontalAlignment(SwingConstants.CENTER);
		section1Label.setFont(new Font("Arial", Font.BOLD, 16));
		section1Label.setForeground(Color.WHITE);

		// CREATE SELECTION CARDS WITH MOUSEEVENT LISTENERS
		JLabel choiceA = new JLabel("");
		choiceA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceA.setIcon(new ImageIcon(ChooseSection.class.getResource("/sectionChoiceCard.png")));
		choiceA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MySQLJDBC reader = new MySQLJDBC();
				reader.initializeConnection();
				if (backend.getStudent(firstName, lastName).alreadyEnrolled(result.getCourseName(),
						result.getCourseNum()) == false) {
					reader.insertStudentCourseData(firstName, lastName, result.getCourseName(), result.getCourseNum(),
							1);
					setSuccess("Successfully added course!");

					System.out.println("Successfully added course!");
					try {
						reader.importStudentCourseData(backend);
					} catch (SQLException e1) {
						System.out.println("Already enrolled");
						e1.printStackTrace();
					}
				} else {
					System.out.println("Already enrolled");
				}
				reader.closeConnection();
				dispose();
			}
		});
		JLabel choiceB = new JLabel("");
		choiceB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceB.setIcon(new ImageIcon(ChooseSection.class.getResource("/sectionChoiceCard.png")));
		choiceB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MySQLJDBC reader = new MySQLJDBC();
				reader.initializeConnection();
				if (backend.getStudent(firstName, lastName).alreadyEnrolled(result.getCourseName(),
						result.getCourseNum()) == false) {
					reader.insertStudentCourseData(firstName, lastName, result.getCourseName(), result.getCourseNum(),
							2);
					setSuccess("Successfully added course!");
					System.out.println("Successfully added course!");

					try {
						reader.importStudentCourseData(backend);
					} catch (SQLException e1) {
						System.out.println("Already enrolled");
						e1.printStackTrace();
					}
				} else {
					System.out.println("Already enrolled");
				}
				reader.closeConnection();
				dispose();
			}
		});
		JLabel choiceC = new JLabel("");
		choiceC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceC.setIcon(new ImageIcon(ChooseSection.class.getResource("/sectionChoiceCard.png")));
		choiceC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MySQLJDBC reader = new MySQLJDBC();
				reader.initializeConnection();
				if (backend.getStudent(firstName, lastName).alreadyEnrolled(result.getCourseName(),
						result.getCourseNum()) == false) {
					reader.insertStudentCourseData(firstName, lastName, result.getCourseName(), result.getCourseNum(),
							3);
					setSuccess("Successfully added course!");

					System.out.println("Successfully added course!");

					try {
						reader.importStudentCourseData(backend);
					} catch (SQLException e1) {
						System.out.println("Already enrolled");
						e1.printStackTrace();
					}
				} else {
					System.out.println("Already enrolled");
				}
				reader.closeConnection();
				dispose();
			}
		});

		// CREATE CANCEL BUTTON
		JLabel cancelButton = new JLabel("");
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setIcon(new ImageIcon(ChooseSection.class.getResource("/cancelButton.png")));
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(153, 123, 78, 30);
		contentPane.add(cancelButton);

		// CREATE SECTION SEAT NUMBER LABELS
		JLabel section1SeatLabel = new JLabel(Integer.toString(result.getCourseOfferingAt(1).getSecCap()) + " Seats");
		section1SeatLabel.setForeground(Color.WHITE);
		section1SeatLabel.setFont(new Font("Arial", Font.BOLD, 13));
		section1SeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		section1SeatLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel section2SeatLabel = new JLabel(Integer.toString(result.getCourseOfferingAt(2).getSecCap()) + " Seats");
		section2SeatLabel.setForeground(Color.WHITE);
		section2SeatLabel.setFont(new Font("Arial", Font.BOLD, 13));
		section2SeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		section2SeatLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel section3SeatLabel = new JLabel("");
		section3SeatLabel.setForeground(Color.WHITE);
		section3SeatLabel.setFont(new Font("Arial", Font.BOLD, 13));
		section3SeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		section3SeatLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// LOGIC FOR CHOOSING HOW MANY CARDS TO DISPLAY
		if (choices == 1) {
			choiceA.setBounds(153, 30, 78, 82);
			contentPane.add(choiceA);

			section1SeatLabel.setBounds(153, 77, 78, 14);
			contentPane.add(section1SeatLabel);

			section1Label.setBounds(187, 50, 9, 14);
			contentPane.add(section1Label);

			revalidate();
		} else if (choices == 2) {
			section1Label.setBounds(99, 52, 9, 14);
			contentPane.add(section1Label);
			section2Label.setBounds(275, 50, 9, 14);
			contentPane.add(section2Label);

			section1SeatLabel.setBounds(65, 77, 78, 14);
			contentPane.add(section1SeatLabel);
			section2SeatLabel.setBounds(241, 77, 78, 14);
			contentPane.add(section2SeatLabel);

			choiceA.setBounds(65, 30, 78, 82);
			contentPane.add(choiceA);
			choiceB.setBounds(241, 30, 78, 82);
			contentPane.add(choiceB);
			revalidate();
		} else if (choices == 3) {
			section1Label.setBounds(74, 52, 9, 14);
			contentPane.add(section1Label);
			section2Label.setBounds(187, 50, 9, 14);
			contentPane.add(section2Label);
			section3Label.setBounds(300, 52, 9, 14);
			contentPane.add(section3Label);

			section1SeatLabel.setBounds(40, 77, 78, 14);
			contentPane.add(section1SeatLabel);
			section2SeatLabel.setBounds(153, 77, 78, 14);
			contentPane.add(section2SeatLabel);
			section3SeatLabel.setBounds(266, 77, 78, 14);
			section3SeatLabel.setText(Integer.toString(result.getCourseOfferingAt(3).getSecCap()) + " Seats");
			contentPane.add(section3SeatLabel);

			choiceA.setBounds(40, 30, 78, 82);
			contentPane.add(choiceA);
			choiceB.setBounds(153, 30, 78, 82);
			contentPane.add(choiceB);
			choiceC.setBounds(266, 30, 78, 82);
			contentPane.add(choiceC);

			revalidate();
		}
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}
