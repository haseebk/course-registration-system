package Project.Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class creates the main frame that is used for the front end of the
 * program. It switches the frame to the program's Login panel.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class FrontEnd extends JFrame {
	/**
	 * Main frame that the application runs on.
	 */
	private JFrame MainFrame;

	/**
	 * Launch the application.
	 */
	public static void runClient() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontEnd frame = new FrontEnd();
					frame.MainFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontEnd() {
		MainFrame = new JFrame();

		MainFrame.setTitle("Course Registration System");
		MainFrame.setResizable(false);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setSize(1366, 768);

		Login loginPanel = new Login(MainFrame);

		MainFrame.setContentPane(loginPanel);
		MainFrame.revalidate();
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setLayout(null);
	}

}
