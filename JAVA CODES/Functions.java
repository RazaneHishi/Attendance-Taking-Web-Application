package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Functions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Functions frame = new Functions();
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
	public Functions() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ButtonIDSearch = new JButton("Search By Name");
		ButtonIDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchName student= new searchName();
				student.setVisible(true);
			}
		});
		ButtonIDSearch.setFont(new Font("Georgia", Font.BOLD, 13));
		ButtonIDSearch.setForeground(new Color(153, 0, 255));
		ButtonIDSearch.setBackground(new Color(204, 153, 255));
		ButtonIDSearch.setBounds(156, 220, 414, 36);
		contentPane.add(ButtonIDSearch);
		
		JButton buttonModify = new JButton("Modify Records");
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modifyRecords modify= new modifyRecords();
				modify.setVisible(true);

			}
		});
		buttonModify.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonModify.setForeground(new Color(153, 0, 255));
		buttonModify.setBackground(new Color(204, 153, 255));
		buttonModify.setBounds(156, 291, 414, 36);
		contentPane.add(buttonModify);
		
		JButton buttonViewRecords = new JButton("View Attendance Records");
		buttonViewRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAttendance view = new viewAttendance();
				view.setVisible(true);
				
			}
		});
		buttonViewRecords.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonViewRecords.setForeground(new Color(153, 0, 255));
		buttonViewRecords.setBackground(new Color(204, 153, 255));
		buttonViewRecords.setBounds(156, 157, 414, 36);
		contentPane.add(buttonViewRecords);
		
		JButton buttonGViewAbs = new JButton("View Absentees");
		buttonGViewAbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAbsentees view= new viewAbsentees();
				view.setVisible(true);
			}
		});
		buttonGViewAbs.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonGViewAbs.setForeground(new Color(153, 0, 255));
		buttonGViewAbs.setBackground(new Color(204, 153, 255));
		buttonGViewAbs.setBounds(156, 93, 414, 36);
		contentPane.add(buttonGViewAbs);
		
		JButton buttonHalf = new JButton("View Who Remained More Than Half The Lecture");
		buttonHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remainedHalf half= new remainedHalf();
				half.setVisible(true);
				
			}
		});
		buttonHalf.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonHalf.setForeground(new Color(153, 0, 255));
		buttonHalf.setBackground(new Color(204, 153, 255));
		buttonHalf.setBounds(156, 362, 414, 41);
		contentPane.add(buttonHalf);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseSelect f= new CourseSelect();
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
