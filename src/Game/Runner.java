package Game;

import People.Person;
import Rooms.*;

import java.util.Scanner;

public class Runner {
	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		Room[][] building = new Room[5][5];
		
		//Fill the building with normal rooms
		for (int x = 0; x<building.length; x++)
		{
			for (int y = 0; y < building[x].length; y++)
			{
				building[x][y] = new Room(x,y);
			}
		}
		
		//Create a random winning room.
        int x = 0;
		int y = 0;
        while(x == 0 && y == 0)
        {
            x = (int) (Math.random() * building.length);
            y = (int) (Math.random() * building.length);
            building[x][y] = new WinningRoom(x, y);
        }
		System.out.println(x);
		System.out.println(y);
		int a = 0;
		int b = 0;
		while(a == x && b == y || a == 0 && b == 0)
		{
            a = (int) (Math.random() * building.length);
            b = (int) (Math.random() * building.length);
            building[a][b] = new LosingRoom(a, b);
        }
        System.out.println(a);
		System.out.println(b);
        int c = 0;
		int d = 0;
		while(c == a || c == x && d == b || d == y || c == 0 && d == 0)
        {
            c = (int) (Math.random() * building.length);
            d = (int) (Math.random() * building.length);
            building[c][d] = new BonusRoom(c,d,x);
        }
        System.out.println(c);
		System.out.println(d);
		int e = 0;
		int f = 0;
		while(e == a || e == x || e == c && f == y || f == b || f == d || e == 0 && f == 0)
        {
            e = (int) (Math.random() * building.length);
            f = (int) (Math.random() * building.length);
            int rndx = (int) (Math.random() * building.length);
            int rndy = (int) (Math.random() * building.length);
            building[e][f] = new TeleportationRoom(e,f,rndx,rndy);
        }

		 
		 //Setup player 1 and the input scanner
		Person player1 = new Person("FirstName", "FamilyName", 0,0);
		building[0][0].enterRoom(player1);
		Scanner in = new Scanner(System.in);
		while(gameOn)
		{
			System.out.println("Where would you like to move? (Choose N, S, E, W)");
			String move = in.nextLine();
			if(validMove(move, player1, building))
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
	


}
