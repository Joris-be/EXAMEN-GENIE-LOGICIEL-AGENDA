package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Termination {
    private LocalDate terminationDateInclusive;
    private long numberOfOccurrences;

    public LocalDate terminationDateInclusive() {
        return terminationDateInclusive;
    }

    public long numberOfOccurrences() {
        return numberOfOccurrences;
    }


    /**
     * Constructs a  termination at a given date
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     * @see ChronoUnit#between(Temporal, Temporal)
     */
    public Termination(LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        if (terminationInclusive == null) {
            throw new IllegalArgumentException("La date de termination ne peut pas être nulle.");
        }
        this.terminationDateInclusive = terminationInclusive;
        this.numberOfOccurrences = frequency.between(start, terminationInclusive) + 1;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public Termination(LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {
        if (numberOfOccurrences <= 0) {
        throw new IllegalArgumentException("Le nombre d occurrences ne peut pas être négatif.");
    }
    this.numberOfOccurrences = numberOfOccurrences;
    this.terminationDateInclusive = start.plus(numberOfOccurrences - 1, frequency);
    }

}
