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
		BasicRoom[][] map = new BasicRoom[15][30];
		Board board = new Board(map);
		map = board.newBoard();
		 //Setup player 1 and the input scanner
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();
		Player player1 = new Player(name, 4,5);
		player1.createStarterSet();
		map[4][5].enterRoom(player1);
        System.out.println("You have entered the cave of Carl. There are many dungeons that contain treasures but many monsters guard those treasures. Slay those monsters and loot the dungeons!");
        System.out.println("(Recommended : Go into easy dungeons and training spots first before you attempt to try harder dungeons or else you will die.)");

		while(gameOn)
		{
            in = new Scanner(System.in);
            System.out.println("Where would you like to move? (Choose W, A, S, D) (Type M or map to open the map. Type I or inventory to open the inventory. Type E or equips to open the equip tab. Type R or stats to open the stats tab)");
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
					int input = 0;
					while(input <= 0 ) {
						try {
							input = Integer.parseInt(in.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Please input a number greater than 0.");
						}
					}
					if(player1.itemInInventoryAtSlot(input - 1) == null)
                    {
                        System.out.println("There is nothing in that slot. Please choose another slot.");
                    }
                    else {
                        if (player1.itemInInventoryAtSlot(input - 1).getType().equalsIgnoreCase("Consumable") ) {
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
                    if(String.valueOf(move).toLowerCase().equalsIgnoreCase("equips") || String.valueOf(move).toLowerCase().equalsIgnoreCase("e"))
                    {
                        player1.showEquips();
                    }
                    else {
                    	if(String.valueOf(move).toLowerCase().equalsIgnoreCase("stats") || String.valueOf(move).toLowerCase().equalsIgnoreCase("r"))
                        {
                            player1.showStats();
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
			case "w":
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
			case "d":
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

			case "a":
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
