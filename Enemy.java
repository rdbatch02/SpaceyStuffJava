package SpaceyStuff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.Random;

public class Enemy {

    //private String enemy = "BaddieShip.png";

    private int x;
    private int y;
    private Image image;
    private boolean alive;

    private String[] ships = new String[]{"images/BaddieShip.png", "images/BaddieShip2.png"};

    private final int ENEMY_SPEED = 2;

    private Random generator = new Random();

    //private int randomX = 100 + (int)(Math.random() * ((750-100) + 1));
    private int randomY = generator.nextInt(550);
    private int randomShip = generator.nextInt(2);

    private String enemy = ships[randomShip];

    public Enemy() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(enemy));
        image = ii.getImage();
        x = 850;
        y = randomY;
        alive = true;

    }


    public void move() {
        x -= ENEMY_SPEED;
/*        if (x <= -80) {
            x = 800;
        }
        else if (x >= 800) {
            x = -80;
        }
        else if (y <= -50) {
            y = 600;
        }
        else if (y >= 600) {
            y = -50;
        }
        x += dx;
        y += dy;*/
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getYBoxPos() {
        return y + 20;
    }

    public int getYBoxNeg() {
        return y - 20;
    }

    public int getXBoxPos() {
        return x + 20;
    }

    public int getXBoxNeg() {
        return x - 20;
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
