package packages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost/final_assessment";
    static final String Username = "root";
    static final String Password = "";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, Username, Password);
            System.out.print("Connecting to database....");
            if (connection != null) {
                System.out.println("Database connected successfully!");
            }
            return connection;
        } catch (SQLException exc) {
            System.out.println("Something went wrong while connecting to the database.");
            exc.printStackTrace();
        }
        return null;
    }

    // Method to check if a user exists in the Student table
    public boolean checkStudent(String email, String password) {
        return checkUserInTable(email, password, "Student");
    }

    // Method to check if a user exists in the Teacher table
    public boolean checkTeacher(String email, String password) {
        return checkUserInTable(email, password, "Teacher");
    }

    // Method to check if a user exists in the Administrator table
    public boolean checkAdministrator(String username, String password) {
        return checkUserInTable(username, password, "Administrator");
    }
    
       
    
    

    // Generic method to check if a user exists in a specified table
    private boolean checkUserInTable(String email, String password, String table) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean userExists = false;

        try {
            connection = getConnection();
            String query = "SELECT COUNT(*) FROM " + table + " WHERE email = ? AND passwrd = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                userExists = count > 0; // If count > 0, user exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userExists;
    }
    
 // Retrieving all courses from the database
    public List<Courses> searchCourses(String keyword) {
        List<Courses> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM courses WHERE name LIKE ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                String result = resultSet.getString("result");
                String overall = resultSet.getString("overall");
                Courses c = new Courses();
                courses.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }
    public boolean isAdmin(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isAdmin = false;
        

        try {
            connection = getConnection();
            String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                isAdmin = count > 0; // If count > 0, user exists as admin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAdmin;
    }
    

}
