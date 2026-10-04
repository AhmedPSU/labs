package edu.spu.se411.lab08.sensor;

public class TemperatureSensor extends Sensor {

    public TemperatureSensor() {
        super("Temperature");
    }

    @Override
    public String getUnit() {
        return "C";
    }
}