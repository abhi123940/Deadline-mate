package deadlinemate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:sqlite:deadlinemate.db";

    public DatabaseManager() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    private void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS deadlines (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT NOT NULL," +
                         "description TEXT)";
            stmt.execute(sql);
            System.out.println("Deadlines table ensured to exist.");
        }
    }

    public void saveDeadline(Deadline deadline) throws SQLException {
        String sql = "INSERT INTO deadlines(name, description) VALUES(?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deadline.getName());
            pstmt.setString(2, deadline.getDescription());
            pstmt.executeUpdate();
        }
    }

    public List<Deadline> getAllDeadlines() throws SQLException {
        List<Deadline> deadlines = new ArrayList<>();
        String sql = "SELECT id, name, description FROM deadlines";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Deadline deadline = new Deadline(
                    rs.getString("name"),
                    rs.getString("description")
                );
                deadlines.add(deadline);
            }
        }
        return deadlines;
    }

    public void deleteDeadline(Deadline deadline) throws SQLException {
        String sql = "DELETE FROM deadlines WHERE name = ? AND description = ?"; 
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deadline.getName());
            pstmt.setString(2, deadline.getDescription());
            pstmt.executeUpdate();
        }
    }
}
