package Pokemon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pokemon {
	private String name, location, effective; //effective tells attacks and effectiveness
	private Type type;
	private PokemonMove[] PokemonMoves = new PokemonMove[4];
	private Color color;
	private int health, maxHealth; //maxHealth calls when you reset their health to max, health is current health
	private boolean hasHealingMove = true;
	public Pokemon(String location, int i) {
		int healthLocation = location.compareTo("A") + 1; //depending on where on the map the pokemon is, the more powerful it is
		int random = (int) (Math.random() * 15 * healthLocation) + 10;
		health = random;
		maxHealth = random;
		this.location = location;
		type = new Type(i);
		setColor(i);
		setMoves();
		setName();
	}
	public Pokemon(String location, int i, String pokemon) {
		int healthLocation = location.compareTo("A") + 1; //depending on where on the map the pokemon is, the more powerful it is
		int random = (int) (Math.random() * 15 * healthLocation) + 10;
		health = random;
		maxHealth = random;
		this.location = location;
		type = new Type(i);
		setColor(i);
		setMoves();
		setName(pokemon);
	}
	public void setColor(int n) {
		switch(n) {
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.GREEN;
				break;
			case 2:
				color = Color.BLUE;
				break;
			case 3:
				color = Color.lightGray;
				break;
		}
	}

	public PokemonMove[] getPokemonMoves() {
		return PokemonMoves;
	}
	public Color getColor() { return color; }
	public Type getType() {
		return type;
	}
	public int getHealth() {
		return health;
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
	public void setName() {
		String[] list = new String[0];
		switch(type.toString()) {
			case "Fire":
				list = new String[] {"Litwick", "Charmander", "Flareon", "Torchic", "Magby", "Tepig", "Volcarona", "Litten"};
				break;
			case "Grass":
				list = new String[] {"Skiddo", "Petilil", "Bulbasaur", "Bellsprout", "Hoppip", "Skiploom", "Applin", "Rowlet", "Deerling", "Shiftry", "Jumpluff"};
				break;
			case "Water":
				list = new String[] {"Golduck", "Poliwag", "Seel", "Shellder", "Krabby", "Horsea", "Staryu", "Vaporeon", "Magikarp", "Mudkip", "Wailmer", "Corphish", "Buizel"};
				break;
			case "Rock":
				list = new String[] {"Bonsly", "Sudowoodo", "Omanyte", "Golem", "Onix", "Larvitar", "Lunatone", "Solrock", "Armaldo", "Tryunt", "Crustle", "Relicanth", "Dwebble"};
				break;
		}
		name = list[(int) (Math.random() * list.length)];
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
	public void setMoves() {
		ArrayList<String> attackList = new ArrayList<>();
		switch(type.toString()) {
			case "Fire":
				attackList = new ArrayList<>(Arrays.asList("Fire Blast", "Flare Blitz", "V-create", "Searing Shot", "Flame Throw", "Overheat", "Will o' Whisp", "Volcano", "Ember Shot", "Nitro Flare", "Hot Steps", "Fire Claw"));
				break;
			case "Water":
				attackList = new ArrayList<>(Arrays.asList("Aqua Strike", "Splash", "Water Cannon", "Jet Stream", "Hydro Pump", "Max Geyser", "Surf", "Water Gun", "Whirlpool", "Water Pulse", "Waterfall", "Soak"));
				break;
			case "Grass":
				attackList = new ArrayList<>(Arrays.asList("Vine Whip", "Razor Leaf", "Seed Cannon", "Roots Spore", "Leaf Storm", "Apple Acid", "Leech Seed", "Wood Hammer", "Seed Flare", "Energy Ball", "Cotton Spore", "Bullet Speed"));
				break;
			case "Rock":
				attackList = new ArrayList<>(Arrays.asList("Roll Out", "Head Smash", "Earthquake", "Sand storm", "Dig", "Fissure", "Magnitude", "Mud Bomb", "Bone Rush", "Spikes", "Sand Tomb", "Sand Attack"));
				break;
		}
		ArrayList<String> healList = new ArrayList<>(Arrays.asList("Wish", "Moonlight", "Recovery", "Synthesis", "Rest"));
		int randomHeal = (int) (Math.random() * 12);
		switch(randomHeal) {
			case 1:
				attackList.add(healList.get(0));
				attackList.add(healList.get(1));
				break;
			case 2:
				attackList.add(healList.get(2));
				attackList.add(healList.get(3));
				break;
			case 3:
				attackList.add(healList.get(4));
				attackList.add(healList.get(0));
				break;
			case 4:
				attackList.add(healList.get(1));
				attackList.add(healList.get(2));
				break;
			default:
				hasHealingMove = false;
				break;
		}
		int[] movesPicked = new int[4];
		for(int i = 0; i < 4; i++) {
			int random = (int) (Math.random() * attackList.size());
			while(contains(movesPicked, random)) {
				random = (int) (Math.random() * attackList.size());
			}
			movesPicked[i] = random;
		}
		int i = 0, damageCheck = location.compareTo("A") + 1;
		for(int x: movesPicked) {
			int max = (int) (Math.random() * 5) + 10 * damageCheck;
			int min = (int) (Math.random() * 5) + 5 * damageCheck;
			if(x > attackList.size() - 3 && hasHealingMove) { //healing move
				int movePoint = (int) (Math.random() * 4) + 3;
				setMove(i, new PokemonMove(attackList.get(x), max, min, movePoint));
			} else { //damage dealer
				int movePoint = (int) (Math.random() * 4) + 6;
				setMove(i, new PokemonMove(attackList.get(x), max, min, movePoint, getType()));
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