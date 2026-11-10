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

    /**
     * Возвращает кол-во рабочих дней за месяц
     *
     * @param month конкретный месяц
     * @return кол-во рабочих дней за конкретный месяц
     */
    public int getWorkingDaysInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Некорректный номер месяца");
        }

        int workDaysInMonth = 0;
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();

        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate currentDate = LocalDate.of(year, month, i);
            DayType dayType = getDayType(currentDate);

            if (dayType == DayType.WORKDAY) {
                workDaysInMonth++;
            }
        }
        return workDaysInMonth;
    }

    /**
     * Возвращает кол-во рабочих дней за квартал
     *
     * @param quarter конкретный квартал
     * @return кол-во рабочих дней за квартал
     */
    public int getWorkingDaysInQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Некорректный номер квартала");
        }

        int startMonth = (quarter - 1) * 3 + 1;
        int workDaysInQuarter = 0;

        for (int month = startMonth; month <= startMonth + 2; month++) {
            int workingDaysInMonth = getWorkingDaysInMonth(month);
            workDaysInQuarter += workingDaysInMonth;
        }
        return workDaysInQuarter;
    }

    /**
     * Возвращает кол-во рабочих дней за год
     *
     * @return кол-во рабочих дней за год
     */
    public int getWorkingDaysInYear() {
        int firstMonth = 1;
        int lastMonth = 12;

        int workDaysInYear = 0;

        for (int month = firstMonth; month <= lastMonth; month++) {
            int workingDaysInMonth = getWorkingDaysInMonth(month);
            workDaysInYear += workingDaysInMonth;
        }
        return workDaysInYear;
    }

    /**
     * Возвращает кол-во рабочих часов за месяц
     *
     * @param month конкретный месяц
     * @return кол-во рабочих часов за месяц
     */
    public int getWorkingHoursInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Некорректный номер месяца");
        }

        int workingHoursInMonth = 0;
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();

        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate currentDate = LocalDate.of(year, month, i);
            DayType dayType = getDayType(currentDate);
            workingHoursInMonth += dayType.getWorkingHours();
        }
        return workingHoursInMonth;
    }

    /**
     * Возвращает кол-во рабочих часов за квартал
     *
     * @param quarter конкретный квартал
     *
     * @return кол-во рабочих часов за квартал
     */
    public int getWorkingHoursInQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Некорректный номер квартала");
        }

        int workingHoursInQuarter = 0;
        int startMonth = (quarter - 1) * 3 + 1;

        for (int month = startMonth; month <= startMonth + 2; month++) {
            int workingHoursInMonth = getWorkingHoursInMonth(month);
            workingHoursInQuarter += workingHoursInMonth;
        }
        return workingHoursInQuarter;
    }

    /**
     * Возвращает кол-во рабочих часов за год
     *
     * @return кол-во рабочих часов за год
     */
    public int getWorkingHoursInYear() {
        int firstMonth = 1;
        int lastMonth = 12;

        int workHoursInYear = 0;

        for (int month = firstMonth; month <= lastMonth; month++) {
            int workingHoursInMonth = getWorkingHoursInMonth(month);
            workHoursInYear += workingHoursInMonth;
        }
        return workHoursInYear;
    }
}
