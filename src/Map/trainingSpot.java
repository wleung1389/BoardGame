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
        int HP = getRndInteger(0,6);
        System.out.println("You've entered a training spot. You increased your max HP by " + HP + ".");
        x.gainHP(HP);
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