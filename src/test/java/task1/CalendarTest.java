package task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

public class CalendarTest {

    @Test
    void shouldReturnWorkday() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(DayType.WORKDAY, calendar.getDayType(LocalDate.of(2026, 6, 9)));
    }

    @Test
    void shouldReturnWeekend() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(DayType.WEEKEND, calendar.getDayType(LocalDate.of(2026, 6,13)));
    }

    @Test
    void shouldReturnHoliday() {
        Holiday holiday = new Holiday(LocalDate.of(2026, 6, 12), " День России", false);

        ProdCalendar calendar = new ProdCalendar(2026, List.of(holiday), List.of());
        assertEquals(DayType.HOLIDAY, calendar.getDayType(LocalDate.of(2026, 6, 12)));
    }

    @Test
    void shouldReturnShortenedWorkday() {
        Holiday holiday = new Holiday(LocalDate.of(2025, 3,8), "Международный женский день", false);

        ProdCalendar calendar = new ProdCalendar(2025, List.of(holiday), List.of());
        assertEquals(DayType.SHORTENED_WORKDAY, calendar.getDayType(LocalDate.of(2025, 3, 7)));
    }

    @Test
    void shouldReturnVacation() {
        Vacation vacation = new Vacation(LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 18));

        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of(vacation));
        assertEquals(DayType.VACATION, calendar.getDayType(LocalDate.of(2026, 6, 11)));
    }

    @Test
    void shouldCheckVacationDates() {
        Vacation vacation = new Vacation(LocalDate.of(2026, 7, 20), LocalDate.of(2026, 7, 29));

        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of(vacation));
        assertEquals(DayType.VACATION, calendar.getDayType(LocalDate.of(2026, 7,20)));
        assertEquals(DayType.VACATION, calendar.getDayType(LocalDate.of(2026, 7,29)));
    }

    @Test
    void shouldCountWorkingDaysInMonth() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(22, calendar.getWorkingDaysInMonth(6));
    }

    @Test
    void shouldCountWorkingHoursInMonth() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(),List.of());
        assertEquals(176, calendar.getWorkingHoursInMonth(6));
    }

    @Test
    void shouldCountWorkingDaysInQuarter() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(64, calendar.getWorkingDaysInQuarter(1));
    }

    @Test
    void shouldCountWorkingHoursInQuarter() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(512, calendar.getWorkingHoursInQuarter(1));
    }

    @Test
    void shouldCountWorkingDaysInYear() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertEquals(261, calendar.getWorkingDaysInYear());
    }

    @Test
    void shouldCountWorkingHoursInYear() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(),List.of());
        assertEquals(2088, calendar.getWorkingHoursInYear());
    }

    @Test
    void shouldThrowEcxepInvMonth() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertThrows(IllegalArgumentException.class,() -> calendar.getWorkingDaysInMonth(13));
    }

    @Test
    void shouldThrowEcxepInvQuarter() {
        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());
        assertThrows(IllegalArgumentException.class,() -> calendar.getWorkingDaysInQuarter(5));
    }

    @Test
    void shouldThrowEcxepInvYear() {
        assertThrows(IllegalArgumentException.class,() -> new ProdCalendar(0, List.of(), List.of()));
    }

    @Test
    void shouldCountWorkingDaysAndHours() {

        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of());

        assertEquals(22, calendar.getWorkingDaysInMonth(3));
        assertEquals(176, calendar.getWorkingHoursInMonth(3));
    }

    @Test
    void shouldCountWorkingDaysAndHoursWithHoliday() {
        Holiday holiday = new Holiday(LocalDate.of(2026,6,12), "День России", false);

        ProdCalendar calendar = new ProdCalendar(2026, List.of(holiday), List.of());
        assertEquals(DayType.SHORTENED_WORKDAY, calendar.getDayType(LocalDate.of(2026, 6, 11)));
        assertEquals(DayType.HOLIDAY, calendar.getDayType(LocalDate.of(2026, 6, 12)));

        assertEquals(21, calendar.getWorkingDaysInMonth(6));
        assertEquals(167, calendar.getWorkingHoursInMonth(6));
    }

    @Test
    void shouldNotCountVacationDays() {
        Vacation vacation = new Vacation(LocalDate.of(2026,6,1), LocalDate.of(2026, 6,5));

        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of(vacation));
        assertEquals(17, calendar.getWorkingDaysInMonth(6));
        assertEquals(136, calendar.getWorkingHoursInMonth(6));
    }

    @Test
    void shouldNotCountHoliday() {
        Holiday holiday = new Holiday(LocalDate.of(2026, 3, 8), "Международный женский день", false);

        ProdCalendar calendar = new ProdCalendar(2026, List.of(holiday), List.of());
        assertEquals(DayType.HOLIDAY, calendar.getDayType(LocalDate.of(2026, 3,8)));
        assertEquals(22, calendar.getWorkingDaysInMonth(3));
    }

    @Test
    void shouldNotCountWeekendDuringVacationAsWorkingDays() {
        Vacation vacation = new Vacation(LocalDate.of(2026,3,6), LocalDate.of(2026,3,10));

        ProdCalendar calendar = new ProdCalendar(2026, List.of(), List.of(vacation));
        assertEquals(19, calendar.getWorkingDaysInMonth(3));
        assertEquals(152, calendar.getWorkingHoursInMonth(3));
    }
}
