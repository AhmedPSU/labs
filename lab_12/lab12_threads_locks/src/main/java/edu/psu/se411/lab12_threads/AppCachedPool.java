package edu.psu.se411.lab12_threads;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.Transaction;

/**
 * Part a (program 2): CACHED thread pool.
 * Program arguments (optional): <nbAccounts> <nbTransactions>   e.g. "10 1000000"
 *
 * A cached pool creates a new thread whenever no idle thread is available
 * and reuses threads that become idle (idle threads die after 60 s).
 */
public class AppCachedPool {

	static Logger logger = LoggerFactory.getLogger(AppCachedPool.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		int nbAccounts = Simulation.arg(args, 0, 10);
		int nbTransactions = Simulation.arg(args, 1, 1_000_000);

		Simulation.run("Cached thread pool",
				Executors.newCachedThreadPool(),
				nbAccounts, nbTransactions, Transaction::new, 600);

		logger.info("Application is closing...");
	}
}
