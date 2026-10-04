package edu.psu.se411.lab07.model;

import java.time.LocalDate;

import edu.psu.se411.lab07.config.AppConfig;
import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public class TrainBooking extends Booking {

    private final SeatClass seatClass;
    private Double distanceKm; // entered later by the system

    public TrainBooking(String bookingId, String customerName, LocalDate travelDate,
            String destination, SeatClass seatClass) {
        super(bookingId, customerName, travelDate, destination);
        this.seatClass = seatClass;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        double distance = requireProvided(distanceKm, "Distance");
        requireInRange(distance, AppConfig.MIN_TRAIN_DISTANCE, AppConfig.MAX_TRAIN_DISTANCE, "Distance");
        return distance * seatClass.getRatePerKm();
    }
}