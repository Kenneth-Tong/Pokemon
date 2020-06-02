package Pokemon.PokemonClass;

public class Damage extends PokemonMove{
    int min, max;
    public Damage(String name, Type type, int min, int max, int movePoint) { //attack
        super(name, type);
        this.min = min;
        this.max = max;
        super.setMovePoint(movePoint);
        super.setDescription(name + "|Damage: " + min + "-" + max + "|Move Points: " + super.getCurrentMovePoint() + "/" + movePoint);
    }
    public int doMove(int turns, Stage s) {
        super.doMove();
        int damage = (int) (Math.random() * (max - min)) + min;
        if(turns > 0 && s.getType().toString().equals(super.getType().toString())) { //buffs attack
            damage *= 1.15;
        }
        return damage;
    }
}