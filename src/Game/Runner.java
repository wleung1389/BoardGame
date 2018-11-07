package Game;

import Entities.Player;
import Map.*;

import javax.swing.*;
import java.util.Scanner;

public class Runner {
	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		safeSpot[][] map = new safeSpot[20][50];
		
		//Fill the map with normal rooms
		for (int x = 0; x<map.length; x++)
		{
			for (int y = 0; y < map[x].length; y++)
			{
				map[x][y] = null;
			}
		}
        int x = 0;
		int y = 0;
        map[0][0] = new startingPosition(0,0);
        for(int i = 0; i < 500; i++)
        {
            if(x == 0 && y == 0)
            {
                map[0][0] = new startingPosition(0,0);
                x++;
                y++;
            }
            else
            {
                double rnd = getRndInteger(1, 4);
                if (rnd == 1)
                {
                    if (y + 1 > 50)
                    {
                        y--;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        y++;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 2)
                {
                    if (x + 1 > 20)
                    {
                        x--;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        x++;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 3)
                {
                    if (y - 1 < 0)
                    {
                        y++;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        y--;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 4)
                {
                    if (x - 1 < 0)
                    {
                        x++;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        x--;
                        makeRndRoom(x,y,map);
                    }
                }
            }
        }

		 
		 //Setup player 1 and the input scanner
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();
		Player player1 = new Player(name, 0,0);
		map[0][0].enterRoom(player1);

		while(gameOn)
		{
            System.out.println("Where would you like to move? (Choose N, S, E, W)");
            String move = in.nextLine();
		    if(String.valueOf(move).toLowerCase().equalsIgnoreCase("map") || String.valueOf(move).toLowerCase().equalsIgnoreCase("m"))
		    {
		        for(int i = 0; i < map.length; i++)
		        {
                    for (int c = 0; c < map[i].length; c++)
                    {
                        if(map[i][c] == null)
                        {
                            System.out.print("   ");
                            System.out.print("\t");
                        }
                        else
                        {
                            System.out.print(map[i][c].toString());
                            System.out.print("\t");
                        }
                    }
                    System.out.println();
                }
            }
            else
            {

                if(validMove(move, player1, map))
                {
                    System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

                }
                else
                    {
                    System.out.println("Please choose a valid move.");
                }
            }
		}
		in.close();
	}

	/**
	 * Checks that the movement chosen is within the valid game map.
	 * @param move the move chosen
	 * @param p person moving
	 * @param map the 2D array of rooms
	 * @return
	 */
	public static boolean validMove(String move, Player p, safeSpot[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "n":
				if (p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "e":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "w":
				if (p.getyLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			default:
				break;
					
		}
		return true;
	}
	public static void gameOff()
	{
		gameOn = false;
	}

    public static int getRndInteger(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) ) + min;
    }
    public static void makeRndRoom(int x, int y, safeSpot[][] map)
    {
        if(x >= 20)
        {
            x = 19;
        }
        if(y >= 50)
        {
            y = 49;

        }
        int rnd = getRndInteger(1,8);
        if(rnd == 1)
        {
            int rndX = getRndInteger(0,19);
            int rndY = getRndInteger(0,49);
            map[x][y] = new teleportationSpot(x,y,rndX,rndY);
        }
        if(rnd == 2)
        {
            int c = getRndInteger(1,15);
            map[x][y] = new hiddenDungeon(x,y,c);
        }
        if(rnd == 3)
        {
            map[x][y] = new safeSpot(x,y);
        }
        if(rnd == 4)
        {
            map[x][y] = new easyDungeon(x,y);
        }
        if(rnd == 5)
        {
            map[x][y] = new normalDungeon(x,y);
        }
        if(rnd == 6)
        {
            map[x][y] = new hardDungeon(x,y);
        }
        if(rnd == 7)
        {
            map[x][y] = new expertDungeon(x,y);
        }
        if(rnd == 8)
        {
            map[x][y] = new trainingSpot(x,y);
        }
    }
}
