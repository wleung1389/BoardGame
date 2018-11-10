package Map;

import Entities.Player;

import static Game.Runner.getRndInteger;

public class trainingSpot extends BasicRoom {
    private double multiplier = 0.5;
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
        int HP = getRndInteger(1,6);
        int lHP = getRndInteger(4,10);
        System.out.println("You've entered a training spot. You increased your max HP by " + HP + ". You lost " + lHP + ".");
        x.gainHP((int) Math.round(HP*multiplier));
        multiplier++;
        x.setChp(x.getChp() - lHP);
        System.out.println("Your HP stat now is " + x.getChp() + "/" + x.getMhp() + ".");
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
        return "{TRAIN}";
    }
}