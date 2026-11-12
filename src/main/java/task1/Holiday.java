package task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Представляет праздничный день производственного календаря
 *
 * @param date дата праздника
 * @param name название праздника
 * @param transferable можно ли перенести выходной
 */
public record Holiday(LocalDate date, String name, boolean transferable) implements CalendarRule {

    public Holiday {
        if (date == null) {
            throw new IllegalArgumentException("Дата праздника не может быть null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя праздника не может быть пустым");
        }
    }

    @Override
    public boolean appliesTo(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public DayType getDayType() {
        return DayType.HOLIDAY;
    }



    public static List<Holiday> getHolidaysForYear(int year) {

        List<Holiday> holidays = new ArrayList<>();

        holidays.add(new Holiday(LocalDate.of(year, 1, 1),"Новый год", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 2),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 3),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 4),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 5),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 6),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year, 1, 8),"Новогодние каникулы", false));
        holidays.add(new Holiday(LocalDate.of(year,1,7),"Рождество",false));
        holidays.add(new Holiday(LocalDate.of(year,2,23),"День защитника Отечества",false));
        holidays.add(new Holiday(LocalDate.of(year,3,8),"Международный женский день",false));
        holidays.add(new Holiday(LocalDate.of(year,5,1),"Праздник Весны и Труда",false));
        holidays.add(new Holiday(LocalDate.of(year,5,9),"День Победы",false));
        holidays.add(new Holiday(LocalDate.of(year,6,12),"День России",false));
        holidays.add(new Holiday(LocalDate.of(year,11,4),"День Народного Единства",false));


        return holidays;
    }
}
