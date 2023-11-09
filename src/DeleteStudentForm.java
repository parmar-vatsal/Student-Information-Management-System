
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudentForm extends JFrame {

    private JTextField enrollmentField; // Change the field name

    public DeleteStudentForm() {
        setTitle("Delete Student");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel enrollmentLabel = new JLabel("Enrollment Number:"); // Change the label
        enrollmentField = new JTextField(20);

        JButton deleteButton = new JButton("Delete Student");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(enrollmentLabel, constraints); // Change label here

        constraints.gridx = 1;
        panel.add(enrollmentField, constraints); // Change field here

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(deleteButton, constraints);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        add(panel);
    }

    private void deleteStudent() {
        // Retrieve data from the enrollment field
        String enrollmentNumber = enrollmentField.getText(); // Change the variable name

        // Perform data validation here
        // Establish a database connection using DatabaseConnection.getConnection()
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Create a SQL DELETE statement and execute it using a PreparedStatement
            String deleteQuery = "DELETE FROM students WHERE enrollment_number = ?"; // Change the column name
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, enrollmentNumber); // Change the variable name

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                dispose(); // Close the form
            } else {
                JOptionPane.showMessageDialog(this, "Student not found or delete failed.");
            }

            // Close the PreparedStatement and Connection
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteStudentForm form = new DeleteStudentForm();
            form.setVisible(true);
        });
    }
}
