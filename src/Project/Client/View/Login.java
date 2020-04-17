package Project.Client.View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;

/**
 * This class runs the Login panel view. It creates username and password fields
 * for user input, which is then verified with database to confirm login.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Username text field for user login input
	 */
	private JTextField usernameTextField;
	/**
	 * Password text field for user login input
	 */
	private JPasswordField passwordField;
	/**
	 * Display text label "Password"
	 */
	private JLabel passwordLabel;
	/**
	 * Display text label "Username"
	 */
	private JLabel usernameLabel;
	JLabel submitLoginButton;

	/**
	 * Create the panel.
	 * 
	 * @param homePanel
	 */
	public Login(JFrame frame, StandardUserHome homePanel, DefaultListModel<String> theList) {
		setLayout(null);
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		usernameTextField.setForeground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.WHITE);
		usernameTextField.setBounds(556, 370, 254, 28);
		usernameTextField.setOpaque(true);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		usernameTextField.setOpaque(true);

		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(556, 443, 254, 28);
		add(passwordField);

		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.GRAY);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setBounds(556, 354, 77, 14);
		add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.GRAY);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel.setBounds(556, 428, 77, 14);
		add(passwordLabel);

		JLabel invalidLoginErrorLabel = new JLabel("<html>"
				+ "Entered login credentials do not match \r\na registered account in our system." + "</html>");
		invalidLoginErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidLoginErrorLabel.setForeground(Color.RED);
		invalidLoginErrorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		invalidLoginErrorLabel.setBounds(556, 301, 254, 45);
		invalidLoginErrorLabel.setVisible(false);
		add(invalidLoginErrorLabel);

		submitLoginButton = new JLabel("");
		submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usernameTextField.getText().compareTo("test") == 0
						&& String.valueOf(passwordField.getPassword()).compareTo("test") == 0) {
					invalidLoginErrorLabel.setVisible(false);
//					StandardUserHome stdUserHomePanel = new StandardUserHome(frame);
					frame.setContentPane(homePanel);
				} else {

					invalidLoginErrorLabel.setVisible(true);
				}
				frame.revalidate();
			}
		});
		submitLoginButton.setBounds(659, 496, 48, 50);
		submitLoginButton.setIcon(new ImageIcon(Login.class.getResource("/enterButton.png")));
		add(submitLoginButton);

		JLabel loginBackground = new JLabel("");
		loginBackground.setBounds(0, 0, 1366, 768);
		loginBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(loginBackground);

	}
}
