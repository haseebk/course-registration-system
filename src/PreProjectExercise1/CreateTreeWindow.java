package PreProjectExercise1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Haseeb Khan and Muhammad Tariq
 * @since April 5, 2020
 * @version 3.0
 * 
 *          This class is responsible for creating the window that pops up once
 *          a user presses the "Create Tree from File" button. It handles any
 *          required response as well.
 * 
 */
public class CreateTreeWindow extends JFrame {
	/**
	 * This is the main window of this class
	 */
	private JPanel contentPane;
	/**
	 * This is the text field that allows the user to enter the file name
	 */
	private JTextField textField;
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
	public void createTreeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTreeWindow frame = new CreateTreeWindow(theTree, theList);
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
	 * @param theList
	 */
	public CreateTreeWindow(BinSearchTree theTree, DefaultListModel<String> theList) {
		this.theTree = theTree;
		this.theList = theList;
		setTitle("Choose file to import data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("File name: ");
		lblNewLabel.setBounds(49, 62, 72, 14);
		contentPane.add(lblNewLabel);

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
				theList.clear();
				try {
					Scanner textFileIn = new Scanner(new FileInputStream(textField.getText()));
					while (textFileIn.hasNextLine()) {
						theTree.insert(textFileIn.next(), textFileIn.next(), textFileIn.next(), textFileIn.next());
						theList.clear();
						theTree.populateListWithTree(theTree.root, theList);
					}
					textFileIn.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "\nError! Unable to find the file: " + textField.getText(),
							" Warning", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_1.setBounds(302, 90, 89, 23);
		contentPane.add(btnNewButton_1);

		textField = new JTextField();
		textField.setBounds(131, 59, 359, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
