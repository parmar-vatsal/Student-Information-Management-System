import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class DisplayStudentsForm extends JFrame {

    private JTable table;

    public DisplayStudentsForm() {
        setTitle("Display Students");
        setSize(1600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a table model to display student data
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Grade");
        tableModel.addColumn("Mobile Number");
        tableModel.addColumn("Age");
        tableModel.addColumn("Enrollment Number");
        tableModel.addColumn("Birth Date");
        tableModel.addColumn("Email");

        // Populate the table with student data from the database
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT name, grade, mobile_number, age, enrollment_number, birth_date, email FROM students";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String grade = resultSet.getString("grade");
                String mobileNumber = resultSet.getString("mobile_number");
                String age = resultSet.getString("age");
                String enrollmentNumber = resultSet.getString("enrollment_number");
                String birthDate = resultSet.getString("birth_date");
                String email = resultSet.getString("email");

                tableModel.addRow(new Object[]{name, grade, mobileNumber, age, enrollmentNumber, birthDate, email});
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayStudentsForm form = new DisplayStudentsForm();
            form.setVisible(true);
        });
    }
}
