import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class EventListView extends JPanel {
    private EventManager eventManager;
    private JPanel eventListPanel;
    private JPanel topPanel;
    private EventActionListener editListener;
    private EventActionListener deleteListener;
    private LocalDate date;

    public EventListView(EventManager eventManager, EventActionListener editListener,
            EventActionListener deleteListener, LocalDate date) {
        this.eventManager = eventManager;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        this.date = date;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));

        // top panel with date and a button to add events
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        add(new JScrollPane(eventListPanel), BorderLayout.CENTER);
    }
    
    private void addEvent() {
        EventDialog dialog = new EventDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Event", date);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Event newEvent = dialog.getEvent();
            eventManager.addEvent(newEvent);
            updateEvents(date);
        }
    }

    public void updateEvents(LocalDate date) {

        this.date = date;

        eventListPanel.removeAll();
        topPanel.removeAll();

        JLabel dateLabel = new JLabel("Events on " + date);
        topPanel.add(dateLabel, BorderLayout.WEST);

        JButton addEventsButton = new JButton("Add Event");
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

    public interface EventActionListener {
        void actionPerformed(Event event);
    }
}
