package Entities;

public class easyDungeonBoss {
    private static String name;
    private static int hp;
    private static int attack;
    private static int defense;
    public easyDungeonBoss(String n, int h, int a, int d)
    {
        name = n;
        hp = h;
        attack = a;
        defense = d;
    }
    public String getName()
    {
        return name;
    }
    public int getBossHP()
    {
        return hp;
    }

}
