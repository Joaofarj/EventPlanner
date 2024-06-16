import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class EventListView extends JPanel {
    private EventManager eventManager;
    private LocalDate selectedDate;
    private JPanel eventListPanel;
    private EventActionListener editListener;
    private EventActionListener deleteListener;

    public EventListView(EventManager eventManager, EventActionListener editListener,
            EventActionListener deleteListener) {
        this.eventManager = eventManager;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(eventListPanel), BorderLayout.CENTER);
    }

    public void updateEvents(LocalDate date) {
        this.selectedDate = date;
        eventListPanel.removeAll();
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
