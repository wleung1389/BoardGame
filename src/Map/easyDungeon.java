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
        String[] mobs = new String[] {"Goblins","Zombies","Spiders"};
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
        System.out.println("The first wave of monsters have come. Fight(F), Inventory(I)");
        while(monster1.getHP() != 0 && monster2.getHP() != 0 && x.getChp() > 0)
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
                if(monster1.status().equals("dead"))
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(1,5);
                    double dmg = (wepAttk * Runner.getRndInteger(6,10))*((rnd/10) * monster1.getDefense());
                    monster2.attacked(dmg);
                    System.out.println("You attacked the second monster with your weapon. You dealt " + dmg + " dmg. The monster has " + monster2.getHP() + " HP left.");
                    double mDmg = monster2.getAttk(x);
                    x.attacked(mDmg);
                    System.out.println("The monster attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + " HP left.");
                }
                else
                {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(1,5);
                    double dmg = (wepAttk * Runner.getRndInteger(6,10))*((rnd/10) * monster1.getDefense());
                    monster1.attacked(dmg);
                    System.out.println("You attacked the first monster(" + monster2.getName() + ") with your weapon. You dealt " + dmg + ". The monster has " + monster1.getHP() + " HP left.");
                    double mDmg = monster1.getAttk(x);
                    x.attacked(mDmg);
                    System.out.println("The monster(" + monster2.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + " HP left.");
                }
            }
            else
            {
                x.showInventory();
            }
        }
        System.out.println("The boss, " + boss.getName() + " has appeared. Fight(F), Inventory(I)");
        while(boss.getHP() > 0)
        {
            System.out.println("Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if(choice.substring(0,1).equalsIgnoreCase("F"))
            {
                int wepAttk = x.getWepStats();
                double rnd = Runner.getRndInteger(1,5);
                double dmg = (wepAttk * Runner.getRndInteger(6,10))*((rnd/10) * monster1.getDefense());
                boss.attacked(dmg);
                System.out.println("You attacked " + boss.getName() + " with your weapon. You dealt " + dmg + ". The boss has " + boss.getHP() + " left.");
                double bDmg = boss.getAttk(x);
                x.attacked(bDmg);
            }
            else
            {
                x.showInventory();
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
        x.addToInventory(gItem);
        System.out.println("You got a[n] " + gItem.toString() + ".");
        input.close();
    }
    public String toString()
    {
        return "[Easy]";
    }
}
