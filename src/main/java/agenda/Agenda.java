package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private  List<Event> events;
    
    public Agenda() {
        events = new ArrayList<>();
    }
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> eventsInDay = new ArrayList<>();
        for (Event event : events) {
            if (event.isInDay(day)) {
                eventsInDay.add(event);
            }
        }
        return eventsInDay;
    }
    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        ArrayList<Event> eventsDay = new ArrayList<>();
        for (Event e : events) {
            if (e.getTitle().equals(title)) {
                eventsDay.add(e);
            }
        }
        return eventsDay;   
    }
    
    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement (aucun autre événement au même moment)
     * @param e L'événement à tester (on se limitera aux événements sans répétition)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        for (Event event : events) {
            if (e.getStart().plus(e.getDuration()).isAfter(event.getStart()) && e.getStart().isBefore(event.getStart().plus(event.getDuration()))) {
                return false;
            }
        }
        return true;
    }
}
