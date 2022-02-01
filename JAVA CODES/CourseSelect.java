package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class CourseSelect extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseSelect frame = new CourseSelect();
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
	public CourseSelect() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelCourse = new JLabel("Select Your Course");
		LabelCourse.setFont(new Font("Georgia", Font.BOLD, 13));
		LabelCourse.setForeground(new Color(153, 51, 255));
		LabelCourse.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCourse.setBounds(312, 93, 168, 32);
		contentPane.add(LabelCourse);
		
		JButton button351 = new JButton("EECE 351");
		button351.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions fun= new Functions();
				fun.setVisible(true);
			}
		});
		button351.setFont(new Font("Georgia", Font.BOLD, 13));
		button351.setForeground(new Color(153, 0, 255));
		button351.setBackground(new Color(204, 153, 255));
		button351.setBounds(323, 151, 162, 51);
		contentPane.add(button351);
		
		JButton button451 = new JButton("EECE 451");
		button451.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,"No Records Found" );
			}
		});
		button451.setForeground(new Color(153, 0, 255));
		button451.setBackground(new Color(204, 153, 255));
		button451.setFont(new Font("Georgia", Font.BOLD, 13));
		button451.setBounds(323, 236, 157, 51);
		contentPane.add(button451);
		
		
	}
}
