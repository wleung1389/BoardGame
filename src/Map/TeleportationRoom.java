package Map;

import People.Person;

public class TeleportationRoom extends Room {
    private int rndx;
    private int rndy;
    public TeleportationRoom(int x, int y, int rndx, int rndy) {
        super(x,y);
        this.rndx = rndx;
        this.rndy = rndy;
    }

    /**
     * Triggers the player losing sequence.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        occupant = x;
        x.setxLoc(this.rndx);
        x.setyLoc(this.rndy);
        System.out.println("You entered the losing room. Minus 10 points from Gryffindor.");
    }
}
