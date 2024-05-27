import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EventTableModel extends AbstractTableModel {
    private List<Event> events;
    private final String[] columnNames = {"Title", "Date", "Time", "Location", "Description"};

    public EventTableModel(List<Event> events) {
        this.events = events;
    }

    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event event = events.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return event.getTitle();
            case 1:
                return event.getDateTime().toLocalDate();
            case 2:
                return event.getDateTime().toLocalTime();
            case 3:
                return event.getLocation();
            case 4:
                return event.getDescription();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
