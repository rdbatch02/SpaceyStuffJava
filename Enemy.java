package SpaceyStuff;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//import java.util.ArrayList;

public class Enemy {

    //private String enemy = "BaddieShip.png";

    private int x;
    private int y;
    private Image image;
    private boolean alive;

    private String[] ships = new String[]{"images/BaddieShip.png", "images/BaddieShip2.png"};

    private final int ENEMY_SPEED = 2;
    private final int HIT_BOX_VERTICAL = 22;
    private final int HIT_BOX_HORIZONTAL = 15;

    private Random generator = new Random();

    //private int randomX = 100 + (int)(Math.random() * ((750-100) + 1));
    private int randomShip = generator.nextInt(2);

    private String enemy = ships[randomShip];

    public Enemy() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(enemy));
        image = ii.getImage();
        x = 850;
        y = generator.nextInt(550);
        alive = true;

    }


    public void move() {
        x -= ENEMY_SPEED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getYBoxPos() {
        return y + HIT_BOX_VERTICAL;
    }

    public int getYBoxNeg() {
        return y - HIT_BOX_VERTICAL;
    }

    public int getXBoxPos() {
        return x + HIT_BOX_HORIZONTAL;
    }

    public int getXBoxNeg() {
        return x - HIT_BOX_HORIZONTAL;
    }

    public Image getImage() {
        return image;
    }

    public boolean isAlive() {
        return alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
        this.x = -9999;
        this.y = -9999;
    }

    /*  We don't need these right now, but we might when the enemies can actually shoot back.

    public ArrayList getMissiles() {
        return missiles;
    }

    public void fire() {
        missiles.add(new SpaceyStuff.Missile(x, y + CRAFT_SIZE/2));
    }*/

}
