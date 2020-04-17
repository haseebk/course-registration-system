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
import Project.Server.Model.Backend;
import Project.Server.Model.Course;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
/**
 * This class runs the Course Catalog panel view. It displays the courses that
 * are in the catalog with the option for user to add courses,
 * return to homepage, logout, or exit the application.
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

	/**
	 * Create the panel.
	 * @param frame frame that the panel is being placed onto 
	 * @param backend backend to obtain information and apply logic
	 */
	public CourseCatalog(JFrame frame, Backend backend) {
		setLayout(null);
		
		// CREATE COURSE-INFO CARD
		JLabel courseInfoCard = new JLabel("");
		courseInfoCard.setBounds(981, 489, 280, 151);
		courseInfoCard.setIcon(new ImageIcon(CourseCatalog.class.getResource("/smallCard.png")));
		add(courseInfoCard);
		
		// CREATE EXIT BUTTON
		JLabel exitButton = new JLabel("");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setBounds(1286, 30, 50, 50);
		exitButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/exitButton.png")));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		add(exitButton);
		
		// CREATE ADD COURSE BUTTON
		JLabel addCourseButton = new JLabel("");
		addCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "\nThis method will be implemented soon.", " Success",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/addCourseButton.png")));
		addCourseButton.setBounds(824, 668, 152, 50);
		add(addCourseButton);
		
		// CREATE SCROLLABLE LIST OF COURSES IN CATALOG
		DefaultListModel<String> theList = new DefaultListModel<String>();
		for(Course course : backend.getCatalog().getCourseList()) {
			theList.addElement(course.toString());
		}
		JList<String> list = new JList<String>(theList);
		list.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(255, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		scrollPane.setBounds(399, 148, 568, 480);
		scrollPane.setVisible(true);

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
		backButton.setIcon(new ImageIcon(CourseCatalog.class.getResource("/backButton.png")));
		add(backButton);
		
		// CREATE BACKGROUND VIEW
		JLabel courseCatalogBackground = new JLabel("");
		courseCatalogBackground.setBounds(0, 0, 1366, 768);
		courseCatalogBackground.setIcon(new ImageIcon(CourseCatalog.class.getResource("/userHomeBackground.png")));
		add(courseCatalogBackground);

	}
}
