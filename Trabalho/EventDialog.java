import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class EventDialog extends JDialog {
    private JTextField titleField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private Event event;

    public EventDialog(Frame owner, String title, Event event) {
        super(owner, title, true);
        this.event = event;

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Title:"));
        titleField = new JTextField(20);
        panel.add(titleField);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(20);
        panel.add(dateField);

        panel.add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField(20);
        panel.add(timeField);

        panel.add(new JLabel("Location:"));
        locationField = new JTextField(20);
        panel.add(locationField);

        panel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(5, 20);
        panel.add(new JScrollPane(descriptionArea));

        if (event != null) {
            titleField.setText(event.getTitle());
            dateField.setText(event.getDateTime().toLocalDate().toString());
            timeField.setText(event.getDateTime().toLocalTime().toString());
            locationField.setText(event.getLocation());
            descriptionArea.setText(event.getDescription());
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                String location = locationField.getText();
                String description = descriptionArea.getText();
                LocalDateTime dateTime = null;
                
                // check if everything is filled
                if (title.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    JOptionPane.showMessageDialog(EventDialog.this, "Please fill all fields", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // check if date is in the correct format
                if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(EventDialog.this, "Invalid date format. Use YYYY-MM-DD", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // check if time is in the correct format
                if (!time.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(EventDialog.this, "Invalid time format. Use HH:MM", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // check if date and time are valid
                try {
                    dateTime = LocalDateTime.parse(date + "T" + time);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EventDialog.this, "Invalid date and time", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

                if (EventDialog.this.event == null) {
                    EventDialog.this.event = new Event(title, dateTime, location, description);
                } else {
                    EventDialog.this.event.setTitle(title);
                    EventDialog.this.event.setDateTime(dateTime);
                    EventDialog.this.event.setLocation(location);
                    EventDialog.this.event.setDescription(description);
                }
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventDialog.this.event = null;
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    public Event getEvent() {
        return event;
    }
}
