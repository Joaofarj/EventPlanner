import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {
    private List<Event> events;

    public EventManager(String filename) {
        events = loadEvents();
    }

    // Method to add an event
    public void addEvent(Event event) {
        events.add(event);
    }

    // Method to edit an event
    public void editEvent(Event oldEvent, Event newEvent) {
        int index = events.indexOf(oldEvent);
        if (index >= 0) {
            events.set(index, newEvent);
        }
    }

    // Method to delete an event
    public void deleteEvent(Event event) {
        events.remove(event);
    }

    // Method to search for events by keyword
    public List<Event> searchEvents(String keyword) {
        return events.stream()
                .filter(event -> event.getTitle().contains(keyword) || event.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    // Method to get events on a specific date
    public List<Event> getEventsByDate(LocalDate date) {
        return events.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    // Method to load events from a file
    @SuppressWarnings("unchecked")
    public List<Event> loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("events.dat"))) {
            return (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Method to save events to a file
    public void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("events.dat"))) {
            oos.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Method to list all events (for debugging purposes)
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
