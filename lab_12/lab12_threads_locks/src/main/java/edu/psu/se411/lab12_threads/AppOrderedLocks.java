package edu.psu.se411.lab12_threads;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.OrderedLockTransaction;

/**
 * Part d: locks with resource ordering (always lock the smaller account id first).
 * Expected result: no deadlock, all transactions finish, total money unchanged.
 */
public class AppOrderedLocks {

	static Logger logger = LoggerFactory.getLogger(AppOrderedLocks.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		int nbAccounts = Simulation.arg(args, 0, 10);
		int nbTransactions = Simulation.arg(args, 1, 1_000_000);

		Simulation.run("Locks with resource ordering",
				Executors.newFixedThreadPool(50),
				nbAccounts, nbTransactions, OrderedLockTransaction::new, 600);

		logger.info("Application is closing...");
	}
}
