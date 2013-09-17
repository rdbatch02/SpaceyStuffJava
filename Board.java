package SpaceyStuff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Craft craft;
    private Enemy enemy;
    private int score;
    private int lives;
    private String score_str;

    private Random generator = new Random();
    private int randomX = generator.nextInt(750);
    private int randomY = generator.nextInt(550);


    private JLabel scoreBoard;
    private Font sans;

    private final int STAR_SIZE = 4;

    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        craft = new Craft();
        enemy = new Enemy();
        score = 0;
        lives = 3;

        sans = new Font("Sans-Serif", Font.BOLD, 26);

        scoreBoard = new JLabel("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives));
        scoreBoard.setForeground(Color.white);
        scoreBoard.setFont(sans);
        add(scoreBoard);
        

        timer = new Timer(5, this);
        timer.start();
    }

    public void flicker() {
        System.out.println("Boom");
        setBackground(Color.white);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //setBackground(Color.black);
    }

    public void paint(Graphics g) {
        super.paint(g);

        score_str = String.valueOf(score);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        if (enemy.isAlive()) {
            g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        }

        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }

        scoreBoard.setText("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives));
        if (craft.isAlive() == false) {
            scoreBoard.setText("You Lose! Final Score: " + String.valueOf(score));
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        setBackground(Color.black);
        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) {
                m.move();
                if ((m.getX() >= enemy.getXBoxNeg() && m.getX() <= enemy.getXBoxPos()) && (m.getY() >= enemy.getYBoxNeg() && m.getY() <= enemy.getYBoxPos())) {
                    flicker();
                    enemy.setAlive(false);
                    enemy = new Enemy();
                    ms.remove(i);
                    score++;


                }
            }
            else {
                ms.remove(i);
            }
        }
        enemy.move();
        if (enemy.getX() <= -80) {
            enemy.setAlive(false);
            lives--;
            if (lives == 0) {
                craft.setAlive(false);
            }
            if (craft.isAlive()) {
                enemy = new Enemy();
            }
        }
        craft.move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}