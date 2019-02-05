package hs.sample.applications.kata.bnp.entity;

/**
 * 
 * Represents the special offer of the following pattern : buy
 * {@link #itemsNumber} items and get {@link #freeItemsNumber} items for free :
 * ({@link #itemsNumber} + {@link #freeItemsNumber}) items for the price of
 * {@link #itemsNumber} items
 *
 */
public class SpecialOffer {

	private int itemsNumber;
	private int freeItemsNumber;

	public SpecialOffer(int itemsNumber, int freeItemsNumber) {
		super();
		this.itemsNumber = itemsNumber;
		this.freeItemsNumber = freeItemsNumber;
	}

	public void printOffer(String itemType) {
		System.out.printf("Special offer : buy %s get %s on %s \n", itemsNumber, freeItemsNumber, itemType);
	}

	public void setItemNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public void setFreeItemNumber(int freeItemsNumber) {
		this.freeItemsNumber = freeItemsNumber;
	}

	public double priceAfterOffer(int itemCount, double itemPrice) {

		double eligiblePartPrice = (itemCount / (itemsNumber + freeItemsNumber)) * (itemPrice * itemsNumber);

		double nonEligiblePartPrice = (itemCount % (itemsNumber + freeItemsNumber)) * itemPrice;

		return eligiblePartPrice + nonEligiblePartPrice;
	}

}
