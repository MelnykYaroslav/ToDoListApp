import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ToDoListApp {
    private static ResourceBundle messages;
    private static TaskManager manager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        messages = ResourceBundle.getBundle("Messages", locale);

        boolean running = true;
        while (running) {
            System.out.println("\n" + messages.getString("chooseAction"));
            System.out.println("1. " + messages.getString("addTask"));
            System.out.println("2. " + messages.getString("showTasks"));
            System.out.println("3. " + messages.getString("saveTasks"));
            System.out.println("4. " + messages.getString("loadTasks"));
            System.out.println("5. " + messages.getString("exit"));
            System.out.print(messages.getString("yourChoice"));

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    addTask();
                    break;
                case 2:
                    showTasks();
                    break;
                case 3:
                    saveTasks();
                    break;
                case 4:
                    loadTasks();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println(messages.getString("invalidInput"));
            }
        }
        scanner.close();
    }

    private static void addTask() {
        System.out.print(messages.getString("enterDescription"));
        String description = scanner.nextLine();

        System.out.print(messages.getString("enterDay"));
        int day = scanner.nextInt();
        System.out.print(messages.getString("enterMonth"));
        int month = scanner.nextInt();
        System.out.print(messages.getString("enterYear"));
        int year = scanner.nextInt();
        scanner.nextLine();

        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, day, 0, 0, 0);

        Task task = new Task(description, date);
        manager.addTask(task);
        System.out.println(messages.getString("taskAdded"));
    }

    private static void showTasks() {
        System.out.println(messages.getString("showAllTasks"));
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
    }

    private static void saveTasks() {
        System.out.print(messages.getString("enterFilenameToSave"));
        String filename = scanner.nextLine();
        try {
            manager.saveTasksToFile(filename);
            System.out.println(messages.getString("tasksSaved") + filename);
        } catch (IOException e) {
            System.out.println(messages.getString("savingError") + e.getMessage());
        }
    }

    private static void loadTasks() {
        System.out.print(messages.getString("enterFilenameToLoad"));
        String filename = scanner.nextLine();
        try {
            manager.loadTasksFromFile(filename);
            System.out.println(messages.getString("tasksLoaded") + filename);
        } catch (IOException e) {
            System.out.println(messages.getString("loadingError") + e.getMessage());
        }
    }
}
