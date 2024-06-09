import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;

/**
 * Panel that displays a monthly calendar with navigation buttons to move between months.
 */
public class MonthlyCalendarPanel extends JPanel {
    private JPanel calendarPanel;
    private Calendar calendar;
    private JLabel monthLabel;

    public MonthlyCalendarPanel() {
        setLayout(new BorderLayout());

        calendar = Calendar.getInstance();

        // header that shows the month and navigation buttons
        JPanel headerPanel = new JPanel();
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        monthLabel = new JLabel("", SwingConstants.CENTER);

        // buttons actions
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        // Adding buttons
        headerPanel.add(prevButton);
        headerPanel.add(monthLabel);
        headerPanel.add(nextButton);

        // main panel 
        calendarPanel = new JPanel(new GridLayout(7, 7));
        add(headerPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    /* 
     * Updates calendar
     */
    private void updateCalendar() {
        calendarPanel.removeAll();

        String[] daysOfWeek = { "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb" };
        for (String day : daysOfWeek) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Fills the calendar with empty panels before the start of the month
        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JPanel()); 
        }
        for (int day = 1; day <= daysInMonth; day++) {
            // Adds a panel for each day of the month
            DayPanel dayPanel = new DayPanel(day);
            calendarPanel.add(dayPanel);
        }

        monthLabel.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " "
                + calendar.get(Calendar.YEAR));

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    // Método para ativar/desativar o calendário
    public void setCalendarVisible(boolean visible) {
        setVisible(visible);
    }
}
