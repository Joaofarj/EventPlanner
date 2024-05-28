import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/** 
 * THe class for remindinig the user for upcoming events
 */
public class ReminderService {
    private List<Event> events;
    private Timer timer;

    /**
     * @param events events to notify
     */
    public ReminderService(List<Event> events) {
        this.events = events;
        this.timer = new Timer(true);
    
    }

    /**
     * Start a timer to check for reminders
     */
    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkReminders();
            }
        }, 0, 60 * 1000); // Check every minute
    }

    /**
     * Check for reminders
     */
    private void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        for (Event event : events) {
            if (event.getDateTime().isAfter(now) && event.getDateTime().isBefore(now.plusMinutes(10))) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null,
                        "Reminder: " + event.getTitle() + " is coming up at " + event.getDateTime()));
            }
        }
    }
}
