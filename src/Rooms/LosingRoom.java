package Rooms;

import Game.Runner;
import People.Person;

public class LosingRoom extends Room {

    public LosingRoom(int x, int y) {
        super(x,y);
    }

    /**
     * Triggers the player losing sequence.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("You entered the losing room. Minus 10 points from Gryffindor.");
        Runner.gameOff();
    }
}
