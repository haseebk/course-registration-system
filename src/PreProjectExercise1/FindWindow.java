package PreProjectExercise1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Haseeb Khan and Muhammad Tariq
 * @since April 5, 2020
 * @version 3.0
 * 
 *          This class is responsible for creating the window that pops up once
 *          a user presses the "Find" button. It handles any required response
 *          as well.
 * 
 */
public class FindWindow extends JFrame {
	/**
	 * This is the main window of this class
	 */
	private JPanel contentPane;
	/**
	 * This is the text field that allows the user to enter a student ID
	 */
	private JTextField textField;
	/**
	 * This member variable is the Binary Search Tree that will hold student records
	 */
	private BinSearchTree theTree;

	/**
	 * Launch the application.
	 */
	public void findWindow() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindWindow frame = new FindWindow(theTree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param theTree
	 */
	public FindWindow(BinSearchTree theTree) {
		this.theTree = theTree;

		setTitle("Find a Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student ID: ");
		lblNewLabel.setBounds(49, 62, 72, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(132, 59, 358, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(401, 90, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Node result = theTree.find(theTree.root, textField.getText());
				if (result == null) {
					JOptionPane.showMessageDialog(null, "\nError! No records with that Student ID were found!",
							" Warning", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Student ID: " + result.data.id + "\nFaculty: " + result.data.faculty + "\nMajor: "
									+ result.data.major + "\nYear: " + result.data.year,
							" Result", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(302, 90, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
