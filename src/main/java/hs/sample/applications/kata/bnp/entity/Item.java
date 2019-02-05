package hs.sample.applications.kata.bnp.entity;

public class Item {

	private String itemType;
	private double itemPrice;

	public Item(String itemType, double itemPrice) {
		this.itemType = itemType;
		this.itemPrice = itemPrice;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

}
