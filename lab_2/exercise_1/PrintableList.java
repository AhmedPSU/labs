package exercise_1;

import java.util.Arrays;
import java.util.List;

public class PrintableList<T> {

    private final List<T> items;

    // Constructor that accepts an array of items
    public PrintableList(T[] array) {
        this.items = Arrays.asList(array);
    }

    // Print all items of the list
    public void printItems() {
        for (T item : items) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie"};

        PrintableList<String> list = new PrintableList<>(names);
        list.printItems();
    }
}