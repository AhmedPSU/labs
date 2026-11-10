package edu.psu.se411.lab12_threads;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.LockTransaction;

/**
 * Parts b/c: locks instead of synchronized, locking sender then receiver.
 * Expected result: the program gets stuck (DEADLOCK). It is stopped after a timeout.
 */
public class AppLocks {

	static Logger logger = LoggerFactory.getLogger(AppLocks.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		int nbAccounts = Simulation.arg(args, 0, 10);
		int nbTransactions = Simulation.arg(args, 1, 1_000_000);

		boolean finished = Simulation.run("Locks, no ordering",
				Executors.newFixedThreadPool(50),
				nbAccounts, nbTransactions, LockTransaction::new, 15);
		if (!finished) {
			logger.error("Deadlock detected: transactions did not finish");
		}

		logger.info("Application is closing...");
	}
}
