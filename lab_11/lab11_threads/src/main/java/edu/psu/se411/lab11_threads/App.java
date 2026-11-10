package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Integer nbAccounts = 10;
	public static Double initialBalance = 10000.0;
	public static Integer nbTransactions = 1000;

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}
		
		// TODO

		logger.info("Application is closing...");
	}

}
