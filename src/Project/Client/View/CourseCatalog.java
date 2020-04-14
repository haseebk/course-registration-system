package Project.Client.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;

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
	 * Create the panel.
	 */
	public CourseCatalog(JFrame frame) {
		setLayout(null);

		JLabel exitButton = new JLabel("");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setBounds(1286, 30, 50, 50);
		exitButton.setIcon(new ImageIcon(MyCourses.class.getResource("/exitButtonOutline.png")));
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

		JLabel courseCatalogBackground = new JLabel("");
		courseCatalogBackground.setBounds(0, 0, 1366, 768);
		courseCatalogBackground.setIcon(new ImageIcon(StandardUserHome.class.getResource("/userHomeBackground.png")));
		add(courseCatalogBackground);

	}

}
