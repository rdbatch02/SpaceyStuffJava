package SpaceyStuff;

/*
Identical copy of Craft.java with some keybindings changed for the game cabinet
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class CraftCabinet {

    //private int dx;
    private int dy;
    private int x;
    private int y;
    private int heat;
    private Image image;
    private boolean alive;
    private boolean visible;

    private ArrayList missiles;

    private final int CRAFT_SIZE = 10;
    private final int CRAFT_SPEED = 2;

    private boolean reset_active = false;

    private String[] ships = new String[]{"images/SpaceShip.png", "images/SpaceShip2.png"};

    private Random generator = new Random();

    private int randomShip = generator.nextInt(2);

    private String craft = ships[randomShip];

    public CraftCabinet() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        missiles = new ArrayList();
        x = 40;
        y = 300;
        heat = 0;
        alive = true;
        visible = true;
    }


    public void move() {
        if (y <= -50) {
            y = 600;
        }
        else if (y >= 600) {
            y = -50;
        }
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void fire() {
        if (heat <= 1000) {
            missiles.add(new Missile(x + CRAFT_SIZE/2, y));
            heat += 125;
        }
    }

    public void cool() {
        if (heat > 0)
            heat--;
    }

    public int getHeat() {
        return heat;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        reset();
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isReset_active() {
        return reset_active;
    }

    public void setReset_active(boolean reset_active) {
        this.reset_active = reset_active;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public void reset() {
        setHeat(0);
        setVisible(true);
        this.y = 300;
    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_R) { //Cab = A
            fire();
        }

        if (key == KeyEvent.VK_H && !isAlive()) { //Cab = F
            reset_active = true;
        }

        if (key == KeyEvent.VK_W) { //Cab = UP
            dy = -CRAFT_SPEED;
        }

        if (key == KeyEvent.VK_S) { //Cab = DOWN
            dy = CRAFT_SPEED;
        }
    }
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}
