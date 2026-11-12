package task1;

import java.time.LocalDate;

public interface CalendarRule {
    boolean appliesTo(LocalDate date);

    DayType getDayType();
}
