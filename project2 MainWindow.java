package deadlinemate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private DeadlineManager deadlineManager;
    private DeadlineTableModel tableModel;
    private JTable deadlineTable;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public MainWindow(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        deadlineManager = new DeadlineManager();
        tableModel = new DeadlineTableModel();
        deadlineTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(deadlineTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Deadline");
        editButton = new JButton("Edit Deadline");
        deleteButton = new JButton("Delete Deadline");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeadlineFormDialog dialog = new DeadlineFormDialog(MainWindow.this);
                dialog.setVisible(true);
                Deadline newDeadline = dialog.getDeadline();
                if (newDeadline != null) {
                    deadlineManager.addDeadline(newDeadline);
                    updateTable();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deadlineTable.getSelectedRow();
                if (selectedRow != -1) {
                    Deadline selectedDeadline = tableModel.getDeadlineAt(selectedRow);
                    DeadlineFormDialog dialog = new DeadlineFormDialog(MainWindow.this);
                    dialog.setDeadline(selectedDeadline);
                    dialog.setVisible(true);
                    Deadline updatedDeadline = dialog.getDeadline();
                    if (updatedDeadline != null) {
                        // In a real application, you might update the existing deadline object
                        // or replace it. For simplicity, we'll just update the table.
                        updateTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please select a deadline to edit.", "No Deadline Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deadlineTable.getSelectedRow();
                if (selectedRow != -1) {
                    Deadline selectedDeadline = tableModel.getDeadlineAt(selectedRow);
                    deadlineManager.removeDeadline(selectedDeadline);
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please select a deadline to delete.", "No Deadline Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        updateTable(); // Initial table load
    }

    private void updateTable() {
        tableModel.setDeadlines(deadlineManager.getAllDeadlines());
    }
}
