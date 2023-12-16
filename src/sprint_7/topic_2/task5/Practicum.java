package sprint_7.topic_2.task5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Practicum {
    public static void main(String[] args) {
        String input = "14 часов 09 минут. Месяц: 02, День: 14, Год: 1966.";

        printCorrectDateTime(input);
    }

    private static void printCorrectDateTime(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH часов mm минут. Месяц: MM, День: dd, Год: yyyy."); // определите входной формат 14 часов 09 минут. Месяц: 02, День: 14, Год: 1966.
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter); // сконвертируйте исходную строку в LocalDateTime

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm"); // определите выходной формат 14_02_1966|14:09
        System.out.println(dateTime.format(outputFormatter)); // выведите результат на экран
    } 
}