

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class AgentGUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel loginPanel;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JPanel userPanel;
	private JPanel activityPanel;
	private JPanel changePasswordPanel;
	private JPanel updateSalesPanel; 
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	private JPasswordField passwordField_4;
	private char echoChar;
	private String username;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private SQLConnect sqlconnect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentGUI window = new AgentGUI();
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
	public AgentGUI() {
		Thread thread = new Thread() {
			public void run() {
				sqlconnect = new SQLConnect();
			}
		};
		thread.start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Real Estate Adda");
				
		userPanel = new JPanel();
		userPanel.setForeground(Color.WHITE);
		userPanel.setBorder(null);
		userPanel.setBackground(Color.BLACK);
		userPanel.setBounds(428, 0, 456, 461);
		frame.getContentPane().add(userPanel);
		userPanel.setLayout(null);
		
		JLabel userlabel_1 = new JLabel("Username");
		userlabel_1.setForeground(SystemColor.activeCaption);
		userlabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		userlabel_1.setBounds(92, 86, 84, 28);
		userPanel.add(userlabel_1);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.BLACK);
		textField.setBounds(92, 118, 290, 20);
		userPanel.add(textField);
		
		JSeparator userseparator_2 = new JSeparator();
		userseparator_2.setBounds(92, 149, 290, 2);
		userPanel.add(userseparator_2);
		
		JSeparator userseparator_3 = new JSeparator();
		userseparator_3.setBounds(92, 236, 290, 2);
		userPanel.add(userseparator_3);
		
		JLabel userlabel_2 = new JLabel("Password");
		userlabel_2.setForeground(SystemColor.activeCaption);
		userlabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		userlabel_2.setBounds(92, 173, 84, 28);
		userPanel.add(userlabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(null);
		passwordField.setBackground(Color.BLACK);
		passwordField.setBounds(92, 205, 290, 20);
		userPanel.add(passwordField);
		echoChar = passwordField.getEchoChar();
		
		JButton userbtnLogin = new JButton("Sign up");
		userbtnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String username = textField.getText();
					if(username.isEmpty()) throw new Exception();
					String password = new String(passwordField.getPassword());
					if(password.isEmpty()) throw new Exception();
					
					passwordField.setText("");
					
					if(sqlconnect.verify(username)) {
						sqlconnect.updateAgent(username, password);
						JOptionPane.showMessageDialog(frame, "Successfully signed up! Login and join the team.");
						Thread thread = new Thread() {
							public void run() {
								loginPanel.setVisible(true);
								for(int i=456; i>=0; i--) {
									try {
										Thread.sleep(1);
										userPanel.setBounds(428, 0, i, 461);
										loginPanel.setBounds(428+i, 0, 456-i, 461);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								userPanel.setVisible(false);
							}
						};
						thread.start();						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Invalid username. Consult the database administrator");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Invalid username or password.");
				}
			}
			
		});
		userbtnLogin.setBorder(null);
		userbtnLogin.setForeground(Color.WHITE);
		userbtnLogin.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		userbtnLogin.setBackground(SystemColor.activeCaption);
		userbtnLogin.setBounds(92, 309, 290, 39);
		userPanel.add(userbtnLogin);
		
		JCheckBox usercheckBox = new JCheckBox("Show");
		usercheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String op = usercheckBox.getText();
				if(op.contentEquals("Show")) {
					passwordField.setEchoChar((char)0);
					usercheckBox.setText("Hide");
				}
				else {
					passwordField.setEchoChar(echoChar);
					usercheckBox.setText("Show");
				}
			}
		});
		usercheckBox.setForeground(Color.WHITE);
		usercheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		usercheckBox.setBackground(Color.BLACK);
		usercheckBox.setBounds(92, 245, 57, 23);
		userPanel.add(usercheckBox);
		
		JLabel lblNewLabel_3 = new JLabel("If already a user, then");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBounds(92, 376, 138, 14);
		userPanel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
					public void run() {
						loginPanel.setVisible(true);
						for(int i=456; i>=0; i--) {
							try {
								Thread.sleep(1);
								userPanel.setBounds(428, 0, i, 461);
								loginPanel.setBounds(428+i, 0, 456-i, 461);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						userPanel.setVisible(false);
					}
				};
				thread.start();
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnNewButton.setBounds(230, 373, 89, 23);
		userPanel.add(btnNewButton);
		
		loginPanel = new JPanel();
		loginPanel.setForeground(Color.WHITE);
		loginPanel.setBorder(null);
		loginPanel.setBackground(Color.BLACK);
		loginPanel.setBounds(428, 0, 456, 461);
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setVisible(false);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setForeground(SystemColor.activeCaption);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label_1.setBounds(92, 86, 84, 28);
		loginPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(92, 118, 290, 20);
		loginPanel.add(textField_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(92, 149, 290, 2);
		loginPanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(92, 236, 290, 2);
		loginPanel.add(separator_3);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setForeground(SystemColor.activeCaption);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label_2.setBounds(92, 173, 84, 28);
		loginPanel.add(label_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(Color.WHITE);
		passwordField_1.setBorder(null);
		passwordField_1.setBackground(Color.BLACK);
		passwordField_1.setBounds(92, 205, 290, 20);
		loginPanel.add(passwordField_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					username = textField_1.getText();
					if(username.isEmpty()) throw new Exception();
					String password = new String(passwordField_1.getPassword());
					if(username.isEmpty()) throw new Exception();

					passwordField_1.setText("");
					
					if(sqlconnect.checkLoginDetails(username, password)) {
						JOptionPane.showMessageDialog(frame, "Login successful");
						Thread thread = new Thread() {
							public void run() {
								activityPanel.setVisible(true);
								for(int i=456; i>=0; i--) {
									try {
										Thread.sleep(1);
										loginPanel.setBounds(428, 0, i, 461);
										activityPanel.setBounds(428+i, 0, 456-i, 461);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								loginPanel.setVisible(false);
							}
						};
						thread.start();
					}
					else {
						JOptionPane.showMessageDialog(frame, "Invalid username or password");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
			}
			
		});
		btnLogin.setBorder(null);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setBounds(92, 309, 290, 39);
		loginPanel.add(btnLogin);
		
		JCheckBox checkBox = new JCheckBox("Show");
		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String op = checkBox.getText();
				if(op.contentEquals("Show")) {
					passwordField_1.setEchoChar((char)0);
					checkBox.setText("Hide");
				}
				else {
					passwordField_1.setEchoChar(echoChar);
					checkBox.setText("Show");
				}				
			}
			
		});
		checkBox.setForeground(Color.WHITE);
		checkBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		checkBox.setBackground(Color.BLACK);
		checkBox.setBounds(92, 245, 57, 23);
		loginPanel.add(checkBox);
		
		activityPanel = new JPanel();
		activityPanel.setForeground(Color.WHITE);
		activityPanel.setBorder(null);
		activityPanel.setBackground(Color.BLACK);
		activityPanel.setBounds(428, 0, 456, 461);
		frame.getContentPane().add(activityPanel);
		activityPanel.setLayout(null);
		activityPanel.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Change Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
					public void run() {
						changePasswordPanel.setVisible(true);
						for(int i=456; i>=0; i--) {
							try {
								Thread.sleep(1);
								activityPanel.setBounds(428, 0, i, 461);
								changePasswordPanel.setBounds(428+i, 0, 456-i, 461);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						activityPanel.setVisible(false);
					}
				};
				thread.start();
			}
		});
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(108, 132, 239, 47);
		activityPanel.add(btnNewButton_1);
		
		JButton btnUpdateSales = new JButton("Update Sales");
		btnUpdateSales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
					public void run() {
						updateSalesPanel.setVisible(true);
						for(int i=456; i>=0; i--) {
							try {
								Thread.sleep(1);
								activityPanel.setBounds(428, 0, i, 461);
								updateSalesPanel.setBounds(428+i, 0, 456-i, 461);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						activityPanel.setVisible(false);
					}
				};
				thread.start();
			}
			
		});
		btnUpdateSales.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnUpdateSales.setBorder(null);
		btnUpdateSales.setBackground(SystemColor.activeCaption);
		btnUpdateSales.setBounds(108, 228, 239, 47);
		activityPanel.add(btnUpdateSales);
		
		changePasswordPanel = new JPanel();
		changePasswordPanel.setForeground(Color.WHITE);
		changePasswordPanel.setBorder(null);
		changePasswordPanel.setBackground(Color.BLACK);
		changePasswordPanel.setBounds(428, 0, 456, 461);
		frame.getContentPane().add(changePasswordPanel);
		changePasswordPanel.setLayout(null);
		changePasswordPanel.setVisible(false);
		
		JLabel lblNewLabel_4 = new JLabel("Current password");
		lblNewLabel_4.setForeground(SystemColor.activeCaption);
		lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(69, 26, 151, 24);
		changePasswordPanel.add(lblNewLabel_4);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setForeground(Color.WHITE);
		passwordField_2.setBorder(null);
		passwordField_2.setBackground(Color.BLACK);
		passwordField_2.setBounds(69, 57, 331, 23);
		echoChar = passwordField_2.getEchoChar();
		changePasswordPanel.add(passwordField_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(69, 91, 331, 10);
		changePasswordPanel.add(separator);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setForeground(SystemColor.activeCaption);
		lblNewPassword.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNewPassword.setBounds(69, 150, 151, 24);
		changePasswordPanel.add(lblNewPassword);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setForeground(Color.WHITE);
		passwordField_3.setBorder(null);
		passwordField_3.setBackground(Color.BLACK);
		passwordField_3.setBounds(69, 181, 331, 23);
		changePasswordPanel.add(passwordField_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(69, 215, 331, 10);
		changePasswordPanel.add(separator_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setForeground(SystemColor.activeCaption);
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(69, 281, 151, 24);
		changePasswordPanel.add(lblConfirmPassword);
		
		passwordField_4 = new JPasswordField();
		passwordField_4.setForeground(Color.WHITE);
		passwordField_4.setBorder(null);
		passwordField_4.setBackground(Color.BLACK);
		passwordField_4.setBounds(69, 312, 331, 23);
		changePasswordPanel.add(passwordField_4);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(69, 346, 331, 10);
		changePasswordPanel.add(separator_4);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String op = chckbxNewCheckBox.getText();
				if(op.contentEquals("Show")) {
					passwordField_2.setEchoChar((char)0);
					chckbxNewCheckBox.setText("Hide");
				}
				else {
					passwordField_2.setEchoChar(echoChar);
					chckbxNewCheckBox.setText("Show");
				}
			}
		});
		chckbxNewCheckBox.setBorder(null);
		chckbxNewCheckBox.setBackground(Color.BLACK);
		chckbxNewCheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBounds(69, 108, 57, 23);
		changePasswordPanel.add(chckbxNewCheckBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Show");
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String op = checkBox_1.getText();
				if(op.contentEquals("Show")) {
					passwordField_3.setEchoChar((char)0);
					checkBox_1.setText("Hide");
				}
				else {
					passwordField_3.setEchoChar(echoChar);
					checkBox_1.setText("Show");
				}
			}
		});
		checkBox_1.setForeground(Color.WHITE);
		checkBox_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		checkBox_1.setBorder(null);
		checkBox_1.setBackground(Color.BLACK);
		checkBox_1.setBounds(69, 232, 57, 23);
		changePasswordPanel.add(checkBox_1);
				
		JCheckBox checkBox_2 = new JCheckBox("Show");
		checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String op = checkBox_2.getText();
				if(op.contentEquals("Show")) {
					passwordField_4.setEchoChar((char)0);
					checkBox_2.setText("Hide");
				}
				else {
					passwordField_4.setEchoChar(echoChar);
					checkBox_2.setText("Show");
				}
			}
		});
		checkBox_2.setForeground(Color.WHITE);
		checkBox_2.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		checkBox_2.setBorder(null);
		checkBox_2.setBackground(Color.BLACK);
		checkBox_2.setBounds(69, 363, 57, 23);
		changePasswordPanel.add(checkBox_2);
		
		JButton btnNewButton_2 = new JButton("Save changes");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String currentPassword = new String(passwordField_2.getPassword());
					String newPassword = new String(passwordField_3.getPassword());
					String confirmPassword = new String(passwordField_4.getPassword());
					
					if(currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) throw new Exception();
					
					passwordField_2.setText("");
					passwordField_3.setText("");
					passwordField_4.setText("");
					
					if(sqlconnect.checkLoginDetails(username, currentPassword)) {
						if(newPassword.equals(confirmPassword)) {
							sqlconnect.updatePassword(username, newPassword);
							JOptionPane.showMessageDialog(frame, "Changes are successfully made.");
							Thread thread = new Thread() {
								public void run() {
									activityPanel.setVisible(true);
									for(int i=456; i>=0; i--) {
										try {
											Thread.sleep(1);
											changePasswordPanel.setBounds(428, 0, i, 461);
											activityPanel.setBounds(428+i, 0, 456-i, 461);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									changePasswordPanel.setVisible(false);
								}
							};
							thread.start();
							
						}
						else {
							JOptionPane.showMessageDialog(frame, "Confirmation of new password failed");
						}
					}
					else {
						JOptionPane.showMessageDialog(frame, "Invalid password");
					}				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Invalid data");
				}
			}
		});
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnNewButton_2.setBounds(69, 405, 331, 31);
		changePasswordPanel.add(btnNewButton_2);
		
		updateSalesPanel = new JPanel();
		updateSalesPanel.setForeground(Color.WHITE);
		updateSalesPanel.setBorder(null);
		updateSalesPanel.setBackground(Color.BLACK);
		updateSalesPanel.setBounds(428, 0, 456, 461);
		frame.getContentPane().add(updateSalesPanel);
		updateSalesPanel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Property ID:");
		lblNewLabel_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewLabel_5.setForeground(SystemColor.activeCaption);
		lblNewLabel_5.setBounds(23, 23, 88, 19);
		updateSalesPanel.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setBounds(121, 24, 301, 20);
		updateSalesPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("Agent ID:");
		label_3.setForeground(SystemColor.activeCaption);
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_3.setBounds(23, 63, 88, 19);
		updateSalesPanel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(121, 64, 301, 20);
		updateSalesPanel.add(textField_3);
		
		JLabel label_4 = new JLabel("Buyer ID:");
		label_4.setForeground(SystemColor.activeCaption);
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_4.setBounds(23, 103, 88, 19);
		updateSalesPanel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(121, 104, 301, 20);
		updateSalesPanel.add(textField_4);
		
		JLabel label_5 = new JLabel("Seller ID:");
		label_5.setForeground(SystemColor.activeCaption);
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_5.setBounds(23, 143, 88, 19);
		updateSalesPanel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(121, 144, 301, 20);
		updateSalesPanel.add(textField_5);
		
		JLabel lblSellingPrice = new JLabel("Selling Price:");
		lblSellingPrice.setForeground(SystemColor.activeCaption);
		lblSellingPrice.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblSellingPrice.setBounds(23, 223, 88, 19);
		updateSalesPanel.add(lblSellingPrice);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setBounds(121, 224, 301, 20);
		updateSalesPanel.add(textField_7);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(SystemColor.activeCaption);
		lblDate.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblDate.setBounds(23, 263, 88, 19);
		updateSalesPanel.add(lblDate);
		
		JXDatePicker picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		picker.setBounds(121, 264, 301, 20);
		picker.setBackground(Color.WHITE);
		updateSalesPanel.add(picker);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Rented");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt = lblSellingPrice.getText();
				if(txt.equals("Selling Price:")) {
					lblSellingPrice.setText("Rent:");
				}
				else {
					lblSellingPrice.setText("Selling Price:");
				}
			}
			
		});
		chckbxNewCheckBox_1.setBackground(SystemColor.activeCaption);
		chckbxNewCheckBox_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		chckbxNewCheckBox_1.setBounds(121, 184, 97, 23);
		updateSalesPanel.add(chckbxNewCheckBox_1);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String propertyID = textField_2.getText();
					if(propertyID.isEmpty()) throw new Exception();
					String agentID = textField_3.getText();
					if(agentID.isEmpty()) throw new Exception();
					String buyerID = textField_4.getText();
					if(buyerID.isEmpty()) throw new Exception();
					String sellerID = textField_5.getText();
					if(sellerID.isEmpty()) throw new Exception();
					String price = textField_7.getText();
					if(price.isEmpty()) throw new Exception();
					boolean isRented;
					if(lblSellingPrice.getText().contentEquals("Selling Price:")) isRented = false;
					else isRented = true;
					
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(picker.getDate());
					String date = "" + calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
					
					if(sqlconnect.verify(propertyID, agentID, buyerID, sellerID)) {
						sqlconnect.updateSales(propertyID, agentID, buyerID, sellerID, isRented, price, date);
						JOptionPane.showMessageDialog(frame, "Successfully updated.");						
					}
					else {
						JOptionPane.showMessageDialog(frame, "ERROR! Some values are invalid. Check again.");
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "ERROR! All fields are mandatory");
				}
			}
		});
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(SystemColor.activeCaption);
		btnNewButton_3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnNewButton_3.setBounds(121, 313, 301, 31);
		updateSalesPanel.add(btnNewButton_3);
		
		JLabel lblNewLabel_6 = new JLabel("Selling price must be in lacs");
		lblNewLabel_6.setForeground(SystemColor.windowBorder);
		lblNewLabel_6.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(23, 382, 154, 14);
		updateSalesPanel.add(lblNewLabel_6);
		
		JLabel lblRentMustBe = new JLabel("Rent must be on a per-month basis");
		lblRentMustBe.setForeground(SystemColor.windowBorder);
		lblRentMustBe.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblRentMustBe.setBounds(23, 407, 195, 14);
		updateSalesPanel.add(lblRentMustBe);
		
		JLabel lblDateMustFollow = new JLabel("All fields are mandatory.");
		lblDateMustFollow.setForeground(SystemColor.windowBorder);
		lblDateMustFollow.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblDateMustFollow.setBounds(23, 432, 175, 14);
		updateSalesPanel.add(lblDateMustFollow);
		
		JButton btnNewButton_4 = new JButton("< Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
					public void run() {
						activityPanel.setVisible(true);
						for(int i=456; i>=0; i--) {
							try {
								Thread.sleep(1);
								updateSalesPanel.setBounds(428, 0, i, 461);
								activityPanel.setBounds(428+i, 0, 456-i, 461);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						updateSalesPanel.setVisible(false);
					}
				};
				thread.start();
			}
		});
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(Color.GRAY);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBounds(333, 427, 89, 23);
		updateSalesPanel.add(btnNewButton_4);
		updateSalesPanel.setVisible(false);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 461);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(0, 0, 0, 200));
		panel.setLayout(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("E");
		lblNewLabel_1.setForeground(new Color(102, 153, 204));
		lblNewLabel_1.setFont(new Font("Castellar", Font.BOLD, 99));
		lblNewLabel_1.setBounds(74, 141, 61, 90);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("R");
		label.setForeground(new Color(102, 153, 204));
		label.setFont(new Font("Castellar", Font.BOLD, 48));
		label.setBounds(39, 141, 44, 51);
		panel.add(label);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(new Color(102, 153, 204));
		lblA.setFont(new Font("Castellar", Font.BOLD, 99));
		lblA.setBounds(141, 104, 84, 90);
		panel.add(lblA);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(new Color(102, 153, 204));
		lblS.setFont(new Font("Castellar", Font.BOLD, 48));
		lblS.setBounds(139, 180, 44, 51);
		panel.add(lblS);
		
		JLabel lblT = new JLabel("T");
		lblT.setForeground(new Color(102, 153, 204));
		lblT.setFont(new Font("Castellar", Font.BOLD, 48));
		lblT.setBounds(181, 180, 44, 51);
		panel.add(lblT);
		
		JLabel lblA_1 = new JLabel("A");
		lblA_1.setForeground(new Color(102, 153, 204));
		lblA_1.setFont(new Font("Castellar", Font.BOLD, 48));
		lblA_1.setBounds(221, 180, 44, 51);
		panel.add(lblA_1);
		
		JLabel lblT_1 = new JLabel("T");
		lblT_1.setForeground(new Color(102, 153, 204));
		lblT_1.setFont(new Font("Castellar", Font.BOLD, 48));
		lblT_1.setBounds(264, 180, 44, 51);
		panel.add(lblT_1);
		
		JLabel lblE = new JLabel("E");
		lblE.setForeground(new Color(102, 153, 204));
		lblE.setFont(new Font("Castellar", Font.BOLD, 48));
		lblE.setBounds(309, 180, 44, 51);
		panel.add(lblE);
		
		JLabel lblL = new JLabel("L");
		lblL.setForeground(new Color(102, 153, 204));
		lblL.setFont(new Font("Castellar", Font.BOLD, 48));
		lblL.setBounds(221, 141, 44, 51);
		panel.add(lblL);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(new Color(102, 153, 204));
		lblD.setFont(new Font("Castellar", Font.BOLD, 48));
		lblD.setBounds(221, 104, 44, 44);
		panel.add(lblD);
		
		JLabel lblD_1 = new JLabel("D");
		lblD_1.setForeground(new Color(102, 153, 204));
		lblD_1.setFont(new Font("Castellar", Font.BOLD, 48));
		lblD_1.setBounds(264, 104, 44, 44);
		panel.add(lblD_1);
		
		JLabel lblA_2 = new JLabel("A");
		lblA_2.setForeground(new Color(102, 153, 204));
		lblA_2.setFont(new Font("Castellar", Font.BOLD, 48));
		lblA_2.setBounds(309, 104, 44, 44);
		panel.add(lblA_2);
		
		JLabel lblNewLabel_2 = new JLabel("Take it, or Leave it...");
		lblNewLabel_2.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(141, 229, 214, 32);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tanishq\\Downloads\\AxonizeBlog_SinglePost_815x600_FacilityintoSmartBuilding.jpg"));
		lblNewLabel.setBounds(0, 0, 450, 500);
		frame.getContentPane().add(lblNewLabel);
	}
}
