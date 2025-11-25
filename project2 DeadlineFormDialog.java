package deadlinemate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeadlineFormDialog extends JDialog {
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JButton saveButton;
    private JButton cancelButton;
    private Deadline deadline;

    public DeadlineFormDialog(Frame owner) {
        super(owner, "Deadline Details", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(owner);

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane);
        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to save deadline
                deadline = new Deadline(nameField.getText(), descriptionArea.getText());
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deadline = null; // Indicate cancellation
                setVisible(false);
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
        if (deadline != null) {
            nameField.setText(deadline.getName());
            descriptionArea.setText(deadline.getDescription());
        } else {
            nameField.setText("");
            descriptionArea.setText("");
        }
    }
}
