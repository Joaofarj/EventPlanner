import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The EventManager class is responsible for managing a list of events.
 * It includes functionalities to add, edit, delete, search, and retrieve events.
 * It also handles loading and saving events to a file.
 */
public class EventManager {
    private List<Event> events;

    /**
     * Constructs an EventManager instance and loads events from the specified file.
     *
     * @param filename the name of the file to load events from
     */
    public EventManager(String filename) {
        events = loadEvents();
    }

    /**
     * Adds an event to the list.
     *
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Edits an existing event.
     *
     * @param oldEvent the event to be replaced
     * @param newEvent the new event to replace the old event
     */
    public void editEvent(Event oldEvent, Event newEvent) {
        int index = events.indexOf(oldEvent);
        if (index >= 0) {
            events.set(index, newEvent);
        }
    }

    /**
     * Deletes an event from the list.
     *
     * @param event the event to delete
     */
    public void deleteEvent(Event event) {
        events.remove(event);
    }

    /**
     * Searches for events by a keyword. The search is performed on event titles and descriptions.
     *
     * @param keyword the keyword to search for
     * @return a list of events that match the keyword
     */
    public List<Event> searchEvents(String keyword) {
        return events.stream()
                .filter(event -> event.getTitle().contains(keyword) || event.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of events occurring on a specific date.
     *
     * @param date the date to filter events by
     * @return a list of events occurring on the specified date
     */
    public List<Event> getEventsByDate(LocalDate date) {
        return events.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    /**
     * Loads events from the "events.dat" file.
     *
     * @return a list of loaded events
     */
    @SuppressWarnings("unchecked")
    public List<Event> loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("events.dat"))) {
            return (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current list of events to the "events.dat" file.
     */
    public void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("events.dat"))) {
            oos.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all events for debugging purposes.
     *
     * @return a list of all events
     */
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
