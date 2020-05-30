package Pokemon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class GrassPokemon extends Pokemon {

	public GrassPokemon(String location) {
		super(location);
		setType(1);
		setName();
		super.setMoves(createMoves());
		super.setColor(new Color(154,205,50).darker());
	}
	public GrassPokemon(String location, String n) {
		super(location);
		setType(1);
		setName(n);
		super.setMoves(createMoves());
		super.setColor(new Color(154,205,50).darker());
	}
	public ArrayList<String> createMoves() { //12 moves per each
		int randomHeal = (int) (Math.random() * 9);
		ArrayList<String> attackList = new ArrayList<>();
		ArrayList<String> heaList = new ArrayList<>(Arrays.asList("Revitalize", "Gather", "Rain Dance", "Rest"));
		ArrayList<String> grassList = new ArrayList<>(Arrays.asList("Vine Whip", "Razor Leaf", "Seed Cannon", "Roots Spore", "Leaf Storm", "Apple Acid", "Leech Seed", "Wood Hammer", "Seed Flare", "Energy Ball", "Cotton Spore", "Bullet Speed"));
		switch(randomHeal) {
			case 1:
				attackList.add(heaList.get(1));
				attackList.add(heaList.get(0));
				break;
			case 2:
				attackList.add(heaList.get(2));
				attackList.add(heaList.get(1));
				break;
			case 3:
				attackList.add(heaList.get(3));
				attackList.add(heaList.get(1));
				break;
			case 4:
				attackList.add(heaList.get(0));
				attackList.add(heaList.get(2));
				break;
			default:
				super.setHasHealingMove(false);
				break;
		}
		attackList.addAll(grassList);
		return attackList;
	}

	public void setName() {
		String[] names = new String[] {"Snivy", "Turtwig", "Bulbasaur", "Geko"};
		super.setName(names[(int) (Math.random() * names.length)]);
	}
	public void setName(String n) {
		super.setName(n);
	}
}
