package edu.spu.se411.lab08.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab08.sensor.Sensor;

public class LoggerObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(LoggerObserver.class);

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor sensor) {
            System.out.printf("[Logger] %s reading changed to %.1f %s%n",
                    sensor.getName(), sensor.getReading(), sensor.getUnit());
            logger.info("{} reading changed to {} {}",
                    sensor.getName(), sensor.getReading(), sensor.getUnit());
        }
    }
}
