package packages;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;


public class login_original {

    protected JFrame frame;
    private JTextField email_text;
    private JLabel lblEnterYourPassword;
    private JButton Login_button;
    private JPanel panel;
    private JLabel lblNewLabel_1;
    private Database database;    //Declare a variable to hold the Database instance
    private JTextPane Result;
    private JComboBox comboBox;
    private JLabel Choose_STA;
    private String enteredUsername; // New field for storing username
    private String enteredPassword;
    private JPasswordField Password_text;



    public login_original() {
        initialize();
        database = new Database();// Initialize the Database instance
    }

    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login_original window = new login_original();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
    private void initialize() {
    	
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(243, 243, 243));
        frame.setBounds(100, 100, 449, 508);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter your Email");
        lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        lblNewLabel.setBounds(91, 196, 169, 33);
        frame.getContentPane().add(lblNewLabel);

        email_text = new JTextField();
        email_text.setForeground(new Color(0, 64, 128));
        email_text.setBackground(new Color(233, 233, 233));
        email_text.setFont(new Font("Tahoma", Font.PLAIN, 13));
        email_text.setBounds(91, 225, 261, 27);
        frame.getContentPane().add(email_text);
        email_text.setColumns(10);
//        System.out.print(enteredpassword);
        
        lblEnterYourPassword = new JLabel("Enter your Password");
        lblEnterYourPassword.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        lblEnterYourPassword.setBounds(91, 283, 169, 20);
        frame.getContentPane().add(lblEnterYourPassword);

        Login_button = new JButton("Submit");
        Login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print(enteredpassword);

                String email_t = email_text.getText();
                enteredPassword = Password_text.getText();
                setEnteredCredentials(email_t, enteredPassword);

             // Debugging: Check if credentials are set correctly
             System.out.println("Username: " + getEnteredUsername());
             System.out.println("Password: " + getEnteredPassword());
                String selectedRole = (String) comboBox.getSelectedItem();

                // Check the selected role and corresponding table
                boolean userExists = false;
                switch (selectedRole) {
                    case "Student":
                        userExists = database.checkStudent(email_t, enteredPassword);
                        break;
                    case "Teacher":
                        userExists = database.checkTeacher(email_t, enteredPassword);
                        break;
                    case "Administrator":
                        userExists = database.checkAdministrator(email_t, enteredPassword);
                        break;
                    default:
                        // Handle if the role is not recognized
                        break;
                }

                if (userExists) {
                    // Close the current frame

                    // Open the appropriate dashboard based on the selected role
                    switch (selectedRole) {
                        case "Student":
                        case "Teacher":
                            Dashboard dashboardFrame = new Dashboard();
                            dashboardFrame.setVisible(true);
                            break;
                        case "Administrator":
                            admin_Dashboard adminDashboardFrame = new admin_Dashboard();
                            adminDashboardFrame.setVisible(true);
                            break;
                    }
                    frame.dispose();
                    // Display a success message
                    JOptionPane.showMessageDialog(frame, "You have successfully logged in!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Display an error message if the user does not exist in the selected role
                    Result.setText("Username or Password Does Not Match");
                }
            }
        });


        Login_button.setFont(new Font("Sylfaen", Font.PLAIN, 18));
        Login_button.setBounds(222, 361, 89, 27);
        frame.getContentPane().add(Login_button);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 204));
        panel.setBounds(10, 11, 414, 45);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        lblNewLabel_1 = new JLabel("This is the login panel");
        lblNewLabel_1.setBackground(new Color(255, 255, 0));
        lblNewLabel_1.setFont(new Font("Sylfaen", Font.BOLD, 20));
        lblNewLabel_1.setBounds(87, 0, 210, 45);
        panel.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Go Back");
        btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        btnNewButton.setBounds(117, 362, 89, 25);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                frame.dispose();

                // Open the login frame
                login loginFrame = new login();
                loginFrame.frame.setVisible(true);
            }
        });

        Result = new JTextPane();
        Result.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 18));
        Result.setBackground(new Color(243, 243, 243));
        Result.setBounds(36, 413, 364, 45);
        frame.getContentPane().add(Result);

        String[] roles = {"Default","Student", "Teacher", "Administrator"};
        comboBox = new JComboBox(roles);
        comboBox.setBounds(92, 152, 260, 22);
        frame.getContentPane().add(comboBox);
        

    	

        
        Choose_STA = new JLabel("Choose");
        Choose_STA.setHorizontalAlignment(SwingConstants.LEFT);
        Choose_STA.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        Choose_STA.setBounds(91, 135, 169, 20);
        frame.getContentPane().add(Choose_STA);
        
        Password_text = new JPasswordField();
        Password_text.setBackground(new Color(233, 233, 233));
        Password_text.setBounds(91, 299, 261, 27);
        frame.getContentPane().add(Password_text);

        // Action Listener for "Go Back" Button
        
    }

    public JFrame getFrame() {
        return frame;
    }
    
    protected void setEnteredCredentials(String username, String password) {
        this.enteredUsername = username;
        this.enteredPassword = password;
        System.out.println("Entered credentials set: Username - " + username + ", Password - " + password); // Debugging
    }


    protected String getEnteredUsername() {
        System.out.println("Entered username retrieved: " + enteredUsername); // Debugging
        return enteredUsername;
    }

    public String getEnteredPassword() {
        System.out.println("Entered password retrieved: " + enteredPassword); // Debugging
        return enteredPassword;
    }  
}

    
    

    
    


