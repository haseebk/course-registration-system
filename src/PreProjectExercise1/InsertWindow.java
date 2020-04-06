package PreProjectExercise1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InsertWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField; // STUDENT ID
	private JTextField textField_1; // FACULTY
	private JTextField textField_2; // MAJOR
	private JTextField textField_3; // YEAR
	private BinSearchTree theTree;

	/**
	 * Launch the application.
	 */
	public void insertWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertWindow frame = new InsertWindow(theTree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertWindow(BinSearchTree theTree) {
		this.theTree = theTree;
		setTitle("Insert New Student Record");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student ID: ");
		lblNewLabel.setBounds(10, 36, 68, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Faculty: ");
		lblNewLabel_1.setBounds(10, 61, 68, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Major: ");
		lblNewLabel_2.setBounds(10, 86, 68, 14);
		contentPane.add(lblNewLabel_2);

		// STUDENT ID
		textField = new JTextField();
		textField.setBounds(88, 33, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// FACULTY
		textField_1 = new JTextField();
		textField_1.setBounds(88, 58, 216, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		// MAJOR
		textField_2 = new JTextField();
		textField_2.setBounds(88, 83, 216, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Year: ");
		lblNewLabel_3.setBounds(314, 86, 39, 14);
		contentPane.add(lblNewLabel_3);

		// YEAR
		textField_3 = new JTextField();
		textField_3.setBounds(363, 83, 127, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Fill in the following information:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 11, 177, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				theTree.insert(getId(), getFaculty(), getMajor(), getYear());
				dispose();
				try {
					theTree.print_tree(theTree.root);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				public void insert(String id, String faculty, String major, String year) {

			}
		});
		btnNewButton.setBounds(302, 114, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(401, 114, 89, 23);
		contentPane.add(btnNewButton_1);

	}

	public String getId() {
		return textField.getText();
	}

	public String getFaculty() {
		return textField_1.getText();
	}

	public String getMajor() {
		return textField_2.getText();
	}

	public String getYear() {
		return textField_3.getText();
	}

}
