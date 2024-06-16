import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event class that stores information about an event
 * like title, date, time, location, and description.
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String description;

    /**
     * Constructs an Event with the specified title, date, time, location, and
     * description.
     *
     * @param title       the title of the event
     * @param date        the date of the event
     * @param time        the time of the event
     * @param location    the location of the event
     * @param description the description of the event
     */
    public Event(String title, LocalDate date, LocalTime time, String location, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    /**
     * Gets the title of the event.
     *
     * @return the title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     *
     * @param title the new title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date of the event.
     *
     * @return the date of the event
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date the new date of the event
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time of the event.
     *
     * @return the time of the event
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the event.
     *
     * @param time the new time of the event
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Gets the location of the event.
     *
     * @return the location of the event
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the event.
     *
     * @param location the new location of the event
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     *
     * @param description the new description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
