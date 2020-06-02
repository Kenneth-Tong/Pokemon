package Pokemon.PokemonClass;

/*
 * Holds types of pokemon and what is effective
 */

public class Type {
	private boolean fire = false, grass = false, water = false, rock = false;
	public Type(int i) {
		switch(i) {
			case 0:
				fire = true;
				break;
			case 1:
				grass = true;
				break;
			case 2:
				water = true;
				break;
			case 3:
				rock = true;
				break;
		}
	}
	public String toString() {
		if(fire)
			return "Fire";
		if(grass)
			return "Grass";
		if(water)
			return "Water";
		if(rock)
			return "Rock";
		return "Null";
	}
}
