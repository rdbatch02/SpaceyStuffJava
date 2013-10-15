package SpaceyStuff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import java.util.ArrayList;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    public Craft craft;
    private Enemy enemy;
    private Asteroid asteroid;
    private int score;
    private int lives;
    private double degrees = 0;

    private int visible_timer = 100;

    private JLabel scoreBoard;
    private JLabel resetRequest;
    private Font sans;

    public ImageIcon background_img = new ImageIcon(this.getClass().getResource("images/background.gif"));

//    private final int STAR_SIZE = 4; We might need this later for star generation

    public Board() {

        addKeyListener(new TAdapter());
        //setBackground(Color.black);
        //setDoubleBuffered(true);
        setOpaque(false);

        craft = new Craft();
        enemy = new Enemy();
        asteroid = new Asteroid();
        score = 0;
        lives = 3;

        sans = new Font("Sans-Serif", Font.BOLD, 26);

        scoreBoard = new JLabel("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives));
        scoreBoard.setForeground(Color.white);
        scoreBoard.setFont(sans);
        add(scoreBoard);

        resetRequest = new JLabel("Press R to play again");
        resetRequest.setForeground(Color.white);
        resetRequest.setFont(sans);
        resetRequest.setVisible(false);

        

        timer = new Timer(5, this);
        timer.start();
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        //score_str = String.valueOf(score);

        Graphics2D g2d = (Graphics2D) g;



        if (craft.isVisible())
            g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        else {
            visible_timer--;
            if (visible_timer == 0) {
                craft.reset();
                visible_timer = 100;
            }
        }

        if (enemy.isAlive()) {
            g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        }

        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }

        if (craft.getHeat() >= 950)
            scoreBoard.setText("Weapon Overheat!");
        else
            scoreBoard.setText("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives));
        if (!craft.isAlive()) {
            if (!craft.isReset_active()) {
                scoreBoard.setText("You Lose! Final Score: " + String.valueOf(score));
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridy = 1;

                add(resetRequest, gbc);
                resetRequest.setVisible(true);
            }
        }

        if (asteroid.isActive() && craft.isAlive()) {
            g2d.rotate(Math.toRadians(degrees+=asteroid.getRotation()), asteroid.getX()+25, asteroid.getY()+25);
            g2d.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(), this);
        }



        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void reset() {
        craft.setReset_active(false);
        this.removeAll();
        SpaceyStuff.reset();
    }

    public void actionPerformed(ActionEvent e) {
        //setBackground(Color.black); //Only needed this for flickering
        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) {
                m.move();
                if ((m.getX() >= enemy.getXBoxNeg() && m.getX() <= enemy.getXBoxPos()) && (m.getY() >= enemy.getYBoxNeg() && m.getY() <= enemy.getYBoxPos())) {
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
        if (craft.isVisible()) {
            craft.move();
            craft.cool();
        }

        asteroid.move();

        if (!asteroid.isActive()) {
            asteroid = new Asteroid();
            asteroid.setActive(true);
        }
        else {
            if (craft.getX() >= asteroid.getXBoxNeg() && craft.getX() <= asteroid.getXBoxPos() && craft.getY() >= asteroid.getYBoxNeg() && craft.getY() <= asteroid.getYBoxPos()) {
                lives--;
                asteroid.setActive(false);
                craft.setVisible(false);
            }
        }

        repaint();
        if (craft.isReset_active())
            reset();
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