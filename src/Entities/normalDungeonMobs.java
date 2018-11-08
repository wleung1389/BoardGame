package Entities;

import Game.Runner;

public class normalDungeonMobs implements NPCs{
    private String name;
    private int hp;
    private int attack;
    private int defense;
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
