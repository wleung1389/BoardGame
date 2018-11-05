package Entities;

import Game.Runner;

public class easyDungeonMobs {
    private String name;
    private int hp;
    private int attack;
    private int defense;



    public easyDungeonMobs(String n, int h, int a, int d)
    {
        name = n;
        attack = a;
        defense = d;
        hp = h;
    }
    public double getMonsterHP()
    {
        return this.hp;
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
        double rnd = Runner.getRndInteger(1,5);
        hp -= dmg/(rnd/10*defense);
        if(hp < 0)
        {
            hp = 0;
        }
    }
    public int attk()
    {
        attack *= Runner.getRndInteger(3,8);
        return attack;
    }
}
