package task1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Представляет производственный календарь на определённый год.
 */
public class ProdCalendar {

    private final int year;
    private final List<Holiday> holidays;
    private final List<Vacation> vacations;
    private final List<CalendarRule> rules;

    /**
     * Создаёт производственный календарь.
     *
     * @param year год календаря
     * @param holidays список праздников
     * @param vacations список отпусков
     */
    public ProdCalendar(int year, List<Holiday> holidays, List<Vacation> vacations) {
        if (year < 1) {
            throw new IllegalArgumentException("Год должен быть положительным");
        }

        this.year = year;
        this.holidays = List.copyOf(holidays);
        this.vacations = List.copyOf(vacations);

        List<CalendarRule> rules = new ArrayList<>();
        rules.addAll(this.holidays);
        rules.addAll(this.vacations);

        this.rules = List.copyOf(rules);
    }

    /**
     * Возвращает список праздников календаря
     *
     * @return список праздников
     */
    public List<Holiday> getHolidays() {
        return holidays;
    }

    /**
     * Определяет тип указанного дня.
     *
     * @param date проверяемая дата
     * @return тип дня
     * @throws IllegalArgumentException если дата равна null
     * @throws CalendarRuleException если дата относится к другому году
     */
    public DayType getDayType(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }

        if (date.getYear() != year) {
            throw new CalendarRuleException("Дата не относится к году данного календаря");
        }

        for (CalendarRule rule : rules) {
            if (rule.getDayType() == DayType.HOLIDAY && rule.appliesTo(date)) {
                return rule.getDayType();
            }
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY ||
                dayOfWeek == DayOfWeek.SUNDAY) {
            return DayType.WEEKEND;
        }

        for (CalendarRule rule : vacations) {
            if (rule.getDayType() == DayType.VACATION && rule.appliesTo(date)) {
                return rule.getDayType();
            }
        }

        for (CalendarRule rule : rules) {
            if (rule.getDayType() == DayType.HOLIDAY
                    && rule.appliesTo(date.plusDays(1))) {
                return DayType.SHORTENED_WORKDAY;
            }
        }

        return DayType.WORKDAY;
    }

    /**
     * Вычисляет количество рабочих дней за месяц
     *
     * @param month номер месяца
     * @return количество рабочих дней
     */
    public int getWorkingDaysInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Номер месяца должен быть от 1 до 12"
            );
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        int workingDays = 0;

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            DayType dayType = getDayType(date);

            if (dayType == DayType.WORKDAY ||
                    dayType == DayType.SHORTENED_WORKDAY) {
                workingDays++;
            }
        }

        return workingDays;
    }

    /**
     * Вычисляет количество рабочих дней за квартал
     *
     * @param quarter номер квартала
     * @return количество рабочих дней
     */
    public int getWorkingDaysInQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException(
                    "Номер квартала должен быть от 1 до 4"
            );
        }

        int workingDays = 0;
        int firstMonth = (quarter - 1) * 3 + 1;

        for (int month = firstMonth; month < firstMonth + 3; month++) {
            workingDays += getWorkingDaysInMonth(month);
        }

        return workingDays;
    }

    /**
     * Вычисляет количество рабочих дней за год
     *
     * @return количество рабочих дней
     */
    public int getWorkingDaysInYear() {
        int workingDays = 0;

        for (int month = 1; month <= 12; month++) {
            workingDays += getWorkingDaysInMonth(month);
        }

        return workingDays;
    }

    /**
     * Вычисляет количество рабочих часов за месяц
     *
     * @param month номер месяца
     * @return количество рабочих часов
     */
    public int getWorkingHoursInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Номер месяца должен быть от 1 до 12"
            );
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        int workingHours = 0;

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            workingHours += getDayType(date).getWorkingHours();
        }

        return workingHours;
    }

    /**
     * Вычисляет количество рабочих часов за квартал
     *
     * @param quarter номер квартала
     * @return количество рабочих часов
     */
    public int getWorkingHoursInQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException(
                    "Номер квартала должен быть от 1 до 4"
            );
        }

        int workingHours = 0;
        int firstMonth = (quarter - 1) * 3 + 1;

        for (int month = firstMonth; month < firstMonth + 3; month++) {
            workingHours += getWorkingHoursInMonth(month);
        }

        return workingHours;
    }

    /**
     * Вычисляет количество рабочих часов за год
     *
     * @return количество рабочих часов
     */
    public int getWorkingHoursInYear() {
        int workingHours = 0;

        for (int month = 1; month <= 12; month++) {
            workingHours += getWorkingHoursInMonth(month);
        }

        return workingHours;
    }
}
