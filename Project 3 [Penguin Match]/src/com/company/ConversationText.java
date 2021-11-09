package com.company;

import javax.swing.*;

public class ConversationText extends Thread
{
    private JTextField temp;
    private JComboBox iconComboBox;

    public ConversationText (JTextField speak,JComboBox iconComboBox)
    {
        this.temp           = speak;
        this.iconComboBox   = iconComboBox;
    }

    @Override
    public void run()
    {
        if(this.iconComboBox.getSelectedItem().equals("LUNAR"))
        {
            temp.setText("Everything you can imagine is real.");
        }
        else if(this.iconComboBox.getSelectedItem().equals("ANDUIN"))
        {
            temp.setText("And still, I rise.");
        }
        else if(this.iconComboBox.getSelectedItem().equals("JAINA"))
        {
            temp.setText("Aspire to inspire before we expire.");
        }
        else if(this.iconComboBox.getSelectedItem().equals("VALEERA"))
        {
            temp.setText("Whatever you do, do it well.");
        }
        else if(this.iconComboBox.getSelectedItem().equals("ARTHUS"))
        {
            temp.setText("Turn your wounds into wisdom.");
        }
        else if(this.iconComboBox.getSelectedItem().equals("MAIEV"))
        {
            temp.setText("Love For All, Hatred For None.");
        }
    }
}
