package edu.psu.se411.lab12_threads.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

/**
 * Part b: "synchronized" replaced by an explicit lock (one ReentrantLock per account).
 * The lock is reentrant, so a transaction that already holds it can still call
 * addToBalance / retrieveFromBalance without blocking itself.
 */
public class CryptoAccount {
	private String id;
	private Double balance;
	private final Lock lock = new ReentrantLock();

	public CryptoAccount(String id, Double balance) {
		if(balance < 0) throw new IllegalArgumentException("Account balance cannot be negative");
		this.id = id;
		this.balance = balance;
	}

	public void addToBalance(Double amount) {
		if(amount < 0) throw new IllegalArgumentException("Amount added to balance cannot be negative");
		lock.lock();
		try {
			balance += amount;
		} finally {
			lock.unlock();
		}
	}

	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		lock.lock();
		try {
			if(amount > balance) throw new InsufficientBalanceException(id);
			balance -= amount;
		} finally {
			lock.unlock();
		}
	}

	public Lock getLock() {
		return lock;
	}

	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}

}
