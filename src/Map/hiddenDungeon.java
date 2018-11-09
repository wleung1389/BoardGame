package Map;

import Entities.Player;
import Game.Runner;
import Items.Armor;
import Items.Consumable;
import Items.Items;
import Items.Sword;

public class hiddenDungeon extends BasicRoom {
    private int c;
    public hiddenDungeon(int x, int y, int c)
    {
        super(x,y);
        this.c = c;
    }

    /**
     * Triggers the bonus help system.
     * @param x the Player entering
     */
    @Override
    public void enterRoom(Player x) {

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("You entered the bonus room.");
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

    }
    public String toString()
    {
        return "    ";
    }
}
