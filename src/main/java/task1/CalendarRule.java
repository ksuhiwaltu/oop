package task1;

import java.time.LocalDate;

/**
 * Определяет общие правила для дней производственного календаря
 * Реализуется праздниками и периодами отпусков
 */
public interface CalendarRule {
    /**
     * Проверяет, распространяется ли правило на указанную дату
     *
     * @param date проверяемая дата
     * @return true, если правило применяется к этой дате
     */
    boolean appliesTo(LocalDate date);

    /**
     * Возвращает тип дня, определяемый правилом
     *
     * @return тип дня
     */
    DayType getDayType();
}
