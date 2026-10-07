package edu.psu.se411.lab09_inventory.model;

import java.util.ArrayList;
import java.util.List;

import edu.psu.se411.lab09_inventory.strategy.SearchStrategy;

public class Inventory<T> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /** Returns every item matching the strategy (works with a strategy for T or any supertype of T). */
    public List<T> findItems(SearchStrategy<? super T> strategy) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (strategy.matches(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Same search, but restricted to one subtype of T. This lets a strategy written for
     * Book (or ElectronicDevice) be used on a mixed Inventory<Item>.
     */
    public <S extends T> List<S> findItems(Class<S> type, SearchStrategy<? super S> strategy) {
        List<S> result = new ArrayList<>();
        for (T item : items) {
            if (type.isInstance(item)) {
                S typed = type.cast(item);
                if (strategy.matches(typed)) {
                    result.add(typed);
                }
            }
        }
        return result;
    }
}