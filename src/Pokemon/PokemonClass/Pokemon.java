package Pokemon.PokemonClass;

import java.awt.*;
import java.util.ArrayList;
import Pokemon.GuiMap;

public class Pokemon {
	private String name, location, effective; //effective tells attacks and effectiveness
	private Type type;
	private PokemonMove[] pokemonMoves = new PokemonMove[4];
	private Color color;
	private int health, maxHealth, stageTurn, burnDamage, burnTurns; //maxHealth calls when you reset their health to max, health is current health
	private Stage stage;

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
		return pokemonMoves;
	}
	public Color getColor() { return color; }
	public Type getType() {
		return type;
	}
	public int getHealth() {
		return health;
	}
	public PokemonMove getPokemonMoves(int i) {
		return pokemonMoves[i];
	}
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

	public String toString() {
		return name;
	}
	public void reset() {
		health = maxHealth;
		for(PokemonMove n: pokemonMoves) {
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
	public int damageTaken(PokemonMove move, Stage s, int stageTurn) {
		String ifEffective = result(move.getType());
		int damageTaken = ((Damage) move).doMove(stageTurn, s);
		switch(ifEffective) {
			case "none":
				effective = "none";
				break;
			case "yes":
				damageTaken *= 1.15;
				effective = "yes";
				break;
			case "no":
				effective = "no";
				damageTaken *= 0.85;
				break;
		}
		health -= damageTaken;
		if(move instanceof OverTimeDamage) {
			burnTurns = ((OverTimeDamage) (move)).getTurns();
			burnDamage = ((OverTimeDamage) (move)).getDamage();
		}
		return damageTaken;
	}
	public boolean isBurning() {
		return burnTurns != 0;
	}
	public void turnPass() {
		if(stageTurn > 0) {
			stageTurn--;
		}
		if(burnTurns > 0)
			burnTurns--;
	}
	public void burn() {
		if(burnTurns > 0) {
			if (health - burnDamage < 1)
				health = 0;
			else
				health -= burnDamage;
		}
	}
	public void setStage() {
		switch(type.toString()) {
			case "Fire":
				GuiMap.stage = new Color(220,20,60);
				break;
			case "Grass":
				GuiMap.stage = new Color(34,139,34);
				break;
			case "Water":
				GuiMap.stage = new Color(30,144,255);
				break;
			case "Rock":
				GuiMap.stage = new Color(112,128,144);
				break;
		}
		stageTurn = ((Stage) pokemonMoves[3]).getTurns();
		stage = ((Stage) pokemonMoves[3]);
	}
	public String getEffective() {
		String returning = effective;
		effective = null;
		return returning;
	}
	public boolean hasFainted() {
		if(health < 1)
			return true;
		return false;
	}
	public void setMoves() {
		ArrayList<PokemonMove> attackList = new ArrayList<>();
		int damageCheck = location.compareTo("A") + 1; //sets scaling damage

		String[] attackListNames = new String[] {"This should be re-written"};
		String damageOverTimeName = "";
		switch(type.toString()) {
			case "Fire":
				attackListNames = new String[] {"Fire Blast", "Flare Blitz", "V-create", "Searing Shot", "Flame Throw", "Overheat", "Volcano", "Ember Shot", "Nitro Flare", "Fire Breath", "Fire Claw"};
				damageOverTimeName = "Will o' Whisp";
				break;
			case "Grass":
				attackListNames = new String[] {"Vine Whip", "Razor Leaf", "Seed Cannon", "Roots Spore", "Leaf Storm", "Wood Hammer", "Seed Flare", "Energy Ball", "Cotton Spore", "Bullet Speed"};
				damageOverTimeName = "Toxin Petal";
				break;
			case "Water":
				attackListNames = new String[] {"Aqua Strike", "Water Cannon", "Jet Stream", "Hydro Pump", "Max Geyser", "Surf", "Water Gun", "Whirlpool", "Water Pulse", "Waterfall", "Soak"};
				damageOverTimeName = "Scald";
				break;
			case "Rock":
				attackListNames = new String[] {"Roll Out", "Head Smash", "Earthquake", "Sand storm", "Dig", "Fissure", "Magnitude", "Mud Bomb", "Bone Rush", "Spikes", "Sand Tomb", "Sand Attack"};
				damageOverTimeName = "Meteorite Slam";
				break;
		}

		for(int i = 0; i < attackListNames.length; i++) {
			int max = (int) (Math.random() * 5) + 10 * damageCheck;
			int min = (int) (Math.random() * 5) + 5 * damageCheck;
			int movePoint = (int) (Math.random() * 4) + 3;
			attackList.add(i, new Damage(attackListNames[i], type, min, max, movePoint));
		}

		int[] movesPicked = new int[4];
		for(int i = 0; i < 4; i++) {
			int random = (int) (Math.random() * attackListNames.length);
			while(contains(movesPicked, random)) {
				random = (int) (Math.random() * attackListNames.length);
			}
			movesPicked[i] = random;
		}

		for(int i = 0; i < 4; i++) {
			pokemonMoves[i] = attackList.get(movesPicked[i]);
		}

		String[] healList = new String[]{"Wish", "Moonlight", "Recovery", "Synthesis", "Rest"};
		if ((int) (Math.random() * 5) == 0) {
			int movePoint = (int) (Math.random() * 4) + 3;
			int max = (int) (Math.random() * 5) + 8 * damageCheck;
			int min = (int) (Math.random() * 5) + 5 * damageCheck;
			pokemonMoves[0] = new Heal(healList[(int) (Math.random() * (healList.length - 1))], type, min, max, movePoint);
		} /*else if((int) (Math.random() * 5) == 2) { */ else {
			int movePoint = (int) (Math.random() * 4) + 3;
			int max = (int) (Math.random() * 2) + 5 * damageCheck;
			int min = (int) (Math.random() * 2) + 3 * damageCheck;
			int minTurns = (int) (Math.random() * 2) + damageCheck;
			int maxTurns = (int) (Math.random() * 2) + 3 * damageCheck;
			pokemonMoves[0] = new OverTimeDamage(damageOverTimeName, type, min, max, minTurns, maxTurns, movePoint);
		}

		String[] stageList = new String[] {"Rain Dance", "Heat Wave", "Rock Slide", "Forest Growth"};
//		if((int) (Math.random() * 6) == 3) {
			int min = (int) (Math.random() * 2) + 3 * damageCheck + 1;
			int max = (int) (Math.random() * 2) + 2 * damageCheck;
			int movePoint = (int) (Math.random() * 2) + 3;
			String name = "Null";
			switch(type.toString()) {
				case "Fire":
					name = stageList[1];
					break;
				case "Grass":
					name = stageList[3];
					break;
				case "Water":
					name = stageList[0];
					break;
				case "Rock":
					name = stageList[2];
					break;
			}
			pokemonMoves[3] = new Stage(name, type, min, max, movePoint);
//		}
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
	public int hasHealingMove() {
		for(int i = 0; i < pokemonMoves.length; i++) {
			if(pokemonMoves[i] instanceof Heal)
				return i;
		}
		return -1;
	}
	public void setStageTurn(int n) {
		stageTurn = n;
	}
	public Stage getStage() {
		return stage;
	}
	public boolean hasMovePoint(int i) {
		return pokemonMoves[i].getCurrentMovePoint() > 0;
	}
	public int getStageTurn() {
		return stageTurn;
	}
	public void setHealth(int i) {
		health = i;
	}
	public boolean hasMoves() {
		for(PokemonMove n: pokemonMoves) {
			if (n.getCurrentMovePoint() > 0) {
				return true;
			}
		}
		return false;
	}
}