package Map;

import Entities.Player;

public class startingPosition extends BasicRoom {

    public startingPosition(int x, int y)
    {
        super(x,y);
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Player entering
     */
    public void enterRoom(Player x)
    {
        System.out.println("*Cave Entrance*");
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
    public String toString()
    {
        return "[ENTR]";
    }
}