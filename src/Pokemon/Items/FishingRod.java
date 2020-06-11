package Pokemon.Items;

import Pokemon.Player;

public class FishingRod extends Item {
    boolean bestRod = false;
    public FishingRod(String name, int n, boolean bestRod) {
        super(name, n);
        bestRod = true;
        if(bestRod)
            super.setDescription("Used to fish very fast!");
        else
            super.setDescription("Used to fish!");
    }
    public void goFish(Player p) {
        p.setFishing(true);
    }
    public boolean isBestRod() {
        return bestRod;
    }
}
