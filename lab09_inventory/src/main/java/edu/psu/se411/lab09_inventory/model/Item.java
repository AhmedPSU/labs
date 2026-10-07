package edu.psu.se411.lab09_inventory.model;

public abstract class Item {

    private final int id;
    private final String name;

    protected Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", name=" + name + "]";
    }
}