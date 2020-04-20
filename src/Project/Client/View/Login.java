package Project.Client.View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

import Project.Server.Model.Account;
import Project.Server.Model.Authenticator;
import Project.Server.Model.Backend;

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
	private JLabel registerButton;
	private JLabel versionLabel;

	/**
	 * Create the panel.
	 * @param frame frame that the panel is being placed onto 
	 * @param backend backend to obtain information and apply logic
	 * @param auth 
	 */
	public Login(JFrame frame, Backend backend, Authenticator auth) {
		setLayout(null);
		
		versionLabel = new JLabel("V3.0.0");
		versionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		versionLabel.setForeground(Color.GRAY);
		versionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		versionLabel.setBounds(692, 585, 118, 14);
		add(versionLabel);
		
		registerButton = new JLabel("CREATE ACCOUNT");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register registerPanel = new Register(frame, backend, auth);
				frame.setContentPane(registerPanel);
				frame.revalidate();
			}
		});
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setForeground(Color.GRAY);
		registerButton.setFont(new Font("Arial", Font.BOLD, 11));
		registerButton.setBounds(556, 585, 106, 14);
		add(registerButton);

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(608, 147, 150, 131);
		uniLogo.setIcon(new ImageIcon(Login.class.getResource("/uniLogoA.png")));
		add(uniLogo);
		
		// CREATE USERNAME TEXT FIELD
		usernameTextField = new JTextField();
		usernameTextField.setToolTipText("Enter your username.");
		usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		usernameTextField.setForeground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.WHITE);
		usernameTextField.setBounds(556, 370, 254, 28);
		usernameTextField.setOpaque(true);
		usernameTextField.setColumns(10);
		usernameTextField.setOpaque(true);
		add(usernameTextField);
		
		// CREATE PASSWORD TEXT FIELD
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your password.");
		passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(556, 443, 254, 28);
		add(passwordField);
		
		// CREATE USERNAME TEXT LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.GRAY);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setBounds(556, 354, 77, 14);
		add(usernameLabel);
		
		// CREATE PASSWORD TEXT LABEL
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.GRAY);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel.setBounds(556, 428, 77, 14);
		add(passwordLabel);
		
		// CREATE ERROR TEXT LABEL
		JLabel invalidLoginErrorLabel = new JLabel("<html>"
				+ "Entered login credentials do not match \r\na registered account in our system." + "</html>");
		invalidLoginErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidLoginErrorLabel.setForeground(Color.RED);
		invalidLoginErrorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		invalidLoginErrorLabel.setBounds(556, 301, 254, 45);
		invalidLoginErrorLabel.setVisible(false);
		add(invalidLoginErrorLabel);
		
		// CREATE SUBMIT LOGIN BUTTON
		submitLoginButton = new JLabel("");
		submitLoginButton.setToolTipText("Login");
		submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = usernameTextField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				Account acc = auth.loginAuth(user, pass);
				if (acc == null) {
					invalidLoginErrorLabel.setVisible(true);
				}
				else {
					invalidLoginErrorLabel.setVisible(false);
					StandardUserHome homePanel = new StandardUserHome(frame, backend, auth, acc);
					frame.setContentPane(homePanel);
				}
				frame.revalidate();
			}
		});
		submitLoginButton.setBounds(659, 494, 48, 50);
		submitLoginButton.setIcon(new ImageIcon(Login.class.getResource("/enterButton.png")));
		add(submitLoginButton);

		// CREATE BACKGROUND VIEW
		JLabel loginBackground = new JLabel("");
		loginBackground.setBounds(0, 0, 1366, 768);
		loginBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(loginBackground);

	}
}
