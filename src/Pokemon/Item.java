package Pokemon;

public class Item {
	private int amount;
	private String name;
	boolean pokeBall = false, bike = false, badge = false;
	public Item(String name, int amount) { //heal or is candy that makes them happy
		this.name = name;
		this.amount = amount;
	}
	public Item(String name) { //heal or is candy that makes them happy
		if(name.equals("Pokeball"))
			pokeBall = true;
		else if(name.equals("Bike"))
			bike = true;
		else if(name.equals("Badge"))
			badge = true;
		else
			amount = (int) (Math.random() * 30) + 10;
		this.name = name;
	}
	public String description() {
		if(!pokeBall)
			return "Heals for " + amount + " hp";
		return "Used to catch pokemon!";
	}
	public boolean isPokeBall() {
		return pokeBall;
	}
	public boolean isBike() {
		return bike;
	}
	public String getName() {
		return name;
	}
	public boolean isBadge() {
		return badge;
	}
	public String toString() {
		return name;
	}
	public int getAmount() { return amount; }
}
