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

public class Edit extends JFrame{

	protected JFrame frame;
	private JTextField id;
	private JTextField Course_name;
	private JTextField seat;
	private JTextField batch;
	private Database database;
	private JLabel lblNewLabel_1_3;
	private JTextField years;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit window = new Edit();
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
	public Edit() {
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
		
		JLabel lblNewLabel = new JLabel("Course ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(66, 108, 105, 22);
		frame.getContentPane().add(lblNewLabel);
		
		id = new JTextField();
		id.setBackground(new Color(240, 240, 240));
		id.setBounds(66, 129, 261, 20);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seats");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(66, 240, 105, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Batch");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(66, 304, 105, 22);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editCourse();
				dispose(); // Close the current JFrame

				new admin_course().setVisible(true);
				dispose();
				
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
		
		JLabel lblNewLabel_2 = new JLabel("Edit Courses");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(83, 0, 221, 45);
		panel.add(lblNewLabel_2);
		
		Course_name = new JTextField();
		Course_name.setColumns(10);
		Course_name.setBackground(UIManager.getColor("Button.background"));
		Course_name.setBounds(66, 197, 261, 20);
		frame.getContentPane().add(Course_name);
		
		seat = new JTextField();
		seat.setColumns(10);
		seat.setBackground(UIManager.getColor("Button.background"));
		seat.setBounds(66, 259, 261, 20);
		frame.getContentPane().add(seat);
		
		batch = new JTextField();
		batch.setColumns(10);
		batch.setBackground(UIManager.getColor("Button.background"));
		batch.setBounds(66, 324, 261, 20);
		frame.getContentPane().add(batch);
		
		lblNewLabel_1_3 = new JLabel("years");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(66, 371, 105, 22);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		years = new JTextField();
		years.setColumns(10);
		years.setBackground(UIManager.getColor("Button.background"));
		years.setBounds(66, 390, 261, 20);
		frame.getContentPane().add(years);
		
		lblNewLabel_3 = new JLabel("Course Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(66, 177, 105, 22);
		frame.getContentPane().add(lblNewLabel_3);
	}
	
	private void editCourse() {
	    try {
	        // Get the existing connection from the isAdmin method
	        Connection connection = database.getConnection();

	        // Logic to edit a course using the existing database connection
	        String sql = "UPDATE course_s SET course_name = ?, Seats = ?, batch = ?, years = ? WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        // Convert text from JTextFields to integers
	        int seatValue = Integer.parseInt(seat.getText());
	        int batchValue = Integer.parseInt(batch.getText());
	        int yearsValue = Integer.parseInt(years.getText());
	        int courseIDValue = Integer.parseInt(id.getText()); // Assuming id is the JTextField for Course ID

	        // Set parameters in the prepared statement
	        statement.setString(1, Course_name.getText()); // Course name from the JTextField
	        statement.setInt(2, seatValue);
	        statement.setInt(3, batchValue);
	        statement.setInt(4, yearsValue);
	        statement.setInt(5, courseIDValue); // Set the courseID parameter

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(frame, "Course updated successfully!");
	        } else {
	            JOptionPane.showMessageDialog(frame, "Failed to update course.");
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error updating course: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        // Handle if the user enters non-integer values in the JTextFields
	        JOptionPane.showMessageDialog(frame, "Please enter valid integer values for Seats, Batch, Years, and Course ID.");
	    }
	}


}
