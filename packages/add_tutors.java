package packages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;

public class add_tutors extends JFrame{

	private JFrame frame;
	private JTextField Name;
	private JTextField email;
	private JTextField password;
	private JTextField course;
	private JTextField number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_tutors window = new add_tutors();
					window.frame.setVisible(true);
	                window.setLocationRelativeTo(null); // Center the frame on the screen


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public add_tutors() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		 frame = new JFrame();
		 frame.setVisible(true); 
	    frame.setBounds(100, 100, 679, 491);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Enter the Tutors name");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblNewLabel.setBounds(37, 136, 182, 27);
	    frame.getContentPane().add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("Email");
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblNewLabel_1.setBounds(248, 144, 82, 19);
	    frame.getContentPane().add(lblNewLabel_1);
	    
	    Name = new JTextField();
	    Name.setBounds(37, 162, 182, 20);
	    frame.getContentPane().add(Name);
	    Name.setColumns(10);
	    
	    email = new JTextField();
	    email.setColumns(10);
	    email.setBounds(248, 162, 163, 20);
	    frame.getContentPane().add(email);
	    
	    password = new JTextField();
	    password.setColumns(10);
	    password.setBounds(449, 162, 178, 20);
	    frame.getContentPane().add(password);
	    
	    course = new JTextField();
	    course.setColumns(10);
	    course.setBounds(248, 229, 163, 20);
	    frame.getContentPane().add(course);
	    
	    number = new JTextField();
	    number.setColumns(10);
	    number.setBounds(37, 229, 182, 20);
	    frame.getContentPane().add(number);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Number");
	    lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblNewLabel_1_1.setBounds(38, 210, 82, 19);
	    frame.getContentPane().add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Password");
	    lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblNewLabel_1_2.setBounds(449, 140, 82, 19);
	    frame.getContentPane().add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_1_2_1 = new JLabel("Course");
	    lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblNewLabel_1_2_1.setBounds(248, 210, 82, 19);
	    frame.getContentPane().add(lblNewLabel_1_2_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Tutors Details");
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
	    lblNewLabel_2.setBounds(213, 35, 228, 51);
	    frame.getContentPane().add(lblNewLabel_2);
	    
	    JButton btnNewButton = new JButton("Submit");
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
	    btnNewButton.setBounds(261, 318, 112, 37);
	    frame.getContentPane().add(btnNewButton);
	    
	    
	    
	    // Action performed by the Submit button
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Get the database connection
	            Database database = new Database();
	            Connection connection = database.getConnection();

	            try {
	                // Prepare the SQL statement to insert a new tutor
	                String sql = "INSERT INTO teacher (username, email,passwrd, number,course) VALUES (?, ?, ?, ?, ?)";
	                PreparedStatement statement = connection.prepareStatement(sql);
	                statement.setString(1, Name.getText());
	                statement.setString(2, email.getText());
	                statement.setString(3, password.getText());
	                statement.setString(4, number.getText());
	                statement.setString(5, course.getText());

	                // Execute the SQL statement to insert the tutor
	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    JOptionPane.showMessageDialog(frame, "Tutor added successfully!");
	                    // Clear the text fields
	                    Name.setText("");
	                    email.setText("");
	                    number.setText("");
	                    password.setText("");
	                    course.setText("");
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Failed to add Tutor");
	                }

	                // Close the statement
	                statement.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                System.out.println("Error adding tutor: " + ex.getMessage());
	            }
	        }
	    });
	    
	}
	

	
	
}
