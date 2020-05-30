package Pokemon;

public class Badge extends Item {
    public Badge(String name, int x) {
        super(name, x);
        super.setDescription("This is Andover's Gym Badge!");
    }
    public boolean hasBadge() {
        return true;
    }
}
