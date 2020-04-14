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

public class StandardUserHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public StandardUserHome(JFrame frame) {
		setLayout(null);
		
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
		
		JLabel viewCatalogLabel = new JLabel("View Course Catalog");
		viewCatalogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		viewCatalogLabel.setFont(new Font("Arial", Font.BOLD, 22));
		viewCatalogLabel.setForeground(Color.WHITE);
		viewCatalogLabel.setBounds(816, 450, 229, 23);
		add(viewCatalogLabel);
		
		JLabel viewCoursesLabel = new JLabel("View My Courses");
		viewCoursesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		viewCoursesLabel.setFont(new Font("Arial", Font.BOLD, 22));
		viewCoursesLabel.setForeground(Color.	WHITE);
		viewCoursesLabel.setBounds(321, 450, 194, 23);
		add(viewCoursesLabel);
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
