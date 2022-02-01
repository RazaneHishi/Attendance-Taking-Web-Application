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

public class viewAbsentees extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewAbsentees frame = new viewAbsentees();
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
	public viewAbsentees() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date of Lecture YYYY-MM-DD ");
		lblNewLabel.setBounds(150, 157, 248, 44);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(153, 51, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldDate.setBounds(432, 157, 172, 44);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton buttonEnter = new JButton("Enter");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date= textFieldDate.getText();
				
				if(textFieldDate.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Woops! You missed filling in the field." );
				}
				else {
					
					JFrame f; 
					JTable j;
					
					f = new JFrame(); 
					  
			        f.setTitle("JTable Example"); 
			        
			        //call method send date
			        
			        DemoWS w= new DemoWS();
			        
					String [] columnNames= {"#","Name","ID", "Date", "Time In", "Time Out", "Total"};
					String[][] data= (String[][]) w.callViewAbsentees(date) ;
					
				
					
					if(data[0][0].contains("ERROR")) {
						
						JOptionPane.showMessageDialog(null,data[0][0]);
					}
					
					
					else {
					j = new JTable(data, columnNames); 
					
					j.setBounds(30, 40, 200, 300); 
					
				
			        JScrollPane sp = new JScrollPane(j); 
			        f.getContentPane().add(sp); 
			     
			        f.setSize(500, 200); 
			     
			        f.setVisible(true); 
					}
				}	
			}
		});
		buttonEnter.setFont(new Font("Georgia", Font.BOLD, 13));
		buttonEnter.setForeground(new Color(153, 0, 255));
		buttonEnter.setBackground(new Color(204, 153, 255));
		buttonEnter.setBounds(327, 286, 156, 44);
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
