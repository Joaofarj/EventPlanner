import javax.swing.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ReminderService class provides functionality to schedule and display reminders
 * for events managed by the EventManager.
 */
public class ReminderService {
    private EventManager eventManager;
    private Timer timer;

    /**
     * Constructs a ReminderService object, initializes the EventManager and Timer,
     * and schedules reminders for all existing events.
     *
     * @param eventManager the EventManager instance to retrieve events from
     */
    public ReminderService(EventManager eventManager) {
        this.eventManager = eventManager;
        this.timer = new Timer(true); // Daemon thread
        scheduleReminders();
    }

    /**
     * Schedules reminders for all events managed by the EventManager.
     */
    private void scheduleReminders() {
        List<Event> events = eventManager.getAllEvents();
        for (Event event : events) {
            scheduleEventReminder(event);
        }
    }

    /**
     * Schedules a reminder for a specific event.
     *
     * @param event the event for which to schedule a reminder
     */
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

    /**
     * Displays a reminder for the specified event using a Swing dialog.
     *
     * @param event the event for which to show a reminder
     */
    private void showReminder(Event event) {
        SwingUtilities.invokeLater(
                () -> JOptionPane.showMessageDialog(null, "Reminder: " + event.getTitle() + " at " + event.getTime(),
                        "Event Reminder", JOptionPane.INFORMATION_MESSAGE));
    }

    /**
     * Adds a new event to the reminder service and schedules a reminder for it.
     *
     * @param event the event to be added
     */
    public void addEvent(Event event) {
        scheduleEventReminder(event);
    }

    /**
     * Stops the reminder service and cancels all scheduled reminders.
     */
    public void stop() {
        timer.cancel();
    }
}
