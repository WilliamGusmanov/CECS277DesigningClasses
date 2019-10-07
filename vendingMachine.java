package designingclasses;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;; 
public class vendingMachine {
	/**
	 * ArrayList of items to be sold in Vending machine 
	 */
	//public enum Coin {penny,nickel,dime,quarter,half_dollar,dollar} //Delete if unused
	
	public HashMap<String, Double> ValueCoins = new HashMap<String,Double>();
	public HashMap<String, Integer> VendingMachineCoins = new HashMap<String,Integer>();
	public HashMap<String, Integer> CustomerCoins = new HashMap<String, Integer>(); 
	private int maxCapacityOfItems; 
	ArrayList<Item> vendingMachineitems = new ArrayList<Item>();
	private Item currentlyChosenItem; 
	Scanner buttonPanel = new Scanner(System.in);
	/**
	 * default constructor
	 */
	vendingMachine(){
		initializeMaps(); 	
	}
	
	/**
	 * prompts user to select item from machine.
	 * This will be an overloaded method 
	 * @return item that user selected, else return null 
	 */
	
	private void initializeMaps() {
		ValueCoins.put("penny", 0.01);
		ValueCoins.put("nickel", 0.05);
		ValueCoins.put("dime", 0.1);
		ValueCoins.put("quarter", 0.25);
		ValueCoins.put("half_dollar", 0.5);
		ValueCoins.put("dollar", 1.0);
		
		VendingMachineCoins.put("penny", 0);
		VendingMachineCoins.put("nickel", 0);
		VendingMachineCoins.put("dime", 0);
		VendingMachineCoins.put("quarter", 0);
		VendingMachineCoins.put("half_dollar", 0);
		VendingMachineCoins.put("dollar", 0);

		CustomerCoins.put("penny", 0);
		CustomerCoins.put("nickel", 0);
		CustomerCoins.put("dime", 0);
		CustomerCoins.put("quarter", 0);
		CustomerCoins.put("half_dollar", 0);
		CustomerCoins.put("dollar", 0);
	}
	/**
	 * user gets to add a coin to the vending machine
	 * spendable balance is incremented
	 * Number of coins hash map is updated with new coin
	 * @param coin
	 */
	public void insertCoin(String coin) {
		assert ValueCoins.containsKey(coin) : "invalid coin";
		if (ValueCoins.containsKey(coin)) {
			CustomerCoins.put(coin, CustomerCoins.get(coin) + 1);
		}
	}
	/**
	 * helper method for buy
	 * Transfers coins from user balance to vending machine
	 * resets Customer balance to 0 
	 */
	private void TransferCtoVM() {
		for (String coinName : CustomerCoins.keySet()) {
			VendingMachineCoins.put(coinName, VendingMachineCoins.get(coinName) + CustomerCoins.get(coinName));
			CustomerCoins.put(coinName, 0);
		}
	}
	/**
	 * FIX FIX THIS AHAHHAHAHAHAH
	 * take string name of coin
	 * check if valid input
	 * take all the coins of the specific coin type out of the vending machine
	 * @param coin
	 */
	public void removeCoin(String coin) {
		if (ValueCoins.containsKey(coin)) {
			VendingMachineCoins.put(coin, 0);
		}
	}
	/**
	 * balance += (number of coins * value of coin)  
	 * @return
	 */
	public double FindUserBalance() {
		double balance = 0.0; 
		for (String coinName : CustomerCoins.keySet()) {
			balance += (CustomerCoins.get(coinName) * ValueCoins.get(coinName));
		}
		return balance; 
		
	}
	/**
	 * The main transaction method where: 
	 * Check if item name exits and there is enough of item to buy
	 * Check if customer has enough money to buy item
	 * transfer user balance to vending machine
	 * decrease number of item in array list of items 
	 * return sold item 
	 * @param itemName, name entered by user
	 * @return, item that is found, else return null 
	 */
	public Item Buy(String itemName) {
		Item chosenItem = SearchVendingMachine(itemName);
		if (chosenItem != null && chosenItem.getPrice() <= FindUserBalance()) {
			TransferCtoVM(); 
			chosenItem.setNumberOfItem(chosenItem.getNumberOfItem() - 1);
			return chosenItem; 
		}
		return null; 
	}
	/**
	 * checks if there is an item with the input name and there is an amount > 0  
	 * @param itemName
	 * @return
	 */
	private Item SearchVendingMachine(String itemName) {
		Item foundItem = findItemName(itemName); 
		if (foundItem != null && foundItem.getNumberOfItem() > 0) {
			return foundItem; 
		}
		else return null; 
	}
	private void DecrementItem(Item item) {
		
		item.setNumberOfItem(item.getNumberOfItem()-1);
	}
	/**
	 * adds a brand new item to the Vending Machine
	 * @param price
	 * @param numberOfItem
	 * @param nameOfItem
	 */
	public void addNewProduct(float price, int numberOfItem, String nameOfItem) {
		if (this.vendingMachineitems.size() != maxCapacityOfItems)
		this.vendingMachineitems.add(new Item(price, numberOfItem, nameOfItem));
	}
	/**
	 * add to existing
	 * @param nameOfItem
	 * @param numberOfItem
	 */
	public void addExistingProduct(String nameOfItem, int numberOfItem) {
		Item foundItem = findItemName(nameOfItem);
		if (foundItem != null) {
			foundItem.setNumberOfItem(foundItem.getNumberOfItem() + numberOfItem);
		}
		
	}
	private Item findItemName(String nameOfItem) {
		Item found = null;
		for (Item item : vendingMachineitems) {
				if (item.getNameOfItem().toLowerCase() == nameOfItem.toLowerCase()) {
					found = item; 	
				}
			}
		return found;  
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
