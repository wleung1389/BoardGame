package Map;

import Entities.Player;

public class teleportationSpot extends safeSpot {
    private int rndx;
    private int rndy;
    public teleportationSpot(int x, int y, int rndx, int rndy) {
        super(x,y);
        this.rndx = rndx;
        this.rndy = rndy;
    }

    /**
     * Triggers the player losing sequence.
     * @param x the Player entering
     */
    @Override
    public void enterRoom(Player x) {

        occupant = x;
        x.setxLoc(this.rndx);
        x.setyLoc(this.rndy);
        System.out.println("You entered the teleportation room.");
    }
    public String toString()
    {
        return "[Tele]";
    }
}
