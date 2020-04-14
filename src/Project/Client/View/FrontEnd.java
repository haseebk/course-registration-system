package Project.Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrontEnd extends JFrame {

	private JFrame MainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
