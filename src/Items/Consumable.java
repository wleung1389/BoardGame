package Items;

import Entities.Player;

public class Consumable extends Items {
    private String name;
    private String rarity;
    private String type;
    private int stats;
    private int quantity;
    public Consumable(String name, String rarity, String type, int stats, int quantity)
    {
        super(name,rarity, type, stats);
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.stats = stats;
        this.quantity = quantity;
    }
    @Override
    public int getStats()
    {
        return 1;
    }
    public void setStats(int s)
    {
        this.stats = s;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public String toString()
    {
        return this.name + " x" +this.quantity;
    }
    public String getType() {
        return this.type;
    }
    public String getName()
    {
        return this.name;
    }
}