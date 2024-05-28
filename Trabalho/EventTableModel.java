import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is a table model for the events table
 */
public class EventTableModel extends AbstractTableModel {
    private List<Event> events;
    private final String[] columnNames = { "Title", "Date", "Time", "Location", "Description" };

    /**
     * @param events the list of events to display
     */
    public EventTableModel(List<Event> events) {
        this.events = events;
    }

    /** 
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return events.size();
    }

    /** 
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * get a especific value from the table
     * @param rowIndex the row index
     * @param columnIndex the column index
     * @return the value at the specified row and column
     */
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

    /**
     * @param column the column index
     * @return the name of the column
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
