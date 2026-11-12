package task1;

/**
 * Исключение, возникающее при нарушении правил производственного календаря
 */
public class CalendarRuleException extends RuntimeException {
    /**
     * Создаёт исключение с указанным сообщением
     *
     * @param message описание причины исключения
     */
    public CalendarRuleException(String message) {
        super(message);
    }
}
