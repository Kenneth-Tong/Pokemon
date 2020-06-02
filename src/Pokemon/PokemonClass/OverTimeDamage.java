package Pokemon.PokemonClass;

public class OverTimeDamage extends Damage {
    int damage, turns;
    public OverTimeDamage(String name, Type type, int min, int max, int maxTurns, int minTurns, int movePoint) { //attack
        super(name, type, min, max, movePoint);
        damage = (int) (Math.random() * (max - min)) + min;
        turns = (int) (Math.random() * (maxTurns - minTurns)) + minTurns;
        super.setMovePoint(movePoint);
        super.setDescription(name + "|Damage: " + damage + " over " + turns + " turns" + "|Move Points: " + super.getCurrentMovePoint() + "/" + movePoint);
    }
    public int getTurns() {
        return turns;
    }
    public int getDamage() {
        return damage;
    }
}
