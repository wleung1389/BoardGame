package Map;

import Entities.Player;

import static Game.Runner.getRndInteger;

public class trainingSpot extends BasicRoom {

    public trainingSpot(int x, int y)
    {
        super(x,y);
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Player entering
     */
    public void enterRoom(Player x)
    {
        int gAttk = getRndInteger(0,6);
        int gDefense = getRndInteger(0,3);
        int lHp = getRndInteger(0,6);
        System.out.println("You've entered a training spot. You gained " + gAttk + " attack, " + gDefense + " defense and lost" + lHp + " HP.");
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
    }

    /**
     * Removes the player from the room.
     * @param x
     */
    public void leaveRoom(Player x)
    {
        occupant = null;
    }

}