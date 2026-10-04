package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class Wallet {

    private double balance;

    public Wallet(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    /** Withdraws money from the wallet and sends it to the user's bank account. */
    public void withdrawToBank(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds: balance is " + balance + ", requested " + amount,
                amount - balance);
        }
        balance -= amount;
        System.out.println("Transferred " + amount + " to bank account. Remaining balance: " + balance);
    }
}