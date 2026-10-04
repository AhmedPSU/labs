package edu.spu.se411.lab08;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab08.observer.DashboardObserver;
import edu.spu.se411.lab08.observer.LoggerObserver;
import edu.spu.se411.lab08.sensor.HumiditySensor;
import edu.spu.se411.lab08.sensor.Sensor;
import edu.spu.se411.lab08.sensor.TemperatureSensor;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        Random random = new Random();

        TemperatureSensor temp = new TemperatureSensor();
        HumiditySensor humidity = new HumiditySensor();

        DashboardObserver dashboard = new DashboardObserver();
        LoggerObserver loggerObserver = new LoggerObserver();

        temp.register(dashboard);
        temp.register(loggerObserver);
        humidity.register(dashboard);
        humidity.register(loggerObserver);

        for (int i = 0; i < 10; i++) {
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Clone demo: the clone keeps the data but has its own (empty) observer list
        System.out.println("--- Cloning the temperature sensor ---");
        Sensor tempClone = temp.clone();
        System.out.println("Clone reading copied: " + tempClone.getReading());

        System.out.println("Changing the clone: nobody is registered, so nothing should print.");
        tempClone.setReading(99.0);

        tempClone.register(dashboard);
        System.out.println("Registered a dashboard on the clone only:");
        tempClone.setReading(50.0);

        System.out.println("Changing the original: both original observers notified, clone's not affected.");
        temp.setReading(25.0);

        logger.info("Application ended.");
    }
}