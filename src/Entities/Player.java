package Entities;

import Game.Runner;
import Items.Items;
import Items.Sword;
import Items.Consumable;
import Items.Armor;

/**
 * Player represents the player as they move through the game.
 */
public class Player {
	String name;
	int xLoc, yLoc;
    int mhp = Runner.getRndInteger(150,200);
	int chp = mhp;



	Items[] inventory = new Items[20];
	Items[] equipped = new Items[2];

	public void createStarterSet()
	{
		Sword sword = new Sword("Beginner's Sword", "Common", "Sword", 10);
		Armor armor = new Armor("Beginner's Armor", "Common", "Armor",  8);
		Consumable apple = new Consumable("Apple", "Common", "Consumable",10, 20);
		equipped[0] = sword;
		equipped[1] = armor;
		inventory[0] = apple;
	}
	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Player(String n, int xLoc, int yLoc)
	{
		this.name = n;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	public void attacked(double dmg)
	{
		chp -= dmg;
		if(chp < 0)
		{
			chp = 0;
		}
	}
	public int getWepStats()
	{
		return equipped[0].getStats();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChp() {
		return chp;
	}

	public void setChp(int chp) {
		this.chp = chp;
	}
	public int getMhp() {
		return mhp;
	}

	public void setMhp(int mhp) {
		mhp = mhp;
	}
	public void showInventory()
	{
	    int newRow = 0;
		for(int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] == null) {
                System.out.print("[Empty]       ");
                System.out.print("\t");
            }
            else
            {
                if(inventory[i] != null) {
                    System.out.print(inventory[i] + "\t");
                }
            }
            newRow++;
            if(newRow == 5) {
                newRow = 0;
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("To equip or use an item type in the position of the equip. (Starts at 1).");
	}
	public void addToInventory(Items item)
    {
        int i = 0;
        while(inventory[i] != null && i < inventory.length)
        {
            i++;
        }
        inventory[i] = item;
    }
    public void equip(int index)
    {
        index -= 1;
        System.out.println("You equipped " + inventory[index].toString() + "." );
        String type = inventory[index].getType();
        if(type.equals("Sword"))
        {
            Items temp = inventory[index];
            inventory[index] = equipped[0];
            equipped[0] = temp;
        }
        if(type.equals("Armor"))
        {
            Items temp = inventory[index];
            inventory[index] = equipped[1];
            equipped[1] = temp;
        }
        showEquips();
    }
    public void showEquips()
    {
        for(int i = 0; i < equipped.length; i++)
        {
            if(equipped[i] == null)
            {
                System.out.print("[Empty]");
                System.out.print("\t");
            }
            else
            {
                if(equipped[i] != null) {
                    System.out.print(equipped[i].toString());
                    System.out.print("\t");
                }
            }
        }
        System.out.println();
    }
    public void gainHP(int hp)
    {
        this.mhp += hp;
    }
    public void consume(int index)
    {
        index -= 1;
        if(inventory[index].getType().equals("Consumable")) {
            int hp = this.getChp() + inventory[index].getStats();
            if (hp > this.getMhp()) {
                hp = this.getMhp();
            }
            this.setChp(hp);
            System.out.println("You ate/used a(n) " + inventory[index].getName() + " and gained " + inventory[index].getStats() + "HP. Your current hp is now " + this.getChp() + "/" + this.getMhp() + ".");
            inventory[index].setQuantity(inventory[index].getQuantity() - 1);
        }
    }
    public Items itemInInventoryAtSlot(int index)
    {
        return inventory[index];
    }
    public int getDefense()
    {
        return equipped[1].getStats();
    }
    public void showStats()
    {
        System.out.println("HP : " + this.chp + "/" + this.mhp);
        System.out.println("Attack : " + equipped[0].getStats());
        System.out.println("Defense : " + equipped[1].getStats());
    }
}
