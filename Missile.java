package SpaceyStuff;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Missile {

    private int x, y;
    private Image image;
    boolean visible;

    private final int BOARD_WIDTH = 900;
    private final int BOARD_HEIGHT = 700;
    private final int MISSILE_SPEED = 3;

    public Missile(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("PewPew.png"));
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

    public int getYBoxPos() {
        return y + 10;
    }

    public int getYBoxNeg() {
        return y - 10;
    }

    public int getXBoxPos() {
        return x + 10;
    }

    public int getXBoxNeg() {
        return x - 10;
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
