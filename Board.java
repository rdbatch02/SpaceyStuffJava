package SpaceyStuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.io.*;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Enemy enemy;
    private Craft craft;
    private int asteroid_count = 0;
    private int score;
    private int lives;
    private ArrayList as;
    private int multiplier = 1;
    private int m_incrementer = 1;
    private int level = 1;
    private boolean onCabinet;

    private int visible_timer = 100;

    private final int ASTEROID_TOTAL = 3;

    private JLabel scoreBoard;
    private JLabel resetRequest;
    private Font sans;

    public Board() {

        //onCabinet = isCab();

        addKeyListener(new TAdapter());
        setDoubleBuffered(true);
        setOpaque(false);

        //craft = new Craft(onCabinet);
        craft = new Craft();
        enemy = new Enemy();
        score = 0;
        int multiplier = 1;
        lives = 3;
        as = new ArrayList();

        sans = new Font("Sans-Serif", Font.BOLD, 26);

        scoreBoard = new JLabel("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives) + " Multiplier: " + String.valueOf(multiplier));
        scoreBoard.setForeground(Color.white);
        scoreBoard.setFont(sans);
        add(scoreBoard);

        resetRequest = new JLabel("Press R to play again");
        resetRequest.setForeground(Color.white);
        resetRequest.setFont(sans);
        resetRequest.setVisible(false);

        timer = new Timer(15, this);
        timer.start();
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

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

        for (Object m1 : ms) {
            Missile m = (Missile) m1;
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }

        if (craft.getHeat() >= 950)
            scoreBoard.setText("Weapon Overheat!");
        else
            scoreBoard.setText("Score: " + String.valueOf(score) + " Lives: " + String.valueOf(lives) + " Multiplier: " + String.valueOf(multiplier));
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
        if (asteroid_count != 0)  {
            for (Object a1 : as) {
                Asteroid a = (Asteroid) a1;
                if (a.isActive() && craft.isAlive()) {
                    AffineTransform oldTrans = g2d.getTransform();
                    g2d.rotate(Math.toRadians(a.getDegrees()), a.getX() + (a.getWidth() / 2), a.getY() + (a.getHeight() / 2));
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
                    g2d.setTransform(oldTrans);
                }
            }
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
        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) {
                m.move();
                if ((m.getX() >= enemy.getXBoxNeg() && m.getX() <= enemy.getXBoxPos()) && (m.getY() >= enemy.getYBoxNeg() && m.getY() <= enemy.getYBoxPos())) {
                    enemy.setAlive(false);
                    enemy = new Enemy();
                    ms.remove(i);
                    score += multiplier;
                    m_incrementer++;
                    if (m_incrementer > 5) {
                        m_incrementer = 0;
                        multiplier++;
                    }
                }
            }
            else {
                ms.remove(i);
            }
        }
        enemy.move();
        if (enemy.getX() <= -80) {
            enemy.setAlive(false);
            //lives--; //For now, you don't lose lives for letting enemies get away.
            multiplier = 1;
            m_incrementer = 0;
            if (craft.isAlive()) {
                enemy = new Enemy();
            }
        }
        if (lives == 0) {
            enemy.setAlive(false);
            as.clear();
            craft.setAlive(false);
        }
        if (craft.isVisible()) {
            craft.move();
            craft.cool();
        }

        if (asteroid_count < ASTEROID_TOTAL) {
            Asteroid a = new Asteroid();
            a.setActive(true);
            as.add(a);
            asteroid_count++;
        }

        if (craft.isAlive()){
            for (int i = 0; i < as.size(); i++) {
                Asteroid a = (Asteroid) as.get(i);
                if (a.isActive()) {
                    a.move();
                    if (craft.getX() >= a.getXBoxNeg() && craft.getX() <= a.getXBoxPos() && craft.getY() >= a.getYBoxNeg() && craft.getY() <= a.getYBoxPos()) {
                        lives--;
                        a.setActive(false);
                        craft.setVisible(false);
                        as.remove(i);
                        asteroid_count--;
                        multiplier = 1;
                        m_incrementer = 0;
                    }
                    else if (a.getX() <= -60) {
                        as.remove(i);
                        asteroid_count--;
                    }
            }

            }
        }
            repaint();
        if (craft.isReset_active())
            reset();
    }

    /*private boolean isCab() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config.txt"));
            if (br.readLine() == "true") {
                System.err.println("Config accepted, entering game cabinet mode!");
                return true;
            }
            else {
                System.err.println("Config rejected, reverting to PC mode.");
                return false;
            }
        }
        catch(FileNotFoundException b) {
            System.err.println("Config file not found! Reverting to PC mode.");
            return false;
        }
        catch(IOException b) {
            System.err.println("Config file not found! Revering to PC mode.");
            return false;
        }
    }*/

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}