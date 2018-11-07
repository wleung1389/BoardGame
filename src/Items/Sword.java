package Items;

public class Sword extends Items {
    private String name;
    private String rarity;
    public Sword(String name, String rarity)
    {
        super(name,rarity);
        this.name = name;
        this.rarity = rarity;
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
}
