import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStudentForm extends JFrame {
    private JTextField enrollmentField, nameField, gradeField, mobileNumberField, ageField, birthDateField, emailField;

    public UpdateStudentForm() {
        setTitle("Update Student");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel enrollmentLabel = new JLabel("Enrollment Number:");
        enrollmentField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel gradeLabel = new JLabel("New Grade:");
        gradeField = new JTextField(20);

        JLabel mobileNumberLabel = new JLabel("New Mobile Number:");
        mobileNumberField = new JTextField(20);

        JLabel ageLabel = new JLabel("New Age:");
        ageField = new JTextField(20);

        JLabel birthDateLabel = new JLabel("New Birth Date:");
        birthDateField = new JTextField(20);

        JLabel emailLabel = new JLabel("New Email Address:");
        emailField = new JTextField(20);

        JButton fetchButton = new JButton("Fetch Student");
        JButton updateButton = new JButton("Update Student");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(enrollmentLabel, constraints);

        constraints.gridx = 1;
        panel.add(enrollmentField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(fetchButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(gradeLabel, constraints);

        constraints.gridx = 1;
        panel.add(gradeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(mobileNumberLabel, constraints);

        constraints.gridx = 1;
        panel.add(mobileNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(ageLabel, constraints);

        constraints.gridx = 1;
        panel.add(ageField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(birthDateLabel, constraints);

        constraints.gridx = 1;
        panel.add(birthDateField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(emailLabel, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        panel.add(updateButton, constraints);

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        add(panel);
    }

    private void fetchStudent() {
        String enrollmentNumber = enrollmentField.getText();

        try {
            Connection connection = DatabaseConnection.getConnection();
            String selectQuery = "SELECT name, grade, mobile_number, age, birth_date, email FROM students WHERE enrollment_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, enrollmentNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve and set the existing data in the text fields
                nameField.setText(resultSet.getString("name"));
                gradeField.setText(resultSet.getString("grade"));
                mobileNumberField.setText(resultSet.getString("mobile_number"));
                ageField.setText(resultSet.getString("age"));
                birthDateField.setText(resultSet.getString("birth_date"));
                emailField.setText(resultSet.getString("email"));
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error.");
        }
    }

    private void updateStudent() {
        String enrollmentNumber = enrollmentField.getText();
        String newName = nameField.getText();
        String newGrade = gradeField.getText();
        String newMobileNumber = mobileNumberField.getText();
        String newAge = ageField.getText();
        String newBirthDate = birthDateField.getText();
        String newEmail = emailField.getText();

        // Perform data validation here

        try {
            Connection connection = DatabaseConnection.getConnection();

            String updateQuery = "UPDATE students SET name = ?, grade = ?, mobile_number = ?, age = ?, birth_date = ?, email = ? WHERE enrollment_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newGrade);
            preparedStatement.setString(3, newMobileNumber);
            preparedStatement.setString(4, newAge);
            preparedStatement.setString(5, newBirthDate);
            preparedStatement.setString(6, newEmail);
            preparedStatement.setString(7, enrollmentNumber);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found or update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateStudentForm form = new UpdateStudentForm();
            form.setVisible(true);
        });
    }
}
