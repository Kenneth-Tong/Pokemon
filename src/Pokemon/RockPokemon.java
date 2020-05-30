package Pokemon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class RockPokemon extends Pokemon {

	public RockPokemon(String location) {
		super(location);
		setType(3);
		setName();
		super.setMoves(createMoves());
		super.setColor(Color.GRAY);
	}
	public RockPokemon(String location, String n) {
		super(location);
		setType(3);
		setName(n);
		super.setMoves(createMoves());
		super.setColor(Color.GRAY);
	}
	public ArrayList<String> createMoves() { //12 moves per each
		int randomHeal = (int) (Math.random() * 9);
		ArrayList<String> attackList = new ArrayList<>();
		ArrayList<String> heaList = new ArrayList<>(Arrays.asList("Revitalize", "Gather", "Rain Dance", "Rest"));
		ArrayList<String> rockList = new ArrayList<>(Arrays.asList("Roll Out", "Head Smash", "Earthquake", "Sand storm", "Dig", "Fissure", "Magnitude", "Mud Bomb", "Bone Rush", "Spikes", "Sand Tomb", "Sand Attack"));
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
		attackList.addAll(rockList);
		return attackList;
	}

	public void setName() {
		String[] names = new String[] {"Onyx", "Diglet", "Geodude"};
		super.setName(names[(int) (Math.random() * names.length)]);
	}
	public void setName(String n) {
		super.setName(n);
	}
}