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

public class easyDungeon extends BasicRoom {
    public easyDungeon(int x, int y) {
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
        int bhp = Runner.getRndInteger(100,150);
        int battk = Runner.getRndInteger(15,20);
        int bdefense = Runner.getRndInteger(13,18);
        Boss boss = new Boss(name,bhp,battk,bdefense);
        System.out.println("You entered an easy dungeon. Find " + boss.getName() + " and kill him.");
        String[] mobs = new String[] {"Goblin","Zombie","Spider"};
        int rnd1 = Runner.getRndInteger(0,2);
        int rnd2 = Runner.getRndInteger(0,2);
        String mob1 = mobs[rnd1];
        String mob2 = mobs[rnd2];
        int hp = Runner.getRndInteger(10,30);
        int attk = Runner.getRndInteger(5,10);
        int defense = Runner.getRndInteger(8,15);
        int hp2 = Runner.getRndInteger(10,30);
        int attk2 = Runner.getRndInteger(5,10);
        int defense2 = Runner.getRndInteger(8,15);
        Mobs monster1 = new Mobs(mob1,hp,attk,defense);
        Mobs monster2 = new Mobs(mob2,hp2,attk2,defense2);
        System.out.println("The first wave of monsters have come.");
        System.out.println(monster1.getName() + " : " + monster1.getHP() + "/" + monster1.getMHP() + ", " + monster2.getName() + " : " + monster2.getHP() + "/" + monster2.getMHP());
        boolean fighting = true;
        while(fighting && x.getChp() != 0)
        {
            if(monster2.status().equalsIgnoreCase("dead"))
            {
                fighting = false;
            }
            System.out.println("Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if(choice.substring(0,1).toLowerCase().equalsIgnoreCase("f"))
            {
                if(monster1.status().equals("dead"))
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(4,6);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(4,6))/((rnd/10) * monster2.getDefense()));
                    monster2.attacked(dmg);
                    System.out.println("You attacked the second monster with your weapon. You dealt " + dmg + " dmg. The second monster has " + monster2.getHP() + "/" + monster2.getMHP() + " HP left.");
                    if (monster2.getHP() > 0) {
                        double mDmg = Math.floor(monster2.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The second monster attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
                    else
                    {
                        System.out.println("You have beaten the monsters.");
                    }
                }
                else
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(4,6);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(4,6))/((rnd/10) * monster1.getDefense()));
                    monster1.attacked(dmg);
                    System.out.println("You attacked the first monster(" + monster2.getName() + ") with your weapon. You dealt " + dmg + ". The first monster has " + monster1.getHP() + "/" + monster1.getMHP() + " HP left.");
                    if (monster1.getHP() > 0) {
                        double mDmg = Math.floor(monster1.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The first monster(" + monster2.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
                    else
                    {
                        System.out.println("You killed the first monster. The second monster is still alive.");
                    }
                }
            }
            else
            {
                if(choice.substring(0,1).toLowerCase().equalsIgnoreCase("i")) {
                    x.showInventory();
                    int in = 0;
                    while(in <= 0 ) {
                        try {
                            in = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number greater than 0.");
                        }
                    }
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
        if(x.getChp() <= 0)
        {
            System.out.println("You have died. Game Over.");
            Runner.gameOff();
            System.exit(0);
        }
        System.out.println("The boss, " + boss.getName() + " has appeared.");
        System.out.println(boss.getName() + " : " + boss.getHP() + "/" + boss.getMHP());
        while(boss.getHP() > 0)
        {
            if(x.getChp() <= 0)
            {
                System.out.println("You have died. Game Over.");
                Runner.gameOff();
                System.exit(0);
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
                    System.out.println("The boss, " + boss.getName() + ", slammed you with his club and dealt " + bDmg + ".");
                    System.out.println("You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                }
            }
            else
            {
                if(choice.substring(0,1).toLowerCase().equalsIgnoreCase("i")) {
                    x.showInventory();
                    int in = 0;
                    while(in <= 0 ) {
                        try {
                            in = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number greater than 0.");
                        }
                    }
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
        System.out.println("You beat the dungeon! Congratulations!");
        Sword item1 = new Sword("Common Sword","Common", "Sword",19);
        Sword item2 = new Sword("Rusty Sword","Common", "Sword",15);
        Armor item3 = new Armor("Common Armor","Common", "Armor", 13);
        Armor item4 = new Armor("Rusty Armor", "Common", "Armor",10);
        Consumable item5 = new Consumable("Apple","Common", "Consumable",10, 10);
        Consumable item6 = new Consumable("Small HP Potion","Common", "Consumable" ,20, 10);
        Items[] items = new Items[6];
        items[0] = item1;
        items[1] = item2;
        items[2] = item3;
        items[3] = item4;
        items[4] = item5;
        items[5] = item6;
        Items gItem = items[Runner.getRndInteger(0,5)];
        gItem.setQuantity(10);
        x.addToInventory(gItem);
        System.out.println("You got a[n] " + gItem.toString() + ".");
        Items aItem = items[Runner.getRndInteger(0,5)];
        aItem.setQuantity(10);
        x.addToInventory(aItem);
        System.out.println("You got a[n] " + aItem.toString() + ".");
        Items bItem = items[Runner.getRndInteger(0,5)];
        bItem.setQuantity(10);
        x.addToInventory(bItem);
        System.out.println("You got a[n] " + bItem.toString() + ".");
    }
    public String toString()
    {
        return "[EASY]";
    }
}
