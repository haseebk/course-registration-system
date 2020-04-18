package Project.Client.View;

import javax.swing.JPanel;

import Project.Server.Model.Backend;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

/**
 * This class runs the User Home Page panel view. It allows the user to view
 * their own courses, view the course catalog, logout, or exit the program.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class StandardUserHome extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel viewCoursesCard;
	public JLabel viewCatalogCard;

	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 */
	public StandardUserHome(JFrame frame, Backend backend) {
		setLayout(null);

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(609, 29, 150, 131);
		uniLogo.setIcon(new ImageIcon(StandardUserHome.class.getResource("/uniLogoB.png")));
		add(uniLogo);

		// CREATE EXIT BUTTON
		JLabel exitButton = new JLabel("");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setBounds(1286, 30, 50, 50);
		exitButton.setIcon(new ImageIcon(StandardUserHome.class.getResource("/exitButtonOutline.png")));
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
		logoutButton.setIcon(new ImageIcon(StandardUserHome.class.getResource("/logoutButtonOutline.png")));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login loginPanel = new Login(frame, backend);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		add(logoutButton);

		// CREATE CATALOG CARD BUTTON
		viewCatalogCard = new JLabel("");
		viewCatalogCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCatalogCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseCatalog courseCatalogPanel = new CourseCatalog(frame, backend);
				frame.setContentPane(courseCatalogPanel);
				frame.revalidate();
			}
		});
		viewCatalogCard.setBounds(736, 188, 340, 398);
		viewCatalogCard.setIcon(new ImageIcon(StandardUserHome.class.getResource("/viewCatalogCard.png")));
		add(viewCatalogCard);

		// CREATE USER COURSES CARD BUTTON
		viewCoursesCard = new JLabel("");
		viewCoursesCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyCourses myCoursesPanel = new MyCourses(frame, backend);
				frame.setContentPane(myCoursesPanel);
				frame.revalidate();
			}
		});
		viewCoursesCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCoursesCard.setBounds(277, 188, 340, 398);
		viewCoursesCard.setIcon(new ImageIcon(StandardUserHome.class.getResource("/viewCoursesCard.png")));
		add(viewCoursesCard);

		// CREATE BACKGROUND VIEW
		JLabel userHomeBackground = new JLabel("");
		userHomeBackground.setBounds(0, 0, 1366, 768);
		userHomeBackground.setIcon(new ImageIcon(StandardUserHome.class.getResource("/userHomeBackground.png")));
		add(userHomeBackground);
	}
}
