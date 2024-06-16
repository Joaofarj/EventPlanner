import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/* 
 * class o show a Dialog to create or edit an event
 */
public class EventDialog extends JDialog {
    private JTextField titleField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private boolean confirmed;

    private Event event;

    /**
     * @param owner the owner of the dialog
     * @param title the title of the dialog
     */
    public EventDialog(Frame owner, String title) {
        super(owner, title, true);
        initializeUI();
    }

    /**
     * @param owner the owner of the dialog
     * @param title the title of the dialog
     * @param event the event to edit, or null to create a new event
     */
    public EventDialog(Frame owner, String title, Event event) {
        super(owner, title, true);
        this.event = event;
        initializeUI();
        populateFields();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        inputPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        inputPanel.add(locationField);

        inputPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(descriptionArea));

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void populateFields() {
        if (event != null) {
            titleField.setText(event.getTitle());
            dateField.setText(event.getDate().toString());
            timeField.setText(event.getTime().toString());
            locationField.setText(event.getLocation());
            descriptionArea.setText(event.getDescription());
        }
    }

    private void onSave() {
        String title = titleField.getText();
        String date_str = dateField.getText();
        String time_str = timeField.getText();
        String location = locationField.getText();
        String description = descriptionArea.getText();
        LocalDate date = null;
        LocalTime time = null;


        // check if everything is filled
        if (title.isEmpty() || date_str.isEmpty() || time_str.isEmpty()) {
            JOptionPane.showMessageDialog(EventDialog.this, "Please fill all fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // check if date is in the correct format
        if (!date_str.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid date format. Use YYYY-MM-DD", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // check if time is in the correct format
        if (!time_str.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid time format. Use HH:MM", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // check if date and time are valid
        try {
            date = LocalDate.parse(dateField.getText());
            time = LocalTime.parse(timeField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid date and time", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        event = new Event(title, date, time, location, description);

        confirmed = true;
        dispose();
    }

    private void onCancel() {
        confirmed = false;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Event getEvent() {
        return event;
    }
}
