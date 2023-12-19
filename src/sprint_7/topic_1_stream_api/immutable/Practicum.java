package sprint_7.topic_1_stream_api.immutable;

import java.util.List;
import java.util.stream.Collectors;

public class Practicum {

    public static void main(String[] args) {
        List<Integer> inputNumbers = List.of(2, 5, 4, 2, 3, 8);

        List<Integer> evenNumbers =  inputNumbers.stream()
                .filter( n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);
    }
}