import java.io.Serializable;
import java.time.LocalDateTime;

/* 
 * Event class that stores information about an event
 *  like title, date, time, location and description
 */
public class Event implements Serializable {
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private String description;

    /**
     * @param title the title of the event
     * @param dateTime the date and time of the event
     * @param location the location of the event
     * @param description the description of the event
     */
    public Event(String title, LocalDateTime dateTime, String location, String description) {
        this.title = title;
        this.dateTime = dateTime;
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
    public LocalDateTime getDateTime() {
        return dateTime;
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
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
}

