package Game;

import Entities.Player;
import Map.*;
import Items.Items;
import Items.Consumable;
import java.util.Scanner;

public class Runner {
	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		BasicRoom[][] map = new BasicRoom[10][20];
		Board board = new Board(map);
		map = board.newBoard();
		 //Setup player 1 and the input scanner
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();
		Player player1 = new Player(name, 0,0);
		map[0][0].enterRoom(player1);
		player1.createStarterSet();

		while(gameOn)
		{
            System.out.println("Where would you like to move? (Choose N, S, E, W) (Type M or map to open the map. Type I or inventory to open the inventory. Type Eq or equips to open the equip tab.)");
            String move = in.nextLine();
		    if(String.valueOf(move).toLowerCase().equalsIgnoreCase("map") || String.valueOf(move).toLowerCase().equalsIgnoreCase("m"))
		    {
		        board.printBoard(player1);
            }
            else
            {
				if(String.valueOf(move).toLowerCase().equalsIgnoreCase("inventory") || String.valueOf(move).toLowerCase().equalsIgnoreCase("i"))
				{
					player1.showInventory();
					int input = Integer.parseInt(in.nextLine());
					if(player1.itemInInventoryAtSlot(input - 1) == null)
                    {
                        System.out.println("There is nothing in that slot. Please choose another slot.");
                    }
                    else {
                        if (player1.itemInInventoryAtSlot(input - 1).getType().equals("Consumable")) {
                            player1.consume(input);
                        } else {
                            if (input > 20) {
                                input = 20;
                            }
                            if (input < 1) {
                                input = 1;
                            }
                            player1.equip(input);
                        }
                    }
				}
                else {
                    if(String.valueOf(move).toLowerCase().equalsIgnoreCase("equips") || String.valueOf(move).toLowerCase().equalsIgnoreCase("eq"))
                    {
                        player1.showEquips();
                    }
                    else {
                        if (validMove(move, player1, map)) {
                            System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

                        } else {
                            System.out.println("Please choose a valid move.");
                        }
                    }
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
				if (p.getxLoc() > 0 && map[p.getxLoc() - 1][p.getyLoc()] != null)
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
				if (p.getyLoc()< map[p.getyLoc()].length -1 && map[p.getxLoc()][p.getyLoc() + 1] != null)
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
				if (p.getxLoc() < map.length - 1 && map[p.getxLoc() + 1][p.getyLoc()] != null)
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
				if (p.getyLoc() > 0 && map[p.getxLoc()][p.getyLoc() - 1] != null )
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
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
