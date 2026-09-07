package exercise_2;

import java.util.Arrays;
import java.util.List;

public class NumberBoxTest {

    public static void main(String[] args) {
        // Test with Integer
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.setItem(10);
        System.out.println("Integer item: " + intBox.getItem());

        // Test with Double
        NumberBox<Double> doubleBox = new NumberBox<>();
        doubleBox.setItem(5.5);
        System.out.println("Double item: " + doubleBox.getItem());

        // Test add() with mixed types
        System.out.println("add(3, 4.5) = " + NumberBox.add(3, 4.5));

        // Test sum()
        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.0);

        System.out.println("Sum of integers: " + NumberBox.sum(ints));
        System.out.println("Sum of doubles:  " + NumberBox.sum(doubles));
    }
}