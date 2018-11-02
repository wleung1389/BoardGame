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

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        Scanner input = new Scanner(System.in);
        easyDungeonBoss boss = new easyDungeonBoss();
        System.out.println("You entered an easy dungeon. Find " + easyDungeonBoss.getName() + " and kill them.");
        normalDungeonMobs.makeMobs();
        System.out.println("The first wave of monsters have come. Fight(F), Inventory(I)");
        String choice = input.nextLine();
        if(choice.substring(0,1).equalsIgnoreCase("F"))
        {
            int wepAttk = x.getWepStats();
            int dmg = wepAttk * 5;
            easyDungeonMobs.attacked(dmg);
            System.out.println("You attacked the monster with your weapon. You dealt " + dmg + ". The monster has " + easyDungeonMobs.monsterHP());
        }
    }
}
