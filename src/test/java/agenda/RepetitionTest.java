package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepetitionTest {

    Repetition daily;
    Repetition weekly;
    LocalDate date1;
    LocalDate date2;

    @BeforeEach
    void setUp() {
        daily = new Repetition(ChronoUnit.DAYS);
        weekly = new Repetition(ChronoUnit.WEEKS);
        date1 = LocalDate.of(2025, 12, 10);
        date2 = LocalDate.of(2025, 12, 17);
    }

    @Test
    public void testFrequency() {
        assertEquals(ChronoUnit.DAYS, daily.getFrequency());
        assertEquals(ChronoUnit.WEEKS, weekly.getFrequency());
    }

    @Test
    public void testAddAndCheckException() {
        daily.addException(date1);
        assertTrue(daily.isException(date1), "La date doit être une exception");
        assertFalse(daily.isException(date2), "La date ne doit pas être une exception");
    }

    @Test
    public void testTermination() {
        Termination term = new Termination(date1, ChronoUnit.DAYS, 5);
        daily.setTermination(term);
        assertNotNull(daily.getTermination(), "La terminaison doit être définie");
        assertEquals(term, daily.getTermination(), "La terminaison doit correspondre à l'objet défini");
    }

    @Test
    public void testMultipleExceptions() {
        daily.addException(date1);
        daily.addException(date2);
        assertTrue(daily.isException(date1));
        assertTrue(daily.isException(date2));
    }

}