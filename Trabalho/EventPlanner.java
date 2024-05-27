import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner extends JFrame {
    private JTable eventTable;
    private EventTableModel eventTableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private List<Event> events;

    public EventPlanner() {
        setTitle("Event Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        events = loadEvents();

        eventTableModel = new EventTableModel(events);
        eventTable = new JTable(eventTableModel);

        JScrollPane scrollPane = new JScrollPane(eventTable);

        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventDialog dialog = new EventDialog(EventPlanner.this, "Add Event", null);
                dialog.setVisible(true);
                Event event = dialog.getEvent();
                if (event != null) {
                    events.add(event);
                    eventTableModel.fireTableDataChanged();
                    saveEvents();
                }
            }
        });

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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
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

    private List<Event> loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("events.dat"))) {
            return (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

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

