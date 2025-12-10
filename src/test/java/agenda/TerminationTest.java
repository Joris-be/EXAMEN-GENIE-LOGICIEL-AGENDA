package agenda;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TerminationTest {

    @Test
    void createTerminationWithDate() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        LocalDate end = start.plusDays(4); // 5 jours d’occurrences
        Termination t = new Termination(start, ChronoUnit.DAYS, end);

        assertEquals(end, t.terminationDateInclusive());
        assertEquals(5, t.numberOfOccurrences());
    }

    @Test
    void createTerminationWithOccurrences() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        long occurrences = 7;
        Termination t = new Termination(start, ChronoUnit.DAYS, occurrences);

        assertEquals(start.plusDays(occurrences - 1), t.terminationDateInclusive());
        assertEquals(occurrences, t.numberOfOccurrences());
    }

    @Test
    void terminationDateCannotBeNull() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        assertThrows(IllegalArgumentException.class,
                () -> new Termination(start, ChronoUnit.DAYS, (LocalDate) null));
    }

    @Test
    void numberOfOccurrencesMustBePositive() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        assertThrows(IllegalArgumentException.class,
                () -> new Termination(start, ChronoUnit.DAYS, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new Termination(start, ChronoUnit.DAYS, -5));
    }

    @Test
    void terminationWithWeeksFrequency() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        long occurrences = 3;
        Termination t = new Termination(start, ChronoUnit.WEEKS, occurrences);

        assertEquals(start.plusWeeks(occurrences - 1), t.terminationDateInclusive());
        assertEquals(occurrences, t.numberOfOccurrences());
    }

    @Test
    void terminationWithMonthsFrequency() {
        LocalDate start = LocalDate.of(2025, 12, 10);
        long occurrences = 2;
        Termination t = new Termination(start, ChronoUnit.MONTHS, occurrences);

        assertEquals(start.plusMonths(occurrences - 1), t.terminationDateInclusive());
        assertEquals(occurrences, t.numberOfOccurrences());
    }
}

