package Project.Client.View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

import Project.Server.Controller.MySQLJDBC;
import Project.Server.Model.Authenticator;
import Project.Server.Model.Backend;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Random;
import java.awt.Cursor;
import javax.swing.SwingConstants;

/**
 * This class runs the Register panel view. It creates fullname, username and
 * password fields for user input, which is then verified and added to database.
 * 
 * @author Haseeb Khan, Muhammad Tariq
 *
 */
public class Register extends JPanel {
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
	private JLabel registerButton;
	private JLabel versionLabel;
	private JPasswordField passwordField2;
	private JLabel passwordLabel2;
	private JLabel fullNameLabel;
	private JTextField fullNameTextField;
	private JLabel backButton;

	/**
	 * Create the panel.
	 * 
	 * @param frame   frame that the panel is being placed onto
	 * @param backend backend to obtain information and apply logic
	 * @param auth    authorizer
	 */
	public Register(JFrame frame, Backend backend, Authenticator auth) {
		setLayout(null);

		versionLabel = new JLabel("V3.0.0");
		versionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		versionLabel.setForeground(Color.GRAY);
		versionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		versionLabel.setBounds(689, 704, 118, 14);
		add(versionLabel);

		// CREATE BACK BUTTON
		backButton = new JLabel("");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login loginPanel = new Login(frame, backend, auth);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 50, 50);
		backButton.setIcon(new ImageIcon(MyCourses.class.getResource("/backButtonOutline.png")));
		add(backButton);

		// CREATE UNI LOGO VIEW
		JLabel uniLogo = new JLabel("");
		uniLogo.setBounds(608, 147, 150, 131);
		uniLogo.setIcon(new ImageIcon(Login.class.getResource("/uniLogoA.png")));
		add(uniLogo);

		// CREATE FULL NAME TEXT FIELD
		fullNameTextField = new JTextField();
		fullNameTextField.setToolTipText("Enter your full name.");
		fullNameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		fullNameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		fullNameTextField.setForeground(Color.DARK_GRAY);
		fullNameTextField.setBackground(Color.WHITE);
		fullNameTextField.setBounds(556, 370, 254, 28);
		fullNameTextField.setOpaque(true);
		fullNameTextField.setColumns(10);
		fullNameTextField.setOpaque(true);
		add(fullNameTextField);

		// CREATE USERNAME TEXT FIELD
		usernameTextField = new JTextField();
		usernameTextField.setToolTipText("Enter your username.");
		usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		usernameTextField.setForeground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.WHITE);
		usernameTextField.setBounds(556, 444, 254, 28);
		usernameTextField.setOpaque(true);
		usernameTextField.setColumns(10);
		usernameTextField.setOpaque(true);
		add(usernameTextField);

		// CREATE PASSWORD TEXT FIELD
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your password.");
		passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(556, 592, 254, 28);
		add(passwordField);

		// CREATE CONFIRM PASSWORD TEXT FIELD
		passwordField2 = new JPasswordField();
		passwordField2.setToolTipText("Re-enter your password.");
		passwordField2.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField2.setForeground(Color.BLACK);
		passwordField2.setBounds(556, 518, 254, 28);
		add(passwordField2);

		// CREATE USERNAME TEXT LABEL
		fullNameLabel = new JLabel("Full name");
		fullNameLabel.setForeground(Color.GRAY);
		fullNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		fullNameLabel.setBounds(556, 354, 77, 14);
		add(fullNameLabel);

		// CREATE USERNAME TEXT LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.GRAY);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setBounds(556, 428, 77, 14);
		add(usernameLabel);

		// CREATE PASSWORD TEXT LABEL
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.GRAY);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel.setBounds(556, 502, 77, 14);
		add(passwordLabel);

		// CREATE PASSWORD TEXT LABEL
		passwordLabel2 = new JLabel("Confirm Password");
		passwordLabel2.setForeground(Color.GRAY);
		passwordLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel2.setBounds(556, 576, 134, 14);
		add(passwordLabel2);

		// CREATE USERNAME ERROR TEXT LABEL
		JLabel invalidUsernameErrorLabel = new JLabel("<html>"
				+ "The username you have entered is either too short or too long, or is already taken." + "</html>");
		invalidUsernameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidUsernameErrorLabel.setForeground(Color.RED);
		invalidUsernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		invalidUsernameErrorLabel.setBounds(556, 301, 254, 45);
		invalidUsernameErrorLabel.setVisible(false);
		add(invalidUsernameErrorLabel);

		// CREATE PASSWORD ERROR TEXT LABEL
		JLabel invalidPasswordErrorLabel = new JLabel(
				"<html>" + "The passwords you have entered do not match." + "</html>");
		invalidPasswordErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidPasswordErrorLabel.setForeground(Color.RED);
		invalidPasswordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		invalidPasswordErrorLabel.setBounds(556, 301, 254, 45);
		invalidPasswordErrorLabel.setVisible(false);
		add(invalidPasswordErrorLabel);

		// CREATE NAME ERROR TEXT LABEL
		JLabel invalidNameErrorLabel = new JLabel(
				"<html>" + "Please make sure you entered your first and last name." + "</html>");
		invalidNameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidNameErrorLabel.setForeground(Color.RED);
		invalidNameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		invalidNameErrorLabel.setBounds(556, 301, 254, 45);
		invalidNameErrorLabel.setVisible(false);
		add(invalidNameErrorLabel);

		// CREATE SUBMIT REGISTER BUTTON
		registerButton = new JLabel("");
		registerButton.setToolTipText("Login");
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random random = new Random();
				int randomId = random.nextInt(30000);
				String fullname = fullNameTextField.getText();
				String[] name = fullname.split(" ");
				String user = usernameTextField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String confirmPass = String.valueOf(passwordField2.getPassword());
				Boolean validName = true;
				Boolean samePasswords = true;
				Boolean uniqueUser = true;

				if (pass.equals(confirmPass) == false) {
					invalidPasswordErrorLabel.setVisible(true);
					samePasswords = false;
					frame.revalidate();
				}
				if (name.length != 2) {
					invalidNameErrorLabel.setVisible(true);
					validName = false;
					frame.revalidate();
				}

				String regResult = auth.registerAuth(user, pass, samePasswords);
				if (regResult.equals("user")) {
					invalidUsernameErrorLabel.setVisible(true);
					frame.revalidate();
				}
				if (validName == true) {
					invalidNameErrorLabel.setVisible(false);
				}
				if (samePasswords == true) {
					invalidPasswordErrorLabel.setVisible(false);
				}

				if (validName == true && samePasswords == true && uniqueUser == true) {
					invalidNameErrorLabel.setVisible(false);
					invalidPasswordErrorLabel.setVisible(false);
					invalidUsernameErrorLabel.setVisible(false);

					MySQLJDBC reader = new MySQLJDBC();
					reader.initializeConnection();
					reader.insertStudentUser(randomId, name[0].trim(), name[1].trim(), user, pass);
					
					try {
						reader.importStudentData(backend);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					reader.closeConnection();
					Login loginPanel = new Login(frame, backend, auth);
					frame.setContentPane(loginPanel);

					frame.revalidate();
				}
			}
		});
		registerButton.setBounds(658, 643, 48, 50);
		registerButton.setIcon(new ImageIcon(Login.class.getResource("/enterButton.png")));
		add(registerButton);

		// CREATE BACKGROUND VIEW
		JLabel loginBackground = new JLabel("");
		loginBackground.setBounds(0, 0, 1366, 768);
		loginBackground.setIcon(new ImageIcon(Login.class.getResource("/loginBackground.png")));
		add(loginBackground);

	}
}
