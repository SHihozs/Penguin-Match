package com.company;

import javax.swing.*;

public class CountTimer extends Thread
{
    private int count;
    private JTextField temp;
    private int minutes;
    private int seconds;
    private MainApplication mainApplication;

    public CountTimer(JTextField timerText, MainApplication mainApplication)
    {
        this.mainApplication = mainApplication;
        count   = 0;
        minutes = 0;
        seconds = 0;
        temp = timerText;
        temp.setText("0" + minutes + " : " + "0" + count);
    }

    @Override
    public void run()
    {
        while(true)
        {
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            if(!mainApplication.getIsPause())
            {
                count++;
            }
            temp.setText(print());

        }
    }

    public String print()
    {
        minutes = count/60;
        seconds = count - (minutes*60);

        if(seconds < 10 && minutes < 10)
        {
            return "0" + minutes + " : " + "0" + seconds;
        }
        else if (seconds >= 10 && minutes < 10)
        {
            return "0" + minutes + " : " + seconds;
        }
        else if(seconds < 10 && minutes >= 10)
        {
            return minutes + " : " + "0" + seconds;
        }
        else
        {
            return minutes + " : " + seconds;
        }

    }
}
