package sprint_7.topic_2;

import java.time.*;
import java.util.Random;

import static java.time.Month.JANUARY;

class Practicum {
    public static void main(String[] args) throws InterruptedException {
        LocalDate today = LocalDate.now();
        // напишите здесь свою дату рождения
        LocalDate birthday = LocalDate.of(1990, JANUARY, 1);

        Period age = Period.between(birthday, today);
        System.out.println("Ваш возраст:");
        System.out.println("Лет: " + age.getYears());
        System.out.println("Месяцев: " + age.getMonths());
        System.out.println("Дней: " + age.getDays());


        LocalDateTime now = LocalDateTime.now();
        // напишите здесь свою дату рождения с точностью до часов и минут
        int yearOfBirth = 1990;
        Month monthOfBirth = JANUARY;
        int dayOfMonth = 1;
        int hourOfBirth = 9;
        int minuteOfBirth = 0;
        LocalDateTime brth = LocalDateTime.of(yearOfBirth, monthOfBirth, dayOfMonth, hourOfBirth, minuteOfBirth);

        Duration duration = Duration.between(brth, now);

        System.out.println("На момент запуска кода вы прожили " + duration.getSeconds()
                + " секунд и " + duration.getNano() + " наносекунд.");

        LocalTime start = LocalTime.of(0, 0);
        LocalTime finish = LocalTime.of(1, 30);

        // находим продолжительность между двумя единицами времени
        Duration ninetyMinutes = Duration.between(start, finish);

        LocalTime noon = LocalTime.of(12, 0);
        System.out.println("Старый момент времени: " + noon);
        // прибавляем к созданному моменту вычисленную продолжительность
        LocalTime newTime = noon.plus(ninetyMinutes);

        System.out.println("Новый момент времени: " + newTime);


        Random random = new Random();

        // фиксируем начало выполнения кода
        LocalDateTime startDate = LocalDateTime.now();

        // производим действия, время исполнения которых хотим замерить
        int num = random.nextInt(5000);
        Thread.sleep(num); // останавливаем выполнение кода на произвольное время

        // фиксируем конец выполнения кода
        LocalDateTime finishDate = LocalDateTime.now();

        // находим продолжительность между двумя моментами
        Duration durationValue = Duration.between(startDate, finishDate);

        // выводим результат
        System.out.println("Код выполнился за " + durationValue.getSeconds() + "." + durationValue.getNano() + "с.");

        LocalTime secondTime = LocalTime.of(23, 40);
        LocalTime firstTime = LocalTime.of(14, 25);

        Duration durationVal = Duration.between(firstTime, secondTime);

        System.out.println("Между двумя моментами времени:");
        System.out.println(durationVal.toHours() + " часов, " + durationVal.toMinutesPart() + " минут,");
        System.out.println("или " + durationVal.toMinutes() + " минут.");


    }
}