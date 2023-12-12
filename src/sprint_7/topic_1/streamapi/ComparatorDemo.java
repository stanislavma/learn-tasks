package sprint_7.topic_1.streamapi;

import java.util.Arrays;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Circle> circles = Arrays.asList(
                new Circle(3, 5, 12),
                new Circle(1, -2, 4),
                new Circle(8, 8, 8),
                new Circle(5, 1, 7)
        );

        // вместо компаратора используем лямбду
        circles.sort((Circle circle1, Circle circle2) -> {
            return circle1.getRadius() - circle2.getRadius();
        });

        for (Circle circle : circles) {
            System.out.println(circle);
        }
    }
}

class Circle {
    private int centerX;
    private int centerY;
    private int radius;

    public Circle(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Радиус окружности: " + radius
                + ", координаты: "
                + centerX
                + ", "
                + centerY;
    }
}

