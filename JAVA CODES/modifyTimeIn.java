package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class modifyTimeIn extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyTimeIn frame = new modifyTimeIn();
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
	public modifyTimeIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelID = new JLabel("Student ID");
		labelID.setFont(new Font("Georgia", Font.BOLD, 13));
		labelID.setForeground(new Color(153, 51, 255));
		labelID.setHorizontalAlignment(SwingConstants.CENTER);
		labelID.setBounds(216, 94, 221, 31);
		contentPane.add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldID.setBounds(471, 90, 172, 40);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel labelDate = new JLabel("Date of Lecture YYYY-MM-DD ");
		labelDate.setFont(new Font("Georgia", Font.BOLD, 13));
		labelDate.setForeground(new Color(153, 51, 255));
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate.setBounds(192, 223, 245, 25);
		contentPane.add(labelDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldDate.setBounds(471, 216, 172, 40);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton buttonEnter = new JButton("Enter");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date= textFieldDate.getText();
				String ID= textFieldID.getText();
				String modifiedIn= textFieldIn.getText();
				
				if(textFieldDate.getText().equals("")|| textFieldID.getText().equals("") || textFieldIn.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Woops! You missed filling in the field." );
				}
				else {
					DemoWS w= new DemoWS();
					String result= w.callModifyTimeIn(date, ID, modifiedIn);//=call function
					
					
					if(result.contains("ERROR")) {
						JOptionPane.showMessageDialog(null,result );
						textFieldDate.setText(null);
						textFieldID.setText(null);
						textFieldIn.setText(null);
						
					}
					
					else {JOptionPane.showMessageDialog(null,result);}
				}	
			}
		});
		buttonEnter.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonEnter.setForeground(new Color(153, 0, 255));
		buttonEnter.setBackground(new Color(204, 153, 255));
		buttonEnter.setBounds(359, 308, 136, 49);
		contentPane.add(buttonEnter);
		
		JLabel labelTime = new JLabel("New In Time");
		labelTime.setFont(new Font("Georgia", Font.BOLD, 13));
		labelTime.setForeground(new Color(153, 51, 255));
		labelTime.setHorizontalAlignment(SwingConstants.CENTER);
		labelTime.setBounds(216, 161, 221, 19);
		contentPane.add(labelTime);
		
		textFieldIn = new JTextField();
		textFieldIn.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldIn.setBounds(471, 151, 172, 40);
		contentPane.add(textFieldIn);
		textFieldIn.setColumns(10);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions f= new Functions();
				f.setVisible(true);
			}
		});
		buttonBack.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonBack.setForeground(new Color(153, 0, 255));
		buttonBack.setBackground(new Color(204, 153, 255));
		buttonBack.setBounds(0, 0, 96, 26);
		contentPane.add(buttonBack);
	}

}
