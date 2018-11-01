package Game;

import People.Person;
import Map.*;

import java.util.Scanner;

public class Runner {
	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		Room[][] map = new Room[100][100];
		
		//Fill the map with normal rooms
		for (int x = 0; x<map.length; x++)
		{
			for (int y = 0; y < map[x].length; y++)
			{
				map[x][y] = new Room(x,y);
			}
		}
        int x = 0;
		int y = 0;
        map[0][0] = new startingRoom();
        for(int i = 0; i < 200; i++)
        {
            if(x == 0 && y == 0)
            {
                x++;
                y++;
            }
            else
            {
                double rnd = getRndInteger(1, 4);
                if (rnd == 1)
                {
                    if (y + 1 > map.length)
                    {
                        y--;
                    }
                    else
                        {
                        y++;
                    }
                }
                if (rnd == 2)
                {
                    if (x + 1 > map.length)
                    {
                        x--;
                    }
                    else
                        {
                        x++;
                    }
                }
                if (rnd == 3)
                {
                    if (y - 1 < 0)
                    {
                        y++;
                    }
                    else
                        {
                        y--;
                    }
                }
                if (rnd == 4)
                {
                    if (x - 1 < 0)
                    {
                        x++;
                    }
                    else
                    {
                        x--;
                    }
                }
            }
        }

		 
		 //Setup player 1 and the input scanner
		Person player1 = new Person("FirstName", "FamilyName", 0,0);
		map[0][0].enterRoom(player1);
		Scanner in = new Scanner(System.in);
		while(gameOn)
		{
			System.out.println("Where would you like to move? (Choose N, S, E, W)");
			String move = in.nextLine();
			if(validMove(move, player1, map))
			{
				System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
				
			}
			else {
				System.out.println("Please choose a valid move.");
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
	public static boolean validMove(String move, Person p, Room[][] map)
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

}
