package designingclasses;

public class Item {
	private float price;
	private int numberOfItem;
	private int maxCapacity; //assume every item is a location in the vending machine and can hold up to a certain amount
	private String NameOfItem;
	
	/**
	 * constructor
	 * @param price
	 * @param numberOfItem
	 * @param nameOfItem
	 */
	public Item(float price, int numberOfItem, String nameOfItem) {
		super();
		this.price = price;
		this.numberOfItem = numberOfItem;
		NameOfItem = nameOfItem;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	public String getNameOfItem() {
		return NameOfItem;
	}
	public void setNameOfItem(String nameOfItem) {
		NameOfItem = nameOfItem;
	}
	
    
	@Override
	/**
	 * toString method
	 */
	public String toString() {
		return "Item [price=" + price + ", numberOfItem=" + numberOfItem + ", NameOfItem=" + NameOfItem + "]";
	} 
	
	
}
