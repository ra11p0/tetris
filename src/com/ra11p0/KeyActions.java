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
            case KeyEvent.VK_UP:
                _target.rotate();
                break;
            case KeyEvent.VK_DOWN:
                _target.timer.setDelay(50);
                break;
        }
        _target.repaint();
    }
    @Override
    public void keyReleased (KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                _target.timer.setDelay(_target.tickTime);
                break;
        }
        _target.repaint();
    }
}
