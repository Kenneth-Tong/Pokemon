package Pokemon;

public class Key extends Item {
    private Boolean TeamRocket;
    public Key(String name, int x, boolean TeamRocket) {
        super(name, x);
        if (TeamRocket)
            super.setDescription("Key to open Team Rocket's door!");
        else
            super.setDescription("Key to open a door!");
        this.TeamRocket = TeamRocket;
    }
    public boolean isTeamRocket() {
        return TeamRocket;
    }
}
