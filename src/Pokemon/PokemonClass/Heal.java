package Pokemon.PokemonClass;

public class Heal extends PokemonMove{
    int min, max;
    public Heal(String name, Type type, int min, int max, int movePoint) {
        super(name, type);
        this.min = min;
        this.max = max;
        super.setMovePoint(movePoint);
        super.setDescription(name + "|Heal:" + min + "-" + max + "|Move Points: " + super.getCurrentMovePoint() + "/" + movePoint);
    }
    public int doMove() {
        super.doMove();
        return (int) (Math.random() * (max - min)) + min;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
}
