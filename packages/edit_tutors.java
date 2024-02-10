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

public class edit_tutors extends JFrame{

	protected JFrame frame;
	private JTextField id;
	private JTextField tutors_name;
	private JTextField email;
	private JTextField password;
	private Database database;
	private JLabel lblNewLabel_1_3;
	private JTextField number;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_tutors window = new edit_tutors();
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
	public edit_tutors() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 422, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tutors ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(66, 108, 105, 22);
		frame.getContentPane().add(lblNewLabel);
		
		id = new JTextField();
		id.setBackground(new Color(240, 240, 240));
		id.setBounds(66, 129, 261, 20);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(66, 240, 105, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(66, 304, 105, 22);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editTutors();
				
		}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		submit.setBounds(161, 430, 89, 23);
		frame.getContentPane().add(submit);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 91));
		panel.setBounds(44, 28, 304, 45);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Tutors");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(83, 0, 221, 45);
		panel.add(lblNewLabel_2);
		
		tutors_name = new JTextField();
		tutors_name.setColumns(10);
		tutors_name.setBackground(UIManager.getColor("Button.background"));
		tutors_name.setBounds(66, 197, 261, 20);
		frame.getContentPane().add(tutors_name);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBackground(UIManager.getColor("Button.background"));
		email.setBounds(66, 259, 261, 20);
		frame.getContentPane().add(email);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBackground(UIManager.getColor("Button.background"));
		password.setBounds(66, 324, 261, 20);
		frame.getContentPane().add(password);
		
		lblNewLabel_1_3 = new JLabel("Number");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(66, 371, 105, 22);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBackground(UIManager.getColor("Button.background"));
		number.setBounds(66, 390, 261, 20);
		frame.getContentPane().add(number);
		
		lblNewLabel_3 = new JLabel("Tutors Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(66, 177, 105, 22);
		frame.getContentPane().add(lblNewLabel_3);
	}
	
	private void editTutors() {
	    try {
	        // Get the existing connection from the database
	        Connection connection = database.getConnection();

	        // Logic to edit a student using the existing database connection
	        String sql = "UPDATE teacher SET username = ?, email = ?, passwrd = ?, number = ? WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        // Convert text from JTextFields to appropriate data types
	        int teacherIDValue = Integer.parseInt(id.getText()); // Assuming id is the JTextField for Teacher ID

	        // Set parameters in the prepared statement
	        statement.setString(1, tutors_name.getText()); // Assuming Course_name JTextField holds teacher name
	        statement.setString(2, email.getText()); // Assuming email JTextField holds teacher email
	        statement.setString(3, password.getText()); // Assuming passwrd JTextField holds teacher password
	        statement.setString(4, number.getText()); // Assuming number JTextField holds teacher number
	        statement.setInt(5, teacherIDValue); // Set the teacher ID parameter

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(frame, "Teacher information updated successfully!");
	        } else {
	            JOptionPane.showMessageDialog(frame, "Failed to update teacher information.");
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error updating teacher information: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        // Handle if the user enters non-integer values in the JTextFields
	        JOptionPane.showMessageDialog(frame, "Please enter valid integer values for Teacher ID.");
	    }
	}



}
