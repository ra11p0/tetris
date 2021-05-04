package com.ra11p0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerActions implements ActionListener {
    private Game _target;
    public TimerActions(Game target)
    {
        _target = target;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        _target.moveDown();
        _target.repaint();



        //_target.setBackground(_target.getBackground().darker());
    }
}
