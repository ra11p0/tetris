package com.ra11p0;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyActions extends KeyAdapter {
    private Game _target;
    public KeyActions (Game target)
    {
        _target = target;
    }
    @Override
    public void keyPressed (KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                _target.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                _target.moveRight();
                break;
        }



        _target.repaint();
        //_target.setBackground(Color.white);
        //_target.temp = e.toString();
    }
}
