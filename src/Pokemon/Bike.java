package Pokemon;

public class Bike extends Item{
    private Player player;
    public Bike(String name, int x, Player p) {
        super(name, x);
        player = p;
        super.setDescription("A bike used to travel faster!");
    }
    public void setBike(boolean n) {
        if(n)
            player.setIsOnBike(true);
        else
            player.setIsOnBike(false);
    }
}
