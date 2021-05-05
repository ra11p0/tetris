package com.ra11p0;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Game extends JPanel{
    private boolean isRunning = false;
    public final Timer timer = new Timer(500, new TimerActions(this));
    private final char[][][] gameTable = new char[10][20][3];
    private int r = 0, shape, score = 0;
    public int tickTime = 500;

    public Game(int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        JFrame window = new JFrame();
        window.addKeyListener(new KeyActions(this));
        window.setFocusable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(this);
        window.pack();
        window.setVisible(true);
        startGame();
    }

    private void startGame()
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 20; y++)
            {
                gameTable[x][y][0] = 'f';
                gameTable[x][y][0] = 'g';
                gameTable[x][y][0] = 'f';
            }
        }
        isRunning = true;
        timer.start();
        newShape();
    }

    private void newShape()
    {
        timer.setDelay(tickTime);
        shape = new Random().nextInt(5);
        r = 0;
        switch (shape) {
            case 0 -> {
                gameTable[5][19][0] = 't';
                gameTable[5][19][1] = 'y';
                gameTable[5][19][2] = 't';
                gameTable[6][19][0] = 't';
                gameTable[6][19][1] = 'y';
                gameTable[6][19][2] = 't';
                gameTable[5][18][0] = 't';
                gameTable[5][18][1] = 'y';
                gameTable[5][18][2] = 't';
                gameTable[6][18][0] = 't';
                gameTable[6][18][1] = 'y';
                gameTable[6][18][2] = 't';
            }
            case 1 -> {
                gameTable[5][19][0] = 't';
                gameTable[5][19][1] = 'b';
                gameTable[5][19][2] = 't';
                gameTable[5][18][0] = 't';
                gameTable[5][18][1] = 'b';
                gameTable[5][18][2] = 't';
                gameTable[5][17][0] = 't';
                gameTable[5][17][1] = 'b';
                gameTable[5][17][2] = 't';
                gameTable[6][17][0] = 't';
                gameTable[6][17][1] = 'b';
                gameTable[6][17][2] = 't';
            }
            case 2 -> {
                gameTable[4][19][0] = 't';
                gameTable[4][19][1] = 'm';
                gameTable[4][19][2] = 't';
                gameTable[5][19][0] = 't';
                gameTable[5][19][1] = 'm';
                gameTable[5][19][2] = 't';
                gameTable[6][19][0] = 't';
                gameTable[6][19][1] = 'm';
                gameTable[6][19][2] = 't';
                gameTable[5][18][0] = 't';
                gameTable[5][18][1] = 'm';
                gameTable[5][18][2] = 't';
            }
            case 3 -> {
                gameTable[5][19][0] = 't';
                gameTable[5][19][1] = 'c';
                gameTable[5][19][2] = 't';
                gameTable[5][18][0] = 't';
                gameTable[5][18][1] = 'c';
                gameTable[5][18][2] = 't';
                gameTable[5][17][0] = 't';
                gameTable[5][17][1] = 'c';
                gameTable[5][17][2] = 't';
                gameTable[5][16][0] = 't';
                gameTable[5][16][1] = 'c';
                gameTable[5][16][2] = 't';
            }
            case 4 -> {
                gameTable[5][19][0] = 't';
                gameTable[5][19][1] = 'g';
                gameTable[5][19][2] = 't';
                gameTable[5][18][0] = 't';
                gameTable[5][18][1] = 'g';
                gameTable[5][18][2] = 't';
                gameTable[6][18][0] = 't';
                gameTable[6][18][1] = 'g';
                gameTable[6][18][2] = 't';
                gameTable[6][17][0] = 't';
                gameTable[6][17][1] = 'g';
                gameTable[6][17][2] = 't';
            }
        }
    }

    public void moveDown()
    {
        collision();
        checkFilledLines();
       for (int x = 0; x < 10 ; x++)
       {
           for (int y = 0; y < 20; y++)
           {
               if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
               {
                   gameTable[x][y-1][0] = gameTable[x][y][0];
                   gameTable[x][y-1][1] = gameTable[x][y][1];
                   gameTable[x][y-1][2] = gameTable[x][y][2];
                   gameTable[x][y][0] = 'f';
                   gameTable[x][y][2] = 'f';
               }
           }
       }


    }

    public void moveLeft()
    {
        for (int x = 0; x < 10 ; x++)
        {
            for (int y = 0; y < 20; y++)
            {
                if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                {
                    if(x< 1 || (gameTable[x-1][y][0] == 't' && gameTable[x-1][y][2] == 'f')) return;
                }
            }
        }

        for (int x = 0; x < 10 ; x++)
        {
            for (int y = 0; y < 20; y++)
            {
                if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                {
                    gameTable[x-1][y][0] = gameTable[x][y][0];
                    gameTable[x-1][y][1] = gameTable[x][y][1];
                    gameTable[x-1][y][2] = gameTable[x][y][2];
                    gameTable[x][y][0] = 'f';
                    gameTable[x][y][2] = 'f';
                }
            }
        }
    }

    public void moveRight()
    {
        for (int x = 0; x < 10 ; x++)
        {
            for (int y = 0; y < 20; y++)
            {
                if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                {
                    if((x + 1) > 9 || (gameTable[x+1][y][0] == 't' && gameTable[x+1][y][2] == 'f')) return;
                }
            }
        }
        for (int x = 9; x >= 0 ; x--)
        {
            for (int y = 19; y >= 0; y--)
            {
                if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                {
                    gameTable[x+1][y][0] = gameTable[x][y][0];
                    gameTable[x+1][y][1] = gameTable[x][y][1];
                    gameTable[x+1][y][2] = gameTable[x][y][2];
                    gameTable[x][y][0] = 'f';
                    gameTable[x][y][2] = 'f';
                }
            }
        }
    }

    private void hideCurrent()
    {
        for(int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 20; y ++)
            {
                if (gameTable[x][y][2] == 't')
                {
                    gameTable[x][y][0] = 'f';
                    gameTable[x][y][2] = 'f';
                }
            }
        }
    }

    public void rotate()
    {
        if (shape == 0) return;
        int[][] tempLocation = new int[4][2];
        int i = 0;
        r = r>=3 ? 0: r+1;
        for(int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 20; y ++)
            {
                if (gameTable[x][y][2] == 't')
                {
                    tempLocation[i][0] = x;
                    tempLocation[i][1] = y;
                    i++;
                }
            }
        }
        switch (shape)
        {
            case 1:
                switch (r) {
                    case 0 -> {
                        if (   tempLocation[0][0]+2 < 10 &&
                                gameTable[tempLocation[0][0]+2][tempLocation[0][1]][0] != 't' &&
                                gameTable[tempLocation[1][0]+1][tempLocation[1][1]-1][0] != 't' &&
                                gameTable[tempLocation[3][0]-1][tempLocation[3][1]+1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1]][1] = 'b';
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] - 1][0] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] - 1][1] = 'b';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] - 1][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'b';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 1][0] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 1][1] = 'b';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 1][2] = 't';
                        }
                        else r = 3;
                    }
                    case 1 -> {
                        if (tempLocation[2][0]-1 > 0 &&
                                gameTable[tempLocation[2][0]-1][tempLocation[2][1]][0] != 't' &&
                                gameTable[tempLocation[3][0]][tempLocation[3][1]+2][0] != 't' &&
                                gameTable[tempLocation[0][0]-1][tempLocation[0][1]-1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][0] = 't';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][1] = 'b';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'b';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][0] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][1] = 'b';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][2] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1] + 2][0] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1] + 2][1] = 'b';
                            gameTable[tempLocation[3][0]][tempLocation[3][1] + 2][2] = 't';
                        }
                        else r = 0;
                    }
                    case 2 -> {
                        if (tempLocation[2][0]-1 > 0 &&
                                gameTable[tempLocation[0][0]+1][tempLocation[0][1]-1][0] != 't' &&
                                gameTable[tempLocation[2][0]-1][tempLocation[2][1]+1][0] != 't' &&
                                gameTable[tempLocation[3][0]-2][tempLocation[3][1]][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] - 1][0] = 't';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] - 1][1] = 'b';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] - 1][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'b';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] + 1][0] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] + 1][1] = 'b';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] + 1][2] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1]][0] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1]][1] = 'b';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1]][2] = 't';
                        }
                        else r = r-1;
                    }
                    case 3 -> {
                        if (tempLocation[2][0]+1 < 10 && tempLocation[0][1]-2 >=0 &&
                                gameTable[tempLocation[0][0]][tempLocation[0][1]-2][0] != 't' &&
                                gameTable[tempLocation[1][0]+1][tempLocation[1][1]+1][0] != 't' &&
                                gameTable[tempLocation[3][0]-1][tempLocation[3][1]-1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1] - 2][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1] - 2][1] = 'b';
                            gameTable[tempLocation[0][0]][tempLocation[0][1] - 2][2] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][0] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][1] = 'b';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'b';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][0] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][1] = 'b';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][2] = 't';
                        }
                        else r = r-1;
                    }
                }
                break;
            case 2:
                switch (r) {
                    case 0 -> {
                        if(tempLocation[3][0]+1 < 10 && tempLocation[3][0]+1 >= 0 &&
                                gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][1] = 'm';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'm';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'm';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 1][0] = 't';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 1][1] = 'm';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 1][2] = 't';
                        }
                        else r = 3;
                    }
                    case 1 -> {
                        if (tempLocation[0][1]+1 < 20 &&
                                gameTable[tempLocation[0][0]+1][tempLocation[0][1]+1][0] !='t')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][0] = 't';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][1] = 'm';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'm';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'm';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][0] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][1] = 'm';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][2] = 't';
                        }
                        else r = 0;
                    }
                    case 2 -> {
                        if (tempLocation[0][0]-1 >=0 && tempLocation[0][1]-1 < 20 &&
                                gameTable[tempLocation[0][0]-1][tempLocation[0][1]-1][0] !='t')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] - 1][tempLocation[0][1] + 1][0] = 't';
                            gameTable[tempLocation[0][0] - 1][tempLocation[0][1] + 1][1] = 'm';
                            gameTable[tempLocation[0][0] - 1][tempLocation[0][1] + 1][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'm';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'm';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][0] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][1] = 'm';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][2] = 't';
                        }
                        else r = r-1;
                    }
                    case 3 -> {
                        if(tempLocation[3][0] - 1 >= 0 &&
                                gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][1] = 'm';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'm';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'm';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][0] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][1] = 'm';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][2] = 't';
                        }
                        else r = r-1;
                    }
                }
                break;
            case 3:
                switch (r) {
                    case 1 -> {
                        if(tempLocation[0][0] + 1 < 10 && tempLocation[3][0] - 2 >= 0 &&
                                gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][0] != 't'&&
                                gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][0] != 't'&&
                                gameTable[tempLocation[3][0] - 2][tempLocation[3][1] - 2][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][0] = 't';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][1] = 'c';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1] + 1][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'c';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][0] = 't';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][1] = 'c';
                            gameTable[tempLocation[2][0] - 1][tempLocation[2][1] - 1][2] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] - 2][0] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] - 2][1] = 'c';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] - 2][2] = 't';
                        }
                        else r = 0;
                    }
                    case 2 -> {
                        if (tempLocation[0][0] + 2 <= 10 && tempLocation[3][0] - 1 >=0 &&
                                gameTable[tempLocation[0][0] + 2][tempLocation[0][1] + 2][0] != 't' &&
                                gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][0] != 't' &&
                                gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1] + 2][0] = 't';
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1] + 2][1] = 'c';
                            gameTable[tempLocation[0][0] + 2][tempLocation[0][1] + 2][2] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][0] = 't';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][1] = 'c';
                            gameTable[tempLocation[1][0] + 1][tempLocation[1][1] + 1][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'c';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][0] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][1] = 'c';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] - 1][2] = 't';
                        }
                        else r = r-1;
                    }
                }
                break;
            case 4:
                switch (r) {
                    case 0 -> {
                        if (gameTable[tempLocation[3][0] - 2][tempLocation[3][1] + 2][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][1] = 'g';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'g';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'g';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] + 2][0] = 't';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] + 2][1] = 'g';
                            gameTable[tempLocation[3][0] - 2][tempLocation[3][1] + 2][2] = 't';
                        }
                        else r = 3;
                    }
                    case 1 -> {
                        if (tempLocation[1][0] + 2 < 10 &&
                                gameTable[tempLocation[1][0] + 2][tempLocation[1][1] - 2][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][1] = 'g';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0] + 2][tempLocation[1][1] - 2][0] = 't';
                            gameTable[tempLocation[1][0] + 2][tempLocation[1][1] - 2][1] = 'g';
                            gameTable[tempLocation[1][0] + 2][tempLocation[1][1] - 2][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'g';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][0] = 't';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][1] = 'g';
                            gameTable[tempLocation[3][0]][tempLocation[3][1]][2] = 't';
                        }
                        else r = 0;
                    }
                    case 2 -> {
                        if (gameTable[tempLocation[1][0] - 1][tempLocation[1][1]][0] != 't'&&
                                gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 2][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][1] = 'g';
                            gameTable[tempLocation[0][0]][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0] - 1][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0] - 1][tempLocation[1][1]][1] = 'g';
                            gameTable[tempLocation[1][0] - 1][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'g';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 2][0] = 't';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 2][1] = 'g';
                            gameTable[tempLocation[3][0] - 1][tempLocation[3][1] + 2][2] = 't';
                        }
                        else r = r-1;
                    }
                    case 3 -> {
                        if (tempLocation[0][0] + 1 < 10 && tempLocation[3][0] + 1 < 10 &&
                                gameTable[tempLocation[0][0] + 1][tempLocation[0][1]][0] != 't'&&
                                gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 2][0] != 't')
                        {
                            hideCurrent();
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1]][0] = 't';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1]][1] = 'g';
                            gameTable[tempLocation[0][0] + 1][tempLocation[0][1]][2] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][0] = 't';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][1] = 'g';
                            gameTable[tempLocation[1][0]][tempLocation[1][1]][2] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][0] = 't';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][1] = 'g';
                            gameTable[tempLocation[2][0]][tempLocation[2][1]][2] = 't';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 2][0] = 't';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 2][1] = 'g';
                            gameTable[tempLocation[3][0] + 1][tempLocation[3][1] - 2][2] = 't';
                        }
                        else r = r-1;
                    }
                }
                break;
        }
        collision();
    }

    public void checkFilledLines()
    {
        int localScore = 0;
        int scoreMultiplier = 0;
        for (int y = 0; y < 20; y++)
        {
            int blocksInLine = 0;
            for (int x = 0; x < 10; x++) if (gameTable[x][y][0] == 't' && gameTable[x][y][2] != 't') blocksInLine++;
            if (blocksInLine == 10)
            {
                localScore += 10;
                scoreMultiplier++;
                for (int x1 = 0; x1 < 10; x1++)
                {
                    for (int y1 = y; y1 < 20; y1++)
                    {
                        if (gameTable[x1][y1][0] == 't' && gameTable[x1][y1][2] != 't')
                        {
                            gameTable[x1][y1][0] = gameTable[x1][y1+1][0];
                            gameTable[x1][y1][1] = gameTable[x1][y1+1][1];
                            gameTable[x1][y1][2] = gameTable[x1][y1+1][2];
                        }
                    }
                }
                y--;
            }
        }
        score += localScore * scoreMultiplier;
        tickTime -= localScore / 10 * scoreMultiplier;
    }

    public void collision()
    {
        boolean collision = false;
        for (int x = 0; x < 10 ; x++)
        {
            for (int y = 0; y < 20; y++)
            {
                if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                {
                    if((y - 1) < 0 || (gameTable[x][y-1][0] == 't' && gameTable[x][y-1][2] == 'f')) collision = true;
                }
            }
        }
        if (collision)
        {
            for (int x = 0; x < 10 ; x++)
            {
                for (int y = 0; y < 20; y++)
                {
                    if (gameTable[x][y][2] == 't' && gameTable[x][y][0] == 't')
                    {
                        gameTable[x][y][0] = 't';
                        gameTable[x][y][2] = 'f';
                    }
                }
            }
            newShape();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g)
    {
        g.drawString("Score: " + score, 670, 20);
        g.drawLine(600,0, 600, 600);
        g.drawRect(150, 0,300, 600);
        for(int i = 0; i < 20; i++)
        {
            g.setColor(Color.gray);
            g.fill3DRect(120, i * 30, 30, 30, true);
        }
        for(int i = 0; i < 20; i++)
        {
            g.setColor(Color.gray);
            g.fill3DRect(450, i * 30, 30, 30, true);
        }
        for(int x = 0; x < 10; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                if (gameTable[x][y][0] == 't')
                {
                    switch (gameTable[x][y][1]) {
                        case 'm' -> g.setColor(Color.magenta);
                        case 'y' -> g.setColor(Color.yellow);
                        case 'r' -> g.setColor(Color.red);
                        case 'g' -> g.setColor(Color.green);
                        case 'b' -> g.setColor(Color.blue);
                        case 'o' -> g.setColor(Color.orange);
                        case 'c' -> g.setColor(Color.cyan);
                    }

                    g.fill3DRect((x * 30) + 150, 570 - (y * 30), 30, 30, false);

                }
            }
        }
    }

}
