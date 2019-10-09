package designingclasses;
import java.util.ArrayList; 
public class VendingMachineTester {

	public static void main(String[] args) {
		ArrayList<Item> newSupply = new ArrayList<Item>();
		//item: price, number, name
		Item Lays = new Item(0.75f, 4, "Lays");
		Item Cola = new Item(1.25f, 2, "Cola");
		Item Snickers = new Item(0.5f,6, "Snickers");
		newSupply.add(Lays);
		newSupply.add(Cola);
		newSupply.add(Snickers); 
		vendingMachine x = new vendingMachine(newSupply); 
		x.displayItems();
		x.customerAccess();	
	}

}
