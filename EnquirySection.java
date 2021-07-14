import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;

public class EnquirySection  {

	private JFrame frame;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnquirySection window = new EnquirySection();
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
	public EnquirySection() {
		start();
	}
	private void start()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 760, 445);
		frame.setTitle("Enquiry Section");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton1 = new JButton("Sales report");
		btnNewButton1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnNewButton1.setForeground(Color.WHITE);
		btnNewButton1.setBackground(Color.BLACK);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesReport().main(null);
			}
		});
		btnNewButton1.setBounds(279, 125, 211, 44);
		frame.getContentPane().add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("Rent report");
		btnNewButton2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnNewButton2.setForeground(Color.WHITE);
		btnNewButton2.setBackground(Color.BLACK);
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PropertyDetails().main(null);
			}
		});
		btnNewButton2.setBounds(279, 222, 211, 44);
		frame.getContentPane().add(btnNewButton2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 752, 413);
		panel.setBackground(new Color(0, 0, 0, 120));
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tanishq\\Pictures\\Title.jpg"));
		lblNewLabel.setBounds(0, 0, 752, 413);
		frame.getContentPane().add(lblNewLabel);
	}

}
