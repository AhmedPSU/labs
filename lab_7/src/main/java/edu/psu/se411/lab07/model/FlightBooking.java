package edu.psu.se411.lab07.model;

import java.time.LocalDate;

import edu.psu.se411.lab07.config.AppConfig;
import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public class FlightBooking extends Booking {

    private final double basePrice;
    private Double luggageWeightKg; // entered later by the customer

    public FlightBooking(String bookingId, String customerName, LocalDate travelDate,
            String destination, double basePrice) {
        super(bookingId, customerName, travelDate, destination);
        this.basePrice = basePrice;
    }

    public void setLuggageWeightKg(Double luggageWeightKg) {
        this.luggageWeightKg = luggageWeightKg;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        double weight = requireProvided(luggageWeightKg, "Luggage weight");
        requireInRange(weight, AppConfig.MIN_LUGGAGE_WEIGHT, AppConfig.MAX_LUGGAGE_WEIGHT, "Luggage weight");
        return basePrice + weight * AppConfig.EXTRA_LUGGAGE_RATE;
    }
}