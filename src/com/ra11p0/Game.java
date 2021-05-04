package com.ra11p0;

import javax.swing.*;
import java.awt.*;
import java.security.Key;
import java.util.Random;


public class Game extends JPanel{
    private boolean isRunning = false;
    private JFrame window = new JFrame();
    private Timer timer = new Timer(500, new TimerActions(this));
    public char[][][] gameTable = new char[10][20][3];
    int shape;





    public Game(int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
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
        isRunning = true;
        timer.start();
        newShape();
    }

    private void newShape()
    {
        shape = new Random().nextInt(4);
        switch(shape)
        {
            case 0:
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
                break;
            case 1:
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
                break;
            case 2:
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
                break;
            case 3:
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
                break;
            case 4:
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
                break;
        }
    }

    public void moveDown()
    {
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
       collision();
    }

    public void moveLeft()
    {
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
        for (int x = 9; x > 0 ; x--)
        {
            for (int y = 19; y > 0; y--)
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

    public void rotateLeft()
    {

    }

    public void rotateRigth()
    {

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
                    switch (gameTable[x][y][1])
                    {
                        case 'm':
                            g.setColor(Color.magenta);
                            break;
                        case 'y':
                            g.setColor(Color.yellow);
                            break;
                        case 'r':
                            g.setColor(Color.red);
                            break;
                        case 'g':
                            g.setColor(Color.green);
                            break;
                        case 'b':
                            g.setColor(Color.blue);
                            break;
                        case 'o':
                            g.setColor(Color.orange);
                            break;
                        case 'c':
                            g.setColor(Color.cyan);
                            break;
                    }

                    g.fill3DRect((x * 30) + 150, 570 - (y * 30), 30, 30, false);
                }
            }
        }
    }

}
