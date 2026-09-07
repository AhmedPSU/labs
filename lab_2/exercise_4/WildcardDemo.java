package exercise_4;

import java.util.Arrays;
import java.util.List;

public class WildcardDemo {

    // 1. Accepts a list of ANY type using <?>
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    // 2. Accepts Number or any subclass using <? extends Number>
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob");
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.0);

        printList(names);
        printList(ints);
        printList(doubles);

        System.out.println("Sum of ints:    " + sumNumbers(ints));
        System.out.println("Sum of doubles: " + sumNumbers(doubles));
    }
}