package Map;

import Entities.Player;
import Game.Runner;

public class finalBossDungeon extends safeSpot
{

	public finalBossDungeon(int x, int y) {
		super(x, y);

	}

	/**
	 * Triggers the player's winning sequence.
	 * @param x the Player entering
	 */
	@Override
	public void enterRoom(Player x) {

		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		System.out.println("You found the winning room! Ten points for Gryffindor.");
		Runner.gameOff();
	}
	public String toString()
	{
		return "[Boss]";
	}

}
