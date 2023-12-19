package sprint_7.topic_1_stream_api.optional;

import java.util.Optional;

public class OptionalNameDemo {

    public static void main(String[] args) {
        Optional<String> maybeName = requestUserName();
        if (maybeName.isPresent()) {
            //вставьте код здесь
            System.out.println("Привет, " + maybeName.get() + "!");
        } else {
            System.out.println("Ваше право не называть имя!");
        }
    }

    static Optional<String> requestUserName() {
        //Здесь должен быть запрос к пользователю, но пока его не реализовали
        //Поместите в переменную username имя пользователя или оставьте ее пустой, если имя скрыто
        Optional<String> username = Optional.empty();

        return username;
    }


}