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
        this.stats = stats;
        this.quantity = quantity;
    }
    @Override
    public int getStats()
    {
        return 1;
    }
    public int setStats()
    {
        return 1;
    }
    public String toString()
    {
        return this.name + " x" +this.quantity;
    }
    public String getType() {
        return this.type;
    }
}