package edu.psu.se411.lab11.crypto;

/**
 * Part 2: the same transaction, fixed with the "synchronized" keyword on ONE method.
 *
 * The method is static synchronized, so it locks on SynchronizedTransaction.class:
 * only one transfer can run at a time across ALL threads.
 *
 * Why not just "public synchronized void execute()"? Every thread creates its own
 * Transaction object, and an instance-synchronized method locks on "this", so each
 * thread would hold a different lock and nothing would be protected.
 */
public class SynchronizedTransaction extends Transaction {

	public SynchronizedTransaction(CryptoAccount sender, CryptoAccount receiver, double amount) {
		super(sender, receiver, amount);
	}

	@Override
	public void execute() {
		safeTransfer(sender, receiver, amount);
	}

	private static synchronized void safeTransfer(CryptoAccount sender, CryptoAccount receiver, double amount) {
		transfer(sender, receiver, amount);
	}
}
