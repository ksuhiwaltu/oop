package task1;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите действие: ");
        System.out.println("1. Подсчет рабочих дней и часов за месяц;");
        System.out.println("2. Подсчет рабочих дней и часов квартал;");
        System.out.println("3. Подсчет рабочих дней и часов за год;");
        System.out.println("4. Определить тип произвольной даты;");
        System.out.println("5. Вывести список праздников в выбранный год;");
        System.out.println("6. Печать календаря.");
        int choice = scanner.nextInt();

        System.out.println("Введите номер года: ");
        int year = scanner.nextInt();

        ProdCalendar calendar = new ProdCalendar(year, Holiday.getHolidaysForYear(year), List.of());

        if(choice == 1) {

            System.out.println("Введите номер месяца от 1 до 12: ");
            int month = scanner.nextInt();


            int workingDaysInMonth = calendar.getWorkingDaysInMonth(month);
            int workingHoursInMonth = calendar.getWorkingHoursInMonth(month);

            System.out.println("Количество рабочих дней: " + workingDaysInMonth);
            System.out.println("Количество рабочих часов: " + workingHoursInMonth);

        } else if (choice == 2) {

            System.out.println("Введите номер квартала от 1 до 4: ");
            int quarter = scanner.nextInt();

            int workingDaysInQuarter = calendar.getWorkingDaysInQuarter(quarter);
            int workingHoursInQuarter = calendar.getWorkingHoursInQuarter(quarter);

            System.out.println("Количество рабочих дней в " + quarter + " кварталe: " + workingDaysInQuarter);
            System.out.println("Количество рабочих часов в " + quarter + " кварталe: " + workingHoursInQuarter);

        } else if (choice == 3) {

            int workingDaysInYear = calendar.getWorkingDaysInYear();
            int workingHoursInYear = calendar.getWorkingHoursInYear();

            System.out.println("Количество рабочих дней в " + year + " году: " + workingDaysInYear);
            System.out.println("Количество рабочих часов в " + year + " году: " + workingHoursInYear);

        } else if (choice == 4){

            System.out.println("Введите месяц: ");
            int monthNumber = scanner.nextInt();
            System.out.println("Введите день: ");
            int dayNumber = scanner.nextInt();

            LocalDate date = LocalDate.of(year, monthNumber, dayNumber);

            DayType dayType = calendar.getDayType(date);
            System.out.println("Тип указанной даты: " + dayType);

        } else if (choice == 5) {
            for (Holiday holiday : calendar.getHolidays()) {
                System.out.println(holiday.date() + " - " + holiday.name() + " (" + holiday.date().getDayOfWeek() + ")");
            }
            
        } else if (choice == 6) {

            System.out.println("Введите номер месяца от 1 до 12: ");
            int month = scanner.nextInt();

            YearMonth yearMonth = YearMonth.of(year, month);
            int daysInMonth = yearMonth.lengthOfMonth();

            for (int day = 1; day <= daysInMonth; day++) {
                LocalDate date = LocalDate.of(year, month, day);

                System.out.println(day + " - " + calendar.getDayType(date));
            }

        } else {
            System.out.println("Некорректный ввод!");
        }
    }
}

