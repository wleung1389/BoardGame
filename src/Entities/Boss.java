package Entities;

import Game.Runner;

public class Boss implements NPCs {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int mhp;
    public Boss(String n, int h, int a, int d)
    {
        name = n;
        hp = h;
        attack = a;
        defense = d;
        mhp = h;
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
        return attack - (0.5 * x.getDefense());
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
    public int getMHP()
    {
        return this.mhp;
    }
}
