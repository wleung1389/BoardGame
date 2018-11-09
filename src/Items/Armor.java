package Items;

public class Armor extends Items {
    private String name;
    private String rarity;
    private String type;
    private int stats;
    public Armor(String name, String rarity, String type, int stats)
    {
        super(name ,rarity, type, stats);
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.stats = stats;
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
        return this.name;
    }
    public String getType() {
        return this.type;
    }
}