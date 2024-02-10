package packages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class edit_student extends JFrame{

	protected JFrame frame;
	private JTextField id;
	private JTextField student_name;
	private JTextField email;
	private JTextField phone_number;
	private Database database;
	private JLabel lblNewLabel_1_3;
	private JTextField course;
	private JLabel lblNewLabel_3;
	private JTextField Password_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_student window = new edit_student();
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
	public edit_student() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 422, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setBounds(66, 108, 105, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel);
		
		id = new JTextField();
		id.setBounds(66, 129, 261, 20);
		id.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(66, 240, 105, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Phone Number");
		lblNewLabel_1_1.setBounds(66, 350, 105, 22);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(161, 490, 89, 23);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editStudent();

				
		}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(submit);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 304, 45);
		panel.setBackground(new Color(255, 255, 91));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Edit students");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(83, 0, 221, 45);
		panel.add(lblNewLabel_2);
		
		student_name = new JTextField();
		student_name.setBounds(66, 197, 261, 20);
		student_name.setColumns(10);
		student_name.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(student_name);
		
		email = new JTextField();
		email.setBounds(66, 259, 261, 20);
		email.setColumns(10);
		email.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(email);
		
		phone_number = new JTextField();
		phone_number.setBounds(66, 370, 261, 20);
		phone_number.setColumns(10);
		phone_number.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(phone_number);
		
		lblNewLabel_1_3 = new JLabel("Course");
		lblNewLabel_1_3.setBounds(66, 411, 105, 22);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1_3);
		
		course = new JTextField();
		course.setBounds(66, 430, 261, 20);
		course.setColumns(10);
		course.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(course);
		
		lblNewLabel_3 = new JLabel("Student Name");
		lblNewLabel_3.setBounds(66, 177, 105, 22);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_3);
		
		Password_text = new JTextField();
		Password_text.setColumns(10);
		Password_text.setBackground(UIManager.getColor("Button.background"));
		Password_text.setBounds(66, 313, 261, 20);
		frame.getContentPane().add(Password_text);
		
		JLabel password = new JLabel("password");
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		password.setBounds(66, 290, 105, 22);
		frame.getContentPane().add(password);
	}
	
	private void editStudent() {
	    try {
	        // Get the existing connection from the database
	        Connection connection = Database.getConnection();

	        // Logic to edit a student using the existing database connection
	        String sql = "UPDATE student SET username = ?, email = ?, passwrd = ?, phone = ?,course =? WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        // Convert text from JTextFields to appropriate data types
	        int studentIDValue = Integer.parseInt(id.getText()); // Assuming id is the JTextField for Student ID

	        // Set parameters in the prepared statement
	        statement.setString(1, student_name.getText()); // Assuming student_name JTextField holds student name
	        statement.setString(2, email.getText()); // Assuming email JTextField holds student email
	        statement.setString(3, Password_text.getText()); // Assuming Password_text JTextField holds student password
	        statement.setString(4, phone_number.getText()); // Assuming phone_number JTextField holds student phone number
	        statement.setString(5, course.getText()); // Assuming course JTextField holds student course
	        statement.setInt(6, studentIDValue); // Set the student ID parameter


	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(frame, "Student information updated successfully!");
	            student_name.setText("");
	            email.setText("");
	            Password_text.setText("");
	            phone_number.setText("");
	            id.setText("");
	            course.setText("");
	            

	        } else {
	            JOptionPane.showMessageDialog(frame, "Failed to update student information.");
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error updating student information: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        // Handle if the user enters non-integer values in the JTextFields
	        JOptionPane.showMessageDialog(frame, "Please enter valid integer values for Student ID.");
	    }
	}
}
