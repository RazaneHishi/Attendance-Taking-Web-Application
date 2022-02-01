package project;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.io.*; 
import java.net.*; 


public class WelcomeWin {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWin window = new WelcomeWin();
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
	public WelcomeWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 250, 250));
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.setBounds(100, 100, 857, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel LabelUsername = new JLabel("Username");
		LabelUsername.setForeground(new Color(153, 51, 255));
		LabelUsername.setFont(new Font("Georgia", Font.BOLD, 13));
		LabelUsername.setBounds(218, 154, 74, 30);
		frame.getContentPane().add(LabelUsername);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setForeground(new Color(153, 51, 255));
		LabelPassword.setFont(new Font("Georgia", Font.BOLD, 13));
		LabelPassword.setBounds(218, 244, 74, 30);
		frame.getContentPane().add(LabelPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				if(textFieldUsername.getText().equals("") || textFieldPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Woops! You missed filling in the fields." );
					textFieldUsername.setText(null);
					textFieldPassword.setText(null);
					
					
				}
				
					
				
				else {
					
					DemoWS w= new DemoWS();
					
					String result= w.login(username,password);//=call function
					String error="User not found";
					
					if(result.equals(error)) {
						JOptionPane.showMessageDialog(null, error);
						
						textFieldUsername.setText(null);
						textFieldPassword.setText(null);
					}
					
					else {
						CourseSelect f= new CourseSelect();
						f.setVisible(true);
					}

					}
				
	
				
			}
		});
		btnNewButton.setForeground(new Color(153, 0, 255));
		btnNewButton.setBackground(new Color(204, 153, 255));
		btnNewButton.setFont(new Font("Georgia", Font.BOLD, 13));
		btnNewButton.setBounds(312, 389, 146, 38);
		frame.getContentPane().add(btnNewButton);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldUsername.setBounds(435, 157, 161, 26);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldPassword.setBounds(435, 247, 161, 25);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		
	}
}
