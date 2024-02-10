package packages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Result extends JFrame {

	protected JFrame frame;
    private JTextField student_ID;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea student_name; // Declare student_name as a class-level field

    
    // Database connection
    private Connection connection;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Result window = new Result();
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
    public Result() {
        initialize();
        // Initialize database connection
        connection = Database.getConnection(); // Implement your database connection method
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        tableModel = new DefaultTableModel();

        tableModel = new DefaultTableModel();
        frame.getContentPane().setBackground(new Color(255, 255, 213));
        frame.setBounds(100, 100, 693, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter Student ID");
        lblNewLabel.setBackground(new Color(255, 242, 149));
        lblNewLabel.setBounds(53, 65, 140, 18);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        frame.getContentPane().add(lblNewLabel);

        student_ID = new JTextField();
        student_ID.setBounds(53, 84, 250, 25);
        frame.getContentPane().add(student_ID);
        student_ID.setColumns(10);

        JLabel Student_name = new JLabel("Student Name: ");
        Student_name.setBounds(53, 165, 107, 20);
        Student_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(Student_name);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(53, 204, 550, 250);
        frame.getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Course", "Persentage", "Grade"}
            );

        table = new JTable(new DefaultTableModel(
        	    new Object[][] {
        	        {null, null, null},
        	        {null, null, null},
        	        {null, null, null},
        	        {null, null, null},
        	        {null, null, null},
        	    },
        	    new String[] {
        	        "Course", "Persentage", "Grade"
        	    }
        	));
        table.setFont(new Font("Arial", Font.PLAIN, 16));


        	// Now you add the table model to your JTable
        	table.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        				"Course", "Persentage", "Grade"
        		}
        	));
            table.setModel(tableModel); // Set the table model

        	// Set the row height for the table
        	table.setRowHeight(30);

        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);

        scrollPane.setViewportView(table);
        table.setModel(tableModel);
        
        JButton btnNewButton = new JButton("Submit");
        btnNewButton.setBounds(53, 115, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentId = student_ID.getText(); // Get the student ID entered by the user
                fetchStudentData(studentId); // Call fetchTeacherData with the student ID
            }
        });
        btnNewButton.setBackground(new Color(190, 255, 125));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        frame.getContentPane().add(btnNewButton);
        
        student_name = new JTextArea();
        student_name.setBounds(170, 165, 250, 22);
        student_name.setFont(new Font("Arial", Font.BOLD, 20));
        student_name.setText("\r\n\r\n\r\n");
        student_name.setBackground(new Color(255, 255, 213));
        frame.getContentPane().add(student_name);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 255, 179));
        panel.setBounds(371, 35, 280, 39);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Student Progress Report");
        lblNewLabel_1.setBounds(30, 0, 222, 39);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setBackground(new Color(217, 255, 179));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
//        JLabel lblNewLabel_2 = new JLabel("");
//        lblNewLabel_2.setBounds(0, 0, 675, 480);
//        frame.getContentPane().add(lblNewLabel_2);
//        Image teach = new ImageIcon(getClass().getResource("/result.png")).getImage();
//        Image Scaleteach = teach.getScaledInstance(675, 650, Image.SCALE_SMOOTH);
//        lblNewLabel_2.setIcon(new ImageIcon(Scaleteach));
        
        
    }

    // Method to populate the table with data from the database
   
    
    private void fetchStudentData(String studentId) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/final_assessment";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT username, course, persentage, grade FROM student WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            // Set the student ID parameter in the prepared statement
            statement.setString(1, studentId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Clear existing data from the table model
            tableModel.setRowCount(0);

            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String studentName = resultSet.getString("username");
                student_name.setText(studentName); // Set the student name in the JTextArea

                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("course"));
                row.add(resultSet.getString("persentage"));
                row.add(resultSet.getString("grade"));

                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception details to console
        }
    }
}