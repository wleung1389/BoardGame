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
        int x = 0;
        int y = 0;
        map[0][0] = new startingPosition(0,0);
        for(int i = 0; i < 500; i++)
        {
            if(x == 0 && y == 0)
            {
                map[0][0] = new startingPosition(0,0);
                x++;
                y++;
            }
            else
            {
                double rnd = getRndInteger(1, 4);
                if (rnd == 1)
                {
                    if (y + 1 > 50)
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
                    if (x + 1 > 20)
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
        return map;
    }
    public void makeRndRoom(int x, int y, BasicRoom[][] map)
    {
        if(x >= 20)
        {
            x = 19;
        }
        if(y >= 50)
        {
            y = 49;

        }
        int rnd = getRndInteger(1,8);
        if(rnd == 1)
        {
            int rndX = getRndInteger(0, 19);
            int rndY = getRndInteger(0, 49);
            while(map[rndX][rndY] == null)
            {
                rndX = getRndInteger(0, 19);
                rndY = getRndInteger(0, 49);
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
            map[x][y] = new easyDungeon(x,y);
        }
        if(rnd == 5)
        {
            map[x][y] = new normalDungeon(x,y);
        }
        if(rnd == 6)
        {
            map[x][y] = new hardDungeon(x,y);
        }
        if(rnd == 7)
        {
            map[x][y] = new expertDungeon(x,y);
        }
        if(rnd == 8)
        {
            map[x][y] = new trainingSpot(x,y);
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
        System.out.println("O = You are here.");
    }
}
