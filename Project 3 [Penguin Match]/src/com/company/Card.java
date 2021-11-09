package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Card extends JLabel implements MouseListener
{
    private Icon front;
    private Icon back;
    private boolean turnedUp;
    private int number;
    private CheckPair check;
    private MainApplication mainApplication;

    public Card(CheckPair check, Icon front, Icon back, int number, MainApplication mainApplication)
    {
        super(back);
        this.front          = front;
        this.back           = back;
        this.number         = number;
        this.turnedUp       = false;
        this.check          = check;
        this.mainApplication = mainApplication;
        this.addMouseListener(this);
    }

    public void flipUp()
    {
        if(!turnedUp)
        {
            this.turnedUp = true;
            this.setIcon(this.front);
            this.check.checkPair(this);
        }
    }

    public void flipDown()
    {
        if(turnedUp)
        {
            this.turnedUp = false;
            this.setIcon(this.back);
        }
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!mainApplication.getIsPause()) { this.flipUp(); }
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
