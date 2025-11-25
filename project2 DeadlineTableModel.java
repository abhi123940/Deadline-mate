package deadlinemate;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DeadlineTableModel extends AbstractTableModel {
    private List<Deadline> deadlines;
    private String[] columnNames = {"Name", "Description"};

    public DeadlineTableModel() {
        this.deadlines = new ArrayList<>();
    }

    public void setDeadlines(List<Deadline> deadlines) {
        this.deadlines = new ArrayList<>(deadlines);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return deadlines.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Deadline deadline = deadlines.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return deadline.getName();
            case 1:
                return deadline.getDescription();
            default:
                return null;
        }
    }

    public Deadline getDeadlineAt(int rowIndex) {
        return deadlines.get(rowIndex);
    }
}
