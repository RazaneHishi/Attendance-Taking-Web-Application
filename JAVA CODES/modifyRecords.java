package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class modifyRecords extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyRecords frame = new modifyRecords();
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
	public modifyRecords() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton buttonIn = new JButton("Modify Time In");
		buttonIn.setBounds(271, 163, 264, 56);
		buttonIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyTimeIn modify= new modifyTimeIn();
				modify.setVisible(true);
			}
		});
		buttonIn.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonIn.setForeground(new Color(153, 0, 255));
		buttonIn.setBackground(new Color(204, 153, 255));
		contentPane.setLayout(null);
		contentPane.add(buttonIn);
		
		JButton buttonOut = new JButton("Modify Time Out");
		buttonOut.setBounds(271, 251, 264, 61);
		buttonOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyTimeOut modify= new modifyTimeOut();
				modify.setVisible(true);
			}
		});
		buttonOut.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonOut.setForeground(new Color(153, 0, 255));
		buttonOut.setBackground(new Color(204, 153, 255));
		contentPane.add(buttonOut);
		
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
