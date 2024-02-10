package packages;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPanel;

public class login {
    protected JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private Database database;
    private login_original loginOriginal; // Add a reference to login_original
    private JComboBox<String> comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
                    window.frame.setVisible(true);
                    window.frame.setLocationRelativeTo(null); // Center the frame on the screen

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    

    
    /**
     * Create the application.
     */
    public login() {
        initialize();
        frame.setVisible(true); // Set the frame visible after initialization
        database = new Database(); 
        loginOriginal = new login_original(); // Initialize login_original
        // Initialize the Database instance
    }
    public boolean validatePassword(String password) {
        // Define the regex pattern for password validation
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,15}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, otherwise return false
        return matcher.matches();
    }
    
    public boolean validateEmail(String email) {
        // Define the regex pattern for email validation
        String EMAIL_PATTERN = "^[^@]+@[^@]+$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise return false
        return matcher.matches() && email.indexOf('@') >= 3 && email.indexOf('@') <= email.length() - 5;
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 563,660 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Your User Name");
        lblNewLabel.setBounds(143, 86, 153, 23);
        lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(143, 108, 260, 23);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(143, 152, 153, 23);
        lblEmail.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblEmail);
        
        textField_1 = new JTextField();
        textField_1.setBounds(143, 170, 260, 23);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textField_1.setColumns(10);
        frame.getContentPane().add(textField_1);
        
        JLabel lblEnterPassword = new JLabel("Enter password");
        lblEnterPassword.setBounds(143, 215, 153, 23);
        lblEnterPassword.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblEnterPassword);
        
        textField_2 = new JTextField();
        textField_2.setBounds(143, 233, 260, 23);
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textField_2.setColumns(10);
        frame.getContentPane().add(textField_2);
        
        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(143, 278, 153, 23);
        lblPhoneNumber.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblPhoneNumber);
        
        textField_3 = new JTextField();
        textField_3.setBounds(143, 295, 260, 23);
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textField_3.setColumns(10);
        frame.getContentPane().add(textField_3);
        
        
        String[] roles = {"Select user Mode","Student", "Teacher", "Administrator"};
        JComboBox<String> comboBox = new JComboBox<String>(roles);
        comboBox.setBounds(143, 362, 260, 22);
        frame.getContentPane().add(comboBox);
        
        JLabel lblPhoneNumber_1 = new JLabel("Choose ");
        lblPhoneNumber_1.setBounds(143, 342, 153, 23);
        lblPhoneNumber_1.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblPhoneNumber_1);
        
        JButton Login = new JButton("Login");
        Login.setBounds(215, 520, 89, 25);
        frame.getContentPane().add(Login);
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here you can add your login logic

                // After login logic, if login is successful, open the login_original frame
                login_original loginOriginal = new login_original();
                loginOriginal.getFrame().setVisible(true);
                frame.dispose(); // Close the current frame
            }
        });
        Login.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(Login);
        
        JButton Create = new JButton("Create");
        Create.setBounds(200, 475, 120, 25);
        Create.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(Create);
        
        String[] role = {"Default","BBA", "BSc(Computer Hons)", "MBA"};
        JComboBox comboBox_1 = new JComboBox(role);
        comboBox_1.setBounds(143, 431, 260, 22);
        frame.getContentPane().add(comboBox_1);
        
        JLabel lblPhoneNumber_1_1 = new JLabel("Select your course");
        lblPhoneNumber_1_1.setBounds(143, 409, 153, 23);
        lblPhoneNumber_1_1.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        frame.getContentPane().add(lblPhoneNumber_1_1);
        
//        comboBox.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String selectedRole = (String) comboBox.getSelectedItem();
//
//                // If the selected role is "Student", show the course selection
//                if (selectedRole.equals("Student")) {
//                    comboBox_1.setVisible(true);
//                    lblPhoneNumber_1_1.setVisible(true);
//                } else {
//                    // If the selected role is not "Student", hide the course selection
//                    comboBox_1.setVisible(false);
//                    lblPhoneNumber_1_1.setVisible(false);
//                }
//            }
//        });
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(98, 83, 35, 30);
        Image img = new ImageIcon(getClass().getResource("/profile.png")).getImage();
        Image scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
        lblNewLabel_1.setIcon(new ImageIcon(scaledImg));
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel Passwrd = new JLabel("");
        Passwrd.setBounds(103, 214, 35, 30);
        Image imga = new ImageIcon(getClass().getResource("/password1.png")).getImage();
        Image scaledImga = imga.getScaledInstance(33, 25, Image.SCALE_SMOOTH);
        Passwrd.setIcon(new ImageIcon(scaledImga));
        frame.getContentPane().add(Passwrd);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(98, 270, 35, 30);
        Image imgae = new ImageIcon(getClass().getResource("/phone.png")).getImage();
        Image scaledImgae = imgae.getScaledInstance(33, 25, Image.SCALE_SMOOTH);
        lblNewLabel_2.setIcon(new ImageIcon(scaledImgae));
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(98, 154, 35, 30);
        Image imag = new ImageIcon(getClass().getResource("/notification.png")).getImage();
        Image scaledImage = imag.getScaledInstance(33, 25, Image.SCALE_SMOOTH);
        lblNewLabel_3.setIcon(new ImageIcon(scaledImage));
        frame.getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(103, 340, 30, 30);
        Image imag5 = new ImageIcon(getClass().getResource("/option.png")).getImage();
        Image scaledImage5 = imag5.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lblNewLabel_4.setIcon(new ImageIcon(scaledImage5));
        frame.getContentPane().add(lblNewLabel_4);
        
        JTextPane showresult = new JTextPane();
        showresult.setBounds(50, 550, 450, 70);
        showresult.setForeground(new Color(255, 94, 94));
        showresult.setFont(new Font("Tahoma", Font.BOLD, 20));
        showresult.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(showresult);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setBounds(388, 111, 64, 64);
        ImageIcon musicIcon = new ImageIcon(getClass().getResource("/music.png"));
        lblNewLabel_5.setIcon(musicIcon);
        frame.getContentPane().add(lblNewLabel_5);
        ImageIcon waveIcon = new ImageIcon(getClass().getResource("/wave.png"));
        waveIcon = new ImageIcon(waveIcon.getImage().getScaledInstance(120, 64, Image.SCALE_SMOOTH));
        
        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setBounds(207, 232, 89, 86);
        ImageIcon wave2Icon = new ImageIcon(getClass().getResource("/wave2.png"));
        wave2Icon = new ImageIcon(wave2Icon.getImage().getScaledInstance(70, 64, Image.SCALE_SMOOTH));
        lblNewLabel_7.setIcon(wave2Icon);
        frame.getContentPane().add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("To login, click here ->");
        lblNewLabel_8.setBounds(80, 520, 140, 20);
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_8.setForeground(new Color(0, 0, 255));
        frame.getContentPane().add(lblNewLabel_8);
        
        JPanel panel = new JPanel();
        panel.setBounds(44, 23, 458, 40);
        panel.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_9 = new JLabel("Create An Account");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_9.setBounds(109, 0, 188, 40);
        panel.add(lblNewLabel_9);
        
        JLabel lblNewLabel_4_1 = new JLabel("");
        lblNewLabel_4_1.setBounds(103, 411, 30, 30);
        frame.getContentPane().add(lblNewLabel_4_1);
        ImageIcon courseIcon = new ImageIcon(getClass().getResource("/homework.png"));
        wave2Icon = new ImageIcon(courseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        lblNewLabel_4_1.setIcon(wave2Icon);
        
        JLabel dragon_icon = new JLabel("");
        dragon_icon.setBounds(217, 249, 333, 272);
        frame.getContentPane().add(dragon_icon);
        ImageIcon dIcon = new ImageIcon(getClass().getResource("/dragon.png"));
        wave2Icon = new ImageIcon(dIcon.getImage().getScaledInstance(333, 272, Image.SCALE_SMOOTH));
        dragon_icon.setIcon(wave2Icon);

        
      //Action Listener for Create Button
        Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String email = textField_1.getText();
                String password = textField_2.getText();
                String phone = textField_3.getText();
                String role = (String) comboBox.getSelectedItem(); // Get selected role from JComboBox
                String course = (String) comboBox_1.getSelectedItem(); // Get selected course from JComboBox
                
                if(password.length() > 15) {
                    showresult.setText("The password must not be longer than 15 letters!!");
                    return;
                }
                if(password.length() < 8) {
                    showresult.setText("The password must not be shorter than 8 letters!!");
                    return;
                }

                // Validate password format
                if (!validatePassword(password)) {
                	showresult.setText("The password must contain at least one capital letter, number and special character!!!");
                	return; // Exit the method without further processing
                }
                if(!validateEmail(email)) {
                	showresult.setText("The email is not correctly entered!!");
                	return;
                }

                if (role.equals("Choose user Mode")) {
                	showresult.setText("Please choose a role.");
                    return; // Exit the method without further processing
                }
                // Insert into the database based on the selected role
                try {
                    Connection connection = database.getConnection();
                    if (connection != null) {
                        String query;
                        if (role.equals("Teacher")) {
                            query = "INSERT INTO teacher (username, email, passwrd, number, course) VALUES (?, ?, ?, ?, ?)";
                        } else if (role.equals("Administrator")) {
                            query = "INSERT INTO administrator (username, email, passwrd, number, course) VALUES (?, ?, ?, ?, ?)";
                        } else {
                            query = "INSERT INTO student (username, email, passwrd, phone, course) VALUES (?, ?, ?, ?, ?)";
                        }
                        if (role.equals("Teacher")) {
                            // Open the Dashboard for teacher
                            Dashboard dashboardFrame = new Dashboard();
                            dashboardFrame.setVisible(true);
                            frame.dispose(); // Close the current frame
                        } else if (role.equals("Administrator")) {
                            // Open the Admin Dashboard for administrator
                            admin_Dashboard adminDashboardFrame = new admin_Dashboard();
                            adminDashboardFrame.setVisible(true);
                            frame.dispose(); // Close the current frame
                        } else {
                            // Open the Dashboard for student
                            Dashboard dashboardFrame = new Dashboard();
                            dashboardFrame.setVisible(true);
                            frame.dispose(); // Close the current frame
                        }


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, password);
                        preparedStatement.setString(4, phone);
                        preparedStatement.setString(5, course);
                        preparedStatement.executeUpdate();
                        
                        System.out.println("User created successfully!");

                        // Clear fields after successful insert
                        textField.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");

                        preparedStatement.close();

                        // After creating user, navigate to Dashboard frame
                        
                    }
                    connection.close();
                } catch (SQLException exc) {
                    System.out.println("Something went wrong while inserting user into the database.");
                    System.out.println(exc);
                }
            }
        });

    }
}
