package sprint_7.topic_1_stream_api.optional.task1;

import java.util.stream.Stream;

public class OptionalInStreams {

    public static void main(String[] args) {
        Stream.of(1, 7, 3, 7, 5)
                .filter(number -> number % 2 == 0)
                .findFirst()
                .ifPresentOrElse(
                        //если Optional не пуст
                        System.out::println,
                        //если Optional пуст
                        () -> System.out.println("Чётное число не найдено")
                );

    }
}