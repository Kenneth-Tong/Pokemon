package Pokemon;

import java.awt.*;
import java.util.ArrayList;

public class Pokemon {
	private String name, location, effective; //effective tells attacks and effectiveness
	private Type type;
	private PokemonMove[] PokemonMoves = new PokemonMove[4];
	private Color color;
	private int health, maxHealth; //maxHealth calls when you reset their health to max, health is current health
	private boolean hasHealingMove = true;
	public Pokemon(String location) {
		int healthLocation = location.compareTo("A") + 1; //depending on where on the map the pokemon is, the more powerful it is
		int random = (int) (Math.random() * 15 * healthLocation) + 10;
		health = random;
		maxHealth = random;
		this.location = location;
	}
	public void setHasHealingMove(boolean x) {
		hasHealingMove = x;
	}
	public void setColor(Color c) { color = c; }
	public Color getColor() { return color; }
	public Type getType() {
		return type;
	}
	public void setType(int n) {
		type = new Type(n);
	}
	public int getHealth() {
		return health;
	}
	public PokemonMove[] getPokemonMoves() {
		return PokemonMoves;
	}
	public PokemonMove getPokemonMoves(int i) {
		return PokemonMoves[i];
	}
	public void setMove(int i, PokemonMove m) {	PokemonMoves[i] = m; }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PokemonMove getAttack(int i) {
		return PokemonMoves[i];
	}
	public String toString() {
		return name;
	}
	public void reset() {
		health = maxHealth;
		for(PokemonMove n: PokemonMoves) {
			n.reset();
		}
	}
	public void heal(int i) {
		if(health + i > maxHealth) {
			health = maxHealth;
		} else {
			health += i;
		}
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int damageTaken(int taken, Type t2) {
		String ifEffective = result(t2);
		switch(ifEffective) {
			case "none":
				effective = "none";
				break;
			case "yes":
				taken *= 1.15;
				effective = "yes";
				break;
			case "no":
				effective = "no";
				taken *= 0.85;
				break;
		}
		health -= taken;
		return taken;
	}
	public String getEffective() {
		String returning = effective;
		effective = null;
		return returning;
	}
	public boolean hasHealingMove() { return hasHealingMove; }
	public boolean hasFainted() {
		if(health < 0)  //if it's health is above 0
			return true;
		return false;
	}
	public void setMoves(ArrayList<String> n) {
		int[] movesPicked = new int[4];
		for(int i = 0; i < 4; i++) {
			int random = (int) (Math.random() * n.size());
			while(contains(movesPicked, random)) {
				random = (int) (Math.random() * n.size());
			}
			movesPicked[i] = random;
		}
		int i = 0, damageCheck = location.compareTo("A");
		for(int x: movesPicked) {
			int max = (int) (Math.random() * 5) + 10 * damageCheck;
			int min = (int) (Math.random() * 5) + 5 * damageCheck;
			if(x < 2 && hasHealingMove) { //healing move
				int movePoint = (int) (Math.random() * 4) + 3;
				setMove(i, new PokemonMove(n.get(x), max, min, movePoint));
			} else { //damage dealer
				int movePoint = (int) (Math.random() * 4) + 6;
				setMove(i, new PokemonMove(n.get(x), max, min, movePoint, getType()));
			}
			i++;
		}
	}
	public boolean contains(int[] array, int x)
	{
		for(int n: array)
			if(n == x)
				return true;
		return false;
	}
	public String result(Type t2) {
		if (new Type(0).toString().equals(t2.toString())) { //fire move
			if (new Type(0).toString().equals(type.toString())) {
				return "none"; //nothing
			} else if (new Type(1).toString().equals(type.toString())) {
				return "yes"; //effective
			} else if (new Type(2).toString().equals(type.toString())) {
				return "no"; //not effective
			} else if (new Type(3).toString().equals(type.toString())) {
				return "none";
			}
		} else if (new Type(1).toString().equals(t2.toString())) {
			if (new Type(0).toString().equals(type.toString())) {
				return "no"; //nothing
			} else if (new Type(1).toString().equals(type.toString())) {
				return "none"; //effective
			} else if (new Type(2).toString().equals(type.toString())) {
				return "yes"; //not effective
			} else if (new Type(3).toString().equals(type.toString())) {
				return "none";
			}
		} else if (new Type(2).toString().equals(t2.toString())) {
			if (new Type(0).toString().equals(type.toString())) {
				return "yes"; //nothing
			} else if (new Type(1).toString().equals(type.toString())) {
				return "no"; //effective
			} else if (new Type(2).toString().equals(type.toString())) {
				return "none"; //not effective
			} else if (new Type(3).toString().equals(type.toString())) {
				return "none";
			}
		} else if (new Type(3).toString().equals(t2.toString())) {
			if (new Type(0).toString().equals(type.toString())) {
				return "no";
			} else if (new Type(1).toString().equals(type.toString())) {
				return "none";
			} else if (new Type(2).toString().equals(type.toString())) {
				return "yes";
			} else if (new Type(3).toString().equals(type.toString())) {
				return "none";
			}
		}
		return null;
	}
}