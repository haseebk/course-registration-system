package Project.Client.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;

public class CourseCatalog extends JPanel {

	/**
	 * Create the panel.
	 */
	public CourseCatalog(JFrame frame) {
		setLayout(null);
		
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
