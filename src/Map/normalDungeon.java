package Map;

import Entities.Player;
import Entities.Boss;
import Entities.Mobs;
import Game.Runner;
import Items.Armor;
import Items.Consumable;
import Items.Items;
import Items.Sword;

import java.util.Scanner;

public class normalDungeon extends BasicRoom {
    public normalDungeon(int x, int y) {
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
        String name = "Drake The Wyvern King";
        int bhp = Runner.getRndInteger(300,450);
        int battk = Runner.getRndInteger(15,20);
        int bdefense = Runner.getRndInteger(13,18);
        Boss boss = new Boss(name,bhp,battk,bdefense);
        System.out.println("You entered a normal dungeon. Find " + boss.getName() + " and kill him.");
        String[] mobs = new String[] {"Wyvern","Yeti","Bear"};
        int rnd1 = Runner.getRndInteger(0,2);
        int rnd2 = Runner.getRndInteger(0,2);
        String mob1 = mobs[rnd1];
        String mob2 = mobs[rnd2];
        int hp = Runner.getRndInteger(30,50);
        int attk = Runner.getRndInteger(20,30);
        int defense = Runner.getRndInteger(13,20);
        int hp2 = Runner.getRndInteger(30,50);
        int attk2 = Runner.getRndInteger(20,30);
        int defense2 = Runner.getRndInteger(13,20);
        Mobs monster1 = new Mobs(mob1,hp,attk,defense);
        Mobs monster2 = new Mobs(mob2,hp2,attk2,defense2);
        System.out.println("The first wave of monsters have come.");
        System.out.println(monster1.getName() + " : " + monster1.getHP() + "/" + monster1.getMHP() + ", " + monster2.getName() + " : " + monster2.getHP() + "/" + monster2.getMHP());
        while(monster1.getHP() != 0 && monster2.getHP() != 0 && x.getChp() > 0)
        {
            System.out.println("Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if(choice.substring(0,1).equalsIgnoreCase("F"))
            {
                if(monster1.status().equals("dead"))
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(4,6);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(4,6))/((rnd/10) * monster2.getDefense()));
                    monster2.attacked(dmg);
                    System.out.println("You attacked the second monster(" + monster2.getName() + ") with your weapon. You dealt " + dmg + " dmg. The second monster has " + monster2.getHP() + "/" + monster2.getMHP() + " HP left.");
                    if(monster2.getHP() > 0) {
                        double mDmg = Math.floor(monster2.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The second monster(" + monster2.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
                }
                else
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(4,6);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(4,6))/((rnd/10) * monster1.getDefense()));
                    monster1.attacked(dmg);
                    System.out.println("You attacked the first monster(" + monster1.getName() + ") with your weapon. You dealt " + dmg + ". The first monster has " + monster1.getHP() + "/" + monster1.getMHP() + " HP left.");
                    if(monster1.getHP() > 0) {
                        double mDmg = Math.floor(monster1.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The first monster(" + monster1.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
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
                if (monster2.getHP() > 0) {
                    double bDmg = Math.floor(boss.getAttk(x));
                    x.attacked(bDmg);
                    System.out.println(boss.getName() + " has hit you for " + bDmg + ".");
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
        System.out.println("You beat the dungeon! Congratulations!");
        Sword item1 = new Sword("Drake's Longsword","Rare", "Sword",42);
        Sword item2 = new Sword("Drake's Broadsword","Rare", "Sword",45);
        Armor item3 = new Armor("Drake's Suit","Rare", "Armor", 36);
        Armor item4 = new Armor("Drake's Armor", "Rare", "Armor",34);
        Consumable item5 = new Consumable("Medium HP Potion","Rare", "Consumable",40, 10);
        Consumable item6 = new Consumable("Small HP Potion","Common", "Consumable" ,20, 20);
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
        return "[NORM]";
    }
}
