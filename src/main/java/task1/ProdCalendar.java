package task1;

import java.util.List;
import java.time.LocalDate;
import java.time.DayOfWeek;

/**
 * Представляет производственный календарь на год
 */
public class ProdCalendar {

    private final int year;
    private final List<Holiday> holidays;

    /**
     * Сооздает календарь для указанного года
     *
     * @param year     год календаря
     * @param holidays список праздников
     * @throws IllegalArgumentException если год < 1
     */
    public ProdCalendar(int year, List<Holiday> holidays) {
        if (year < 1) {
            throw new IllegalArgumentException("Год должен быть положительным");
        }

        this.year = year;
        this.holidays = List.copyOf(holidays);
    }

    /**
     * возвращает год календаря
     *
     * @return год календаря
     */
    public int getYear() {
        return year;
    }

    /**
     * возвращает список праздников в указанный год
     *
     * @return список праздников
     */
    public List<Holiday> getHolidays() {
        return holidays;
    }

    /**
     * Возвращает тип конкретного дня
     *
     * @param date дата конкретного дня
     *
     * @return тип конкретного дня
     */
    public DayType getDayType(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }

        if (date.getYear() != year) {
            throw new IllegalArgumentException("Дата не относится к году данного календаря");
        }

        for (Holiday holiday : holidays) {
            if (holiday.date().equals(date)) {
                return DayType.HOLIDAY;
            }
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return DayType.WEEKEND;
        }

        return DayType.WORKDAY;
    }
}
