package Pokemon.PokemonClass;

public class Stage extends PokemonMove {
    int turns;
    public Stage(String name, Type type, int minTurns, int maxTurns, int movePoint) { //attack
        super(name, type);
        turns = (int) (Math.random() * (maxTurns - minTurns)) + minTurns;
        super.setMovePoint(movePoint);
        super.setDescription(name + "|Stage lasts: " + turns + " turns" + "|Move Points: " + super.getCurrentMovePoint() + "/" + movePoint);
    }
    public int getTurns() {
        return turns;
    }
}
