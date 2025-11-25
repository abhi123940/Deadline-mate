package deadlinemate;

import javax.swing.SwingUtilities;

public class DeadlineMateApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow("Deadline Mate");
            mainWindow.setVisible(true);
        });
    }
}
