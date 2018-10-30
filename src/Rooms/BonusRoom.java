package Rooms;

import People.Person;

public class BonusRoom extends Room{
    private int c;
    public BonusRoom(int x, int y, int c)
    {
        super(x,y);
        this.c = c;
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
        double hint = Math.pow((4*c)+2,3);
        System.out.println("You entered the bonux room. The hint is ((4*c)+2)^3 = " + hint + ". Find c to find the x coordinate of the winning room.");
    }
}
