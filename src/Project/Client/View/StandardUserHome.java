package Project.Client.View;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	 * @param courseCatalogPanel 
	 * @param myCoursesPanel 
	 * @param theList 
	 * @param theList 
	 */
	public StandardUserHome(JFrame frame, MyCourses myCoursesPanel, CourseCatalog courseCatalogPanel, DefaultListModel<String> theList) {
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
		
		viewCatalogCard = new JLabel("");
		viewCatalogCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCatalogCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(courseCatalogPanel);
				frame.revalidate();
			}
		});
		viewCatalogCard.setBounds(755, 188, 340, 398);
		viewCatalogCard.setIcon(new ImageIcon(StandardUserHome.class.getResource("/viewCatalogCard.png")));
		add(viewCatalogCard);

		viewCoursesCard = new JLabel("");
		viewCoursesCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
