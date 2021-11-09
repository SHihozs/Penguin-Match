package com.company;

class MySoundEffect
{
    private java.applet.AudioClip audio;


    public MySoundEffect(String filename)
    {
        try
        {
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
        }
        catch (Exception e) { e.printStackTrace(); }
    }
    public void setMySoundName(String filename) {
        try
        {
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
        }
        catch (Exception e) { e.printStackTrace(); }


    }

    public void playLoop()   { audio.loop(); }
    public void stop()       { audio.stop(); }
}