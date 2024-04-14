import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void saveTasksToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                writer.write(task.getDescription() + "," + task.getDueDate().getTimeInMillis() + "\n");
            }
        }
    }

    public void loadTasksFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String description = parts[0];
                Calendar dueDate = Calendar.getInstance();
                dueDate.setTimeInMillis(Long.parseLong(parts[1]));
                tasks.add(new Task(description, dueDate));
            }
        }
    }
}