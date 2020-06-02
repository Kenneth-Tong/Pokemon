package Pokemon;

import Pokemon.Items.Badge;
import Pokemon.Items.Item;
import Pokemon.Items.Key;
import Pokemon.PokemonClass.Pokemon;

import java.awt.*;
import java.util.ArrayList;

public class Player {
	private Rectangle hitBox;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	private String name, trainerType;
	private Color color;
	private int velocity = 3, Height = 15, Width = 15, posX = 0, posY = 0, lastX = 0, lastY = 0, moveX = 0, moveY = 0, pokemonDollar = 0;
	private boolean lost = false, isOnBike = false, isSuperBike = false;

	public Player(String name) {
		this.name = name;
		hitBox = new Rectangle(posX, posY, Height, Width);
		lastX = posX;
		lastY = posY;
	}
	public void updateHitBox() {
		hitBox = new Rectangle(posX, posY, Height, Width);
	}
	public void moveX() {
		lastX = posX;
		posX += moveX;
		updateHitBox();
	}
	public void setLocation(int x, int y) {
		posX = x;
		posY = y;
	}
	public void moveY() {
		lastY = posY;
		posY += moveY;
		updateHitBox();
	}
	public void resetX() {
		posX = lastX;
		updateHitBox();
	}
	public void resetY() {
		posY = lastY;
		updateHitBox();
	}
	public Rectangle getHitBox() {
		return hitBox;
	}
	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}
	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public Item getInventory(int i, boolean take) {
		Item returnItem = inventory.get(i);
		if(take) {
			inventory.get(i).useItem();
			if(inventory.get(i).getAmount() < 1)
				inventory.remove(i);
		}
		return returnItem;
	}
	public Item[] getInventory() {
		Item[] list = new Item[inventory.size()];
		int i = 0;
		for(Item n: inventory) {
			list[i] = n;
			i++;
		}
		return list;
	}
	public void addInventory(Item a) {
		for(Item n: inventory) {
			if(a.getName().equals(n.getName())) {
				n.addItem(a.getAmount()); //combined amount of items
				return;
			}
		}
		inventory.add(a);
	}
	public void setIsOnSuperBike(boolean n) {
		if(n)
			velocity = 7;
		else
			velocity = 3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return Height;
	}

	public int getWidth() {
		return Width;
	}

	public void addPokemon(Pokemon p) {
		pokemonList.add(p);
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemonList;
	}

	public Pokemon getPokemon(int i) {
		return pokemonList.get(i);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(String n) {
		trainerType = n;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean n) {
		lost = n;
	}

	public boolean getIsOnBike() {
		return isOnBike;
	}

	public boolean getIsOnSuperBike() { return isSuperBike; }

	public int getVelocity() {
		return velocity;
	}

	public int getPokemonDollar() { return pokemonDollar; }
	public void addPokemonDollar(int x) { pokemonDollar += x; }
	public void withdrawPokemonDollar(int x) {
		pokemonDollar -= x;
	}
	public void setIsOnBike(boolean n) {
		if(n)
			velocity = 5;
		else
			velocity = 3;
	}
	public void switchPokemon(Pokemon a, Pokemon b) {
		Pokemon changedWith = a;
		pokemonList.set(pokemonList.indexOf(a), b);
		pokemonList.set(pokemonList.indexOf(b), changedWith);
	}

	public boolean hasPokemon() { //can the person continue the fight
		if (pokemonList.size() > 5) {
			for (int i = 0; i < 6; i++) {
				if (!pokemonList.get(i).hasFainted()) //if at least one is not dead
					return true;
			}
		} else {
			for (int i = 0; i < pokemonList.size(); i++) {
				if (!pokemonList.get(i).hasFainted())
					return true;
			}
		}
		lost = true;
		return false;
	}
	public int hasKey(boolean TeamRocket) { //if i'm looking for team rocket key
		if(TeamRocket) {
			for (Item n : inventory) {
				if (n instanceof Key && ((Key) n).isTeamRocket()) {
					return inventory.indexOf(n);
				}
			}
		} else {
			for (Item n : inventory) {
				if (n instanceof Key) {
					return inventory.indexOf(n);
				}
			}
		}
		return -1;
	}
	public int hasBadge() {
		for (Item n : inventory) {
			if (n instanceof Badge) {
				return inventory.indexOf(n);
			}
		}
		return -1;
	}
	public String[] getInventoryNames() {
		String[] list = new String[inventory.size()];
		int i = 0;
		for (Item n : inventory) {
			list[i] = n.getName();
			i++;
		}
		return list;
	}
}
