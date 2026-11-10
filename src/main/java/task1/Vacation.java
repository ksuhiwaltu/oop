package task1;

import java.time.LocalDate;

/**
 * Представляет период отпуска
 *
 * @param startDate начальная дата отпуска
 * @param endDate конечная дата отпуска
 */
public record Vacation(LocalDate startDate, LocalDate endDate) {

    public Vacation {
        if (startDate == null) {
            throw new IllegalArgumentException("Дата начала отпуска не может быть null");
        }

        if (endDate == null) {
            throw new IllegalArgumentException("Дата окончания отпуска не может быть null");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Дата окончания отпуска не может быть раньше даты начала");
        }
    }

    /**
     * Возвращает проверку вхождения конкретной даты в сроки отпуска
     *
     * @param date конкретная дата
     *
     * @return проверку вхождения конкретной даты в сроки отпуска
     */
    public boolean contains(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
