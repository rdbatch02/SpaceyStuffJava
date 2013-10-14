package SpaceyStuff;

import javax.swing.*;
import java.awt.*;

import java.util.Random;


public class Asteroid {

    private int x, y;
    private Image image;
    private boolean active = false;

    private Random generator = new Random();

    private final int BOARD_WIDTH = 900;
    private final int BOARD_HEIGHT = 700;
    private final int ASTEROID_SPEED = 1;

    private int RandomY = generator.nextInt(550);

    public Asteroid() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/asteroid.png"));
        image = ii.getImage();
        this.x = 850;
        this.y = RandomY;
        active = true;
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

    public void move() {
        x -= ASTEROID_SPEED;
        if (x < -200) {
            active = false;
        }
    }
}
