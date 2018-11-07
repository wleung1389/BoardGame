package Map;

import Entities.Player;

public class hiddenDungeon extends safeSpot {
    private int c;
    public hiddenDungeon(int x, int y, int c)
    {
        super(x,y);
        this.c = c;
    }

    /**
     * Triggers the bonus help system.
     * @param x the Player entering
     */
    @Override
    public void enterRoom(Player x) {

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("You entered the bonux room.");
    }
    public String toString()
    {
        return "    ";
    }
}
