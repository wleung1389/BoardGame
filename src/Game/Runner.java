package Game;

import Entities.Player;
import Map.*;

import java.util.Scanner;

public class Runner {
	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		BasicRoom[][] map = new BasicRoom[20][50];
		Board board = new Board(map);
		map = board.newBoard();
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
		        board.printBoard(player1);
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
	public static boolean validMove(String move, Player p, BasicRoom[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "n":
				if (map[p.getxLoc() - 1][p.getyLoc()] != null && p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "e":
				if (map[p.getxLoc()][p.getyLoc() + 1] != null && p.getyLoc()< map[p.getyLoc()].length -1)
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
				if (map[p.getxLoc()][p.getyLoc() + 1] != null && p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "w":
				if (map[p.getxLoc()][p.getyLoc()] != null && p.getyLoc() > 0)
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
