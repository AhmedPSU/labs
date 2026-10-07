package edu.psu.se411.lab09_inventory.strategy;

public interface SearchStrategy<T> {

    boolean matches(T item);

}