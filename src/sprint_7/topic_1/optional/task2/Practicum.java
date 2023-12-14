package sprint_7.topic_1.optional.task2;

import java.util.Optional;

public class Practicum {
    public static void main(String[] args) {
        // Доработайте данный метод, чтобы на экран выводилось 
        // сообщение в соответствии с заданием

        SearchService searchService = new SearchService();

        Optional<Candy> candy = searchService.search("Африка");

        if (candy.isPresent()) {
            System.out.println("Товар " + candy.get().name + " доступен в количестве " + candy.get().amount + " кг по цене " + candy.get().price + " руб за кг");
        } else {
            System.out.println("Данный товар не найден.");
        }

    }
}