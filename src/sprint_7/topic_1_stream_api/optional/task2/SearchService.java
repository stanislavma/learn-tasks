package sprint_7.topic_1_stream_api.optional.task2;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class SearchService {
    // Создаём объект класса, отвечающий за склад магазина
    private final Warehouse warehouse = new Warehouse();
    // Создаём объект класса, отвечающий за работу с поставщиками
    public final SRM srm = new SRM();

    // Основной метод поиска
    // Проверяет наличие товара с указанным именем на складе магазина
    // Если товар отсутствует, то проверяются склады поставщиков,
    // предпочтение отдаётся тому поставщику, у которого наименьшая цена товара.
    // Для поиска товара на складе поставщиков используется метод supplierSearch
    // Если товар нигде не найден, то возвращается пустой Optional
    public Optional<Candy> search(String candyName) {
        Optional<Candy> candy = Optional.empty();

        if (!candyName.isEmpty()) {
            candy = warehouse.search(candyName);

            if (candy.isEmpty()) {
                candy = supplierSearch(candyName);
            }

        } else {
            candy = Optional.empty();
        }

        return candy;

    }

    // Ищет товар с указанным именем на складах поставщиков
    // Возвращает Optional с самым дешевым вариантом товара среди всех
    // поставщиков или пустой Optional, если товар не найден
    private Optional<Candy> supplierSearch(String candyName) {
        // Реализуйте данный метод при помощи Stream API и Optional,
        // используйте метод min из Stream API для нахождения товара с наименьшей ценой

        Optional<Candy> findCandy = srm.listSuppliers().stream()
                .map(supplier -> srm.getProduct(supplier, candyName)) // Получаем конфету у каждого поставщика
                .filter(Objects::nonNull) // Исключаем null значения (т.е. конфеты, которые не найдены)
                .max(Comparator.comparing(candy -> candy.price));

        return findCandy;
    }
}