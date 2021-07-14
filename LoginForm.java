import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginForm {

	private JFrame frame;
	private JTextField txtUsername;
	private boolean changeUser = true;
	private JPasswordField pwdPassword;
	private boolean changePass = true;
	private JLabel label;
	private char echoChar;
	//private final JTextField textField_1 = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0, 120));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 823, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle("Login for Admin");
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 815, 461);
		panel.setBackground(new Color(0, 0, 0, 120));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("Username:");
		label.setBounds(254, 136, 99, 21);
		panel.add(label);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(383, 133, 152, 28);
		panel.add(txtUsername);
		txtUsername.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(254, 206, 99, 21);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(383, 203, 152, 28);
		panel.add(pwdPassword);
		pwdPassword.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		pwdPassword.setForeground(Color.BLACK);
		echoChar = pwdPassword.getEchoChar();
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(383, 267, 152, 28);
		panel.add(btnNewButton);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		
		JButton btnNewButton_1 = new JButton("Show");
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnNewButton_1.getText().equals("Show")) {
					btnNewButton_1.setText("Hide");
					pwdPassword.setEchoChar((char)0);
				}
				else {
					btnNewButton_1.setText("Show");
					pwdPassword.setEchoChar(echoChar);
				}
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(545, 206, 64, 24);
		panel.add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(pwdPassword.getPassword());
				pwdPassword.setText("");
				
				if(username.equalsIgnoreCase("owner") && password.equalsIgnoreCase("adda")){
					JOptionPane.showMessageDialog(frame, "Login successful");
					frame.setVisible(false);
					frame.dispose();
					new EnquirySection().main(null);
					}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Tanishq\\Downloads\\AxonizeBlog_SinglePost_815x600_FacilityintoSmartBuilding.jpg"));
		lblNewLabel_1.setBounds(0, 0, 815, 461);
		frame.getContentPane().add(lblNewLabel_1);
		//textField_1.setBounds(180, 50, 152, 28);
		//frame.getContentPane().add(textField_1);
		//textField_1.setColumns(10);
	}
}
