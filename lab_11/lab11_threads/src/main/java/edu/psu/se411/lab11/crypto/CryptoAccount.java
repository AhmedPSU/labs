package edu.psu.se411.lab11.crypto;

/**
 * A simple crypto-currency account holding a balance in riyals.
 * NOTE: deposit/withdraw are NOT synchronized on purpose (read-modify-write race).
 */
public class CryptoAccount {

	private final int id;
	private double balance;

	public CryptoAccount(int id, double initialBalance) {
		this.id = id;
		this.balance = initialBalance;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		double newBalance = balance + amount; // read
		Thread.yield();                        // widens the race window so the problem is visible
		balance = newBalance;                  // write (may overwrite another thread's update)
	}

	public void withdraw(double amount) {
		double newBalance = balance - amount;
		Thread.yield();
		balance = newBalance;
	}

	@Override
	public String toString() {
		return String.format("Account %d: %.2f SAR", id, balance);
	}
}
