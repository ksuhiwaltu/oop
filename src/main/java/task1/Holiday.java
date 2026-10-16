package task1;

import java.time.LocalDate;

/**
 * Представляет праздничный день производственного календаря
 *
 * @param date дата праздника
 * @param name название праздника
 * @param transferable можно ли перенести выходной
 */
public record Holiday(LocalDate date, String name, boolean transferable) {

    public Holiday {
        if (date == null) {
            throw new IllegalArgumentException("Дата праздника не может быть null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя праздника не может быть пустым");
        }
    }
}
