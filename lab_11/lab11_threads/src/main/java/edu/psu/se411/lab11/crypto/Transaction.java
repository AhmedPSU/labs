package edu.psu.se411.lab11.crypto;

/**
 * Part 1: a payment from a sender to a receiver, WITHOUT synchronization.
 * Many threads executing transactions at the same time cause lost updates,
 * so the total money in the system changes (double spending).
 */
public class Transaction {

	protected final CryptoAccount sender;
	protected final CryptoAccount receiver;
	protected final double amount;

	public Transaction(CryptoAccount sender, CryptoAccount receiver, double amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	public void execute() {
		transfer(sender, receiver, amount);
	}

	protected static void transfer(CryptoAccount sender, CryptoAccount receiver, double amount) {
		if (sender == receiver) {
			return;                         // paying yourself changes nothing
		}
		if (sender.getBalance() >= amount) { // check ...
			sender.withdraw(amount);         // ... then act (race: another thread may spend in between)
			receiver.deposit(amount);
		}
	}
}
