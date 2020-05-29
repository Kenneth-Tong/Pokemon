package Pokemon;

public class PokemonMove {
	private int min, max, movePoint, currentMovePoint;
	private String name;
	private Type type;
	private boolean isHeal = false;

	public PokemonMove(String name, int min, int max, int movePoint, Type type) { //attack
		this.name = name;
		this.min = min;
		this.max = max;
		this.movePoint = movePoint;
		currentMovePoint = movePoint;
		this.type = type;
	}
	public PokemonMove(String name, int min, int max, int movePoint) { //heal
		this.name = name;
		this.min = min;
		this.max = max;
		this.movePoint = movePoint;
		currentMovePoint = movePoint;
		isHeal = true;
	}
	public int doMove() {
		if(currentMovePoint > 0) { //the move point decreases
			currentMovePoint--;
			return (int) (Math.random() * (max - min)) + min;
		}	
		return -1;
	}
	public Type getType() {
		return type;
	}
	public int getMax() {
		return max;
	}
	public int getMin() {
		return min;
	}
	public void reset() {
		currentMovePoint = movePoint;
	}
	public String toString() {
		return name;
	}
	public boolean isHeal() {
		return isHeal;
	}
	public String getName() {
		return name;
	}
	public int getMovePoint() {
		return movePoint;
	}
	public int getCurrentMovePoint() {
		return currentMovePoint;
	}
}
