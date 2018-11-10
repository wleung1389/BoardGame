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
        return this.stats;
    }
    public void setStats(int s)
    {
        this.stats = s;
    }
    public String toString()
    {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getName()
    {
        return this.name;
    }

    @Override
    public int getQuantity() {
        return 1;
    }

    @Override
    public void setQuantity(int q) {

    }
}