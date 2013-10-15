package SpaceyStuff;

import javax.swing.*;
import java.awt.*;


public class Missile {

    private int x, y;
    private Image image;
    boolean visible;

    private final int BOARD_WIDTH = 900;
    private final int MISSILE_SPEED = 3;

    public Missile(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/PewPew.png"));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
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

    public boolean isVisible() {
        return visible;
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}
