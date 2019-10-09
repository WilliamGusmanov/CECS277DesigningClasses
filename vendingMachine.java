package designingclasses;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;; 
/**
 * 
 * @author William Gusmanov, @author Bryan Vu 
 * Program that simulates Vending Machine 
 */
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
	Scanner buttonPanel = new Scanner(System.in);
	/**
	 * default constructor
	 */
	vendingMachine(){
		initializeMaps(); 	
	}
	vendingMachine(ArrayList<Item> x){
		initializeMaps();
		vendingMachineitems = x; //set reference of vendingMachineitems to Array List parameter 
	}
	/**
	 * prompts user to select item from machine.
	 * This will be an overloaded method 
	 * @return item that user selected, else return null 
	 */
	private void initializeMaps() {
		ValueCoins.put("penny", 0.01);
		ValueCoins.put("nickel", 0.05);
		ValueCoins.put("dime", 0.10);
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
		if (ValueCoins.containsKey(coin)) {
			CustomerCoins.put(coin, CustomerCoins.get(coin) + 1);
		}
		else {
			System.out.println("invalid coin");
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
	 * take string name of coin
	 * check if valid input
	 * take all the coins of the specific coin type out of the vending machine
	 * @param coin
	 */
	public void removeCoins() {
		for (String coin : CustomerCoins.keySet()) {
			 CustomerCoins.put(coin, 0);
		}
	}
	/**
	 * for each type of coin: balance += (number of coin type * value of coin)
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
			DecrementItem(chosenItem);
			return chosenItem; 
		}
		else if (chosenItem != null){
			System.out.println("insufficient balance.");
		}
		else {
			System.out.println("item not found.");
		}
		return null; 
	}
	/**
	 * checks if there is an item with the input name and there is an amount > 0  
	 * @param itemName
	 * @return item, else return null
	 */
	private Item SearchVendingMachine(String itemName) {
		Item foundItem = findItemName(itemName); 
		if (foundItem != null && foundItem.getNumberOfItem() > 0) {
			return foundItem; 
		}
		else return null; 
	}
	/**
	 * helper method to decrement item when the item is bought
	 * @param item item to be bought
	 */
	private void DecrementItem(Item item) {
		if (item.getNumberOfItem() > 0) {
		item.setNumberOfItem(item.getNumberOfItem()-1);
		}
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
	public boolean addExistingProduct(String nameOfItem, int numberOfItem) {
		Item foundItem = findItemName(nameOfItem);
		boolean added = false;
		if (foundItem != null) {
			foundItem.setNumberOfItem(foundItem.getNumberOfItem() + numberOfItem);
			added = true; 
		}
		return added; 
	}
	/**
	 * traverses through the vending machien and returns item if it is found, else returns null
	 * @param nameOfItem
	 * @return item if found, null otherwise
	 */
	private Item findItemName(String nameOfItem) {
		Item found = null;
		for (Item item : vendingMachineitems) {
			if (item.getNameOfItem().equalsIgnoreCase(nameOfItem)) {
					found = item; 	
			}
		}
		return found;
	}
	/**
	 * S)how Products I)nsert B)uy A)dd Product R)emove Coins Q)uit
	 * runs Vending Machine as a customer.
	 * Can buy, add money 
	 */
	public void customerAccess() {
		char input = 0;
		while (input != 'Q' && input != 'B') { 
		do {
			System.out.printf("Your current balance is: $%.2f\n", FindUserBalance());
			System.out.println("S)how Products I)nsert B)uy A)dd Product R)emove Coins Q)uit");
			input = buttonPanel.next().charAt(0);
			//System.out.println("checkinput: " + checkInput(input)); //DEBUG
		} while (!checkInput(input));
		switch(input) {
		case 'S': //show products
			displayItems(); 
			break;
		case 'I': //insert coin
			System.out.print("Enter type of coin: ");
			insertCoin(buttonPanel.next());
			break;
		case 'B': //buy 
			System.out.print("Enter product name: ");
			Buy(buttonPanel.next());
			break;
		case 'A': //add product
			AddNewOrExistingProduct(); 
			break;
		case 'R': //remove coins
			removeCoins();
			break;
		case 'Q': //quit
			System.out.println("Have a nice day.");
			TransferCtoVM(); 
			break;
		default:
			System.out.println("invalid input");
			break;
			}
		}
	}
	/**
	 * take in user input to determine to add existing or new product
	 */
	private void AddNewOrExistingProduct(){
		System.out.println("E)xisting or N)ew product? or R)eturn to menu");
		char input2 = buttonPanel.next().charAt(0);
		String name; int size; 
		switch (input2) {
		case 'E':
			System.out.println("Enter product name:");
			name = buttonPanel.next();
			System.out.println("Enter number of items:");
			size = buttonPanel.nextInt();
			addExistingProduct(name,size);
			break;
		case 'N':
			System.out.println("Enter product name:");
			name = buttonPanel.next();
			System.out.println("Enter price of item:");
			float price = buttonPanel.nextFloat();
			System.out.println("Enter number of items:");
			size = buttonPanel.nextInt();
			addNewProduct(price,size,name);
			break;
		case 'R':
			break;
		default:
			System.out.println("invalid input");
			break;
		}
	}
	/**
	 * used to check if input is one of the options for Customer access Vending Machine
	 * @param input
	 * @return boolean
	 */
	private boolean checkInput(char input) {
		char validInputs[] = new char [] {'S','I','B','A','R','Q'}; 
		boolean valid = false;
		for (char validChar : validInputs) {
			if (input == validChar) {
				valid = true;
			} 
		}
			return valid;  
		}
	/**
	 * display function to display items utilizing the toString Method
	 */
	public void displayItems() {
		for (Item item:vendingMachineitems) {
			System.out.println(item);
		}
	}
}
