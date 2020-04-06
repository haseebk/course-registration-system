package PreProjectExercise1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateTreeWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private BinSearchTree theTree;

	/**
	 * Launch the application.
	 */
	public void createTreeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTreeWindow frame = new CreateTreeWindow(theTree);
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
	public CreateTreeWindow(BinSearchTree theTree) {
		this.theTree = theTree;
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
				dispose();
				try {
					Scanner textFileIn = new Scanner(new FileInputStream(textField.getText()));
					while (textFileIn.hasNextLine()) {
						theTree.insert(textFileIn.next(), textFileIn.next(), textFileIn.next(), textFileIn.next());
					}
					textFileIn.close();

					try {
						theTree.print_tree(theTree.root);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
