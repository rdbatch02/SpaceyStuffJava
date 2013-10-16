package SpaceyStuff;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Asteroid {

    private int x, y, width, height;
    private Image image;
    private boolean active = false;
    private int ASTEROID_SPEED;
    private int ROTATION_SPEED;
    private double degrees;

    private Random generator = new Random();

    public Asteroid() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/asteroid.png"));
        image = ii.getImage();
        this.x = 850;
        this.y = generator.nextInt(550);
        active = true;
        int type = generator.nextInt(5) + 1;
        ASTEROID_SPEED = type;
        ROTATION_SPEED = type;
        degrees = 0;
        width = ii.getIconWidth();
        height = ii.getIconHeight();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getDegrees() {
        degrees += ROTATION_SPEED;
        return degrees;
    }

    public void move() {
        x -= ASTEROID_SPEED;
        if (x < -200) {
            active = false;
        }
    }
}
