package Game;

import Entities.Player;
import Map.*;

import static Game.Runner.getRndInteger;

public class Board {
    private BasicRoom[][] map;
    public Board(BasicRoom[][] map)
    {
        this.map = map;
    }
    public Board(int rows, int columns)
    {
        BasicRoom[][] map = new BasicRoom[columns][rows];
        this.map = map;
    }
    public BasicRoom[][] newBoard()
    {
        for (int x = 0; x<map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                map[x][y] = null;
            }
        }
        int x = 4;
        int y = 5;
        map[4][5] = new startingPosition(4,5);
        for(int i = 0; i < 100; i++)
        {
            if(3 < x && x < 5 && 4 < y && y< 6)
            {
                map[4][5] = new startingPosition(4,5);
                x-=2;
                y-=2;
                BasicRoom e = new BasicRoom(3,4);
                map[3][4] = e;
                easyDungeon ez = new easyDungeon(3,5);
                map[3][5] = ez;
                e = new BasicRoom(3,6);
                map[3][6] = e;
                trainingSpot p = new trainingSpot(4,6);
                map[4][6] = p;
                e = new BasicRoom(5,6);
                map[5][6] = e;
                normalDungeon n = new normalDungeon(5,5);
                map[5][5] = n;
                e = new BasicRoom(5,4);
                map[5][4] = e;
                hardDungeon t = new hardDungeon(4,4);
                map[4][4] = t;
            }
            else
            {
                double rnd = getRndInteger(1, 4);
                if (rnd == 1)
                {
                    if (y + 1 > 100)
                    {
                        y--;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        y++;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 2)
                {
                    if (x + 1 > 15)
                    {
                        x--;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        x++;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 3)
                {
                    if (y - 1 < 0)
                    {
                        y++;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        y--;
                        makeRndRoom(x,y,map);
                    }
                }
                if (rnd == 4)
                {
                    if (x - 1 < 0)
                    {
                        x++;
                        makeRndRoom(x,y,map);
                    }
                    else
                    {
                        x--;
                        makeRndRoom(x,y,map);
                    }
                }
            }
        }
        if(x < 14) {
            x++;
        }
        else {
            if (y < 29) {
                y++;
            }
        }
        finalBossDungeon b = new finalBossDungeon(4,7);
        map[4][7] = b;
        return map;
    }
    public void makeRndRoom(int x, int y, BasicRoom[][] map)
    {
        if(x >= 15)
        {
            x = 14;
        }
        if(y >= 30)
        {
            y = 29;

        }
        int rnd = getRndInteger(1,8);
        if(rnd == 1)
        {
            int rndX = getRndInteger(0, 14);
            int rndY = getRndInteger(0, 29);
            while(map[rndX][rndY] == null)
            {
                rndX = getRndInteger(0, 14);
                rndY = getRndInteger(0, 29);
            }
            map[x][y] = new teleportationSpot(x, y, rndX, rndY);
        }
        if(rnd == 2)
        {
            int c = getRndInteger(1,15);
            map[x][y] = new hiddenDungeon(x,y,c);
        }
        if(rnd == 3)
        {
            map[x][y] = new BasicRoom(x,y);
        }
        if(rnd == 4)
        {
        map[x][y] = new trainingSpot(x,y);
        }
        if(rnd >= 5)
        {
            int rnd2 = getRndInteger(1,20);
            if(rnd2 >= 0 && rnd2 <= 7) {
                map[x][y] = new easyDungeon(x, y);
            }
            if(rnd2 >= 8 && rnd2 <= 13) {
                map[x][y] = new normalDungeon(x, y);
            }
            if(rnd2 >= 14 && rnd2 <= 17) {
                map[x][y] = new hardDungeon(x, y);
            }
            if(rnd2 >= 18 && rnd2 <= 20) {
                map[x][y] = new expertDungeon(x, y);
            }
        }

    }
    public void printBoard(Player p1)
    {
        for(int i = 0; i < map.length; i++)
        {
            for (int c = 0; c < map[i].length; c++)
            {
                if(map[i][c] == null)
                {
                    System.out.print("     ");
                    System.out.print("\t");
                }
                else
                {
                    if(i == p1.getxLoc() && c == p1.getyLoc()) {
                        System.out.print("  YOU ");
                        System.out.print("\t");
                    }
                    else
                    {
                        System.out.print(map[i][c].toString());
                        System.out.print("\t");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("YOU = You are here, {TRAIN} = Training Spot, {EASY} = Easy Dungeon, {NORM} = Normal Dungeon, {HARD} = Hard Dungeon, {EXPT} = Expert Dungeon, {BOSS} = Final Boss");
        System.out.println("{ENTR} = Starting Position, {SAFE} = Safe Spots, {TELE} = Teleportation Spots(Randomly teleports you to a set spot in the dungeon)(Different tele points teleport you into different places)" + "\n" + "Hidden Dungeons are hidden in the map somewhere. Try to find them all!");



    }
}
