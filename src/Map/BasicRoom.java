package Map;

import Entities.Player;

public class BasicRoom {
	Player occupant;
	int xLoc,yLoc;
	
	public BasicRoom(int x, int y)
	{
		xLoc = x;
		yLoc = y;
	}

	/**
	 * Method controls the results when a person enters this room.
	 * @param x the Player entering
	 */
	public void enterRoom(Player x)
	{
		System.out.println("You've entered a safe spot.");
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
		return "[SAFE]";
	}
}
