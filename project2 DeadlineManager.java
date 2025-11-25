package deadlinemate;

import java.util.ArrayList;
import java.util.List;

public class DeadlineManager {
    private List<Deadline> deadlines;

    public DeadlineManager() {
        this.deadlines = new ArrayList<>();
    }

    public void addDeadline(Deadline deadline) {
        this.deadlines.add(deadline);
    }

    public void removeDeadline(Deadline deadline) {
        this.deadlines.remove(deadline);
    }

    public List<Deadline> getAllDeadlines() {
        return new ArrayList<>(this.deadlines);
    }
}
