package exercise_2;

import java.util.List;

public class NumberBox<T extends Number> {

    private T item;

    // a. Store an item of type T
    public void setItem(T item) {
        this.item = item;
    }

    // b. Retrieve the stored item
    public T getItem() {
        return item;
    }

    // 3. Add two numbers
    public static double add(Number a, Number b) {
        return a.doubleValue() + b.doubleValue();
    }

    // 3. Sum a list of numbers
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }
}