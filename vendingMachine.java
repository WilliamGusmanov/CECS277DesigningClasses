package designingclasses;
import java.util.ArrayList;
import java.util.Scanner;
public class vendingMachine {
	/**
	 * ArrayList of items to be sold in Vending machine 
	 */
	private int maxCapacityOfItems; 
	ArrayList<Item> vendingMachineitems = new ArrayList<Item>();
	private float moneyInVendingMachine = 0;
	private Item CurrentlyChosenItem; 
	Scanner buttonPanel = new Scanner(System.in);
	
	/**
	 * prompts user to select item from machine.
	 * This will be an overloaded method 
	 * @return item that user selected, else return null 
	 */
	public void addBalance(float coin) {
		
	}
	public Item select(String itemName) {
		Item chosenItem = SearchVendingMachine(itemName);
		if (chosenItem != null) {
			return chosenItem; 
		}
		return null; 
	}
	private Item SearchVendingMachine(String itemName) {
		for (Item item : vendingMachineitems) {
			if (item.getNameOfItem().toLowerCase() == itemName.toLowerCase())
				return item; 
		}
		return null; 
	}
	/**
	 * adds a brand new item to the Vending Machine
	 * @param price
	 * @param numberOfItem
	 * @param nameOfItem
	 */
	public void addNewItem(float price, int numberOfItem, String nameOfItem) {
		if (this.vendingMachineitems.size() != maxCapacityOfItems)
		this.vendingMachineitems.add(new Item(price, numberOfItem, nameOfItem));
	}
	/**
	 * runs Vending Machine as a customer.
	 * Can buy, add money 
	 */
	public void customerAccess() {
		int input = -1;
		do {
			System.out.println("0: add balance. \n1:select item ");
			input = buttonPanel.nextInt();
		} while (checkInput(input));
		
		switch(input) {
		case 0: //add balance
			
			break;
		case 1: //select item 
			break;
		default:
			break;
		}
		
	}
	/**
	 * used to check if input is one of the options for Customer access Vending Machine
	 * @param input
	 * @return boolean
	 */
	private boolean checkInput(int input) {
		if (input == 0 || input == 1){
			return true; 
		}
		else return false; 
	}
	//everything the customer can do, but can add and remove items freely and add brand new items
	public void adminAccess() {
		
		
	}
	
	
}
