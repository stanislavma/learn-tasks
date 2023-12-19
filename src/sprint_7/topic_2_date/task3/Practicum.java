package sprint_7.topic_2_date.task3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;



class Practicum {
    public static void main(String[] args) {
        // передаём все единицы времени
        // год, месяц, день, часы, минуты, секунды, наносекунды
        LocalDateTime newMillennium = LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(newMillennium);
        // не передаём секунды и наносекунды
        LocalDateTime alsoNewMillennium = LocalDateTime.of(2000, 1, 1, 0, 0);
        System.out.println(alsoNewMillennium);

        // используем месяц из констант перечисления java.time.Month
        LocalDateTime dateTimeOfTwos = LocalDateTime.of(2222, FEBRUARY, 2, 22, 22);
        System.out.println(dateTimeOfTwos);

        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Сейчас " + currentDateTime);

        LocalDateTime twoWeeksAgo = currentDateTime.minusWeeks(2);
        System.out.println("Две недели назад было " + twoWeeksAgo);

        LocalDateTime currentDateTimeTest = LocalDateTime.now();
        System.out.println("Сейчас " + currentDateTimeTest);

        LocalDateTime newDateTime = currentDateTimeTest.plusMonths(3).plusWeeks(2);
        System.out.println("Через три с половиной месяца будет " + newDateTime);

        LocalDateTime newYear = LocalDateTime.of(2022, JANUARY, 1, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Сейчас " + now);
        if (now.isBefore(newYear)) {
            System.out.println("С наступающим!");
        }
        if (now.isAfter(newYear)) {
            System.out.println("С прошедшим!");
        }
        if (now.equals(newYear)) {
            System.out.println("С Новым годом!");
        }

        System.out.println("Было: " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        String formatDateTime = now.format(formatter);
        System.out.println("Стало: " + formatDateTime);

        LocalDateTime dateTime = LocalDateTime.parse("2021-12-21T21:21:21");
        System.out.println(dateTime);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        LocalDateTime anotherDateTime = LocalDateTime.parse("22.02.2022, 22:22", fmt);
        System.out.println(anotherDateTime);

        System.out.println(dateTime.isAfter(anotherDateTime));

        // создаём экземпляр LocalDate, в котором будет храниться 32-й день в году
        LocalDate someDate = LocalDate.ofYearDay(2000, 32);
        // это 1 февраля 2000 года

        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);

        // проверяем, больше ли местное время 10:50
        System.out.println(currentTime.isAfter(LocalTime.of(10, 50)));


        // создаём экземпляр LocalDate, в котором будет храниться дата 25 мая 2005 года
        LocalDate demoDate = LocalDate.of(2005, 5, 25);
        System.out.println(demoDate);

        // добавляем к 25 мая 2005 года 5 месяцев
        System.out.println(demoDate.plusMonths(5));

        // создадим по отдельности дату и время
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        // соберём их вместе — в экземпляр LocalDateTime
        LocalDateTime fullDateTime = LocalDateTime.of(date, time);
        System.out.println(fullDateTime);

        System.out.println("День программиста отмечается в " + PROGRAMMER_YEAR_DAY + "-й день в году.");
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        System.out.println("Сейчас " + currentYear + " год.");

        LocalDate programmerDay = LocalDate.ofYearDay(currentYear, PROGRAMMER_YEAR_DAY);
        System.out.println("Значит, в этом году день программиста — " + programmerDay.getDayOfMonth() +
                " " + programmerDay.getMonth());
        System.out.println("День недели — " + programmerDay.getDayOfWeek());

    }

    public static final int PROGRAMMER_YEAR_DAY = 256;
}