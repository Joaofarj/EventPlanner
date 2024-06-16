import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/* 
 * Event class that stores information about an event
 *  like title, date, time, location and description
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String description;


    /**
     * @param title the title of the event
     * @param dateTime the date and time of the event
     * @param location the location of the event
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
     * @return the title of the event 
     **/
    public String getTitle() {
        return title;
    }

    /**
     * @return the date and time of the event
     **/

    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the location of the event
     **/
    public String getLocation() {
        return location;
    }

    /**
     * @return the description of the event
     **/
    public String getDescription() {
        return description;
    }

    /**
     * sets the title to the event
     * @param title the title of the event
    */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /**
     * Set the date to the event
     * @param dateTime the date to the event
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Set the location to the event
     * @param location the location to the event
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Set the description to the event
     * @param description the description to the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }

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

