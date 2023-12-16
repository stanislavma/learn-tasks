package sprint_7.topic_4.task_3;

class AirportDatabase {
    private static Airport vnukovo = new Airport(
            "Внуково",
            "Москва",
            "MOSCOW          ",
            "Europe/Moscow"
    );

    private static Airport pulkovo = new Airport(
            "Пулково",
            "Санкт-Петербург",
            "SAINT-PETERSBURG",
            "Europe/Moscow"
    );

    private static Airport vladivostok = new Airport(
            "Владивосток",
            "Владивосток",
            "VLADIVOSTOK     ",
            "Asia/Vladivostok"
    );

    private static Airport koltsovo = new Airport(
            "Кольцово",
            "Екатеринбург",
            "YEKATERINBURG   ",
            "Asia/Yekaterinburg"
    );

    public static Airport getAirportByCode(String airportCode) {
        /* С помощью оператора switch case верните правильный аэропорт по его коду:
         * VKO - vnukovo
         * LED - pulkovo
         * SVX - koltsovo
         * VVO - vladivostok
         * Для неверного кода пробросьте исключение.
         */

        switch (airportCode) {
            case "VKO":
                return AirportDatabase.vnukovo;

            case "LED":
                return AirportDatabase.pulkovo;

            case "SVX":
                return AirportDatabase.koltsovo;

            case "VVO":
                return AirportDatabase.vladivostok;

            default:
                throw new IllegalStateException("Неизвестный код аэропорта: " + airportCode);
        }
    }
}