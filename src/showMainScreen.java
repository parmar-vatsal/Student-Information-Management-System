import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showMainScreen {
    public static void main(String[] args) {
        // Create and display the Home Screen
        JFrame frame = new JFrame("Student Information System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton displayButton = new JButton("Display Students");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(displayButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Add Student form
                AddStudentForm.main(null);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Update Student form
                UpdateStudentForm.main(null);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Delete Student form
                DeleteStudentForm.main(null);
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayStudentsForm.main(null);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
