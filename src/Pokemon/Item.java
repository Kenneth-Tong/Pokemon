package Pokemon;

public class Item {
	private String name, Description;
	private int amount;
	public Item(String name, int x) {
		this.name = name;
		amount = x;
	}
	public void setDescription(String n) {
		Description = n;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return name;
	}
	public String getDescription() {
		return Description;
	}
	public int getAmount() {
		return amount;
	}
	public void useItem() {
		amount--;
	}
	public void addItem(int n) {
		amount += n;
	}
}
