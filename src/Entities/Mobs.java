package Entities;

import Game.Runner;

public class Mobs implements NPCs{
    private String name;
    private int hp;
    private int attack;
    private int defense;



    public Mobs(String n, int h, int a, int d)
    {
        name = n;
        attack = a;
        defense = d;
        hp = h;
    }
    public String status()
    {
        if(hp <= 0)
        {
            return "dead";
        }
        else
        {
            return "alive";
        }
    }
    public void attacked(double dmg)
    {
        hp -= dmg;
        if(hp < 0)
        {
            hp = 0;
        }
    }
    public String getName()
    {
        return name;
    }
    public int getHP()
    {
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

}
