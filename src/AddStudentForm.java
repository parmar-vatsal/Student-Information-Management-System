import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentForm extends JFrame {

    private JTextField nameField, gradeField, mobileNumberField, ageField, enrollmentField, birthDateField, emailField;

    public AddStudentForm() {
        setTitle("Add Student");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        JLabel enrollmentLabel = new JLabel("Enrollment Number:");
        enrollmentField = new JTextField(20);

        JLabel birthDateLabel = new JLabel("Birth Date:");
        birthDateField = new JTextField(20);  // Use a regular text field for the date

        JLabel emailLabel = new JLabel("Email Address:");
        emailField = new JTextField(20);

        JButton addButton = new JButton("Add Student");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(gradeLabel, constraints);

        constraints.gridx = 1;
        panel.add(gradeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(mobileNumberLabel, constraints);

        constraints.gridx = 1;
        panel.add(mobileNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(ageLabel, constraints);

        constraints.gridx = 1;
        panel.add(ageField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(enrollmentLabel, constraints);

        constraints.gridx = 1;
        panel.add(enrollmentField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(birthDateLabel, constraints);

        constraints.gridx = 1;
        panel.add(birthDateField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(emailLabel, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        add(panel);
    }

    private void addStudent() {
        // Retrieve data from text fields
        String name = nameField.getText();
        String grade = gradeField.getText();
        String mobileNumber = mobileNumberField.getText();
        String age = ageField.getText();
        String enrollment = enrollmentField.getText();
        String birthDate = birthDateField.getText();  // Retrieve date as a string
        String email = emailField.getText();

        // Perform data validation here

        // Establish a database connection using DatabaseConnection.getConnection()
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Create a SQL INSERT statement and execute it using a PreparedStatement
            String insertQuery = "INSERT INTO students (name, grade, mobile_number, age, enrollment_number, birth_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, enrollment);
            preparedStatement.setString(6, birthDate);
            preparedStatement.setString(7, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                dispose(); // Close the form
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student.");
            }

            // Close the PreparedStatement and Connection
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddStudentForm form = new AddStudentForm();
            form.setVisible(true);
        });
    }
}
