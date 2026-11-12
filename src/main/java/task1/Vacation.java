package task1;

import java.time.LocalDate;

/**
 * Представляет период отпуска
 *
 * @param startDate начальная дата отпуска
 * @param endDate конечная дата отпуска
 */
public record Vacation(LocalDate startDate, LocalDate endDate) implements CalendarRule {

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
     * Проверка на попадание указанной даты в период отпуска
     *
     * @param date проверяемая дата
     * @return true, если эта дата входит в период отпуска
     */
    @Override
    public boolean appliesTo(LocalDate date) {
        return contains(date);
    }

    /**
     * Возвращает тип дня для отпуска
     *
     * @return VACATION
     */
    @Override
    public DayType getDayType() {
        return DayType.VACATION;
    }

    /**
     * Возвращает проверку вхождения конкретной даты в сроки отпуска
     *
     * @param date конкретная дата
     *
     * @return true, если дата попадает в период отпуска
     */
    public boolean contains(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
