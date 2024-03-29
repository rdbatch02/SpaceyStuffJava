package SpaceyStuff;

import javax.swing.*;

public class SpaceyStuff extends JFrame {

    public static SpaceyStuff game;

    public SpaceyStuff() {
        ImageIcon background_img = new ImageIcon(this.getClass().getResource("images/background.gif"));
        BackgroundPanel background = new BackgroundPanel(background_img.getImage(), BackgroundPanel.ACTUAL, 0.50f, 0.5f);
        this.setContentPane(background);
        Board b = new Board();
        this.getContentPane().add(b);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Spacey Stuff");
        setResizable(false);
        setVisible(true);
        //System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString()); //Fix for super slow-mo on first run caused by Aero.
        ImageIcon taskbarIcon = new ImageIcon("images/taskbar_icon.gif");
        setIconImage(taskbarIcon.getImage());
    }

    public static void reset() {
        game = new SpaceyStuff();
    }

    public static void main(String[] args) {
        //System.out.println("Let's do this!");
        game = new SpaceyStuff();
        //reset(); //This is a terrible band-aid, but this should hopefully fix the issue with input not working on the first init. Removed for now.
    }

}
