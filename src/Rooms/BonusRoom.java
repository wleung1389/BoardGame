package Rooms;

import Game.Runner;
import People.Person;

public class BonusRoom extends Room{
    private int c;
    private int d;
    public BonusRoom(int x, int y, int c, int d)
    {
        super(x,y);
        this.c = c;
        this.d = d;
    }

    /**
     * Triggers the bonus help system.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        double hint = (5*c+2)/3;
        System.out.println("You entered the bonux room. The hint is (5*c+2)/3 = " + hint + ". Find c to find the x coordinate of the winning room.");
    }
}
