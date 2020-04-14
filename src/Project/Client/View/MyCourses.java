package Project.Client.View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	 * Create the panel.
	 */
	public MyCourses(JFrame frame) {
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

		JLabel backButton = new JLabel("");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StandardUserHome stdUserHomePanel = new StandardUserHome(frame);
				frame.setContentPane(stdUserHomePanel);
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

			}
		});
		addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addCourseButton.setIcon(new ImageIcon(MyCourses.class.getResource("/addCourseButton.png")));
		addCourseButton.setBounds(823, 639, 152, 50);
		add(addCourseButton);

		JLabel myCourseBackground = new JLabel("");
		myCourseBackground.setBounds(0, 0, 1366, 768);
		myCourseBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(myCourseBackground);

	}
}
