package sprint_7.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {
        printRandomInts();
        printRandomDoubles();
        printIncrementingNumbers();

    }

    static Stream<Task> getTaskStream() {
        List<Task> tasks = new ArrayList<>();

        for (int id = 1; id <= 10; id++) {
            Task task = new Task(id);
            tasks.add(task);

            List<Integer> subtasks = new ArrayList<>();

            for (int sid = 1; sid <= 10; sid++) {
                subtasks.add(100 * task.getId() + sid);
            }
            task.setSubtasksId(subtasks);
        }

        return tasks.stream();
    }


    static void printRandomInts() {
        new Random().ints(10, 0, 11)
                .forEach(System.out::println);
    }

    static void printRandomDoubles() {
        Stream.generate(Math::random)
                .limit(11)
                .forEach(System.out::println);
    }

    static void printIncrementingNumbers() {
        Stream.iterate(0, n -> n + 1)
                .limit(11)
                .forEach(System.out::println);
    }


}
