package Map;

import Entities.Player;
import Entities.easyDungeonBoss;
import Entities.easyDungeonMobs;
import Entities.normalDungeonMobs;
import Game.Runner;

import java.util.Scanner;

public class easyDungeon extends safeSpot {
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
        easyDungeonBoss boss = new easyDungeonBoss(name,bhp,battk,bdefense);
        System.out.println("You entered an easy dungeon. Find " + boss.getName() + " and kill them.");
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
        easyDungeonMobs monster1 = new easyDungeonMobs(mob1,hp,attk,defense);
        easyDungeonMobs monster2 = new easyDungeonMobs(mob2,hp2,attk2,defense2);
        while(monster1.getMonsterHP() != 0 && monster2.getMonsterHP() != 0)
        {
            System.out.println("The first wave of monsters have come. Fight(F), Inventory(I)");
            String choice = input.nextLine();
            if(choice.substring(0,1).equalsIgnoreCase("F"))
            {
                if(monster1.status() == "dead")
                {
                    int wepAttk = x.getWepStats();
                    int dmg = wepAttk * 5;
                    monster2.attacked(dmg);
                    System.out.println("You attacked the monster with your weapon. You dealt " + dmg + " dmg. The monster has " + monster2.getMonsterHP() + " HP");
                    int mDmg = monster2.attk();
                    x.attacked(mDmg);
                }
                else
                {
                    double wepAttk = x.getWepStats();
                    double dmg = wepAttk * 5;
                    monster2.attacked(dmg);
                    System.out.println("You attacked the monster with your weapon. You dealt " + dmg + ". The monster has " + monster1.getMonsterHP());
                }
            }
        }
        while(boss.getBossHP() != 0)
        {

        }
        input.close();
    }
    public String toString()
    {
        return "[Easy]";
    }
}
