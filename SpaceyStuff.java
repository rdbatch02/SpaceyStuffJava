package SpaceyStuff;

import javax.swing.JFrame;

public class SpaceyStuff extends JFrame {

    public SpaceyStuff() {
        add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Spacey Stuff");
        setResizable(false);
        setVisible(true);
    }


    public static void main(String[] args) {
        System.out.println("Let's do this!");
        new SpaceyStuff();
    }

}
