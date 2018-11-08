package Entities;

import Game.Runner;

/**
 * Player represents the player as they move through the game.
 */
public class Player {
	String name;
	int xLoc, yLoc;
	int chp;



	int mhp;
	int wepAttk;
	int defense;


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
		chp -= dmg/(rnd/10*defense);
		if(chp < 0)
		{
			chp = 0;
		}
	}
	public int getWepStats()
	{
		return wepAttk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChp() {
		return chp;
	}

	public void setChp(int chp) {
		this.chp = chp;
	}
	public int getMhp() {
		return mhp;
	}

	public void setMhp(int mhp) {
		mhp = mhp;
	}
}
