package edu.psu.se411.lab11.crypto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Simulates the double spending problem:
 * 10 accounts x 1000 SAR = 10000 SAR, then 10,000 threads each make one random transaction.
 * Run 1 uses Transaction (unsynchronized), run 2 uses SynchronizedTransaction.
 */
public class CryptoSystemMain {

	private static final int NUM_ACCOUNTS = 10;
	private static final double INITIAL_BALANCE = 1000.0;
	private static final int NUM_THREADS = 10_000;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("=== Part 1: without synchronization ===");
		runSimulation(false);

		System.out.println();
		System.out.println("=== Part 2: with synchronized ===");
		runSimulation(true);
	}

	private static void runSimulation(boolean synchronizedVersion) throws InterruptedException {
		List<CryptoAccount> accounts = new ArrayList<>();
		for (int i = 0; i < NUM_ACCOUNTS; i++) {
			accounts.add(new CryptoAccount(i + 1, INITIAL_BALANCE));
		}
		System.out.printf("Total before: %.2f SAR%n", totalMoney(accounts));

		CountDownLatch startSignal = new CountDownLatch(1); // start all threads at the same moment
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < NUM_THREADS; i++) {
			Thread thread = new Thread(() -> {
				try {
					startSignal.await();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
				CryptoAccount randomA = accounts.get((int) (Math.random() * 10));
				CryptoAccount randomB = accounts.get((int) (Math.random() * 10));
				Double randomAmount = (double) (Math.random() * 10 + 1);

				Transaction t = synchronizedVersion
						? new SynchronizedTransaction(randomA, randomB, randomAmount)
						: new Transaction(randomA, randomB, randomAmount);
				t.execute();
			});
			threads.add(thread);
			thread.start();
		}

		startSignal.countDown();
		for (Thread thread : threads) {
			thread.join();          // wait for all 10,000 transactions to finish
		}

		for (CryptoAccount account : accounts) {
			System.out.println(account);
		}
		double total = totalMoney(accounts);
		System.out.printf("Total after:  %.2f SAR -> %s%n", total,
				Math.abs(total - NUM_ACCOUNTS * INITIAL_BALANCE) < 0.001 ? "CONSISTENT" : "INCONSISTENT (money created/lost!)");
	}

	/** Total amount of money in the crypto currency system. */
	public static double totalMoney(List<CryptoAccount> accounts) {
		double total = 0;
		for (CryptoAccount account : accounts) {
			total += account.getBalance();
		}
		return total;
	}
}
