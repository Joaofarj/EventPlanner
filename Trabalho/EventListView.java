import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

/**
 * EventListView class represents a panel that displays a list of events for a
 * selected date.
 * It provides functionality to add, edit, and delete events.
 */
public class EventListView extends JPanel {
    private EventManager eventManager;
    private JPanel eventListPanel;
    private JPanel topPanel;
    private EventActionListener editListener;
    private EventActionListener deleteListener;
    private MainWindow main;
    private LocalDate date;

    /**
     * Constructs an EventListView with the specified parameters.
     *
     * @param eventManager   the EventManager that manages events
     * @param editListener   the listener for edit actions
     * @param deleteListener the listener for delete actions
     * @param date           the date for which events are displayed
     */
    public EventListView(EventManager eventManager, EventActionListener editListener,
            EventActionListener deleteListener, LocalDate date,MainWindow main) {
        this.eventManager = eventManager;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        this.date = date;
        this.main = main;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));

        // Top panel with date and a button to add events
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        add(new JScrollPane(eventListPanel), BorderLayout.CENTER);
    }

    /**
     * Adds a new event by showing the EventDialog.
     */
    private void addEvent() {
        EventDialog dialog = new EventDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Event", date);
        dialog.setVisible(true);
        main.onAddEvent(dialog);
    }

    /**
     * Updates the list of events for the specified date.
     *
     * @param date the date for which to display events
     */
    public void updateEvents(LocalDate date) {
        this.date = date;

        eventListPanel.removeAll();
        topPanel.removeAll();

        JLabel dateLabel = new JLabel("Events on " + date);
        topPanel.add(dateLabel, BorderLayout.WEST);

        JButton addEventsButton = new JButton("+");
        addEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });
        topPanel.add(addEventsButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        List<Event> events = eventManager.getEventsByDate(date);

        for (Event event : events) {
            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BorderLayout());
            eventPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel eventLabel = new JLabel(event.getTitle() + " at " + event.getTime());
            eventPanel.add(eventLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editListener.actionPerformed(event);
                }
            });
            buttonPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteListener.actionPerformed(event);
                }
            });
            buttonPanel.add(deleteButton);

            eventPanel.add(buttonPanel, BorderLayout.EAST);

            eventListPanel.add(eventPanel);
        }

        revalidate();
        repaint();
    }

    /**
     * The EventActionListener interface should be implemented by any class that
     * handles
     * actions performed on events, such as editing or deleting an event.
     */
    public interface EventActionListener {
        /**
         * Invoked when an action is performed on an event.
         *
         * @param event the event on which the action is performed
         */
        void actionPerformed(Event event);
    }
}
