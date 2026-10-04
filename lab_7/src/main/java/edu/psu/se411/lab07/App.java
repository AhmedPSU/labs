package edu.psu.se411.lab07;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.model.Booking;
import edu.psu.se411.lab07.model.CarRentalBooking;
import edu.psu.se411.lab07.model.FlightBooking;
import edu.psu.se411.lab07.model.SeatClass;
import edu.psu.se411.lab07.model.TrainBooking;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    /** Works for any booking type thanks to polymorphism. */
    public static double computeTotalPrice(Booking booking)
            throws MissingInformationException, InvalidArgumentException {
        return booking.computeTotalPrice();
    }

    public static void main(String[] args) {
        logger.info("Application is starting...");

        List<Booking> bookings = new ArrayList<>();

        FlightBooking flightOk = new FlightBooking("F1", "Ali Hassan", LocalDate.of(2026, 11, 15), "Paris", 300);
        flightOk.setLuggageWeightKg(25.0);
        bookings.add(flightOk);

        FlightBooking flightMissing = new FlightBooking("F2", "Sara Khan", LocalDate.of(2026, 11, 16), "Rome", 250);
        bookings.add(flightMissing);

        FlightBooking flightInvalid = new FlightBooking("F3", "Omar Saleh", LocalDate.of(2026, 11, 17), "Berlin", 280);
        flightInvalid.setLuggageWeightKg(55.0);
        bookings.add(flightInvalid);

        TrainBooking trainOk = new TrainBooking("T1", "Lina Noor", LocalDate.of(2026, 12, 1), "Lyon", SeatClass.FIRST_CLASS);
        trainOk.setDistanceKm(450.0);
        bookings.add(trainOk);

        TrainBooking trainInvalid = new TrainBooking("T2", "Yusuf Ali", LocalDate.of(2026, 12, 2), "Milan", SeatClass.STANDARD);
        trainInvalid.setDistanceKm(5000.0);
        bookings.add(trainInvalid);

        CarRentalBooking carOk = new CarRentalBooking("C1", "Huda Rami", LocalDate.of(2026, 12, 10), "Nice", 60);
        carOk.setRentalDays(7);
        bookings.add(carOk);

        CarRentalBooking carMissing = new CarRentalBooking("C2", "Zaid Fahad", LocalDate.of(2026, 12, 11), "Madrid", 55);
        bookings.add(carMissing);

        for (Booking booking : bookings) {
            try {
                double price = computeTotalPrice(booking);
                System.out.println(booking + " -> total price: " + price);
                logger.info("{} priced at {}", booking, price);
            } catch (MissingInformationException | InvalidArgumentException e) {
                System.out.println(booking + " -> ERROR: " + e.getMessage());
                logger.error("Could not price {}: {}", booking, e.getMessage(), e);
            }
        }

        logger.info("Application ended.");
    }
}