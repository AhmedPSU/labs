package edu.psu.se411.lab07.model;

import java.time.LocalDate;

import edu.psu.se411.lab07.config.AppConfig;
import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public class CarRentalBooking extends Booking {

    private final double dailyRate;
    private Integer rentalDays; // entered later by the customer

    public CarRentalBooking(String bookingId, String customerName, LocalDate travelDate,
            String destination, double dailyRate) {
        super(bookingId, customerName, travelDate, destination);
        this.dailyRate = dailyRate;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        int days = requireProvided(rentalDays, "Number of rental days");
        requireInRange(days, AppConfig.MIN_RENTAL_DAYS, AppConfig.MAX_RENTAL_DAYS, "Number of rental days");
        return dailyRate * days;
    }
}