package Map;

import Entities.Player;
import Entities.Boss;
import Entities.Mobs;
import Game.Runner;
import Items.Items;
import Items.Sword;
import Items.Armor;
import Items.Consumable;

import java.util.Scanner;

public class finalBossDungeon extends BasicRoom {
	public finalBossDungeon(int x, int y) {
		super(x,y);
	}

	/**
	 * Triggers the player losing sequence.
	 * @param x the Player entering
	 */
	@Override
	public void enterRoom(Player x) {
		Scanner input = new Scanner(System.in);
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		String name = "Orac The Orc";
		int bhp = Runner.getRndInteger(2000,2100);
		int battk = Runner.getRndInteger(85,100);
		int bdefense = Runner.getRndInteger(70,90);
		Boss boss = new Boss(name,bhp,battk,bdefense);
		System.out.println("You have entered the final boss's room. Kill " + boss.getName() + " and escape from the cave.");
		System.out.println("The final boss, " + boss.getName() + " has appeared.");
        System.out.println(boss.getName() + " : " + boss.getHP() + "/" + boss.getMHP());
		while(boss.getHP() > 0)
		{
			if(x.getChp() <= 0)
			{
				System.out.println("You have died. Game Over.");
				Runner.gameOff();
			}
			System.out.println("Fight(F), Inventory(I)");
			String choice = input.nextLine();
			if(choice.substring(0,1).equalsIgnoreCase("F"))
			{
				int wepAttk = x.getWepStats();
				double rnd = Runner.getRndInteger(4,6);
				double dmg = Math.floor((wepAttk * Runner.getRndInteger(4,6))/((rnd/10) * boss.getDefense()));
				boss.attacked(dmg);
				System.out.println("You attacked " + boss.getName() + " with your weapon. You dealt " + dmg + ". " + boss.getName() + " has " + boss.getHP() + "/" + boss.getMHP() + " left.");
				if (boss.getHP() > 0) {
					double bDmg = Math.floor(boss.getAttk(x));
					x.attacked(bDmg);
					System.out.println(boss.getName() + ", " + boss.getName() + ", slammed you with his club and dealt " + bDmg + ".");
					System.out.println("You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
				}
			}
			else
			{
				if(choice.substring(0,1).toLowerCase().equalsIgnoreCase("i")) {
                    x.showInventory();
                    int in = Integer.parseInt(input.nextLine());
                    if(x.itemInInventoryAtSlot(in - 1) == null)
                    {
                        System.out.println("There is nothing in that slot. Please choose another slot.");
                    }
                    else {
                        if (x.itemInInventoryAtSlot(in - 1).getType().equalsIgnoreCase("Consumable") ) {
                            x.consume(in);
                        } else {
                            if (in > 20) {
                                in = 20;
                            }
                            if (in < 1) {
                                in = 1;
                            }
                            x.equip(in);
                        }
                    }
				}
				else
				{
					System.out.println("Your input is incorrect. Try again.");
				}
			}
		}
		input.close();
		System.out.println("You beat the final boss! Congratulations! You have won the game. Play again next time!");
		Runner.gameOff();
	}
	public String toString()
	{
		return "[BOSS]";
	}
}
