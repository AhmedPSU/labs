package edu.psu.se411.lab07.model;

import java.time.LocalDate;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public abstract class Booking {

    private final String bookingId;
    private final String customerName;
    private final LocalDate travelDate;
    private final String destination;

    protected Booking(String bookingId, String customerName, LocalDate travelDate, String destination) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelDate = travelDate;
        this.destination = destination;
    }

    /** Each booking type has its own pricing rule. */
    public abstract double computeTotalPrice() throws MissingInformationException, InvalidArgumentException;

    protected static <T> T requireProvided(T value, String fieldName) throws MissingInformationException {
        if (value == null) {
            throw new MissingInformationException(fieldName + " has not been provided");
        }
        return value;
    }

    protected static void requireInRange(double value, double min, double max, String fieldName)
            throws InvalidArgumentException {
        if (value < min || value > max) {
            throw new InvalidArgumentException(
                    fieldName + " must be between " + min + " and " + max + " but was " + value);
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + bookingId + ", " + customerName + ", "
                + travelDate + ", " + destination + "]";
    }
}