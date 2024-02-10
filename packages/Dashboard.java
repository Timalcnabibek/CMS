package packages;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.CompoundBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Dashboard extends JFrame {
    protected JTextArea studentCount;
    protected JTextArea tutors_count;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    protected JFrame frame;
    protected JTextArea Course_Count;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard frame = new Dashboard();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); // Center the frame on the screen

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700); // Adjust the size as needed
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 255, 230));
        contentPane.setBorder(new CompoundBorder());
        setContentPane(contentPane);
        frame = this;
        contentPane.setLayout(null);

        studentCount = new JTextArea();
        studentCount.setBackground(new Color(240, 240, 240));
        studentCount.setFont(new Font("Arial", Font.PLAIN, 38));

        tutors_count = new JTextArea();
        tutors_count.setFont(new Font("Arial", Font.PLAIN, 38));
        tutors_count.setBackground(UIManager.getColor("Button.background"));
        
        Course_Count = new JTextArea();
        Course_Count.setFont(new Font("Arial", Font.PLAIN, 38));
        Course_Count.setBackground(UIManager.getColor("Button.background"));

        loadStudentCount();

        // Title Label
        JLabel titleLabel = new JLabel("University of Wolverhampton");
        titleLabel.setBounds(333, 10, 560, 47);
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
        contentPane.add(titleLabel);

        // Address Label
        JLabel addressLabel = new JLabel("Naxal, Bhagwati mandir, Kathmandu (014236548)");
        addressLabel.setBounds(441, 60, 360, 21);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(addressLabel);

        // View Courses Button
        JButton dashboard = new JButton("Dashboard");
        dashboard.setBounds(50, 250, 150, 30);
        dashboard.setBackground(new Color(255, 255, 168));
        dashboard.setFont(new Font("Tahoma", Font.BOLD, 16));
        dashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Opens the Dashboard frame with username and role
                Dashboard dashboardFrame = new Dashboard();
                dashboardFrame.setVisible(true);
                dispose(); // Close the current frame (login frame)
            }
        });
        contentPane.add(dashboard);

        JButton Tutor = new JButton("  Tutors");
        Tutor.setBounds(50, 350, 150, 30);
        Tutor.setBackground(new Color(255, 255, 255));
        Tutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image teach = new ImageIcon(getClass().getResource("/teacher_icon.png")).getImage();
        Image Scaleteach = teach.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Tutor.setIcon(new ImageIcon(Scaleteach));
        // Add action listener to Tutor button, not dashboard button
        Tutor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                tutors tutorFrame = new tutors();
                tutorFrame.frame.setVisible(true);
                dispose();
            }
        });
        contentPane.add(Tutor);


        // Manage Students Button
        JButton Student = new JButton("  Students");
        Student.setBounds(50, 400, 150, 30);
        Student.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		students stuframe = new students();
        		stuframe.setVisible(true);
        		dispose();
        	}
        });
        Student.setBackground(new Color(255, 255, 255));
        Student.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image Simg = new ImageIcon(getClass().getResource("/Another.png")).getImage();
        Image scaleSimg = Simg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Student.setIcon(new ImageIcon(scaleSimg));
        
        
        contentPane.add(Student);

        // Mail Button
        JButton Mail = new JButton("    Mail");
        Mail.setBounds(50, 450, 150, 30);
        Mail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Define the URL for Gmail
                String url = "https://mail.google.com/";

                try {
                    // Create a URI object from the URL string
                    URI uri = new URI(url);

                    // Check if the Desktop is supported to browse the web
                    if (Desktop.isDesktopSupported()) {
                        // Get the Desktop instance
                        Desktop desktop = Desktop.getDesktop();

                        // Open the URI in the default web browser
                        desktop.browse(uri);
                    } else {
                        // If Desktop is not supported, handle the case accordingly
                        JOptionPane.showMessageDialog(frame, "Desktop browsing is not supported.");
                    }
                } catch (URISyntaxException | IOException ex) {
                    // Handle exceptions related to URI syntax or I/O errors
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error opening Gmail: " + ex.getMessage());
                }
        	}
        });
        Mail.setBackground(new Color(255, 255, 255));
        Mail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image mail = new ImageIcon(getClass().getResource("/gmail.png")).getImage();
        Image Scalemail = mail.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        Mail.setIcon(new ImageIcon(Scalemail));
       
        contentPane.add(Mail);

        // Setting Button
        JButton Setting = new JButton("  Settings");
        Setting.setBounds(50, 500, 150, 30);
        Setting.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Setting sframe = new Setting();
        		sframe.setVisible(true);
        		frame.dispose();
        		
        	}
        });
        Setting.setBackground(new Color(255, 255, 255));
        Setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image set = new ImageIcon(getClass().getResource("/Setting.png")).getImage();
        Image ScaleSet = set.getScaledInstance(25,25, Image.SCALE_SMOOTH);
        Setting.setIcon(new ImageIcon(ScaleSet));
        
        contentPane.add(Setting);

        
        JButton Logout = new JButton("  Logout");
        Logout.setBounds(50, 550, 150, 30);
        Logout.setBackground(new Color(255, 255, 255));
        Logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image Log = new ImageIcon(getClass().getResource("/Logout.png")).getImage();
        Image ScaleLog = Log.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Logout.setIcon(new ImageIcon(ScaleLog));
       
        contentPane.add(Logout);
        
        JPanel Container = new JPanel();
        Container.setBounds(250, 250, 674, 400);
        contentPane.add(Container);
        Container.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Welcome to the Dashboard!!!");
        lblNewLabel.setForeground(new Color(64, 0, 64));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(130, 64, 400, 40);
        Container.add(lblNewLabel);
        
        JPanel For_Students = new JPanel();
        For_Students.setBounds(250, 120, 200, 103);
        contentPane.add(For_Students);
        For_Students.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Student Number");
        lblNewLabel_1.setBounds(10, 0, 150, 24);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        For_Students.add(lblNewLabel_1);
        
        
        JLabel Student_Icon = new JLabel("  ||");
        Student_Icon.setForeground(new Color(255, 0, 0));
        Student_Icon.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
        Student_Icon.setBounds(10, 35, 103, 55);
        For_Students.add(Student_Icon);
        Image std = new ImageIcon(getClass().getResource("/Student.png")).getImage();
        Image scaledstd = std.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Student_Icon.setIcon(new ImageIcon(scaledstd));

        
        
        JPanel For_Course = new JPanel();
        For_Course.setBounds(490, 120, 200, 103);
        contentPane.add(For_Course);
        For_Course.setLayout(null);
        
        JLabel Course = new JLabel("  ||");
        Course.setBounds(10, 41, 103, 50);
        Course.setForeground(new Color(255, 0, 0));
        Course.setFont(new Font("Tahoma", Font.PLAIN, 29));
        For_Course.add(Course);
        Image Course_icon = new ImageIcon(getClass().getResource("/course.png")).getImage();
        Image scaledCIcon = Course_icon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Course.setIcon(new ImageIcon(scaledCIcon));
        
        JLabel lblNewLabel_1_1 = new JLabel("Courses");
        lblNewLabel_1_1.setBounds(10, 0, 86, 24);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        For_Course.add(lblNewLabel_1_1);
        
        
        Course_Count.setBounds(130, 36, 60, 55);
        For_Course.add(Course_Count);
        
       
        Student_Icon.setForeground(new Color(255, 0, 0));
        Student_Icon.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
        Student_Icon.setBounds(10, 35, 103, 55);
        Image cimg = new ImageIcon(getClass().getResource("/Student.png")).getImage();
        Image scaledcimg = cimg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Student_Icon.setIcon(new ImageIcon(scaledcimg));
        
        studentCount.setBounds(126, 41, 60, 55);
        For_Students.add(studentCount);
        
        JPanel For_Tutors = new JPanel();
        For_Tutors.setBounds(724, 120, 200, 103);
        For_Tutors.setLayout(null);
        contentPane.add(For_Tutors);
        
        JLabel Tutors = new JLabel("  ||");
        Tutors.setForeground(Color.RED);
        Tutors.setFont(new Font("Tahoma", Font.PLAIN, 29));
        Tutors.setBounds(10, 41, 103, 50);
        For_Tutors.add(Tutors);
        Image Timg = new ImageIcon(getClass().getResource("/Tutors.png")).getImage();
        Image ScaleTimg = Timg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        Tutors.setIcon(new ImageIcon(ScaleTimg));
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Total Tutors");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_1_1_1.setBounds(10, 0, 150, 24);
        For_Tutors.add(lblNewLabel_1_1_1);
        
        tutors_count.setFont(new Font("Arial", Font.PLAIN, 38));
        tutors_count.setBackground(UIManager.getColor("Button.background"));
        tutors_count.setBounds(130, 41, 60, 55);
        For_Tutors.add(tutors_count);
        
        JLabel CMSL = new JLabel("");
        CMSL.setBounds(71, 75, 113, 135);
        contentPane.add(CMSL);
        Image CMS = new ImageIcon(getClass().getResource("/Course_management_System.png")).getImage();
        Image ScaledCMS = CMS.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        CMSL.setIcon(new ImageIcon(ScaledCMS));
        
        JButton btnCourses = new JButton("Courses");
        btnCourses.setBounds(50, 300, 150, 30);
        btnCourses.setForeground(new Color(0, 0, 0));
        btnCourses.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCourses.setBackground(new Color(255, 255, 255));
        contentPane.add(btnCourses);
        btnCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Courses frame
                Courses coursesFrame = new Courses();
                coursesFrame.setVisible(true);
                dispose(); // Close the current frame (tutors frame)
            }
        });
        
    }
    
    private void loadStudentCount() {
        try {
            // Establish a connection to the database
            Connection connection = Database.getConnection();
            if (connection != null) {
                // Prepare a SQL query to count the number of students
                String studentQuery = "SELECT COUNT(*) AS total_students FROM student";
                PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
                ResultSet studentResultSet = studentStatement.executeQuery();

                // Retrieve the count of students from the result set
                if (studentResultSet.next()) {
                    int totalStudents = studentResultSet.getInt("total_students");
                    // Set the count of students to the studentCount text area
                    studentCount.setText(String.valueOf(totalStudents));
                }

                // Prepare a SQL query to count the number of tutors
                String tutorQuery = "SELECT COUNT(*) AS total_tutors FROM teacher";
                PreparedStatement tutorStatement = connection.prepareStatement(tutorQuery);
                ResultSet tutorResultSet = tutorStatement.executeQuery();

                // Retrieve the count of tutors from the result set
                if (tutorResultSet.next()) {
                    int totalTutors = tutorResultSet.getInt("total_tutors");
                    // Set the count of tutors to the tutors_count text area
                    tutors_count.setText(String.valueOf(totalTutors));
                }

                // Prepare a SQL query to count the number of courses from the admin table
                String courseQuery = "SELECT COUNT(*) AS total_courses FROM course_s";
                PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
                ResultSet courseResultSet = courseStatement.executeQuery();

                // Retrieve the count of courses from the result set
                if (courseResultSet.next()) {
                    int totalCourses = courseResultSet.getInt("total_courses");
                    // Set the count of courses to the Course_Count text area
                    Course_Count.setText(String.valueOf(totalCourses));
                }

                // Close the result sets, statements, and connection
                studentResultSet.close();
                studentStatement.close();
                tutorResultSet.close();
                tutorStatement.close();
                courseResultSet.close();
                courseStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}