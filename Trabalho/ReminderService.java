import javax.swing.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService {
    private EventManager eventManager;
    private Timer timer;

    public ReminderService(EventManager eventManager) {
        this.eventManager = eventManager;
        this.timer = new Timer(true); // Daemon thread
        scheduleReminders();
    }

    private void scheduleReminders() {
        List<Event> events = eventManager.getAllEvents();
        for (Event event : events) {
            scheduleEventReminder(event);
        }
    }

    private void scheduleEventReminder(Event event) {
        LocalDateTime eventTime = LocalDateTime.of(event.getDate(), event.getTime());
        LocalDateTime reminderTime = eventTime.minusMinutes(10); // Set reminder 10 minutes before the event
        long delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), reminderTime);

        if (delay > 0) {
            TimerTask reminderTask = new TimerTask() {
                @Override
                public void run() {
                    showReminder(event);
                }
            };
            timer.schedule(reminderTask, delay);
        }
    }

    private void showReminder(Event event) {
        SwingUtilities.invokeLater(
                () -> JOptionPane.showMessageDialog(null, "Reminder: " + event.getTitle() + " at " + event.getTime(),
                        "Event Reminder", JOptionPane.INFORMATION_MESSAGE));
    }

    public void addEvent(Event event) {
        scheduleEventReminder(event);
    }

    public void stop() {
        timer.cancel();
    }
}
