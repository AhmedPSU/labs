package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.exceptions.InvalidAgeException;

public class App {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age " + age + " is invalid: must be 18 or older");
        }
        System.out.println("Age valid.");
    }

    public static void main(String[] args) {
        // Exercise 1
        try {
            validateAge(20);
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // Exercise 2
        Wallet wallet = new Wallet(100.0);
        try {
            wallet.withdrawToBank(40.0);
            wallet.withdrawToBank(80.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Short by: " + e.getShortfall());
        }
    }
}