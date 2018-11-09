package Entities;

import Game.Runner;

public class Carl implements NPCs {
    private static String name;
    private static int hp;
    private static int attack;
    private static int defense;
    public Carl(String n, int h, int a, int d)
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
    public int getHP()
    {
        if(hp < 0)
        {
            hp = 0;
        }
        return hp;
    }
    public double getAttk(Player x)
    {
        double rnd = Runner.getRndInteger(1,5);
        return attack* Runner.getRndInteger(3,8)*((rnd/10) * x.getChp());
    }
    public int getDefense()
    {
        return defense;
    }
    public void attacked(double dmg)
    {
        hp -= dmg;
        if(hp < 0)
        {
            hp = 0;
        }
    }
}
