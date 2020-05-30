package Pokemon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class WaterPokemon extends Pokemon {

	public WaterPokemon(String location) {
		super(location);
		setType(2);
		setName();
		super.setMoves(createMoves());
		super.setColor(Color.BLUE);
	}
	public WaterPokemon(String location, String n) {
		super(location);
		setType(2);
		setName(n);
		super.setMoves(createMoves());
		super.setColor(Color.BLUE);
	}
	public ArrayList<String> createMoves() { //12 moves per each
		int randomHeal = (int) (Math.random() * 9);
		ArrayList<String> attackList = new ArrayList<>();
		ArrayList<String> heaList = new ArrayList<>(Arrays.asList("Revitalize", "Gather", "Rain Dance", "Rest"));
		ArrayList<String> waterList = new ArrayList<>(Arrays.asList("Aqua Strike", "Splash", "Water Cannon", "Jet Stream", "Hydro Pump", "Max Geyser", "Surf", "Water Gun", "Whirlpool", "Water Pulse", "Waterfall", "Soak"));
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
		attackList.addAll(waterList);
		return attackList;
	}
	public void setName() {
		String[] names = new String[] {	"Magikarp", "Merril", "Squirtle"};
		super.setName(names[(int) (Math.random() * names.length)]);
	}
	public void setName(String n) {
		super.setName(n);
	}
}
