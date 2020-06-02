package Pokemon.PokemonClass;

public class PokemonMove {
	private int movePoint, currentMovePoint;
	private String name, description;
	private Type type;

	public PokemonMove(String name, Type type) { //attack
		this.name = name;
		this.type = type;
	}

	public int doMove() {
		if(currentMovePoint > 0) { //the move point decreases
			currentMovePoint--;
		}
		return -1;
	}

	public Type getType() {
		return type;
	}

	public void reset() {
		currentMovePoint = movePoint;
	}

	public int getCurrentMovePoint() {
		return currentMovePoint;
	}

	public void setMovePoint(int movePoint) {
		this.movePoint = movePoint;
		currentMovePoint = movePoint;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
