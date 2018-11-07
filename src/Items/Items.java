package Items;

public abstract class Items {
    private String name;
    private String rarity;
    public Items(String name, String rarity)
    {
        this.name = name;
        this.rarity = rarity;
    }
    public abstract int getStats();
    public abstract int setStats();
}
