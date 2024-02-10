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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.CompoundBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Setting extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    protected JFrame frame;
    private JTextField old_passowrd;
    private JTextField new_passowrd;
    protected login_original loginOriginalInstance; // Declare an instance variable
    private JComboBox comboBox;



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Setting frame = new Setting(); // Pass the instance to the Setting constructor
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Setting(login_original loginOriginal) {
        this.loginOriginalInstance = loginOriginal;
    }
    
    
    public Setting() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700); // Adjust the size as needed
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 255, 230));
        contentPane.setBorder(new CompoundBorder());
        setContentPane(contentPane);
        frame = this;
        contentPane.setLayout(null);


        // Title Label
        JLabel titleLabel = new JLabel("University of Wolverhampton");
        titleLabel.setBounds(343, 0, 510, 47);
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
        contentPane.add(titleLabel);

        // Address Label
        JLabel addressLabel = new JLabel("Naxal, Bhagwati mandir, Kathmandu (014236548).");
        addressLabel.setBounds(441, 43, 321, 21);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(addressLabel);

        // View Courses Button
        JButton dashboard = new JButton("Dashboard");
        dashboard.setBackground(new Color(255, 255, 255));
        dashboard.setFont(new Font("Tahoma", Font.BOLD, 16));
        dashboard.setBounds(50, 250, 150, 30);
        dashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 frame.dispose();

                 // Open the login frame
                 Dashboard dashFrame = new Dashboard();
                 dashFrame.frame.setVisible(true);
            }
        });
        contentPane.add(dashboard);

        // Add Course Button
        JButton Tutor = new JButton("  Tutors");
        Tutor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		tutors tutoframe = new tutors();
        		tutoframe.setVisible(true);
        		dispose();
        	}
        });
        Tutor.setBackground(new Color(255, 255, 255));
        Tutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Tutor.setBounds(50, 350, 150, 30);
        Image teach = new ImageIcon(getClass().getResource("/teacher_icon.png")).getImage();
        Image Scaleteach = teach.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Tutor.setIcon(new ImageIcon(Scaleteach));
        contentPane.add(Tutor);

        // Directs to Student.java file
        JButton Student = new JButton("  Students");
        Student.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		students sframe = new students();
        		sframe.setVisible(true);
        		dispose();
        	}
        });
        Student.setBackground(new Color(255, 255, 255));
        Student.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Student.setBounds(50, 400, 150, 30);
        Image Simg = new ImageIcon(getClass().getResource("/Another.png")).getImage();
        Image scaleSimg = Simg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Student.setIcon(new ImageIcon(scaleSimg));
        
        contentPane.add(Student);

        // Directs to Mail.java file
        JButton Mail = new JButton("    Mail");
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
        Mail.setBounds(50, 450, 150, 30);
        Image mail = new ImageIcon(getClass().getResource("/gmail.png")).getImage();
        Image Scalemail = mail.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        Mail.setIcon(new ImageIcon(Scalemail));
       
        contentPane.add(Mail);

        // Directs to Setting.java file
        JButton Setting = new JButton("  Settings");
        Setting.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Setting Sframe = new Setting();
        		Sframe.setVisible(true);
        		dispose();
        	}
        });
        Setting.setBackground(new Color(255, 255, 168));
        Setting.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Setting.setBounds(50, 500, 150, 30);
        Image set = new ImageIcon(getClass().getResource("/Setting.png")).getImage();
        Image ScaleSet = set.getScaledInstance(25,25, Image.SCALE_SMOOTH);
        Setting.setIcon(new ImageIcon(ScaleSet));
        
        contentPane.add(Setting);

        
        JButton Logout = new JButton("  Logout");
        Logout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		login loginFrame = new login();
                loginFrame.frame.setVisible(true);
                dispose();
        	}
        });
        Logout.setBackground(new Color(255, 255, 255));
        Logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Logout.setBounds(50, 550, 150, 30);
        Image Log = new ImageIcon(getClass().getResource("/Logout.png")).getImage();
        Image ScaleLog = Log.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Logout.setIcon(new ImageIcon(ScaleLog));
       
        contentPane.add(Logout);
        
        JPanel panel = new JPanel();
        panel.setBounds(250, 250, 674, 400);
        contentPane.add(panel);
        panel.setLayout(null);
        
        old_passowrd = new JTextField();
        old_passowrd.setBounds(82, 155, 250, 20);
        panel.add(old_passowrd);
        old_passowrd.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Old Password");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(82, 129, 180, 25);
        panel.add(lblNewLabel);
        
        JLabel lblNewPassword = new JLabel("New Password");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewPassword.setBounds(391, 129, 180, 25);
        panel.add(lblNewPassword);
        
        new_passowrd = new JTextField();
        new_passowrd.setColumns(10);
        new_passowrd.setBounds(391, 155, 250, 20);
        panel.add(new_passowrd);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPassword = old_passowrd.getText();
                String newPassword = new_passowrd.getText();

                // Check if loginOriginalInstance is null
                if (loginOriginalInstance != null) {
                    String username_of_student = loginOriginalInstance.getEnteredUsername(); // Retrieve username
                    compareAndSetPassword(oldPassword, newPassword, username_of_student);
                } else {
                    // Handle the case where loginOriginalInstance is null
                    JOptionPane.showMessageDialog(frame, "Login information is not available.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        submit.setFont(new Font("Tahoma", Font.PLAIN, 17));
        submit.setBounds(291, 272, 100, 27);
        panel.add(submit);
        Image std = new ImageIcon(getClass().getResource("/Student.png")).getImage();
        Image scaledstd = std.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image Course_icon = new ImageIcon(getClass().getResource("/course.png")).getImage();
        Image scaledCIcon = Course_icon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image cimg = new ImageIcon(getClass().getResource("/Student.png")).getImage();
        Image scaledcimg = cimg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image Timg = new ImageIcon(getClass().getResource("/Tutors.png")).getImage();
        Image ScaleTimg = Timg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        
        JLabel CMSL = new JLabel("");
        CMSL.setBounds(71, 75, 113, 135);
        contentPane.add(CMSL);
        Image CMS = new ImageIcon(getClass().getResource("/Course_management_System.png")).getImage();
        Image ScaledCMS = CMS.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        CMSL.setIcon(new ImageIcon(ScaledCMS));
        
        JButton btnCourses = new JButton("Courses");
        btnCourses.setForeground(new Color(0, 0, 0));
        btnCourses.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCourses.setBackground(new Color(255, 255, 255));
        btnCourses.setBounds(50, 300, 150, 30);
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
    
        // Other class variables and methods...

    public void compareAndSetPassword(String oldPassword, String newPassword, String username) {
        if (loginOriginalInstance != null) {
            // Retrieve the stored username from the login_original instance
            String storedUsername = loginOriginalInstance.getEnteredUsername();
            String storedPassword = loginOriginalInstance.getEnteredPassword();
            String selectedRole = (String) comboBox.getSelectedItem();

            if (storedUsername != null && storedPassword != null && storedUsername.equals(username) && storedPassword.equals(oldPassword)) {
                // Passwords match, set the new password in the database
                String url = "jdbc:mysql://localhost:3306/final_assessment";
                String dbUsername = "root";
                String dbPassword = "";

                try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                    String tableName = ""; // Variable to hold the table name
                    // Determine the table name based on the selected role
                    switch (selectedRole) {
                        case "Student":
                            tableName = "student";
                            break;
                        case "Teacher":
                            tableName = "teacher";
                            break;
                        case "Administrator":
                            tableName = "administrator";
                            break;
                        default:
                            // Handle if the role is not recognized
                            break;
                    }

                    String sql = "UPDATE " + tableName + " SET password = ? WHERE username = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, newPassword);
                    statement.setString(2, username);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(frame, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Database error.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Username or old password is incorrect
                JOptionPane.showMessageDialog(frame, "Username or old password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Login information is not available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    
