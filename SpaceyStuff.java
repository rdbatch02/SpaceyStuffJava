package SpaceyStuff;

import javax.swing.JFrame;

public class SpaceyStuff extends JFrame {

    public static SpaceyStuff game;

    public SpaceyStuff() {
        add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Spacey Stuff");
        setResizable(false);
        setVisible(true);
    }

    public static void reset() {
        game.dispose();
        game.setVisible(false);
        game = new SpaceyStuff();
    }


    public static void main(String[] args) {
        System.out.println("Let's do this!");
        game = new SpaceyStuff();
    }

}
