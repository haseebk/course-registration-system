package PreProjectExercise1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Haseeb Khan & Muhammad Tariq
 * @since April 5, 2020
 * @version 3.0
 * 
 *          This class is responsible for creating display panels for the
 *          Student Record management application. It is essentially the
 *          front-end of the application, utilizing several classes for
 *          modularity such as FindWindow, InsertWindow, and CreateTreeWindow
 *          classes.
 */
public class Ex1GUI extends JFrame {
	/**
	 * This is the main window of the application
	 */
	private JPanel contentPane;
	/**
	 * This member variable creates the button functionality that allows the user to
	 * insert a student's data into the system.
	 */
	private InsertWindow insertButton;
	/**
	 * This member variable creates the button functionality that allows the user to
	 * search for a student's data
	 */
	private FindWindow findButton;
	/**
	 * This member variable creates the button functionality that allows the user to
	 * create a tree of student records
	 */
	private CreateTreeWindow createButton;
	/**
	 * This member variable refreshes the main window to display the student records
	 */
	private BrowseDisplay browser;
	/**
	 * This member variable is the Binary Search Tree that will hold student records
	 */
	private BinSearchTree theTree;
	/**
	 * This member variable is the list that holds generic student records (in
	 * String form) which will be used to display the browser panel
	 */
	private DefaultListModel<String> theList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ex1GUI frame = new Ex1GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the main window frame.
	 */
	public Ex1GUI() {
		theTree = new BinSearchTree();
		theList = new DefaultListModel<String>();

		setTitle("Main Window");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Application to Maintain Student Records");
		lblNewLabel.setBounds(5, 25, 704, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertButton = new InsertWindow(theTree, theList);
				insertButton.insertWindow();
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(60, 390, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findButton = new FindWindow(theTree);
				findButton.findWindow();
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(200, 390, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Browse");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JList<String> viewList = new JList<String>(theList);
				browser = new BrowseDisplay(contentPane, viewList, theTree);
				revalidate();
				repaint();
			}
		});
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(340, 390, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Create Tree From File");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createButton = new CreateTreeWindow(theTree, theList);
				createButton.createTreeWindow();
			}
		});
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBounds(480, 390, 172, 23);
		contentPane.add(btnNewButton_3);

	}
}
