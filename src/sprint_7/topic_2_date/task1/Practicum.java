package sprint_7.topic_2_date.task1;

import java.time.Instant;
import java.util.Random;

public class Practicum {
    public static void main(String[] args) {
        long millis = 9_000_000_000L; // количество миллисекунд

        System.out.println(Instant.ofEpochMilli(millis)); // дата millis миллисекунд после Unix-эпохи
        System.out.println(Instant.ofEpochMilli(-millis)); // дата millis миллисекунд до Unix-эпохи

        //Пицца разогревается 180 секунд - это 3 минуты
        long seconds = 180;

        Instant currentMoment = Instant.now();
        System.out.println("Сейчас " + currentMoment);
        Instant futureMoment = currentMoment.plusSeconds(seconds);
        System.out.println("А через " + seconds + " секунд будет " + futureMoment
                + " и пицца будет готова!");

        int chickenUnixSecond = new Random().nextInt(1000000000);
        Instant chickenMoment = Instant.ofEpochSecond(chickenUnixSecond);

        int eggUnixSecond = new Random().nextInt(1000000000);
        Instant eggMoment = Instant.ofEpochSecond(eggUnixSecond);

        System.out.println("Курица появилась в " + chickenMoment);
        System.out.println("Яйцо появилось в " + eggMoment);

        if (chickenMoment.isBefore(eggMoment)) {
            System.out.println("Первой была курица!");
        }
        if (chickenMoment.isAfter(eggMoment)) {
            System.out.println("Первым было яйцо!");
        }
        if (chickenMoment.equals(eggMoment)) {
            System.out.println("Яйцо было одновременно с курицей ¯\\_(ツ)_/¯)!");
        }


    }
}