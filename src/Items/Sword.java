package Items;

public class Sword extends Items {
    private String name;
    private String rarity;
    private String type;
    private int stats;
    public Sword(String name, String rarity, String type, int stats)
    {
        super(name,rarity, type, stats);
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
    public void setQuantity(int q) {
    }

    @Override
    public int getQuantity() {
        return 1;
    }
}
