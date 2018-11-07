package Map;

import Entities.Player;

public class hardDungeon extends safeSpot {

    public hardDungeon(int x, int y) {
        super(x, y);
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Player entering
     */
    public void enterRoom(Player x) {
        System.out.println("You've entered a safe spot.");
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
    }

    /**
     * Removes the player from the room.
     *
     * @param x
     */
    public void leaveRoom(Player x) {
        occupant = null;
    }
    public String toString()
    {
        return "[Hard]";
    }
}
