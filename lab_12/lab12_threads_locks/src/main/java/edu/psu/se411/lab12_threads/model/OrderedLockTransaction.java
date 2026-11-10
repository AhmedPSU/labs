package edu.psu.se411.lab12_threads.model;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

/**
 * Part d: resource ordering.
 * Accounts are totally ordered by their id. Every transaction locks the account
 * with the smaller id first, whatever the direction of the payment.
 * A -> B and B -> A both lock A then B, so a circular wait (deadlock) is impossible.
 */
public class OrderedLockTransaction extends LockTransaction {

	public OrderedLockTransaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super(id, sender, receiver, amount);
	}

	@Override
	public void execute() throws InsufficientBalanceException, InterruptedException {
		CryptoAccount first, second;
		if(sender.getId().compareTo(receiver.getId()) < 0) {
			first = sender;
			second = receiver;
		} else {
			first = receiver;
			second = sender;
		}

		lockBoth(first, second);     // always ascending id order
		try {
			transfer();
		} finally {
			second.getLock().unlock();
			first.getLock().unlock();
		}
	}
}
