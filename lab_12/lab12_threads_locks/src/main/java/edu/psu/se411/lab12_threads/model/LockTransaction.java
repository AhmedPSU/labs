package edu.psu.se411.lab12_threads.model;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

/**
 * Part b/c: the whole transfer is protected by locking BOTH accounts
 * (sender first, then receiver).
 *
 * What could go wrong? DEADLOCK.
 *   Thread 1: A -> B  locks A, waits for B
 *   Thread 2: B -> A  locks B, waits for A
 * Each thread holds the lock the other one needs, so both wait forever.
 *
 * lockInterruptibly() is used so a stuck program can still be stopped with shutdownNow().
 */
public class LockTransaction implements Runnable {
	protected final String id;
	protected final CryptoAccount sender;
	protected final CryptoAccount receiver;
	protected final Double amount;

	public LockTransaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		if(id == null || sender == null || receiver == null || amount == null || amount < 0) throw new IllegalArgumentException();
		if(sender == receiver) throw new IllegalArgumentException("Cannot make a transaction where the sender and the receiver are the same account");
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	@Override
	public void run() {
		try {
			execute();
		} catch (InsufficientBalanceException e) {
			// not enough money: transaction rejected
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // stopped while waiting for a lock
		}
	}

	public void execute() throws InsufficientBalanceException, InterruptedException {
		lockBoth(sender, receiver);  // always sender first, then receiver -> can deadlock
		try {
			transfer();
		} finally {
			receiver.getLock().unlock();
			sender.getLock().unlock();
		}
	}

	/** Locks 'first' then 'second'. */
	protected static void lockBoth(CryptoAccount first, CryptoAccount second) throws InterruptedException {
		first.getLock().lockInterruptibly();
		try {
			second.getLock().lockInterruptibly();
		} catch (InterruptedException e) {
			first.getLock().unlock();
			throw e;
		}
	}

	/** Check and move the money; both locks are held, so this is atomic. */
	protected void transfer() throws InsufficientBalanceException {
		if(sender.getBalance() < amount) throw new InsufficientBalanceException(sender.getId());
		sender.retrieveFromBalance(amount);
		receiver.addToBalance(amount);
	}

	public String getId() {
		return id;
	}
}
