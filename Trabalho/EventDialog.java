import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * EventDialog class displays a dialog to create or edit an event.
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
     * Constructs an EventDialog for creating a new event.
     *
     * @param owner the owner of the dialog
     * @param title the title of the dialog
     */
    public EventDialog(Frame owner, String title) {
        super(owner, title, true);
        initializeUI();
    }

    /**
     * Constructs an EventDialog for editing an existing event.
     *
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

    /**
     * Constructs an EventDialog for creating a new event with a pre-filled date.
     *
     * @param owner the owner of the dialog
     * @param title the title of the dialog
     * @param date  the pre-filled date for the event
     */
    public EventDialog(Frame owner, String title, LocalDate date) {
        super(owner, title, true);
        initializeUI();
        populateDate(date);
    }

    /**
     * Populates the date field with the specified date.
     *
     * @param date the date to populate
     */
    private void populateDate(LocalDate date) {
        dateField.setText(date.toString());
    }

    /**
     * Initializes the user interface components.
     */
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

    /**
     * Populates the fields with the event details for editing.
     */
    private void populateFields() {
        if (event != null) {
            titleField.setText(event.getTitle());
            dateField.setText(event.getDate().toString());
            timeField.setText(event.getTime().toString());
            locationField.setText(event.getLocation());
            descriptionArea.setText(event.getDescription());
        }
    }

    /**
     * Handles the save action, validates the input, and creates or updates the
     * event.
     */
    private void onSave() {
        String title = titleField.getText();
        String dateStr = dateField.getText();
        String timeStr = timeField.getText();
        String location = locationField.getText();
        String description = descriptionArea.getText();
        LocalDate date = null;
        LocalTime time = null;

        // Check if all required fields are filled
        if (title.isEmpty() || dateStr.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(EventDialog.this, "Please fill all fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date format
        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid date format. Use YYYY-MM-DD", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate time format
        if (!timeStr.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid time format. Use HH:MM", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date and time
        try {
            date = LocalDate.parse(dateStr);
            time = LocalTime.parse(timeStr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(EventDialog.this, "Invalid date and time", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        event = new Event(title, date, time, location, description);
        confirmed = true;
        dispose();
    }

    /**
     * Handles the cancel action, discards the input, and closes the dialog.
     */
    private void onCancel() {
        confirmed = false;
        dispose();
    }

    /**
     * Returns whether the dialog was confirmed.
     *
     * @return true if the dialog was confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Returns the event created or edited by the dialog.
     *
     * @return the event created or edited
     */
    public Event getEvent() {
        return event;
    }
}
