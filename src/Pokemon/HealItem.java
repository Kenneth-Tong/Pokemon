package Pokemon;

public class HealItem extends Item{
    private int heal;
    public HealItem(String name, int amount, int effectiveness) {
        super(name, amount);
        heal = effectiveness;
        super.setDescription("Heals for " + heal + " hp");
    }
    public HealItem(String name, int amount) {
        super(name, amount);
        heal = (int) (Math.random() * 20) + 10;
    }
    public int getHeal() {
        return heal;
    }
}
