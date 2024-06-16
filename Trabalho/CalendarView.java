import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class CalendarView extends JPanel {
    private EventManager eventManager;
    private LocalDate selectedDate;
    private YearMonth currentMonth;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JButton prevButton;
    private JButton nextButton;
    private List<Event> events;
    private DateSelectedListener dateSelectedListener;

    public CalendarView(EventManager eventManager, DateSelectedListener dateSelectedListener) {
        this.eventManager = eventManager;
        this.dateSelectedListener = dateSelectedListener;
        this.selectedDate = LocalDate.now();
        this.currentMonth = YearMonth.now();
        
        initializeUI();
        refresh();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        prevButton = new JButton("<");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth = currentMonth.minusMonths(1);
                refresh();
            }
        });

        nextButton = new JButton(">");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth = currentMonth.plusMonths(1);
                refresh();
            }
        });

        monthLabel = new JLabel();
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(7, 7));
        add(calendarPanel, BorderLayout.CENTER);
    }

    public void refresh() {
        calendarPanel.removeAll();

        monthLabel.setText(currentMonth.getMonth().toString() + " " + currentMonth.getYear());
        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

        for (String day : daysOfWeek) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));

        }

        LocalDate firstOfMonth = currentMonth.atDay(1);
        int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue();
        int daysInMonth = currentMonth.lengthOfMonth();

        for (int i = 1; i < dayOfWeekValue; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentMonth.atDay(day);
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedDate = date;
                    dateSelectedListener.dateSelected(date);
                }
            });

            events = eventManager.getEventsByDate(date);
            if (!events.isEmpty()) {
                dayButton.setBackground(Color.CYAN); // Highlight dates with events
            }

            calendarPanel.add(dayButton);
        }
        // Fills the calendar with empty panels after the end of the month

        for (int i = 1; i < 42 - daysInMonth - dayOfWeekValue; i++) {
            calendarPanel.add(new JPanel());
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public interface DateSelectedListener {
        void dateSelected(LocalDate date);
    }
}
