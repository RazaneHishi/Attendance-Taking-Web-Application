package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class modifyTimeOut extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldOut;
	private JTextField textFieldDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyTimeOut frame = new modifyTimeOut();
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
	public modifyTimeOut() {
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
		labelID.setBounds(181, 92, 248, 38);
		contentPane.add(labelID);
		
		JLabel labelTime = new JLabel("Modified Out Time");
		labelTime.setFont(new Font("Georgia", Font.BOLD, 13));
		labelTime.setForeground(new Color(153, 51, 255));
		labelTime.setHorizontalAlignment(SwingConstants.CENTER);
		labelTime.setBounds(181, 157, 248, 48);
		contentPane.add(labelTime);
		
		JLabel labelDate = new JLabel("Date of Lecture YYYY-MM-DD ");
		labelDate.setFont(new Font("Georgia", Font.BOLD, 13));
		labelDate.setForeground(new Color(153, 51, 255));
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate.setBounds(181, 234, 248, 37);
		contentPane.add(labelDate);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(471, 94, 161, 38);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldOut = new JTextField();
		textFieldOut.setText("");
		textFieldOut.setBounds(471, 164, 161, 38);
		contentPane.add(textFieldOut);
		textFieldOut.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(471, 233, 161, 38);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton buttonEnter = new JButton("Enter");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date= textFieldDate.getText();
				String ID= textFieldID.getText();
				String modifiedOut= textFieldOut.getText();
				
				if(textFieldDate.getText().equals("")|| textFieldID.getText().equals("") || textFieldOut.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Woops! You missed filling in the field." );
				}
				else {
					 DemoWS w= new DemoWS();
					String result=w.callModifyTimeOut(date, ID, modifiedOut);//=call function
				
					
					if(result.contains("ERROR")) {
						JOptionPane.showMessageDialog(null,result);
						textFieldDate.setText(null);
						textFieldID.setText(null);
						textFieldOut.setText(null);
					}
					
					else {JOptionPane.showMessageDialog(null,result);}
				}		
			}
		});
		buttonEnter.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonEnter.setForeground(new Color(153, 0, 255));
		buttonEnter.setBackground(new Color(204, 153, 255));
		buttonEnter.setBounds(322, 306, 156, 48);
		contentPane.add(buttonEnter);
		
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
