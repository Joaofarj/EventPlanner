import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Main class for displaying the event planner
  */
public class EventPlanner extends JFrame {
    private JTable eventTable;
    private EventTableModel eventTableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private List<Event> events;



    /**
     * Event planner constructor 
     */
    public EventPlanner() {
        // Set window properties
        setTitle("Event Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load events from file
        events = loadEvents();

        // Create table and buttons
        eventTableModel = new EventTableModel(events);
        eventTable = new JTable(eventTableModel);

        JScrollPane scrollPane = new JScrollPane(eventTable);

        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");

        // Add event button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventDialog dialog = new EventDialog(EventPlanner.this, "Add Event", null);
                dialog.setVisible(true);
                Event event = dialog.getEvent();
                if (event != null) {
                    events.add(event);
                    // refresh the events table
                    eventTableModel.fireTableDataChanged();
                    saveEvents();
                } 

            }
        });

        // Edit event button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event event = events.get(selectedRow);
                    EventDialog dialog = new EventDialog(EventPlanner.this, "Edit Event", event);
                    dialog.setVisible(true);
                    Event updatedEvent = dialog.getEvent();
                    if (updatedEvent != null) {
                        events.set(selectedRow, updatedEvent);
                        eventTableModel.fireTableDataChanged();
                        saveEvents();
                    }
                } else {
                    JOptionPane.showMessageDialog(EventPlanner.this, "No event selected.");
                }
            }
        });

        // Delete event button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    // dialog to confirm deletion
                    int option = JOptionPane.showConfirmDialog(EventPlanner.this, "Are you sure you want to delete this event?",
                            "Delete Event", JOptionPane.YES_NO_OPTION);

                    if (option != JOptionPane.YES_OPTION) {
                        return;
                    }
                    events.remove(selectedRow);
                    eventTableModel.fireTableDataChanged();
                    saveEvents();
                } else {
                    JOptionPane.showMessageDialog(EventPlanner.this, "No event selected.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Reminder Service
        ReminderService reminderService = new ReminderService(events);
        reminderService.start();

        setVisible(true);
    }

    /**
     * Load events from file
     * @return list of events
     */
    @SuppressWarnings("unchecked")
    private List<Event> loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("events.dat"))) {
            return (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Save events to file
     */
    private void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("events.dat"))) {
            oos.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventPlanner::new);
    }
}

