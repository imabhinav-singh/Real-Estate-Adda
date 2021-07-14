import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class PropertyDetails {

	private JFrame frame;
	private JLabel label;
	private JTextField textField1;
	private JTextField textField;
	private JLabel lblYear;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxFilterByYear;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxSortByDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertyDetails window = new PropertyDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PropertyDetails() {
		initialize();
	}
		
		private void initialize(){
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(64, 224, 208));
			frame.getContentPane().setForeground(new Color(255, 255, 255));
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setBounds(100, 100, 761, 445);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setTitle("Agent's Rent Enquiry");
			frame.getContentPane().setLayout(null);
			
			textField1 = new JTextField();
			textField1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			textField1.setBounds(392, 106, 166, 34);
			frame.getContentPane().add(textField1);
			textField1.setColumns(10);
			
			label = new JLabel("AgentID:");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			label.setBounds(290, 108, 77, 32);
			frame.getContentPane().add(label);
			
			JButton btnNewButton = new JButton("Go");
			btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			btnNewButton.setBorder(null);
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBackground(new Color(0, 0, 205));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxNewCheckBox.isSelected() || chckbxFilterByYear.isSelected()) {
						if((chckbxNewCheckBox.isSelected() && textField1.getText().equals("")) || (chckbxFilterByYear.isSelected() && textField.getText().equals(""))) {
							JOptionPane.showMessageDialog(frame, "ERROR! Invalid entry.");
						}
						else {
							if(chckbxFilterByYear.isSelected() && chckbxNewCheckBox.isSelected()) {
								SQLConnect sqlconnect = new SQLConnect();
								String agentID = textField1.getText();
								String year = textField.getText();
								if(!sqlconnect.verifyAgent(agentID)) JOptionPane.showMessageDialog(frame, "Invalid ID");
								else sqlconnect.rentResultAgentYear(agentID, year, chckbxNewCheckBox_1.isSelected(), chckbxSortByDate.isSelected());
							}
							else if(chckbxNewCheckBox.isSelected()) {
								SQLConnect sqlconnect = new SQLConnect();
								String agentID = textField1.getText();
								if(!sqlconnect.verifyAgent(agentID)) JOptionPane.showMessageDialog(frame, "Invalid ID");
								else sqlconnect.rentResult(agentID, chckbxNewCheckBox_1.isSelected(), chckbxSortByDate.isSelected());								
							}
							else {
								SQLConnect sqlconnect = new SQLConnect();
								String year = textField.getText();
								sqlconnect.rentResultYear(year, chckbxNewCheckBox_1.isSelected(), chckbxSortByDate.isSelected());
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(frame, "ERROR! No filter chosen.");						
					}
				}
			});
			
			btnNewButton.setBounds(392, 224, 166, 28);
			frame.getContentPane().add(btnNewButton);
			
			chckbxNewCheckBox = new JCheckBox("Filter by AgentID");
			chckbxNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxNewCheckBox.isSelected()) {
						label.setVisible(true);
						textField1.setVisible(true);
					}
					else {
						label.setVisible(false);
						textField1.setVisible(false);
					}
				}
			});
			chckbxNewCheckBox.setSelected(true);
			chckbxNewCheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			chckbxNewCheckBox.setBackground(new Color(0, 0, 205));
			chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
			chckbxNewCheckBox.setBounds(119, 113, 135, 23);
			frame.getContentPane().add(chckbxNewCheckBox);
			
			chckbxFilterByYear = new JCheckBox("Filter by year");
			chckbxFilterByYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxFilterByYear.isSelected()) {
						lblYear.setVisible(true);
						textField.setVisible(true);
					}
					else {
						lblYear.setVisible(false);
						textField.setVisible(false);
					}
				}
			});
			chckbxFilterByYear.setSelected(true);
			chckbxFilterByYear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			chckbxFilterByYear.setBackground(new Color(0, 0, 205));
			chckbxFilterByYear.setForeground(new Color(255, 255, 255));
			chckbxFilterByYear.setBounds(119, 170, 135, 23);
			frame.getContentPane().add(chckbxFilterByYear);
			
			lblYear = new JLabel("Year:");
			lblYear.setForeground(new Color(0, 0, 0));
			lblYear.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			lblYear.setBounds(290, 165, 77, 32);
			frame.getContentPane().add(lblYear);
			
			textField = new JTextField();
			textField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			textField.setColumns(10);
			textField.setBounds(392, 163, 166, 34);
			frame.getContentPane().add(textField);
			
			chckbxNewCheckBox_1 = new JCheckBox("Sort by Selling Price");
			chckbxNewCheckBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxNewCheckBox_1.isSelected()) chckbxSortByDate.setSelected(false);
				}
			});
			chckbxNewCheckBox_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			chckbxNewCheckBox_1.setBackground(new Color(0, 0, 205));
			chckbxNewCheckBox_1.setForeground(new Color(255, 255, 255));
			chckbxNewCheckBox_1.setBounds(392, 273, 166, 28);
			frame.getContentPane().add(chckbxNewCheckBox_1);
			
			chckbxSortByDate = new JCheckBox("Sort by date");
			chckbxSortByDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxSortByDate.isSelected()) chckbxNewCheckBox_1.setSelected(false);
				}
			});
			chckbxSortByDate.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			chckbxSortByDate.setBackground(new Color(0, 0, 205));
			chckbxSortByDate.setForeground(new Color(255, 255, 255));
			chckbxSortByDate.setBounds(392, 316, 166, 28);
			frame.getContentPane().add(chckbxSortByDate);
		}
}
