package SpaceyStuff;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Random;

public class Craft {

    private String craft = "SpaceShip.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int heat;
    private Image image;
    private boolean alive;

    private ArrayList missiles;

    private final int CRAFT_SIZE = 10;
    private final int CRAFT_SPEED = 2;

    private boolean reset_active = false;

    private Random generator = new Random();

    //private int randomX = generator.nextInt(750);
    //private int randomY = generator.nextInt(550);

    public Craft() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        missiles = new ArrayList();
        x = 40;
        y = 300;
        heat = 0;
        alive = true;
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

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_R && !isAlive()) {
            reset_active = true;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            dy = -CRAFT_SPEED;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            dy = CRAFT_SPEED;
        }
    }
/*    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            if (getX() <= -80) {
                x = 800;
            }
            else {
                dx = -1;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (getX() >= 800) {
                x = -80;
            }
            else {
            dx = 1;
            }
        }

        if (key == KeyEvent.VK_UP) {
            if (getY() <= -50) {
                y = 600;
            }
            else {
            dy = -1;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if (getY() >= 600) {
                y = -50;
            }
            else {
            dy = 1;
            }
        }
    }*/

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

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

/*        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }*/

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
        this.x = -9999;
        this.y = -9999;
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
}
