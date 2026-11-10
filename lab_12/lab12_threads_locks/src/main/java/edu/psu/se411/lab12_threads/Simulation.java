package edu.psu.se411.lab12_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import edu.psu.se411.lab12_threads.model.CryptoAccount;

/**
 * Shared simulation code used by all the main programs:
 * creates the accounts, submits random transactions to the given thread pool,
 * waits for them and prints the totals and the elapsed time.
 */
public class Simulation {

	/** Creates the transaction object that will run in the pool. */
	@FunctionalInterface
	public interface TransactionFactory {
		Runnable create(String id, CryptoAccount sender, CryptoAccount receiver, Double amount);
	}

	public static final Double INITIAL_BALANCE = 10000.0;

	/**
	 * @return true if all transactions finished before the timeout
	 */
	public static boolean run(String label, ExecutorService executor, int nbAccounts, int nbTransactions,
			TransactionFactory factory, long timeoutSeconds) {

		System.out.printf("=== %s: %d accounts, %d transactions ===%n", label, nbAccounts, nbTransactions);

		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (int i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i, INITIAL_BALANCE);
		}
		System.out.printf("Initial money volume in system: %.2f%n", sumTotalMoneyInAccounts(accounts));

		long startTime = System.currentTimeMillis();

		// 2- choose sender and receiver accounts randomly and submit the transaction
		for (int i = 0; i < nbTransactions; i++) {
			int senderIndex = (int) (Math.random() * nbAccounts);
			int receiverIndex = (int) (Math.random() * nbAccounts);
			if (senderIndex == receiverIndex) {
				receiverIndex = (receiverIndex + 1) % nbAccounts;
			}
			Double amount = Math.random() * 100;
			executor.execute(factory.create("id_" + i, accounts[senderIndex], accounts[receiverIndex], amount));
		}

		// 3- wait for all transactions to complete
		executor.shutdown();
		boolean finished;
		try {
			finished = executor.awaitTermination(timeoutSeconds, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			finished = false;
		}

		long endTime = System.currentTimeMillis();

		if (!finished) {
			System.out.printf("NOT FINISHED after %d seconds -> threads are stuck waiting for each other's locks (DEADLOCK)%n", timeoutSeconds);
			executor.shutdownNow(); // interrupts the stuck threads (they use lockInterruptibly)
		}

		// 4- check total money in system after all transactions
		System.out.printf("Final money volume in system:   %.2f%n", sumTotalMoneyInAccounts(accounts));
		System.out.println("Total time: " + (endTime - startTime) + " milliseconds");
		System.out.println();
		return finished;
	}

	public static Double sumTotalMoneyInAccounts(CryptoAccount[] accounts) {
		Double total = 0.0;
		for (CryptoAccount c : accounts) {
			total += c.getBalance();
		}
		return total;
	}

	/** args[0] = number of accounts, args[1] = number of transactions (optional). */
	static int arg(String[] args, int index, int defaultValue) {
		return args.length > index ? Integer.parseInt(args[index]) : defaultValue;
	}
}
