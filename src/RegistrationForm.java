import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField studentNameField;

    public RegistrationForm() {
        setTitle("Registration");
        setSize(400, 250); // Slightly larger size to accommodate the additional field
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField();

        JButton registerButton = new JButton("Register");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(studentNameLabel);
        panel.add(studentNameField);
        panel.add(new JPanel());
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        add(panel);
    }

    private void registerUser() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String studentName = studentNameField.getText();

        // Perform data validation here

        try {
            Connection connection = DatabaseConnection.getConnection();
            String insertQuery = "INSERT INTO users (username, password, student_name) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, studentName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Registration successful.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistrationForm form = new RegistrationForm();
            form.setVisible(true);
        });
    }
}
