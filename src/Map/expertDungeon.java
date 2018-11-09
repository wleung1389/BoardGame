package Map;

import Entities.Boss;
import Entities.Player;
import Entities.Mobs;
import Game.Runner;
import Items.Armor;
import Items.Consumable;
import Items.Items;
import Items.Sword;

import java.util.Scanner;

public class expertDungeon extends BasicRoom {
    public expertDungeon(int x, int y) {
        super(x, y);
    }

    /**
     * Triggers the player losing sequence.
     *
     * @param x the Player entering
     */
    @Override
    public void enterRoom(Player x) {
        Scanner input = new Scanner(System.in);
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        String name = "Boj The Destroyer Of Worlds";
        int bhp = Runner.getRndInteger(700, 1000);
        int battk = Runner.getRndInteger(50, 60);
        int bdefense = Runner.getRndInteger(40, 55);
        Boss boss = new Boss(name, bhp, battk, bdefense);
        System.out.println("You entered an expert dungeon. Find " + boss.getName() + " and kill him.");
        String[] mobs = new String[]{"Dragon", "Unicorn", "Wyvern"};
        int rnd1 = Runner.getRndInteger(0, 2);
        int rnd2 = Runner.getRndInteger(0, 2);
        String mob1 = mobs[rnd1];
        String mob2 = mobs[rnd2];
        int hp = Runner.getRndInteger(80, 100);
        int attk = Runner.getRndInteger(40, 50);
        int defense = Runner.getRndInteger(30, 40);
        int hp2 = Runner.getRndInteger(80, 100);
        int attk2 = Runner.getRndInteger(35, 50);
        int defense2 = Runner.getRndInteger(30, 40);
        Mobs monster1 = new Mobs(mob1, hp, attk, defense);
        Mobs monster2 = new Mobs(mob2, hp2, attk2, defense2);
        System.out.println("The first wave of monsters have come. Fight(F), Inventory(I)");
        while (monster1.getHP() != 0 && monster2.getHP() != 0 && x.getChp() > 0) {
            if (x.getChp() <= 0) {
                System.out.println("You have died. Game Over.");
                Runner.gameOff();
            }
            System.out.println("Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if (choice.substring(0, 1).equalsIgnoreCase("F")) {
                if (monster1.status().equals("dead")) {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(1, 5);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(2, 4)) * ((rnd / 10) * monster1.getDefense()));
                    monster2.attacked(dmg);
                    System.out.println("You attacked the first monster (" + monster2.getName() + ") with your weapon. You dealt " + dmg + " dmg. The monster has " + monster2.getHP() + "/" + monster2.getMHP() + " HP left.");
                    if (monster2.getHP() > 0) {
                        double mDmg = Math.floor(monster2.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The monster(" + monster2.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
                } else {
                    int wepAttk = x.getWepStats();
                    double rnd = Runner.getRndInteger(1, 5);
                    double dmg = Math.floor((wepAttk * Runner.getRndInteger(2, 4)) * ((rnd / 10) * monster1.getDefense()));
                    monster1.attacked(dmg);
                    System.out.println("You attacked the first monster(" + monster1.getName() + ") with your weapon. You dealt " + dmg + ". The monster has " + monster1.getHP() + "/" + monster1.getMHP() + " HP left.");
                    if (monster1.getHP() > 0) {
                        double mDmg = Math.floor(monster1.getAttk(x));
                        x.attacked(mDmg);
                        System.out.println("The monster(" + monster1.getName() + ") attacked you. You lost " + mDmg + " HP. You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                    }
                }
            } else {
                x.showInventory();
            }
        }
        System.out.println("The boss, " + boss.getName() + " has appeared. Fight(F), Inventory(I)");
        while (boss.getHP() > 0) {
            if(x.getChp() <= 0)
            {
                System.out.println("You have died. Game Over.");
                Runner.gameOff();
            }
            System.out.println("Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if (choice.substring(0, 1).equalsIgnoreCase("F")) {
                int wepAttk = x.getWepStats();
                double rnd = Runner.getRndInteger(1, 5);
                double dmg = Math.floor((wepAttk * Runner.getRndInteger(2, 4)) * ((rnd / 10) * monster1.getDefense()));
                boss.attacked(dmg);
                System.out.println("You attacked " + boss.getName() + " with your weapon. You dealt " + dmg + ". The boss has " + boss.getHP() + "/" + boss.getMHP() + " left.");
                if (boss.getHP() > 0) {
                    double bDmg = Math.floor(boss.getAttk(x));
                    x.attacked(bDmg);
                    System.out.println("You have " + x.getChp() + "/" + x.getMhp() + " HP left.");
                }
            }
            else
            {
                x.showInventory();
            }
            System.out.println("You beat the dungeon! Congratulations!");
            Sword item1 = new Sword("Boj's Sword", "Legendary", "Sword", 120);
            Sword item2 = new Sword("Arch's Sword", "Legendary", "Sword", 130);
            Armor item3 = new Armor("Boj's Armor", "Legendary", "Armor", 110);
            Armor item4 = new Armor("Arch's Armor", "Legendary", "Armor", 100);
            Consumable item5 = new Consumable("Full Heal", "Epic", "Consumable", x.getMhp(), 10);
            Consumable item6 = new Consumable("Large HP Potion", "Rare", "Consumable", 20, 10);
            Items[] items = new Items[6];
            items[0] = item1;
            items[1] = item2;
            items[2] = item3;
            items[3] = item4;
            items[4] = item5;
            items[5] = item6;
            Items gItem = items[Runner.getRndInteger(0, 5)];
            x.addToInventory(gItem);
            System.out.println("You got a[n] " + gItem.toString() + ".");
            input.close();
        }
    }
        public String toString()
        {
            return "[Expt}";
        }
}
