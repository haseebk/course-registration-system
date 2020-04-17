package Project.Client.View;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import Project.Server.Model.Backend;

/**
 * This class creates the main frame that is used for the front end of the
 * program. It switches the frame to the program's Login panel.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class FrontEnd extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Main frame that the application runs on.
	 */
	private JFrame MainFrame;
	private CourseCatalog courseCatalogPanel;
	private MyCourses myCoursesPanel;
	private StandardUserHome homePanel;
	private Login loginPanel;
	private DefaultListModel<String> theList;

	/**
	 * Create the frame.
	 */
	public FrontEnd(DefaultListModel<String> theList, Backend backend) {
		theList.clear();
		theList.addElement(backend.viewCourseCatalogue());
		setMainFrame(new JFrame());
		myCoursesPanel = new MyCourses(getMainFrame(), theList);
		courseCatalogPanel = new CourseCatalog(getMainFrame(), theList);
		homePanel = new StandardUserHome(getMainFrame(), myCoursesPanel, courseCatalogPanel, theList);
		loginPanel = new Login(getMainFrame(), homePanel, theList);

		getMainFrame().setTitle("Course Registration System");
		getMainFrame().setResizable(false);
		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainFrame().setSize(1366, 768);
		getMainFrame().setContentPane(loginPanel);
		getMainFrame().revalidate();
		getMainFrame().setLocationRelativeTo(null);
		getMainFrame().setLayout(null);
	}

	public void addViewMyCoursesMouseClicked(MouseListener e) {
		homePanel.viewCoursesCard.addMouseListener(e);
	}

	public void addViewCourseCatalogMouseClicked(MouseListener e) {
		homePanel.viewCatalogCard.addMouseListener(e);
	}

	public void addSubmitLoginMouseClicked(MouseListener e) {
		loginPanel.submitLoginButton.addMouseListener(e);
	}

	public JFrame getMainFrame() {
		return MainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		MainFrame = mainFrame;
	}
	public void displayGUI() {
		getMainFrame().setVisible(true);
	}
}
