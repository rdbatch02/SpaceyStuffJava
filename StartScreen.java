package SpaceyStuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

/**
 * Created by: Ryan Batchelder
 * Date: 10/16/13
 */
public class StartScreen extends JFrame implements ActionListener {
    public static SpaceyStuff game;
    public static StartScreen start;

    public StartScreen() {
        ImageIcon background_img = new ImageIcon(this.getClass().getResource("images/background.gif"));
        BackgroundPanel background = new BackgroundPanel(background_img.getImage(), BackgroundPanel.ACTUAL, 0.50f, 0.5f);
        this.setContentPane(background);
        ImageIcon logo = new ImageIcon("images/icon.png");
        GridLayout windowLayout = new GridLayout(5, 2, 2, 50);

        setLayout(windowLayout);

        JLabel welcome = new JLabel("Welcome to Spacey Stuff!");
        welcome.setForeground(Color.white);
        //welcome.setSize(new Dimension(250, 200));
        welcome.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setBackground(Color.white);
        startButton.setOpaque(true);
        startButton.setActionCommand("startGame");
        //startButton.setSize(new Dimension(150, 50));

        JButton quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.setOpaque(true);
        quitButton.setActionCommand("quitGame");
        quitButton.setPreferredSize(new Dimension(150, 50));

        JButton aboutButton = new JButton("About");
        aboutButton.setBackground(Color.white);
        aboutButton.setOpaque(true);
        aboutButton.setActionCommand("about");
        aboutButton.setSize(new Dimension(150, 50));

        JLabel copyright = new JLabel("Copyright 2013 - Ryan Batchelder");
        copyright.setForeground(Color.white);
        copyright.setFont(new Font("Sans-serif", Font.PLAIN, 10));
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setVerticalTextPosition(SwingConstants.BOTTOM);

        startButton.addActionListener(this);
        quitButton.addActionListener(this);
        aboutButton.addActionListener(this);

        add(welcome, windowLayout);
        add(startButton, windowLayout);
        add(quitButton, windowLayout);
        add(aboutButton, windowLayout);
        add(copyright, windowLayout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Spacey Stuff");
        setResizable(false);
        setVisible(true);
        //pack();
        //System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString()); //Fix for super slow-mo on first run caused by Aero.
        ImageIcon taskbarIcon = new ImageIcon("images/taskbar_icon.gif");
        setIconImage(taskbarIcon.getImage());

    }

    public void actionPerformed(ActionEvent e) {
        if ("startGame".equals(e.getActionCommand())) {
            startGame();
        }
        if ("about".equals(e.getActionCommand())) {
            openDuplex();
        }
        if ("quitGame".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }

    public static void openDuplex() {
        try {
            Desktop.getDesktop().browse(new URL("http://duplexstudios.com/ryan.html").toURI());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void startGame() {
        game = new SpaceyStuff();
    }

    public static void reset() {
        game.dispose();
        game.setVisible(false);
        game = new SpaceyStuff();
    }

    public static void main(String[] args) {
        start = new StartScreen();
    }
}
