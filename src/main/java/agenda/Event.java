package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;
    private Termination termination;
    private Repetition repetition;
    private Set<LocalDate> exceptions = new HashSet<>();

    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    public void setRepetition(ChronoUnit frequency) {
        this.repetition = new Repetition(frequency);
    }

    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    public void setTermination(LocalDate terminationInclusive) {
        this.termination = new Termination(myStart.toLocalDate(), repetition.getFrequency(), terminationInclusive);
    }

    public void setTermination(long numberOfOccurrences) {
        this.termination = new Termination(myStart.toLocalDate(), repetition.getFrequency(), numberOfOccurrences);
    }

    public int getNumberOfOccurrences() {
        if (termination != null && termination.terminationDateInclusive() != null) {
            LocalDate startDate = myStart.toLocalDate();
            long occurrences = ChronoUnit.DAYS.between(startDate, termination.terminationDateInclusive()) / repetition.getFrequency().getDuration().toDays();
            return (int) occurrences + 1;
        }
        return termination != null ? (int) termination.numberOfOccurrences() : 0;
    }

    public LocalDate getTerminationDate() {
        if (termination != null && termination.numberOfOccurrences() > 0) {
            LocalDate startDate = myStart.toLocalDate();
            long durationDays = repetition.getFrequency().getDuration().toDays();
            return startDate.plusDays((termination.numberOfOccurrences() - 1) * durationDays);
        }
        return termination != null ? termination.terminationDateInclusive() : null;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
      if (repetition != null) {

        LocalDate startDate = myStart.toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, aDay);

        if (daysBetween < 0) {
            return false;
        }
        if (termination != null && termination.terminationDateInclusive() != null) {
            if (aDay.isAfter(termination.terminationDateInclusive())) {
                return false;
            }
        }
        if (termination != null && termination.numberOfOccurrences() > 0) {
            LocalDate lastDate = startDate.plus(termination.numberOfOccurrences() - 1,
                    repetition.getFrequency());
            if (aDay.isAfter(lastDate)) {
                return false;
            }
        }
        if (daysBetween % repetition.getFrequency().getDuration().toDays() == 0) {
            return !exceptions.contains(aDay);
        }

        return false;
    }
    LocalDateTime endDateTime = myStart.plus(myDuration);
    return !aDay.isBefore(myStart.toLocalDate()) &&
           !aDay.isAfter(endDateTime.toLocalDate());
}
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}
