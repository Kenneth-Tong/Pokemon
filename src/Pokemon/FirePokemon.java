package Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

public class FirePokemon extends Pokemon {

	public FirePokemon(String location) {
		super(location);
		setType(0);
		setName();
		super.setMoves(createMoves());
		super.setColor(Color.RED);
	}
	public FirePokemon(String location, String n) {
		super(location);
		setType(0);
		setName(n);
		super.setMoves(createMoves());
		super.setColor(Color.RED);
	}
	public ArrayList<String> createMoves() { //12 moves per each
		int randomHeal = (int) (Math.random() * 9);
		ArrayList<String> attackList = new ArrayList<>();
		ArrayList<String> heaList = new ArrayList<>(Arrays.asList("Revitalize", "Gather", "Rain Dance", "Rest"));
		ArrayList<String> fireList = new ArrayList<>(Arrays.asList("Fire Blast", "Flare Blitz", "V-create", "Searing Shot", "Flame Throw", "Overheat", "Will o' Whisp", "Volcano", "Ember Shot", "Nitro Flare", "Hot Steps", "Fire Claw"));
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
		attackList.addAll(fireList);
		return attackList;
	}
	public void setName() {
		String[] names = new String[] {"Lickwit", "Charmander", "Ente"};
		super.setName(names[(int) (Math.random() * names.length)]);
	}
	public void setName(String n) {
		super.setName(n);
	}
}
