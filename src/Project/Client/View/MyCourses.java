package Project.Client.View;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.MatteBorder;

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
	private JTextField addCourseTextField;
	private JTextField courseIDTextField;

	/**
	 * Create the panel.
	 * @param theList 
	 */
	public MyCourses(JFrame frame, DefaultListModel<String> theList) {

//	public MyCourses(JFrame frame, StandardUserHome homePanel, DefaultListModel<String> theList) {
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
		
		courseIDTextField = new JTextField();
		courseIDTextField.setFont(new Font("Arial", Font.PLAIN, 17));
		courseIDTextField.setColumns(10);
		courseIDTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		courseIDTextField.setBounds(494, 694, 107, 24);
		add(courseIDTextField);
		
		JLabel courseNumLabel = new JLabel("Course ID:");
		courseNumLabel.setForeground(Color.GRAY);
		courseNumLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		courseNumLabel.setBounds(399, 689, 115, 39);
		add(courseNumLabel);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setForeground(Color.GRAY);
		courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		courseNameLabel.setBounds(399, 650, 115, 39);
		add(courseNameLabel);
		
		addCourseTextField = new JTextField();
		addCourseTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		addCourseTextField.setFont(new Font("Arial", Font.PLAIN, 17));
		addCourseTextField.setBounds(517, 659, 107, 24);
		add(addCourseTextField);
		addCourseTextField.setColumns(10);
		add(exitButton);

		JLabel backButton = new JLabel("");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StandardUserHome homePanel = new StandardUserHome(frame, null, null, theList);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(MyCourses.class.getResource("/backButton.png")));
		add(backButton);

		JList list = new JList();
		list.setBorder(new LineBorder(Color.RED, 5, true));
		list.setBounds(399, 148, 568, 480);
		add(list);

		JLabel addCourseButton = new JLabel("");
		addCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "\nThis method will be implemented soon.", " Success",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(MyCourses.class.getResource("/addCourseButton.png")));
		addCourseButton.setBounds(824, 668, 152, 50);
		add(addCourseButton);

		JLabel myCourseBackground = new JLabel("");
		myCourseBackground.setBounds(0, 0, 1366, 768);
		myCourseBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(myCourseBackground);

	}
}
