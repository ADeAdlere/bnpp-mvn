package hs.sample.applications.kata.bnp;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.function.BiConsumer;

import hs.sample.applications.kata.bnp.entity.Item;
import hs.sample.applications.kata.bnp.entity.SpecialOffer;

public class BasketAmountCalculator {

	private static final String ITEMS_KEY = "items";
	private static final String PRICES_KEY = "prices";
	private static final String OFFERS_KEY = "offers";
	private static final String SEPARATOR = "#";
	private static final String OFFER_SEPARATOR = ":";

	private Set<Item> itemSet;
	private Map<String, SpecialOffer> itemOffer;
	private Map<Item, Integer> basket;

	public BasketAmountCalculator() throws Exception {
		initialize();
	}

	private void initialize() throws Exception {
		itemSet = new HashSet<Item>();
		itemOffer = new HashMap<String, SpecialOffer>();
		// Initialization using the initialization properties file
		InputStream input = getClass().getClassLoader().getResourceAsStream("initialization.properties");
		if (input != null) {
			Properties prop = new Properties();
			prop.load(input);
			String items = prop.getProperty(ITEMS_KEY);
			String prices = prop.getProperty(PRICES_KEY);
			String offers = prop.getProperty(OFFERS_KEY);
			if (items != null && prices != null && offers != null) {
				String[] itemsArray = items.split(SEPARATOR);
				String[] pricesArray = prices.split(SEPARATOR);
				String[] offersArray = offers.split(SEPARATOR);
				if (itemsArray.length == pricesArray.length && itemsArray.length == offersArray.length) {

					for (int i = 0; i < itemsArray.length; i++) {
						double itemPrice = Double.valueOf(pricesArray[i]);
						String itemName = itemsArray[i];
						Item item = new Item(itemName, itemPrice);
						String offer = offersArray[i];
						if (offer != null && !offer.isEmpty()) {
							int itemsNumber = Integer.valueOf(offer.split(OFFER_SEPARATOR)[0]);
							int freeItemsNumber = Integer.valueOf(offer.split(OFFER_SEPARATOR)[1]);
							itemOffer.put(itemName, new SpecialOffer(itemsNumber, freeItemsNumber));
						}
						itemSet.add(item);
					}
					// Printing supermarket contents
					System.out.println("*** The supermarket has been initialized with the following items ***");
					itemSet.forEach(e -> {

						System.out.println("Item  : " + e.getItemType());
						System.out.println("Price : " + e.getItemPrice());
						if (itemOffer.containsKey(e.getItemType())) {
							itemOffer.get(e.getItemType()).printOffer(e.getItemType());
						}
					});
					// End printing
					return;
				}
				throw new Exception("Problem while loading the initialization file");
			}
		} else {
			System.err.println("Could not found the initialization file");
		}
	}

	public void getBasketContents() {
		basket = new HashMap<Item, Integer>();
		Scanner reader = new Scanner(System.in);

		for (Item item : itemSet) {
			String itemName = item.getItemType();
			System.out.println("How many " + itemName + "s in the basket :");
			int howMany = reader.nextInt();
			if (howMany > 0) {
				basket.put(item, howMany);
			}
		}

		reader.close();

		// Printing basket contents
		System.out.println("*** Your basket contains the following ***");
		BiConsumer<Item, Integer> printer = (key, value) -> System.out.printf("%o %s(s) \n", value, key.getItemType());
		basket.forEach(printer);
		// End printing

	}

	public double getBasketPrice() {
		double price = 0;
		for (Item item : basket.keySet()) {
			int itemCount = basket.get(item);
			double itemPrice = item.getItemPrice();
			if (itemOffer.containsKey(item.getItemType())) {
				SpecialOffer specialOffer = itemOffer.get(item.getItemType());
				price += specialOffer.priceAfterOffer(itemCount, itemPrice);
			} else {
				price += itemPrice * itemCount;
			}
		}
		return price;
	}
}
