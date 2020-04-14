package Project.Client.View;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * This class runs the User Home Page panel view. It allows the user to view
 * their own courses, view the course catalog, logout, or exit the program.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class StandardUserHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public StandardUserHome(JFrame frame) {
		setLayout(null);

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

		JLabel viewCatalogueCard = new JLabel("");
		viewCatalogueCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCatalogueCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseCatalog courseCatalogPanel = new CourseCatalog(frame);
				frame.setContentPane(courseCatalogPanel);
				frame.revalidate();
			}
		});
		viewCatalogueCard.setBounds(755, 188, 340, 398);
		viewCatalogueCard.setIcon(new ImageIcon(StandardUserHome.class.getResource("/viewCatalogueCard.png")));
		add(viewCatalogueCard);

		JLabel viewCoursesCard = new JLabel("");
		viewCoursesCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyCourses myCoursesPanel = new MyCourses(frame);
				frame.setContentPane(myCoursesPanel);
				frame.revalidate();
			}
		});
		viewCoursesCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCoursesCard.setBounds(244, 188, 340, 398);
		viewCoursesCard.setIcon(new ImageIcon(StandardUserHome.class.getResource("/viewCoursesCard.png")));
		add(viewCoursesCard);

		JLabel userHomeBackground = new JLabel("");
		userHomeBackground.setBounds(0, 0, 1366, 768);
		userHomeBackground.setIcon(new ImageIcon(StandardUserHome.class.getResource("/userHomeBackground.png")));
		add(userHomeBackground);
	}
}
