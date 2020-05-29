package Pokemon;

import java.awt.*;
import java.util.ArrayList;

public class Player {
	private Rectangle hitBox;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	private String name, trainerType;
	private Color color;
	private int velocity = 3, Height = 15, Width = 15, posX = 0, posY = 0, lastX = 0, lastY = 0, moveX = 0, moveY = 0, pokemonDollar = 0;
	private boolean lost = false, isOnBike = false;

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
	public Player(String name) {
		this.name = name;
		hitBox = new Rectangle(posX, posY, Height, Width);
		lastX = posX;
		lastY = posY;
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
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	public ArrayList<String> getInventoryNames() {
		ArrayList<String> list = new ArrayList<String>();
		for(Item n: inventory) {
			list.add(n.getName());
		}
		return list;
	}
	public void addInventory(Item a) {
		inventory.add(a);
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
	public void switchPokemon(Pokemon a, Pokemon b) {
		Pokemon changedWith = a;
		pokemonList.set(pokemonList.indexOf(a), b);
		pokemonList.set(pokemonList.indexOf(b), changedWith);
	}
	public boolean hasPokemon() { //can the person continue the fight
		if(pokemonList.size() > 5) {
			for (int i = 0; i < 6; i++) {
				if(!pokemonList.get(i).hasFainted()) //if at least one is not dead
					return true;
			}
		} else {
			for (int i = 0; i < pokemonList.size(); i++) {
				if(!pokemonList.get(i).hasFainted())
					return true;
			}
		}
		lost = true;
		return false;
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
	public void setIsOnBike(boolean n) {
		isOnBike = n;
		if(isOnBike) {
			velocity = 5;
		} else {
			velocity = 3;
		}
	}
	public int getVelocity() {
		return velocity;
	}
	public int getPokemonDollar() {
		return pokemonDollar;
	}
	public void addPokemonDollar(int x) {
		pokemonDollar += x;
	}
	public void withdrawPokemonDollar(int x) {
		pokemonDollar -= x;
	}
}
