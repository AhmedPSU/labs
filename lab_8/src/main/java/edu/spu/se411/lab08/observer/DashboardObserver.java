package edu.spu.se411.lab08.observer;

import edu.spu.se411.lab08.sensor.Sensor;

public class DashboardObserver implements Observer {

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor sensor) {
            System.out.printf("[Dashboard] %s: %.1f %s%n",
                    sensor.getName(), sensor.getReading(), sensor.getUnit());
        }
    }
}