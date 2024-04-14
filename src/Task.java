import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task {
    private String description;
    private Calendar dueDate;
    public Task(String description, Calendar dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }
    public String getDescription() {
        return description;
    }

    public Calendar getDueDate() {
        return dueDate;
    }
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return description + " (due: " + format.format(dueDate.getTime()) + ")";
    }

}
