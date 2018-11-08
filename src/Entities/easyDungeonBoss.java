package Entities;

import Game.Runner;

public class easyDungeonBoss implements NPCs {
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
    public int getHP()
    {
        return hp;
    }
    public int getAttk()
    {
        return attack* Runner.getRndInteger(3,8);
    }
    public int getDefense()
    {
        return defense;
    }
}
