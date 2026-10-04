package edu.psu.se411.lab07.config;

public final class AppConfig {

    private AppConfig() {
    }

    public static final double EXTRA_LUGGAGE_RATE = 5.0;
    public static final double TRAIN_STANDARD_RATE = 0.10;
    public static final double TRAIN_FIRST_CLASS_RATE = 0.20;

    public static final double MIN_LUGGAGE_WEIGHT = 0;
    public static final double MAX_LUGGAGE_WEIGHT = 40;

    public static final double MIN_TRAIN_DISTANCE = 1;
    public static final double MAX_TRAIN_DISTANCE = 2000;

    public static final int MIN_RENTAL_DAYS = 1;
    public static final int MAX_RENTAL_DAYS = 30;
}