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
import javax.swing.table.TableColumn;
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

public class searchName extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchName frame = new searchName();
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
	public searchName() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelID = new JLabel("Search by Student Name");
		labelID.setFont(new Font("Georgia", Font.BOLD, 13));
		labelID.setForeground(new Color(153, 51, 255));
		labelID.setHorizontalAlignment(SwingConstants.CENTER);
		labelID.setBounds(204, 201, 200, 28);
		contentPane.add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldID.setBounds(450, 190, 161, 51);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton buttonEnter = new JButton("Enter");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldID.getText();
				if(textFieldID.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Woops! You missed filling in the field." );
				}
				else {
					
					JFrame f; 
					JTable j;
					
					DemoWS w= new DemoWS();
					
					
					f = new JFrame(); 
					  
			        f.setTitle("JTable Example"); 
			        
			        //call method send date
			        
					String [] columnNames= {"#","Name","ID", "Date", "Time In", "Time Out", "Total"};
					String[][] data= w.callSearchByName(name) ;
					
					
					
					if(data[0][0].contains("ERROR")) {
						
						JOptionPane.showMessageDialog(null,"ERROR");
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
		buttonEnter.setBounds(318, 282, 161, 51);
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
