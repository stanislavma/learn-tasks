package sprint_7.stream;

import java.util.List;

public class Task {

    int id;
    List<Integer> subtasksId;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(List<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", subtasksId=" + subtasksId +
                '}';
    }
}
