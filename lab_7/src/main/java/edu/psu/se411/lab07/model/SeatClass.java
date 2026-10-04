package edu.psu.se411.lab07.model;

import edu.psu.se411.lab07.config.AppConfig;

public enum SeatClass {

    STANDARD(AppConfig.TRAIN_STANDARD_RATE),
    FIRST_CLASS(AppConfig.TRAIN_FIRST_CLASS_RATE);

    private final double ratePerKm;

    SeatClass(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }
}