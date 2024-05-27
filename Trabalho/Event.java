import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private String description;

    public Event(String title, LocalDateTime dateTime, String location, String description) {
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

