package edu.psu.se411.lab09_inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab09_inventory.model.Book;
import edu.psu.se411.lab09_inventory.model.ElectronicDevice;
import edu.psu.se411.lab09_inventory.model.Inventory;
import edu.psu.se411.lab09_inventory.model.Item;
import edu.psu.se411.lab09_inventory.strategy.SearchByAuthor;
import edu.psu.se411.lab09_inventory.strategy.SearchByCategory;
import edu.psu.se411.lab09_inventory.strategy.SearchById;
import edu.psu.se411.lab09_inventory.strategy.SearchByName;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	/** Prints every item of any inventory, whatever its item type. */
	public static void displayInventory(Inventory<?> inventory) {
		System.out.println("--- Inventory contents ---");
		for (Object item : inventory.getAll()) {
			System.out.println(item);
		}
	}

	public static void main(String[] args) {
		logger.info("Application is starting...");

		// Part 1: one inventory holding books and electronic devices
		Inventory<Item> inventory = new Inventory<>();
		inventory.add(new Book(1, "Concurrency in Java", "Brian Goetz"));
		inventory.add(new Book(2, "Effective Java", "Joshua Bloch"));
		inventory.add(new ElectronicDevice(3, "Laptop X1", "Computers"));
		inventory.add(new ElectronicDevice(4, "Phone Z", "Phones"));

		// Part 2: strategy-based search
		List<Item> byName = inventory.findItems(new SearchByName("Concurrency in Java"));
		System.out.println("Search by name 'Concurrency in Java': " + byName);

		List<Item> deviceByName = inventory.findItems(new SearchByName("Laptop X1"));
		System.out.println("Search by name 'Laptop X1': " + deviceByName);

		List<Item> byId = inventory.findItems(new SearchById(4));
		System.out.println("Search by id 4: " + byId);

		List<Book> byAuthor = inventory.findItems(Book.class, new SearchByAuthor("Joshua Bloch"));
		System.out.println("Books by Joshua Bloch: " + byAuthor);

		List<ElectronicDevice> byCategory = inventory.findItems(ElectronicDevice.class, new SearchByCategory("Phones"));
		System.out.println("Devices in category 'Phones': " + byCategory);

		// Part 3: display any inventory
		displayInventory(inventory);

		// Show that displayInventory works for another item type too
		Inventory<Book> books = new Inventory<>();
		books.add(new Book(10, "Clean Code", "Robert Martin"));
		displayInventory(books);

		logger.info("Application is closing...");
	}

}