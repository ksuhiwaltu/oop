package task1;

/**
 * Определяет типы дней производственного календаря и кол-во рабочих часов для каждого типа
 */
public enum DayType {
    WORKDAY(8),
    WEEKEND(0),
    HOLIDAY(0),
    SHORTENED_WORKDAY(7),
    VACATION(0);

    private final int workingHours;

    DayType(int workingHours) {
        this.workingHours = workingHours;
    }

    /**
     * Возвращает кол-во рабочих часов для данного типа дня
     *
     * @return кол-во рабочих часов
     */
    public int getWorkingHours() {
        return workingHours;
    }
}
