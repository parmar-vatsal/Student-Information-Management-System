
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Student Information System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginPanel.add(new JLabel(""));
        loginPanel.add(titleLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                if (isLoginValid(username, new String(password))) {
                    loginFrame.dispose();
                    showMainScreen.main(null);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login failed. Try again or register.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the registration screen
                loginFrame.dispose();
                RegistrationForm.main(null);
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    private static boolean isLoginValid(String username, String password) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            return false; // Unable to connect to the database
        }

        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true; // User exists in the database and provided password is correct
            } else {
                return false; // User does not exist or password is incorrect
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Database error
        }
    }
}
