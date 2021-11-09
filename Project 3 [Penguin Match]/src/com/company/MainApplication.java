package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainApplication implements ActionListener
{
    private JFrame                  mainFrame;
    private JPanel                  mainContentPane;
    private JMenuBar                menuBar;
    private JMenu                   gameMenu;
    private ArrayList<ImageIcon>    cardIcon;
    private CheckPair               check;
    private String                  PlayerName;
    private CountTimer              timer;
    private boolean                 otherFrameIsOpen;
    private ImageIcon               icon;
    private JComboBox               iconComboBox;
    private MySoundEffect           themeSound;
    private boolean                 isPause;

    public MainApplication()
    {
        this.check                  = new CheckPair(this);
        this.PlayerName             = "";
        this.isPause                = false;
        this.otherFrameIsOpen       = false;
        this.icon                   = new ImageIcon("image/Icon0.gif");
        this.cardIcon               = new ArrayList<>();
        this.mainFrame              = new JFrame("MAIN APPLICATION");
        this.mainFrame.setResizable(false);
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        for (int i = 0; i <= 20; i++)
        {
            this.cardIcon.add(new ImageIcon("image/card"+i+".png"));
        }

        String iconList[] = new String[6];
        iconList[0] = "LUNAR";
        iconList[1] = "ANDUIN";
        iconList[2] = "JAINA";
        iconList[3] = "VALEERA";
        iconList[4] = "ARTHUS";
        iconList[5] = "MAIEV";

        this.iconComboBox = new JComboBox(iconList);
        this.iconComboBox.setPreferredSize(new Dimension(190,30));
        this.iconComboBox.setBackground(Color.white);
        this.iconComboBox.setBorder(new LineBorder(Color.black,3,false));
        this.iconComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                setIcon(e.getItem().toString());
                mainContentPane.removeAll();
                chooseIcon();
            }
        });

        mainMenu();
    }

    //==========[GETTER/SETTER]==========//

    public String getPlayerName() {
        return PlayerName;
    }

    public CountTimer getTimer()
    {
        return timer;
    }

    public JPanel getMainContentPane() {
        return mainContentPane;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public boolean getIsPause()
    {
        return isPause;
    }

    public MySoundEffect getThemeSound() { return themeSound; }

    public void setPause(boolean pause) { isPause = pause; }

    public void setIcon(String n)
    {
        if(n.equals("LUNAR"))
        {
            icon = new ImageIcon("image/Icon0.gif");
        }
        else if(n.equals("ANDUIN"))
        {
            icon = new ImageIcon("image/Icon1.gif");
        }
        else if(n.equals("JAINA"))
        {
            icon = new ImageIcon("image/Icon2.gif");
        }
        else if(n.equals("VALEERA"))
        {
            icon = new ImageIcon("image/Icon3.gif");
        }
        else if(n.equals("ARTHUS"))
        {
            icon = new ImageIcon("image/Icon4.gif");
        }
        else if(n.equals("MAIEV"))
        {
            icon = new ImageIcon("image/Icon5.gif");
        }
    }

    //==========[MAIN MENU FRAME]==========//

    public void mainMenu()
    {
        this.mainFrame.setSize(500,600);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainContentPane = (JPanel) this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new FlowLayout());
        this.mainContentPane.setBackground(Color.BLACK);

        themeSound = new MySoundEffect("song/song0.wav");
        themeSound.playLoop();

        ImageIcon logo      = new ImageIcon("image/Logo.gif");
        JLabel logoLabel    = new JLabel(logo);
        JPanel logoPanel    = new JPanel();
        logoPanel.setBackground(Color.black);
        logoPanel.add(logoLabel);
        this.mainContentPane.add(logoPanel);

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout());
        mainMenuPanel.setBorder(new EmptyBorder(0,100,200,100));
        mainMenuPanel.setBackground(Color.BLACK);

        JPanel rowPanel = new JPanel(new GridLayout(5,1,10,10));
        rowPanel.setBackground(Color.BLACK);

        addMainMenuItem("START GAME",rowPanel);
        addMainMenuItem("HOW TO PLAY",rowPanel);
        addMainMenuItem("CHOOSE ICON",rowPanel);
        addMainMenuItem("CREDIT",rowPanel);
        addMainMenuItem("EXIT",rowPanel);

        mainMenuPanel.add(rowPanel);

        this.mainContentPane.add(mainMenuPanel);

        this.mainFrame.validate();
    }

    public void addMainMenuItem(String name,JPanel rowPanel)
    {
        JButton item = new JButton(name);
        item.setActionCommand(name);
        item.addActionListener(this);
        item.setPreferredSize(new Dimension(250,40));
        item.setBackground(Color.white);
        item.setFont(new Font("Arial Black",Font.PLAIN,20));
        item.setBorder(new LineBorder(new Color(234,193,23),3,false));
        rowPanel.add(item,BorderLayout.CENTER);
    }

    //==========[GAME FRAME]==========//

    public void GenerateGameFrame()
    {
        this.mainFrame.setSize(1366,768);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainContentPane = (JPanel)mainFrame.getContentPane();
        this.mainContentPane.setLayout(new BorderLayout());

        this.gameMenu = new JMenu("MENU");

        this.menuBar = new JMenuBar();
        this.menuBar.add(gameMenu);

        this.mainFrame.setJMenuBar(menuBar);

        addMenuBarItem("NEW GAME");
        addMenuBarItem("MAIN MENU");
        addMenuBarItem("EXIT");

        GenerateBoard();
        GenerateCard();

        this.mainFrame.validate();
    }

    public void addMenuBarItem(String name)
    {
        JMenuItem item = new JMenuItem(name);
        item.setActionCommand(name);
        item.addActionListener(this);
        this.gameMenu.add(item);
    }

    public void GenerateBoard()
    {
        JPanel BoardPanel = new JPanel(new FlowLayout(0,10,10));
        BoardPanel.setPreferredSize(new Dimension(210,768));
        BoardPanel.setBackground(Color.black);

        JTextField name = new JTextField(this.PlayerName.toUpperCase());
        name.setEditable(false);
        name.setFont(new Font("Arial Black",Font.BOLD,20));
        name.setPreferredSize(new Dimension(190,30));
        name.setBorder(new LineBorder(Color.gray,2,false));
        name.setHorizontalAlignment(JTextField.CENTER);
        BoardPanel.add(name);

        JLabel iconLabel = new JLabel(this.icon);
        iconLabel.setBorder(new LineBorder(Color.gray,2,false));
        iconLabel.setPreferredSize(new Dimension(190,200));
        BoardPanel.add(iconLabel);

        JTextField timerText = new JTextField();
        timerText.setEditable(false);
        timerText.setFont(new Font("Arial Black",Font.BOLD,20));
        timerText.setPreferredSize(new Dimension(190,30));
        timerText.setBorder(new LineBorder(Color.gray,2,false));
        timerText.setHorizontalAlignment(JTextField.CENTER);

        this.timer = new CountTimer(timerText,this);
        this.timer.start();

        BoardPanel.add(timerText);

        JButton resumeButton = new JButton("RESUME");
        resumeButton.setActionCommand("RESUME");
        resumeButton.addActionListener(this);
        resumeButton.setPreferredSize(new Dimension(90,30));
        resumeButton.setBackground(Color.lightGray);
        BoardPanel.add(resumeButton);

        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setActionCommand("PAUSE");
        pauseButton.addActionListener(this);
        pauseButton.setPreferredSize(new Dimension(90,30));
        pauseButton.setBackground(Color.lightGray);
        BoardPanel.add(pauseButton);

        JLabel space = new JLabel("");
        space.setPreferredSize(new Dimension(190,100));
        BoardPanel.add(space);

        JLabel songLabel = new JLabel("SONGS");
        songLabel.setFont(new Font("Arial Black",Font.BOLD,18));
        songLabel.setForeground(Color.lightGray);
        BoardPanel.add(songLabel);

        JCheckBox song1 = GenerateSong("SONG 1");
        JCheckBox song2 = GenerateSong("SONG 2");
        JCheckBox song3 = GenerateSong("SONG 3");
        JCheckBox song4 = GenerateSong("SONG 4");
        JCheckBox song5 = GenerateSong("SONG 5");

        ButtonGroup songGroup = new ButtonGroup();
        song1.setSelected(true);
        songGroup.add(song1);
        songGroup.add(song2);
        songGroup.add(song3);
        songGroup.add(song4);
        songGroup.add(song5);

        BoardPanel.add(song1);
        BoardPanel.add(song2);
        BoardPanel.add(song3);
        BoardPanel.add(song4);
        BoardPanel.add(song5);

        themeSound = new MySoundEffect("song/song1.wav");
        themeSound.playLoop();

        song1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeSound.stop();

                themeSound.setMySoundName("song/song1.wav");
                themeSound.playLoop();
            }
        });

        song2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                themeSound.stop();
                themeSound.setMySoundName("song/song2.wav");
                themeSound.playLoop();
            }
        });
        song3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeSound.stop();
                themeSound.setMySoundName("song/song3.wav");
                themeSound.playLoop();
            }
        });
        song4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeSound.stop();
                themeSound.setMySoundName("song/song4.wav");
                themeSound.playLoop();
            }
        });
        song5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeSound.stop();
                themeSound.setMySoundName("song/song5.wav");
                themeSound.playLoop();
            }
        });

        mainContentPane.add(BoardPanel,BorderLayout.WEST);
    }

    public JCheckBox GenerateSong(String songName)
    {
        JCheckBox song = new JCheckBox(songName);
        song.setPreferredSize(new Dimension(190,30));
        song.setFont(new Font("Arial Black",Font.PLAIN,12));
        song.setForeground(Color.lightGray);
        song.setBackground(Color.black);

        return song;
    }

    public void GenerateCard()
    {
        JPanel cardPanel = new JPanel(new GridLayout(5,10,5,5));
        cardPanel.setPreferredSize(new Dimension(1000,768));
        cardPanel.setBackground(Color.gray);

        ArrayList<Integer> cardNumber = new ArrayList<>();

        for (int i=0;i<20;i++)
        {
            cardNumber.add(i);
            cardNumber.add(i);
        }

        Collections.shuffle(cardNumber);

        for (int i=0;i<cardNumber.size();i++)
        {
            int n = cardNumber.get(i);
            Card card = new Card(check,this.cardIcon.get(n),this.cardIcon.get(20),cardNumber.get(i),this);
            card.setBorder(new LineBorder(Color.black,3,false));
            cardPanel.add(card);
        }

        this.mainContentPane.add(cardPanel);
    }

    //==========[CHOOSE ICON]==========//

    public void chooseIcon()
    {
        this.mainFrame.setSize(400,500);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainContentPane = (JPanel) this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new FlowLayout());
        this.mainContentPane.setBackground(Color.gray);

        JPanel iconPanel = new JPanel();
        iconPanel.setBorder(new EmptyBorder(50,20,20,20));
        iconPanel.setLayout(new FlowLayout());
        iconPanel.setPreferredSize(new Dimension(200,410));
        iconPanel.setBackground(Color.gray);

        JLabel iconLabel = new JLabel(this.icon);
        iconLabel.setBorder(new LineBorder(Color.black,4,false));
        iconLabel.setPreferredSize(new Dimension(190,200));
        iconPanel.add(iconLabel);

        iconPanel.add(this.iconComboBox);

        JTextField speak = new JTextField("");
        speak.setPreferredSize(new Dimension(190,30));
        speak.setEditable(false);
        speak.setBorder(new LineBorder(Color.black,4,false));
        speak.setFont(new Font("Arial",Font.ITALIC,12));
        ConversationText con = new ConversationText(speak,iconComboBox);
        iconPanel.add(speak);
        con.start();

        try { con.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(100,30));
        space.setBackground(Color.gray);
        iconPanel.add(space);

        JButton back = new JButton("BACK");
        back.setActionCommand("BACK");
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(100,30));
        back.setBackground(Color.white);
        back.setBorder(new LineBorder(Color.black,3,false));
        iconPanel.add(back);

        this.mainContentPane.add(iconPanel);

        this.mainFrame.validate();
    }

    //==========[HOW TO PLAY]==========//

    public void howToPlay()
    {
        JFrame howToPlayFrame = new JFrame();
        howToPlayFrame.setSize(1366,768);
        howToPlayFrame.setResizable(false);
        howToPlayFrame.setVisible(true);
        howToPlayFrame.setLocationRelativeTo(null);
        howToPlayFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        howToPlayFrame.addWindowListener (new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                otherFrameIsOpen = false;
            }
        });

        JPanel howToPlayPanel = new JPanel();

        ImageIcon htp = new ImageIcon("image/how to play.GIF");

        JLabel howToPlayLabel = new JLabel(htp);

        howToPlayPanel.add(howToPlayLabel);

        howToPlayFrame.add(howToPlayPanel);

        howToPlayFrame.validate();
    }

    //==========[CREDIT FRAME]==========//

    public void credit()
    {
        JFrame creditFrame = new JFrame();
        creditFrame.setSize(1366,768);
        creditFrame.setResizable(false);
        creditFrame.setVisible(true);
        creditFrame.setLocationRelativeTo(null);
        creditFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        creditFrame.addWindowListener (new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                otherFrameIsOpen = false;
            }
        });

        JPanel creditPanel = new JPanel();

        ImageIcon credit = new ImageIcon("image/credit.GIF");

        JLabel creditLabel = new JLabel(credit);

        creditPanel.add(creditLabel);

        creditFrame.add(creditPanel);

        creditFrame.validate();
    }

    //==========[ACTION LISTENER]==========//

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("NEW GAME"))
        {
            this.mainContentPane.removeAll();
            this.check.setPaired(0);
            this.themeSound.stop();
            GenerateGameFrame();
        }
        else if(e.getActionCommand().equals("START GAME"))
        {
            if(!otherFrameIsOpen)
            {
                try
                {
                    this.PlayerName = JOptionPane.showInputDialog("ENTER YOUR NAME (LIMITS 10 CHARACTER)");

                    if (this.PlayerName.length() != 0 && this.PlayerName.length() <= 10) {
                        this.mainContentPane.removeAll();
                        this.themeSound.stop();
                        GenerateGameFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "PLEASE ENTER YOUR NAME CORRECTLY", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NullPointerException err) { }
            }

        }
        else if (e.getActionCommand().equals("HOW TO PLAY") && !otherFrameIsOpen)
        {
            this.otherFrameIsOpen = true;
            howToPlay();
        }
        else if (e.getActionCommand().equals("CHOOSE ICON") && !otherFrameIsOpen)
        {
            this.mainContentPane.removeAll();
            chooseIcon();
        }
        else if (e.getActionCommand().equals("CREDIT") && !otherFrameIsOpen)
        {
            this.otherFrameIsOpen = true;
            credit();
        }
        else if (e.getActionCommand().equals("MAIN MENU"))
        {
            this.mainContentPane.removeAll();
            this.mainFrame.getJMenuBar().removeAll();
            this.themeSound.stop();
            this.check.setPaired(0);
            mainMenu();
        }
        else if (e.getActionCommand().equals("BACK"))
        {
            this.mainContentPane.removeAll();
            this.themeSound.stop();
            mainMenu();
        }
        else if (e.getActionCommand().equals("PAUSE"))
        {
            this.isPause = true;
        }
        else if (e.getActionCommand().equals("RESUME"))
        {
            this.isPause = false;
        }
        else if (e.getActionCommand().equals("EXIT"))
        {
            System.exit(-1);
        }
    }
}
