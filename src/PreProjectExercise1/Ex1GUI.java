package PreProjectExercise1;
import javax.swing.*;
import java.awt.EventQueue;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.SwingConstants;
import java.awt.Font;
//import javax.swing.JButton;
//import javax.swing.JList;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ex1GUI extends JFrame {

	private JPanel contentPane;
	private InsertWindow insertButton;
	private FindWindow findButton;
	private CreateTreeWindow createButton;
	private BinSearchTree theTree;

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
	 * Create the frame.
	 */
	public Ex1GUI() {
		theTree = new BinSearchTree();
		
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
		lblNewLabel.setBounds(5, 5, 704, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertButton = new InsertWindow(theTree);
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
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(340, 390, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Create Tree From File");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createButton = new CreateTreeWindow(theTree);
				createButton.createTreeWindow();
			}
		});
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBounds(480, 390, 172, 23);
		contentPane.add(btnNewButton_3);
		
		

	}
}
