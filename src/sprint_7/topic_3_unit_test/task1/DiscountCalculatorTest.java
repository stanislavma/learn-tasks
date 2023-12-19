package sprint_7.topic_3_unit_test.task1;

class DiscountCalculatorTest {

    DiscountCalculator discountCalculator = new DiscountCalculator();

    public void shouldGiveNoDiscountForValue999() {
        // Подготовка
        int buySum = 999;
        int expectedSum = 999;

        // Исполнение
        int resultSum = discountCalculator.sumAfterDiscount(buySum);

        // Проверка
        TestLibrary.assertEquals(expectedSum, resultSum);
    }

    public static void main(String[] args) {
        DiscountCalculatorTest test = new DiscountCalculatorTest();
        test.shouldGiveNoDiscountForValue999();
    }
}

class TestLibrary {
    public static <T> void assertEquals(T expected, T actual) {
        if (!expected.equals(actual)) {
            throw new RuntimeException(String.format("actual value [%s] not equal to expected [%s]", actual, expected));
        }
    }
}

class DiscountCalculator {

    public int sumAfterDiscount(int sum) {
        if (sum < 1000) {
            return sum;
        } else {
            return (int) (sum * 0.98);
        }
    }
}