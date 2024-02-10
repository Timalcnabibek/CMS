package packages;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.CompoundBorder;

import javax.swing.JScrollPane;



public class admin_student extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    protected JFrame frame;
    private JTextField Search_bar;
    protected Database database;
    protected login_original loginOriginal;
    private DefaultTableModel tableModel;
    private JTable table;


    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	admin_student frame = new admin_student();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); // Center the frame on the screen

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public admin_student() {
        tableModel = new DefaultTableModel();
        database = new Database();
        loginOriginal = new login_original();
        
        
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

        // Dashboard Button
        JButton dashboard = new JButton("Dashboard");
        dashboard.setBounds(50, 250, 150, 30);
        dashboard.setBackground(new Color(255, 255, 255));
        dashboard.setFont(new Font("Tahoma", Font.BOLD, 16));
        dashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 frame.dispose();

                 // Open the login frame
                 Dashboard dashFrame = new Dashboard();
                 dashFrame.frame.setVisible(true);
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
                
                admin_tutors tutorFrame = new admin_tutors();
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
        Student.setBackground(new Color(255, 255, 168));
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
        Logout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		login loginFrame = new login();
                loginFrame.frame.setVisible(true);
                dispose();
        	}
        });
        Logout.setBounds(50, 550, 150, 30);
        Logout.setBackground(new Color(255, 255, 255));
        Logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Image Log = new ImageIcon(getClass().getResource("/Logout.png")).getImage();
        Image ScaleLog = Log.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Logout.setIcon(new ImageIcon(ScaleLog));
       
        contentPane.add(Logout);
        
        JLabel CMSL = new JLabel("");
        CMSL.setBounds(70, 75, 113, 135);
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
                Courses coursesFrame = new Courses();
                coursesFrame.setVisible(true);
                dispose();
            }
        });
    
        
        Search_bar = new JTextField();
        Search_bar.setBounds(250, 210, 270, 20);
        contentPane.add(Search_bar);
        Search_bar.setColumns(10);
        
        JButton ADD_TUTORS = new JButton("Students Progress");
        ADD_TUTORS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the Result class
                Result resultFrame = new Result();
                
                // Set the visibility of the Result frame to true
                resultFrame.frame.setVisible(true);
            }
        });

        ADD_TUTORS.setBounds(850, 206, 140, 25);
        ADD_TUTORS.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(ADD_TUTORS);
        
        JButton Edit_course = new JButton("Edit Students");
        Edit_course.setBounds(733, 206, 110, 25);
        Edit_course.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(Edit_course); 
        
        JButton Delete_Course = new JButton("Delete Students");
        Delete_Course.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		delete_student tutframe = new delete_student();
        		tutframe.setVisible(true);
        	}
        });
        Delete_Course.setBounds(605, 206, 125, 25);
        Delete_Course.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(Delete_Course);
        
                
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Username", "Email", "Phone number", "course"}
            );

        table = new JTable(new DefaultTableModel(
        	    new Object[][] {
        	        {null, null, null, null},
        	        {null, null, null, null},
        	        {null, null, null, null},
        	        {null, null, null, null},
        	        {null, null, null, null},
        	    },
        	    new String[] {
        	        "Username", "Email", "Phone number","course"
        	    }
        	));
        table.setFont(new Font("Arial", Font.PLAIN, 16));


        	// Now you add the table model to your JTable
        	table.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        			"Username", "Email", "Phone number", "course"
        		}
        	));
            table.setModel(tableModel); // Set the table model

        	// Set the row height for the table
        	table.setRowHeight(30);

        	JScrollPane scrollPane = new JScrollPane(table);
        	scrollPane.setBounds(250, 250, 740, 380);
        	contentPane.add(scrollPane);
        	getContentPane().add(scrollPane, BorderLayout.CENTER);

        	fetchTeacherData(); 
        
        JLabel lblNewLabel_1_1_3 = new JLabel("Search for Tutors");
        lblNewLabel_1_1_3.setBounds(250, 185, 180, 20);
        lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(lblNewLabel_1_1_3);
        
        JButton Delete_Course_1 = new JButton("Search");
        Delete_Course_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String searchText = Search_bar.getText().trim();
                filterTable(searchText);
        	}
        });
        Delete_Course_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Delete_Course_1.setBounds(515, 208, 78, 23);
        contentPane.add(Delete_Course_1);

    }
    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive filter
        }
    }
    
    private void fetchTeacherData() {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/final_assessment";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT username, email, phone,course FROM student";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Clear existing data from the table model
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("username"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("phone"));
                row.add(resultSet.getString("course"));

                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception details to console
        }
    }
}