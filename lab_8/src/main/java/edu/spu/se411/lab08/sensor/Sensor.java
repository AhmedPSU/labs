package edu.spu.se411.lab08.sensor;

import java.util.ArrayList;
import java.util.List;

import edu.spu.se411.lab08.observer.Observer;
import edu.spu.se411.lab08.observer.Subject;

public abstract class Sensor implements Subject, Cloneable {

    private final String name;
    private double reading;
    private List<Observer> observers = new ArrayList<>();

    protected Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getReading() {
        return reading;
    }

    /** Unit shown next to the reading, e.g. "C" or "%". */
    public abstract String getUnit();

    /** Updates the reading and notifies observers only if the value changed. */
    public void setReading(double newReading) {
        if (newReading != this.reading) {
            this.reading = newReading;
            notifyObservers();
        }
    }

    @Override
    public void register(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        // iterate over a copy so observers can unregister while being notified
        for (Observer o : new ArrayList<>(observers)) {
            o.update(this);
        }
    }

    /** The clone keeps the sensor's data but starts with its own, empty observer list. */
    @Override
    public Sensor clone() {
        try {
            Sensor copy = (Sensor) super.clone();
            copy.observers = new ArrayList<>();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Sensor is Cloneable, this cannot happen", e);
        }
    }
}