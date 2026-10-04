package edu.spu.se411.lab08.sensor;

public class HumiditySensor extends Sensor {

    public HumiditySensor() {
        super("Humidity");
    }

    @Override
    public String getUnit() {
        return "%";
    }
}