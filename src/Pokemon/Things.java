package Pokemon;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/*
 * Letter = change location
 * 14 = dark rock
 * 13 = White
 * 12 = chest
 * 11 = Sand
 * 10 = black
 * 9 = brown
 * 8 = red
 * 7 = person
 * 6 = sign
 * 5 = tree
 * 4 = path
 * 3 = water
 * 2 = wall
 * 1 = tall grass
 * 0 = grass
 * 15 = needs item to open
 *
 * | = spacebar
 * / = question
 */

public class Things {
	private Rectangle hitBox, drawBox; //draw box is only for drawing, hitBox depends on whether it is a person or an object
	private String connectingLocation = "", message = " ", name = ""; //name is only for person, message is only for sign
	private ArrayList<String> dialogue; //person dialogue only
	private boolean tallGrass, passable, person, sign = false, talkedTo, chest, key, door; //talk means there are choices, talkedTo means you're done talking to
	private Color color;
	private int amountOfItems = 1; //only applies to chests
	private Item item;

	public Things(int i, int posX, int posY, int width, int height) {
		switch(i) {
			case 0:
				color = new Color(154,205,50);
				passable = true;
				break;
			case 1:
				color = Color.GREEN.darker();
				passable = true;
				tallGrass = true;
				break;
			case 2:
				color = new Color(47,79,79);
				passable = false;
				break;
			case 3:
				color = new Color(102, 178, 255);
				passable = false;
				break;
			case 4:
				color = new Color(169,169,169);
				passable = true;
				break;
			case 5:
				color = new Color(0,100,0);
				passable = false;
				break;
			case 8:
				color = new Color(200,92,92);
				passable = false;
				break;
			case 9:
				color = new Color(210,105,30);
				passable = true;
				break;
			case 10:
				color = Color.BLACK;
				passable = false;
				break;
			case 11:
				color = new Color(222, 184, 135);
				passable = false;
				break;
			case 13:
				color = Color.WHITE;
				passable = true;
				break;
			case 14:
				color = new Color(128,128,128);
				passable = true;
				tallGrass = true;
				break;
		}
		hitBox = drawBox = new Rectangle(posX, posY, width, height);
		drawBox = new Rectangle(posX, posY, width, height); //Make the collisions more smooth
	}
	public Things(int posX, int posY, int width, int height) { //door that can be opened
		color = new Color(205,133,63);
		passable = false;
		door = true;
		hitBox = new Rectangle(posX - 5, posY - 5, width + 10, height + 10);
		drawBox = new Rectangle(posX, posY, width, width);
	}
	public Things(String n, int posX, int posY, int width, int height) { //change location
		color = new Color(105,105,105);
		passable = true;
		connectingLocation = n; 
		hitBox = new Rectangle(posX, posY, width, width);
		drawBox = new Rectangle(posX, posY, width, width);
		key = true; //can be passed with key
	}
	public boolean hasKey(Player p) {
		if(p.getInventoryNames().contains(" Key ")) {
			return true;
		}
		return false;
	}
	public void openDoor() {
		if(key) {
			passable = true;
			color = new Color(154,205,50); //there is only one door, to the castle
			hitBox = drawBox; //cannot interact anymore
		}
	}
	public Things(int n, String m, int posX, int posY, int width, int height) { //int doesn't do anything, is a sign
		if(n == 6) { //sign
			color = new Color(255, 140, 0);
			message = m;
			passable = true;
			sign = true;
			hitBox = new Rectangle(posX, posY, width, height);
			drawBox = new Rectangle(posX, posY, width, height);
			name = "Sign";
		} else if (n == 12){ //chest
			color = new Color(139,69,19);
			chest = true;
			passable = false;
			String itemName = m.substring(locationItem(m)); //get item name
			amountOfItems = Integer.parseInt(m.substring(locationItem(m) - 1)); //get item
			if(itemName.contains("Pokeball")) {
				item = new Pokeball("Pokeball", amountOfItems);
			} else if(itemName.contains("Key")) {
				item = new Key(itemName, amountOfItems, false); //chests will never have the team rocket key, only joradan
			} else {
				item = new HealItem(itemName, amountOfItems);
			}
			drawBox = new Rectangle(posX, posY, width, height);
			hitBox = new Rectangle(posX - 5, posY - 5, width + 10, height + 10);
			name = "Chest";
			talkedTo = false;
		}
	}
	public Things(String n, ArrayList<String> m, int posX, int posY, int width, int height) { //person
		color = new Color(138,43,226);
		name = n;
		dialogue = m;
		passable = false;
		person = true;
		hitBox = new Rectangle(posX - 5, posY - 5, width + 10, height + 10);
		drawBox = new Rectangle(posX, posY, width, height);
		talkedTo = false;
	}

	public String getMessage() {
		return message;
	}
	public Rectangle getHitBox() {
		return hitBox;
	}
	public boolean isPassable() {
		return passable;
	}
	public Color getColor() {
		return color;
	}
	public String getConnectingLocation() {
		return connectingLocation;
	}
	public boolean isSign() {
		return sign;
	}
	public boolean isTallGrass() {
		return tallGrass;
	}
	public boolean isPerson() {
		return person;
	}
	public boolean isDoor() {
		return door;
	}
	public Rectangle getDrawBox() {
		return drawBox;
	}
	public ArrayList<String> getDialogue() {
		return dialogue;
	}
	public String getName() {
		return name;
	}
	public boolean isTalkedTo() {
		return talkedTo;
	}
	public void setTalkedTo(boolean n) {
		talkedTo = n;
	}
	public boolean isChest() {
		return chest;
	}
	public Item getItem() {
		return item;
	}
	public int getAmountOfItems() {
		return amountOfItems;
	}
	public int locationItem(String m) {
		boolean integer = false;
		for(int i = 0; i < m.length(); i++) {
			char a = m.charAt(i);
			if(Character.isDigit(a)) {
				integer = true;
				break;
			}
		}
		if(!integer)
			return -1;
		for(int i = 0; i < m.length(); i++) {
			char a = m.charAt(i);
			if(Character.isAlphabetic(a))
				return i;
		}
		return -1;
	}
}
