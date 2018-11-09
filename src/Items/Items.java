package Items;

public abstract class Items {
    private String name;
    private String rarity;
    private String type;
    private int stats;
    public Items(String name, String rarity, String type, int stats)
    {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.stats = stats;
    }
    public abstract int getStats();
    public abstract int setStats();
    public abstract String toString();
    public abstract String getType();
}
