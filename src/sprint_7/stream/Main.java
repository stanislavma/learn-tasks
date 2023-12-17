package sprint_7.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
       List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            numbers.add(i, i);
        }

        // Вычисление квадратного корня каждого числа в массиве
        long start = System.currentTimeMillis();

       Integer result = numbers.stream()
               .parallel()
               .map(Main::factorial)
               .reduce(0, Integer::sum);

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start) + " мс");

        System.out.println(result); // ~1000ms
    }
    private static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}
