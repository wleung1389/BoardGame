package Entities;

import Game.Runner;

/**
 * Player represents the player as they move through the game.
 */
public class Player {
	String name;
	int xLoc, yLoc;
	int hp, wepAttk, defense;


	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Player(String n, int xLoc, int yLoc)
	{
		this.name = n;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	public void attacked(int dmg)
	{
		double rnd = Runner.getRndInteger(1,5);
		hp -= dmg/(rnd/10*defense);
		if(hp < 0)
		{
			hp = 0;
		}
	}
	public int getWepStats()
	{
		return wepAttk;
	}


}
