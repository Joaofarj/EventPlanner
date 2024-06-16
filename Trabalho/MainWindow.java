import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

/**
 * MainWindow class represents the main window of the Event Planner application.
 * It provides functionalities to add, edit, delete events, manage reminders,
 * and search events by keywords.
 */
public class MainWindow extends JFrame {
    private EventManager eventManager;
    private CalendarView calendarView;
    private EventListView eventListView;
    private LocalDate today;
    private ReminderService reminderService;
    private JTextField searchField;
    private JPanel searchResultsPanel;

    /**
     * Constructs a MainWindow object, initializes the EventManager and
     * ReminderService,
     * and sets up the user interface.
     */
    public MainWindow() {
        eventManager = new EventManager("events.dat");
        reminderService = new ReminderService(eventManager);
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("Event Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.today = LocalDate.now();

        // Navigation pane with calendar view
        calendarView = new CalendarView(eventManager, this::onDateSelected);
        add(calendarView, BorderLayout.WEST);

        // Detailed view area with event list view
        eventListView = new EventListView(eventManager, this::onEventEdited, this::onEventDeleted, today);
        add(new JScrollPane(eventListView), BorderLayout.CENTER);

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);

        // Search results panel
        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BoxLayout(searchResultsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(searchResultsPanel), BorderLayout.EAST);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddEvent();
            }
        });
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Refresh the views
        refreshViews();
    }

    /**
     * Updates the event list view when a date is selected in the calendar view.
     *
     * @param date the selected date
     */
    private void onDateSelected(LocalDate date) {
        eventListView.updateEvents(date);
    }

    /**
     * Opens the EventDialog to add a new event, and updates the views and
     * reminders.
     */
    private void onAddEvent() {
        EventDialog dialog = new EventDialog(this, "Add Event");
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Event newEvent = dialog.getEvent();
            eventManager.addEvent(newEvent);
            reminderService.addEvent(newEvent);
            refreshViews();
        }
    }

    /**
     * Opens the EventDialog to edit an existing event, and updates the views and
     * reminders.
     *
     * @param event the event to be edited
     */
    private void onEventEdited(Event event) {
        EventDialog dialog = new EventDialog(this, "Edit Event", event);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Event updatedEvent = dialog.getEvent();
            eventManager.editEvent(event, updatedEvent);
            reminderService.addEvent(updatedEvent);
            refreshViews();
        }
    }

    /**
     * Deletes an event after confirming with the user, and updates the views.
     *
     * @param event the event to be deleted
     */
    private void onEventDeleted(Event event) {
        int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this event?",
                "Delete Event", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            eventManager.deleteEvent(event);
            refreshViews();
        }
    }

    /**
     * Searches for events based on the keyword entered in the search field,
     * and displays the results in the search results panel.
     */
    private void onSearch() {
        String keyword = searchField.getText().trim();
        List<Event> searchResults = eventManager.searchEvents(keyword);
        searchResultsPanel.removeAll();

        for (Event event : searchResults) {
            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BorderLayout());
            eventPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel eventLabel = new JLabel(event.getTitle() + " on " + event.getDate() + " at " + event.getTime());
            eventPanel.add(eventLabel, BorderLayout.CENTER);

            searchResultsPanel.add(eventPanel);
        }

        revalidate();
        repaint();
    }

    /**
     * Refreshes the calendar view and event list view, and saves the events to the
     * file.
     */
    private void refreshViews() {
        LocalDate selectedDate = calendarView.getSelectedDate();
        calendarView.refresh();
        eventListView.updateEvents(selectedDate);
        eventManager.saveEvents();
    }

    /**
     * The main method to run the Event Planner application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
