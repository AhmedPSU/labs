package edu.psu.se411.lab12_threads;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.Transaction;

/**
 * Part a (program 1): FIXED thread pool.
 * Program arguments (optional): <nbAccounts> <nbTransactions>   e.g. "1000 1000000"
 *
 * The pool size is the number of CPU cores. (The starting code used
 * newFixedThreadPool(nbTransactions), i.e. one thread per transaction:
 * with one million transactions that means one million threads.)
 *
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		int nbAccounts = Simulation.arg(args, 0, 10);
		int nbTransactions = Simulation.arg(args, 1, 1_000_000);
		int poolSize = Runtime.getRuntime().availableProcessors();

		Simulation.run("Fixed thread pool (" + poolSize + " threads)",
				Executors.newFixedThreadPool(poolSize),
				nbAccounts, nbTransactions, Transaction::new, 600);

		logger.info("Application is closing...");
	}
}
