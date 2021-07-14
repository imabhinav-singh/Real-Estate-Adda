import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 852, 479);
		this.setTitle("Real Estate Adda");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 120));
		panel.setBounds(0, 0, 836, 440);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Real Estate Adda Office");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm.main(null);
				setVisible(false);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton.setBounds(284, 115, 284, 60);
		panel.add(btnNewButton);
		
		JButton btnAgentLogin = new JButton("Agent Login");
		btnAgentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgentGUI.main(null);
				setVisible(false);
			}
		});
		btnAgentLogin.setForeground(new Color(255, 255, 255));
		btnAgentLogin.setBackground(new Color(0, 0, 0));
		btnAgentLogin.setBorder(null);
		btnAgentLogin.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnAgentLogin.setBounds(284, 233, 284, 60);
		panel.add(btnAgentLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Tanishq\\Downloads\\duo-buro-ole-scheeren-hexagon-facades-architecture_iwan-baan_dezeen_hero-852x479.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 440);
		contentPane.add(lblNewLabel);
	}
}
