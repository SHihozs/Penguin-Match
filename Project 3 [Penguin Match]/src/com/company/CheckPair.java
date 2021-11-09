package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class CheckPair
{
    private ArrayList<Card> check;
    private int paired;
    private MainApplication mainApplication;

    public void setPaired(int p)
    {
        this.paired = p;
    }

    public CheckPair(MainApplication mainApplication)
    {
        this.mainApplication = mainApplication;
        this.check          = new ArrayList<>();
        this.paired         = 0;
    }

    public void checkPair(Card card)
    {
        if(this.check.size()==0)
        {
            this.check.add(card);
        }
        else if(this.check.size()==1)
        {
            this.check.add(card);

            if (this.check.get(1).getNumber() == this.check.get(0).getNumber())
            {
                this.check.clear();
                this.paired++;
            }
            else
            {
                this.check.get(0).flipDown();
                this.check.get(1).flipDown();
                this.check.clear();
            }
        }
        if(paired == 20)
        {
            this.paired = 0;
            this.mainApplication.setPause(true);

            JOptionPane.showMessageDialog(null,"NAME : " + this.mainApplication.getPlayerName() +" \n TIME : " + this.mainApplication.getTimer().print(),"Congratulation!",JOptionPane.INFORMATION_MESSAGE);

            this.mainApplication.getMainContentPane().removeAll();
            this.mainApplication.getMenuBar().removeAll();
            this.mainApplication.getThemeSound().stop();
            this.mainApplication.mainMenu();

            this.mainApplication.setPause(false);
        }
    }
}
