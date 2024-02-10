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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class deletecourse extends JFrame {
    
    private JTextField textField;
    private Database database; // Declare a Database object
    private JFrame frame;

    public deletecourse() {
        initialize();
        database = new Database(); // Initialize the Database object
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    deletecourse window = new deletecourse();
                    window.setVisible(true); // Call setVisible() on the frame object
                    window.setLocationRelativeTo(null); // Center the frame on the screen

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Delete Course");
        setBounds(100, 100, 307, 382);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Delete Course");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
        lblNewLabel.setBounds(33, 42, 192, 32);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Course ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(52, 129, 147, 25);
        getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(51, 165, 185, 25);
        getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Assuming textField is a JTextField where the user inputs the course ID
                String courseIdToDelete = textField.getText();

                deleteCourse(database, courseIdToDelete);
                textField.setText("");

            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(89, 241, 89, 23);
        getContentPane().add(btnNewButton);
    }

    public void deleteCourse(Database database, String courseIdToDelete) {
    	if (courseIdToDelete == null || courseIdToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(frame,"Course ID cannot be empty");
            
            return;
        }
        // Get the database connection
        Connection connection = database.getConnection();

        try {
            // Prepare the SQL statement to delete the course
            String sql = "DELETE FROM Course_s WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseIdToDelete);
            
            // Execute the SQL statement to delete the course
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Successfully deleted the course");
                textField.setText("");
                
            }
            
            else {
                JOptionPane.showMessageDialog(frame, "Failed to delete course. Course ID may not exist.");
                
            }

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }
}
